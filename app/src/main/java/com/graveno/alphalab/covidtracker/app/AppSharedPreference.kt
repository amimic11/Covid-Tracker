package com.graveno.alphalab.covidtracker.app

import android.app.Activity
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.app.model.ContinentsModel
import com.graveno.alphalab.covidtracker.app.model.CountryModel
import com.graveno.alphalab.covidtracker.app.model.GlobalModel
import com.graveno.alphalab.covidtracker.fragments.splash.SplashFragment
import java.lang.reflect.Type
import kotlin.collections.ArrayList

class AppSharedPreference {
    private val globalData: String = "global Data"
    private val continentSummary: String = "continent summary"
    private val updatedCountires: String = "app Updated reports"
    private val countriesInfo: String = "app countries info"
    private val fragTag: String = "fragment tag name"
    val TAG : String = "AppSharedPreference"

    fun setFrag(tag : String, mainActivity: MainActivity) {
        val sharedPreference : SharedPreferences = mainActivity.getSharedPreferences(mainActivity.packageName, Activity.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(fragTag, tag)
        editor.apply()
    }

    fun getFrag(mainActivity: MainActivity) : String {
        val sharedPreferences : SharedPreferences = mainActivity.getSharedPreferences(mainActivity.packageName, Activity.MODE_PRIVATE)
        return sharedPreferences.getString(fragTag, SplashFragment().TAG)!!
    }

    fun setCountryList(list : ArrayList<CountryModel>, mainActivity: MainActivity) {
        val sharedPreferences : SharedPreferences = mainActivity.getSharedPreferences(mainActivity.packageName, Activity.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        val gson : Gson = Gson()
        editor.putString(updatedCountires, gson.toJson(list))
        editor.apply()
    }

    fun getCountryList(mainActivity: MainActivity) : ArrayList<CountryModel> {
        val sharedPreferences : SharedPreferences = mainActivity.getSharedPreferences(mainActivity.packageName, Activity.MODE_PRIVATE)
        val gson : Gson = Gson()
        val type : Type = object : TypeToken<ArrayList<CountryModel>>() {}.type
        val listStr : String = sharedPreferences.getString(updatedCountires, JsonArray().toString())!!
        return gson.fromJson<ArrayList<CountryModel>>(listStr, type).toCollection(ArrayList())
    }

    fun setContinentSummary(mainActivity: MainActivity, continentsList: ArrayList<ContinentsModel>) {
        val sharedPreferences : SharedPreferences = mainActivity.getSharedPreferences(mainActivity.packageName, Activity.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        val gson : Gson = Gson()
        editor.putString(continentSummary, gson.toJson(continentsList))
        editor.apply()
    }

    fun getContinentSummary(mainActivity: MainActivity) : ArrayList<ContinentsModel> {
        val sharedPreferences : SharedPreferences = mainActivity.getSharedPreferences(mainActivity.packageName, Activity.MODE_PRIVATE)
        val arrayType : Type = object  : TypeToken<List<ContinentsModel>>() {}.type
        val gson : Gson = Gson()
        val jsonArray : String = sharedPreferences.getString(continentSummary, JsonArray().toString())!!
        return gson.fromJson<List<ContinentsModel>>(jsonArray, arrayType).toCollection(ArrayList())
    }

    fun setGlobalInfo(globalModel: GlobalModel, mainActivity: MainActivity) {
        val sharedPreference : SharedPreferences = mainActivity.getSharedPreferences(mainActivity.packageName, Activity.MODE_PRIVATE)
        val gson : Gson = Gson()
        val editor : SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(globalData, gson.toJson(globalModel))
        editor.apply()
    }

    fun getGobalData(mainActivity: MainActivity): GlobalModel {
        val sharedPreferences : SharedPreferences = mainActivity.getSharedPreferences(mainActivity.packageName, Activity.MODE_PRIVATE)
        val gson : Gson = Gson()
        val type : Type = object : TypeToken<GlobalModel>() {}.type
        return gson.fromJson(sharedPreferences.getString(globalData, JsonObject().toString()), type)
    }

}