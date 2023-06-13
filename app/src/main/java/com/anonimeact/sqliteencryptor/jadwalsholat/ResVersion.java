package com.anonimeact.sqliteencryptor.jadwalsholat;

import java.io.Serializable;

/**
 * Created by anonimeact on 2019-09-28.
 * Email didiyuliantos@gmail.com
 */
public class ResVersion implements Serializable {
    int v_min;
    String v_name;
    int v_up;
    String v_up_note;

    public int getV_min() {
        return v_min;
    }

    public int getV_up() {
        return v_up;
    }

    public String getV_name() {
        return v_name;
    }

    public String getV_up_note() {
        return v_up_note;
    }
}
