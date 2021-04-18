package com.graveno.alphalab.covidtracker.fragments.setting

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.graveno.alphalab.covidtracker.R
import com.graveno.alphalab.covidtracker.activity.MainActivity
import com.graveno.alphalab.covidtracker.app.model.SettingModel
import kotlinx.android.synthetic.main.card_setting.view.*


class SettingAdapter(private var list: ArrayList<SettingModel>, private val mainActivity: MainActivity) : RecyclerView.Adapter<SettingAdapter.ViewHold>() {
    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindInfo(settingModel: SettingModel, mainActivity: MainActivity) {
            itemView.txt_setting.text = settingModel.settingName
            itemView.img_setting.background = ContextCompat.getDrawable(
                mainActivity,
                settingModel.settingDrawable
            )
            itemView.lyt_setting.setOnClickListener {
                when(settingModel.settingName) {
                    mainActivity.getString(R.string.indian_state_detail) -> {
                        //open about page...
                        val aboutIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mainActivity.getString(R.string.india_covid_detail)))
                        mainActivity.startActivity(aboutIntent)
                    }
                    mainActivity.getString(R.string.exit) -> {
                        mainActivity.onBackPressed()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val rootView : View = LayoutInflater.from(mainActivity).inflate(R.layout.card_setting, parent, false)
        return ViewHold(rootView)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.bindInfo(list[position], mainActivity)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}