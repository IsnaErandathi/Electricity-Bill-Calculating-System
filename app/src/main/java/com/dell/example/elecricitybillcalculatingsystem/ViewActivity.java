package com.dell.example.elecricitybillcalculatingsystem;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class ViewActivity extends AppCompatActivity {

    TableView<String[]> ViewT;
    TableHelper tableHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar ViewTb = (Toolbar) findViewById(R.id.tbView);
        setSupportActionBar(ViewTb);



        tableHelper = new TableHelper(this);
        ViewT = (TableView<String[]>) findViewById(R.id.tView);
        ViewT.setColumnCount(3);
        ViewT.setHeaderBackgroundColor(Color.parseColor("#2ecc71"));
        ViewT.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders()));

        FloatingActionButton ViewFb = (FloatingActionButton) findViewById(R.id.fbView);
        ViewFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MySQLClient(ViewActivity.this).retrieve(ViewT);

            }
        });


    }
}
