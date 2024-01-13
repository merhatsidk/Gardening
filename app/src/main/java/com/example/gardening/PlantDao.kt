package com.example.gardening

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {


    @Query("SELECT * FROM plant_table ORDER BY plant_id ASC")
    fun getPlant(): Flow<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(plant: Plant)

    @Query("DELETE FROM plant_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM plant_table WHERE plant_id = :plantId")
    fun getPlantById(plantId: Int): LiveData<Plant>
}
