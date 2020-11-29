package com.silverknife.meizhi.features.feature_test

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silvericekey.skutilslibrary.utils.*
import com.silverknife.meizhi.R
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.fragment_test.*
import me.jessyan.autosize.AutoSize
import java.io.File


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
    override fun fitSystemBar(): Boolean {
        return true
    }
    override fun getLayoutID(): Int {
        return R.layout.fragment_test
    }

    @SuppressLint("WrongConstant")
    override fun initView(view: View) {
        text.text = textStr
        request.setOnClickListener { request() }
        tabs.setOnClickListener { tabs() }
        request_install_permission.setOnClickListener { requestInstallPermission() }
        create_qr_code.setOnClickListener { createQRCode() }
        anime_move.setOnClickListener { startActivity(Intent(activity, BlockAnimMoveActivity::class.java)) }
        start.setOnClickListener { start() }
        pause.setOnClickListener { pause() }
        reset.setOnClickListener { reset() }
        select_image.setOnClickListener {
            PictureSelectorUtil.getInstance().openGallery(this,{
                ShareUtil.getInstance().shareImage("分享",Uri.parse(it!![0].path))
            },{
                ToastUtils.showShort("操作取消")
            })
        }
        rn.setOnClickListener { startActivity(Intent(activity, RNActivity::class.java)) }
        var data = arrayListOf<String>()
        data.add("男")
        data.add("女")
        spinner.adapter = DropDownAdapter(data)
        numView.text = "0"
        flashlight.setOnClickListener {
            if (!PermissionUtils.isGranted(Manifest.permission.CAMERA)) {
                PermissionUtils.permission(Manifest.permission.CAMERA).callback(object : PermissionUtils.SimpleCallback {
                    override fun onGranted() {
                        flashLightStatus()
                    }

                    override fun onDenied() {

                    }

                }).request()
                return@setOnClickListener
            }
            flashLightStatus()
        }
        btnStatus.setOnClickListener { ToastUtils.showShort("按钮点击成功") }
        btnStatusChange.setOnClickListener { btnStatusChange() }
        img.setOnClickListener {
            Blurry.with(context)
                    .radius(10)
                    .color(Color.argb(66, 0, 255, 255))
                    .async()
                    .capture(img)
                    .into(img)
        }
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

    fun tabs() {
        var intent = Intent(activity, TabsActivity::class.java)
        startActivity(intent)
    }

    fun requestInstallPermission() {
        PermissionUtil.getInstance().canInstallAPK()
    }

    fun createQRCode() {
        if (qr_info.text.toString().isEmpty()) {
            ToastUtils.showShort("请先输入二维码信息")
            return
        }
        qr_img.setImageBitmap(QRCodeUtil.getInstance().createQRCode(qr_info.text.toString(), 300, Color.parseColor("#00ffff")))
    }

    var numberAnimUtils: NumberAnimUtil?=null
    fun start() {
        isPause = false
        numberAnimUtils = NumberAnimUtil().setEnd("1000000000.0")
        numberAnimUtils?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                pause.text = "暂停"
                isPause = false
            }
        })?.playOn(numView)
    }

    var isPause = false
    fun pause() {
        if (numberAnimUtils==null||!numberAnimUtils!!.isRunning()) {
            ToastUtils.showShort("请先开始")
            return
        }
        if (!isPause) {
            numberAnimUtils?.pause()
            pause.text = "继续"
        } else {
            numberAnimUtils?.resume()
            pause.text = "暂停"
        }
        isPause = !isPause
    }

    fun reset() {
        numberAnimUtils?.stop()
        numView.text = "0"
        pause.text = "暂停"
        isPause = false
    }

    var flashEnable = false
    fun flashLightStatus() {
        flashEnable = !flashEnable
        CameraUtil.getInstance().enableFlashLight(flashEnable)
        if (flashEnable) {
            flashlight.text = "关闭闪光灯"
        } else {
            flashlight.text = "打开闪光灯"
        }
    }

    fun btnStatusChange() {
        if (btnStatus.isEnabled) {
            btnStatus.text = "不可点击"
        } else {
            btnStatus.text = "可点击"
        }
        btnStatus.isEnabled = !btnStatus.isEnabled
    }

}