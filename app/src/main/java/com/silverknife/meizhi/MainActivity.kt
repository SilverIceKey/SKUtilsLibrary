package com.silverknife.meizhi

import android.graphics.Color
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress_circular.setShowText(true)
        progress_circular.setTextColor(Color.parseColor("#000000"))
        progress_circular.setOnProgressListener { progress -> seekbar.setProgress(progress.toInt()) }
        progress_circular.setDuration(4000)
        progress_circular.startAnime()
        seekbar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                println(progress)
                progress_circular.setProgress(progress)
            }

        })
    }
}
