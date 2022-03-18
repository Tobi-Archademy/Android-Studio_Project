package com.example.android.marsphotos

import com.example.android.marsphotos.network.MarsApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MarsApiServiceTests : BaseTest() {
    private lateinit var service: MarsApiService

    @Before
    fun setUp() {
        val url = mockWebServer.url("/")

        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            )).build()
            .create(MarsApiService::class.java)

    }

    @Test
    fun api_service() {
        enqueue("mars_photos.json")
        runBlocking {
            val apiResponse = service.getPhotos()

            assertNotNull(apiResponse)
            assertTrue("The list was empty", apiResponse.isNotEmpty())
            assertEquals("The ID's did not match", apiResponse[0].id)
        }
    }
}