package com.demospeachjava.activity.contact;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import com.demospeachjava.R;
import com.demospeachjava.helper.ContactHelper;
import com.demospeachjava.model.People;
import com.demospeachjava.utils.Tools;

public class EditContact extends AppCompatActivity {
    private RadioGroup editPriority;
    private AppCompatEditText editFirsname;
    private AppCompatEditText editLastname;
    private AppCompatEditText editEmail;
    private AppCompatEditText editPhone;
    private AppCompatEditText editCell;
    private AppCompatEditText editAddress;
    private AppCompatSpinner editState;
    private AppCompatSpinner editType;
    private AppCompatEditText editCi;
    private AppCompatSpinner editGender;
    private AppCompatEditText editBirth;

    private ContactHelper dbHelper;
    private long peopleId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        Bundle extras = getIntent().getExtras();

        editPriority = (RadioGroup) findViewById(R.id.edit_priority);
        editFirsname = (AppCompatEditText) findViewById(R.id.edit_firsname);
        editLastname = (AppCompatEditText) findViewById(R.id.edit_lastname);
        editEmail = (AppCompatEditText) findViewById(R.id.edit_email);
        editPhone = (AppCompatEditText) findViewById(R.id.edit_phone);
        editCell = (AppCompatEditText) findViewById(R.id.edit_cell);
        editAddress = (AppCompatEditText) findViewById(R.id.edit_address);
        editState = (AppCompatSpinner) findViewById(R.id.edit_state);
        editType = (AppCompatSpinner) findViewById(R.id.edit_type);
        editCi = (AppCompatEditText) findViewById(R.id.edit_ci);
        editGender = (AppCompatSpinner) findViewById(R.id.edit_gender);
        editBirth = (AppCompatEditText) findViewById(R.id.edit_birth);


        dbHelper = new ContactHelper(this);

        try{
            peopleId = extras.getLong("CONTACT_ID",-1);
        }catch (Exception e){
            e.printStackTrace();
        }

        People queriedPeople = dbHelper.getPeople(peopleId);
        editFirsname.setText(queriedPeople.getFirsname());
        editLastname.setText(queriedPeople.getLastname());
        editEmail.setText(queriedPeople.getEmail());
        editPhone.setText(queriedPeople.getTelephone_private());
        editCell.setText(queriedPeople.getCell_phone());
        editAddress.setText(queriedPeople.getAddress());
        //editState.setText(queriedPeople.getState());
        //editType.setText(queriedPeople.getType_register());
        editCi.setText(queriedPeople.getCi());
        //editGender.setText(queriedPeople.getGender());
        editBirth.setText(queriedPeople.getFecha());

        String[] list_state = getResources().getStringArray(R.array.state);
        ArrayAdapter<String> array = new ArrayAdapter<>(this, R.layout.simple_spinner_item, list_state);
        array.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        editState.setAdapter(array);
        //spn_state.setSelection(0);

        String[] list_type = getResources().getStringArray(R.array.type);
        ArrayAdapter<String> array_type = new ArrayAdapter<>(this, R.layout.simple_spinner_item, list_type);
        array_type.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        editType.setAdapter(array_type);
        //spn_type.setSelection(0);

        String[] list_gender = getResources().getStringArray(R.array.gender);
        ArrayAdapter<String> array_gender = new ArrayAdapter<>(this, R.layout.simple_spinner_item, list_gender);
        array_gender.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        editGender.setAdapter(array_gender);
        //spn_gender.setSelection(0);

        editPriority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == R.id.edit_radio_si){
                    Toast.makeText(EditContact.this, "Este contacto resivira tus mensajes de ayuda", Toast.LENGTH_SHORT).show();
                }else if (checkedId == R.id.edit_radio_no){
                    Toast.makeText(EditContact.this, "Este contacto no resivira tus mensajes de ayuda", Toast.LENGTH_SHORT).show();
                }

            }

        });
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Actualizar datos de usuario.");
        Tools.setSystemBarColor(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update_people, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cancel:
                finish();
                return true;
            case R.id.update:
                String firsname = editFirsname.getText().toString().trim();
                String lastname = editLastname.getText().toString().trim();
                String email = editEmail.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                String cell = editCell.getText().toString().trim();
                String address = editAddress.getText().toString().trim();
                String state = editState.getSelectedItem().toString();
                String type = editType.getSelectedItem().toString();
                int priority = editPriority.getCheckedRadioButtonId();
                String ci = editCi.getText().toString().trim();
                String gender = editGender.getSelectedItem().toString();
                String birth = editBirth.getText().toString().trim();

                if (firsname.isEmpty()){
                    Toast.makeText(this, "Debes ingresar su(s) nombre(s)", Toast.LENGTH_SHORT).show();
                }
                if (email.isEmpty()){
                    Toast.makeText(this, "Debes ingresar su email", Toast.LENGTH_SHORT).show();
                }
                if (phone.isEmpty()){
                    Toast.makeText(this, "Debes ingresar su telefono fijo", Toast.LENGTH_SHORT).show();
                }
                if (cell.isEmpty()){
                    Toast.makeText(this, "Debes ingresar su numero de celular", Toast.LENGTH_SHORT).show();
                }
                if (address.isEmpty()){
                    Toast.makeText(this, "Debes ingresar su direccion", Toast.LENGTH_SHORT).show();
                }

                People people = new People(firsname,lastname,email,phone,cell,address,state,birth,ci,type, priority, gender);
                dbHelper.updatePeople(peopleId, this, people);
                goBackHome();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void goBackHome(){
        startActivity(new Intent(EditContact.this, Contact.class));
    }

}
