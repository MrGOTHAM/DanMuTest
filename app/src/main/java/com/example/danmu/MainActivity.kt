package com.example.danmu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import master.flame.danmaku.controller.DrawHandler
import master.flame.danmaku.danmaku.model.BaseDanmaku
import master.flame.danmaku.danmaku.model.DanmakuTimer
import master.flame.danmaku.danmaku.model.IDanmakus
import master.flame.danmaku.danmaku.model.android.DanmakuContext
import master.flame.danmaku.danmaku.model.android.Danmakus
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser
import android.os.Build
import android.text.Editable
import android.text.TextUtils
import kotlin.random.Random


class MainActivity : AppCompatActivity(), View.OnClickListener {


    private val url = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"

    private var showDanmu: Boolean = false

    // 用于对弹幕各种全局配置进行绑定
    private val danmakuContext = DanmakuContext.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videoPlay()
        danmukuPlay()

        dan_mu_ku_view.setOnClickListener(this)
        send.setOnClickListener(this)
        // 避免自己发送弹幕后，失去焦点，退出沉浸模式，这里对系统全局Ui变化进行监听，保证处于沉浸模式
        window.decorView.setOnSystemUiVisibilityChangeListener{
            if (it == View.SYSTEM_UI_FLAG_VISIBLE){
                onWindowFocusChanged(true)
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.dan_mu_ku_view ->{
            if (edit_layout.visibility == View.GONE){
                edit_layout.visibility = View.VISIBLE
            }else{
                edit_layout.visibility = View.GONE
            }
            }
            R.id.send -> {
                val text = edit_text.text.toString()
                if (!TextUtils.isEmpty(text)){
                    addDanmaku(text,true)
                    edit_text.text=Editable.Factory.getInstance().newEditable("")
                }
            }
        }
    }

    private fun danmukuPlay() {
        // 提升绘制效率
        dan_mu_ku_view.enableDanmakuDrawingCache(true)
        // 设置回调函数
        dan_mu_ku_view.setCallback(object : DrawHandler.Callback {
            override fun drawingFinished() {

            }

            override fun danmakuShown(danmaku: BaseDanmaku?) {

            }

            override fun prepared() {
                showDanmu = true
                dan_mu_ku_view.start()
                generateSomeDanmaku()
            }

            override fun updateTimer(timer: DanmakuTimer?) {

            }
        })
        // 准备，包含了解析器、弹幕全局context
        dan_mu_ku_view.prepare(parser, danmakuContext)
    }

    // 添加一条弹幕
    private fun addDanmaku(text: String, withBorder: Boolean) {
        // createDanmaku创建一个从右向左滚动的弹幕
        val danmuku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL)
        danmuku.padding = 5
        danmuku.text = text
        danmuku.textSize = sp2px(20F).toFloat()
        danmuku.textColor = Color.WHITE
        danmuku.time = dan_mu_ku_view.currentTime
        if (withBorder) {
            danmuku.borderColor = Color.GREEN
        }
        dan_mu_ku_view.addDanmaku(danmuku)
    }

    // 生成随机的消息
    private fun generateSomeDanmaku() {
        run {
            while (showDanmu) {
                val time: Long = Random.nextLong(300)
                val text = "" + time + time
                // 发送消息时，调用addDanmaku
                addDanmaku(text, false)
                try {
                    Thread.sleep(time)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * sp转px的方法。
     */
    private fun sp2px(spValue: Float): Int {
        val fontScale = resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    override fun onPause() {
        super.onPause()
        if (dan_mu_ku_view != null && dan_mu_ku_view.isPaused) {
            dan_mu_ku_view.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (dan_mu_ku_view != null && dan_mu_ku_view.isPrepared && dan_mu_ku_view.isPaused) {
            dan_mu_ku_view.resume()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dan_mu_ku_view != null) {
            showDanmu = false
            dan_mu_ku_view.release()
        }
    }

    // 沉浸式界面
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    private fun videoPlay() {
        video_view.setVideoPath(url)
        video_view.start()
    }

    // 弹幕解析器
    private val parser = object : BaseDanmakuParser() {
        override fun parse(): IDanmakus {
            return Danmakus()
        }
    }
}
