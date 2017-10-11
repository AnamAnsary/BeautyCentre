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
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstBranches;
import com.example.beautycentre.DatabaseTables.MstSalons;

import java.util.ArrayList;
import java.util.List;

import static com.example.beautycentre.AddBranch.FRAGMENT_B;
import static com.example.beautycentre.AddSalon.FRAGMENT_S;

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
    private ImageButton btnV;
    private ImageButton btnE;
    private ImageButton btnD;
    private MstSalons mstSalons;
    private int SIdSelected;


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
        idTV.setText("Sr No");
        idTV.setTextColor(Color.parseColor("#009688"));
        idTV.setTextSize(15);
        idTV.setBackgroundResource(R.drawable.cell_shape);
        idTV.setGravity(Gravity.CENTER);
        idTV.setPadding(10, 20, 10, 20);
        tr.addView(idTV);  // Adding textView to tablerow.

        TextView slNameTV = new TextView(getActivity());
        slNameTV.setText("Salon Name");
        slNameTV.setTextColor(Color.parseColor("#009688"));
        slNameTV.setTextSize(15);
        slNameTV.setPadding(10, 20, 10, 20);
        slNameTV.setBackgroundResource(R.drawable.cell_shape);
        slNameTV.setGravity(Gravity.CENTER);
        //slNameTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(slNameTV); // Adding textView to tablerow.


        /** Creating another textview **/
        TextView owNameTV = new TextView(getActivity());
        owNameTV.setText("Owner Name");
        owNameTV.setTextColor(Color.parseColor("#009688"));
        owNameTV.setTextSize(15);
        owNameTV.setPadding(10, 20, 10, 20);
        owNameTV.setBackgroundResource(R.drawable.cell_shape);
        owNameTV.setGravity(Gravity.CENTER);
        //owNameTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(owNameTV); // Adding textView to tablerow.

      /*  *//** Creating another textview **//*
        TextView descTV = new TextView(getActivity());
        descTV.setText("Description");
        descTV.setTextColor(Color.parseColor("#009688"));
        descTV.setTextSize(18);
        descTV.setPadding(20, 20, 5, 20);
        descTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(descTV); // Adding textView to tablerow.
*/

        TextView act = new TextView(getActivity());
        act.setText("Actions");
        act.setTextColor(Color.parseColor("#009688"));
        act.setBackgroundResource(R.drawable.cell_shape);
        act.setTextSize(15);
        act.setPadding(10, 20, 10, 20);
        act.setGravity(Gravity.CENTER);
        //act.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(act); // Adding textView to tablerow.
        //theChild in this case is the child of TableRow
        TableRow.LayoutParams params = (TableRow.LayoutParams) act.getLayoutParams();
        params.span = 3; //amount of columns you will span
        act.setLayoutParams(params);
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
            tvSid.setText(String.valueOf(i+1));
            tvSid.setTextColor(Color.BLACK);
            tvSid.setBackgroundResource(R.drawable.cell_shape);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvSid.setPadding(10, 20, 10, 20);
            tvSid.setGravity(Gravity.CENTER);
            tr.addView(tvSid);  // Adding textView to tablerow.


            /** Creating a TextView to add to the row **/
            tvsN = new TextView(getActivity());
            tvsN.setText(String.valueOf(SNamelist.get(i)));
            tvsN.setTextColor(Color.BLACK);
            tvsN.setBackgroundResource(R.drawable.cell_shape);
            tvsN.setGravity(Gravity.CENTER);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvsN.setPadding(10, 20, 10, 20);
            tr.addView(tvsN);  // Adding textView to tablerow.

            /** Creating another textview **/
            tvsOwn = new TextView(getActivity());
            tvsOwn.setText(String.valueOf(SOwner.get(i)));
            tvsOwn.setTextColor(Color.BLACK);
            tvsOwn.setPadding(10, 20, 10, 20);
            tvsOwn.setBackgroundResource(R.drawable.cell_shape);
            tvsOwn.setGravity(Gravity.CENTER);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvsOwn); // Adding textView to tablerow.


          /*  *//** Creating another textview **//*
            tvsDes = new TextView(getActivity());
            tvsDes.setText(String.valueOf(Sdesclist.get(i)));
            tvsDes.setTextColor(Color.BLACK);
            tvsDes.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvsDes); // Adding textView to tablerow.
*/

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
                    mstSalons = db.getSingleSalon(SIdlist.get(finalI));
                    SIdSelected = SIdlist.get(finalI);
                    Log.w(TAG, "addData: SIdSelected "+ SIdSelected);
                    viewSalon(mstSalons);
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
                    editSalon(SIdlist.get(finalI));
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
                    SIdSelected = SIdlist.get(finalI);
                    Log.w(TAG, "onClick: SId is "+SIdSelected );
                    deleteDialog(false);
                }

            });
            tr.addView(btnD);

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

    private void viewSalon(MstSalons mstSalons) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity(),R.style.CustomAlertDialog );
        Context dialogContext = builder.getContext();
        LayoutInflater inflater = LayoutInflater.from(dialogContext);

        View alertHead = inflater.inflate(R.layout.alert_header,null);
        //builder.setView(alertHead);
        TextView tv = (TextView) alertHead.findViewById(R.id.tvAlertHeader);
        tv.setText("Salon Detail");
        builder.setCustomTitle(alertHead);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        View alertView = inflater.inflate(R.layout.frag_table, null);
        builder.setView(alertView);
        TableLayout tableLayout = (TableLayout)alertView.findViewById(R.id.maintable);
        ArrayList<String> FRowList = new ArrayList<String>();
        ArrayList<String> SRowList = new ArrayList<String>();

        FRowList.add("Salon Name : ");
        FRowList.add("Owner Name : ");
        FRowList.add("Description : ");

        SRowList.add(mstSalons.getSname());
        SRowList.add(mstSalons.getOwner_name());
        SRowList.add(mstSalons.getDescrip());

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
                editSalon(SIdSelected);
                //editBranch(BIdlist.get(finalI));
                dialog.dismiss();
                //finish();
            }
        });

        builder.setNegativeButton("Delete",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDialog(true);
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

    private void editSalon(Integer sid) {
        Intent intent = new Intent(getActivity(),AddSalon.class);
        intent.putExtra("SalonId",sid);
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
                Log.w(TAG, "onClick: SId is "+SIdSelected );
                deleteSalon(SIdSelected);
                Intent i = new Intent(getActivity(), Dashboard.class);
                i.putExtra("frgToLoad", FRAGMENT_S);
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
                viewSalon(mstSalons);
                finalDialog1.dismiss();
            }
        });
        dialog2 = builder1.create();
        dialog2.show();
    }

    private void deleteSalon(Integer sid) {
        DatabaseHandler db = new DatabaseHandler(getActivity());
        MstSalons mstSalon = db.getSingleSalon(sid);
        Log.w(TAG, "deleteSalon: salon id " +mstSalon.getSid() );
        db.deleteSalon(mstSalon);
        SIdlist.remove(sid);

        Log.w(TAG, "Deleted salon" );

        List<MstSalons> users2 = db.getAllSalons();
        for (MstSalons ur : users2) {
            String log = "SId: " +ur.getSid() + " ,Name: " + ur.getSname() + " ,Owner: " +ur.getOwner_name();
            Log.w(TAG, "salon is : " +log);
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

