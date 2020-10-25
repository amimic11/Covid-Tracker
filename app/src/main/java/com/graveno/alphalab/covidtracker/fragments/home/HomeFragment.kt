package com.graveno.alphalab.covidtracker.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    
    val TAG: String = "HomeFragment"
    private lateinit var rootView: View
    private lateinit var viewModel: HomeViewModel
    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        viewModel = ViewModelProvider(this)[HomeViewModel :: class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.run {
            home_view_pager.adapter = viewModel.viewPagerAdapter(mainActivity)
            viewModel.setViewNTab(mainActivity, home_view_pager, home_tab_layout)
        }
    }

}