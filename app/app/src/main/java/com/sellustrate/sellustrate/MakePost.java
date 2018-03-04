package com.sellustrate.sellustrate;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.File;

import static com.sellustrate.sellustrate.CameraActivity.*;

public class MakePost extends AppCompatActivity {

    @Override
    public static File mediaFile=CameraActivity.getImage();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_make_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override

        });

    }
    public String qualityIntToDescription(int i)
    {
        switch(i) {
            case 0:
                return "The product is brand new and has never been opened.";
            case 1:
                return "The product is brand new, but already opened. Barely touched.";
            case 2:
                return "The product is brand new, but contains some defects. Still more than functional.";
            case 3:
                return "The product is in good condition, having been recently refurbished by the manufacturer.";
            case 4:
                return "The product is in good condition - recently refurbished.";
            case 5:
                return "The product is in absolute mint condition.";
            case 6:
                return "The product is in very good condition.";
            case 7:
                return "The product is used, but still in very good condition.";
            case 8:
                return "The product is quite well used, but still in an acceptable condition. Still works.";
            case 9:
                return "The product is very heavily used, and no longer functions.";
        }
        return null;
    }


    public String randomComment(int i) {
        switch (i) {

            case 0:
                return " Definitely a great deal.";
            case 1:
                return " Hurry now before it's too late!";
            case 2:
                return " Going fast - make it yours today.";
            case 3:
                return " You don't find a lot of deals like this.";
            case 4:
                return " Definitely worth the price.";
            case 5:
                return " Deals like this are hard to come by.";
        }
        return null;
    }

    public String getCondition(int j){
        switch(j){
            case 0:
                return "New";
            case 1:
                return "New other";
            case 2:
                return "New with defects";
            case 3:
                return "Manufacturer refurbished";
            case 4:
                return "Seller refurbished";
            case 5:
                return "Used";
            case 6:
                return "Very Good";
            case 7:
                return "Good";
            case 8:
                return "Acceptable";
            case 9:
                return "For parts or not working";
        }
        return null;
    }

    public String returnFinalDescription()
    {
        return "Selling " + KEYWORD + ": " + qualityIntToDescription(QUALITY) + randomComment(4);
    }

}
