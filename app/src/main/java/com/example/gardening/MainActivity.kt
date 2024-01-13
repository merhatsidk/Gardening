package com.example.gardening

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import layout.NewPlantActivity

class MainActivity : AppCompatActivity() {

    private val newPlantActivityRequestCode = 1
    val plantViewModel: PlantViewModel by viewModels {
        PlantViewModelFactory((applicationContext as PlantsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PlantListAdapter { plantId ->
            val intent = Intent(this@MainActivity, PlantDetailActivity::class.java)
            intent.putExtra("plantId", plantId)
            startActivity(intent)
        }

        recyclerView.adapter = adapter





        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPlantActivity::class.java)
            startActivityForResult(intent, newPlantActivityRequestCode)
        }
        plantViewModel.allPlants.observe( this) { plants ->
            plants.let { adapter.submitList(it) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (requestCode == newPlantActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getParcelableExtra<Plant>(NewPlantActivity.EXTRA_REPLY_NAME)?.let { plant ->
                plantViewModel.insert(plant)
            } ?: run {
                Toast.makeText(
                    applicationContext,
                    R.string.app_name,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
