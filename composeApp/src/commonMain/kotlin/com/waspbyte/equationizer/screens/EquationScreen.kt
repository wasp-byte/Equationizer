package com.waspbyte.equationizer.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.waspbyte.equationizer.PrefsDataStore
import com.waspbyte.equationizer.Puzzle
import com.waspbyte.equationizer.PuzzleResult
import kotlin.math.log
import kotlin.random.Random

@Composable
fun EquationScreen(navController: NavHostController, prefs: PrefsDataStore) {
    GameScreen(EquationPuzzle(1), prefs, navController)
}

class EquationPuzzle(private val level: Int) : Puzzle {
    private var solution: MutableList<String>
    override val puzzle: String
    override fun check(input: String): PuzzleResult {
        if (solution.remove(input)) {
            if (solution.isEmpty())
                return PuzzleResult.Finished
            return PuzzleResult.Correct
        }
        return PuzzleResult.Wrong
    }

    override fun next(): Puzzle {
        return EquationPuzzle(level + 1)
    }

    private fun powera(a: Int, power: Int): String {
        return if (a == 0) "" else if (a == 1) "x^$power " else "${a}x^$power "
    }

    private fun power(a: Int, power: Int): String {
        val p = if (power == 0) "" else if (power == 1) "x" else "x^$power"
        return when (a) {
            0 -> ""
            1 -> {
                if (power == 0)
                    "+ $a "
                else
                    "+ $p "
            }
            -1 -> {
                if (power == 0)
                    "- ${-a} "
                else
                    "- $p "
            }
            in (1..Int.MAX_VALUE) -> {
                "+ ${a}$p "
            }
            else -> {
                "- ${-a}$p "
            }
        }
    }

    init {
        val scale = (log(level.toDouble(), 10.0).toInt() + 3.0).toInt()

        var numSol = 2
        val n = Random.nextInt(0, level)
        if (n > 5)
            numSol++
        if (n > 15)
            numSol++

        val xs = (0 until numSol).map {Random.nextInt(scale)}.toList()
        solution = xs.map{it.toString()}.toMutableList()

        puzzle = when (numSol) {
            2 -> {
                val a = Random.nextInt(1, scale)
                val b = -(xs[0] + xs[1]) * a
                val c = xs[0] * xs[1] * a

                "${powera(a, 2)}${power(b, 1)}${power(c, 0)}"
            }
            3 -> {
                val a = Random.nextInt(1, scale)
                val b = -(xs[0] + xs[1] + xs[2]) * a
                val c = (xs[0] * xs[1] + xs[0] * xs[2] + xs[1] * xs[2]) * a
                val d = -xs[0] * xs[1] * xs[2] * a

                "${powera(a, 3)}${power(b, 2)}${power(c, 1)}${power(d, 0)}"
            }
            4 -> {
                val a = Random.nextInt(1, scale)
                val b = -(xs[0] + xs[1] + xs[2] + xs[3]) * a
                val c = (xs[0] * xs[1] + xs[0] * xs[2] + xs[0] * xs[3] + xs[1] * xs[2] + xs[1] * xs[3] + xs[2] * xs[3]) * a
                val d = -(xs[0] * xs[1] * xs[2] + xs[0] * xs[1] * xs[3] + xs[0] * xs[2] * xs[3] + xs[1] * xs[2] * xs[3]) * a
                val e = xs[0] * xs[1] * xs[2] * xs[3] * a

                "${powera(a, 4)}${power(b, 3)}${power(c, 2)}${power(d, 1)}${power(e, 0)}"
            }
            else -> ""
        }
    }
}