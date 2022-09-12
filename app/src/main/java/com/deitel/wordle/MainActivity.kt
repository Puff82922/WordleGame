package com.deitel.wordle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.et_simple)
        val button = findViewById<Button>(R.id.button)
        val guess1 = findViewById<TextView>(R.id.test1)
        val guess2 = findViewById<TextView>(R.id.test2)
        val guess3 = findViewById<TextView>(R.id.test3)
        val result1 = findViewById<TextView>(R.id.result1)
        val result2 = findViewById<TextView>(R.id.result2)
        val result3 = findViewById<TextView>(R.id.result3)
        var counter = 1
        val showAnswer = findViewById<TextView>(R.id.answerView)

        button.setOnClickListener { it ->
            if (counter == 1) {
                val enter1 = editText.text.toString().uppercase()
                guess1.text = enter1
                result1.text = checkGuess(enter1)
                editText.text.clear()
                it.hideKeyboard()
            }
            ++counter

            button.setOnClickListener { it ->
                if (counter == 2) {
                    val enter2 = editText.text.toString().uppercase()
                    guess2.text = enter2
                    result2.text = checkGuess(enter2)
                    editText.text.clear()
                    ++counter
                    it.hideKeyboard()
                }

                button.setOnClickListener {
                    if (counter == 3) {
                        val enter3 = editText.text.toString().uppercase()
                        guess3.text = enter3
                        result3.text = checkGuess(enter3)
                        editText.text.clear()
                        ++counter
                        it.hideKeyboard()
                        showAnswer.text = wordToGuess
                    }
                    if (counter >= 4) {
                        button.setOnClickListener {
                            Toast.makeText(it.context, "max tries", Toast.LENGTH_SHORT).show()
                            it.hideKeyboard()
                            ++counter
                        }
                    }
                }
            }
        }
    }
}

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }



