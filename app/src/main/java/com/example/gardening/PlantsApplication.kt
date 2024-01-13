package com.example.gardening

import PlantRepository
import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PlantsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())


    val database by lazy { PlantRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { PlantRepository(database.plantDao()) }
}
