package com.pixabay.app.repository

import android.arch.lifecycle.LiveData
import com.pixabay.app.api.ApiService
import com.pixabay.app.models.Pixaby
import io.reactivex.disposables.CompositeDisposable

class PixabySearchRepository(private val apiService: ApiService) {


    lateinit var pixabySearchNetworkData: PixabySearchNetworkData

    fun fetchSearchData(compositeDisposable: CompositeDisposable, searchQ: String): LiveData<Pixaby>{
        pixabySearchNetworkData = PixabySearchNetworkData(apiService, compositeDisposable)
        pixabySearchNetworkData.fetchPixabySearchData(searchQ)

        return pixabySearchNetworkData.getSearchResponse
    }

}