package com.example.beautycentre;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstBranches;
import com.example.beautycentre.DatabaseTables.MstProducts;
import com.example.beautycentre.DatabaseTables.MstSalons;
import com.example.beautycentre.DatabaseTables.MstUsers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmplapp on 14/9/17.
 */

public class AddBranch extends AppCompatActivity {

    public static final String FRAGMENT_B = "Fragment_Branch" ;
    private static final String TAG = "AddBranch";
    ConstraintLayout mConstraintLayout;
    TextView tvSalon;
    EditText bname,adrs,cPname,cPemail,cPmob;
    Spinner spSalon;
    Button btnAdd,btBack;
    String brname,bAdd,CPname,CPemail,CPmob;
    private ArrayList<String> Snamelist;
    private ArrayList<Integer> SIdlist;
    private int pos2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_branch);

        mConstraintLayout = (ConstraintLayout) findViewById(R.id.conslayout);
        tvSalon = (TextView) findViewById(R.id.tvSalon);
        bname = (EditText) findViewById(R.id.bname);
        adrs = (EditText) findViewById(R.id.baddrs);
        cPname = (EditText) findViewById(R.id.cntctPname);
        cPemail = (EditText) findViewById(R.id.cntctPEmail);
        cPmob = (EditText) findViewById(R.id.cntctPMob);
        spSalon = (Spinner) findViewById(R.id.spSalon);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btBack = (Button) findViewById(R.id.btBack);

        final DatabaseHandler db = new DatabaseHandler(this);

        Snamelist = new ArrayList<String>();
        SIdlist = new ArrayList<Integer>();
        List<MstSalons> salon = db.getAllSalons();
        for (MstSalons sl : salon) {
            Snamelist.add(sl.getSname());
            SIdlist.add(sl.getSid());
        }

        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, Snamelist);
        spSalon.setAdapter(adapter);

      /*  actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);*/

        if (getIntent().getExtras() != null) {
            final int bid = getIntent().getExtras().getInt("BranchId");
            Log.w(TAG, "Intent received is bid "+bid);
            MstBranches mstBranches = db.getSingleBranch(bid);
            final int Sid = mstBranches.getSalonId();
            //final int branchid = mstBranches.getSalonId();
            //Log.w(TAG, "onCreate: Branch id to update " +bid );
            String salName = db.getSalonName(Sid);
            spSalon.setVisibility(View.INVISIBLE);
            tvSalon.setText("Selected Salon : "+ salName);
            bname.setText(mstBranches.getbName());
            adrs.setText(mstBranches.getBrAdd());
            cPname.setText(mstBranches.getBrCPName());
            cPemail.setText(mstBranches.getBrCPEmail());
            cPmob.setText(mstBranches.getBrCPMob());
            btnAdd.setText("Update");

            //spSalon.setPadding(0,0,0,0);

           /* ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 0);
            spSalon.setLayoutParams(params);
            params.setMargins(24, 0, 24, 0);
            adrs.setLayoutParams(params);*/

            ConstraintSet set = new ConstraintSet();
            set.clone(mConstraintLayout);

            set.connect(R.id.textInputLayout9, ConstraintSet.TOP, R.id.tvSalon, ConstraintSet.BOTTOM, 16);
            //set.clear(spSalon.getId(), ConstraintSet.GONE);
            set.applyTo(mConstraintLayout);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //salname = actv.getText().toString();
                    brname = bname.getText().toString();
                    bAdd = adrs.getText().toString();
                    CPname = cPname.getText().toString();
                    CPemail = cPemail.getText().toString();
                    CPmob = cPmob.getText().toString();

                    if (brname.length() != 0 && bAdd.length() != 0 && CPname.length() != 0 && CPemail.length() != 0 && CPmob.length() != 0) {
                        // MstProducts mstProducts = new MstProducts(pname,descriptn,quantity,quantity,1);
                        // sid = db.getSIDfromSalon(salname);
                        //Log.w(TAG, "onClick: sid is "+sid );
                        //Log.w(TAG, "onClick: bid in if "+bid );
                        MstBranches mstBranches = new MstBranches(bid, Sid, brname, bAdd, CPname, CPemail, CPmob, 1);
                        Log.w(TAG, "new updated row should be : id "+mstBranches.getBid()+"name "+mstBranches.getbName()+
                                "Address "+mstBranches.getBrAdd()+ "Contact person "+mstBranches.getBrCPName()+
                                "Email "+mstBranches.getBrCPEmail()+" Contact "+mstBranches.getBrCPMob());
                        //MstBranches mstBranches2 = db.getSingleBranch(branchid);
                        //Log.w(TAG, "Branch id current " + mstBranches2.getBid());
                        db.updateBranch(mstBranches);
                        List<MstBranches> users2 = db.getAllBranches();
                        for (MstBranches ur : users2) {
                            String log = "Id: " +ur.getBid() + " ,Name: " + ur.getbName() + ",Contact P: " +ur.getBrCPName();
                            Log.w(TAG, "branch is : " +log);

                        }
                        Intent i = new Intent(AddBranch.this, Dashboard.class);
                        i.putExtra("frgToLoad", FRAGMENT_B);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();//finishing activity

                    } else
                        Toast.makeText(AddBranch.this, "Please fill each fields", Toast.LENGTH_LONG).show();
                }
            });

        } else{
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //salname = actv.getText().toString();
                    brname = bname.getText().toString();
                    bAdd = adrs.getText().toString();
                    CPname = cPname.getText().toString();
                    CPemail = cPemail.getText().toString();
                    CPmob = cPmob.getText().toString();

                    if (pos2 != -1 && brname.length() != 0 && bAdd.length() != 0 && CPname.length() != 0 && CPemail.length() != 0 && CPmob.length() != 0) {
                        // MstProducts mstProducts = new MstProducts(pname,descriptn,quantity,quantity,1);

                        // sid = db.getSIDfromSalon(salname);
                        //Log.w(TAG, "onClick: sid is "+sid );
                        MstBranches mstBranches = new MstBranches(pos2, brname, bAdd, CPname, CPemail, CPmob, 1);
                        db.addBranch(mstBranches);
                        Intent i = new Intent(AddBranch.this, Dashboard.class);
                        i.putExtra("frgToLoad", FRAGMENT_B);
                        startActivity(i);
                        finish();//finishing activity

                    } else
                        Toast.makeText(AddBranch.this, "Please fill each fields", Toast.LENGTH_LONG).show();

                }
            });

        }
       /* actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                String selection = (String) parent.getItemAtPosition(pos);
                pos2 = -1;
                Log.w(TAG, "onItemClick: selection is "+selection );
                for (int i = 0; i < Snamelist.size(); i++) {
                    if (Snamelist.get(i).equals(selection)) {
                        pos2 = i+1;
                        Log.w(TAG, "onItemClick: pos2 is "+pos2 );
                        break;
                    }
                }
            }
        });
*/
        spSalon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String selection = (String) adapterView.getItemAtPosition(pos);
                pos2 = -1;
                Log.w(TAG, "onItemClick: selection is " + selection);
                for (int i = 0; i < Snamelist.size(); i++) {
                    if (Snamelist.get(i).equals(selection)) {
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

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddBranch.this, Dashboard.class);
                i.putExtra("frgToLoad", FRAGMENT_B);
                startActivity(i);
                finish();//finishing activity
            }
        });
    }

    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(AddBranch.this,Dashboard.class);
        i.putExtra("frgToLoad", FRAGMENT_B);
        startActivity(i);
        finish();//finishing activity
    }
}