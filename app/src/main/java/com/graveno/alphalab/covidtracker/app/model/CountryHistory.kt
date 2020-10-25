package com.graveno.alphalab.covidtracker.app.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CountryHistory : Serializable {

    @SerializedName("country")
    @Expose
    val country: String = ""

    @SerializedName("province")
    @Expose
    val province: ArrayList<String> = ArrayList()

    @SerializedName("timeline")
    @Expose
    val timeline: Timeline = Timeline()
    
}

class Timeline : Serializable {

    @SerializedName("cases")
    @Expose
    val cases : LinkedHashMap<String, Float> = LinkedHashMap()

    @SerializedName("deaths")
    @Expose
    val deaths : LinkedHashMap<String, Float> = LinkedHashMap()

    @SerializedName("recovered")
    @Expose
    val recovered : LinkedHashMap<String, Float> = LinkedHashMap()


}
