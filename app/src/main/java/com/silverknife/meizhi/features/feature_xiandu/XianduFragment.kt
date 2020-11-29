package com.silverknife.meizhi.features.feature_xiandu

import android.view.View
import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.get
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silvericekey.skutilslibrary.inline.toString
import com.silvericekey.skutilslibrary.utils.FileIOUtil
import com.silverknife.meizhi.R
import com.silverknife.meizhi.features.feature_xiandu.XianduPresenter
import kotlinx.android.synthetic.main.fragment_xiandu.*

class XianduFragment: BaseFragment<XianduPresenter>() {
    override fun getLayoutID(): Int {
        return R.layout.fragment_xiandu
    }

    override fun initView(view: View) {
        var list: JsonElement = Gson().fromJson(FileIOUtil.getInstance().readFile2StringFromAssets("data.json"))
        json.text = list["results"]["Android"][0]["desc"].toString(true)
    }

    override fun initPresenter(): XianduPresenter {
        return XianduPresenter()
    }
}