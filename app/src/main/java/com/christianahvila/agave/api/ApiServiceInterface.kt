package com.christianahvila.agave.api

import com.christianahvila.agave.models.Beer
import com.christianahvila.agave.models.BeerDetail
import com.christianahvila.agave.utils.Constants.Companion.BASE_URL
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 */

interface ApiServiceInterface {
    /**
     * Get the list of the articles from the API
     */
    @GET("beers")
    fun getBeers(@Query("page") page: Int): Observable<ArrayList<Beer>>

    /**
     * Get the detail of an article from the API
     */
    @GET("beers/{id}")
    fun getDetailBeer(@Path("id") id: Int): Observable<ArrayList<BeerDetail>>

    /*
    * It is just an object associated to the interface has one singleton instance
     */
    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }

}