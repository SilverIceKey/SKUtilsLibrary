package com.silverknife.meizhi.mvp.adapter

import android.database.DataSetObserver
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.core.view.setPadding

class DropDownAdapter(var data: ArrayList<String>) : SpinnerAdapter {
    override fun isEmpty(): Boolean {
        return data.size == 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var textView = TextView(parent?.context)
        textView.setText(data[position])
        textView.gravity = Gravity.CENTER
        textView.setPadding(20)
        return textView
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var textView = TextView(parent?.context)
        textView.setText(data[position])
        textView.gravity = Gravity.CENTER
        textView.setPadding(20)
        return textView
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
    }

    override fun getCount(): Int {
        return data.size
    }
}