import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.gardening.Plant
import com.example.gardening.PlantDao
import kotlinx.coroutines.flow.Flow

class PlantRepository(private val plantDao: PlantDao) {

    val allPlant: Flow<List<Plant>> = plantDao.getPlant()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Plant) {
        plantDao.insert(word)
    }

    fun getPlantById(plantId: Int): LiveData<Plant> {
        return plantDao.getPlantById(plantId)
    }
}
