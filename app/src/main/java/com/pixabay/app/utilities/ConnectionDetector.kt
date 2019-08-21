package com.pixabay.app.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build

//Connection Detector class to verify the internet is available or not
class ConnectionDetector(private val _context: Context?) {

    /**
     * Checking for all possible internet providers
     */
    fun isConnectingToInternet(): Boolean {
        if (_context != null) {
            val manager = _context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val allNetworks = manager?.allNetworks?.let { it } ?: return false
                allNetworks.forEach { network ->
                    val info = manager.getNetworkInfo(network)
                    if (info.state == NetworkInfo.State.CONNECTED) return true
                }
            } else {
                val allNetworkInfo = manager?.allNetworkInfo?.let { it } ?: return false
                allNetworkInfo.forEach { info ->
                    if (info.state == NetworkInfo.State.CONNECTED) return true
                }
            }
        }
        return false
    }
}