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

class MainActivity : AppCompatActivity() {

    private val intervals = listOf(
        EarInterval("Unison", 0),
        EarInterval("Perfect Fifth", 7),
        EarInterval("Major Second", 2),
        EarInterval("Major Sixth", 9),
        EarInterval("Major Third", 4),
        EarInterval("Major Seventh", 11),
        EarInterval("Tritone", 6),
        EarInterval("Minor Second", 1),
        EarInterval("Minor Sixth", 8),
        EarInterval("Minor Third", 3),
        EarInterval("Minor Seventh", 10),
        EarInterval("Perfect Fourth", 5)
    )
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val workingIntervalData = intervals[0]
        val contrastIntervalData = intervals[1]

       val workingInterval = findViewById<TextView>(R.id.working_interval)
       val contrastInterval = findViewById<TextView>(R.id.contrast_interval)

        workingInterval.text = getString(R.string.working_interval_text,
        workingIntervalData )
        contrastInterval.text = getString(R.string.contrast_interval_text,
            contrastIntervalData )
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.US
                tts.speak(
                    "Perfect Fifth",
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    "interval"
                )
            }
        }

        val toneGenerator = ToneGenerator()
        toneGenerator.playMelodicInterval(   MusicConstants.C4,
            EarInterval(
                "Perfect Fifth",
                7)
        )

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}