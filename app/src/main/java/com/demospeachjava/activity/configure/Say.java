package com.demospeachjava.activity.configure;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.*;
import android.location.Location;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.demospeachjava.R;
import com.demospeachjava.adapter.AdapterGridSayCategory;
import com.demospeachjava.data.DataGenerator;
import com.demospeachjava.model.SayCategory;
import com.demospeachjava.service.recorder;
import com.demospeachjava.utils.Tools;
import com.demospeachjava.widget.SpacingItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Say extends AppCompatActivity {
    protected LocationManager locationManager;
    private RecyclerView recyclerView;
    private AdapterGridSayCategory mAdapter;
    public TextView coordenadas;
    public TextView direccion;
    private View parent_view;
    public String menssage;
    //used to unmute the phone's audio system
    public static AudioManager mAudioManager;
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_say);

        /*datos = dbHelper.getPhraseKey();

        if (datos[0].equals("")){
            //Me notifica si tengo o no una palabra clave en mi base de datos
            Toast.makeText(this,datos[1]+" Grave una.",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,VistaMessagePalabra.class);
            startActivity(i);
        }else{
            Toast.makeText(this,datos[1],Toast.LENGTH_SHORT).show();
            txvResult.setText(datos[0]);
        }*/

        initToolbar();
        initComponent();

        coordenadas = (TextView) findViewById(R.id.txtCoordenadas);
        direccion = (TextView) findViewById(R.id.txtDireccion);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Voz de alerta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSay);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new SpacingItemDecoration(8,
                Tools.dpToPx(this, 3), true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        List<SayCategory> items = DataGenerator.getSayCategory(this);

        //Establecemos los datos y la lista
        mAdapter = new AdapterGridSayCategory(this, items);
        recyclerView.setAdapter(mAdapter);

        //Eventos de click en los items
        mAdapter.setOnItemClickListener(new AdapterGridSayCategory.OnItemClickListener() {
            @Override
            public void onItemClick(View view, SayCategory obj, int position) {
                if (obj.title.equals("INICIAR")) {
                    //Intent i = new Intent(Say.this, recorder.class);
                    //startService(i);
                    Intent sendData = new Intent(Say.this,recorder.class);
                    sendData.putExtra("coordenadas",coordenadas.getText().toString());
                    sendData.putExtra("direccion",direccion.getText().toString());
                    startService(sendData);



                } else if (obj.title.equals("APAGAR")){
                    //activate the audiomanager in order to control the audio of the system
                    mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                    //Stop the running service from here
                    stopService(new Intent(Say.this, recorder.class));
//unmutes any sound that might have been muted in the process of this application
                    mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, false );
                    mAudioManager.setStreamSolo(AudioManager.STREAM_MUSIC, false );
                }
            }
        });
    }

    public void getSpeechInput(View view) {

        /*Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Tu dispositivo no soporta Speech", Toast.LENGTH_SHORT).show();
        }*/
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txvResult.setText(result.get(0));
                }
                break;
        }
    }*/


    @Override
    public void onResume(){
        super.onResume();
        //we want to stop app because when the user goes to app, most likely they will want to stop app
        stopService(new Intent(this, recorder.class));
    }

    public void locationStart(){
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);

        coordenadas.setText("Localización agregada");
        direccion.setText("");
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    direccion.setText("Mi direccion es: \n"
                            + DirCalle.getAddressLine(0));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        Say mainActivity;

        public Say getMainActivity() {
            return mainActivity;
        }

        public void setMainActivity(Say mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            loc.getLatitude();
            loc.getLongitude();

            String Text = "Mi ubicacion actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
            coordenadas.setText(Text);
            this.mainActivity.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            coordenadas.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            coordenadas.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }
}