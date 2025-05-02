package com.waspbyte.equationizer.screens

import androidx.lifecycle.ViewModel
import com.waspbyte.equationizer.Equation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(equation: Equation): ViewModel() {
    private val _currentEquation = MutableStateFlow(equation)
    val currentEquation = _currentEquation.asStateFlow()

    private val _gameInput = MutableStateFlow<String>("")
    val gameInput = _gameInput.asStateFlow()

    fun onInputChange(newText: String) {
        if (_currentEquation.value.check(newText)) {
            _gameInput.value = ""
            _currentEquation.value = _currentEquation.value.next()
        } else {
            _gameInput.value = newText
        }
    }
}