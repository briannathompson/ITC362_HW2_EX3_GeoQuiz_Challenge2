package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding // Pg40 Listing 2.7

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }
        // Pg 41 Listing 2.8: Using ActivityMainBinding, added binding to the front
        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        // Challenge 2: Create a Previous Button
        // Copied the nextButton code and changed the current index to decrease by one
        /* The below code was functional, but if I was on the first question (currentIndex = 0, the program would crash),
        so I thought that if I used an if-else statement, I could avoid it and it worked!!!
         */
        //binding.previousButton.setOnClickListener {
        //    currentIndex = (currentIndex - 1) % questionBank.size
        //    updateQuestion()
        //}
        binding.previousButton.setOnClickListener {
            if(currentIndex > 0){                                   // Checks if the currentIndex is more than 0
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
            }
            else{
                currentIndex = 5 % questionBank.size                // if the currentIndex IS 0, then the currentIndex is set to 5 (there are 6 questions)
                updateQuestion()
            }
        }

        // Challenge 1: Set a Listener for the TextView that displays the Question (questionTextView)
        // Copied code from the nextButton and just replaced the button with the TextView
        binding.questionTextView.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}