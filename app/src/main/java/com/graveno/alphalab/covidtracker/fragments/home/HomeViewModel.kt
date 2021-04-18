package com.graveno.alphalab.covidtracker.fragments.home

import android.app.Application
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.fragments.continent.ContinentFragment
import com.graveno.alphalab.covidtracker.fragments.country.CountryFragment
import com.graveno.alphalab.covidtracker.fragments.countrystatesdetail.CountryStatesDetailFragment
import com.graveno.alphalab.covidtracker.fragments.global.GlobalFragment
import com.graveno.alphalab.covidtracker.fragments.setting.SettingFragment

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val TAG : String = "HomeViewModel"
    private var fragments : ArrayList<Fragment> = ArrayList()
    private lateinit var adapter : HomeAdapter

    fun viewPagerAdapter(mainActivity: MainActivity) : FragmentStateAdapter {
        fragments = ArrayList()
        fragments.add(GlobalFragment())
        fragments.add(ContinentFragment())
        fragments.add(CountryFragment())
//        fragments.add(CountryStatesDetailFragment())
        fragments.add(SettingFragment())

        adapter = HomeAdapter(fragments, mainActivity)
        return adapter
    }

    fun setViewNTab(mainActivity: MainActivity, viewPager: ViewPager2, tablayout: TabLayout) {
       TabLayoutMediator(tablayout, viewPager) { tab, position ->
           when (position) {
               0 -> {
                   tab.icon = ContextCompat.getDrawable(mainActivity, R.drawable.covid_19)
               }
               1 -> {
                   tab.icon = ContextCompat.getDrawable(mainActivity, R.drawable.continent)
               }
               2 -> {
                   tab.icon = ContextCompat.getDrawable(mainActivity, R.drawable.country)
               }
//               3 -> {
//                   tab.icon = ContextCompat.getDrawable(mainActivity, R.drawable.web)
//               }
               3 -> {
                   tab.icon = ContextCompat.getDrawable(mainActivity, R.drawable.setting)
               }
           }
       }.attach()
    }

}