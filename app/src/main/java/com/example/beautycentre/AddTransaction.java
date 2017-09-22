package com.example.beautycentre;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.support.v4.app.DialogFragment;

/**
 * Created by vmplapp on 22/9/17.
 */

public class AddTransaction extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "AddTransaction";
    public static final String FRAGMENT_I = "Fragment_Inventory";
    TextView tvStoreVndr;
    AutoCompleteTextView actv;
    static EditText etTransDate;
    static EditText etExpDate;
    EditText qty;
    EditText textLayoutExpDate;
    EditText remarks;
    Spinner sptransType, spStatus, spStoreVndr;
    Button btnsave,btBack;

    private DatePickerDialogFragment mDatePickerDialogFragment;

    private Calendar calendar;
    private int year, month, day;
    private String dateSelected;

    String trans_type;

    String[] type = { "--Select--", "Sales","Purchase"};
    String[] status;
    //String[] stSales = { "--Select--", "Dispatched","Booked"};
    //String[] stPurchase = { "--Select--", "Cleared","In-Transit"};
    String[] storeOrVendorList;
    /*= { "--Select--", "Andheri","Crawford Market", "Mulund", "Thane"};
    String[] vendorList = { "--Select--", "Abc","Jkl", "Mno","Pqr", "Xyz"};*/
    private String Status_selected;
    private String storeOrVendor_selected;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trans);

        tvStoreVndr = (TextView) findViewById(R.id.tvStoreVndr);
        actv= (AutoCompleteTextView)findViewById(R.id.autoCompleteTVProduct);

        etTransDate = (EditText) findViewById(R.id.etTransDate);
        etExpDate = (EditText) findViewById(R.id.etExpDate);
        //textLayoutExpDate = (EditText) findViewById(R.id.textLayoutExpDate);
        qty = (EditText) findViewById(R.id.qty);
        remarks = (EditText) findViewById(R.id.remarks);

        spStatus = (Spinner) findViewById(R.id.spStatus);
        sptransType = (Spinner) findViewById(R.id.sptransType);
        spStoreVndr = (Spinner) findViewById(R.id.spStoreVndr);

        btBack = (Button) findViewById(R.id.btBack);
        btnsave = (Button) findViewById(R.id.btnsave);

        etTransDate.setOnClickListener(this);
        etExpDate.setOnClickListener(this);

        mDatePickerDialogFragment = new DatePickerDialogFragment();


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter typeArray = new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        typeArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        sptransType.setAdapter(typeArray);
        sptransType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                trans_type = type[i];
                if(trans_type.equals(null))
                    Toast.makeText(AddTransaction.this,"Please select Transaction Type",Toast.LENGTH_LONG).show();
                else if(i == 1) {
                    calltoSales();
                }
                else if(i == 2)
                    calltoPurchase();

                Log.w(TAG, "onItemSelected:  Transaction Type selected "+ type[i] );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

       /* textLayoutExpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
            }
        });*/
    }

  /*  @SuppressWarnings("deprecation")
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    dateSelected = arg3+"/"+(arg2+1)+"/"+arg1;
                    Log.w(TAG, "onDateSet: "+dateSelected );

                    //showDate(arg1, arg2+1, arg3);
                }
            };*/
  /*  //onItemClick
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        *//*if(spinner.getId() == R.id.sptransType)
        {
            trans_type = type[i];
            if(i == 1) {
                calltoSales();
            }
            else if(i == 2)
                calltoPurchase();
            else
                Toast.makeText(this,"Please select Transaction Type",Toast.LENGTH_LONG).show();

            Log.w(TAG, "onItemSelected:  Transaction Type selected "+ type[i] );
        }
        else *//*
        if(spinner.getId() == R.id.spStatus)
        {
            if(i==0)
                Toast.makeText(this,"Please select Status of Transaction",Toast.LENGTH_LONG).show();
            else
                Status_selected = status[i];
            Log.w(TAG, "onItemSelected: Status_selected is "+Status_selected );
        }
        else if(spinner.getId() == R.id.spStoreVndr)
        {
            if(i==0)
                Toast.makeText(this,"Please select Store or Vendor",Toast.LENGTH_LONG).show();
            else
                storeOrVendor_selected = storeOrVendorList[i];
            Log.w(TAG, "onItemSelected: storeOrVendor_selected is "+storeOrVendor_selected );
        }

    }*/

    private void calltoPurchase() {
        //STATUS AND VENDOR TO SHOW WHEN TRANSACTION TYPE IS PURCHASE


        status = new String[]{"--Select--", "Cleared", "In-Transit"};
        storeOrVendorList = new String[]{"--Select--", "Abc", "Jkl", "Mno", "Pqr", "Xyz"};
        tvStoreVndr.setText("Vendor");
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter statusPurchaseArr = new ArrayAdapter(this,android.R.layout.simple_spinner_item,status);
        statusPurchaseArr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spStatus.setAdapter(statusPurchaseArr);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter purchase_vendorsArray = new ArrayAdapter(this,android.R.layout.simple_spinner_item,storeOrVendorList);
        purchase_vendorsArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spStoreVndr.setAdapter(purchase_vendorsArray);

        callListeners();


    }

    private void callListeners() {
        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spStoreVndr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                storeOrVendor_selected = storeOrVendorList[i];
                if(storeOrVendor_selected.equals(null))
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

        status = new String[]{"--Select--", "Dispatched", "Booked"};
        storeOrVendorList = new String[]{"--Select--", "Andheri", "Crawford Market", "Mulund", "Thane"};
        tvStoreVndr.setText("Store");
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter statusSalesArr = new ArrayAdapter(this,android.R.layout.simple_spinner_item,status);
        statusSalesArr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spStatus.setAdapter(statusSalesArr);
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


    public static class DatePickerDialogFragment extends DialogFragment implements
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
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (flag == FLAG_START_DATE) {
                etTransDate.setText(format.format(calendar.getTime()));
            } else if (flag == FLAG_END_DATE) {
                etExpDate.setText(format.format(calendar.getTime()));
            }
        }
    }

    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(AddTransaction.this,Dashboard.class);
        i.putExtra("frgToLoad", FRAGMENT_I);
        startActivity(i);
        finish();//finishing activity
    }
}

  /*  @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }**/
