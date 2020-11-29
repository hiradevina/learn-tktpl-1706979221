package id.ac.ui.cs.mobileprogramming.hira.helloworld


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal object APIClient {
    fun getRetrofit() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl("https://908b7ef34beb.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getService() = getRetrofit().create(ApiEndpoint::class.java)
}
