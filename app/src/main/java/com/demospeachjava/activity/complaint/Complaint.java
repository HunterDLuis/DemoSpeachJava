package com.demospeachjava.activity.complaint;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import com.demospeachjava.R;
import com.demospeachjava.utils.ViewAnimation;

import java.util.ArrayList;
import java.util.List;

public class Complaint extends AppCompatActivity {

    private List<View> view_list = new ArrayList<>();
    private List<RelativeLayout> step_view_list = new ArrayList<>();
    private int success_step = 0;
    private int current_step = 0;
    private View parent_view;

    private Button typeAggress;
    private TextView mItemSelected;
    private String[] listItems;
    private boolean[] checkedItems;
    private ArrayList<Integer> mAggressItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        parent_view = findViewById(android.R.id.content);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initComponent() {
        // populate layout field
        view_list.add(findViewById(R.id.lyt_user));
        view_list.add(findViewById(R.id.lyt_aggress));
        view_list.add(findViewById(R.id.lyt_aggression));
        view_list.add(findViewById(R.id.lyt_complaint));
        view_list.add(findViewById(R.id.lyt_confirmation));

        // populate view step (circle in left)
        step_view_list.add(((RelativeLayout) findViewById(R.id.step_user)));
        step_view_list.add(((RelativeLayout) findViewById(R.id.step_aggress)));
        step_view_list.add(((RelativeLayout) findViewById(R.id.step_aggression)));
        step_view_list.add(((RelativeLayout) findViewById(R.id.step_complaint)));
        step_view_list.add(((RelativeLayout) findViewById(R.id.step_confirmation)));

        for (View v : view_list) {
            v.setVisibility(View.GONE);
        }

        view_list.get(0).setVisibility(View.VISIBLE);
        hideSoftKeyboard();

        // type aggression
        typeAggress = (Button) findViewById(R.id.bt_type_aggression);
        mItemSelected = (TextView) findViewById(R.id.tvItemSelected);

        listItems = getResources().getStringArray(R.array.typeAggression);
        checkedItems = new boolean[listItems.length];

        typeAggress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Complaint.this);
                builder.setTitle("Tipos de agresiones");
                builder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked){
                            if (!mAggressItems.contains(position)){
                                mAggressItems.add(position);
                            }
                        }else if (mAggressItems.contains(position)){
                            mAggressItems.remove(position);
                        }
                    }
                });

                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = "";
                        for (int i=0; i<mAggressItems.size(); i++){
                            item = item + listItems[mAggressItems.get(i)];
                            if (i != mAggressItems.size()-1){
                                item = item + ", ";
                            }
                        }
                        mItemSelected.setText(item);
                    }
                });

                builder.setNegativeButton("Reducir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Limpiar todo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0; i<checkedItems.length;i++){
                            checkedItems[i] = false;
                            mAggressItems.clear();
                            mItemSelected.setText("");
                        }
                    }
                });

                AlertDialog mDialog = builder.create();
                mDialog.show();
            }
        });
    }


    public void clickAction(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.bt_continue_user:
                // validate input user here

                collapseAndContinue(0);
                break;
            case R.id.bt_continue_aggress:
                // validate input user here
                /*if (((EditText) findViewById(R.id.et_description)).getText().toString().trim().equals("")) {
                    Snackbar.make(parent_view, "Description cannot empty", Snackbar.LENGTH_SHORT).show();
                    return;
                }*/

                collapseAndContinue(1);
                break;
            case R.id.bt_continue_aggression:
                // validate input user here
                /*if (time == null) {
                    Snackbar.make(parent_view, "Please set event time", Snackbar.LENGTH_SHORT).show();
                    return;
                }*/
                collapseAndContinue(2);
                break;
            case R.id.bt_continue_complaint:
                // validate input user here
                /*if (date == null) {
                    Snackbar.make(parent_view, "Please set event date", Snackbar.LENGTH_SHORT).show();
                    return;
                }*/

                collapseAndContinue(3);
                break;
            case R.id.bt_add_event:
                // validate input user here
                finish();
                break;
        }
    }

    public void clickLabel(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_label_user:
                if (success_step >= 0 && current_step != 0) {
                    current_step = 0;
                    collapseAll();
                    ViewAnimation.expand(view_list.get(0));
                }
                break;
            case R.id.tv_label_aggress:
                if (success_step >= 1 && current_step != 1) {
                    current_step = 1;
                    collapseAll();
                    ViewAnimation.expand(view_list.get(1));
                }
                break;
            case R.id.tv_label_aggression:
                if (success_step >= 2 && current_step != 2) {
                    current_step = 2;
                    collapseAll();
                    ViewAnimation.expand(view_list.get(2));
                }
                break;
            case R.id.tv_label_complaint:
                if (success_step >= 3 && current_step != 3) {
                    current_step = 3;
                    collapseAll();
                    ViewAnimation.expand(view_list.get(3));
                }
                break;
            case R.id.tv_label_confirmation:
                if (success_step >= 4 && current_step != 4) {
                    current_step = 4;
                    collapseAll();
                    ViewAnimation.expand(view_list.get(4));
                }
                break;
        }
    }

    private void collapseAndContinue(int index) {
        ViewAnimation.collapse(view_list.get(index));
        setCheckedStep(index);
        index++;
        current_step = index;
        success_step = index > success_step ? index : success_step;
        ViewAnimation.expand(view_list.get(index));
    }

    private void collapseAll() {
        for (View v : view_list) {
            ViewAnimation.collapse(v);
        }
    }

    private void setCheckedStep(int index) {
        RelativeLayout relative = step_view_list.get(index);
        relative.removeAllViews();
        ImageButton img = new ImageButton(this);
        img.setImageResource(R.drawable.ic_done);
        img.setBackgroundColor(Color.TRANSPARENT);
        img.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        relative.addView(img);
    }


    public void hideSoftKeyboard()
    {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

}
