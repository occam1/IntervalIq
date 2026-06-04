package com.rwwf.intervaliq
// TODO: Exclude Unison from default working intervals via configuration
// TODO: Save TrainingEngine state to SharedPreferences
// TODO: Keep main screen minimal; move interval, root, and speech/playback options to config screens.
// TODO: Do we need to shorten the duration of the lower note of the interval
// TODO: Add Perfect 8th as a working interval (extended notes?)9,11,13
// TODO: play root and working note closer together?
// TODO: example song phrases for intervals - eyes of texas 6th reveille 4th etc
// TODO: running time timer
// TODO: earbud mute => pause/resume
// TODO: harmonic
// TODO: Investigate Bluetooth earbud play/pause button support.
object MusicConstants {
    const val C3 = 48
    const val C4 = 60
    const val A4 = 69
    const val B4 = 71
    const val C5 = 72
    const val A5 = 81
    const val A_SHARP_5 = 82
    const val LOWEST_NOTE = 48     // C3
    const val LOWEST_ROOT = 48     // C3
    const val HIGHEST_ROOT = 71    // B4
    const val HIGHEST_NOTE = 82    // A#5 / Bb5

    val ROOTS = (C4..B4).toList()
     val INTERVALS = listOf(
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
        EarInterval("Perfect Fourth", 5),
        EarInterval("Octave", 12)
    )
}