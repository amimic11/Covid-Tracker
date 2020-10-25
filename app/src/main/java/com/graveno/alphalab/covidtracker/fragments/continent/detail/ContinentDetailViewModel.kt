package com.graveno.alphalab.covidtracker.fragments.continent.detail

import android.app.Application
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.app.AppSharedPreference
import com.graveno.alphalab.covidtracker.app.model.CountryModel

class ContinentDetailViewModel(application: Application) : AndroidViewModel(application) {

    val TAG : String = "ContinentDetailViewModel"
    private val appSharedPreference : AppSharedPreference = AppSharedPreference()
    private var countries : ArrayList<CountryModel> = ArrayList()

    var obsCountries : MutableLiveData<ArrayList<CountryModel>> = MutableLiveData()

    fun bindCountries(mainActivity : MainActivity, list : ArrayList<String>) {
        countries = ArrayList()
        countries = appSharedPreference.getCountryList(mainActivity = mainActivity)
        var temp : ArrayList<CountryModel> = ArrayList()
        for ((i, country : CountryModel) in countries.withIndex()) {
            for ((j : Int, countryName : String) in list.withIndex()) {
                if (list[j] == country.country) {
                    temp.add(country)
                }
            }
        }
        obsCountries.postValue(temp)
    }

    private fun getImageUrl(continent: String): String {
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

    fun getContinentImage(mainActivity: MainActivity, continent: String, img_continent: ImageView) {
        Glide.with(mainActivity)
            .load(getImageUrl(continent))
            .error(R.drawable.ic_country_selected)
            .into(img_continent)
    }

}
