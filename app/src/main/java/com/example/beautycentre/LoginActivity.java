package com.example.beautycentre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstProducts;
import com.example.beautycentre.DatabaseTables.MstSalons;
import com.example.beautycentre.DatabaseTables.MstUsers;

import java.util.HashMap;
import java.util.List;

import static com.example.beautycentre.Dashboard.Email;
import static com.example.beautycentre.Dashboard.Gender;
import static com.example.beautycentre.Dashboard.MyPREFERENCES;
import static com.example.beautycentre.Dashboard.Name;
import static com.example.beautycentre.Dashboard.Phone;
import static com.example.beautycentre.Dashboard.Pwd;

/**
 * Created by vmplapp on 11/9/17.
 */

public class LoginActivity  extends AppCompatActivity {

    MstUsers mstUser;
    EditText password;
    EditText email;
    Button login;

    SharedPreferences sharedpreferences;


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

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        List<MstUsers> users = db.getAllUsers();
        for (MstUsers ur : users) {
            String log = "Id: " +ur.getId() + " ,Name: " + ur.getFullname() + " ,Email: " +ur.getEmail();
            Log.w(TAG, "user is : " +log);

        }

        if(users.size() == 0)
        {
            Log.w(TAG, "onCreate: User table empty");

            mstUser = new MstUsers("Anam Ansari", "anam.ansary36@gmail.com","anam123", 0, "9168567787", 1);
            db.addUser(mstUser);
            mstUser = new MstUsers("Zain Ansari", "anam.ansari36@yahoo.in","anamyahoo", 1, "0123456789", 1);
            db.addUser(mstUser);
            mstUser = new MstUsers("Aariz Ansari", "anamansary.developer@gmail.com","anamgmail", 1, "9876543210", 1);
            db.addUser(mstUser);

        }

      /*  List<MstUsers> users2 = db.getAllUsers();
        for (MstUsers ur : users2) {
            String log = "Id: " +ur.getId() + " ,Name: " + ur.getFullname() + " ,Email: " +ur.getEmail();
            Log.w(TAG, "user2 is : " +log);

        }*/

       /* mstProducts = new MstProducts("Garnier Shampoo","Shampoo for long hair",10, 15,1);
        db.addProduct(mstProducts);
        mstProducts = new MstProducts("XYZ Hair Straightener","Hair Straightener",4, 12,1);
        db.addProduct(mstProducts);
        mstProducts = new MstProducts("Lotus Bleach Cream","Bleach Cream",8, 8,1);
        db.addProduct(mstProducts);
*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lemail = email.getText().toString();
                Lpass = password.getText().toString();
                Log.w(TAG, "onClick: email and password is "+Lemail+" "+Lpass);
                if (Lemail.length() != 0 && Lpass.length() != 0)
                {
                    try {
                        mstUser = db.checkUser(Lemail, Lpass);
                        Toast.makeText(LoginActivity.this, "FullName, contact no and usertype is " + mstUser.getFullname() + " " + mstUser.getContactno(), Toast.LENGTH_LONG).show();

                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(Name,  mstUser.getFullname());
                        editor.putString(Phone, mstUser.getContactno());
                        editor.putInt(String.valueOf(Gender), mstUser.getGender());
                        editor.putString(Email, Lemail);
                        editor.putString(Pwd, Lpass);
                        editor.commit();


                        Intent intent = new Intent(LoginActivity.this,Dashboard.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                      /*  intent.putExtra("email",mstUser.getEmail());
                        intent.putExtra("fullname",mstUser.getFullname());*/
                        startActivity(intent);
                        finish();

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