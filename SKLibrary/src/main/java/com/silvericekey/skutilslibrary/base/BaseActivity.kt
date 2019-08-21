package com.silvericekey.skutilslibrary.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T : BasePresenter> : AppCompatActivity() {
    protected lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        mPresenter = initPresenter()
        initView()
    }

    abstract fun getLayoutID():Int

    abstract fun initView()

    abstract fun initPresenter(): T

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }
}