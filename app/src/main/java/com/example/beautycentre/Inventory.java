package com.example.beautycentre;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

    private static final String TAG = "Inventory";
    View rootView;
    TableLayout tl;
    TableRow tr;
    TextView pid,pname,pdesc,pbrand,pcateg,pqt,pst;
    private ImageButton btnV,btnE,btnD;

    MstTransaction mstTransaction;

    private ArrayList<Integer> TIdlist;
    private ArrayList<Integer> PIdlist;
    private ArrayList<String> trnamelist;
    private ArrayList<String> trTypelist;
    private ArrayList<String> payStatlist;
    private ArrayList<String> trDatelist;
    private ArrayList<Integer> quantlist;
    private ArrayList<String> PNamelist;
    private Integer TIdSelected;
    private String PnameSelected;


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
            Log.w(TAG, "onCreateView: Id value " +i.getPid() );
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

        Log.w(TAG, "onCreateView: PIDlist " +PIdlist.size() );
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
        id.setText("Sr No");
        id.setTextColor(Color.parseColor("#009688"));
        id.setTextSize(15);
        id.setBackgroundResource(R.drawable.cell_shape);
        id.setGravity(Gravity.CENTER);
        id.setPadding(10, 20, 10, 20);
        tr.addView(id);  // Adding textView to tablerow.


        /** Creating a TextView to add to the row **/
        TextView nameTV = new TextView(getActivity());
        nameTV.setText("Product Name");
        nameTV.setTextColor(Color.parseColor("#009688"));
        nameTV.setTextSize(15);
        nameTV.setBackgroundResource(R.drawable.cell_shape);
        nameTV.setGravity(Gravity.CENTER);
        nameTV.setPadding(10, 20, 10, 20);
        tr.addView(nameTV);  // Adding textView to tablerow.

        /** Creating a TextView to add to the row **/
        TextView descTV = new TextView(getActivity());
        descTV.setText("Concerned Person");
        descTV.setTextColor(Color.parseColor("#009688"));
        descTV.setTextSize(15);
        descTV.setBackgroundResource(R.drawable.cell_shape);
        descTV.setGravity(Gravity.CENTER);
        descTV.setPadding(10, 20, 10, 20);
        tr.addView(descTV);  // Adding textView to tablerow.

        /** Creating a TextView to add to the row **/
        TextView brTV = new TextView(getActivity());
        brTV.setText("Transaction Type");
        brTV.setTextColor(Color.parseColor("#009688"));
        brTV.setTextSize(15);
        brTV.setBackgroundResource(R.drawable.cell_shape);
        brTV.setGravity(Gravity.CENTER);
        brTV.setPadding(10, 20, 10, 20);
        tr.addView(brTV);  // Adding textView to tablerow.

        TextView ctTV = new TextView(getActivity());
        ctTV.setText("Payment Status");
        ctTV.setTextColor(Color.parseColor("#009688"));
        ctTV.setTextSize(15);
        ctTV.setBackgroundResource(R.drawable.cell_shape);
        ctTV.setGravity(Gravity.CENTER);
        ctTV.setPadding(10, 20, 10, 20);
        tr.addView(ctTV);  // Adding textView to tablerow.


        /** Creating another textview **/
        TextView qtTV = new TextView(getActivity());
        qtTV.setText("Transaction Date");
        qtTV.setTextColor(Color.parseColor("#009688"));
        qtTV.setTextSize(15);
        qtTV.setBackgroundResource(R.drawable.cell_shape);
        qtTV.setGravity(Gravity.CENTER);
        qtTV.setPadding(10, 20, 10, 20);
        tr.addView(qtTV); // Adding textView to tablerow.

        /** Creating another textview **/
        TextView stTV = new TextView(getActivity());
        stTV.setText("Quantity");
        stTV.setTextColor(Color.parseColor("#009688"));
        stTV.setTextSize(15);
        stTV.setBackgroundResource(R.drawable.cell_shape);
        stTV.setGravity(Gravity.CENTER);
        stTV.setPadding(10, 20, 10, 20);
        tr.addView(stTV); // Adding textView to tablerow.

        TextView act = new TextView(getActivity());
        act.setText("Actions");
        act.setTextColor(Color.parseColor("#009688"));
        act.setBackgroundResource(R.drawable.cell_shape);
        act.setTextSize(15);
        act.setPadding(10, 20, 10, 20);
        act.setGravity(Gravity.CENTER);
        tr.addView(act); // Adding textView to tablerow.
        //theChild in this case is the child of TableRow
        TableRow.LayoutParams params = (TableRow.LayoutParams) act.getLayoutParams();
        params.span = 3; //amount of columns you will span
        act.setLayoutParams(params);

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
            pid.setText(String.valueOf(i+1));
            pid.setTextColor(Color.BLACK);
            pid.setTextColor(Color.BLACK);
            pid.setBackgroundResource(R.drawable.cell_shape);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            pid.setPadding(10, 20, 10, 20);
            pid.setGravity(Gravity.CENTER);
            tr.addView(pid);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pname = new TextView(getActivity());
            pname.setText(String.valueOf(PNamelist.get(i)));
            pname.setTextColor(Color.BLACK);
            pname.setTextColor(Color.BLACK);
            pname.setBackgroundResource(R.drawable.cell_shape);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            pname.setPadding(10, 20, 10, 20);
            pname.setGravity(Gravity.CENTER);
            tr.addView(pname);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pdesc = new TextView(getActivity());
            pdesc.setText(String.valueOf(trnamelist.get(i)));
            pdesc.setTextColor(Color.BLACK);
            pdesc.setTextColor(Color.BLACK);
            pdesc.setBackgroundResource(R.drawable.cell_shape);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            pdesc.setPadding(10, 20, 10, 20);
            pdesc.setGravity(Gravity.CENTER);
            tr.addView(pdesc);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pbrand = new TextView(getActivity());
            pbrand.setText(String.valueOf(trTypelist.get(i)));
            pbrand.setTextColor(Color.BLACK);
            pbrand.setTextColor(Color.BLACK);
            pbrand.setBackgroundResource(R.drawable.cell_shape);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            pbrand.setPadding(10, 20, 10, 20);
            pbrand.setGravity(Gravity.CENTER);
            tr.addView(pbrand);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pcateg = new TextView(getActivity());
            pcateg.setText(String.valueOf(payStatlist.get(i)));
            pcateg.setTextColor(Color.BLACK);
            pcateg.setTextColor(Color.BLACK);
            pcateg.setBackgroundResource(R.drawable.cell_shape);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            pcateg.setPadding(10, 20, 10, 20);
            pcateg.setGravity(Gravity.CENTER);
            tr.addView(pcateg);  // Adding textView to tablerow.

            /** Creating another textview **/
            pqt = new TextView(getActivity());
            pqt.setText(String.valueOf(trDatelist.get(i)));
            pqt.setTextColor(Color.BLACK);
            pqt.setTextColor(Color.BLACK);
            pqt.setBackgroundResource(R.drawable.cell_shape);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            pqt.setPadding(10, 20, 10, 20);
            pqt.setGravity(Gravity.CENTER);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(pqt); // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pst = new TextView(getActivity());
            pst.setText(String.valueOf(quantlist.get(i)));
            pst.setTextColor(Color.BLACK);
            pst.setTextColor(Color.BLACK);
            pst.setBackgroundResource(R.drawable.cell_shape);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            pst.setPadding(10, 20, 10, 20);
            pst.setGravity(Gravity.CENTER);
            tr.addView(pst);  // Adding textView to tablerow.


            final int finalI = i;
            btnV = new ImageButton(getActivity());
            btnV.setImageResource(R.drawable.iconseye);
            btnV.setBackgroundColor(Color.TRANSPARENT);
            btnV.setBackgroundResource(R.drawable.cell_shape);
            btnV.setPadding(20, 20, 20, 20);
            btnV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            btnV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    //DatabaseHandler db = new DatabaseHandler(getActivity());
                    //mstTransaction = db.getSingleTransaction(TIdlist.get(finalI));
                    TIdSelected = TIdlist.get(finalI);
                    PnameSelected = PNamelist.get(finalI);
                    Log.w(TAG, "addData: TIdSelected "+ TIdSelected);
                    viewTransaction(TIdSelected, PnameSelected);
                }
            });
            tr.addView(btnV);


            btnE = new ImageButton(getActivity());
            btnE.setImageResource(R.drawable.iconsedit);
            btnE.setBackgroundColor(Color.TRANSPARENT);
            btnE.setBackgroundResource(R.drawable.cell_shape);
            btnE.setPadding(20, 20, 20, 20);
            btnE.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            btnE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(getActivity(), "BId is "+ BIdlist.get(finalI), Toast.LENGTH_LONG).show();
                    /*editSalon(SIdlist.get(finalI));*/
                   /* final TableRow parent = (TableRow) v.getParent();
                    tl.removeView(parent);*/
                }
            });
            tr.addView(btnE);

            btnD = new ImageButton(getActivity());
            btnD.setImageResource(R.drawable.iconreddelete);
            btnD.setBackgroundColor(Color.TRANSPARENT);
            btnD.setPadding(20, 20, 20, 20);
            btnD.setBackgroundResource(R.drawable.cell_shape);
            btnD.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            btnD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  /*  SIdSelected = SIdlist.get(finalI);
                    Log.w(TAG, "onClick: SId is "+SIdSelected );
                    deleteDialog(false);*/
                }

            });
            tr.addView(btnD);
            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

    private void viewTransaction(int tid, String pnameSelected) {
        DatabaseHandler db = new DatabaseHandler(getActivity());
        mstTransaction = db.getSingleTransaction(tid);

        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity(),R.style.CustomAlertDialog );
        Context dialogContext = builder.getContext();
        LayoutInflater inflater = LayoutInflater.from(dialogContext);

        View alertHead = inflater.inflate(R.layout.alert_header,null);
        //builder.setView(alertHead);
        TextView tv = (TextView) alertHead.findViewById(R.id.tvAlertHeader);
        tv.setText("Transaction Detail");
        builder.setCustomTitle(alertHead);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        View alertView = inflater.inflate(R.layout.frag_table, null);
        builder.setView(alertView);
        TableLayout tableLayout = (TableLayout)alertView.findViewById(R.id.maintable);
        ArrayList<String> FRowList = new ArrayList<String>();
        ArrayList<String> SRowList = new ArrayList<String>();

        FRowList.add("Product Name : ");
        FRowList.add("Concerned Person : ");
        FRowList.add("Type : ");
        FRowList.add("Status : ");
        FRowList.add("Date : ");
        FRowList.add("Quantity : ");

        SRowList.add(pnameSelected);
        SRowList.add(mstTransaction.getConcernedPname());
        SRowList.add(mstTransaction.getTtype());
        if(null == mstTransaction.getStatus())
            SRowList.add("- -");
        else
            SRowList.add(mstTransaction.getStatus());
        if(null == mstTransaction.getTransDate())
            SRowList.add("- -");
        else
            SRowList.add(mstTransaction.getTransDate());
        SRowList.add(String.valueOf(mstTransaction.getTransQuantity()));

        for(int j=0; j < FRowList.size(); j++ ){

            TableRow tableRow = new TableRow(dialogContext);
            tableRow.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            TextView textView1 = new TextView(dialogContext);
            textView1.setText(FRowList.get(j));
            textView1.setTextColor(Color.parseColor("#757575"));
            textView1.setTextSize(15);
            textView1.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textView1.setPadding(20, 15, 5, 15);
            textView1.setGravity(Gravity.RIGHT);
            tableRow.addView(textView1);

            TextView textView2 = new TextView(dialogContext);
            textView2.setText(SRowList.get(j));
            textView2.setTextColor(Color.parseColor("#000000"));
            textView2.setTextSize(15);
            //textView2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textView2.setPadding(20, 15, 5, 15);
            tableRow.addView(textView2);

            tableLayout.addView(tableRow);
        }

        // add the buttons
        builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //editProduct(PIdSelected);
                dialog.dismiss();
                //finish();
            }
        });

        builder.setNegativeButton("Delete",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //deleteDialog(true);
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("Cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Inventory");
    }
}