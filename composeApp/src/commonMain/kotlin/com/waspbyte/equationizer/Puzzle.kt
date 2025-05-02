package com.waspbyte.equationizer

interface Puzzle {
    val puzzle: String
    fun check(input: String): PuzzleResult
    fun next(): Puzzle
}

enum class PuzzleResult {
    Wrong,
    Correct,
    Finished,
}