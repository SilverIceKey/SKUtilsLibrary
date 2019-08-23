package com.silverknife.meizhi

import android.content.Intent
import android.view.View
import com.silvericekey.skutilslibrary.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(),IMainView {
    override fun setResponse(response: String) {
        textStr = response
    }

    override fun getLayoutID(): Int = R.layout.activity_main
    var textStr = "暂无数据"
    override fun initView() {
        text.text = textStr
        camera.setOnClickListener { startActivity(Intent(this@MainActivity,CameraActivity::class.java)) }
    }

    override fun initPresenter(): MainPresenter = MainPresenter(this@MainActivity)

    fun request(view: View) {
        mPresenter.getData()
    }
    fun webSocketRequest(view: View) {
        mPresenter.webSocketRequest()
    }
}
