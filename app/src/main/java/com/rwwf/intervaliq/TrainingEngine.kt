package com.rwwf.intervaliq
import android.os.Handler
import android.os.Looper
import com.rwwf.intervaliq.MusicConstants.ROOTS

class TrainingEngine(
    private val toneGenerator: ToneGenerator,
    private val speechManager: SpeechManager,
    private val onIntervalsChanged: (EarInterval, EarInterval) -> Unit
) {
    private val handler = Handler(Looper.getMainLooper())

    private val roots = listOf(MusicConstants.C4)


    private var isMelodic = true
    private var rootIndex = 0
    private var workingIndex = 1
    private var contrastIndex = 0
    private var isRunning = false



    fun start() {
        isRunning = true
        playCurrentTrainingUnit()
    }

    fun stop() {
        isRunning = false
        handler.removeCallbacksAndMessages(null)
    }

    private fun playCurrentTrainingUnit() {
        if (!isRunning) return

        val root = ROOTS[rootIndex]
        val working = MusicConstants.INTERVALS[workingIndex]
        val contrast = MusicConstants.INTERVALS[contrastIndex]
        onIntervalsChanged(working, contrast)
        speechManager.speak(working.name)


        handler.postDelayed({
            playInterval(root, working)
        },1500)

        handler.postDelayed({
            playInterval(root, working)
        }, 4200)

        handler.postDelayed({
            toneGenerator.playMelodicInterval(root, working)
        }, 6900)

        handler.postDelayed({
            toneGenerator.playMelodicInterval(root, contrast)
        }, 9600)
        handler.postDelayed({
            speechManager.speak(contrast.name)
        }, 12100)

        handler.postDelayed({
            advanceIndexes()
            playCurrentTrainingUnit()
        }, 14000)
    }

    private fun advanceIndexes() {
        contrastIndex++

        if (contrastIndex >= MusicConstants.INTERVALS.size) {
            contrastIndex = 0
            workingIndex++
        }

        if (workingIndex >= MusicConstants.INTERVALS.size) {
            workingIndex = 0
            rootIndex++
        }

        if (rootIndex >= roots.size) {
            rootIndex = 0
        }
    }


    private fun playInterval(
        root: Int,
        interval: EarInterval
    ) {
        if (isMelodic) {
            toneGenerator.playMelodicInterval(root, interval)
        } else {
            toneGenerator.playHarmonicInterval(root, interval)
        }
    }


    fun pause() {
        isRunning = false
        handler.removeCallbacksAndMessages(null)
    }

    fun resume() {
        if (!isRunning) {
            isRunning = true
            playCurrentTrainingUnit()
        }
    }
}