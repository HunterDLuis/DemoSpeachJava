package com.demospeachjava.activity.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.demospeachjava.R;
import com.demospeachjava.helper.ContactHelper;
import com.demospeachjava.model.People;
import com.demospeachjava.utils.Tools;

public class CreateContact extends AppCompatActivity {

    private AppCompatEditText createAvatar;
    private AppCompatEditText createFirsname;
    private AppCompatEditText createLastname;
    private AppCompatEditText createEmail;
    private AppCompatEditText createPhone;
    private AppCompatEditText createCell;
    private AppCompatEditText createAddress;
    private AppCompatSpinner spn_state;
    private AppCompatSpinner spn_type;
    private RadioGroup createPriority;
    private AppCompatEditText createCi;
    private AppCompatSpinner spn_gender;
    private AppCompatEditText createBirth;

    private View parent_view;

    private ContactHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        createFirsname = (AppCompatEditText) findViewById(R.id.create_firsname);
        createLastname = (AppCompatEditText) findViewById(R.id.create_lastname);
        createEmail = (AppCompatEditText) findViewById(R.id.create_email);
        createPhone = (AppCompatEditText) findViewById(R.id.create_phone);
        createCell = (AppCompatEditText) findViewById(R.id.create_cell);
        createAddress = (AppCompatEditText) findViewById(R.id.create_address);
        spn_state = (AppCompatSpinner) findViewById(R.id.spn_state);
        createBirth = (AppCompatEditText) findViewById(R.id.etPlannedDate);
        createCi = (AppCompatEditText) findViewById(R.id.create_ci);
        spn_type = (AppCompatSpinner) findViewById(R.id.spn_type);
        createPriority = (RadioGroup) findViewById(R.id.priority);
        spn_gender = (AppCompatSpinner) findViewById(R.id.spn_gender);

        //DataPicker
        //createBirth.setOnClickListener(this);


        String[] list_state = getResources().getStringArray(R.array.state);
        ArrayAdapter<String> array = new ArrayAdapter<>(this, R.layout.simple_spinner_item, list_state);
        array.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spn_state.setAdapter(array);
        spn_state.setSelection(0);

        String[] list_type = getResources().getStringArray(R.array.type);
        ArrayAdapter<String> array_type = new ArrayAdapter<>(this, R.layout.simple_spinner_item, list_type);
        array_type.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spn_type.setAdapter(array_type);
        spn_type.setSelection(0);

        String[] list_gender = getResources().getStringArray(R.array.gender);
        ArrayAdapter<String> array_gender = new ArrayAdapter<>(this, R.layout.simple_spinner_item, list_gender);
        array_gender.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spn_gender.setAdapter(array_gender);
        spn_gender.setSelection(0);

        createPriority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.radio_si){
                    String[] radio_priority = getResources().getStringArray(R.array.priority);
                    spn_type.setSelection(Integer.parseInt(radio_priority[0]));
                    Toast.makeText(CreateContact.this, "Este contacto resivira tus mensajes de ayuda "+ spn_type , Toast.LENGTH_SHORT).show();
                }else if (checkedId == R.id.radio_no){
                    Toast.makeText(CreateContact.this, "Este contacto no resivira tus mensajes de ayuda", Toast.LENGTH_SHORT).show();
                }

            }

        });


        parent_view = findViewById(android.R.id.content);

        initToolbar();

    }
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Crear nuevo usuario.");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_people, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                String firsname = createFirsname.getText().toString().trim();
                String lastname = createLastname.getText().toString().trim();
                String email = createEmail.getText().toString().trim();
                String phone = createPhone.getText().toString().trim();
                String cell = createCell.getText().toString().trim();
                String address = createAddress.getText().toString().trim();
                String state = spn_state.getSelectedItem().toString();
                String birth = createBirth.getText().toString().trim();
                String ci = createCi.getText().toString().trim();
                String type = spn_type.getSelectedItem().toString();
                int is_priority = createPriority.getCheckedRadioButtonId();
                String gender = spn_gender.getSelectedItem().toString();
                dbHelper = new ContactHelper(this);

                if (firsname.isEmpty()){
                    Snackbar.make(parent_view, "Ingresar nombres del usuario", Snackbar.LENGTH_SHORT).show();
                                  }
                if (((AppCompatEditText) findViewById(R.id.create_email)).getText().toString().trim().equals("")) {
                    Snackbar.make(parent_view, "Ingresar el email del usuario", Snackbar.LENGTH_SHORT).show();
                }
                if (((AppCompatEditText) findViewById(R.id.create_address)).getText().toString().trim().equals("")) {
                    Snackbar.make(parent_view, "Ingresar la direccion del usuario", Snackbar.LENGTH_SHORT).show();
                }
                if (((AppCompatEditText) findViewById(R.id.create_cell)).getText().toString().trim().equals("")) {
                    Snackbar.make(parent_view, "Ingresar el numero del celular del usuario", Snackbar.LENGTH_SHORT).show();
                }else if(((AppCompatEditText) findViewById(R.id.create_cell)).getText().toString().trim().length()<9){
                    Snackbar.make(parent_view, "Revizar el numero ingresado tiene mas de 8 caracteres", Snackbar.LENGTH_SHORT).show();
                }

                People people = new People(firsname,lastname,email,phone,cell,address,state,birth,ci,type, is_priority,gender);
                dbHelper.saveNewPeople(people);
                goBackHome();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goBackHome(){
        startActivity(new Intent(CreateContact.this, Contact.class));
    }
}
