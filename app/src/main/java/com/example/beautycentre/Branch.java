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
import com.example.beautycentre.DatabaseTables.MstBranches;
import com.example.beautycentre.DatabaseTables.MstProducts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmplapp on 12/9/17.
 */

public class Branch extends Fragment {


    View rootView;
    TableLayout tl;
    TableRow tr;
    TextView tvSn,tvBid,tvBrN,tvBrAd,tvPrN,tvPrE,tvPrM;

    private ArrayList<String> Bnamelist;
    private ArrayList<Integer> BIdlist;
    private ArrayList<String> BAddlist;
    private ArrayList<String> CPNamelist;
    private ArrayList<String> CPEmaillist;
    private ArrayList<String> CPMoblist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        //return inflater.inflate(R.layout.frag_branches, container, false);

        rootView = inflater.inflate(R.layout.frag_branches, container, false);

        final DatabaseHandler db = new DatabaseHandler(getActivity());
        Bnamelist = new ArrayList<String>();
        BIdlist = new ArrayList<Integer>();
        BAddlist = new ArrayList<String>();
        CPNamelist = new ArrayList<String>();
        CPEmaillist = new ArrayList<String>();
        CPMoblist = new ArrayList<String>();

        List<MstBranches> totalBranList = db.getAllBranches();
        for (MstBranches i : totalBranList){
            //String log = "Id : " + hodU.getId() +" , Name : " + hodU.getFullname();
            //Log.w(TAG, "Name : " + log );
            BIdlist.add(i.getBid());
            Bnamelist.add(i.getbName());
            BAddlist.add(i.getBrAdd());
            CPNamelist.add(i.getBrCPName());
            CPEmaillist.add(i.getBrCPEmail());
            CPMoblist.add(i.getBrCPMob());

        }

        tl = (TableLayout) rootView.findViewById(R.id.maintable);
        addHeaders();
        addData();

        return rootView;
    }

    public void addHeaders(){

        /** Create a TableRow dynamically **/
        tr = new TableRow(getActivity());
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        TextView idTV = new TextView(getActivity());
        idTV.setText("ID");
        idTV.setTextColor(Color.parseColor("#009688"));
        idTV.setTextSize(18);
        idTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        idTV.setPadding(20, 20, 5, 20);
        tr.addView(idTV);  // Adding textView to tablerow.

        TextView slNameTV = new TextView(getActivity());
        slNameTV.setText("Salon Name");
        slNameTV.setTextColor(Color.parseColor("#009688"));
        slNameTV.setTextSize(18);
        slNameTV.setPadding(20, 20, 5, 20);
        slNameTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(slNameTV); // Adding textView to tablerow.


        /** Creating another textview **/
        TextView brNameTV = new TextView(getActivity());
        brNameTV.setText("Branch Name");
        brNameTV.setTextColor(Color.parseColor("#009688"));
        brNameTV.setTextSize(18);
        brNameTV.setPadding(20, 20, 5, 20);
        brNameTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(brNameTV); // Adding textView to tablerow.

        /** Creating another textview **/
        TextView brAddTV = new TextView(getActivity());
        brAddTV.setText("Address");
        brAddTV.setTextColor(Color.parseColor("#009688"));
        brAddTV.setTextSize(18);
        brAddTV.setPadding(20, 20, 5, 20);
        brAddTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(brAddTV); // Adding textView to tablerow.

        TextView brCPNameTV = new TextView(getActivity());
        brCPNameTV.setText("Contact Person");
        brCPNameTV.setTextColor(Color.parseColor("#009688"));
        brCPNameTV.setTextSize(18);
        brCPNameTV.setPadding(20, 20, 5, 20);
        brCPNameTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(brCPNameTV); // Adding textView to tablerow.


        /** Creating another textview **/
        TextView brCPEmailTV = new TextView(getActivity());
        brCPEmailTV.setText("Email");
        brCPEmailTV.setTextColor(Color.parseColor("#009688"));
        brCPEmailTV.setTextSize(18);
        brCPEmailTV.setPadding(20, 20, 5, 20);
        brCPEmailTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(brCPEmailTV); // Adding textView to tablerow.

        TextView brCPMobTV = new TextView(getActivity());
        brCPMobTV.setText("Contact Number");
        brCPMobTV.setTextColor(Color.parseColor("#009688"));
        brCPMobTV.setTextSize(18);
        brCPMobTV.setPadding(20, 20, 5, 20);
        brCPMobTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(brCPMobTV); // Adding textView to tablerow.

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

        for (int i = 0; i < BIdlist.size(); i++)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(getActivity());
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tvSn = new TextView(getActivity());
            tvSn.setText("");
            tvSn.setTextColor(Color.BLACK);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvSn.setPadding(20, 20, 5, 20);
            tr.addView(tvSn);  // Adding textView to tablerow.


            /** Creating a TextView to add to the row **/
            tvBid = new TextView(getActivity());
            tvBid.setText(String.valueOf(BIdlist.get(i)));
            tvBid.setTextColor(Color.BLACK);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvBid.setPadding(20, 20, 5, 20);
            tr.addView(tvBid);  // Adding textView to tablerow.

            /** Creating another textview **/
            tvBrN = new TextView(getActivity());
            tvBrN.setText(String.valueOf(Bnamelist.get(i)));
            tvBrN.setTextColor(Color.BLACK);
            tvBrN.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvBrN); // Adding textView to tablerow.


            /** Creating another textview **/
            tvBrAd = new TextView(getActivity());
            tvBrAd.setText(String.valueOf(BAddlist.get(i)));
            tvBrAd.setTextColor(Color.BLACK);
            tvBrAd.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvBrAd); // Adding textView to tablerow.


            /** Creating another textview **/
            tvPrN = new TextView(getActivity());
            tvPrN.setText(String.valueOf(CPNamelist.get(i)));
            tvPrN.setTextColor(Color.BLACK);
            tvPrN.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvPrN); // Adding textView to tablerow.

            /** Creating another textview **/
            tvPrE = new TextView(getActivity());
            tvPrE.setText(String.valueOf(CPEmaillist.get(i)));
            tvPrE.setTextColor(Color.BLACK);
            tvPrE.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvPrE); // Adding textView to tablerow.

            /** Creating another textview **/
            tvPrM = new TextView(getActivity());
            tvPrM.setText(String.valueOf(CPMoblist.get(i)));
            tvPrM.setTextColor(Color.BLACK);
            tvPrM.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvPrM); // Adding textView to tablerow.


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
        getActivity().setTitle("Branches");
    }
}
