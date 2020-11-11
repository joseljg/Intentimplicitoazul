package es.joseljg.intentimplicitoazul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edt_url = null;
    EditText edt_localizacion = null;
    EditText edt_texto = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_url = (EditText) findViewById(R.id.edt_url);
        edt_localizacion = (EditText) findViewById(R.id.edt_localizacion);
        edt_texto = (EditText) findViewById(R.id.edt_texto);
    }

    public void enviar_url(View view) {
         String direccionweb = String.valueOf(edt_url.getText());
            Uri url = Uri.parse(direccionweb);
        Log.i("direccion_url", "url -> " + url.getQuery());
                 Intent intent = new Intent(Intent.ACTION_VIEW, url);
                 if (intent.resolveActivity(getPackageManager()) != null) {
                     startActivity(intent);
                 } else {
                     Log.e("errores", "no puedo abrir con ninguna aplicacion el intent implicito");
                     edt_url.setError("escribe bien la url");
                 }
             }

    public void enviar_localizacion(View view) {
        String localizacionweb = String.valueOf(edt_localizacion.getText());
        Uri url = Uri.parse("geo:0,0?q="+ localizacionweb);
        Intent intent = new Intent(Intent.ACTION_VIEW, url);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.e("errores", "no puedo abrir con ninguna aplicacion el intent implicito");
            edt_url.setError("escribe bien la url");
        }
    }

    public void enviar_texto(View view) {
        String texto = String.valueOf(edt_texto.getText());
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.escribe_tu_texto)
                .setText(texto)
                .startChooser();

    }
}

