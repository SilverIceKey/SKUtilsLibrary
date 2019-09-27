package com.silverknife.meizhi.mvp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.adapter.GankListAdapter
import com.silverknife.meizhi.mvp.presenter.GankListPresenter
import kotlinx.android.synthetic.main.fragment_gank_list.*

class GankListFragment : BaseFragment<GankListPresenter>() {
    var gankListAdapter: GankListAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance(category: String): GankListFragment {
            val fragment = GankListFragment()
            val args = Bundle()
            args.putString("category", category)
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
        mPresenter!!.getList(arguments!!.getString("category"), {
            gankListAdapter!!.setNewData(it)
        })
        refresh.setOnRefreshListener {
            mPresenter!!.getList(arguments!!.getString("category"), {
                gankListAdapter!!.setNewData(it)
                refresh.isRefreshing = false
            })
        }
    }

    override fun initPresenter(): GankListPresenter {
        return GankListPresenter()
    }
}