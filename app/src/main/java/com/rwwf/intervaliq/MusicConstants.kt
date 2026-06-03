package com.rwwf.intervaliq

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
        EarInterval("Perfect Fourth", 5)
    )
}