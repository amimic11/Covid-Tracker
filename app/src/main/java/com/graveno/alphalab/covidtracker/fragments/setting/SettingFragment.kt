package com.graveno.alphalab.covidtracker.fragments.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    val TAG : String = "SettingFragment"
    private lateinit var mainActivity: MainActivity
    private lateinit var viewModel: SettingViewModel
    private lateinit var adapter: SettingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView : View = inflater.inflate(R.layout.fragment_setting, container, false)
        viewModel = ViewModelProvider(this)[SettingViewModel :: class.java]
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.run {
            recycler_setting.layoutManager = LinearLayoutManager(mainActivity)
            prepSettings(mainActivity)
            obsSettings.observe(viewLifecycleOwner, Observer { list ->
                adapter = SettingAdapter(list, mainActivity)
                recycler_setting.adapter = adapter
            })
        }

    }
}