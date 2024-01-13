package com.example.gardening

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Plant::class], version = 1)
abstract class PlantRoomDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    companion object {
        @Volatile
        private var INSTANCE: PlantRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PlantRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantRoomDatabase::class.java,
                    "plant_database"
                )

                    .fallbackToDestructiveMigration()
                    .addCallback(PlantDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class PlantDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.plantDao())
                    }
                }
            }
        }


        suspend fun populateDatabase(plantDao: PlantDao) {

            plantDao.deleteAll()

            var plant = Plant(plantName = "tree", species = "something", sunlightRequirement = "true", wateringSchedule = "ele")
            plantDao.insert(plant)
//
        }
    }
}
