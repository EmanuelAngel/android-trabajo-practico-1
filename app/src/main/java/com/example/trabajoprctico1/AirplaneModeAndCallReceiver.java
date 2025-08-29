package com.example.trabajoprctico1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class AirplaneModeAndCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        var isAirplaneModeOn = intent.getBooleanExtra("state", false);

        Toast.makeText(
            context,
            isAirplaneModeOn
                ? "Modo avión activado"
                : "Modo avión desactivado",
            Toast.LENGTH_LONG
        ).show();

        if (isAirplaneModeOn) {
            var callIntent = new Intent(Intent.ACTION_CALL);

            callIntent.setData(Uri.parse("tel:2664553747"));

            context.startActivity(callIntent);
        }
    }
}