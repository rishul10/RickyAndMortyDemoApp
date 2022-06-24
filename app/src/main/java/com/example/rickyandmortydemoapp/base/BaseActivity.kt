package com.example.rickyandmortydemoapp.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.rickyandmortydemoapp.R
import com.example.rickyandmortydemoapp.activity.RickyAndMortyListingActivity
import com.example.rickyandmortydemoapp.di.module.Injectable
import com.example.rickyandmortydemoapp.utils.CustomProgressDialog
import com.example.rickyandmortydemoapp.viewmodel.BaseViewModel

abstract class BaseActivity : AppCompatActivity(), Injectable {

    private var spinningDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_base)

        spinningDialog = CustomProgressDialog.showProgressDialog(this)
        spinningDialog?.setCancelable(false)
    }

    abstract fun setupObservers()

    protected fun observeProgressBarAndErrorLiveData(viewModel: BaseViewModel) {
        viewModel.progressBar.observe(this, Observer {
            if (it) showActivitySpinner() else dismissActivitySpinner()
        })
        viewModel.errorLiveData.observe(this@BaseActivity, Observer {
            when (it.first) {
                401 -> handleAuthorizationFailed()
                else -> toast(it.second)
            }
        })
    }

    protected fun Activity.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    fun showActivitySpinner() {
        try {
            if (spinningDialog != null) spinningDialog?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

     fun dismissActivitySpinner() {
        try {
            if (spinningDialog != null) spinningDialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun handleAuthorizationFailed() {

        //Clear all the locally saved data and redirect to login screen

        try {
            val userInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE)
            userInfo.edit().clear().apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            val intent = Intent(this, RickyAndMortyListingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /*protected fun startFwdAnimation(activity: Activity) {
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    protected fun startBackAnimation(activity: Activity) {
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }*/

}
