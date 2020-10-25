package com.graveno.alphalab.covidtracker.app.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ContinentsModel : Serializable {

    @SerializedName("updated")
    @Expose
     val updated: Long = 0

    @SerializedName("cases")
    @Expose
     val cases: Long = 0

    @SerializedName("todayCases")
    @Expose
     val todayCases: Long = 0

    @SerializedName("deaths")
    @Expose
     val deaths: Long = 0

    @SerializedName("todayDeaths")
    @Expose
     val todayDeaths: Long = 0

    @SerializedName("recovered")
    @Expose
     val recovered: Long = 0

    @SerializedName("todayRecovered")
    @Expose
     val todayRecovered: Long = 0

    @SerializedName("active")
    @Expose
     val active: Long = 0

    @SerializedName("critical")
    @Expose
     val critical: Long = 0

    @SerializedName("casesPerOneMillion")
    @Expose
     val casesPerOneMillion: Double = 0.0

    @SerializedName("deathsPerOneMillion")
    @Expose
     val deathsPerOneMillion: Double = 0.0

    @SerializedName("tests")
    @Expose
     val tests: Long = 0

    @SerializedName("testsPerOneMillion")
    @Expose
     val testsPerOneMillion: Double = 0.0

    @SerializedName("population")
    @Expose
     val population: Long = 0

    @SerializedName("continent")
    @Expose
     val continent: String = ""

    @SerializedName("activePerOneMillion")
    @Expose
     val activePerOneMillion: Double = 0.0

    @SerializedName("recoveredPerOneMillion")
    @Expose
     val recoveredPerOneMillion: Double = 0.0

    @SerializedName("criticalPerOneMillion")
    @Expose
     val criticalPerOneMillion: Double = 0.0

    @SerializedName("countries")
    @Expose
     val countries: ArrayList<String>? = ArrayList()
    
}
