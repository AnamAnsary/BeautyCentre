package com.example.beautycentre;

import android.app.Activity;
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
    EditText bname,adrs,cPname,cPemail,cPmob;
    AutoCompleteTextView actv;
    Button btnAdd;
    String salname,brname,bAdd,CPname,CPemail,CPmob;
    private ArrayList<String> Snamelist;
    private ArrayList<Integer> SIdlist;
    List< String> salonNames = new ArrayList< String>();
    private int sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_branch);

       // sname = (EditText) findViewById(R.id.autoCompleteTVSalon);
        bname = (EditText) findViewById(R.id.bname);
        adrs = (EditText) findViewById(R.id.baddrs);
        cPname = (EditText) findViewById(R.id.cntctPname);
        cPemail = (EditText) findViewById(R.id.cntctPEmail);
        cPmob = (EditText) findViewById(R.id.cntctPMob);
        actv= (AutoCompleteTextView)findViewById(R.id.autoCompleteTVSalon);
        btnAdd = (Button) findViewById(R.id.btnAdd);



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
                (this,android.R.layout.select_dialog_item,Snamelist);
        //Getting the instance of AutoCompleteTextView

        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                salname = actv.getText().toString();
                brname = bname.getText().toString();
                bAdd = adrs.getText().toString();
                CPname = cPname.getText().toString();
                CPemail= cPemail.getText().toString();
                CPmob = cPmob.getText().toString();

                if(salname.length() !=0 && brname.length() !=0 && bAdd.length() !=0 && CPname.length() !=0 && CPemail.length() !=0 && CPmob.length() != 0 )
                {
                   // MstProducts mstProducts = new MstProducts(pname,descriptn,quantity,quantity,1);

                    sid = db.getSIDfromSalon(salname);
                    Log.w(TAG, "onClick: sid is "+sid );
                    MstBranches mstBranches = new MstBranches(sid,brname,bAdd,CPname,CPemail,CPmob,1);
                    db.addBranch(mstBranches);
                    Intent i = new Intent(AddBranch.this,Dashboard.class);
                    i.putExtra("frgToLoad", FRAGMENT_B);
                    startActivity(i);
                    finish();//finishing activity

                }
                else
                    Toast.makeText(AddBranch.this, "Please fill each fields", Toast.LENGTH_LONG).show();

            }
        });
    }
}
