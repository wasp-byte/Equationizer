package com.waspbyte.equationizer.screens

import androidx.lifecycle.ViewModel
import com.waspbyte.equationizer.Puzzle
import com.waspbyte.equationizer.PuzzleResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(puzzle: Puzzle): ViewModel() {
    private val _currentEquation = MutableStateFlow(puzzle)
    val currentEquation = _currentEquation.asStateFlow()

    private val _gameInput = MutableStateFlow<String>("")
    val gameInput = _gameInput.asStateFlow()

    fun onInputChange(newText: String) {
        println(newText)
        when (_currentEquation.value.check(newText)) {
            PuzzleResult.Wrong -> _gameInput.value = newText
            PuzzleResult.Finished -> {
                _gameInput.value = ""
                _currentEquation.value = _currentEquation.value.next()
            }
            PuzzleResult.Correct -> _gameInput.value = ""
        }
    }
}