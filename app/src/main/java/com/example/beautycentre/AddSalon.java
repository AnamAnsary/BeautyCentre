package com.example.beautycentre;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstProducts;
import com.example.beautycentre.DatabaseTables.MstSalons;

/**
 * Created by vmplapp on 14/9/17.
 */

public class AddSalon extends AppCompatActivity {

    public static final String FRAGMENT_S = "Fragment_Salon" ;
    EditText sname,desc,oname;
    Button btAdd;
    String slname,descriptn,owname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_salon);

        sname = (EditText) findViewById(R.id.sname);
        desc = (EditText) findViewById(R.id.desc);
        oname = (EditText) findViewById(R.id.oname);
        btAdd = (Button) findViewById(R.id.btAdd);

        final DatabaseHandler db = new DatabaseHandler(this);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slname = sname.getText().toString();
                descriptn = desc.getText().toString();
                owname = oname.getText().toString();

                if(sname.length() != 0 && descriptn.length() != 0 && owname.length() != 0)
                {

                    MstSalons mstSalons = new MstSalons(slname,descriptn,owname,1);
                    db.addSalon(mstSalons);

                    Intent i = new Intent(AddSalon.this,Dashboard.class);
                  /*  Bundle b = new Bundle();
                    b.putString("frgToLoad", "nav_salon");*/
                    //i.putExtra("frgToLoad", FRAGMENT_S);
                    startActivity(i);
                    finish();//finishing activity

                   /* Fragment fr = new Salon();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    //FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fr);
                    fragmentTransaction.commit();
*/
                }
                else
                    Toast.makeText(AddSalon.this, "Please fill each fields", Toast.LENGTH_LONG).show();

            }
        });
    }
}
