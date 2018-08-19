package com.carpool.base

import android.annotation.SuppressLint
import android.util.Log
import com.carpool.data.Error
import com.carpool.ui.components.LoaderDialog
import dagger.android.support.DaggerAppCompatActivity
import android.os.Bundle


abstract class BaseActivity : DaggerAppCompatActivity(), IBaseView {

    private var mLoaderDialogFragment: LoaderDialog? = null

    override fun onError(error: Error) {
        Log.d("Error", error.message)
    }

    override fun onInvalidSession(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoader() {
        if (mLoaderDialogFragment == null) this.mLoaderDialogFragment = LoaderDialog()
        mLoaderDialogFragment!!.show(getSupportFragmentManager(), "Loader")
    }

    override fun hideLoader() {

        if (mLoaderDialogFragment != null && mLoaderDialogFragment!!.isVisible()) {
            mLoaderDialogFragment!!.dismiss()
        }
    }

}