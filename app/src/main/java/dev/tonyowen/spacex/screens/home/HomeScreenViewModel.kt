package dev.tonyowen.spacex.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.tonyowen.spacex.modules.business.rocket.Rocket
import dev.tonyowen.spacex.network.repos.RocketRepository
import dev.tonyowen.spacex.network.utils.NetworkResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val rocketRepository: RocketRepository) : ViewModel() {

    private val _rockets = MutableStateFlow<NetworkResponse<List<Rocket>>>(NetworkResponse.Loading())
    val rockets = _rockets.asStateFlow()

    fun getRockets() {
        viewModelScope.launch {
            _rockets.value = rocketRepository.getRockets()
        }
    }
}