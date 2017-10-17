package com.example.beautycentre;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstBranches;
import com.example.beautycentre.DatabaseTables.MstProducts;
import com.example.beautycentre.DatabaseTables.MstSalons;

import java.util.List;

/**
 * Created by vmplapp on 14/9/17.
 */

public class AddSalon extends AppCompatActivity {

    public static final String FRAGMENT_S = "Fragment_Salon" ;
    private static final String TAG = "AddSalon";

    ConstraintLayout mConstraintLayout;
    LinearLayout llbuttons;
    EditText sname,desc,oname,bname,adrs,cPname,cPemail,cPmob;
    Button btnAdd,btBack;
    String slname,descriptn,owname,brname,bAdd,CPname,CPemail,CPmob;;
    private int branchIddisplayed;
    private int active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_salon);

        mConstraintLayout = (ConstraintLayout) findViewById(R.id.conslayout);
        llbuttons = (LinearLayout) findViewById(R.id.llbuttons);
        sname = (EditText) findViewById(R.id.sname);
        desc = (EditText) findViewById(R.id.desc);
        oname = (EditText) findViewById(R.id.oname);
       /* sname = (EditText) findViewById(R.id.autoCompleteTVSalon);*/
        bname = (EditText) findViewById(R.id.bname);
        adrs = (EditText) findViewById(R.id.baddrs);
        cPname = (EditText) findViewById(R.id.cntctPname);
        cPemail = (EditText) findViewById(R.id.cntctPEmail);
        cPmob = (EditText) findViewById(R.id.cntctPMob);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btBack = (Button) findViewById(R.id.btBack);

        final DatabaseHandler db = new DatabaseHandler(this);

        if (getIntent().getExtras() != null) {
            final int sid = getIntent().getExtras().getInt("SalonId");
            Log.w(TAG, "Intent received is sid "+sid);
            MstSalons mstSalons = db.getSingleSalon(sid);
            sname.setText(mstSalons.getSname());
            desc.setText(mstSalons.getDescrip());
            oname.setText(mstSalons.getOwner_name());

            MstBranches mstBranches = db.getTopBranch(sid);
            branchIddisplayed = mstBranches.getBid();
            active = mstBranches.getActive();

            bname.setText(mstBranches.getbName());
            adrs.setText(mstBranches.getBrAdd());
            cPname.setText(mstBranches.getBrCPName());
            cPemail.setText(mstBranches.getBrCPEmail());
            cPmob.setText(mstBranches.getBrCPMob());

           /* findViewById(R.id.textInputLayout9).setVisibility(View.INVISIBLE);
            findViewById(R.id.textInputLayout10).setVisibility(View.INVISIBLE);
            findViewById(R.id.textInputLayout11).setVisibility(View.INVISIBLE);
            findViewById(R.id.textInputLayout12).setVisibility(View.INVISIBLE);
            findViewById(R.id.textInputLayout13).setVisibility(View.INVISIBLE);*/

          /*  bname.setVisibility(View.INVISIBLE);
            adrs.setVisibility(View.INVISIBLE);
            cPname.setVisibility(View.INVISIBLE);
            cPmob.setVisibility(View.INVISIBLE);
            cPemail.setVisibility(View.INVISIBLE);*/

            btnAdd.setText("Update");

           /* ConstraintSet set = new ConstraintSet();
            set.clone(mConstraintLayout);

            set.connect(R.id.llbuttons, ConstraintSet.TOP, R.id.textInputLayout8, ConstraintSet.BOTTOM, 16);
            //set.clear(spSalon.getId(), ConstraintSet.GONE);
            set.applyTo(mConstraintLayout);*/

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    slname = sname.getText().toString();
                    descriptn = desc.getText().toString();
                    owname = oname.getText().toString();

                    brname = bname.getText().toString();
                    bAdd = adrs.getText().toString();
                    CPname = cPname.getText().toString();
                    CPemail = cPemail.getText().toString();
                    CPmob = cPmob.getText().toString();

                    if(sname.length() != 0 && descriptn.length() != 0 && owname.length() != 0 &&
                            brname.length() != 0 && bAdd.length() != 0 && CPname.length() != 0 && CPemail.length() != 0 && CPmob.length() != 0) {
                        MstSalons mstSalons = new MstSalons(sid,slname, descriptn, owname, 1);
                        db.updateSalon(mstSalons);

                        MstBranches mstBranches = new MstBranches(branchIddisplayed,sid, brname, bAdd, CPname, CPemail, CPmob,active);
                        db.updateBranch(mstBranches);
                        onBackPressed();

                    } else
                        Toast.makeText(AddSalon.this, "Please fill each fields", Toast.LENGTH_LONG).show();
                }
            });

        }
        else {

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    slname = sname.getText().toString();
                    descriptn = desc.getText().toString();
                    owname = oname.getText().toString();

                    brname = bname.getText().toString();
                    bAdd = adrs.getText().toString();
                    CPname = cPname.getText().toString();
                    CPemail = cPemail.getText().toString();
                    CPmob = cPmob.getText().toString();
                    if (sname.length() != 0 && descriptn.length() != 0 && owname.length() != 0 &&
                            brname.length() != 0 && bAdd.length() != 0 && CPname.length() != 0 && CPemail.length() != 0 && CPmob.length() != 0) {

                        MstSalons mstSalons = new MstSalons(slname, descriptn, owname, 1);
                        db.addSalon(mstSalons);
                        int lastId = db.getLastInsertedID();
                        Log.w(TAG, "onClick: Last inserted salon id is " + lastId);

                        MstBranches mstBranches = new MstBranches(lastId, brname, bAdd, CPname, CPemail, CPmob, 2);
                        db.addBranch(mstBranches);
                        onBackPressed();
                       /* Intent i = new Intent(AddSalon.this, Dashboard.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.putExtra("frgToLoad", FRAGMENT_S);
                        startActivity(i);*/
                        finish();//finishing activity

                  /*  Intent i = new Intent(AddSalon.this,AddBranchofSalon.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("SalonName", slname);
                    bundle.putString("Descriptn",descriptn);
                    bundle.putString("Owner",owname);
                    i.putExtras(bundle);
                    startActivity(i);*/

                  /*  MstSalons mstSalons = new MstSalons(slname,descriptn,owname,1);
                    db.addSalon(mstSalons);
                    Intent i = new Intent(AddSalon.this,Dashboard.class);
                    i.putExtra("frgToLoad", FRAGMENT_S);
                    startActivity(i);
                    finish();
*/
                    } else
                        Toast.makeText(AddSalon.this, "Please fill each fields", Toast.LENGTH_LONG).show();

                }
            });
        }

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(AddSalon.this,Dashboard.class);
        i.putExtra("frgToLoad", FRAGMENT_S);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();//finishing activity
    }
}
