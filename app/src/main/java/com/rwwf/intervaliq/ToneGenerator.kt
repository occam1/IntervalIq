package com.rwwf.intervaliq

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlin.math.PI
import kotlin.math.sin
import kotlin.math.pow
import android.os.Handler
import android.os.Looper


class ToneGenerator {
    companion object {
        private const val SAMPLE_RATE = 44100
        private const val AMPLITUDE = 0.5
    }

    fun playNote(
        midiNote: Int,
        durationMs: Int
    ) {
        val frequency = midiToFrequency(midiNote)
        val sampleCount = SAMPLE_RATE * durationMs / 1000
        val samples = ShortArray(sampleCount)



        for (i in samples.indices) {

            val angle =
                2.0 * PI * i * frequency / SAMPLE_RATE
            val fundamental = sin(angle)
            val octave = sin(angle * 2.0) * .3

            val value = (fundamental + octave) * AMPLITUDE * envelope(i, sampleCount)
           // val value =
            //    sin(angle) *
            //            AMPLITUDE *
            //            envelope(i, sampleCount)

            samples[i] =
                (value * Short.MAX_VALUE)
                    .toInt()
                    .toShort()
        }

        val audioTrack = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setSampleRate(SAMPLE_RATE)
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                    .build()
            )
            .setBufferSizeInBytes(samples.size * 2)
            .setTransferMode(AudioTrack.MODE_STATIC)
            .build()

        audioTrack.write(samples, 0, samples.size)
        audioTrack.play()

        Handler(Looper.getMainLooper()).postDelayed({
            audioTrack.stop()
            audioTrack.release()
        }, durationMs.toLong() + 100)
    }

    internal fun midiToFrequency(
        midiNote: Int
    ): Double {
        return 440.0 * 2.0.pow(((midiNote - 69) / 12.0))
    }

    fun playMelodicInterval(
        rootMidi: Int,
        interval: EarInterval
    ) {
        playNote(rootMidi, 1000)

        Handler(Looper.getMainLooper()).postDelayed({
            playNote(
                rootMidi + interval.semitones,
                1000
            )
        }, 1200)
    }

    private fun envelope(
        sampleIndex: Int,
        sampleCount: Int
    ): Double {

        val fadeSamples = SAMPLE_RATE * 20 / 1000

        return when {
            sampleIndex < fadeSamples ->
                sampleIndex.toDouble() / fadeSamples

            sampleIndex >= sampleCount - fadeSamples ->
                (sampleCount - sampleIndex).toDouble() / fadeSamples

            else -> 1.0
        }
    }
}