package com.example.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MonthlyPaymentCalculatorActivity extends AppCompatActivity {



    private EditText principal;
    private EditText annualRate;
    private EditText period;

    private TextView numOfPayments;
    private TextView mp;
    private TextView totalForPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motghly_payment_calculator);


        this.numOfPayments = findViewById(R.id.txtNumOfPayments);
        this.mp = findViewById(R.id.txtMonthlyPayments);
        this.totalForPeriod = findViewById(R.id.txtTotal);


        Button btnPeriodCalActivity = findViewById(R.id.btnPeriodCal);
        btnPeriodCalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PeriodCalculatorActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Button btnCalculateMonthlyPayment = findViewById(R.id.btnCalculateMonthlyPayment);
        btnCalculateMonthlyPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initEditText();

                if (!getPrincipal().getText().toString().isEmpty() &&
                        !getAnnualRate().getText().toString().isEmpty() &&
                        !getPeriod().getText().toString().isEmpty()) {

                    double principal = Double.parseDouble(getPrincipal().getText().toString());
                    double annualRate = Double.parseDouble(getAnnualRate().getText().toString());
                    double periodInyears = Double.parseDouble(getPeriod().getText().toString());

                    if(principal > 0 && principal <= 1000000000 &&
                            annualRate > 0 && annualRate <= 100
                            && periodInyears > 0){
                        DecimalFormat decimalFormat = new DecimalFormat("##.##");

                        PaymentCalculator paymentCalculator = new PaymentCalculator(principal, annualRate);
                        paymentCalculator.setPeriod(periodInyears);

                        String numPayments = (paymentCalculator.getNumberOfPayments()) + "";
                        numOfPayments.setText(numPayments);

                        String x = "$ " + decimalFormat.format(paymentCalculator.calculateMonthlyPayment());
                        mp.setText(x);

                        String y = "$ " + decimalFormat.format(paymentCalculator.getTotal());
                        totalForPeriod.setText(y);

                    }else{
                        Toast toast = Toast.makeText(getContext(), "Wrong data!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else{
                    Toast toast = Toast.makeText(getContext(), "Fill the fields!", Toast.LENGTH_LONG);
                    toast.show();
                }


            }
        });

    }


    private void initEditText(){
        setPrincipal(findViewById(R.id.editPrincipal));
        setAnnualRate(findViewById(R.id.editAnnualRate));
        setPeriod(findViewById(R.id.editPeriod));

    }


    public Context getContext(){
        return  this;
    }

    public EditText getPrincipal() {
        return principal;
    }

    public EditText getAnnualRate() {
        return annualRate;
    }

    public EditText getPeriod() {
        return period;
    }

    public void setPrincipal(EditText principal) {
        this.principal = principal;
    }

    public void setAnnualRate(EditText annualRate) {
        this.annualRate = annualRate;
    }

    public void setPeriod(EditText period) {
        this.period = period;
    }


}