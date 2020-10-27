package com.graveno.alphalab.covidtracker.fragments.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.app.model.SettingModel

class SettingViewModel(application: Application) : AndroidViewModel(application) {
    val TAG : String = "SettingViewModel"
    private var settings : ArrayList<SettingModel> = ArrayList()
    private lateinit var settingModel: SettingModel

    var obsSettings : MutableLiveData<ArrayList<SettingModel>> = MutableLiveData()

    fun prepSettings(mainActivity: MainActivity) {
        settings = ArrayList()

//        settingModel = SettingModel()
//        settingModel.settingName = mainActivity.getString(R.string.about)
//        settingModel.settingDrawable = R.drawable.ic_arrow_right
//        settings.add(settingModel)

//        settingModel = SettingModel()
//        settingModel.settingName = mainActivity.getString(R.string.privacy_policy)
//        settingModel.settingDrawable = R.drawable.ic_arrow_go
//        settings.add(settingModel)

        settingModel = SettingModel()
        settingModel.settingName = mainActivity.getString(R.string.exit)
        settingModel.settingDrawable = R.drawable.ic_exit
        settings.add(settingModel)
        obsSettings.postValue(settings)
    }

}