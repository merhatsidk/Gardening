
package layout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.gardening.Plant
import com.example.gardening.R


class NewPlantActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_plant)
        val plantName = findViewById<EditText>(R.id.plant_name)
        val plantSpecies = findViewById<EditText>(R.id.plant_species)
        val wateringFrequency = findViewById<EditText>(R.id.watering_frequency)
        val plantingDate = findViewById<EditText>(R.id.planting_date)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(plantName.text) || TextUtils.isEmpty(plantSpecies.text) ||
                TextUtils.isEmpty(wateringFrequency.text) || TextUtils.isEmpty(plantingDate.text)
            ) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val plant = Plant(
                    0,
                    plantName.text.toString(),
                    plantSpecies.text.toString(),
                    wateringFrequency.text.toString(),
                    plantingDate.text.toString()
                )

                replyIntent.putExtra(EXTRA_REPLY_NAME, plant)

                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }


    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.gardening.REPLY"
        const val EXTRA_REPLY_NAME = "com.example.gardening.REPLY_NAME"
        const val EXTRA_REPLY_SPECIES = "com.example.gardening.REPLY_SPECIES"
        const val EXTRA_REPLY_WATERING = "com.example.gardening.REPLY_WATERING"
        const val EXTRA_REPLY_PLANTING = "com.example.gardening.REPLY_PLANTING"
    }
}
