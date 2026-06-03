package com.rwwf.intervaliq

import android.os.Bundle

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.speech.tts.TextToSpeech
import androidx.core.os.postDelayed
import java.util.Locale
import android.os.Handler
import android.os.Looper
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var speechManager: SpeechManager
    private lateinit var toneGenerator: ToneGenerator
    private lateinit var trainingEngine: TrainingEngine

    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val workingInterval = findViewById<TextView>(R.id.working_interval)
        val contrastInterval = findViewById<TextView>(R.id.contrast_interval)
        val pauseButton = findViewById<Button>(R.id.pause_button)
        var isPaused = false;

            pauseButton.setOnClickListener {
            if (isPaused) {
                trainingEngine.resume()
                pauseButton.text = "Pause"
                isPaused = false
            } else {
                trainingEngine.pause()
                pauseButton.text = "Resume"
                isPaused = true
            }
        }

        toneGenerator = ToneGenerator()

        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.US

                speechManager = SpeechManager(tts)


                trainingEngine = TrainingEngine(
                    toneGenerator,
                    speechManager
                ) { working, contrast ->

                    workingInterval.text = getString(
                        R.string.working_interval_text,
                        working.name
                    )

                    contrastInterval.text = getString(
                        R.string.contrast_interval_text,
                        contrast.name
                    )
                }
                speechManager.speak("Lets get this party started")
                Handler(Looper.getMainLooper()).postDelayed({
                    trainingEngine.start()
                }, 3000)
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}