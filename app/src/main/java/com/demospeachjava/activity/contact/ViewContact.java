package com.demospeachjava.activity.contact;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.demospeachjava.R;
import com.demospeachjava.data.DataGenerator;
import com.demospeachjava.helper.ContactHelper;
import com.demospeachjava.model.People;
import com.demospeachjava.utils.Tools;

import java.io.FileNotFoundException;

public class ViewContact extends AppCompatActivity {

    private long id;
    private People people;
    private ContactHelper dbHelper;
    private ImageView fotoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        Bundle extras = getIntent().getExtras();

        dbHelper = new ContactHelper(this);

        try{
            id = extras.getLong("CONTACT_ID",-1);
        }catch (Exception e){
            e.printStackTrace();
        }

        people = dbHelper.getPeople(id);

        initToolbar();
        actualizarVistas();
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(people.getFirsname());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }



    public void actualizarVistas(){
        fotoPerfil = (ImageView) findViewById(R.id.image);

        TextView firsname = (TextView)findViewById(R.id.firsname);
        firsname.setText(people.getFirsname());

        TextView lastname = (TextView)findViewById(R.id.lastname);
        if (people.getLastname() == "") {
            findViewById(R.id.lastname).setVisibility(View.GONE);
        } else {
            lastname.setText(people.getLastname());
        }

        TextView email = (TextView)findViewById(R.id.peopleEmail);
        email.setText(people.getEmail());

        TextView telefono = (TextView)findViewById(R.id.peoplePhonePrivate);
        telefono.setText(people.getTelephone_private());

        TextView celular = (TextView)findViewById(R.id.peoplePhoneCell);
        celular.setText(people.getCell_phone());

        TextView direccion = (TextView)findViewById(R.id.peopleAddress);
        direccion.setText(people.getAddress());

        if (people.getState().equals("ninguno")){
            findViewById(R.id.lyt_view_state).setVisibility(View.GONE);
        }else {
            TextView state = (TextView) findViewById(R.id.peopleState);
            state.setText(people.getState());
        }

        if (people.getFecha().equals("")){
            findViewById(R.id.lyt_view_peopleFecha).setVisibility(View.GONE);
        }else{
            TextView fecha = (TextView) findViewById(R.id.peopleFecha);
            fecha.setText(people.getFecha());
        }

        if (people.getCi().equals("")){
            findViewById(R.id.lyt_view_ci).setVisibility(View.GONE);
        } else {
            TextView ci = (TextView)findViewById(R.id.peopleCi);
            ci.setText(people.getCi());
        }

        TextView type = (TextView) findViewById(R.id.peopleTypeRegister);
        type.setText(people.getType_register());


        if (people.getGender().equals("ninguno")){
            findViewById(R.id.lyt_view_gender).setVisibility(View.GONE);
        } else {
            TextView gender = (TextView)findViewById(R.id.peopleGender);
            gender.setText(people.getGender());
        }

        ponerFoto(fotoPerfil, people.getAvatar());
    }


    public void llamadaTelefono (View v){
        startActivity(new Intent (Intent.ACTION_DIAL, Uri.parse("tel:"+ people.getTelephone_private())));
    }
    public void llamadaCelular (View v){
        startActivity(new Intent (Intent.ACTION_DIAL, Uri.parse("cel:"+ people.getCell_phone())));
    }

    public void mandarCorreo(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"asunto");
        intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {people.getEmail()});
    }

    public void verMapa(View view){
        Uri uri = null;
        uri = uri.parse("geo:0,0?q=" + people.getAddress());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    protected void ponerFoto (ImageView imagen, String uri){
        if (uri != null){
            fotoPerfil.setImageBitmap(reduceBitmap(this, uri, 48, 48));
        } else {
            imagen.setImageBitmap(null);
        }
    }

    private Bitmap reduceBitmap(Context contexto, String uri, int maxAncho, int maxAlto) {

        try {
            final BitmapFactory.Options opciones = new BitmapFactory.Options();
            opciones.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(contexto.getContentResolver().openInputStream(Uri.parse(uri)),
                    null, opciones);
            opciones.inSampleSize = (int) Math.max(
                    Math.ceil(opciones.outWidth / maxAncho),
                    Math.ceil(opciones.outHeight / maxAlto));
            opciones.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(contexto.getContentResolver().openInputStream(Uri.parse(uri)),
                    null,opciones);
        } catch (FileNotFoundException e) {
            Toast.makeText(contexto, "Fichero/recurso no encontrado", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return null;
        }
    }

}
