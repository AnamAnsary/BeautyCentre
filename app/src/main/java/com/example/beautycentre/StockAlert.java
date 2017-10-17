package com.example.beautycentre;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstProducts;

import java.util.ArrayList;
import java.util.List;

import static com.example.beautycentre.AddProduct.FRAGMENT_P;

/**
 * Created by root on 17/10/17.
 */

public class StockAlert extends Fragment {
    private static final String TAG = "StockAlert";
    View rootView;
    TableLayout tl;
    TableRow tr;
    TextView pid,pname,pdesc,pbrand,pcateg,pqt,pst;
    private ImageButton btnV,btnE,btnD;
    private int PIdSelected;

    MstProducts mstProducts;

    private ArrayList<Integer> PIdlist;
    private ArrayList<String> Pnamelist;
    //private ArrayList<String> Pdesclist;
    //private ArrayList<String> Pbrandlist;
    //private ArrayList<String> Pcategorylist;
    private ArrayList<Integer> Pquantlist;
    //private ArrayList<Integer> PstAlertlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        rootView = inflater.inflate(R.layout.frag_table, container, false);
        final DatabaseHandler db = new DatabaseHandler(getActivity());

        PIdlist = new ArrayList<Integer>();
        Pnamelist = new ArrayList<String>();
        Pquantlist = new ArrayList<Integer>();
        //PstAlertlist = new ArrayList<Integer>();

        List<MstProducts> totalProList = db.getAllProducts();
        for (MstProducts i : totalProList){

            int finalQty = db.getFinalQuantityValue(i.getPid());

            if(finalQty <= i.getStockAlert())
            {
                PIdlist.add(i.getPid());
                Pnamelist.add(i.getPname());
                Pquantlist.add(finalQty);
            }

          /*  PIdlist.add(i.getPid());
            Pnamelist.add(i.getPname());
            Pquantlist.add(i.getQuantity());*/
            //PstAlertlist.add(i.getStockAlert());

        }

        tl = (TableLayout) rootView.findViewById(R.id.maintable);
        addHeaders();
        addData();
        return rootView;

    }
    /** This function add the headers to the table **/
    public void addHeaders(){

        /** Create a TableRow dynamically **/
        tr = new TableRow(getActivity());
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        TextView id = new TextView(getActivity());
        id.setText("Sr No");
        id.setTextColor(Color.parseColor("#009688"));
        id.setTextSize(15);
        id.setBackgroundResource(R.drawable.cell_shape);
        id.setGravity(Gravity.CENTER);
        //idTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        id.setPadding(10, 20, 10, 20);
        tr.addView(id);  // Adding textView to tablerow.


        /** Creating a TextView to add to the row **/
        TextView nameTV = new TextView(getActivity());
        nameTV.setText("Product Name");
        nameTV.setTextColor(Color.parseColor("#009688"));
        nameTV.setTextSize(15);
        nameTV.setBackgroundResource(R.drawable.cell_shape);
        nameTV.setGravity(Gravity.CENTER);
        //idTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        nameTV.setPadding(10, 20, 10, 20);
        tr.addView(nameTV);  // Adding textView to tablerow.

        /** Creating another textview **/
        TextView qtTV = new TextView(getActivity());
        qtTV.setText("Quantity");
        qtTV.setTextColor(Color.parseColor("#009688"));
        qtTV.setTextSize(15);
        qtTV.setBackgroundResource(R.drawable.cell_shape);
        qtTV.setGravity(Gravity.CENTER);
        //idTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        qtTV.setPadding(10, 20, 10, 20);
        tr.addView(qtTV); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

    }

    /** This function add the data to the table **/
    public void addData(){

        for (int i = 0; i < Pnamelist.size(); i++)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(getActivity());
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            /** Creating a TextView to add to the row **/
            pid = new TextView(getActivity());
            pid.setText(String.valueOf(i+1));
            pid.setTextColor(Color.BLACK);
            pid.setBackgroundResource(R.drawable.cell_shape);
            pid.setPadding(10, 20, 10, 20);
            pid.setGravity(Gravity.CENTER);
            tr.addView(pid);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pname = new TextView(getActivity());
            pname.setText(String.valueOf(Pnamelist.get(i)));
            pname.setTextColor(Color.BLACK);
            pname.setBackgroundResource(R.drawable.cell_shape);
            pname.setPadding(10, 20, 10, 20);
            pname.setGravity(Gravity.CENTER);
            tr.addView(pname);  // Adding textView to tablerow.

            /** Creating another textview **/
            pqt = new TextView(getActivity());
            pqt.setText(String.valueOf(Pquantlist.get(i)));
            pqt.setTextColor(Color.BLACK);
            pqt.setBackgroundResource(R.drawable.cell_shape);
            pqt.setPadding(10, 20, 10, 20);
            pqt.setGravity(Gravity.CENTER);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(pqt); // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Stock Alerts");

    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
