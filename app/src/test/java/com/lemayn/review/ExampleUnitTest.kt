package com.lemayn.review

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
        val PARAM = "[a-zA-Z][a-zA-Z0-9_-]*"
        val PARAM_URL_REGEX = Pattern.compile("\\{($PARAM)\\}")
        println("\\{($PARAM)\\}")
        val value = "www.baidu.com/aa/aaa/aa?aaa=b&&ds=12"
        val question = value.indexOf('?')
        if (question != -1 && question < value.length - 1) {
            // Ensure the query string does not have any named parameters.
            val queryParams = value.substring(question + 1)
            println(queryParams)
            // 大写字母，小写字母，数字，下划线和连字符。
            val queryParamMatcher = PARAM_URL_REGEX.matcher(queryParams)
            if (queryParamMatcher.find()) {
                println("1111")
            }
        }
        println("2222")

        val m = PARAM_URL_REGEX.matcher(value)
        val patterns = LinkedHashSet<String>()
        while (m.find()) {
            patterns.add(m.group(1))
        }
        println("---------" + patterns.size)
    }
}
