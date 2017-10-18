package com.example.beautycentre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.beautycentre.DatabaseTables.MstTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.example.beautycentre.Dashboard.MyPREFERENCES;
import static com.example.beautycentre.Dashboard.Name;

/**
 * Created by vmplapp on 13/9/17.
 */

public class AddProduct  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String FRAGMENT_P = "Fragment_Product";
    private static final String TAG = "AddProduct";
    TextView tvProCateg,tvProBrand;
    EditText name,descr,qt,stAlert;
    Spinner bSpin,cSpin;
    Button btAdd,btBack;
    String pname,descriptn,pbrand,pcategory;

    int quantity = 0, stAlQu = 0;

    String[] brands = { "--Select--","Loreal Paris", "M.A.C.", "Alba", "Clean & Clear", "Maybelline", "Lakme", "Other" };
    String[] category = { "--Select--","Nails and Accessories", "Gloves", "Equipments", "Brushes", "Spa Accessories","Makeup Chair", "Hair Accessories","Make-Up","Other" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        setTitle("Add Product");

        tvProBrand = (TextView) findViewById(R.id.tvProBrand);
        tvProCateg = (TextView) findViewById(R.id.tvProCateg);

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
        ArrayAdapter brandsArray = new ArrayAdapter(this, android.R.layout.simple_spinner_item, brands);
        brandsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        bSpin.setAdapter(brandsArray);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter categoryArray = new ArrayAdapter(this, android.R.layout.simple_spinner_item, category);
        categoryArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        cSpin.setAdapter(categoryArray);


        if (getIntent().getExtras() != null) {
            final int pid = getIntent().getExtras().getInt("ProductId");
            Log.w(TAG, "Intent received is pid " + pid);

            MstProducts mstProducts = db.getSingleProduct(pid);

            name.setText(mstProducts.getPname());
            descr.setText(mstProducts.getDescrip());
            qt.setText(""+mstProducts.getQuantity());
            qt.setFocusable(false);
            qt.setClickable(true);
            stAlert.setText(""+mstProducts.getStockAlert());

           // spinnerObject.setSelection(INDEX_OF_CATEGORY2)
            for(int k = 0; k < brands.length; k++)
            {
                Log.w(TAG, "onCreate: brand value "+brands[k]);
                if(brands[k].equals( mstProducts.getPbrand()))
                    bSpin.setSelection(k);
            }
            for(int k = 0; k < category.length; k++)
            {
                if(category[k].equals( mstProducts.getPcategory()))
                    cSpin.setSelection(k);
            }
            //bSpin.setSelection(2);

          /*  tvProCateg.setText("Selected Category:" + mstProducts.getPcategory());
            tvProBrand.setText("Selected Brand: " + mstProducts.getPbrand());*/


            pbrand =  mstProducts.getPbrand();
            pcategory = mstProducts.getPcategory();

            btAdd.setText("Update");

            btAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    pname = name.getText().toString();
                    descriptn = descr.getText().toString();
                    try {
                        quantity = Integer.parseInt(qt.getText().toString());
                        stAlQu = Integer.parseInt(stAlert.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(AddProduct.this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
                    }
                    if (pname.length() != 0 && descriptn.length() != 0 && pbrand.length() != 0 && pcategory.length() != 0) {

                        MstProducts mstProducts = new MstProducts(pid,pname, descriptn, pbrand, pcategory, quantity, stAlQu, 1);
                        db.updateProduct(mstProducts);

                        callToFragment();

                       /* MstProducts mstProducts = new MstProducts(pname, descriptn, pbrand, pcategory, quantity, stAlQu, 1);
                        db.addProduct(mstProducts);

                        int proId = db.getLastInsertedIDFromProduct();
                        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                        String username = shared.getString(Name, "");

                        MstTransaction mstTransaction = new MstTransaction(proId, username, "Purchase", quantity, 1, 1);
                        db.addTransaction(mstTransaction);

                        callToFragment();*/

                    } else
                        Toast.makeText(AddProduct.this, "Please fill each fields", Toast.LENGTH_LONG).show();
                }
            });

        } else {

            btAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    pname = name.getText().toString();
                    descriptn = descr.getText().toString();
                    try {
                        quantity = Integer.parseInt(qt.getText().toString());
                        stAlQu = Integer.parseInt(stAlert.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(AddProduct.this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
                    }

                    if (pname.length() != 0 && descriptn.length() != 0 && pbrand.length() != 0 && pcategory.length() != 0) {

                        if(pbrand ==  brands[0])
                            Toast.makeText(AddProduct.this, "Please select Product Brand.", Toast.LENGTH_SHORT).show();
                        else if (pcategory == category[0])
                            Toast.makeText(AddProduct.this, "Please select Product Category.", Toast.LENGTH_SHORT).show();
                        else {
                            Calendar c = Calendar.getInstance();
                            //Log.w(TAG, "onClick: Current time =>  "+ c.getTime());
                            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            String formattedDate = df.format(c.getTime());
                            Log.w(TAG, "onClick: Date "+formattedDate );

                            MstProducts mstProducts = new MstProducts(pname, descriptn, pbrand, pcategory, quantity, stAlQu, 1);
                            db.addProduct(mstProducts);

                            int proId = db.getLastInsertedIDFromProduct();

                            SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                            String username = shared.getString(Name, "");

                            MstTransaction mstTransaction = new MstTransaction(proId, username, "Purchase", formattedDate, quantity, 1, 1);
                            db.addTransaction(mstTransaction);

                            List<MstTransaction> totalTransList = db.getAllTransactions();
                            for (MstTransaction i : totalTransList) {
                                String log = "Id : " + i.getTid() + " , Name : " + i.getConcernedPname() + "PID : " + i.getPid() +
                                    "Type : " + i.getTtype() + "date : " + i.getTransDate() + "isparent : " + i.getIsparent() + "quantity : " + i.getTransQuantity();
                            Log.w(TAG, "Transaction info " + log);

                        }
                        callToFragment();
                    }
                    }
                    else
                        Toast.makeText(AddProduct.this, "Please fill each fields", Toast.LENGTH_LONG).show();
                }
            });
        }

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
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
            /*if (getIntent().getExtras() != null)
                tvProBrand.setText("Selected Brand: " + pbrand);*/
                Log.w(TAG, "onItemSelected: brand selected "+pbrand );
        }
        else if(spinner.getId() == R.id.catSp)
        {
            //do this
            pcategory = category[i];
           /* if (getIntent().getExtras() != null)
                tvProCateg.setText("Selected Category:" + pcategory);*/
            Log.w(TAG, "onItemSelected: category selected "+pcategory );
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
               /* Intent i = new Intent(AddProduct.this,Dashboard.class);
                i.putExtra("frgToLoad", FRAGMENT_P);
                startActivity(i);
                finish();//finishing activity*/
               callToFragment();
    }
}
