package com.rwwf.intervaliq

import android.speech.tts.TextToSpeech
class SpeechManager(
    private val tts: TextToSpeech
) {

    fun speak(text: String) {
        tts.speak(
            text,
            TextToSpeech.QUEUE_FLUSH,
            null,
            "speech"
        )
    }
}