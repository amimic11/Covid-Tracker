package com.graveno.alphalab.covidtracker.fragments.continent.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.app.model.ContinentsModel
import com.graveno.alphalab.covidtracker.app.utils.DateAndTime
import kotlinx.android.synthetic.main.fragment_continent_detail.*

class ContinentDetailFragment : Fragment() {

    val TAG : String = "ContinentDetailFragment"
    private lateinit var mainActivity: MainActivity
    private lateinit var viewModel : ContinentDetailViewModel
    private lateinit var navController: NavController
    private lateinit var model: ContinentsModel
    private lateinit var adapter : ContinentDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        model = requireArguments().getSerializable(mainActivity.getString(R.string.nav_continent)) as ContinentsModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView : View = inflater.inflate(R.layout.fragment_continent_detail, container, false)
        viewModel = ViewModelProvider(this)[ContinentDetailViewModel :: class.java]
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        viewModel.run {
            bindCountries(mainActivity, model.countries!!)

            continent_detail_recycler.layoutManager = LinearLayoutManager(mainActivity)
            lyt_progress.visibility = View.VISIBLE
            continent_detail_recycler.visibility = View.GONE

            continent.text = model.continent
            continent_population.text = "%,d".format(model.population)
            continent_update.text = DateAndTime().milliSecondToStrDate(model.updated)
            txt_total_confirmed.text = "%,d".format(model.cases)
            txt_new_confirmed.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayCases))
            txt_total_recovered.text = "%,d".format(model.recovered)
            txt_new_recovered.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayRecovered))
            txt_total_death.text = "%,d".format(model.deaths)
            txt_new_death.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayDeaths))
            txt_test.text = "%,d".format(model.tests)
            txt_test_million.text = model.testsPerOneMillion.toString()
            txt_active.text = "%,d".format(model.active)
            txt_active_million.text = model.activePerOneMillion.toString()
            txt_critical.text = "%,d".format(model.critical)
            txt_critical_million.text = model.criticalPerOneMillion.toString()
            txt_recovered_million.text = model.recoveredPerOneMillion.toString()
            txt_death_million.text = model.deathsPerOneMillion.toString()
            getContinentImage(mainActivity, model.continent, img_continent)
            obsCountries.observe(viewLifecycleOwner, Observer { countries ->
                adapter = ContinentDetailAdapter(mainActivity, list = countries, navController)
                continent_detail_recycler.adapter = adapter
                lyt_progress.visibility = View.GONE
                continent_detail_recycler.visibility = View.VISIBLE
            })
        }
    }
}