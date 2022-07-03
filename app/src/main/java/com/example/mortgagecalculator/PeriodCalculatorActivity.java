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

public class PeriodCalculatorActivity extends AppCompatActivity  {

    private EditText principal;
    private EditText annualRate;
    private EditText monthlyPayment;

    private TextView period;
    private TextView lastMonthlyPayment;
    private TextView totalForPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_calculator);

        this.period = findViewById(R.id.txtPeriod);
        this.lastMonthlyPayment = findViewById(R.id.txtLastMonthlyPayments);
        this.totalForPeriod = findViewById(R.id.txtTotal);

        Button btnMonthlyCal = findViewById(R.id.btnMonthlyCal);
        btnMonthlyCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MonthlyPaymentCalculatorActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnCalculateMonthlyPayment = findViewById(R.id.btnCalculateMonthlyPayment);
        btnCalculateMonthlyPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initEditText();

                if(!getPrincipal().getText().toString().isEmpty() &&
                    !getAnnualRate().getText().toString().isEmpty() &&
                    !getMonthlyPayment().getText().toString().isEmpty()){

                    double principal = Double.parseDouble(getPrincipal().getText().toString());
                    double annualRate = Double.parseDouble(getAnnualRate().getText().toString());
                    double mPayment = Double.parseDouble(getMonthlyPayment().getText().toString());

                    if(principal > 0 && principal <= 1000000000 &&
                            annualRate > 0 && annualRate <= 100 &&
                            mPayment > 0 && mPayment <= principal){
                        DecimalFormat decimalFormat = new DecimalFormat("##.##");
                        DecimalFormat decimalFormat1 = new DecimalFormat("##");

                        PaymentCalculator paymentCalculator = new PaymentCalculator(principal, annualRate);
                        paymentCalculator.setMonthlyPayment(mPayment);
                        if(paymentCalculator.calculatePeriod() > 0 &&
                        paymentCalculator.getNumberOfPayments() < 50){
                            String numPayments = paymentCalculator.getYear()  + " y. " +
                                    paymentCalculator.getMonth() + " m.";
                            period.setText(numPayments);

                            String x = "$ " + decimalFormat.format(paymentCalculator.getMonthlyPayment());

                            lastMonthlyPayment.setText(x);

                            String y = "$ " + decimalFormat.format(paymentCalculator.getTotal());
                            totalForPeriod.setText(y);
                        }else{
                            Toast toast = Toast.makeText(getContext(), "Annual rate is too high for this payment.", Toast.LENGTH_LONG);
                            toast.show();
                        }




                    }
                    else{
                        Toast toast = Toast.makeText(getContext(), "Wrong data!", Toast.LENGTH_LONG);
                        toast.show();
                    }

                }else{
                    Toast toast = Toast.makeText(getContext(), "Fill the fields!", Toast.LENGTH_LONG);
                    toast.show();
                }



            }
        });

    }

    private void initEditText(){
        this.principal = findViewById(R.id.editPrincipal);
        this.annualRate = findViewById(R.id.editAnnualRate);
        this.monthlyPayment = findViewById(R.id.editMonthlyPayment);
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

    public EditText getMonthlyPayment() {
        return monthlyPayment;
    }



}