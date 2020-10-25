package com.graveno.alphalab.covidtracker.fragments.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.api.ApiClient
import com.graveno.alphalab.covidtracker.api.ApiConstant
import com.graveno.alphalab.covidtracker.api.ApiInterface
import com.graveno.alphalab.covidtracker.app.AppSharedPreference
import com.graveno.alphalab.covidtracker.app.model.ContinentsModel
import com.graveno.alphalab.covidtracker.app.model.CountryModel
import com.graveno.alphalab.covidtracker.app.model.GlobalModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    val TAG : String = "SplashViewModel"
    private var apiConstant : ApiConstant = ApiConstant()
    private var countries : ArrayList<CountryModel> = ArrayList()
    private var apiInterface : ApiInterface = ApiClient().getClient()!!.create(ApiInterface :: class.java)
    private var appSharedPreference : AppSharedPreference = AppSharedPreference()

    var obsLoading : MutableLiveData<String> = MutableLiveData()

//    fun loadgif(mainActivity: MainActivity, imgSplash: ImageView) {
//        Glide.with(mainActivity)
//            .load(R.raw.covid_19_outbreak)
//            .into(imgSplash)
//    }

    fun updateGlobal(mainActivity: MainActivity, navController: NavController) {
        obsLoading.postValue("Loading global data...")
        val call : Call<GlobalModel> = apiInterface.global()
        call.enqueue(object : Callback<GlobalModel> {
            override fun onResponse(call: Call<GlobalModel>?, response: Response<GlobalModel>?) {
                if (apiConstant.ok == response!!.code()) {
                    val globalModel : GlobalModel = response.body()
                    appSharedPreference.setGlobalInfo(globalModel, mainActivity)
                } else obsLoading.postValue("unable to fetch global data, Caching is in progress")
                updateContinent(mainActivity, navController)
            }

            override fun onFailure(call: Call<GlobalModel>?, t: Throwable?) {
                obsLoading.postValue("slow internet detected, please check your network connectivity.")
                updateContinent(mainActivity, navController)
            }
        })
    }

    private fun updateContinent(mainActivity: MainActivity, navController: NavController) {
        obsLoading.postValue("Loading continents data...")
        val call : Call<ArrayList<ContinentsModel>> = apiInterface.continentsList()
        call.enqueue(object : Callback<ArrayList<ContinentsModel>> {
            override fun onResponse(call: Call<ArrayList<ContinentsModel>>?, response: Response<ArrayList<ContinentsModel>>?) {
                if (apiConstant.ok == response!!.code()) {
                    val continentList : ArrayList<ContinentsModel> = response.body()
                    appSharedPreference.setContinentSummary(mainActivity, continentList)

                } else obsLoading.postValue("unable to fetch continent data, Caching is in progress")
                updateCountrys(mainActivity, navController)
            }

            override fun onFailure(call: Call<ArrayList<ContinentsModel>>?, t: Throwable?) {
                obsLoading.postValue("slow internet detected, please check your network connectivity.")
                updateCountrys(mainActivity, navController)
            }
        })
    }

    private fun updateCountrys(mainActivity: MainActivity, navController: NavController) {
        obsLoading.postValue("Loading country data...")
        val call : Call<ArrayList<CountryModel>> = apiInterface.countryList()
        call.enqueue(object : Callback<ArrayList<CountryModel>> {
            override fun onResponse(call: Call<ArrayList<CountryModel>>?, response: Response<ArrayList<CountryModel>>?) {
                if (apiConstant.ok == response!!.code()) {
                    appSharedPreference.setCountryList(response.body(), mainActivity)
                } else obsLoading.postValue("unable to fetch country data, Caching is in progress")
                gotoHome(navController)
            }

            override fun onFailure(call: Call<ArrayList<CountryModel>>?, t: Throwable?) {
                obsLoading.postValue("slow internet detected, please check your network connectivity.")
                gotoHome(navController)
            }
        })
    }

    private fun gotoHome(navController: NavController) {
        navController.navigate(R.id.action_splashFragment_to_homeFragment)
    }
}