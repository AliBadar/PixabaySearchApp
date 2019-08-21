package com.pixabay.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import butterknife.ButterKnife

abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        ButterKnife.bind(this)  //Binding the Butterkinfe to the Activity


        setViews()
        setValues()
        setActions()
    }

    //Setting the layout resource to the activity
    abstract fun getLayoutRes(): Int

    //Setting the views
    abstract fun setViews();

    //Setting the values to the objects
    abstract fun setValues();

    //Handle all click events herer
    abstract fun setActions();

}