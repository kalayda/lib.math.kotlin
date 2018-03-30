package com.itgrx.lib.math

/**
 * Created by Алексей Калайда on 20.12.2017.
 */
class TimeEasing(
        private val ease: Ease = Ease.CUBIC_IN_OUT,
        duration: Long = 5000L,
        x: Float = 0f,
        var t: Long = System.currentTimeMillis()
) {
    var duration: Long = duration
        set(value) {
            x = x
            field = value
        }

    var x: Float = x
        get() {
            val ct = System.currentTimeMillis()
            return if (duration == 0L) field else {
                val d = ct - t
                if (duration < 0) {
                    val dv = -duration * field
                    if (d > dv)
                        0f
                    else
                        field + d.toFloat() / duration
                } else {
                    val dv = duration * (1 - field)
                    if (d > dv)
                        1f
                    else
                        field + d.toFloat() / duration
                }
            }
        }
        set(value) {
            field = when {
                value < 0 -> 0f
                value > 1 -> 1f
                else -> value
            }
            t = System.currentTimeMillis()
        }

    operator fun invoke(): Float = ease[x]

}