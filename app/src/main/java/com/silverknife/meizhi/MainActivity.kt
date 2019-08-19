package com.silverknife.meizhi

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.silvericekey.skutilslibrary.netUtils.HttpUtils
import com.silvericekey.skutilslibrary.uiUtils.SKToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun request(view: View) {
        HttpUtils.getInstance().addInterceptor(TestInterceptor())
                .obtainClass(Api::class.java)
                .login("18368402184", "400938")
                .setSchedulers()
                .subscribe({
                    SKToastUtils.showToast("登录成功")
                },{
                    SKToastUtils.showToast("登录失败")
                },{
                    SKToastUtils.showToast("登录结束")
                })

    }

}
