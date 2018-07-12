package com.jurajkusnier.thebookfinderapp.data.api

import com.jurajkusnier.thebookfinderapp.data.model.GoogleBooksResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("books/v1/volumes")
    fun listBooks(@Query("q") bookName: String): Observable<GoogleBooksResult>
}