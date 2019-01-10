package com.wanve.mykotlin.base

import android.content.Context
import android.graphics.PixelFormat
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.wanve.mykotlin.R
import com.wanve.mykotlin.widget.MultipleStatusView
import org.greenrobot.eventbus.EventBus

/**
 * Created by zhou
 * on 2019/1/10.
 */
abstract class BaseActivity : AppCompatActivity(){

    //布局view
    protected abstract fun attachLayoutRes() : Int
    //判断是否使用evenBus
    open fun userEventBus() : Boolean = true
    //初始化数据
    abstract fun initData()
    //布局文件id
    abstract fun initView()
    //开始强求
    abstract fun start()
    protected var mLayoutStatusView: MultipleStatusView? = null
    /**
     * 提示View
     */
    protected lateinit var mTipView : View
    protected lateinit var mWindowManager: WindowManager
    protected lateinit var mLayoutParams: WindowManager.LayoutParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(attachLayoutRes())
        if (userEventBus()){
            EventBus.getDefault().register(this)
        }

        initData()
        initTipView()
        initView()
        start()
        initListtener()
    }

    private fun initTipView(){
        mTipView = layoutInflater.inflate(R.layout.layout_network_tip, null)
        mWindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mLayoutParams = WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT)
        mLayoutParams.gravity = Gravity.TOP
        mLayoutParams.x = 0
        mLayoutParams.y = 0
        mLayoutParams.windowAnimations = R.style.anim_float_view // add animations
    }

    private fun initListtener(){
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        start()
    }
}
