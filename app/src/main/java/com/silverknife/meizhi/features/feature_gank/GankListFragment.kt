package com.silverknife.meizhi.features.feature_gank

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silverknife.meizhi.R
import kotlinx.android.synthetic.main.fragment_gank_list.*
import java.io.Serializable

class GankListFragment : BaseFragment<GankListPresenter>() {
    var gankListAdapter: GankListAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance(data: ArrayList<GankItemModel>): GankListFragment {
            val fragment = GankListFragment()
            val args = Bundle()
            args.putSerializable("data", data as Serializable)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_gank_list
    }

    override fun initView(view: View) {
        gankListAdapter = GankListAdapter()
        recycler.layoutManager = LinearLayoutManager(activity)
        gankListAdapter!!.bindToRecyclerView(recycler)
        gankListAdapter!!.setOnItemClickListener { adapter, view, position ->
            var item: GankItemModel = adapter.getItem(position) as GankItemModel
            if (item.url.endsWith(".jpg")) {
                var intent = Intent(activity, PhotoViewActivity::class.java)
                intent.putExtra("url", item.url)
                intent.putExtra("title", item.desc)
                initOptionsCompat(Pair.create(view.findViewById(R.id.content_title), PhotoViewActivity.TITLE))
                initOptionsCompat(Pair.create(view.findViewById(R.id.image), PhotoViewActivity.IMAGE))
                startActivity(intent)
            } else {
                var intent = Intent(activity, GankDetailActivity::class.java)
                intent.putExtra("url", item.url)
                intent.putExtra("title", item.desc)
                startActivity(intent)
            }
        }
        gankListAdapter!!.listener = object : GankListAdapter.onImagesClickListener {
            override fun onImagesClick(url: String) {
                var intent = Intent(activity, PhotoViewActivity::class.java)
                intent.putExtra("url", url)
                initOptionsCompat(Pair.create(view, PhotoViewActivity.IMAGE))
                startActivity(intent)
            }
        }
        gankListAdapter!!.setOnItemChildClickListener { adapter, view, position ->
            var item: GankItemModel = adapter.getItem(position) as GankItemModel
            when (view.id) {
                R.id.images -> {
                    var intent = Intent(activity, GankDetailActivity::class.java)
                    intent.putExtra("url", item.url)
                    intent.putExtra("title", item.desc)
                    startActivity(intent)
                }
                R.id.image -> {
                    var intent = Intent(activity, PhotoViewActivity::class.java)
                    intent.putExtra("url", item.url)
                    intent.putExtra("title", item.desc)
                    var optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!,
                            Pair.create(view, PhotoViewActivity.IMAGE))
                    startActivity(intent, optionsCompat.toBundle())
                }
            }
        }
        gankListAdapter!!.setNewData(arguments!!.getSerializable("data") as List<GankItemModel>)
//        mPresenter!!.getList(arguments!!.getString("data"), {
//            gankListAdapter!!.setNewData(it)
//        })
        refresh.setOnRefreshListener {
//            mPresenter!!.getList(arguments!!.getString("category"), {
//                gankListAdapter!!.setNewData(it)
//                refresh.isRefreshing = false
//            })
            refresh.isRefreshing = false
        }
//        PagerSnapHelper().attachToRecyclerView(recycler)
    }

    override fun initPresenter(): GankListPresenter {
        return GankListPresenter()
    }
}