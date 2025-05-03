package com.waspbyte.equationizer.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.waspbyte.equationizer.PrefsDataStore
import com.waspbyte.equationizer.Puzzle
import com.waspbyte.equationizer.PuzzleResult
import kotlin.math.log
import kotlin.random.Random

@Composable
fun ClassicScreen(navController: NavHostController, prefs: PrefsDataStore) {
    GameScreen(ClassicPuzzle(), prefs, navController)
}

class ClassicPuzzle : Puzzle {
    private var solution: String
    override val puzzle: String
    override fun check(input: String): PuzzleResult {
        return if (input == solution) PuzzleResult.Finished else PuzzleResult.Wrong
    }

    override fun next(): Puzzle {
        return ClassicPuzzle()
    }

    init {
        val level = 1.0
        val scale = log(level, 10.0).toInt() + 5
        var a = Random.nextInt(scale)
        val b = Random.nextInt(1, scale)
        val signs = arrayOf('+', '-', '*', '/')
        val sign = signs.random()
        solution = when(sign) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> {
                a *= b
                a / b
            }
            else -> {0}
        }.toString()
        puzzle = "$a $sign $b"
    }
}
