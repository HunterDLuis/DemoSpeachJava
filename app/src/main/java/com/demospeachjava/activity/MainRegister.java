package com.demospeachjava.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.demospeachjava.R;

public class MainRegister extends AppCompatActivity {

    private AutoCompleteTextView reg_firstname;
    private AutoCompleteTextView reg_lastname;
    private AutoCompleteTextView reg_address;
    private AutoCompleteTextView reg_email;
    private EditText reg_password;
    private EditText reg_confir_pass;
    private EditText reg_phone;
    private EditText reg_cel;
    private AppCompatSpinner spn_reg_state;
    private EditText reg_date;
    private EditText reg_ci;



    private Button reg_sign_in_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        initToolbar();
        initComponent();
    }

    private void initComponent() {
        reg_firstname = (AutoCompleteTextView) findViewById(R.id.reg_firstname);
        reg_lastname = (AutoCompleteTextView) findViewById(R.id.reg_lastname);
        reg_address = (AutoCompleteTextView) findViewById(R.id.reg_address);
        reg_email = (AutoCompleteTextView) findViewById(R.id.reg_email);
        reg_password = (EditText) findViewById(R.id.reg_password);
        reg_confir_pass = (EditText) findViewById(R.id.reg_confir_pass);
        reg_phone = (EditText) findViewById(R.id.reg_phone);
        reg_cel = (EditText) findViewById(R.id.reg_cel);
        spn_reg_state = (AppCompatSpinner) findViewById(R.id.spn_reg_state);
        reg_date = (EditText) findViewById(R.id.reg_date);
        reg_ci = (EditText) findViewById(R.id.reg_ci);
        reg_sign_in_button = (Button) findViewById(R.id.reg_sign_in_button);


        String[] list_state = getResources().getStringArray(R.array.state);
        ArrayAdapter<String> array = new ArrayAdapter<>(this, R.layout.simple_spinner_item, list_state);
        array.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spn_reg_state.setAdapter(array);
        spn_reg_state.setSelection(0);

        reg_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFormRegister();
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registrate");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void validateFormRegister(){
        reg_email.setError(null);
        reg_password.setError(null);
        reg_confir_pass.setError(null);
        reg_phone.setError(null);

        String email = reg_email.getText().toString();
        String password = reg_password.getText().toString().trim();
        String confPass = reg_confir_pass.getText().toString().trim();
        String phone = reg_phone.getText().toString().trim();
        String cel = reg_cel.getText().toString().trim();

        if(!validateAutoCompleteTextView(reg_firstname,
                R.string.errorFirsname)){
            return;
        }
        if(!validateAutoCompleteTextView(reg_lastname,
                R.string.errorLastname)){
            return;
        }
        if(!validateAutoCompleteTextView(reg_address,
                R.string.errorAddress)){
            return;
        }
        if(!isEmailValid(email,
                R.string.errorEmail, R.string.lessEmail)){
            return;
        }
        if(!isPasswordValidate(password, R.string.errorPasword,
                R.string.errorPaswordLength)){
            return;
        }
        if(!isConfPassValidate(confPass, R.string.errorConfPasword)){
            return;
        }
        if(!isPhoneValid(phone, R.string.errorPhone, R.string.errorPhoneLength)){
            return;
        }
        if(!isCelValid(cel, R.string.errorCel, R.string.errorPhoneLength)){
            return;
        }
        if(!validateEditText(reg_date, R.string.errorDate)){
            return;
        }
        if(!validateEditText(reg_ci, R.string.errorCi)){
            return;
        }
        Toast.makeText(getApplicationContext(), "Realizar peticion.", Toast.LENGTH_SHORT).show();
    }

    private boolean validateAutoCompleteTextView(AutoCompleteTextView actv, int error){

        if (actv.getText().toString().trim().isEmpty()){
            actv.setError(getString(error));
            return false;
        }
        return true;
    }

    private boolean validateEditText(EditText editText, int error){
        if (editText.getText().toString().trim().isEmpty()){
            editText.setError(getString(error));
            return false;
        }
        return true;
    }

    private boolean isPasswordValidate(String password, int errorPass,
                                    int errorPassLength){

        if(TextUtils.isEmpty(password)){
            reg_password.setError(getString(errorPass));
            // focusView = til_reg_password;
            return false;
        }else if(password.length()<6){
            reg_password.setError(getString(errorPassLength));
            //focusView = til_reg_password;
            return false;
        }
        return true;
    }

    private boolean isConfPassValidate(String confPass, int error){
        if (reg_password.getText().toString().trim().equals(confPass)){
            return true;
        }else {
            reg_confir_pass.setError(getString(error));
            return false;
        }
    }

    private boolean isEmailValid(String email,
                                 int errorFirs, int errorLast) {
        if(TextUtils.isEmpty(email)){
            reg_email.setError(getString(errorLast));
            return false;
        }else if(!(!TextUtils.isEmpty(email) &&
                android.util.Patterns.EMAIL_ADDRESS.
                        matcher(email).matches())){
            reg_email.setError(getString(errorFirs));
            return false;
        }

        return true;
    }

    private boolean isPhoneValid(String phone, int first, int last){
        if(TextUtils.isEmpty(phone)){
            reg_phone.setError(getString(first));
            return false;
        }else if(phone.length()>8&&phone.length()<7){
            reg_phone.setError(getString(last));
            return false;
        }
        return true;
    }

    private boolean isCelValid(String cel, int first, int last){
        if(TextUtils.isEmpty(cel)){
            reg_cel.setError(getString(first));
            return false;
        }else if(cel.length()>9&&cel.length()<8){
            reg_phone.setError(getString(last));
            return false;
        }
        return true;
    }

}
