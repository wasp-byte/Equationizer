package com.waspbyte.equationizer.screens

import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.waspbyte.equationizer.HIGH_SCORE_KEY
import com.waspbyte.equationizer.PrefsDataStore
import com.waspbyte.equationizer.Puzzle
import com.waspbyte.equationizer.PuzzleResult
import com.waspbyte.equationizer.TIMER_KEY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.math.min
import kotlin.reflect.KClass

class GameViewModel(private val prefs: PrefsDataStore): ViewModel() {
    val currentEquation = MutableStateFlow<Puzzle>(EquationPuzzle(1))

    private val _gameInput = MutableStateFlow<String>("")
    val gameInput = _gameInput.asStateFlow()

    val totalTime = 20000.0f
    var timeLeft = totalTime

    private val _progress = MutableStateFlow(1f)
    var progress = _progress.asStateFlow()

    var score = 0


    fun startTimer() {
        viewModelScope.launch {
            if (prefs.data.first()[TIMER_KEY] != true) {
                _progress.value = 0.0f
                return@launch
            }
            while (true) {
                delay(100L)
                timeLeft -= 100.0f
                _progress.value = timeLeft / totalTime
                if (timeLeft <=  0f) {
                    val high = prefs.data.first()[HIGH_SCORE_KEY] ?: 0
                    if (score > high)
                        prefs.edit { it[HIGH_SCORE_KEY] = score }
                    return@launch
                }
            }
        }
    }

    fun onInputChange(newText: String) {
        when (currentEquation.value.check(newText)) {
            PuzzleResult.Wrong -> _gameInput.value = newText
            PuzzleResult.Finished -> {
                _gameInput.value = ""
                currentEquation.value = currentEquation.value.next()
                timeLeft = min(totalTime, timeLeft + 2000)
                score++
            }
            PuzzleResult.Correct -> _gameInput.value = ""
        }
    }
}

class GameVMFactory(private val prefs: PrefsDataStore) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return GameViewModel(prefs) as T
    }
}