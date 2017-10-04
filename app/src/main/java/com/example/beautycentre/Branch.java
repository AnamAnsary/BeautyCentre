package com.example.beautycentre;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.LayoutInflater;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstBranches;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmplapp on 12/9/17.
 */

public class Branch extends Fragment {


    private static final String TAG = "Branch";
    View rootView;
    TableLayout tl;
    TableRow tr;
    TextView tvSn,tvBid,tvBrN,tvBrAd,tvPrN,tvPrE,tvPrM;
    ImageButton btnV, btnE, btnD;

    private ArrayList<Integer> SIdlist;
    private ArrayList<String> SNamelist;
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
        //return inflater.inflate(R.layout.frag_table, container, false);

        rootView = inflater.inflate(R.layout.frag_table, container, false);

        final DatabaseHandler db = new DatabaseHandler(getActivity());
        SIdlist = new ArrayList<Integer>();
        SNamelist = new ArrayList<String>();
        Bnamelist = new ArrayList<String>();
        BIdlist = new ArrayList<Integer>();
        BAddlist = new ArrayList<String>();
        CPNamelist = new ArrayList<String>();
        CPEmaillist = new ArrayList<String>();
        CPMoblist = new ArrayList<String>();

        List<MstBranches> totalBranList = db.getAllBranches();
        for (MstBranches ur : totalBranList) {
            String log = "Id: " +ur.getSalonId() + " ,Name: " + ur.getbName();
            Log.w(TAG, "user2 is : " +log);

        }

        for (MstBranches i : totalBranList){
            //String log = "Id : " + hodU.getId() +" , Name : " + hodU.getFullname();
            //Log.w(TAG, "Name : " + log );
            Log.w(TAG, "MstBranch Salon id is : "+i.getSalonId());

            SIdlist.add(i.getSalonId());
            BIdlist.add(i.getBid());
            Bnamelist.add(i.getbName());
            BAddlist.add(i.getBrAdd());
            CPNamelist.add(i.getBrCPName());
            CPEmaillist.add(i.getBrCPEmail());
            CPMoblist.add(String.valueOf(i.getBrCPMob()));

            Log.w(TAG, "Salon id  " +i.getSalonId() );
            String sname = db.getSalonName(i.getSalonId());
            SNamelist.add(sname);



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
        idTV.setText("Sr No");
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

       /* *//** Creating another textview **//*
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


        *//** Creating another textview **//*
        TextView brCPEmailTV = new TextView(getActivity());
        brCPEmailTV.setText("Email");
        brCPEmailTV.setTextColor(Color.parseColor("#009688"));
        brCPEmailTV.setTextSize(18);
        brCPEmailTV.setPadding(20, 20, 5, 20);
        brCPEmailTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(brCPEmailTV); // Adding textView to tablerow.*/

        TextView brCPMobTV = new TextView(getActivity());
        brCPMobTV.setText("Contact Number");
        brCPMobTV.setTextColor(Color.parseColor("#009688"));
        brCPMobTV.setTextSize(18);
        brCPMobTV.setPadding(20, 20, 5, 20);
        brCPMobTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(brCPMobTV); // Adding textView to tablerow.

        /*Button btnview = new Button(getActivity());
        btnview.setText("View");
        btnview.setTextColor(Color.parseColor("#009688"));
        btnview.setTextSize(18);
        btnview.setPadding(20, 20, 5, 20);
        btnview.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(btnview); // Adding textView to tablerow.
*/
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
            int IdClicked = i+1;
            /** Create a TableRow dynamically **/
            tr = new TableRow(getActivity());
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tvSn = new TextView(getActivity());
            //tvSn.setText(String.valueOf(BIdlist.get(i)));
            tvSn.setText(String.valueOf(i+1));
            tvSn.setTextColor(Color.BLACK);
            //companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tvSn.setPadding(20, 20, 5, 20);
            tr.addView(tvSn);  // Adding textView to tablerow.


            /** Creating a TextView to add to the row **/
            tvBid = new TextView(getActivity());
            tvBid.setText(String.valueOf(SNamelist.get(i)));
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


           /* *//** Creating another textview **//*
            tvBrAd = new TextView(getActivity());
            tvBrAd.setText(String.valueOf(BAddlist.get(i)));
            tvBrAd.setTextColor(Color.BLACK);
            tvBrAd.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvBrAd); // Adding textView to tablerow.


            *//** Creating another textview **//*
            tvPrN = new TextView(getActivity());
            tvPrN.setText(String.valueOf(CPNamelist.get(i)));
            tvPrN.setTextColor(Color.BLACK);
            tvPrN.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvPrN); // Adding textView to tablerow.

            *//** Creating another textview **//*
            tvPrE = new TextView(getActivity());
            tvPrE.setText(String.valueOf(CPEmaillist.get(i)));
            tvPrE.setTextColor(Color.BLACK);
            tvPrE.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvPrE); // Adding textView to tablerow.
*/
            /** Creating another textview **/
            tvPrM = new TextView(getActivity());
            tvPrM.setText(String.valueOf(CPMoblist.get(i)));
            tvPrM.setTextColor(Color.BLACK);
            tvPrM.setPadding(20, 20, 5, 20);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(tvPrM); // Adding textView to tablerow.

         /*   btnV = new Button(getActivity());
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
            btnV.setBackgroundResource(R.drawable.view_eye48);
            btnV.setPadding(20, 20, 5, 20);
            btnV.setLayoutParams(params);
            //valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(btnV); // Adding textView to tablerow.
*/

            final int finalI = i;
            // Creation  button
            btnV = new ImageButton(getActivity());
            btnV.setImageResource(R.drawable.iconseye24);
            btnV.setBackgroundColor(Color.TRANSPARENT);
            btnV.setPadding(20, 20, 5, 20);
            btnV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            btnV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                   /* final TableRow parent = (TableRow) v.getParent();
                    tl.removeView(parent);*/

                    DatabaseHandler db = new DatabaseHandler(getActivity());
                    MstBranches mstBranches = db.getSingleBranch(BIdlist.get(finalI));


                    /*LayoutInflater inflater = getActivity().getLayoutInflater();
                    View alertLayout = inflater.inflate(R.layout.dialog_viewlayout, null);
                    final TextView sname = (TextView) alertLayout.findViewById(R.id.sname);
                    final TextView bname = (TextView) alertLayout.findViewById(R.id.bname);
                    final TextView badd = (TextView) alertLayout.findViewById(R.id.badd);
                    final TextView cpname = (TextView) alertLayout.findViewById(R.id.cpname);
                    final TextView cpemail = (TextView) alertLayout.findViewById(R.id.cpemail);
                    final TextView cpnumber = (TextView) alertLayout.findViewById(R.id.cpnumber);
                    sname.setTextSize(18);
                    bname.setTextSize(18);
                    badd.setTextSize(18);
                    cpname.setTextSize(18);
                    cpemail.setTextSize(18);
                    cpnumber.setTextSize(18);

                    sname.setTextColor(Color.parseColor("#EF5350"));
                    bname.setTextColor(Color.parseColor("#EF5350"));
                    badd.setTextColor(Color.parseColor("#EF5350"));
                    cpemail.setTextColor(Color.parseColor("#EF5350"));
                    cpname.setTextColor(Color.parseColor("#EF5350"));
                    cpnumber.setTextColor(Color.parseColor("#EF5350"));

                    sname.setText(SNamelist.get(finalI));
                    bname.setText(mstBranches.getbName());
                    badd.setText(mstBranches.getBrAdd());
                    cpname.setText(mstBranches.getBrCPName());
                    cpemail.setText(mstBranches.getBrCPEmail());
                    cpnumber.setText(mstBranches.getBrCPMob());
*/


                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Branch Detail");
                    //builder.setView(alertLayout);
                   /* builder.setMessage( "Salon Name : " +SNamelist.get(finalI) + "\n" +
                            "Branch Name : " + mstBranches.getbName() + "\n" +
                            "Branch Address : " + mstBranches.getBrAdd() + "\n" +
                            "Contact Person : " + mstBranches.getBrCPName() + "\n" +
                            "Email : " + mstBranches.getBrCPEmail()+ "\n" +
                            "Contact Number : " + mstBranches.getBrCPMob() );*/


                    Context dialogContext = builder.getContext();
                    LayoutInflater inflater = LayoutInflater.from(dialogContext);
                    View alertView = inflater.inflate(R.layout.frag_table, null);
                    builder.setView(alertView);
                    TableLayout tableLayout = (TableLayout)alertView.findViewById(R.id.maintable);
                    ArrayList<String> FRowList = new ArrayList<String>();
                    ArrayList<String> SRowList = new ArrayList<String>();

                    FRowList.add("Salon Name : ");
                    FRowList.add("Branch Name : ");
                    FRowList.add("Branch Address : ");
                    FRowList.add("Contact Person : ");
                    FRowList.add("Email : ");
                    FRowList.add("Contact Number : ");

                    SRowList.add(SNamelist.get(finalI));
                    SRowList.add(mstBranches.getbName());
                    SRowList.add(mstBranches.getBrAdd());
                    SRowList.add(mstBranches.getBrCPName());
                    SRowList.add(mstBranches.getBrCPEmail());
                    SRowList.add(mstBranches.getBrCPMob());

                    //TransactionDetails transactionDetails = new TransactionDetails();
                    for(int j=0; j < FRowList.size(); j++ ){

                        TableRow tableRow = new TableRow(dialogContext);
                        tableRow.setLayoutParams(new LinearLayout.LayoutParams
                                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                        TextView textView1 = new TextView(dialogContext);
                        textView1.setText(FRowList.get(j));
                        textView1.setTextColor(Color.parseColor("#607D8B"));
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
                            editBranch(BIdlist.get(finalI));
                            dialog.dismiss();
                            //finish();
                        }
                    });

                    builder.setNegativeButton("Delete",  new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("Are you sure you want to delete this row?");
                            // add the buttons
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Toast.makeText(getActivity(), "BId is "+ BIdlist.get(finalI), Toast.LENGTH_LONG).show();
                                    deleteBranch(BIdlist.get(finalI));
                                    TableRow parent = (TableRow) v.getParent();
                                    tl.removeView(parent);
                                    dialog.dismiss();
                                    //finish();
                                }
                            });

                            builder.setNegativeButton("No",  new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog2 = builder.create();
                            dialog2.show();
                            /*deleteBranch(BIdlist.get(finalI));
                            TableRow parent = (TableRow) v.getParent();
                            tl.removeView(parent);*/
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
                    //Toast.makeText(getActivity(), "Id is "+ BIdlist.get(finalI), Toast.LENGTH_LONG).show();

                }
            });
            tr.addView(btnV);

            btnE = new ImageButton(getActivity());
            btnE.setImageResource(R.drawable.iconsedit24);
            btnE.setBackgroundColor(Color.TRANSPARENT);
            btnE.setPadding(20, 20, 5, 20);
            btnE.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            btnE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(getActivity(), "BId is "+ BIdlist.get(finalI), Toast.LENGTH_LONG).show();
                    editBranch(BIdlist.get(finalI));
                   /* final TableRow parent = (TableRow) v.getParent();
                    tl.removeView(parent);*/
                }
            });
            tr.addView(btnE);

            btnD = new ImageButton(getActivity());
            btnD.setImageResource(R.drawable.iconsdelete24);
            btnD.setBackgroundColor(Color.TRANSPARENT);
            btnD.setPadding(20, 20, 5, 20);
            btnD.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            btnD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                   /* final TableRow parent = (TableRow) v.getParent();
                    tl.removeView(parent);*/
                    Log.w(TAG, "Id is "+ BIdlist.get(finalI) );
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Are you sure you want to delete this row?");
                    // add the buttons
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(getActivity(), "BId is "+ BIdlist.get(finalI), Toast.LENGTH_LONG).show();
                            deleteBranch(BIdlist.get(finalI));
                            TableRow parent = (TableRow) v.getParent();
                            tl.removeView(parent);
                            dialog.dismiss();
                            //finish();
                        }
                    });

                    builder.setNegativeButton("No",  new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    //Toast.makeText(getActivity(), "Id is "+ finalI, Toast.LENGTH_LONG).show();
                }
            });
            tr.addView(btnD);
            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

    private void editBranch(Integer bid) {
        Intent intent = new Intent(getActivity(),AddBranch.class);
        intent.putExtra("BranchId",bid);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                      /*  intent.putExtra("email",mstUser.getEmail());
                        intent.putExtra("fullname",mstUser.getFullname());*/
        startActivity(intent);
        //finish();
    }


    private void deleteBranch(Integer bid) {
        DatabaseHandler db = new DatabaseHandler(getActivity());
        MstBranches mstBranch = db.getSingleBranch(bid);
        Log.w(TAG, "deleteBranch: branch id " +mstBranch.getBid() );
        db.deleteBranch(mstBranch);
        Log.w(TAG, "Deleted branch" );

        List<MstBranches> users2 = db.getAllBranches();
        for (MstBranches ur : users2) {
            String log = "BId: " +ur.getBid() + " ,Name: " + ur.getbName() + " ,Sid: " +ur.getSalonId();
            Log.w(TAG, "user2 is : " +log);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Branches");
    }

}