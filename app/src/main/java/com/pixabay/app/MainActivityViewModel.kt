package com.pixabay.app

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.pixabay.app.models.Pixaby
import com.pixabay.app.repository.PixabySearchRepository
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(private val pixabySearchRepository: PixabySearchRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private var searchQ: String = "flowers" //asssign the default value to get result as app starts for the first time

    var pixabyData : LiveData<Pixaby>? = null

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    //setting the search query
    open fun setSearch(searchQ: String){
        this.searchQ = searchQ
    }

    //to get the latest updated data
    open fun getUpdatedData(): LiveData<Pixaby>? {
        pixabyData = pixabySearchRepository.fetchSearchData(compositeDisposable, searchQ)
        return pixabyData
    }

    open fun getIntialData(): LiveData<Pixaby>? {
        if (pixabyData == null){
            getUpdatedData()
        }
        return pixabyData
    }

}