package com.graveno.alphalab.covidtracker.fragments.country

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.api.ApiClient
import com.graveno.alphalab.covidtracker.api.ApiConstant
import com.graveno.alphalab.covidtracker.api.ApiInterface
import com.graveno.alphalab.covidtracker.app.AppSharedPreference
import com.graveno.alphalab.covidtracker.app.model.CountryModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryViewModel(application: Application) : AndroidViewModel(application) {
    val TAG : String = "CountryViewModel"
    private val apiInterface : ApiInterface = ApiClient().getClient()!!.create(ApiInterface :: class.java)
    private val appSharedPreference : AppSharedPreference = AppSharedPreference()
    private val apiConstant : ApiConstant = ApiConstant()
    private var countryList : ArrayList<CountryModel> = ArrayList<CountryModel>()

    var obsToast : MutableLiveData<String> = MutableLiveData()
    var obsList : MutableLiveData<ArrayList<CountryModel>> = MutableLiveData()

    fun fetchCountry(mainActivity: MainActivity) {
        countryList = ArrayList()
        countryList = appSharedPreference.getCountryList(mainActivity)
        if (countryList.size <= 0) {
            //api...
            val call : Call<ArrayList<CountryModel>> = apiInterface.countryList()
            call.enqueue(object : Callback<ArrayList<CountryModel>> {
                override fun onResponse(call: Call<ArrayList<CountryModel>>?, response: Response<ArrayList<CountryModel>>?) {
                    if (apiConstant.ok == response!!.code()) {
                        appSharedPreference.setCountryList(response.body(), mainActivity)
                        obsList.postValue(response.body())
                    } else obsToast.postValue("Something went wrong, please Check internet connection")
                }

                override fun onFailure(call: Call<ArrayList<CountryModel>>?, t: Throwable?) {
                    obsToast.postValue("encounters an error as : ${t!!.message}")
                }
            })
        } else {
            //prefs...
            obsList.postValue(countryList)
        }
    }
}