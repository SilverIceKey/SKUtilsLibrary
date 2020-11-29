package com.silverknife.meizhi.mvp.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silvericekey.skutilslibrary.utils.permission.PermissionUtil
import com.silvericekey.skutilslibrary.utils.qrcode.QRCodeUtil
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.TestPresenter
import com.silverknife.meizhi.mvp.ui.activity.TabsActivity
import com.silverknife.meizhi.mvp.ui.interfaces.ITestView
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : BaseFragment<TestPresenter>(), ITestView {
    var textStr = "暂无数据"

    companion object {
        @JvmStatic
        fun newInstance(): TestFragment {
            val fragment = TestFragment()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_test
    }

    override fun initView(view: View) {
        text.text = textStr
        request.setOnClickListener { request() }
        websocker_request.setOnClickListener { webSocketRequest() }
        tabs.setOnClickListener { tabs() }
        request_install_permission.setOnClickListener { requestInstallPermission() }
        create_qr_code.setOnClickListener { createQRCode() }
    }

    override fun initPresenter(): TestPresenter {
        return TestPresenter(this)
    }

    override fun setResponse(response: String) {
        textStr = response
    }

    fun request() {
        mPresenter?.getData()
    }

    fun webSocketRequest() {
        mPresenter?.webSocketRequest()
    }

    fun tabs() {
        var intent = Intent(activity, TabsActivity::class.java)
        startActivity(intent)
    }

    fun requestInstallPermission() {
        PermissionUtil.canInstallAPK()
    }

    fun createQRCode() {
        if (qr_info.text.toString().isEmpty()) {
            ToastUtils.showShort("请先输入二维码信息")
        }
        qr_img.setImageBitmap(QRCodeUtil.createQRCode(qr_info.text.toString(), 300, Color.parseColor("#00ffff")))
    }
}