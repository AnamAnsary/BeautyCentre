package com.example.beautycentre;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstProducts;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.BLACK;

/**
 * Created by vmplapp on 23/9/17.
 */

public class HomePage extends Fragment {

    private static final String TAG = "HomePage";
    View rootView;

    Button btnsearch;
    Spinner spPro;

    private int pos2;
    private ArrayList<String> Pnamelist;
    private ArrayList<Integer> PIdlist;

    ConstraintLayout proDetails;
    TableLayout tableLayout;
    View hdView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.frag_main, container, false);
        spPro = (Spinner) rootView.findViewById(R.id.spProduct);
        btnsearch = (Button) rootView.findViewById(R.id.btnsearch);

        final DatabaseHandler db = new DatabaseHandler(getActivity());

        Pnamelist = new ArrayList<String>();
        PIdlist = new ArrayList<Integer>();
        List<MstProducts> product = db.getAllProducts();
        for (MstProducts pr : product) {
            Log.w(TAG, "onCreateView: getAllProducts "+pr.getPid()+" " +pr.getPname() );
            //PId_nameList.add(new MstProducts(pr.getPid(),pr.getPname()));
            Pnamelist.add(pr.getPname());
            PIdlist.add(pr.getPid());
        }

        /*if(Pnamelist.size() == 0){
            Toast.makeText(getActivity(), "There are no products available! Please add Products first", Toast.LENGTH_SHORT).show();
        }*/

        ArrayAdapter proadapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, Pnamelist);
        spPro.setAdapter(proadapter);


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Pnamelist.size() == 0){
                    Toast.makeText(getActivity(), "There are no products available! Please add Products first", Toast.LENGTH_SHORT).show();
                }
                else {
                    MstProducts mstProducts = db.getSingleProduct(pos2);
                    if (mstProducts.equals(null))
                        Toast.makeText(getActivity(), "There are no products available! Please add Products first", Toast.LENGTH_SHORT).show();

                    int finalQty = db.getFinalQuantityValue(mstProducts.getPid());
                    Log.w(TAG, "onClick: Db returns " + finalQty);

                    proDetails = (ConstraintLayout) rootView.findViewById(R.id.tvProDetails);
                    hdView = proDetails.getRootView();
                    tableLayout = (TableLayout) hdView.findViewById(R.id.maintable);

                    ArrayList<String> FRowList = new ArrayList<String>();
                    ArrayList<String> SRowList = new ArrayList<String>();

                    FRowList.add("Product ID : ");
                    FRowList.add("Product Name : ");
                    FRowList.add("Description : ");
                    FRowList.add("Brand : ");
                    FRowList.add("Category : ");
                    FRowList.add("Quantity in Stock: ");

                    SRowList.add(String.valueOf(mstProducts.getPid()));
                    SRowList.add(mstProducts.getPname());
                    SRowList.add(mstProducts.getDescrip());
                    SRowList.add(mstProducts.getPbrand());
                    SRowList.add(mstProducts.getPcategory());
                    SRowList.add(String.valueOf(finalQty));

                    if (tableLayout != null) {
                        tableLayout.removeAllViews();
                    }
                    for (int j = 0; j < FRowList.size(); j++) {

                        TableRow tableRow = new TableRow(getActivity());
                        tableRow.setLayoutParams(new TableLayout.LayoutParams
                                (TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

                        TextView textView1 = new TextView(getActivity());
                        textView1.setText(FRowList.get(j));
                        textView1.setTextColor(Color.parseColor("#4db6ac"));
                        textView1.setTextSize(15);
                        textView1.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                        textView1.setPadding(20, 15, 5, 15);
                        textView1.setGravity(Gravity.RIGHT);
                        tableRow.addView(textView1);

                        TextView textView2 = new TextView(getActivity());
                        textView2.setText(SRowList.get(j));
                        textView2.setTextColor(Color.parseColor("#000000"));
                        textView2.setTextSize(15);
                        //textView2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                        textView2.setPadding(20, 15, 5, 15);
                        tableRow.addView(textView2);

                        tableLayout.addView(tableRow);
                    }
                    //Log.w(TAG, "onClick: frow and srow "+FRowList.size()+" "+SRowList.size());

               /* proDetails.setTextSize(15);
                proDetails.setTextColor(Color.parseColor("#009688"));
                proDetails.setPadding(25, 20, 25, 20);
                proDetails.setTextColor(BLACK);
                proDetails.setTextSize(18);
                proDetails.setText("Product ID : " + mstProducts.getPid() + "\nProduct Name : "+ mstProducts.getPname() + "\nDescription : "+ mstProducts.getDescrip() +
                "\nBrand : " + mstProducts.getPbrand() +"\nCategory : " +mstProducts.getPcategory() + "\nQuantity in Stock: "+ finalQty);
*/
                    FRowList.clear();
                    SRowList.clear();
                    //Log.w(TAG, "onClick: frow and srow "+FRowList.size()+" "+SRowList.size());
                }
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
                        pos2 = PIdlist.get(i);
                        //pos2 = i + 1;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Dashboard");

    }
}
