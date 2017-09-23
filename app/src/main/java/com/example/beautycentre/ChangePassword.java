package com.example.beautycentre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstUsers;

import java.util.List;

import static com.example.beautycentre.Dashboard.Email;
import static com.example.beautycentre.Dashboard.Gender;
import static com.example.beautycentre.Dashboard.ID;
import static com.example.beautycentre.Dashboard.MyPREFERENCES;
import static com.example.beautycentre.Dashboard.Name;
import static com.example.beautycentre.Dashboard.Phone;
import static com.example.beautycentre.Dashboard.Pwd;

/**
 * Created by vmplapp on 21/9/17.
 */

public class ChangePassword extends AppCompatActivity {

    EditText oldP,newP,cNewP;
    Button btncp;

    SharedPreferences sharedpreferences;

    String oldpwd,newpwd,cnpwd;

    private static final String TAG = "ChangePassword";
    private MstUsers mstUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepwd);

        final DatabaseHandler db = new DatabaseHandler(this);

        oldP = (EditText) findViewById(R.id.oldP);
        newP = (EditText) findViewById(R.id.newP);
        cNewP = (EditText) findViewById(R.id.cNewP);
        btncp = (Button) findViewById(R.id.btncp);

        btncp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldpwd = oldP.getText().toString();
                newpwd = newP.getText().toString();
                cnpwd = cNewP.getText().toString();

                if(oldpwd.length() != 0 && newpwd.length() != 0 && cnpwd.length() != 0)
                {
                    SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    int id = shared.getInt(ID,0);
                    String name = shared.getString(Name,"");
                    String email = shared.getString(Email,"");
                    String phone = shared.getString(Phone,"");
                    String oldPStored = (shared.getString(Pwd, ""));
                   // String gender = shared.getInt(Gender,"");
                    Log.w(TAG, "onClick: old pwd stored "+oldPStored );

                    if(oldpwd.equals(oldPStored))
                    {
                        if(newpwd.equals(cnpwd)) {

                            db.updatePassword(id,newpwd);
                            List<MstUsers> users2 = db.getAllUsers();
                            for (MstUsers ur : users2) {
                                String log = "Id: " + ur.getId() + " ,Name: " + ur.getFullname() + " ,Email: " + ur.getEmail() + " ,New Password : "+ ur.getPassword();
                                Log.w(TAG, "user2 is : " + log);
                            }


                            Snackbar.make(view,"Updated Password!",Snackbar.LENGTH_LONG).show();
                            Intent i = new Intent(ChangePassword.this,Dashboard.class);
                            startActivity(i);
                            finish();//finishing activity


                        }
                        else {
                            Snackbar.make(view,"Passwords do not match!",Snackbar.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Snackbar.make(view,"Your old password entered is wrong!",Snackbar.LENGTH_LONG).show();
                    }
                }
                else
                    Snackbar.make(view,"Please fill each field!",Snackbar.LENGTH_LONG).show();
            }

        });

    }
}
