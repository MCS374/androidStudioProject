package com.example.dndquiz

import Question
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dndquiz.databinding.ActivityMainBinding
// add a log variable for debugging
private const val TAG = "MainActivity"



//This is a test comment to test out pushing changes to git

class MainActivity : AppCompatActivity() {
    // create two button objects ready to go
    private lateinit var binding: ActivityMainBinding
    //private lateinit var trueButton: Button
    //private lateinit var falseButton: Button
    // question bank
    private val quizViewModel: QuizViewModel by viewModels()

    // create cheatLauncher

    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        // do whatever it takes to handle the result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // before a process is created
        // put the tag in your log
        Log.d(TAG, "onCreate(Bundle?) is called, a process is created")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // once your app is launched, you need to
        // you need to wire those two buttons
        // trueButton = findViewById(R.id.true_button)
        // falseButton = findViewById(R.id.false_button)

        // log the process
        Log.d(TAG, "We got a QuizViewModel: QuizViewModel")
        binding.trueButton.setOnClickListener { view: View ->
            // do something when you click the true button
            // popping out a text that says something
            //Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { view: View ->
            // do somethign when you click on the false button
            //Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()
            checkAnswer(false)
        }

        // once you click the next button, text is going to rotate
        binding.nextButton.setOnClickListener { view: View->

            quizViewModel.moveToNext()
            //val questionTextResId = questionBank[currentIndex].textResId
            //binding.questionTextView.setText(questionTextResId)
            updateQuestion()
        }

        binding.cheatButton.setOnClickListener {
            // if you click this, start cheatActivity
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            //startActivity(intent)
            cheatLauncher(intent)

        }
        updateQuestion()
        // we are going to create the roating text
        //val questionTextResId = questionBank[currentIndex].textResId
        //binding.questionTextView.setText(questionTextResId)

    }

    // onstart in my log
    override fun onStart(){
        super.onStart()
        Log.d(TAG, "OnStart() is called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "OnResume() is called")
    }

     fun OnPause(){
        super.onPause()
        Log.d(TAG, "OnPause() is called ")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "OnStop() is called")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "OnDestroyed() is called")
    }
    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    // I need a function that checks my answer
    private fun checkAnswer (userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
     // check if my answer is going to be the correct answer ot not
       val messageResId =  if (userAnswer == correctAnswer ){
           R.string.correct_toast
       }
        else{
            R.string.incorrect_toast
       }
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }
}