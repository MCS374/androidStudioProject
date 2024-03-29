package com.example.dndquiz

import Question
import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_beholder, false),
        Question(R.string.question_Lily, false),
        Question(R.string.question_fireball, true),
        Question(R.string.question_Peace, true),
        Question(R.string.question_Yahya, true))
        var currentIndex: Int = 0

        // add a few more functions to remember the position
    // of my current question

    // this function keep track of current question's answer
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    // once I remember the current question's position
    // I'll write a function that move to the next question
    fun moveToNext(){
        currentIndex = (currentIndex+1) % questionBank.size
    }

}