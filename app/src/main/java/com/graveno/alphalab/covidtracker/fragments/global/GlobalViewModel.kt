package com.graveno.alphalab.covidtracker.fragments.global

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.api.ApiClient
import com.graveno.alphalab.covidtracker.api.ApiConstant
import com.graveno.alphalab.covidtracker.api.ApiInterface
import com.graveno.alphalab.covidtracker.app.AppSharedPreference
import com.graveno.alphalab.covidtracker.app.model.GlobalModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalViewModel(application: Application) : AndroidViewModel(application) {
    val TAG : String  = "GlobalViewModel"
    private val apiConstant : ApiConstant = ApiConstant()
    val apiInterface : ApiInterface = ApiClient().getClient()!!.create(ApiInterface :: class.java)
    var obsToast : MutableLiveData<String> = MutableLiveData()
    var obsBindInfo : MutableLiveData<GlobalModel> = MutableLiveData()
    private val appSharedPreference : AppSharedPreference = AppSharedPreference()
    private var globalModel : GlobalModel = GlobalModel()

    fun fetchGlobalData(mainActivity: MainActivity) {
        globalModel = GlobalModel()
        globalModel = appSharedPreference.getGobalData(mainActivity)
        if (globalModel == GlobalModel()) {
            val call : Call<GlobalModel> = apiInterface.global()
            call.enqueue(object : Callback<GlobalModel> {
                override fun onResponse(call: Call<GlobalModel>?, response: Response<GlobalModel>?) {
                    if (apiConstant.ok == response!!.code()) {
                        val globalModel : GlobalModel = response.body()
                        obsBindInfo.postValue(globalModel)
                    } else obsToast.postValue("Please try after some time, Caching is in progress")
                }

                override fun onFailure(call: Call<GlobalModel>?, t: Throwable?) {
                    obsToast.postValue("Something wet wrong, please check your network connectivity.")
                }
            })
        } else obsBindInfo.postValue(globalModel)
    }

    fun setGlobe(mainActivity: MainActivity, img_global: ImageView) {
        Glide.with(mainActivity)
            .load(R.raw.global_gif)
            .into(img_global)
    }
}
