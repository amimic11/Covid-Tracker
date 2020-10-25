package com.graveno.alphalab.covidtracker.fragments.continent

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.app.model.ContinentsModel
import kotlinx.android.synthetic.main.card_continent.view.*
import kotlinx.android.synthetic.main.card_continent.view.txt_continent
import kotlinx.android.synthetic.main.card_continent.view.txt_new_confirmed
import kotlinx.android.synthetic.main.card_continent.view.txt_new_recovered
import kotlinx.android.synthetic.main.card_continent.view.txt_population
import kotlinx.android.synthetic.main.card_continent.view.txt_total_confirmed
import kotlinx.android.synthetic.main.card_continent.view.txt_total_death
import kotlinx.android.synthetic.main.card_continent.view.txt_total_recovered
import java.io.Serializable

class ContinentAdapter(
    private val list: ArrayList<ContinentsModel>,
    val mainActivity: MainActivity,
    private val navController: NavController
) : RecyclerView.Adapter<ContinentAdapter.ViewHold>() {
    
    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindInfo(mainActivity: MainActivity, model: ContinentsModel, navController: NavController) {
            itemView.txt_continent.text = model.continent
            itemView.txt_population.text = "%,d".format(model.population)
            itemView.txt_total_confirmed.text = "%,d".format(model.cases)
            itemView.txt_new_confirmed.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayCases))
            itemView.txt_total_recovered.text = "%,d".format(model.recovered)
            itemView.txt_new_recovered.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayRecovered))
            itemView.txt_total_death.text = "%,d".format(model.deaths)
            itemView.txt_new_deaths.text = mainActivity.getString(R.string.new_status, "%,d".format(model.todayDeaths))
            val url : String = getImageUrl(model.continent)
            Glide.with(mainActivity)
                .load(url)
                .error(R.drawable.ic_country_selected)
                .into(itemView.img_continent)
            itemView.lyt_card.setOnClickListener {
                val bundle : Bundle = bundleOf(mainActivity.getString(R.string.nav_continent) to model as Serializable)
                navController.navigate(R.id.action_homeFragment_to_continentDetailFragment, bundle)
            }
        }

        private fun getImageUrl(continent: String): String {
            return when (continent) {
                "Asia" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Asia_%28orthographic_projection%29.svg/220px-Asia_%28orthographic_projection%29.svg.png"
                "North America" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Location_North_America.svg/2000px-Location_North_America.svg.png"
                "South America" -> "https://upload.wikimedia.org/wikipedia/commons/8/8c/Location_South_America.png"
                "Europe" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Europe_orthographic_Caucasus_Urals_boundary_%28with_borders%29.svg/1200px-Europe_orthographic_Caucasus_Urals_boundary_%28with_borders%29.svg.png"
                "Africa" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/Africa_%28orthographic_projection%29.svg/1200px-Africa_%28orthographic_projection%29.svg.png"
                "Australia/Oceania" -> "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Australia-New_Guinea_%28orthographic_projection%29.svg/1200px-Australia-New_Guinea_%28orthographic_projection%29.svg.png"
                else -> "https://toppng.com/uploads/preview/world-globe-png-clip-freeuse-download-world-map-globe-11562910726e1f0hvovtx.png"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val rootView : View = mainActivity.layoutInflater.inflate(R.layout.card_continent, parent, false)
        return ViewHold(rootView)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.bindInfo(mainActivity, list[position], navController)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}