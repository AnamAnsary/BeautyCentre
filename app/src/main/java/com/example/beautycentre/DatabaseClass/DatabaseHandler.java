package com.example.beautycentre.DatabaseClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.beautycentre.DatabaseTables.MstBranches;
import com.example.beautycentre.DatabaseTables.MstProducts;
import com.example.beautycentre.DatabaseTables.MstSalons;
import com.example.beautycentre.DatabaseTables.MstUsers;

import java.util.ArrayList;
import java.util.List;

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
    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_SALONS = "salons";
    private static final String TABLE_BRANCHES = "branches";

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


    //MstProduct Table column names
    private static final String KEY_PNAME = "productname";
    private static final String KEY_DESC = "description";
    private static final String KEY_INITQUAN = "initial_quantity";
    private static final String KEY_QUANTITY= "quantity";

    //MstSalon Table column names
    private static final String KEY_SNAME = "salonname";
    private static final String KEY_SDESC = "Sdescription";
    private static final String KEY_OWNER= "ownername";

    //MstBranch Table column names
    private static final String KEY_BRNAME= "branchName";
    private static final String KEY_BRADD= "branchAdd";
    private static final String KEY_BREMAIL= "branchEmail";
    private static final String KEY_BRCNTCT= "branchCntct";

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
                + KEY_ACTIVE + " INTEGER"
                + ")";

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PNAME + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_INITQUAN + " INTEGER,"
                + KEY_QUANTITY + " INTEGER,"
                + KEY_ACTIVE + " INTEGER"
                + ")";

        String CREATE_SALONS_TABLE = "CREATE TABLE " + TABLE_SALONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SNAME + " TEXT,"
                + KEY_SDESC + " TEXT,"
                + KEY_OWNER + " TEXT,"
                + KEY_ACTIVE + " INTEGER"
                + ")";

        String CREATE_BRANCHES_TABLE = "CREATE TABLE " + TABLE_BRANCHES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_BRNAME + " TEXT,"
                + KEY_BRADD + " TEXT,"
                + KEY_BREMAIL + " TEXT,"
                + KEY_BRCNTCT + " INTEGER,"
                + KEY_ACTIVE + " INTEGER"
                + ")";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_SALONS_TABLE);
        db.execSQL(CREATE_BRANCHES_TABLE);
        Log.w(TAG, "onCreate: Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BRANCHES);
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
        values.put(KEY_ACTIVE, mstUsers.getActive());

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        Log.w(TAG, "addUser: added user" );
        //Toast.makeText(context, "User Added", Toast.LENGTH_LONG).show();
        db.close(); // Closing database connection
    }

    public void addProduct(MstProducts mstProducts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PNAME, mstProducts.getPname()); // Contact Name
        values.put(KEY_DESC, mstProducts.getDescrip()); // Contact Phone
        values.put(KEY_INITQUAN, mstProducts.getIntial_quantity()); // Contact Name
        values.put(KEY_QUANTITY, mstProducts.getQuantity());
        values.put(KEY_ACTIVE, mstProducts.getActive());

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        Log.w(TAG, "addProduct: added product" );
        //Toast.makeText(context, "User Added", Toast.LENGTH_LONG).show();
        db.close(); // Closing database connection
    }


    public void addSalon(MstSalons mstSalons) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SNAME, mstSalons.getSname());
        values.put(KEY_SDESC, mstSalons.getDescrip());
        values.put(KEY_OWNER, mstSalons.getOwner_name());
        values.put(KEY_ACTIVE, mstSalons.getActive());

        // Inserting Row
        db.insert(TABLE_SALONS, null, values);
        Log.w(TAG, "addSalon: added salon" );
        //Toast.makeText(context, "User Added", Toast.LENGTH_LONG).show();
        db.close(); // Closing database connection
    }

    public void addBranch(MstBranches mstBranches) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BRNAME, mstBranches.getbName());
        values.put(KEY_BRADD, mstBranches.getBrAdd());
        values.put(KEY_BREMAIL, mstBranches.getBrEmail());
        values.put(KEY_BRCNTCT, mstBranches.getBrContact());
        values.put(KEY_ACTIVE, mstBranches.getActive());

        // Inserting Row
        db.insert(TABLE_SALONS, null, values);
        Log.w(TAG, "addBranch: added branch" );
        //Toast.makeText(context, "User Added", Toast.LENGTH_LONG).show();
        db.close(); // Closing database connection
    }

    // Getting single contact
    MstUsers getSingleUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, null,
                KEY_ID + "=?",new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        MstUsers userDetail = new MstUsers(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5), Integer.parseInt(cursor.getString(8)));
        // return contact
        return userDetail;
    }


    // Getting single contact
    MstProducts getSingleProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCTS, null,
                KEY_ID + "=?",new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        MstProducts prodDetail = new MstProducts(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
        // return contact
        return prodDetail;
    }

    MstSalons getSingleSalon(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SALONS, null,
                KEY_ID + "=?",new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        MstSalons salonDetail = new MstSalons(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2),cursor.getString(3),
               Integer.parseInt(cursor.getString(4)));
        // return contact
        return salonDetail;
    }

    MstBranches getSingleBranch (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BRANCHES, null,
                KEY_ID + "=?",new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        MstBranches branchDetail = new MstBranches (Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2),cursor.getString(3),
              cursor.getString(4), Integer.parseInt(cursor.getString(5)));
        // return contact
        return branchDetail;
    }



    public List<MstUsers> getAllUsers() {
        List<MstUsers> userList = new ArrayList<MstUsers>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MstUsers user = new MstUsers();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setFullname(cursor.getString(3));
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userList;

    }

    public List<MstProducts> getAllProducts() {
        List<MstProducts> prodList = new ArrayList<MstProducts>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MstProducts prod = new MstProducts();
                prod.setPid(Integer.parseInt(cursor.getString(0)));
                prod.setPname(cursor.getString(1));
                prod.setDescrip(cursor.getString(2));
                prod.setIntial_quantity(cursor.getInt(3));
                prod.setQuantity(cursor.getInt(4));
                // Adding contact to list
                prodList.add(prod);
            } while (cursor.moveToNext());
        }
        // return contact list
        return prodList;
    }

    public List<MstSalons> getAllSalons() {
        List<MstSalons> salonList = new ArrayList<MstSalons>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SALONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MstSalons salon = new MstSalons();
                salon.setSid(Integer.parseInt(cursor.getString(0)));
                salon.setSname(cursor.getString(1));
                salon.setDescrip(cursor.getString(2));
                salon.setOwner_name(cursor.getString(3));
                // Adding contact to list
                salonList.add(salon);
            } while (cursor.moveToNext());
        }

        // return contact list
        return salonList;

    }

    public List<MstBranches> getAllBranches() {
        List<MstBranches> branchList = new ArrayList<MstBranches>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BRANCHES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MstBranches branch = new MstBranches();
                branch.setBid(Integer.parseInt(cursor.getString(0)));
                branch.setbName(cursor.getString(1));
                branch.setBrAdd(cursor.getString(2));
                branch.setBrEmail(cursor.getString(3));
                branch.setBrContact(cursor.getString(4));
                // Adding contact to list
                branchList.add(branch);
            } while (cursor.moveToNext());
        }

        // return contact list
        return branchList;

    }


    // Getting contacts Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Getting contacts Count
    public int getProductsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    // Updating single contact
    public int updateUser(MstUsers mstUsers) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, mstUsers.getUsername());
        values.put(KEY_PASSWORD, mstUsers.getPassword());
        values.put(KEY_FULLNAME, mstUsers.getFullname());
        values.put(KEY_CONTACT, mstUsers.getContactno());
        values.put(KEY_EMAIL, mstUsers.getEmail());
        values.put(KEY_ACTIVE, mstUsers.getActive());

        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(mstUsers.getId()) });
    }

    // Updating single contact
    public int updateProduct(MstProducts mstProducts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PNAME, mstProducts.getPname());
        values.put(KEY_DESC, mstProducts.getDescrip());
        values.put(KEY_INITQUAN, mstProducts.getIntial_quantity());
        values.put(KEY_QUANTITY, mstProducts.getQuantity());

        // updating row
        return db.update(TABLE_PRODUCTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(mstProducts.getPid()) });
    }

    public int updateSalon(MstSalons mstSalons) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SNAME, mstSalons.getSname());
        values.put(KEY_SDESC, mstSalons.getDescrip());
        values.put(KEY_OWNER, mstSalons.getOwner_name());
        values.put(KEY_ACTIVE, mstSalons.getActive());

        // updating row
        return db.update(TABLE_SALONS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(mstSalons.getSid()) });
    }

    public int updateBranch(MstBranches mstBranches) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BRNAME, mstBranches.getbName());
        values.put(KEY_BRADD, mstBranches.getBrAdd());
        values.put(KEY_BREMAIL, mstBranches.getBrEmail());
        values.put(KEY_BRCNTCT, mstBranches.getBrContact());
        values.put(KEY_ACTIVE, mstBranches.getActive());

        // updating row
        return db.update(TABLE_BRANCHES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(mstBranches.getBid()) });
    }

    // Deleting single contact
    public void deleteUser(MstUsers mstUsers) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(mstUsers.getId()) });
        db.close();
    }

    // Deleting single contact
    public void deleteProduct(MstProducts mstProducts) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_ID + " = ?",
                new String[] { String.valueOf(mstProducts.getPid()) });
        db.close();
    }

    // Deleting single contact
    public void deleteSalon(MstSalons mstSalons) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SALONS, KEY_ID + " = ?",
                new String[] { String.valueOf(mstSalons.getSid()) });
        db.close();
    }

    // Deleting single contact
    public void deleteBranch(MstBranches mstBranches) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BRANCHES, KEY_ID + " = ?",
                new String[] { String.valueOf(mstBranches.getBid()) });
        db.close();
    }


    public MstUsers checkUser(String email, String pass) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERS, null, KEY_EMAIL + "=? AND " +KEY_PASSWORD + "=?", new String[] { email, pass }, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        MstUsers userDetail = new MstUsers(Integer.parseInt(cursor.getString(0)), cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5), Integer.parseInt(cursor.getString(6)));
        // return contact
        return userDetail;
    }
}
