package id.ac.ui.cs.mobileprogramming.hira.helloworld

import com.google.gson.annotations.SerializedName

class WifiModel {
    @SerializedName("access_point")
    val accessPoint: String

    constructor(accessPoint: String) {
        this.accessPoint = accessPoint
    }
}