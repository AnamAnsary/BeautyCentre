package com.example.beautycentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstProducts;

/**
 * Created by vmplapp on 13/9/17.
 */

public class AddProduct  extends AppCompatActivity {

    EditText name,descr,qt;
    Button btAdd;
    String pname,descriptn;
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        name = (EditText) findViewById(R.id.name);
        descr = (EditText) findViewById(R.id.descr);
        qt = (EditText) findViewById(R.id.qt);
        btAdd = (Button) findViewById(R.id.btAdd);

        final DatabaseHandler db = new DatabaseHandler(this);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pname = name.getText().toString();
                descriptn = descr.getText().toString();
                quantity = Integer.parseInt(qt.getText().toString());

                if(pname.length() !=0 && descriptn.length() !=0 )
                {
                    MstProducts mstProducts = new MstProducts(pname,descriptn,quantity,quantity,1);
                    db.addProduct(mstProducts);
                    Intent i = new Intent(AddProduct.this,Product.class);
                    startActivity(i);
                    finish();//finishing activity

                }
                else
                Toast.makeText(AddProduct.this, "Please fill each fields", Toast.LENGTH_LONG).show();

            }
        });
    }
}
