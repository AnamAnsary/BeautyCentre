package com.example.beautycentre;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstBranches;
import com.example.beautycentre.DatabaseTables.MstSalons;

import java.util.ArrayList;
import java.util.List;

import static com.example.beautycentre.AddSalon.FRAGMENT_S;

/**
 * Created by vmplapp on 19/9/17.
 */

public class AddBranchofSalon extends AppCompatActivity {

    public static final String FRAGMENT_B = "Fragment_Branch" ;
    private static final String TAG = "AddBranchofSalon";
    EditText sname,bname,adrs,cPname,cPemail,cPmob;
    Button btnAdd;
    String salname,descr,owner,brname,bAdd,CPname,CPemail,CPmob;
    /*private ArrayList<String> Snamelist;
    List< String> salonNames = new ArrayList< String>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_branch);

        salname = getIntent().getExtras().getString("SalonName");
        descr = getIntent().getExtras().getString("Descriptn");
        owner = getIntent().getExtras().getString("Owner");


        sname = (EditText) findViewById(R.id.autoCompleteTVSalon);
        bname = (EditText) findViewById(R.id.bname);
        adrs = (EditText) findViewById(R.id.baddrs);
        cPname = (EditText) findViewById(R.id.cntctPname);
        cPemail = (EditText) findViewById(R.id.cntctPEmail);
        cPmob = (EditText) findViewById(R.id.cntctPMob);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        final DatabaseHandler db = new DatabaseHandler(this);

     /*   Snamelist = new ArrayList<String>();
        List<MstSalons> salon = db.getAllSalons();
        for (MstSalons sl : salon) {
            Snamelist.add(sl.getSname());
        }

        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,Snamelist);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);*/

        sname.setVisibility(View.INVISIBLE);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //salname = sname.getText().toString();
                brname = bname.getText().toString();
                bAdd = adrs.getText().toString();
                CPname = cPname.getText().toString();
                CPemail= cPemail.getText().toString();
                CPmob = cPmob.getText().toString();

                if(brname.length() !=0 && bAdd.length() !=0 && CPname.length() !=0 && CPemail.length() !=0 && CPmob.length() != 0 )
                {
                    // MstProducts mstProducts = new MstProducts(pname,descriptn,quantity,quantity,1);
                  /*  MstBranches mstBranches = new MstBranches(brname,bAdd,CPname,CPemail,CPmob,1);
                    db.addBranch(mstBranches);
                    Intent i = new Intent(AddBranchofSalon.this,Dashboard.class);
                    i.putExtra("frgToLoad", FRAGMENT_B);
                    startActivity(i);
                    finish();//finishing activity*/

                    MstSalons mstSalons = new MstSalons(salname,descr,owner,1);
                    db.addSalon(mstSalons);
                    int lastId = db.getLastInsertedID();
                    Log.w(TAG, "onClick: Last inserted salon id is " +lastId );

                    MstBranches mstBranches = new MstBranches(lastId, brname,bAdd,CPname,CPemail,CPmob,1);
                    db.addBranch(mstBranches);
                    Intent i = new Intent(AddBranchofSalon.this,Dashboard.class);
                    i.putExtra("frgToLoad", FRAGMENT_S);
                    startActivity(i);
                    finish();//finishing activity

                }
                else
                    Toast.makeText(AddBranchofSalon.this, "Please fill each fields", Toast.LENGTH_LONG).show();

            }
        });
    }
}
