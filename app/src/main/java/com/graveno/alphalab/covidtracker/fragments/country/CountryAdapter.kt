package com.graveno.alphalab.covidtracker.fragments.country

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.app.model.CountryModel
import kotlinx.android.synthetic.main.card_country.view.*
import java.io.Serializable
import kotlin.collections.ArrayList

class CountryAdapter(
    private val mainActivity: MainActivity,
    private var list: ArrayList<CountryModel>,
    private var navController : NavController
) : RecyclerView.Adapter<CountryAdapter.ViewHold>() {
    private lateinit var rootView : View
    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(model: CountryModel, mainActivity: MainActivity, navController: NavController) {
            itemView.country.text = mainActivity.getString(R.string.country_continent, model.country, model.continent)
            itemView.txt_total_confirmed.text = "%,d".format(model.cases)
            itemView.txt_new_confirmed.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayCases))
            itemView.txt_total_recovered.text = "%,d".format(model.recovered)
            itemView.txt_new_recovered.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayRecovered))
            itemView.txt_total_death.text = "%,d".format(model.deaths)
            itemView.txt_new_death.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayDeaths))
            Glide.with(mainActivity)
                .load(model.countryInfo.flag)
                .error(R.drawable.ic_country_selected)
                .into(itemView.img_flag)
            itemView.lyt_stats.setOnClickListener {
                val bundle : Bundle = bundleOf(mainActivity.getString(R.string.nav_country) to model as Serializable)
                navController.navigate(R.id.action_homeFragment_to_countryDetailFragment, bundle)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        rootView = mainActivity.layoutInflater.inflate(R.layout.card_country, parent, false)
        return ViewHold(rootView)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) { holder.bindItems(list[position], mainActivity, navController) }

    override fun getItemCount(): Int { return list.size }
}