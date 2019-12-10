package com.lemayn.review

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val i = '_'.toInt() shl 24 or ('N'.toInt() shl 16) or ('T'.toInt() shl 8) or 'F'.toInt()
        println(i)
    }

}
