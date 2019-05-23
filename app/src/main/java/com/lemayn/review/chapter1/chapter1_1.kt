package com.lemayn.review.chapter1

import org.junit.Test

/**
 * author: ly
 * date  : 2019/4/24 23:32
 * desc  :
 */
class chapter1_1 {

    @Test
    fun Test() {
        var a = 1
        // 模板中的简单名称：
        val s1 = "a is $a"
        println(s1)
        a = 2
        // 模板中的任意表达式：
        println("${s1.replace("is", "was")}, but now is $a")

        println("max of 0 and 42 is ${maxOf(0, 42)}")
    }

    @Test
    fun rangeTest() {
        for (x in 1..10 step 2) {
            print(x)
        }
        println()
        for (x in 9 downTo 0 step 3) {
            print(x)
        }
    }


}
