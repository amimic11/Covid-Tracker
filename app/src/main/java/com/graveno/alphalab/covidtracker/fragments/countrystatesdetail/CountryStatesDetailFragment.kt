package com.graveno.alphalab.covidtracker.fragments.countrystatesdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_country_states_detail.*

class CountryStatesDetailFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var viewModel : CountryStatesDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView : View = inflater.inflate(R.layout.fragment_country_states_detail, container, false)
        viewModel = ViewModelProvider(this)[CountryStatesDetailViewModel :: class.java]
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.run {
            loadStatesDetail(mainActivity, web_states_detail)
            img_web_forward.setOnClickListener { if (web_states_detail.canGoForward()) web_states_detail.goForward() }
            img_web_back.setOnClickListener { if (web_states_detail.canGoBack()) web_states_detail.goBack() }
            img_web_refresh.setOnClickListener { loadStatesDetail(mainActivity, web_states_detail) }
        }
    }
}