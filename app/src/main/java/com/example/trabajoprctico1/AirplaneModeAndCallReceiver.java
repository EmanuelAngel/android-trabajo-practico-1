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

            /**
             * Inicia la actividad en una nueva tarea.
             * Recomendación por parte de ChatGPT.
             * Razón: En Android, cuando lanzas un `Intent` desde un **`BroadcastReceiver`**, el
             * `context` que recibes en `onReceive()` **no es una `Activity`**, sino el contexto del
             * sistema o de la aplicación.
             * Eso significa que si intentas abrir una `Activity` sin flags, Android te lanzará una
             * excepción:
             * ```bash
             * android.util.AndroidRuntimeException:
             * Calling startActivity() from outside of an Activity context requires the
             * FLAG_ACTIVITY_NEW_TASK flag.
             * ```
             * Por eso:
             * ```java
             * intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             * ```
             * @see <a href="https://developer.android.com/reference/android/content/Intent#flags">
             *      Documentación oficial de flags de Intent</a>
             */
            callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(callIntent);
        }
    }
}