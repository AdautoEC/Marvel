package com.k4tr1n4.core.network

import android.util.Log
import com.k4tr1n4.core.network.interceptor.HttpRequestInterceptor
import com.k4tr1n4.core.network.service.MarvelService
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import java.io.IOException
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.`is`

@ExperimentalCoroutinesApi
class MarvelServiceTest : ApiAbstract<MarvelService>() {

    private lateinit var service: MarvelService

    @Before
    fun initService(){
        service = createService(MarvelService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchMarvelListFromNetworkTest() = runTest {
        enqueueResponse("/CharacterResponse.json")
        val response = service.fetchMarvelList()
        println(response.toString())
        val responseBody = requireNotNull((response as ApiResponse.Success).data.data)

        assertThat(responseBody.offset, `is`(0))
        assertThat(responseBody.limit, `is`(20))
        assertThat(responseBody.total, `is`(1562))
        assertThat(responseBody.count, `is`(20))
        assertThat(responseBody.results[0].id, `is`(1011334))
        assertThat(responseBody.results[0].name, `is`("3-D Man"))
        assertThat(responseBody.results[0].description, `is`(""))
    }
}