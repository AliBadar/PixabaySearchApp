package com.pixabay.app.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.pixabay.app.api.ApiService
import com.pixabay.app.models.Pixaby
import com.pixabay.app.utilities.Constants
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class PixabySearchNetworkData(private val apiService: ApiService, private val compositeDisposable: CompositeDisposable) {

    private val pixabySearchResponse=  MutableLiveData<Pixaby>()

   val getSearchResponse: LiveData<Pixaby>
    get() = pixabySearchResponse


    fun fetchPixabySearchData(searchQ: String){
        try {

            compositeDisposable.add(
                apiService.searchData(Constants.API_KEY, searchQ, Constants.IMAGE_TYPE, true)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            pixabySearchResponse.postValue(it)
                        },
                        {

                        }
                    )
            )

        }catch (e: Exception){e.printStackTrace()}
    }

}