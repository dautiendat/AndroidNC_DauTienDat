package com.example.baith2;

public class Transaction {
    private String dateTrans,nameTrans, typeTrans, money1,money2;

    public Transaction(String dateTrans, String nameTrans, String typeTrans, String money1, String money2) {
        this.dateTrans = dateTrans;
        this.nameTrans = nameTrans;
        this.typeTrans = typeTrans;
        this.money1 = money1;
        this.money2 = money2;
    }

    public String getDateTrans() {
        return dateTrans;
    }

    public void setDateTrans(String dateTrans) {
        this.dateTrans = dateTrans;
    }

    public String getNameTrans() {
        return nameTrans;
    }

    public void setNameTrans(String nameTrans) {
        this.nameTrans = nameTrans;
    }

    public String getTypeTrans() {
        return typeTrans;
    }

    public void setTypeTrans(String typeTrans) {
        this.typeTrans = typeTrans;
    }

    public String getMoney1() {
        return money1;
    }

    public void setMoney1(String money1) {
        this.money1 = money1;
    }

    public String getMoney2() {
        return money2;
    }

    public void setMoney2(String money2) {
        this.money2 = money2;
    }
}
