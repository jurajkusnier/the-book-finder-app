package com.jurajkusnier.thebookfinderapp.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.jurajkusnier.thebookfinderapp.data.model.GoogleBooksResult
import com.jurajkusnier.thebookfinderapp.data.repository.BookRepository
import io.reactivex.disposables.Disposable

class MainViewModel(private val bookRepository: BookRepository) : ViewModel( ) {

    val TAG = MainViewModel::class.java.simpleName

    private val _results: MutableLiveData<GoogleBooksResult> = MutableLiveData()
    val results: LiveData<GoogleBooksResult>
        get() = _results

    var searchName = ""
    var disposable:Disposable? = null

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    fun findBooks(name:String?) {
        if (name == null || searchName == name) return

        searchName = name

        disposable?.dispose()

        disposable = bookRepository.findBooks(name)?.subscribe({ data ->
            _results.value = data
        }, { error ->
            Log.e(TAG, Log.getStackTraceString(error))
        }
        )
    }
}
