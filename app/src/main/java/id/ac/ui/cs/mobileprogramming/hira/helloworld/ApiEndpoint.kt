package id.ac.ui.cs.mobileprogramming.hira.helloworld

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiEndpoint {

    @POST("/wifi")
    fun addWifi(@Body wifi: WifiModel): Call<WifiModel>

}