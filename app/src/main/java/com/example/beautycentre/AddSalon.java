package com.example.beautycentre;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstBranches;
import com.example.beautycentre.DatabaseTables.MstProducts;
import com.example.beautycentre.DatabaseTables.MstSalons;

/**
 * Created by vmplapp on 14/9/17.
 */

public class AddSalon extends AppCompatActivity {

    public static final String FRAGMENT_S = "Fragment_Salon" ;
    private static final String TAG = "AddSalon";
    EditText sname,desc,oname,bname,adrs,cPname,cPemail,cPmob;
    Button btnAdd;
    String slname,descriptn,owname,brname,bAdd,CPname,CPemail,CPmob;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_salon);

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


        final DatabaseHandler db = new DatabaseHandler(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slname = sname.getText().toString();
                descriptn = desc.getText().toString();
                owname = oname.getText().toString();

                brname = bname.getText().toString();
                bAdd = adrs.getText().toString();
                CPname = cPname.getText().toString();
                CPemail= cPemail.getText().toString();
                CPmob = cPmob.getText().toString();
                if(sname.length() != 0 && descriptn.length() != 0 && owname.length() != 0 &&
                        brname.length() !=0 && bAdd.length() !=0 && CPname.length() !=0 && CPemail.length() !=0 && CPmob.length() != 0 )
                {

                    MstSalons mstSalons = new MstSalons(slname,descriptn,owname,1);
                    db.addSalon(mstSalons);
                    int lastId = db.getLastInsertedID();
                    Log.w(TAG, "onClick: Last inserted salon id is " +lastId );

                    MstBranches mstBranches = new MstBranches(lastId, brname,bAdd,CPname,CPemail,CPmob,1);
                    db.addBranch(mstBranches);
                    Intent i = new Intent(AddSalon.this,Dashboard.class);
                    i.putExtra("frgToLoad", FRAGMENT_S);
                    startActivity(i);
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
                }
                else
                    Toast.makeText(AddSalon.this, "Please fill each fields", Toast.LENGTH_LONG).show();

            }
        });
    }
}
