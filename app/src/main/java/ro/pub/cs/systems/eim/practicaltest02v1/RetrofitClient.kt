package ro.pub.cs.systems.eim.practicaltest02v1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object RetrofitClient {
    private const val BASE_URL = "http://localhost:8080/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

// API-ul pentru Retrofit
interface ApiService {
    @GET("data")
    suspend fun getData(@Query("key") key: String): Map<String, String>

    @POST("data")
    suspend fun postData(@Body request: DataRequest): Map<String, String>

    @DELETE("data")
    suspend fun deleteData(@Query("key") key: String): Map<String, String>
}

// Definiția clasei DataRequest
data class DataRequest(val key: String, val value: String)
