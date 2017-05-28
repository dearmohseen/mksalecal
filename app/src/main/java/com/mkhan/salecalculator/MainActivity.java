package com.mkhan.salecalculator;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    AdView mAdView1;
    private Configuration config;
    private int width, height;
    public SharedPreferences sharedPref;

    Button btn10;
    Button btn20;
    Button btn30;
    Button btn40;
    Button btn50;
    Button btn60;
    Button btn70;
    Button btn80;
    Button btn90;
    Button btnCalculatePercentage;

    EditText initialPrice;
    EditText otherPercentage;
    TextView txtFinalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        config = getResources().getConfiguration();
        width = config.screenWidthDp;
        height = config.screenHeightDp;

        initializeAdUnit();

        initializeButtons();
    }

    public boolean validate(String btnText){
        boolean isValid = true;
        if(initialPrice.getText().length() == 0){
            Utility.displayToast(getApplicationContext(),"Please Enter Initial Price");
            isValid = false;
        } else if("calculate".equalsIgnoreCase(btnText) && otherPercentage.getText().length() == 0){
            Utility.displayToast(getApplicationContext(),"Please Enter Other Percentage");
            isValid = false;
        }

        return isValid;
    }

    public void calculateOtherPercentage(Double initialPriceDbl,Double otherPrcntDbl ){
        Double finalValue = initialPriceDbl - (initialPriceDbl/100)*otherPrcntDbl;
        updateUI(String.valueOf(finalValue));
    }

    public void updateUI(String finalValue){
        System.out.println(" Mohseen : update ui Price " + finalValue);

        if(finalValue.indexOf(".") > 0){
            txtFinalPrice.setText(Utility.formatStringUpto2DigitDecimal(finalValue));
        } else {
            txtFinalPrice.setText(finalValue);
        }

    }

    public void calculatePercentage(View v){
        Button b = (Button)v;
        String btnText = b.getText().toString();
        if(validate(btnText)){
            double initialPriceDbl = Double.parseDouble(initialPrice.getText().toString());
            double otherPrcntDbl = 0.0;
            if("calculate".equalsIgnoreCase(btnText)){
                otherPrcntDbl = Double.parseDouble(otherPercentage.getText().toString());
            } else if(btnText.contains("10")) {
                otherPrcntDbl = 10;
            } else if(btnText.contains("20")) {
                otherPrcntDbl = 20;
            } else if(btnText.contains("30")) {
                otherPrcntDbl = 30;
            }else if(btnText.contains("40")) {
                otherPrcntDbl = 40;
            }else if(btnText.contains("50")) {
                otherPrcntDbl = 50;
            }else if(btnText.contains("60")) {
                otherPrcntDbl = 60;
            }else if(btnText.contains("70")) {
                otherPrcntDbl = 70;
            }else if(btnText.contains("80")) {
                otherPrcntDbl = 80;
            }else if(btnText.contains("90")) {
                otherPrcntDbl = 90;
            }

            calculateOtherPercentage(initialPriceDbl,otherPrcntDbl);
            //System.out.println(" Mohseen : initial Price " + initialPrice.getText());
        }

    }

    public void initializeButtons(){

        initialPrice = (EditText)findViewById(R.id.editTextInitial);
        otherPercentage = (EditText)findViewById(R.id.edittextOther);

        txtFinalPrice = (TextView) findViewById(R.id.txtFinalPrice);

        btn10 = (Button) findViewById(R.id.btn10);
        btn10.setOnClickListener(new MyButtonListener());
        btn20 = (Button) findViewById(R.id.btn20);
        btn20.setOnClickListener(new MyButtonListener());
        btn30 = (Button) findViewById(R.id.btn30);
        btn30.setOnClickListener(new MyButtonListener());
        btn40 = (Button) findViewById(R.id.btn40);
        btn40.setOnClickListener(new MyButtonListener());
        btn50 = (Button) findViewById(R.id.btn50);
        btn50.setOnClickListener(new MyButtonListener());
        btn60 = (Button) findViewById(R.id.btn60);
        btn60.setOnClickListener(new MyButtonListener());
        btn70 = (Button) findViewById(R.id.btn70);
        btn70.setOnClickListener(new MyButtonListener());
        btn80 = (Button) findViewById(R.id.btn80);
        btn80.setOnClickListener(new MyButtonListener());
        btn90 = (Button) findViewById(R.id.btn90);
        btn90.setOnClickListener(new MyButtonListener());
        btnCalculatePercentage = (Button) findViewById(R.id.btnCalulate);
        btnCalculatePercentage.setOnClickListener(new MyButtonListener());

    }

    public class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            calculatePercentage(v);
        }
    }
    private void initializeAdUnit() {
        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_ad_unit_id));
        mAdView1 = (AdView) findViewById(R.id.adView);
        //mAdView1.setVisibility(View.INVISIBLE);

        AdRequest adRequest = new AdRequest.Builder().build();
        adRequest.isTestDevice(this);
        mAdView1.loadAd(adRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView1.resume();
        //System.out.println("Mohseen : MainActivity Resume");
    }

    @Override
    protected void onPause() {
        mAdView1.pause();
        super.onPause();
    }
}
