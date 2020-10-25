package com.graveno.alphalab.covidtracker.fragments.continent

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.api.ApiClient
import com.graveno.alphalab.covidtracker.api.ApiConstant
import com.graveno.alphalab.covidtracker.api.ApiInterface
import com.graveno.alphalab.covidtracker.app.AppSharedPreference
import com.graveno.alphalab.covidtracker.app.model.ContinentsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContinentViewModel(application: Application) : AndroidViewModel(application) {

    val TAG : String = "ContinentViewModel"
    private val apiInterface : ApiInterface = ApiClient().getClient()!!.create(ApiInterface :: class.java)
    private val apiConstant : ApiConstant = ApiConstant()
    private var continentsList : ArrayList<ContinentsModel> = ArrayList()
    private val appSharedPreference : AppSharedPreference = AppSharedPreference()

    var obsToast : MutableLiveData<String> = MutableLiveData()
    var obsContinentList : MutableLiveData<ArrayList<ContinentsModel>> = MutableLiveData()

    fun getContinents(mainActivity: MainActivity) {
        val continents : ArrayList<ContinentsModel> = appSharedPreference.getContinentSummary(mainActivity)
        if (continents.size <= 0) {
            //load api...
            val call : Call<ArrayList<ContinentsModel>> = apiInterface.continentsList()
            call.enqueue(object : Callback<ArrayList<ContinentsModel>> {
                override fun onResponse(call: Call<ArrayList<ContinentsModel>>?, response: Response<ArrayList<ContinentsModel>>?) {
                    if (apiConstant.ok == response!!.code()) {
                        continentsList = response.body()
                        appSharedPreference.setContinentSummary(mainActivity, continentsList)
                        obsContinentList.postValue(continentsList)
                    } else obsToast.postValue("Something went wrong , please try again after some time...")
                }

                override fun onFailure(call: Call<ArrayList<ContinentsModel>>?, t: Throwable?) {
                    obsToast.postValue("error occurred as : ${t!!.message}")
                }
            })
        } else {
            //load from prefs...
            obsContinentList.postValue(continents)
        }
    }

}
