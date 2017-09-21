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
import com.example.beautycentre.DatabaseTables.MstSalons;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmplapp on 12/9/17.
 */

public class Salon extends Fragment {

    private static final String TAG = "Salon";
    View rootView;
    TableLayout tl;
    TableRow tr;
    TextView tvSid,tvsN,tvsOwn,tvsDes;

    private ArrayList<Integer> SIdlist;
    private ArrayList<String> SNamelist;
    private ArrayList<String> Sdesclist;
    private ArrayList<String> SOwner;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       /* DatabaseHandler db = new DatabaseHandler(getActivity());
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> hm1 = new HashMap<String,String>();
        hm1.put("id", "ID");
        hm1.put("name","Salon Name");
        hm1.put("desc", "Description");
        hm1.put("owner","Owner Name");


        List<MstSalons> salons = db.getAllSalons();
        for (MstSalons sal : salons) {
            String log = "Id: " +sal.getSid() + " ,Name: " + sal.getSname() + " ,Desc: " +sal.getDescrip();
            Log.w(TAG, "log is : " +log);
            // Writing Contacts to log
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("id", String.valueOf(sal.getSid()));
            hm.put("name", sal.getSname());
            hm.put("desc", sal.getDescrip());
            hm.put("owner", sal.getOwner_name());
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "id","name","desc", "owner" };
        // Ids of views in listview_layout
        int[] to = { R.id.sid,R.id.sname,R.id.sdesc,R.id.owname};
        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.salon_customlist, from, to);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);*/

        rootView = inflater.inflate(R.layout.frag_table, container, false);

        final DatabaseHandler db = new DatabaseHandler(getActivity());
        SIdlist = new ArrayList<Integer>();
        SNamelist = new ArrayList<String>();
        Sdesclist = new ArrayList<String>();
        SOwner = new ArrayList<String>();

        List<MstSalons> salons = db.getAllSalons();
        for (MstSalons sal : salons) {

            SIdlist.add(sal.getSid());
            SNamelist.add(sal.getSname());
            Sdesclist.add(sal.getDescrip());
            SOwner.add(sal.getOwner_name());

        }

        tl = (TableLayout) rootView.findViewById(R.id.maintable);
        addHeaders();
        addData();

        return rootView;
    }

    private void addHeaders() {
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
        TextView owNameTV = new TextView(getActivity());
        owNameTV.setText("Owner Name");
        owNameTV.setTextColor(Color.parseColor("#009688"));
        owNameTV.setTextSize(18);
        owNameTV.setPadding(20, 20, 5, 20);
        owNameTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(owNameTV); // Adding textView to tablerow.

        /** Creating another textview **/
        TextView descTV = new TextView(getActivity());
        descTV.setText("Description");
        descTV.setTextColor(Color.parseColor("#009688"));
        descTV.setTextSize(18);
        descTV.setPadding(20, 20, 5, 20);
        descTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(descTV); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
    }


    private void addData() {
        for (int i = 0; i < SIdlist.size(); i++)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(getActivity());
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tvSid = new TextView(getActivity());
            tvSid.setText(String.valueOf(SIdlist.get(i)));
            tvSid.setTextColor(Color.BLACK);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvSid.setPadding(20, 20, 5, 20);
            tr.addView(tvSid);  // Adding textView to tablerow.


            /** Creating a TextView to add to the row **/
            tvsN = new TextView(getActivity());
            tvsN.setText(String.valueOf(SNamelist.get(i)));
            tvsN.setTextColor(Color.BLACK);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvsN.setPadding(20, 20, 5, 20);
            tr.addView(tvsN);  // Adding textView to tablerow.

            /** Creating another textview **/
            tvsOwn = new TextView(getActivity());
            tvsOwn.setText(String.valueOf(SOwner.get(i)));
            tvsOwn.setTextColor(Color.BLACK);
            tvsOwn.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvsOwn); // Adding textView to tablerow.


            /** Creating another textview **/
            tvsDes = new TextView(getActivity());
            tvsDes.setText(String.valueOf(Sdesclist.get(i)));
            tvsDes.setTextColor(Color.BLACK);
            tvsDes.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvsDes); // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

    /*@Override
    public void onStart() {
        super.onStart();
        Log.w(TAG, "inside onStart ");
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {
                //Toast.makeText(getActivity(), data.get(pos).get("Name"), Toast.LENGTH_SHORT).show();
                Log.w(TAG, "onItemClick: Item number is " + position);
            }
        });

    }*/



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Salons");
    }
}

