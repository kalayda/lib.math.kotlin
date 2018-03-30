package com.itgrx.lib.math

/**
 * Created by Алексей Калайда on 20.12.2017.
 * https://github.com/MasayukiSuda/EasingInterpolator
 */
enum class Ease {
    LINEAR,
    QUAD_IN,
    QUAD_OUT,
    QUAD_IN_OUT,
    CUBIC_IN,
    CUBIC_OUT,
    CUBIC_IN_OUT,
    QUART_IN,
    QUART_OUT,
    QUART_IN_OUT,
    QUINT_IN,
    QUINT_OUT,
    QUINT_IN_OUT,
    SINE_IN,
    SINE_OUT,
    SINE_IN_OUT,
    BACK_IN,
    BACK_OUT,
    BACK_IN_OUT,
    CIRC_IN,
    CIRC_OUT,
    CIRC_IN_OUT,
    BOUNCE_IN,
    BOUNCE_OUT,
    BOUNCE_IN_OUT,
    ELASTIC_IN,
    ELASTIC_OUT,
    ELASTIC_IN_OUT;

    operator fun get(_elapsedTimeRate: Float): Float {
        var elapsedTimeRate = _elapsedTimeRate
        when (this) {
            Ease.LINEAR -> return elapsedTimeRate
            Ease.QUAD_IN -> return getPowIn(elapsedTimeRate, 2.0)
            Ease.QUAD_OUT -> return getPowOut(elapsedTimeRate, 2.0)
            Ease.QUAD_IN_OUT -> return getPowInOut(elapsedTimeRate, 2.0)
            Ease.CUBIC_IN -> return getPowIn(elapsedTimeRate, 3.0)
            Ease.CUBIC_OUT -> return getPowOut(elapsedTimeRate, 3.0)
            Ease.CUBIC_IN_OUT -> return getPowInOut(elapsedTimeRate, 3.0)
            Ease.QUART_IN -> return getPowIn(elapsedTimeRate, 4.0)
            Ease.QUART_OUT -> return getPowOut(elapsedTimeRate, 4.0)
            Ease.QUART_IN_OUT -> return getPowInOut(elapsedTimeRate, 4.0)
            Ease.QUINT_IN -> return getPowIn(elapsedTimeRate, 5.0)
            Ease.QUINT_OUT -> return getPowOut(elapsedTimeRate, 5.0)
            Ease.QUINT_IN_OUT -> return getPowInOut(elapsedTimeRate, 5.0)
            Ease.SINE_IN -> return (1f - Math.cos(elapsedTimeRate * Math.PI / 2f)).toFloat()
            Ease.SINE_OUT -> return Math.sin(elapsedTimeRate * Math.PI / 2f).toFloat()
            Ease.SINE_IN_OUT -> return (-0.5f * (Math.cos(Math.PI * elapsedTimeRate) - 1f)).toFloat()
            Ease.BACK_IN -> return (elapsedTimeRate.toDouble() * elapsedTimeRate.toDouble() * ((1.7 + 1f) * elapsedTimeRate - 1.7)).toFloat()
            Ease.BACK_OUT -> return ((--elapsedTimeRate).toDouble() * elapsedTimeRate.toDouble() * ((1.7 + 1f) * elapsedTimeRate + 1.7) + 1f).toFloat()
            Ease.BACK_IN_OUT -> return getBackInOut(elapsedTimeRate, 1.7f)
            Ease.CIRC_IN -> return (-(Math.sqrt((1f - elapsedTimeRate * elapsedTimeRate).toDouble()) - 1)).toFloat()
            Ease.CIRC_OUT -> return Math.sqrt((1f - --elapsedTimeRate * elapsedTimeRate).toDouble()).toFloat()
            Ease.CIRC_IN_OUT -> {
                elapsedTimeRate *= 2f
                return if (elapsedTimeRate < 1f) {
                    (-0.5f * (Math.sqrt((1f - elapsedTimeRate * elapsedTimeRate).toDouble()) - 1f)).toFloat()
                } else {
                    elapsedTimeRate -= 2f
                    (0.5f * (Math.sqrt((1f - elapsedTimeRate * elapsedTimeRate).toDouble()) + 1f)).toFloat()
                }
            }
            Ease.BOUNCE_IN -> return getBounceIn(elapsedTimeRate)
            Ease.BOUNCE_OUT -> return getBounceOut(elapsedTimeRate)
            Ease.BOUNCE_IN_OUT -> {
                return if (elapsedTimeRate < 0.5f) {
                    getBounceIn(elapsedTimeRate * 2f) * 0.5f
                } else getBounceOut(elapsedTimeRate * 2f - 1f) * 0.5f + 0.5f
            }
            Ease.ELASTIC_IN -> return getElasticIn(elapsedTimeRate, 1.0, 0.3)

            Ease.ELASTIC_OUT -> return getElasticOut(elapsedTimeRate, 1.0, 0.3)

            Ease.ELASTIC_IN_OUT -> return getElasticInOut(elapsedTimeRate, 1.0, 0.45)

            else -> return elapsedTimeRate
        }

    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param pow             pow The exponent to use (ex. 3 would return a cubic ease).
     * @return easedValue
     */
    private fun getPowIn(elapsedTimeRate: Float, pow: Double): Float {
        return Math.pow(elapsedTimeRate.toDouble(), pow).toFloat()
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param pow             pow The exponent to use (ex. 3 would return a cubic ease).
     * @return easedValue
     */
    private fun getPowOut(elapsedTimeRate: Float, pow: Double): Float {
        return (1.toFloat() - Math.pow((1 - elapsedTimeRate).toDouble(), pow)).toFloat()
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param pow             pow The exponent to use (ex. 3 would return a cubic ease).
     * @return easedValue
     */
    private fun getPowInOut(elapsedTimeRate: Float, pow: Double): Float {
        val etr = elapsedTimeRate * 2f
        return if (etr < 1) {
            (0.5 * Math.pow(etr.toDouble(), pow)).toFloat()
        } else (1 - 0.5 * Math.abs(Math.pow((2 - etr).toDouble(), pow))).toFloat()

    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param amount          amount The strength of the ease.
     * @return easedValue
     */
    private fun getBackInOut(elapsedTimeRate: Float, amount: Float): Float {
        val amt = amount * 1.525f
        var etr = elapsedTimeRate * 2f
        return if (etr < 1) {
            (0.5 * (etr * etr * ((amt + 1) * etr - amt))).toFloat()
        } else {
            etr -= 2f
            (0.5 * (etr * etr * ((amt + 1) * etr + amt) + 2)).toFloat()
        }
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @return easedValue
     */
    private fun getBounceIn(elapsedTimeRate: Float): Float {
        return 1f - getBounceOut(1f - elapsedTimeRate)
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @return easedValue
     */
    private fun getBounceOut(elapsedTimeRate: Float): Float {
        var etr = elapsedTimeRate
        return if (etr < 1 / 2.75) {
            (7.5625 * etr.toDouble() * etr.toDouble()).toFloat()
        } else if (etr < 2 / 2.75) {
            etr -= (1.5 / 2.75).toFloat()
            (7.5625 * etr.toDouble() * etr.toDouble() + 0.75).toFloat()
        } else if (etr < 2.5 / 2.75) {
            etr -= (2.25 / 2.75).toFloat()
            (7.5625 * etr.toDouble() * etr.toDouble() + 0.9375).toFloat()
        } else {
            etr -= (2.625 / 2.75).toFloat()
            (7.5625 * etr.toDouble() * etr.toDouble() + 0.984375).toFloat()
        }
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param amplitude       Amplitude of easing
     * @param period          Animation of period
     * @return easedValue
     */
    private fun getElasticIn(elapsedTimeRate: Float, amplitude: Double, period: Double): Float {
        var etr = elapsedTimeRate
        if (etr == 0f || etr == 1f) return etr
        val pi2 = Math.PI * 2
        val s = period / pi2 * Math.asin(1 / amplitude)
        etr -= 1f
        return (-(amplitude * Math.pow(2.0, (10f * etr).toDouble()) * Math.sin((etr - s) * pi2 / period))).toFloat()
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param amplitude       Amplitude of easing
     * @param period          Animation of period
     * @return easedValue
     */
    private fun getElasticOut(elapsedTimeRate: Float, amplitude: Double, period: Double): Float {
        if (elapsedTimeRate == 0f || elapsedTimeRate == 1f) return elapsedTimeRate

        val pi2 = Math.PI * 2
        val s = period / pi2 * Math.asin(1 / amplitude)
        return (amplitude * Math.pow(2.0, (-10 * elapsedTimeRate).toDouble()) * Math.sin((elapsedTimeRate - s) * pi2 / period) + 1).toFloat()
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param amplitude       Amplitude of easing
     * @param period          Animation of period
     * @return easedValue
     */
    private fun getElasticInOut(elapsedTimeRate: Float, amplitude: Double, period: Double): Float {
        val pi2 = Math.PI * 2

        val s = period / pi2 * Math.asin(1 / amplitude)
        var etr = elapsedTimeRate * 2f
        return if (etr < 1) {
            etr -= 1f
            (-0.5f * (amplitude * Math.pow(2.0, (10 * etr).toDouble()) * Math.sin((etr - s) * pi2 / period))).toFloat()
        } else {
            etr -= 1f
            (amplitude * Math.pow(2.0, (-10 * etr).toDouble()) * Math.sin((etr - s) * pi2 / period) * 0.5 + 1).toFloat()
        }

    }
}