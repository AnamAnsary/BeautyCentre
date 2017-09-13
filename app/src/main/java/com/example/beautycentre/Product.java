package com.example.beautycentre;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstProducts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmplapp on 12/9/17.
 */

public class Product extends Fragment {

    View rootView;
    TableLayout tl;
    TableRow tr;
    TextView companyTV,valueTV;
    /*Button del;

    String companies[] = {"Google","Windows","iPhone","Nokia","Samsung",
            "Google","Windows","iPhone","Nokia","Samsung",
            "Google","Windows","iPhone","Nokia","Samsung"};
    String os[]       =  {"Android","Mango","iOS","Symbian","Bada",
            "Android","Mango","iOS","Symbian","Bada",
            "Android","Mango","iOS","Symbian","Bada"};*/
    private ArrayList<String> Pnamelist;
    private ArrayList<Integer> PIdlist;
    private ArrayList<Integer> Pquantlist;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        rootView = inflater.inflate(R.layout.frag_products, container, false);

        /*FloatingActionButton fab_bt = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab_bt.setVisibility(View.INVISIBLE);
*/
        //btn = (Button) rootView.findViewById(R.id.order_btn);

        final DatabaseHandler db = new DatabaseHandler(getActivity());
        Pnamelist = new ArrayList<String>();
        PIdlist = new ArrayList<Integer>();
        Pquantlist = new ArrayList<Integer>();

        List<MstProducts> totalProList = db.getAllProducts();
        for (MstProducts i : totalProList){
            //String log = "Id : " + hodU.getId() +" , Name : " + hodU.getFullname();
            //Log.w(TAG, "Name : " + log );
            Pnamelist.add(i.getPname());
            PIdlist.add(i.getPid());
            Pquantlist.add(i.getQuantity());

        }

        tl = (TableLayout) rootView.findViewById(R.id.maintable);
        addHeaders();
        addData();
        //return inflater.inflate(R.layout.frag_products, container, false);

       /* FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add Product", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
/*
        View appbar =  navigationView.getFocusedChild();
        fab = (FloatingActionButton) appbar.findViewById(R.id.fab);
        Dashboard dashboard = (Dashboard) getActivity();
        dashboard.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Do what you want
                Snackbar.make(view, "Add Product", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        return rootView;

    }

    /** This function add the headers to the table **/
    public void addHeaders(){

        /** Create a TableRow dynamically **/
        tr = new TableRow(getActivity());
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        TextView companyTV = new TextView(getActivity());
        companyTV.setText("Product Names");
        companyTV.setTextColor(Color.GRAY);
        companyTV.setTextSize(18);
        companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        companyTV.setPadding(20, 20, 5, 20);
        tr.addView(companyTV);  // Adding textView to tablerow.

        /** Creating another textview **/
        TextView valueTV = new TextView(getActivity());
        valueTV.setText("Quantity");
        valueTV.setTextColor(Color.GRAY);
        valueTV.setTextSize(18);
        valueTV.setPadding(20, 20, 5, 20);
        valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(valueTV); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

     /*
     // we are adding two textviews for the divider because we have two columns
        tr = new TableRow(getActivity());
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

        *//** Creating another textview **//*
        TextView divider = new TextView(getActivity());
        divider.setText("-----------------");
        divider.setTextColor(Color.BLUE);
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.

        TextView divider2 = new TextView(getActivity());
        divider2.setText("-------------------------");
        divider2.setTextColor(Color.BLUE);
        divider2.setPadding(5, 0, 0, 0);
        divider2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider2); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

                */
    }

    /** This function add the data to the table **/
    public void addData(){

        for (int i = 0; i < Pnamelist.size(); i++)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(getActivity());
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

            /** Creating a TextView to add to the row **/
            companyTV = new TextView(getActivity());
            companyTV.setText(Pnamelist.get(i));
            companyTV.setTextColor(Color.BLACK);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            companyTV.setPadding(20, 10, 5, 10);
            tr.addView(companyTV);  // Adding textView to tablerow.

            /** Creating another textview **/
            valueTV = new TextView(getActivity());
            valueTV.setText(String.valueOf(Pquantlist.get(i)));
            valueTV.setTextColor(Color.BLACK);
            valueTV.setPadding(20, 10, 5, 10);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(valueTV); // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Products");

    }

    @Override
    public void onResume() {
        super.onResume();
    }

}

