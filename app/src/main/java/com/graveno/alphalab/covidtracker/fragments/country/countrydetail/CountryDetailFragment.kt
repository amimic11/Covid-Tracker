package com.graveno.alphalab.covidtracker.fragments.country.countrydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.app.model.CountryModel
import com.graveno.alphalab.covidtracker.app.utils.DateAndTime
import kotlinx.android.synthetic.main.card_continent.view.*
import kotlinx.android.synthetic.main.card_country.view.*
import kotlinx.android.synthetic.main.fragment_country_detail.*

class CountryDetailFragment : Fragment() {

    val TAG : String = "CountryDetailFragment"
    private lateinit var mainActivity: MainActivity
    private lateinit var viewModel : CountryDetailViewModel
    private lateinit var navController: NavController
    private var model : CountryModel = CountryModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        model = requireArguments().getSerializable(mainActivity.getString(R.string.nav_country)) as CountryModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView : View = inflater.inflate(R.layout.fragment_country_detail, container, false)
        viewModel = ViewModelProvider(this)[CountryDetailViewModel::class.java]
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.run {
            fetchHistory(model.country)
            country_detail.text = mainActivity.getString(R.string.country_continent, model.country, model.continent)
            country_detail_population.text = "%,d".format(model.population)
            country_detail_update.text = DateAndTime().milliSecondToStrDate(model.updated)
            Glide.with(mainActivity)
                .load(model.countryInfo.flag)
                .error(R.drawable.ic_country_selected)
                .into(img_country_detail_flag)
            Glide.with(mainActivity)
                .load(getImageUrl(model.continent))
                .error(R.drawable.ic_country_selected)
                .into(img_country_detail_continent)
            txt_country_cases.text = "%,d".format(model.cases)
            txt_country_new_cases.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayCases))
            txt_country_recovered.text = "%,d".format(model.recovered)
            txt_country_new_recovered.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayRecovered))
            txt_country_death.text = "%,d".format(model.deaths)
            txt_country_new_death.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayDeaths))
            txt_country_test.text = "%,d".format(model.tests)
            txt_country_test_million.text = model.testsPerOneMillion.toString()
            txt_country_test_people.text = "%,d".format(model.oneTestPerPeople)
            txt_country_case_people.text = "%,d".format(model.oneCasePerPeople)
            txt_country_death_people.text = "%,d".format(model.oneDeathPerPeople)
            txt_country_critical.text = "%,d".format(model.critical)
            txt_country_critical_million.text = model.criticalPerOneMillion.toString()
            txt_country_recovered_million.text = model.recoveredPerOneMillion.toString()
            txt_country_death_million.text = model.deathsPerOneMillion.toString()
            txt_country_active.text = "%,d".format(model.active)
            txt_country_active_million.text = model.activePerOneMillion.toString()

            obsCountryHistory.observe(viewLifecycleOwner, Observer { history ->
                /*case...*/
                graph_cases.animation.duration = 3000
                graph_cases.animate(history.timeline.cases)
                graph_cases.lineColor = ContextCompat.getColor(mainActivity, android.R.color.holo_orange_dark)
                /*recovered...*/
                graph_recovered.animation.duration = 3000
                graph_recovered.animate(history.timeline.recovered)
                graph_recovered.lineColor = ContextCompat.getColor(mainActivity, android.R.color.holo_green_dark)
                /*deaths...*/
                graph_death.animation.duration = 3000
                graph_death.animate(history.timeline.deaths)
                graph_death.lineColor = ContextCompat.getColor(mainActivity, android.R.color.holo_red_dark)
            })
        }
    }
}