package com.pixabay.app

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import com.pixabay.app.adapter.PixabyListAdapter
import com.pixabay.app.api.ApiService
import com.pixabay.app.api.RetrofitClient
import com.pixabay.app.models.Hit
import com.pixabay.app.models.Pixaby
import com.pixabay.app.repository.PixabySearchRepository
import com.pixabay.app.utilities.ConnectionDetector
import com.pixabay.app.utilities.Constants
import java.lang.Exception
import java.net.URLEncoder

class MainActivity : BaseActivity(), PixabyListAdapter.ItemClick {

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.searchView)
    lateinit var searchView: SearchView

    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.imgRefresh)
    lateinit var imgRefresh: ImageView

    @BindView(R.id.tvPlaceHolder)
    lateinit var tvPlaceHolder: TextView

    private lateinit var viewModel: MainActivityViewModel

    lateinit var cd: ConnectionDetector

    lateinit var pixabySearchRepository: PixabySearchRepository

    //Setting the layout resource to the activity
    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    //Setting the views
    override fun setViews() {
        val apiService: ApiService = RetrofitClient.getClient()
        pixabySearchRepository = PixabySearchRepository(apiService)
        viewModel = getViewModel()
    }

    //Setting the values to the objects
    override fun setValues() {
        cd = ConnectionDetector(this)
        if (isNetworkAvailable()){
            showLoading(View.VISIBLE)
            viewModel.getIntialData()?.observe(this, Observer {
                showLoading(View.GONE)
                setAdapter(it)
            })
        }
    }

    //Handle all click events here
    override fun setActions() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                /*if (newText.length > 2){ //if you want to get the on every text change uncomment it
                    setSearch(query)
                }*/
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                if (query.length > 2){
                    setSearch(query)
                }else{
                    Toast.makeText(this@MainActivity, getString(R.string.alert_search_length), Toast.LENGTH_LONG).show()
                }
                return false
            }

        })

        imgRefresh.setOnClickListener {
            setSearch("flowers")
            searchView.setQuery("", false)
        }
    }

    override fun onClick(view: View?) {
    }


    private fun getViewModel(): MainActivityViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(pixabySearchRepository) as T
            }
        })[MainActivityViewModel::class.java]
    }

    private fun setSearch(searchQ: String){
        var encodeURLStr = ""

        try {
            encodeURLStr = URLEncoder.encode(searchQ, "UTF-8")
        }catch (e:Exception){e.printStackTrace()}

        viewModel.setSearch(encodeURLStr)

        fetchUpdatedData()
    }

    //function to fetch the updated data
    private fun fetchUpdatedData(){
        if (isNetworkAvailable()){
            showLoading(View.VISIBLE)
            viewModel.getUpdatedData()?.observe(this, Observer {
                showLoading(View.GONE)
                setAdapter(it)
            })
        }
    }

    //setting the adapter to recyclerview
    private fun setAdapter(pixby: Pixaby?){
        val mAdapter =  PixabyListAdapter(this, pixby?.hits, this)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            recyclerView.layoutManager = GridLayoutManager(this, 3) as RecyclerView.LayoutManager?
        } else {
            // In portrait
            recyclerView.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?
        }


        recyclerView.adapter = mAdapter

        var hitSize = pixby?.hits?.size
        if (hitSize!! > 0){
            tvPlaceHolder.visibility = View.GONE
        }else{
            tvPlaceHolder.visibility = View.VISIBLE
        }
    }

    //Handles Recyceview Items click events
    override fun onAdapterItemClick(hit: Hit) {
        val intent = Intent(this, HitDetailActivity::class.java)
        intent.putExtra(Constants.USER_NAME, hit.user)
        intent.putExtra(Constants.IMAGE_URL, hit.largeImageURL)
        intent.putExtra(Constants.IMAGES_LIST, hit.tags)
        startActivity(intent)
    }

    //setting the value to Progress bar
    private fun showLoading(visibility: Int){
        progressBar.visibility = visibility
    }

    //checking wheter the internet is available
    fun isNetworkAvailable():  Boolean{
        if (cd.isConnectingToInternet()) {
            return true
        }

        Toast.makeText(this, getString(R.string.no_network_found), Toast.LENGTH_LONG).show()
        return false
    }
}
