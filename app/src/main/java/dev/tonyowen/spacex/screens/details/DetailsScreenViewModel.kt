package dev.tonyowen.spacex.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.tonyowen.spacex.modules.business.rocket.Rocket
import dev.tonyowen.spacex.network.repos.RocketRepository
import dev.tonyowen.spacex.network.utils.NetworkResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsScreenViewModel(private val rocketRepository: RocketRepository) : ViewModel() {

    private val _rocketState = MutableStateFlow<NetworkResponse<Rocket>>(NetworkResponse.Loading())
    val rocketState = _rocketState.asStateFlow()

    fun getRocket(id: String) {
        viewModelScope.launch {
            val response = rocketRepository.getRocketById(id)
            _rocketState.value = response
        }
    }
}