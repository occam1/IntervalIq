package com.rwwf.intervaliq

import org.junit.Assert.assertEquals
import org.junit.Test

class ToneGeneratorTest {
    @Test
    fun midiToFrequency_returnsA440ForMidi69() {

        val toneGenerator = ToneGenerator()

        assertEquals(
            440.0,
            toneGenerator.midiToFrequency(69),
            0.001
        )
    }
    @Test
    fun midiToFrequency_returnsMiddleCForMidi60() {

        val toneGenerator = ToneGenerator()

        assertEquals(
            261.625565,
            toneGenerator.midiToFrequency(60),
            0.001
        )
    }
    @Test
    fun midiToFrequency_returnsOctaveCForMidi72() {

        val toneGenerator = ToneGenerator()

        assertEquals(
            2 * 261.625565,
            toneGenerator.midiToFrequency(72),
            0.001
        )
    }
    @Test
    fun midiToFrequency_returnsOctaveAForMidi81() {

        val toneGenerator = ToneGenerator()

        assertEquals(
            880.0,
            toneGenerator.midiToFrequency(81),
            0.001
        )
    }
}