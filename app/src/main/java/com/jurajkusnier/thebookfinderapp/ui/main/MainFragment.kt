package com.jurajkusnier.thebookfinderapp.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jurajkusnier.thebookfinderapp.R
import com.jurajkusnier.thebookfinderapp.data.api.BooksApiService
import com.jurajkusnier.thebookfinderapp.data.model.GoogleBooksResult
import com.jurajkusnier.thebookfinderapp.data.repository.BookRepository
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {

    val TAG = MainFragment::class.java.simpleName

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val retrofit = Retrofit.Builder()
                        .baseUrl("https://www.googleapis.com/")
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                val service = retrofit.create<BooksApiService>(BooksApiService::class.java)

                val repository = BookRepository(service)

                return MainViewModel(repository) as T
            }

        }).get(MainViewModel::class.java)

        viewModel.findBooks("book")

        recycleViewBooks.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        recycleViewBooks.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(recycleViewBooks.context, layoutManager.orientation)
        recycleViewBooks.addItemDecoration(dividerItemDecoration)

        viewModel.results.observe(this, Observer<GoogleBooksResult> {
            it?.apply {
                recycleViewBooks.adapter = BooksListAdapter(items)
            }
            Log.d(TAG,it.toString())
        })
    }
}
