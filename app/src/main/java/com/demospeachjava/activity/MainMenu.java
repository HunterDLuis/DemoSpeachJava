package com.demospeachjava.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.demospeachjava.R;
import com.demospeachjava.activity.aggression.Aggression;
import com.demospeachjava.activity.chat.ChatActivity;
import com.demospeachjava.activity.configure.Configure;
import com.demospeachjava.activity.contact.Contact;
import com.demospeachjava.adapter.AdapterGridShopCategory;
import com.demospeachjava.data.DataGenerator;
import com.demospeachjava.activity.complaint.Complaint;
import com.demospeachjava.model.ShopCategory;
import com.demospeachjava.utils.Tools;
import com.demospeachjava.widget.SpacingItemDecoration;

import java.util.List;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ActionBar actionBar;
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private AdapterGridShopCategory mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proinfem);

        initToolbar();
        initNavigationMenu();
        initComponent();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Proinfem");
    }

    private void initNavigationMenu() {
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                int id=item.getItemId();
                if (id == R.id.nav_exit){
                    finish();
                }
                drawer.closeDrawers();
                return true;
            }
        });

        // open drawer at start
        //drawer.openDrawer(GravityCompat.START);
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 6), true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        List<ShopCategory> items = DataGenerator.getShoppingCategory(this);

        //set data and list adapter
        mAdapter = new AdapterGridShopCategory(this, items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterGridShopCategory.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ShopCategory obj, int position) {
                if (obj.title.equals("DENUNCIA")) {
                    Intent intent = new Intent(MainMenu.this, Complaint.class);
                    startActivity(intent);
                } else if (obj.title.equals("CONTACTOS")){
                    Intent intent = new Intent(MainMenu.this, Contact.class);
                    startActivity(intent);
                } else if (obj.title.equals("INFORMATE")){
                    Intent intent = new Intent(MainMenu.this, Aggression.class);
                    startActivity(intent);
                }else if (obj.title.equals("CHAT")){
                    Intent intent = new Intent(MainMenu.this, ChatActivity.class);
                    startActivity(intent);
                }else if (obj.title.equals("CONFIGURAR")){
                    Intent intent = new Intent(MainMenu.this, Configure.class);
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


}
