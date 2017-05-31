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
    TextView txtInitialPrice;
    TextView txtFinalLabel;
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

        setTextSizes();
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

        txtInitialPrice = (TextView) findViewById(R.id.txtInitialPrice);
        txtFinalPrice = (TextView) findViewById(R.id.txtFinalPrice);
        txtFinalLabel = (TextView) findViewById(R.id.txtFinalLabel);

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

    private void setTextSizes(){

        System.out.println("Mohseen : setTextSizes " + width + " : " +height + " Orientation : " + config.orientation);
        final float scale = this.getResources().getDisplayMetrics().density;
        if(config.orientation == 1) {

            if (width > 500 && height > 600) {
                updateTextSize(scale,34);
                updateButtonSize(scale,180,100);
            } else if(width > 310 && height > 500){
                updateTextSize(scale,24);
                updateButtonSize(scale,100,60);
            }
        } else {
            if (width > 700 && height > 450) {
                updateTextSize(scale,34);
                updateButtonSize(scale,180,90);
            } else if (width > 500 && height >= 275 && height < 310) {
                updateTextSize(scale,24);
                updateButtonSize(scale,100,45);
            } else if (width > 500 && height >= 310 && height < 450) {
                updateTextSize(scale,24);
                updateButtonSize(scale,100,55);
            }
        }

    }

    public void updateTextSize(float scale,int size){
        //int size  = txtSize;//(int) (txtSize * scale + 0.5f);;
        txtInitialPrice.setTextSize(size-4);
        txtFinalLabel.setTextSize(size-4);

        txtFinalPrice.setTextSize(size);

        initialPrice.setTextSize(size-4);
        otherPercentage.setTextSize(size-4);
        btn10.setTextSize(size);
        btn20.setTextSize(size);
        btn30.setTextSize(size);
        btn40.setTextSize(size);
        btn50.setTextSize(size);
        btn60.setTextSize(size);
        btn70.setTextSize(size);
        btn80.setTextSize(size);
        btn90.setTextSize(size);
        btnCalculatePercentage.setTextSize(size);
    }

    public void updateButtonSize(float scale,int width, int height){

        int btnSize = (int) (width * scale + 0.5f);
        int btnHeight = (int) (height * scale + 0.5f);
        btn10.getLayoutParams().width = btnSize;
        btn10.getLayoutParams().height = btnHeight;
        btn20.getLayoutParams().width = btnSize;
        btn20.getLayoutParams().height = btnHeight;
        btn30.getLayoutParams().width = btnSize;
        btn30.getLayoutParams().height = btnHeight;
        btn40.getLayoutParams().width = btnSize;
        btn40.getLayoutParams().height = btnHeight;
        btn50.getLayoutParams().width = btnSize;
        btn50.getLayoutParams().height = btnHeight;
        btn60.getLayoutParams().width = btnSize;
        btn60.getLayoutParams().height = btnHeight;
        btn70.getLayoutParams().width = btnSize;
        btn70.getLayoutParams().height = btnHeight;
        btn80.getLayoutParams().width = btnSize;
        btn80.getLayoutParams().height = btnHeight;
        btn90.getLayoutParams().width = btnSize;
        btn90.getLayoutParams().height = btnHeight;

        if(config.orientation == 2){
            btnCalculatePercentage.getLayoutParams().height = btnHeight - 10;
        } else {
            btnCalculatePercentage.getLayoutParams().height = btnHeight - 50;
        }

        //initialPrice.getLayoutParams().width = btnSize;

        initialPrice.getLayoutParams().height = btnCalculatePercentage.getLayoutParams().height;
        otherPercentage.getLayoutParams().width = btnSize;
        otherPercentage.getLayoutParams().height = btnCalculatePercentage.getLayoutParams().height;
        btnCalculatePercentage.getLayoutParams().width = (int) ((width + 30) * scale + 0.5f);
        initialPrice.getLayoutParams().width = btnCalculatePercentage.getLayoutParams().width ;
        //btnCalculatePercentage.getLayoutParams().height = btnHeight-20;
    }

    private class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            calculatePercentage(v);
        }
    }
    private void initializeAdUnit() {
        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_ad_unit_id));
        mAdView1 = (AdView) findViewById(R.id.adView);
        mAdView1.setVisibility(View.INVISIBLE);

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

    @Override
    protected void onDestroy() {
        mAdView1.destroy();
        super.onDestroy();
    }
}
