package com.joaquinalvidrez.tecgurusfirstapp.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ChargerReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        when (p1?.action) {
            Intent.ACTION_POWER_CONNECTED ->
                Toast.makeText(p0, "Charger has been connected", Toast.LENGTH_SHORT).show()
            Intent.ACTION_POWER_DISCONNECTED ->
                Toast.makeText(p0, "Charger has been disconnected", Toast.LENGTH_SHORT).show()
        }

    }
}