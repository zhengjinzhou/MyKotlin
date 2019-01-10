package com.wanve.mykotlin.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.wanve.mykotlin.R
import com.wanve.mykotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    protected var alphaAnimation: AlphaAnimation? = null

    override fun userEventBus(): Boolean {
        return false
    }

    override fun attachLayoutRes(): Int = R.layout.activity_splash

    override fun initData() {
    }

    override fun initView() {
        alphaAnimation = AlphaAnimation(0.3F, 1.0F)
        alphaAnimation?.run {
            duration = 2000
            setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationEnd(animation: Animation?) {
                    jumpToMain()
                }

                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationStart(animation: Animation?) {

                }
            })
            layout_splash.startAnimation(alphaAnimation)
        }
    }

    override fun start() {
    }

    fun jumpToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
}
