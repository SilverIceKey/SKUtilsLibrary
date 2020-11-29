package com.silverknife.meizhi.features.feature_news

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silverknife.meizhi.R
import kotlinx.android.synthetic.main.fragment_news_list.*

/**
 * <pre>
 *     time   : 2020/07/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class NewsListFragment : BaseFragment<NewsListPresenter>() {
    companion object {
        @JvmStatic
        fun newInstance(type: String): NewsListFragment {
            val args = Bundle()
            args.putString("type", type)
            val fragment = NewsListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_news_list
    }

    override fun initView(view: View) {
        var type = arguments?.getString("type")
        var adapter = NewListAdapter()
        adapter.setOnItemClickListener { adapter, view, position ->
            var item = adapter.getItem(position) as NewsModel
            var intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("url", item.url)
            startActivity(intent)
        }
        recycler.layoutManager = LinearLayoutManager(context)
        adapter.bindToRecyclerView(recycler)
        mPresenter?.getData(type!!, {
            adapter.setNewData(it)
            refresh.isRefreshing = false
        }, {
            refresh.isRefreshing = false
        })
        refresh.setOnRefreshListener {
            mPresenter?.getData(type!!, {
                adapter.setNewData(it)
                refresh.isRefreshing = false
            }, { refresh.isRefreshing = false })
        }
    }

    override fun initPresenter(): NewsListPresenter {
        return NewsListPresenter()
    }
}