package com.example.chargethedebt.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class debtor {
    protected String name;
    protected String phoneNumber;
    protected String address;

    public String getPurpose() {
        return purpose;
    }

    public String getDateDebt() {
        return dateDebt;
    }

    protected String purpose;
    protected double originAmount, currentAmount, interestRate, paid;
    protected String dateDebt;


    // khai báo constructer để khởi tạo giá trị cho đối tượng
    public debtor(String name, String phoneNumber, String address, String purpose, double originAmount, double interestRate) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.purpose = purpose;
        this.originAmount = originAmount;
        this.interestRate = interestRate;
        this.paid = 0;
        this.currentAmount = originAmount;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.dateDebt = sdf.format(new Date());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getOriginAmount() {
        return originAmount;
    }

    public void setOriginAmount(double amount) {
        this.originAmount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setCurrentAmount(double originAmount) {
        this.originAmount = originAmount;
    }

    public double getCurrentAmount() {
        return originAmount;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public String getdateDebt() {
        return dateDebt;
    }

    public void setdateDebt(double paid) {
        this.dateDebt = dateDebt;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setDateDebt(String dateDebt) {
        this.dateDebt = dateDebt;
    }

}
