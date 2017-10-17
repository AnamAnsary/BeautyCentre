package com.example.beautycentre;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.support.v4.app.DialogFragment;

import com.example.beautycentre.DatabaseClass.DatabaseHandler;
import com.example.beautycentre.DatabaseTables.MstProducts;
import com.example.beautycentre.DatabaseTables.MstSalons;
import com.example.beautycentre.DatabaseTables.MstTransaction;

import static com.example.beautycentre.Dashboard.MyPREFERENCES;
import static com.example.beautycentre.Dashboard.Name;

/**
 * Created by vmplapp on 22/9/17.
 */

public class AddTransaction extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "AddTransaction";
    public static final String FRAGMENT_I = "Fragment_Inventory";
    TextView tvStoreVndr;
    static EditText etTransDate;
    static EditText etExpDate;
    EditText qty;
    EditText remarks;
    Spinner spProduct,sptransType, spStoreVndr;
    Button btnsave,btBack;

    private DatePickerDialogFragment mDatePickerDialogFragment;

    private ArrayList<String> Pnamelist;
    private ArrayList<Integer> PIdlist;

    //private ArrayList<MstProducts> PId_nameList;
    String trans_type;
    String[] type = { "--Select--", "Sales","Purchase"};
    private int pos2;

    //String[] stSales = { "--Select--", "Dispatched","Booked"};
    //String[] stPurchase = { "--Select--", "Cleared","In-Transit"};
    String[] storeOrVendorList;
    /*= { "--Select--", "Andheri","Crawford Market", "Mulund", "Thane"};
    String[] vendorList = { "--Select--", "Abc","Jkl", "Mno","Pqr", "Xyz"};*/
    private String Payment_status;
    private String storeOrVendor_selected;
    String tDate,eDate,tRemark;
    int tQnty;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trans);

        tvStoreVndr = (TextView) findViewById(R.id.tvStoreVndr);

        etTransDate = (EditText) findViewById(R.id.etTransDate);
        etExpDate = (EditText) findViewById(R.id.etExpDate);
        //textLayoutExpDate = (EditText) findViewById(R.id.textLayoutExpDate);
        qty = (EditText) findViewById(R.id.qty);
        remarks = (EditText) findViewById(R.id.remarks);

        spProduct = (Spinner) findViewById(R.id.spProduct);
        sptransType = (Spinner) findViewById(R.id.sptransType);
        spStoreVndr = (Spinner) findViewById(R.id.spStoreVndr);

        btBack = (Button) findViewById(R.id.btBack);
        btnsave = (Button) findViewById(R.id.btnsave);

        etTransDate.setOnClickListener(this);
        etExpDate.setOnClickListener(this);

        mDatePickerDialogFragment = new DatePickerDialogFragment();


        final DatabaseHandler db = new DatabaseHandler(this);

        //PId_nameList = new ArrayList<MstProducts>();
        Pnamelist = new ArrayList<String>();
        PIdlist = new ArrayList<Integer>();
        List<MstProducts> product = db.getAllProducts();
        for (MstProducts pr : product) {
            //PId_nameList.add(new MstProducts(pr.getPid(),pr.getPname()));
            Pnamelist.add(pr.getPname());
            PIdlist.add(pr.getPid());
        }

        if(Pnamelist.size() == 0){
            Toast.makeText(AddTransaction.this, "There are no products available! Please add Products first", Toast.LENGTH_SHORT).show();
        }

        else {
       /* ArrayAdapter<MstProducts> proadapter = new ArrayAdapter<MstProducts>
                (this,android.R.layout.select_dialog_item, (List<MstProducts>) PId_nameList.get(0));
        spProduct.setAdapter(proadapter);
*/

            ArrayAdapter proadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Pnamelist);
            spProduct.setAdapter(proadapter);

            //Creating the ArrayAdapter instance having the country list
            ArrayAdapter typeArray = new ArrayAdapter(this, android.R.layout.simple_spinner_item, type);
            typeArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            sptransType.setAdapter(typeArray);


            btnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    tDate = etTransDate.getText().toString();
                    eDate = etExpDate.getText().toString();
                    tRemark = remarks.getText().toString();

                    try {
                        tQnty = Integer.parseInt(qty.getText().toString());
                    } catch (NumberFormatException ne) {
                        Toast.makeText(AddTransaction.this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
                    }

                    if (pos2 != -1 && trans_type != null && storeOrVendor_selected != null && tDate != null && eDate != null && tQnty != 0) {
                        Log.w(TAG, "onClick: Add to transactions table!");

                        if (trans_type == "Sales")
                            Payment_status = "Booked";
                        else if (trans_type == "Purchase")
                            Payment_status = "Cleared";

                        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                        String username = shared.getString(Name, "");

                        MstTransaction mstTransaction = new MstTransaction(pos2, username, trans_type, Payment_status, tDate, eDate, tQnty, 0, 1);
                        db.addTransaction(mstTransaction);

                        List<MstTransaction> totalTransList = db.getAllTransactions();
                        for (MstTransaction i : totalTransList) {
                            String log = "Id : " + i.getTid() + " , Name : " + i.getConcernedPname() + "PID : " + i.getPid() +
                                    "Type : " + i.getTtype() + "date : " + i.getTransDate() + "isparent : " + i.getIsparent() + "quantity : " + i.getTransQuantity();
                            Log.w(TAG, "Transaction info " + log);
                        }

                        callToFragment();
                    } else
                        Toast.makeText(AddTransaction.this, "You have missed something! Please provide missing data.", Toast.LENGTH_SHORT).show();


                }
            });
        }

            btBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callToFragment();
                }
            });
            sptransType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    trans_type = type[i];
                    if (trans_type.equals(null) || i == 0)
                        Toast.makeText(AddTransaction.this, "Please select Transaction Type", Toast.LENGTH_LONG).show();
                    else if (i == 1) {
                        calltoSales();
                    } else if (i == 2)
                        calltoPurchase();

                    Log.w(TAG, "onItemSelected:  Transaction Type selected " + type[i]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                    String selection = (String) adapterView.getItemAtPosition(pos);
                    pos2 = -1;
                    Log.w(TAG, "onItemClick: selection is " + selection);
                    for (int i = 0; i < Pnamelist.size(); i++) {
                        if (Pnamelist.get(i).equals(selection)) {
                            pos2 = PIdlist.get(i);
                            //pos2 = i + 1;
                            Log.w(TAG, "onItemClick: pos2 is " + pos2);
                            break;
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
    }

    private void callToFragment() {
        Intent i = new Intent(AddTransaction.this,Dashboard.class);
        i.putExtra("frgToLoad", FRAGMENT_I);
        startActivity(i);
        finish();//finishing activity
    }


    private void calltoPurchase() {
        //STATUS AND VENDOR TO SHOW WHEN TRANSACTION TYPE IS PURCHASE
        //  status = new String[]{"--Select--", "Cleared", "In-Transit"};
        storeOrVendorList = new String[]{"--Select--", "Abc", "Jkl", "Mno", "Pqr", "Xyz"};
        tvStoreVndr.setText("Vendor");

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter purchase_vendorsArray = new ArrayAdapter(this, android.R.layout.simple_spinner_item, storeOrVendorList);
        purchase_vendorsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spStoreVndr.setAdapter(purchase_vendorsArray);

        callListeners();

    }

    private void callListeners() {
        /*spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Status_selected = status[i];
                    if(Status_selected.equals(null))
                        Toast.makeText(AddTransaction.this,"Please select Status of Transaction",Toast.LENGTH_LONG).show();
                Log.w(TAG, "onItemSelected: Status_selected is "+Status_selected );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
*/
        spStoreVndr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                storeOrVendor_selected = storeOrVendorList[i];
                if(storeOrVendor_selected.equals(null) || i==0 )
                    Toast.makeText(AddTransaction.this,"Please select Store or Vendor",Toast.LENGTH_LONG).show();
                Log.w(TAG, "onItemSelected: storeOrVendor_selected is "+storeOrVendor_selected );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void calltoSales() {
        //STATUS and STORE LIST TO SHOW WHEN TRANSACTION TYPE IS SALES

       // status = new String[]{"--Select--", "Dispatched", "Booked"};
        storeOrVendorList = new String[]{"--Select--", "Andheri", "Crawford Market", "Mulund", "Thane"};
        tvStoreVndr.setText("Store");

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter sales_storesArray = new ArrayAdapter(this,android.R.layout.simple_spinner_item,storeOrVendorList);
        sales_storesArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spStoreVndr.setAdapter(sales_storesArray);

        callListeners();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.etTransDate) {
            mDatePickerDialogFragment.setFlag(DatePickerDialogFragment.FLAG_START_DATE);
            mDatePickerDialogFragment.show(getSupportFragmentManager(), "datePicker");
        } else if (id == R.id.etExpDate) {
            mDatePickerDialogFragment.setFlag(DatePickerDialogFragment.FLAG_END_DATE);
            mDatePickerDialogFragment.show(getSupportFragmentManager(), "datePicker");
        }
    }



    public static class  DatePickerDialogFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        public static final int FLAG_START_DATE = 0;
        public static final int FLAG_END_DATE = 1;

        private int flag = 0;

            @Override
            public Dialog onCreateDialog (Bundle savedInstanceState){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void setFlag(int i) {
            flag = i;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            if (flag == FLAG_START_DATE) {
                etTransDate.setText(format.format(calendar.getTime()));
            } else if (flag == FLAG_END_DATE) {
                etExpDate.setText(format.format(calendar.getTime()));
            }
        }
    }

    public void onBackPressed() {
       callToFragment();
    }
}