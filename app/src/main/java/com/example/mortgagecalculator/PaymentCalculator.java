package com.example.mortgagecalculator;

public class PaymentCalculator {

    private final double principal;
    private int numberOfPayments;
    private double period;
    private final double r;
    private double total = 0;
    private double monthlyPayment;
    private int year;
    private int month;




    public PaymentCalculator(double principal, double annualRate){
        this.principal = principal;
        this.r = annualRate / 100 / 12;
    }


    public double calculateMonthlyPayment(){
        double mp = this.principal * ((this.r * Math.pow(1 + this.r, this.numberOfPayments)) / (Math.pow(1 + this.r, this.numberOfPayments) -1));
        total = getNumberOfPayments() * mp;
        return mp;
    }

    public double calculatePeriod(){

        double x = Math.log((getMonthlyPayment()/this.r) / ((getMonthlyPayment()/this.r) - this.principal));
        double y = Math.log(1 + r);
        this.period = x / y / 12;
        total = getPeriod() * 12 * getMonthlyPayment();
        setYearMonthPeriod(getPeriod());
        return getPeriod();
    }

    public void setYearMonthPeriod(double period){
        this.year = (int)Math.round(period);
        if(this.year > period){
            this.year--;
        }
        this.month = (int)Math.round((period * 12) % 12);
    }

    public void setPeriod(double period) {
        this.period = period;
        numberOfPayments = (int)Math.round(period * 12);
        if(numberOfPayments < period * 12){
            numberOfPayments++;
        }
    }

    public double getNumberOfPayments() {
        double numberOfPayments = this.numberOfPayments;
        return numberOfPayments;
    }

    public double getTotal() {
        return total;
    }


    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getPeriod() {
        return period;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
}
