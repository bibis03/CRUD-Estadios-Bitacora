package edu.itvo.roompersistence.presentation.stadium

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.itvo.roompersistence.domain.model.Stadium
import edu.itvo.roompersistence.domain.usecase.StadiumUseCases
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StadiumViewModel @Inject constructor(
    private val stadiumUseCases: StadiumUseCases
) : ViewModel() {

    val stadiums = stadiumUseCases.getStadiums()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addStadium(stadium: Stadium) {
        viewModelScope.launch {
            stadiumUseCases.addStadium(stadium)
        }
    }

    fun deleteStadium(stadium: Stadium) {
        viewModelScope.launch {
            stadiumUseCases.deleteStadium(stadium)
        }
    }
}