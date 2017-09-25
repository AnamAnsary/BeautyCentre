package com.example.beautycentre;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstProducts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmplapp on 23/9/17.
 */

public class HomePage extends Fragment {

    private static final String TAG = "HomePage";
    View rootView;
    Button btnsearch;
    TextView proDetails;
    Spinner spPro;

    private int pos2;
    private ArrayList<String> Pnamelist;
    private ArrayList<Integer> PIdlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.frag_main, container, false);

        proDetails = (TextView) rootView.findViewById(R.id.tvProDetails);
        spPro = (Spinner) rootView.findViewById(R.id.spProduct);
        btnsearch = (Button) rootView.findViewById(R.id.btnsearch);

        final DatabaseHandler db = new DatabaseHandler(getActivity());

        Pnamelist = new ArrayList<String>();
        PIdlist = new ArrayList<Integer>();
        List<MstProducts> product = db.getAllProducts();
        for (MstProducts pr : product) {
            //PId_nameList.add(new MstProducts(pr.getPid(),pr.getPname()));
            Pnamelist.add(pr.getPname());
            PIdlist.add(pr.getPid());
        }

        if(Pnamelist.size() == 0){
            Toast.makeText(getActivity(), "There are no products available! Please add Products first", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter proadapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, Pnamelist);
        spPro.setAdapter(proadapter);

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MstProducts mstProducts = db.getSingleProduct(pos2);
                if(mstProducts.equals(null))
                    Toast.makeText(getActivity(), "There are no products available! Please add Products first", Toast.LENGTH_SHORT).show();

                int finalQty = db.getFinalQuantityValue(mstProducts.getPid());
                Log.w(TAG, "onClick: Db returns " +finalQty );

                proDetails.setTextSize(15);
                proDetails.setTextColor(Color.parseColor("#009688"));
                proDetails.setPadding(25, 20, 25, 20);
                proDetails.setText("Product ID : " + mstProducts.getPid() + "\nProduct Name : "+ mstProducts.getPname() + "\nDescription : "+ mstProducts.getDescrip() +
                "\nBrand : " + mstProducts.getPbrand() +"\nCategory : " +mstProducts.getPcategory() + "\nQuantity in Stock: "+ finalQty);

            }
        });


        spPro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String selection = (String) adapterView.getItemAtPosition(pos);
                pos2 = -1;
                Log.w(TAG, "onItemClick: selection is " + selection);
                for (int i = 0; i < Pnamelist.size(); i++) {
                    if (Pnamelist.get(i).equals(selection)) {
                        pos2 = i + 1;
                        Log.w(TAG, "onItemClick: pos2 is " + pos2);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return rootView;
        //return inflater.inflate(R.layout.frag_main, container, false);
    }
}
