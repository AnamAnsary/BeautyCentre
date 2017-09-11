package com.example.beautycentre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstUsers;

/**
 * Created by vmplapp on 11/9/17.
 */

public class LoginActivity  extends AppCompatActivity {

    MstUsers mstUser;
    EditText password;
    EditText email;
    Button login;


    String Lemail;
    String Lpass;

    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final DatabaseHandler db = new DatabaseHandler(this);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pwd);
        login = (Button) findViewById(R.id.btnlogin);


        mstUser = new MstUsers("Anam Ansari", "anam.ansary36@gmail.com","anam123", "AnamAnsary","9168567787","distributor", 1);
        db.addUser(mstUser);
        mstUser = new MstUsers("Zain Ansari", "anam.ansari36@yahoo.in","anamyahoo", "ZainAnsary","0123456789","distributor", 1);
        db.addUser(mstUser);
        mstUser = new MstUsers("Aariz Ansari", "anamansary.developer@gmail.com","anamgmail", "AarizAnsary","9876543210","distributor", 1);
        db.addUser(mstUser);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lemail = email.getText().toString();
                Lpass = password.getText().toString();
                Log.w(TAG, "onClick: email and password is "+Lemail+" "+password);
                if (Lemail.length() != 0 && Lpass.length() != 0)
                {
                    try {
                        MstUsers user = db.checkUser(Lemail, Lpass);
                        Toast.makeText(LoginActivity.this, "FullName, contact no and usertype is " + user.getFullname() + " " + user.getContactno() + " " + user.getUsertype(), Toast.LENGTH_LONG).show();

                    }
                    catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "No such user found", Toast.LENGTH_LONG).show();
                    }
                }
                else
                    Toast.makeText(LoginActivity.this, "Please fill each fields", Toast.LENGTH_LONG).show();

            }
        });
    }
}