package com.example.gardening

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PlantListAdapter(private val onItemClick: (Int) -> Unit) : ListAdapter<Plant, PlantListAdapter.PlantViewHolder>(PLANT_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)

        return PlantViewHolder.create(itemView, onItemClick)    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.plantName)
    }

    class PlantViewHolder(itemView: View, onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val plantItemView: TextView = itemView.findViewById(R.id.textView)

        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(text: String?) {
            plantItemView.text = text
        }

        companion object {
            fun create(itemView: View, onItemClick: (Int) -> Unit): PlantViewHolder {
                return PlantViewHolder(itemView, onItemClick)
            }
        }
    }


    companion object {
        private val PLANT_COMPARATOR = object : DiffUtil.ItemCallback<Plant>() {
            override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
                return oldItem.plantName == newItem.plantName
            }
        }
    }
}
