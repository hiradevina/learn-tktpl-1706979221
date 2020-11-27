package id.ac.ui.cs.mobileprogramming.hira.helloworld

import retrofit2.Call
import retrofit2.http.POST

interface ApiEndpoint {

    @POST("api/wifi")
    fun addWifi(wifi: WifiModel): Call<WifiModel>

}