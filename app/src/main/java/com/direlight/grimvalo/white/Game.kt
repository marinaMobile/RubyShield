package com.direlight.grimvalo.white

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import com.direlight.grimvalo.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class Game : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        var counter: Int = 0

        bug.setOnClickListener{
            counter++
        }

        val running : TextView = findViewById(R.id.running)


        val s : Long = "30".toLong() * 1000

        object : CountDownTimer( s , 1000) {

            override fun onTick(millisUntilFinished: Long) {
                running.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                MaterialAlertDialogBuilder(this@Game)
                    .setTitle("Time's over!")
                    .setMessage("Your score: $counter")
                    .setCancelable(false)
                    .setPositiveButton("Play again"){dialog, _ ->
                        val preferences = getSharedPreferences("PREF", 0)
                        val editor = preferences.edit()
                        editor.apply()
                        startActivity(Intent(applicationContext, Game::class.java))
                        finish()
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
        }.start()




    Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    val rand = Random()
                    val dx: Float = rand.nextFloat() * (ll_wasp.width-bug.width)
                    val dy: Float = rand.nextFloat() * (ll_wasp.height-bug.height)
                    bug.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(300)
                        .start()
                }
            }
        }, 0, 300)


    }
}