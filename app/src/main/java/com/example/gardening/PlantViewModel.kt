package com.example.gardening

import PlantRepository
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PlantViewModel(private val repository: PlantRepository) : ViewModel() {


    val allPlants: LiveData<List<Plant>> = repository.allPlant.asLiveData()

    fun insert(plant: Plant) = viewModelScope.launch {
        repository.insert(plant)
    }
    fun getPlantById(plantId: Int): LiveData<Plant> {
        return repository.getPlantById(plantId)
    }
}

class PlantViewModelFactory(private val repository: PlantRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlantViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
