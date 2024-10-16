package com.almarpa.dragonballapp.ui.home

import com.almarpa.dragonballapp.domain.Repository
import com.almarpa.dragonballapp.domain.model.CharacterModel
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _characters = MutableStateFlow<List<CharacterModel>>(viewModelScope, emptyList())
    @NativeCoroutinesState
    val characters: StateFlow<List<CharacterModel>> = _characters

    init {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) { repository.getCharacters() }
            _characters.value = response
        }
    }
}