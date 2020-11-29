# Lab Report 5: Scan Near Access Point
Get List of access points near device and send the data to outside service (with HTTP)

[Repository](https://github.com/hiradevina/learn-tktpl-1706979221/tree/lab-5) 

![Example](https://lh3.googleusercontent.com/MGS6QK3jpNe727VL7VVFbvuPbVy2hfhe_C3aiZBcIUBK3XXAA8cdy4_TXeXFm3wxpRzoR7UyvORvCUelGm8m6KmcDoraVByIYTU2TMtmJ67CXOQuxpyrVz14jZpOmHt8iXfLgPtjuMwY0-0E1jIr6D7_ru105e3qc15wTDVx3QKm-LOS2cKVf8SL3aRWhBsiHcK9yOg3eoYqU-t1u6lPHHf7Bs-BxdmHtOPEgJSGF4Udr9N9GyU9HcYCpg4ZJCSRUWY_Zj7w-kXINqJ_sccIxbGucCuSGKfKRPDLwKBRytOPGAr3JfFjknouWZN6Jcp_kHIrWGeCqg6u3Wv1gaLveQhrOZ7vaLBPfMYcqQnn-z9t-0Z5A4tP0xp4FSqfZTEWv7DvGRYONyb-wL8lWgHTYVCleg3giT4wFXAvfKDwbcOYdwT5WOV5Bkux-WYsT7ij9SBi1AY3Wgk4Kyi8UESKdt9BDhvWO7wyK86fELSujzh79FQxdhycpxb2cUntdz24drdAkRI-kmCvqvlAdhExuRVGg7ljDlQTj5AoofkCk05f4BKZPzMfDbRlC2q-Js-UsVy7ui_JRPZeXFKysbRPwvPP-0hhD5U-c8ORLER91SnZbFpllpf7G3S14eeEZMDpY5dp1acg_NVmLXDtJAvrDYPr9WnC405Dn8yZRcchTcMuR2HmM8fqKQuiZhCG=w425-h943-no?authuser=0)
## Get List of Wifi Access Points
Add Location and Wifi permission for getting the access point
```
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```
Get List of Access Point with WifiManager.startScan() and add a BroadcastReceiver if new access point found. If Receiver gets new data, do something (in this case, send the data to outside service)
```
private fun checkWifi() {
    val mWifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    // receiver when finding access point
    val wifiScanReceiver = object : BroadcastReceiver() {

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceive(context: Context, intent: Intent) {
            val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success) {
                // do something
            } else {
                Log.d("MainActivity", "failed to get access points")
            }
        }
    }

    // register wifi access point receiver
    val intentFilter = IntentFilter()
    intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
    applicationContext.registerReceiver(wifiScanReceiver, intentFilter)
    val success = mWifiManager.startScan()
}
```

## Send Data to Outside Service
Add Internet permission for sending data to outside service
```
<uses-permission android:name="android.permission.INTERNET" />
```
Send Data to Outside Server with HTTP (I use json-server and ngrok to implement mock server).

Build APIClient with Retrofit
```
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
```
and endpoints
```
interface ApiEndpoint {

    @POST("/wifi")
    fun addWifi(@Body wifi: WifiModel): Call<WifiModel>

}
```
After BroadcastReceiver receives new access point, send data to server with retrofit
```
mWifiManager.scanResults.forEach {
    Log.d("MainActivity", it.SSID)
    // send SSID
    APIClient.getService().addWifi(WifiModel(it.SSID)).enqueue(object :
        Callback<WifiModel> {
        override fun onFailure(
            call: retrofit2.Call<WifiModel>?,
            t: Throwable?
        ) {
            Toast.makeText(context, "Failed Wifi Data", Toast.LENGTH_SHORT)
                .show()
        }

        override fun onResponse(
            call: retrofit2.Call<WifiModel>?,
            response: Response<WifiModel>?
        ) {
            Toast.makeText(context, "Sent Wifi Data", Toast.LENGTH_SHORT)
                .show()
        }
    })
}
```
![SendData](https://lh3.googleusercontent.com/pLM4rS2qpp5cXtQj7JDbCqk39I1rJ-Et7X9z5Zn4b1k3KEvERaFihG3eg3po5JT66Qi8ixoGOXfTIn33eQvtNxM6ASX8ynFv69SSB8x8Xnpzpu01Ro7VW6Z1R9tCJCrWKGCjbMSzk0jCv4ZIAqnvhdYg8Rw3I9r-hzIUL7WVRyu_WocOS8zqIhZB6CYZ6e1vSRQgBk6p2nDfovYGD1hyhNMj0Ydl8PH_FP3ZzerzD7xFdyzSLRMuCduhn3XE9mrTZHRF49ygyV4BNWNPSKaegE6Yv8sIFqFe34JfgJH4P7hgu-cjAPv2g1TZ-dLwJWsTHzC_C-MhRSQjnwQwMQIVV3mSDXqQT_uUoxVkm9for6TFBaZWta5RPspCsX0kmPFpyKfeG99_4QDnyBrvwEuqLr1YashpP7TiF7bCNtex08-Q0VTOMbKknNOGO47VhgfMRoWO5RYMW1mCrZSjd825txPr3MLQ5HFvtPsCObHIE5Ypz3B8OA-F9nzqvqX3gcXXk5VBon0t2_tc3NmbgyGEbhjLbDrwpRtUK2gkRoFyCpO1l-AEEoiC66txfeAn_z3R4bt52uy3ws6b1jmpVU--LZvjSoUl0TWhZQk0psc48HMsEO_wW3J5kIgw3ALf0j7GRJs_ZdgLqx0bNIn9_uxUtM9rJ58VamwyZ1ey0Lu8pKiszw96z_Fi6f1ReF6V=w699-h684-no?authuser=0)