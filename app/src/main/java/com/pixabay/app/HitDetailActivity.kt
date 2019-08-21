package com.pixabay.app

import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.pixabay.app.utilities.Constants

class HitDetailActivity : BaseActivity() {

    @BindView(R.id.ivLarge)
    lateinit var ivLarge: ImageView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.tvTags)
    lateinit var tvTags: TextView

    //Setting the layout resource to the activity
    override fun getLayoutRes(): Int {
        return R.layout.activity_hit_detail
    }

    //Setting the views
    override fun setViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //Setting the values to the objects
    override fun setValues() {
        val userName = intent.getStringExtra(Constants.USER_NAME);
        val imageUrl = intent.getStringExtra(Constants.IMAGE_URL);
        val imageList = intent.getStringExtra(Constants.IMAGES_LIST);

        supportActionBar?.setTitle(userName)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvTags.text = getString(R.string.tags) + " "  + imageList

        Glide.with(this)
            .load(imageUrl)
            .into(ivLarge)
    }

    //Handle all click events here
    override fun setActions() {
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onClick(p0: View?) {
    }
}
