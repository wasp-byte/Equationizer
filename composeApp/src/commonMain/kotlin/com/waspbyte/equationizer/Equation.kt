package com.waspbyte.equationizer

interface Equation {
    val equation: String
    fun check(input: String): Boolean
    fun next(): Equation
}