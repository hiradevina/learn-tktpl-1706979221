package id.ac.ui.cs.mobileprogramming.hira.helloworld

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import id.ac.ui.cs.mobileprogramming.hira.helloworld.APIClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val PERMISSION_REQUEST_CODE = 111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        enableLocation()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun enableLocation() {
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
                    ) && (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {
            checkWifi()
        } else {
            requestPermissions(this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun checkWifi() {
        val mWifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        // receiver when finding access point
        val wifiScanReceiver = object : BroadcastReceiver() {

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onReceive(context: Context, intent: Intent) {
                val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                if (success) {
                    Log.d("MainActivity", "results ${mWifiManager.scanResults}")
                    if (mWifiManager.scanResults.isNotEmpty()) {
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
                    }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty()) {
                checkWifi()
            }
        }
    }

}
