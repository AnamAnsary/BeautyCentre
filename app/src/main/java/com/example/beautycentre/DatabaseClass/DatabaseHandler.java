package com.example.beautycentre.DatabaseClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.beautycentre.DatabaseTables.MstUsers;

/**
 * Created by vmplapp on 11/9/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper{


    private static final String TAG = "DatabaseHandler";
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BeautyCentreDB";

    // Contacts table name
    private static final String TABLE_USERS = "users";

    //Common columns
    private static final String KEY_ID = "id";
    private static final String KEY_ACTIVE = "active";


    // Mst_Users Table - column names
    //private static final String KEY_USERID = "userId";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_FULLNAME = "fullname";
    private static final String KEY_CONTACT = "contactno";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERTYPE = "usertype";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FULLNAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_USERNAME + " INTEGER,"
                + KEY_CONTACT + " TEXT,"
                + KEY_USERTYPE + " TEXT,"
                + KEY_ACTIVE + " INTEGER"
                + ")";

        db.execSQL(CREATE_USERS_TABLE);
        Log.w(TAG, "onCreate: Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        Log.w(TAG, "onUpgrade: Database upgrade" );
        // Create tables again
        onCreate(db);
    }

    public void addUser(MstUsers mstUsers) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, mstUsers.getUsername()); // Contact Name
        values.put(KEY_PASSWORD, mstUsers.getPassword()); // Contact Phone
        values.put(KEY_FULLNAME, mstUsers.getFullname()); // Contact Name
        values.put(KEY_CONTACT, mstUsers.getContactno());
        values.put(KEY_EMAIL, mstUsers.getEmail()); // Contact Name
        values.put(KEY_USERTYPE, mstUsers.getUsertype());
        values.put(KEY_ACTIVE, mstUsers.getActive());

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        Log.w(TAG, "addUser: added user" );
        //Toast.makeText(context, "User Added", Toast.LENGTH_LONG).show();
        db.close(); // Closing database connection
    }

    public MstUsers checkUser(String email, String pass) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERS, null, KEY_EMAIL + "=? AND " +KEY_PASSWORD + "=?", new String[] { email, pass }, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        MstUsers userDetail = new MstUsers(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5),cursor.getString(6), Integer.parseInt(cursor.getString(7)));
        // return contact
        return userDetail;
    }
}
