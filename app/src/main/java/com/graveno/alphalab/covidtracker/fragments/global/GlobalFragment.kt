package com.graveno.alphalab.covidtracker.fragments.global

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.app.utils.DateAndTime
import kotlinx.android.synthetic.main.fragment_global.*
import java.text.DecimalFormat

class GlobalFragment : Fragment() {

    val TAG : String = "GlobalFragment"
    private lateinit var mainActivity: MainActivity
    private lateinit var viewModel : GlobalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView : View = inflater.inflate(R.layout.fragment_global, container, false)
        viewModel = ViewModelProvider(this)[GlobalViewModel :: class.java]

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.run {
            setGlobe(mainActivity, img_global)
            fetchGlobalData(mainActivity)

            obsBindInfo.observe(viewLifecycleOwner, Observer { model ->
                global_population.text = "%,d".format(model.population)
                global_affect_country.text = "%,d".format(model.affectedCountries)
                global_active.text = "%,d".format(model.active)
                global_active_million.text = DecimalFormat("0.00").format(model.activePerOneMillion).toString()
                global_critical.text = "%,d".format(model.critical)
                global_critical_million.text = DecimalFormat("0.00").format(model.criticalPerOneMillion).toString()
                global_test.text = "%,d".format(model.tests)
                global_test_million.text = DecimalFormat("0.00").format(model.testsPerOneMillion).toString()
                global_total_case.text = "%,d".format(model.cases)
                global_new_case.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayCases))
                global_case_million.text = "%,d".format(model.casesPerOneMillion)
                global_total_recovered.text = "%,d".format(model.recovered)
                global_new_recovered.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayRecovered))
                global_recovered_million.text = DecimalFormat("0.00").format(model.recoveredPerOneMillion).toString()
                global_total_deaths.text = "%,d".format(model.deaths)
                global_new_death.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayDeaths))
                global_death_million.text = "%,f".format(model.deathsPerOneMillion)
                global_update.text = DateAndTime().milliSecondToStrDate(model.updated)
            })
        }
    }

}