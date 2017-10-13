package com.example.beautycentre;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TableRow.LayoutParams;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstProducts;
import com.example.beautycentre.DatabaseTables.MstSalons;

import java.util.ArrayList;
import java.util.List;

import static com.example.beautycentre.AddProduct.FRAGMENT_P;
import static com.example.beautycentre.AddSalon.FRAGMENT_S;

/**
 * Created by vmplapp on 12/9/17.
 */

public class Product extends Fragment {

    private static final String TAG = "Product";
    View rootView;
    TableLayout tl;
    TableRow tr;
    TextView pid,pname,pdesc,pbrand,pcateg,pqt,pst;
    private ImageButton btnV,btnE,btnD;
    private int PIdSelected;

    MstProducts mstProducts;

    private ArrayList<Integer> PIdlist;
    private ArrayList<String> Pnamelist;
    private ArrayList<String> Pdesclist;
    private ArrayList<String> Pbrandlist;
    private ArrayList<String> Pcategorylist;
    private ArrayList<Integer> Pquantlist;
    private ArrayList<Integer> PstAlertlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        rootView = inflater.inflate(R.layout.frag_table, container, false);
        final DatabaseHandler db = new DatabaseHandler(getActivity());

        PIdlist = new ArrayList<Integer>();
        Pnamelist = new ArrayList<String>();
        Pdesclist = new ArrayList<String>();
        Pbrandlist = new ArrayList<String>();
        Pcategorylist = new ArrayList<String>();
        Pquantlist = new ArrayList<Integer>();
        PstAlertlist = new ArrayList<Integer>();

        List<MstProducts> totalProList = db.getAllProducts();
        for (MstProducts i : totalProList){
            //String log = "Id : " + hodU.getId() +" , Name : " + hodU.getFullname();
            //Log.w(TAG, "Name : " + log );
            PIdlist.add(i.getPid());
            Pnamelist.add(i.getPname());
            Pdesclist.add(i.getDescrip());
            Pbrandlist.add(i.getPbrand());
            Pcategorylist.add(i.getPcategory());
            Pquantlist.add(i.getQuantity());
            PstAlertlist.add(i.getStockAlert());

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

        /** Creating a TextView to add to the row **//*
        TextView descTV = new TextView(getActivity());
        descTV.setText("Description");
        descTV.setTextColor(Color.parseColor("#009688"));
        descTV.setTextSize(15);
        descTV.setBackgroundResource(R.drawable.cell_shape);
        //idTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        descTV.setPadding(10, 20, 10, 20);
        tr.addView(descTV);  // Adding textView to tablerow.
*/
        /** Creating a TextView to add to the row **/
        TextView brTV = new TextView(getActivity());
        brTV.setText("Brand");
        brTV.setTextColor(Color.parseColor("#009688"));
        brTV.setTextSize(15);
        brTV.setBackgroundResource(R.drawable.cell_shape);
        brTV.setGravity(Gravity.CENTER);
        //idTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        brTV.setPadding(10, 20, 10, 20);
        tr.addView(brTV);  // Adding textView to tablerow.

        TextView ctTV = new TextView(getActivity());
        ctTV.setText("Category");
        ctTV.setTextColor(Color.parseColor("#009688"));
        ctTV.setTextSize(15);
        ctTV.setBackgroundResource(R.drawable.cell_shape);
        ctTV.setGravity(Gravity.CENTER);
        //idTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        ctTV.setPadding(10, 20, 10, 20);
        tr.addView(ctTV);  // Adding textView to tablerow.


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

       /* *//** Creating another textview **//*
        TextView stTV = new TextView(getActivity());
        stTV.setText("Stock Alert");
        stTV.setTextColor(Color.parseColor("#009688"));
        stTV.setTextSize(15);
        stTV.setBackgroundResource(R.drawable.cell_shape);
        //idTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        stTV.setPadding(10, 20, 10, 20);
        tr.addView(stTV); // Adding textView to tablerow.
*/
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

           /* *//** Creating a TextView to add to the row **//*
            pdesc = new TextView(getActivity());
            pdesc.setText(String.valueOf(Pdesclist.get(i)));
            pdesc.setTextColor(Color.BLACK);
            pdesc.setPadding(20, 10, 5, 10);
            tr.addView(pdesc);  // Adding textView to tablerow.*/

            /** Creating a TextView to add to the row **/
            pbrand = new TextView(getActivity());
            pbrand.setText(String.valueOf(Pbrandlist.get(i)));
            pbrand.setTextColor(Color.BLACK);
            pbrand.setBackgroundResource(R.drawable.cell_shape);
            pbrand.setPadding(10, 20, 10, 20);
            pbrand.setGravity(Gravity.CENTER);
            tr.addView(pbrand);  // Adding textView to tablerow.

            /** Creating a TextView to add to the row **/
            pcateg = new TextView(getActivity());
            pcateg.setText(String.valueOf(Pcategorylist.get(i)));
            pcateg.setTextColor(Color.BLACK);
            pcateg.setBackgroundResource(R.drawable.cell_shape);
            pcateg.setPadding(10, 20, 10, 20);
            pcateg.setGravity(Gravity.CENTER);
            tr.addView(pcateg);  // Adding textView to tablerow.

            /** Creating another textview **/
            pqt = new TextView(getActivity());
            pqt.setText(String.valueOf(Pquantlist.get(i)));
            pqt.setTextColor(Color.BLACK);
            pqt.setBackgroundResource(R.drawable.cell_shape);
            pqt.setPadding(10, 20, 10, 20);
            pqt.setGravity(Gravity.CENTER);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(pqt); // Adding textView to tablerow.

           /* *//** Creating a TextView to add to the row **//*
            pst = new TextView(getActivity());
            pst.setText(String.valueOf(PstAlertlist.get(i)));
            pst.setTextColor(Color.BLACK);
            pst.setPadding(20, 10, 5, 10);
            tr.addView(pst);  // Adding textView to tablerow.*/


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

                    DatabaseHandler db = new DatabaseHandler(getActivity());
                    mstProducts = db.getSingleProduct(PIdlist.get(finalI));
                    PIdSelected = PIdlist.get(finalI);
                    Log.w(TAG, "addData: PIdSelected "+ PIdSelected);
                    viewProduct(mstProducts);
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
                    editProduct(PIdlist.get(finalI));
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
                   /* PIdSelected = PIdlist.get(finalI);
                    Log.w(TAG, "onClick: PId is "+PIdSelected );
                    deleteDialog(false);*/
                }

            });
            tr.addView(btnD);


            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }

    private void viewProduct(MstProducts mstProducts) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity(),R.style.CustomAlertDialog );
        Context dialogContext = builder.getContext();
        LayoutInflater inflater = LayoutInflater.from(dialogContext);

        View alertHead = inflater.inflate(R.layout.alert_header,null);
        //builder.setView(alertHead);
        TextView tv = (TextView) alertHead.findViewById(R.id.tvAlertHeader);
        tv.setText("Product Detail");
        builder.setCustomTitle(alertHead);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        View alertView = inflater.inflate(R.layout.frag_table, null);
        builder.setView(alertView);
        TableLayout tableLayout = (TableLayout)alertView.findViewById(R.id.maintable);
        ArrayList<String> FRowList = new ArrayList<String>();
        ArrayList<String> SRowList = new ArrayList<String>();

        FRowList.add("Product Name : ");
        FRowList.add("Brand : ");
        FRowList.add("Category : ");
        FRowList.add("Quantity : ");
        FRowList.add("Stock Alert : ");
        FRowList.add("Description : ");

        SRowList.add(mstProducts.getPname());
        SRowList.add(mstProducts.getPbrand());
        SRowList.add(mstProducts.getPcategory());
        SRowList.add(String.valueOf(mstProducts.getQuantity()));
        SRowList.add(String.valueOf(mstProducts.getStockAlert()));
        SRowList.add(mstProducts.getDescrip());

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
                //editSalon(SIdSelected);
                editProduct(PIdSelected);
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

    private void editProduct(int pId) {

        Intent intent = new Intent(getActivity(),AddProduct.class);
        intent.putExtra("ProductId",pId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    private void deleteDialog(final boolean show) {
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(),R.style.CustomAlertDialog );
        builder1.setMessage("Are you sure you want to delete this record?");
        // add the buttons
        AlertDialog dialog2 = builder1.create();

        //builder1.show();
        final AlertDialog finalDialog = dialog2;
        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.w(TAG, "onClick: PId is "+PIdSelected );
                deleteProduct(PIdSelected);
                Intent i = new Intent(getActivity(), Dashboard.class);
                i.putExtra("frgToLoad", FRAGMENT_P);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finalDialog.dismiss();
            }
        });

        final AlertDialog finalDialog1 = dialog2;
        builder1.setNegativeButton("No",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(show == true)
                    viewProduct(mstProducts);
                finalDialog1.dismiss();
            }
        });
        dialog2 = builder1.create();
        dialog2.show();

    }

    private void deleteProduct(int pid) {
        DatabaseHandler db = new DatabaseHandler(getActivity());
        MstProducts mstProducts = db.getSingleProduct(pid);
        Log.w(TAG, "deleteProduct: product id " + mstProducts.getPid());
        db.deleteProduct(mstProducts);
        //PIdlist.remove(pid);
        Log.w(TAG, "Deleted product");

        List<MstProducts> users2 = db.getAllProducts();
        for (MstProducts ur : users2) {
            String log = "PId: " +ur.getPid() + " ,Name: " + ur.getPname() + " ,Quantity: " +ur.getQuantity();
            Log.w(TAG, "product is : " +log);
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

