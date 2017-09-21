package com.example.beautycentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstProducts;

/**
 * Created by vmplapp on 13/9/17.
 */

public class AddProduct  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String FRAGMENT_P = "Fragment_Product";
    private static final String TAG = "AddProduct";
    EditText name,descr,qt,stAlert;
    Spinner bSpin,cSpin;
    Button btAdd,btBack;
    String pname,descriptn,pbrand,pcategory;

    int quantity = 0, stAlQu = 0;

    String[] brands = { "Loreal Paris", "Avon", "M.A.C.", "Alba", "Clean & Clear", "Maybelline", "Lakme", "Other" };
    String[] category = { "Nails and Accessories", "Gloves", "Equipments", "Brushes", "Spa Accessories","Makeup Chair", "Hair Accessories","Make-Up","Other" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        name = (EditText) findViewById(R.id.name);
        descr = (EditText) findViewById(R.id.descr);
        qt = (EditText) findViewById(R.id.qt);
        stAlert = (EditText) findViewById(R.id.stAlert);
        btAdd = (Button) findViewById(R.id.btAdd);
        btBack = (Button) findViewById(R.id.btBack);

        final DatabaseHandler db = new DatabaseHandler(this);

        bSpin = (Spinner) findViewById(R.id.brandSp);
        cSpin = (Spinner) findViewById(R.id.catSp);
        bSpin.setOnItemSelectedListener(this);
        cSpin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter brandsArray = new ArrayAdapter(this,android.R.layout.simple_spinner_item,brands);
        brandsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        bSpin.setAdapter(brandsArray);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter categoryArray = new ArrayAdapter(this,android.R.layout.simple_spinner_item,category);
        categoryArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        cSpin.setAdapter(categoryArray);


        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                pname = name.getText().toString();
                descriptn = descr.getText().toString();
                try {
                    quantity = Integer.parseInt(qt.getText().toString());
                    stAlQu = Integer.parseInt(stAlert.getText().toString());
                }catch (NumberFormatException e){
                    Toast.makeText(AddProduct.this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
                }
                if(pname.length() !=0 && descriptn.length() !=0 && pbrand.length() !=0 && pcategory.length() !=0 )
                {
                    MstProducts mstProducts = new MstProducts(pname,descriptn,pbrand, pcategory, quantity, stAlQu, 1);
                    db.addProduct(mstProducts);
                    callToFragment();

                }
                else
                Toast.makeText(AddProduct.this, "Please fill each fields", Toast.LENGTH_LONG).show();
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               callToFragment();
            }
        });
    }

    private void callToFragment() {
        Intent i = new Intent(AddProduct.this,Dashboard.class);
        i.putExtra("frgToLoad", FRAGMENT_P);
        startActivity(i);
        finish();//finishing activity
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        //Toast.makeText(getApplicationContext(),country[position] ,Toast.LENGTH_LONG).show();
        Spinner spinner = (Spinner) adapterView;
        if(spinner.getId() == R.id.brandSp)
        {
            //do this
            pbrand = brands[i];
            Log.w(TAG, "onItemSelected: brand selected "+pbrand );
        }
        else if(spinner.getId() == R.id.catSp)
        {
            //do this
            pcategory = category[i];
            Log.w(TAG, "onItemSelected: category selected "+pcategory );
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
                Intent i = new Intent(AddProduct.this,Dashboard.class);
                i.putExtra("frgToLoad", FRAGMENT_P);
                startActivity(i);
                finish();//finishing activity
    }
}
