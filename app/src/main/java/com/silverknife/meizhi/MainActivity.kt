package com.silverknife.meizhi

import android.content.Intent
import android.graphics.Color
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silvericekey.skutilslibrary.utils.PermissionUtil
import com.silvericekey.skutilslibrary.utils.QRCodeUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), IMainView {
    override fun setResponse(response: String) {
        textStr = response
    }

    override fun getLayoutID(): Int = R.layout.activity_main
    var textStr = "暂无数据"
    override fun initView() {
        text.text = textStr
    }

    override fun initPresenter(): MainPresenter = MainPresenter(this@MainActivity)

    fun request(view: View) {
        mPresenter.getData()
    }

    fun camera(view: View) {
        startActivity(Intent(this@MainActivity, CameraActivity::class.java))
    }

    fun webSocketRequest(view: View) {
        mPresenter.webSocketRequest()
    }

    fun requestInstallPermission(view: View) {
        PermissionUtil.canInstallAPK()
    }

    fun createQRCode(view: View){
        if (qr_info.text.toString().isEmpty()){
            ToastUtils.showShort("请先输入二维码信息")
        }
        qr_img.setImageBitmap(QRCodeUtil.createQRCode(qr_info.text.toString(),300,Color.parseColor("#00ffff")))
    }
}
