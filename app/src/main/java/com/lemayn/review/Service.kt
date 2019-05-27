package com.lemayn.review

import retrofit2.Call
import retrofit2.http.GET

/**
 * author: ly
 * date  : 2019/5/27 15:28
 * desc  :
 */
interface Service {

    @GET
    fun baidu(): Call<String>

}
