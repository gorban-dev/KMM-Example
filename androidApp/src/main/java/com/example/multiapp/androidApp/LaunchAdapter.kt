package com.example.multiapp.androidApp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.multiapp.shared.entity.RocketLaunch

class LaunchAdapter : RecyclerView.Adapter<LaunchAdapter.LaunchHolder>() {

    private var listLaunch = emptyList<RocketLaunch>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_item, parent, false)
        return LaunchHolder(view)
    }

    override fun onBindViewHolder(holder: LaunchHolder, position: Int) {
        holder.bind(listLaunch[position])
    }

    fun setLaunchData(list: List<RocketLaunch>){
        listLaunch = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = listLaunch.size

    class LaunchHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val missionNameTextView = itemView.findViewById<TextView>(R.id.missionName)
        private val launchYearTextView = itemView.findViewById<TextView>(R.id.launchYear)
        private val launchSuccessTextView = itemView.findViewById<TextView>(R.id.launchSuccess)
        private val missionDetailsTextView = itemView.findViewById<TextView>(R.id.details)

        @SuppressLint("SetTextI18n")
        fun bind(launch: RocketLaunch) {
            missionNameTextView.text =  launch.missionName
            launchYearTextView.text =  launch.launchYear.toString()
            missionDetailsTextView.text =  launch.details ?: ""
            val launchSuccess = launch.launchSuccess
            if (launchSuccess != null ) {
                if (launchSuccess) {
                    launchSuccessTextView.text = "Successful"
                    launchSuccessTextView.setTextColor((ContextCompat.getColor(itemView.context, R.color.green)))
                } else {
                    launchSuccessTextView.text = "Unsuccessful"
                    launchSuccessTextView.setTextColor((ContextCompat.getColor(itemView.context, R.color.red)))
                }
            } else {
                launchSuccessTextView.text = "No data"
                launchSuccessTextView.setTextColor((ContextCompat.getColor(itemView.context, R.color.grey)))
            }
        }
    }
}
