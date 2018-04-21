package com.demospeachjava.activity.configure;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.demospeachjava.R;
import com.demospeachjava.utils.Tools;

public class Configure extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton view_inf_user;
    private Button buttonConfSay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        view_inf_user = (FloatingActionButton) findViewById(R.id.view_inf_user);
        view_inf_user.setOnClickListener(this);

        initToolbar();
        initCoponent();
    }

    private void initCoponent() {
        buttonConfSay = (Button) findViewById(R.id.configure_say);
        buttonConfSay.setOnClickListener(this);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Configuracion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.configure_say){
            Intent intent = new Intent(this,Say.class);
            startActivity(intent);
        }else if(view.getId()==R.id.view_inf_user){
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        }
    }
}
