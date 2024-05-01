//MainActivity.kt
package com.example.splash

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.color.utilities.Score

class MainActivity : AppCompatActivity(),GameTask {
    lateinit var rootLayout:LinearLayout
    lateinit var startBtn:Button
    lateinit var mGameView: GameView
    lateinit var score: TextView
    lateinit var sharedPreferences: SharedPreferences
    var currentScore=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startBtn=findViewById(R.id.startBtn)
        rootLayout=findViewById(R.id.rootLayout)
        score=findViewById(R.id.score)
        mGameView= GameView(this,this)


        sharedPreferences = getSharedPreferences("GamePrefs", MODE_PRIVATE)


        startBtn.setOnClickListener{
            mGameView.setBackgroundResource(R.drawable.rd1)
            rootLayout.addView(mGameView)
            startBtn.visibility=View.GONE
            score.visibility=View.GONE

        }
        loadHighScore()
    }

    override fun closeGame(mScore: Int) {
        currentScore=mScore
        score.text="High Score: ${sharedPreferences.getInt("highScore", 0)} \nCurrent Score: $currentScore"
        rootLayout.removeView(mGameView)
        startBtn.visibility=View.VISIBLE
        score.visibility=View.VISIBLE

        saveHighScore(currentScore)
    }

    private fun loadHighScore() {
        val highScore = sharedPreferences.getInt("highScore", 0)
        score.text = "High Score: $highScore"
    }
    private fun saveHighScore(newScore: Int) {
        val currentHighScore = sharedPreferences.getInt("highScore", 0)
        if (newScore > currentHighScore) {
            sharedPreferences.edit().putInt("highScore", newScore).apply()
            loadHighScore()
        }
    }


}