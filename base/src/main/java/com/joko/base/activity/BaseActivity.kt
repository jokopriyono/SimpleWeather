package com.joko.base.activity

import android.app.AlertDialog
import android.content.Intent
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.joko.base.BuildConfig
import com.joko.base.viewmodel.BaseViewModel
import com.joko.base.viewmodel.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseActivity : ComponentActivity(), CoroutineScope by MainScope() {

    private var isAlertShow = false

    protected abstract fun setupObserver()

    override fun onStart() {
        super.onStart()
        if (BuildConfig.DEBUG) {
            window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    open fun BaseViewModel.enableForceLogout(activity: BaseActivity) {
        userSession.observe(this@BaseActivity) {
            showMessageToast("Sesi login anda telah habis")
            startActivity(Intent(this@BaseActivity, activity::class.java))
            finishAffinity()
        }
    }

    fun showMessage(message: Message) {
        when (message) {
            is Message.Toast -> showMessageToast(message.message)
            is Message.LongToast -> showMessageLongToast(message.message)
            is Message.Dialog -> showMessageDialog(message.message)
            else -> {}
        }
    }


    fun showMessageDialog(msg: String?) {
        if (!isAlertShow) {
            isAlertShow = true
            AlertDialog.Builder(this)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, _ ->
                    isAlertShow = false
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }


    fun showMessageToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showMessageLongToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}