package com.graveno.alphalab.covidtracker.fragments.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.android.synthetic.main.fragment_country.view.*

class CountryFragment : Fragment() {

    val TAG : String = "CountryFragment"
    private lateinit var viewModel: CountryViewModel
    private lateinit var mainActivity: MainActivity
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_country, container, false)
        viewModel = ViewModelProvider(this)[CountryViewModel :: class.java]
        rootView.recycler_country.layoutManager = LinearLayoutManager(mainActivity)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.run {
            fetchCountry(mainActivity)
            obsList.observe(viewLifecycleOwner, Observer { list ->
                list.sortByDescending { it.cases }
                adapter = CountryAdapter(mainActivity, list, findNavController())
                recycler_country.adapter = adapter
            })
            obsToast.observe(viewLifecycleOwner, Observer { message -> Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show() })
        }
    }
}