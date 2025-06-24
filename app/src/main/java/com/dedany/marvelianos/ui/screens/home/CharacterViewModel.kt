package com.dedany.marvelianos.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedany.Marvelianos.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.dedany.domain.entities.Character

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase

): ViewModel() {
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadCharacters() {
        viewModelScope.launch {
            _isLoading.value = true
            val list = characterUseCase.getCharacters()
            _characters.value = list
            _isLoading.value = false
        }
    }
    fun searchCharacters(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val results = characterUseCase.searchCharacter(query)
            _characters.value = results
            _isLoading.value = false
        }
    }


}