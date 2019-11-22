package com.silverknife.meizhi.mvp.ui.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silvericekey.skutilslibrary.utils.NumberAnimUtils
import com.silvericekey.skutilslibrary.utils.PermissionUtil
import com.silvericekey.skutilslibrary.utils.QRCodeUtil
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.adapter.DropDownAdapter
import com.silverknife.meizhi.mvp.presenter.TestPresenter
import com.silverknife.meizhi.mvp.ui.activity.AnimeMoveActivity
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
        anime_move.setOnClickListener { startActivity(Intent(activity, AnimeMoveActivity::class.java)) }
        start.setOnClickListener { start() }
        pause.setOnClickListener { pause() }
        reset.setOnClickListener { reset() }
        var data = arrayListOf<String>()
        data.add("男")
        data.add("女")
        spinner.adapter = DropDownAdapter(data)
        numView.text = "0"
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

    lateinit var numberAnimUtils: NumberAnimUtils
    fun start() {
        isPause = false
        numberAnimUtils = NumberAnimUtils().setEnd("1000000000.0")
        numberAnimUtils.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                pause.text = "暂停"
                isPause = false
            }
        }).playOn(numView)
    }

    var isPause = false
    fun pause() {
        if (!numberAnimUtils.isRunning()){
            ToastUtils.showShort("请先开始")
            return
        }
        if (!isPause) {
            numberAnimUtils.pause()
            pause.text = "继续"
        } else {
            numberAnimUtils.resume()
            pause.text = "暂停"
        }
        isPause = !isPause
    }

    fun reset() {
        numberAnimUtils.stop()
        numView.text = "0"
        pause.text = "暂停"
        isPause = false
    }

}