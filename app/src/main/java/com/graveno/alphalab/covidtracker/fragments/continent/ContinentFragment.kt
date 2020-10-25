package com.graveno.alphalab.covidtracker.fragments.continent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_continent.*
import kotlinx.android.synthetic.main.fragment_continent.view.*

class ContinentFragment : Fragment() {

    val TAG : String = "ContinentFragment"
    private lateinit var viewmodel : ContinentViewModel
    private lateinit var mainActivity: MainActivity
    private lateinit var adapter : ContinentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_continent, container, false)
        viewmodel = ViewModelProvider(this)[ContinentViewModel :: class.java]
        rootView.recyclerContinent.layoutManager = LinearLayoutManager(mainActivity)
        viewmodel.run {
            getContinents(mainActivity)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.run {
            obsContinentList.observe(viewLifecycleOwner, Observer { list ->
                adapter = ContinentAdapter(list, mainActivity, findNavController())
                recyclerContinent.adapter = adapter
            })
            obsToast.observe(viewLifecycleOwner, Observer { message -> Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show() })
        }
    }
}