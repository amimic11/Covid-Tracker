package com.graveno.alphalab.covidtracker.api

import com.graveno.alphalab.covidtracker.app.model.ContinentsModel
import com.graveno.alphalab.covidtracker.app.model.CountryHistory
import com.graveno.alphalab.covidtracker.app.model.GlobalModel
import com.graveno.alphalab.covidtracker.app.model.CountryModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

class ApiClient() {

    private lateinit var retrofit: Retrofit

    fun getClient(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client : OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://corona.lmao.ninja/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }
}

interface ApiInterface {

    @GET("countries?yesterday&sort")
    fun countryList() : Call<ArrayList<CountryModel>>

    @GET("all?yesterday")
    fun global() : Call<GlobalModel>

    @GET("continents?yesterday=true&sort")
    fun continentsList(): Call<ArrayList<ContinentsModel>>

    @GET
    fun selectedCountryhHistory(@Url url : String) : Call<CountryHistory>
}