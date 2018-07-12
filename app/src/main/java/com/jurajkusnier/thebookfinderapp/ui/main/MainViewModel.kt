package com.jurajkusnier.thebookfinderapp.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.jurajkusnier.thebookfinderapp.data.model.GoogleBooksResult
import com.jurajkusnier.thebookfinderapp.data.repository.BookRepository

class MainViewModel(private val bookRepository: BookRepository) : ViewModel( ) {

    private val _results: MutableLiveData<GoogleBooksResult> = MutableLiveData()
    val results: LiveData<GoogleBooksResult>
        get() = _results

    fun findBooks(name:String) {
        val disposable = bookRepository.findBooks(name)?.subscribe {
            _results.value = it
        }
    }
}
