package com.anonimeact.sqliteencryptor.jadwalsholat;

import java.io.Serializable;

/**
 * Created by anonimeact on 2019-10-22.
 * Email didiyuliantos@gmail.com
 */
public class PojoLokasi implements Serializable {
    int id;
    String pv, ct;
    double lt, lg;

    public double getLg() {
        return lg;
    }

    public double getLt() {
        return lt;
    }

    public int getId() {
        return id;
    }

    public String getCt() {
        return ct;
    }

    public String getPv() {
        return pv;
    }

    public void setId(int id) {
        this.id = id;
    }
}
