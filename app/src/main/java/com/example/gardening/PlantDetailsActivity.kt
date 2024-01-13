package com.example.gardening

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gardening.R

class PlantDetailActivity : AppCompatActivity() {

    private var plantId: Int = 0

    val plantViewModel: PlantViewModel by viewModels {
        PlantViewModelFactory((applicationContext as PlantsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        plantId = intent.getIntExtra("plantId", 0) + 1
        println("this is plant id")
        println(plantId)


        plantViewModel.getPlantById(plantId).observe(this, Observer { plant ->
            plant?.let { displayPlantDetails(it) }
        })
    }

    private fun displayPlantDetails(plant: Plant) {
        findViewById<TextView>(R.id.plantNameTextView)?.text = plant.plantName
        findViewById<TextView>(R.id.plantTypeTextView)?.text = "Type: ${plant.species}"
        findViewById<TextView>(R.id.wateringFrequencyTextView)?.text =
            "Watering Frequency: ${plant.wateringSchedule} days"
        findViewById<TextView>(R.id.plantingDateTextView)?.text =
            "Planting Date: ${plant.sunlightRequirement}"
    }
}
