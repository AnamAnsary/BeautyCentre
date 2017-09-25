package com.example.beautycentre;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstProducts;
import com.example.beautycentre.DatabaseTables.MstTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmplapp on 12/9/17.
 */

public class Inventory extends Fragment {

    View rootView;
    TableLayout tl;
    TableRow tr;
    TextView pid,pname,pdesc,pbrand,pcateg,pqt,pst;


    private ArrayList<Integer> TIdlist;
    private ArrayList<Integer> PIdlist;
    private ArrayList<String> trnamelist;
    private ArrayList<String> trTypelist;
    private ArrayList<String> payStatlist;
    private ArrayList<String> trDatelist;
    private ArrayList<Integer> quantlist;
    private ArrayList<String> PNamelist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        rootView = inflater.inflate(R.layout.frag_table, container, false);
        final DatabaseHandler db = new DatabaseHandler(getActivity());

        TIdlist = new ArrayList<Integer>();
        PIdlist = new ArrayList<Integer>();
        trnamelist = new ArrayList<String>();
        trTypelist = new ArrayList<String>();
        payStatlist = new ArrayList<String>();
        trDatelist = new ArrayList<String>();
        quantlist = new ArrayList<Integer>();
        PNamelist = new ArrayList<String>();

        List<MstTransaction> totalTransList = db.getAllTransactions();
        for (MstTransaction i : totalTransList){
            //String log = "Id : " + hodU.getId() +" , Name : " + hodU.getFullname();
            //Log.w(TAG, "Name : " + log );
            TIdlist.add(i.getTid());
            PIdlist.add(i.getPid());
            trnamelist.add(i.getConcernedPname());
            trTypelist.add(i.getTtype());
            quantlist.add(i.getTransQuantity());

            if(i.getStatus() == null)
                payStatlist.add("--");
            else
                payStatlist.add(i.getStatus());

            if(i.getTransDate() == null)
                trDatelist.add("--");
            else
                trDatelist.add(i.getTransDate());

        }

        for(int i = 0; i < PIdlist.size(); i++)
        {
            MstProducts mstProducts = db.getSingleProduct(PIdlist.get(i));
            PNamelist.add(mstProducts.getPname());

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
        id.setText("ID");
        id.setTextColor(Color.parseColor("#009688"));
        id.setTextSize(18);
        id.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        id.setPadding(20, 20, 5, 20);
        tr.addView(id);  // Adding textView to tablerow.


        /** Creating a TextView to add to the row **/
        TextView nameTV = new TextView(getActivity());
        nameTV.setText("Product Name");
        nameTV.setTextColor(Color.parseColor("#009688"));
        nameTV.setTextSize(18);
        nameTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        nameTV.setPadding(20, 20, 5, 20);
        tr.addView(nameTV);  // Adding textView to tablerow.

        /** Creating a TextView to add to the row **/
        TextView descTV = new TextView(getActivity());
        descTV.setText("Concerned Person");
        descTV.setTextColor(Color.parseColor("#009688"));
        descTV.setTextSize(18);
        descTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        descTV.setPadding(20, 20, 5, 20);
        tr.addView(descTV);  // Adding textView to tablerow.

        /** Creating a TextView to add to the row **/
        TextView brTV = new TextView(getActivity());
        brTV.setText("Transaction Type");
        brTV.setTextColor(Color.parseColor("#009688"));
        brTV.setTextSize(18);
        brTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        brTV.setPadding(20, 20, 5, 20);
        tr.addView(brTV);  // Adding textView to tablerow.

        TextView ctTV = new TextView(getActivity());
        ctTV.setText("Payment Status");
        ctTV.setTextColor(Color.parseColor("#009688"));
        ctTV.setTextSize(18);
        ctTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        ctTV.setPadding(20, 20, 5, 20);
        tr.addView(ctTV);  // Adding textView to tablerow.


        /** Creating another textview **/
        TextView qtTV = new TextView(getActivity());
        qtTV.setText("Transaction Date");
        qtTV.setTextColor(Color.parseColor("#009688"));
        qtTV.setTextSize(18);
        qtTV.setPadding(20, 20, 5, 20);
        qtTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(qtTV); // Adding textView to tablerow.

        /** Creating another textview **/
        TextView stTV = new TextView(getActivity());
        stTV.setText("Quantity");
        stTV.setTextColor(Color.parseColor("#009688"));
        stTV.setTextSize(18);
        stTV.setPadding(20, 20, 5, 20);
        stTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(stTV); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

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

        for (int i = 0; i < TIdlist.size(); i++)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(getActivity());
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            /** Creating a TextView to add to the row **/
            pid = new TextView(getActivity());
            pid.setText(String.valueOf(TIdlist.get(i)));
            pid.setTextColor(Color.BLACK);
            pid.setPadding(20, 10, 5, 10);
            tr.addView(pid);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pname = new TextView(getActivity());
            pname.setText(String.valueOf(PNamelist.get(i)));
            pname.setTextColor(Color.BLACK);
            pname.setPadding(20, 10, 5, 10);
            tr.addView(pname);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pdesc = new TextView(getActivity());
            pdesc.setText(String.valueOf(trnamelist.get(i)));
            pdesc.setTextColor(Color.BLACK);
            pdesc.setPadding(20, 10, 5, 10);
            tr.addView(pdesc);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pbrand = new TextView(getActivity());
            pbrand.setText(String.valueOf(trTypelist.get(i)));
            pbrand.setTextColor(Color.BLACK);
            pbrand.setPadding(20, 10, 5, 10);
            tr.addView(pbrand);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pcateg = new TextView(getActivity());
            pcateg.setText(String.valueOf(payStatlist.get(i)));
            pcateg.setTextColor(Color.BLACK);
            pcateg.setPadding(20, 10, 5, 10);
            tr.addView(pcateg);  // Adding textView to tablerow.

            /** Creating another textview **/
            pqt = new TextView(getActivity());
            pqt.setText(String.valueOf(trDatelist.get(i)));
            pqt.setTextColor(Color.BLACK);
            pqt.setPadding(20, 10, 5, 10);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(pqt); // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pst = new TextView(getActivity());
            pst.setText(String.valueOf(quantlist.get(i)));
            pst.setTextColor(Color.BLACK);
            pst.setPadding(20, 10, 5, 10);
            tr.addView(pst);  // Adding textView to tablerow.

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
        getActivity().setTitle("Inventory");
    }
}