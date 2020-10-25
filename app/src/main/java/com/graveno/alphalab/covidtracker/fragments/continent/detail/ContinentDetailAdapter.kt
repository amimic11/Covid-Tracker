package com.graveno.alphalab.covidtracker.fragments.continent.detail

import android.os.Bundle
import android.view.LayoutInflater
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

class ContinentDetailAdapter(private val mainActivity: MainActivity, private var list : ArrayList<CountryModel>,private val navController: NavController) : RecyclerView.Adapter<ContinentDetailAdapter.ViewHold>() {
    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindInfo(
            mainActivity : MainActivity,
            model : CountryModel,
            navController : NavController
        ) {
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
                navController.navigate(R.id.action_continentDetailFragment_to_countryDetailFragment, bundle)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val rootView : View = LayoutInflater.from(mainActivity).inflate(R.layout.card_country, parent, false)
        return ViewHold(rootView)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.bindInfo(mainActivity, list[position], navController)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}