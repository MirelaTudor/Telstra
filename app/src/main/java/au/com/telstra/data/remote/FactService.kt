package au.com.telstra.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface FactService {
    @GET("facts.json")
    suspend fun getFacts(): Response<FactListResponse>
}
