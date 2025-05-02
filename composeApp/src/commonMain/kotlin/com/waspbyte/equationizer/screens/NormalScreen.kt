package com.waspbyte.equationizer.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.waspbyte.equationizer.Equation
import kotlin.math.log
import kotlin.random.Random

@Composable
fun NormalScreen(navController: NavHostController) {
    GameScreen(NormalEquation())
}

class NormalEquation : Equation {
    private var solution: String
    override val equation: String
    override fun check(input: String): Boolean {
        return input == solution
    }

    override fun next(): Equation {
        return NormalEquation()
    }

    init {
        val level = 1.0
        val scale = log(level, 10.0).toInt() + 5
        var a = Random.nextInt(scale)
        val b = Random.nextInt(scale)
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
        equation = "$a $sign $b"
    }
}