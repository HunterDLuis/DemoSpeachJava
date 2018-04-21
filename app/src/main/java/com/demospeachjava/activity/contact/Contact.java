package com.demospeachjava.activity.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Spinner;
import com.demospeachjava.R;
import com.demospeachjava.adapter.AdapterPeopleLeft;
import com.demospeachjava.helper.ContactHelper;
import com.demospeachjava.utils.Tools;

public class Contact extends AppCompatActivity{

    public static final String URL_DATA = "http://127.0.0.1:8000/api/proinfem/contact";

    public static final int NAME_SYNCED_WITH_SERVER = 1;
    public static final int NAME_NOT_SYNCED_WITH_SERVER = 0;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ContactHelper dbHelper;
    private AdapterPeopleLeft adapter;
    private String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        initComponent();
        initToolbar();
        initFloatingActionButton();
    }

    private void initComponent() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //set data and list adapter
        populaterecyclerView(filter);

    }

    private void populaterecyclerView(String filter){
        dbHelper = new ContactHelper(this);
        adapter = new AdapterPeopleLeft(dbHelper.peopleList(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mis contactos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basic, menu);

        MenuItem item = menu.findItem(R.id.filterSpinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.filterOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String filter = parent.getSelectedItem().toString();
                populaterecyclerView(filter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                populaterecyclerView(filter);
            }
        });


        spinner.setAdapter(adapter);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void initFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Contact.this, CreateContact.class);
                startActivity(i);
            }
        });
    }

}
