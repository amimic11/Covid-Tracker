package com.graveno.alphalab.covidtracker.fragments.country.countrydetail

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.db.williamchart.view.LineChartView
import com.graveno.alphalab.covidtracker.api.ApiClient
import com.graveno.alphalab.covidtracker.api.ApiConstant
import com.graveno.alphalab.covidtracker.api.ApiInterface
import com.graveno.alphalab.covidtracker.app.model.CountryHistory
import com.graveno.alphalab.covidtracker.app.model.CountryModel
import kotlinx.android.synthetic.main.fragment_country_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryDetailViewModel(application: Application) : AndroidViewModel(application) {
    val TAG : String = "CountryDetailViewModel"
    private val apiInterface : ApiInterface = ApiClient().getClient()!!.create(ApiInterface :: class.java)
    private var apiConstant : ApiConstant = ApiConstant()
    private var countries : CountryModel = CountryModel()
    private var countryHistory : CountryHistory = CountryHistory()

    var obsToast : MutableLiveData<String> = MutableLiveData()
    var obsCountries : MutableLiveData<CountryModel> = MutableLiveData()
    var obsCountryHistory : MutableLiveData<CountryHistory> = MutableLiveData()

    fun fetchHistory(selectedCountry : String) {
        countryHistory = CountryHistory()
        val url : String = "https://corona.lmao.ninja/v2/historical/$selectedCountry?lastdays=all"
        val call : Call<CountryHistory> = apiInterface.selectedCountryhHistory(url)
        call.enqueue(object : Callback<CountryHistory> {
            override fun onResponse(call : Call<CountryHistory>?, response: Response<CountryHistory>?) {
                if (apiConstant.ok == response!!.code()) {
                    countryHistory = response.body()
                    obsCountryHistory.postValue(countryHistory)
                } else obsToast.postValue("please check your internet connection")

            }

            override fun onFailure(call : Call<CountryHistory>?, t: Throwable?) {
                obsToast.postValue("error as : ${t!!.message}")
            }
        })
    }

    fun getImageUrl(continent: String): String {
        return when (continent) {
            "Asia" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Asia_%28orthographic_projection%29.svg/220px-Asia_%28orthographic_projection%29.svg.png"
            "North America" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Location_North_America.svg/2000px-Location_North_America.svg.png"
            "South America" -> "https://upload.wikimedia.org/wikipedia/commons/8/8c/Location_South_America.png"
            "Europe" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Europe_orthographic_Caucasus_Urals_boundary_%28with_borders%29.svg/1200px-Europe_orthographic_Caucasus_Urals_boundary_%28with_borders%29.svg.png"
            "Africa" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/Africa_%28orthographic_projection%29.svg/1200px-Africa_%28orthographic_projection%29.svg.png"
            "Australia/Oceania" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Australia-New_Guinea_%28orthographic_projection%29.svg/1200px-Australia-New_Guinea_%28orthographic_projection%29.svg.png"
            else -> "https://toppng.com/uploads/preview/world-globe-png-clip-freeuse-download-world-map-globe-11562910726e1f0hvovtx.png"
        }
    }

}
