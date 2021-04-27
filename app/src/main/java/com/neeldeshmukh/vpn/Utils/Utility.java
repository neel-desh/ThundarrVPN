package com.neeldeshmukh.vpn.Utils;

import android.net.Uri;

import com.neeldeshmukh.vpn.R;

public class Utility {


    public static String getImgURL(int resourceId) {

        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();
    }
}
