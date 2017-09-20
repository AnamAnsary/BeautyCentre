package com.example.beautycentre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstSalons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vmplapp on 12/9/17.
 */

public class Salon extends ListFragment {

    private static final String TAG = "Salon";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        //return inflater.inflate(R.layout.frag_salons, container, false);

        DatabaseHandler db = new DatabaseHandler(getActivity());

        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();


        HashMap<String, String> hm1 = new HashMap<String,String>();
        hm1.put("id", "ID");
        hm1.put("name","Salon Name");
        hm1.put("desc", "Description");
        hm1.put("owner","Owner Name");
        aList.add(hm1);
/*
        for(int i=0;i<nameofBatt.length;i++){
            hm.put("nametext", nameofBatt[i]);
            hm.put("destext", desc[i]);
            hm.put("image", Integer.toString(imageId[i]) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "nametext","destext","image" };

        // Ids of views in listview_layout
        int[] to = { R.id.nameid,R.id.descid,R.id.imgid};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.salon_customlist, from, to);
        setListAdapter(adapter);*/

        Log.d("Reading: ", "Reading all contacts..");
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

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
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

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Salons");
    }
}

