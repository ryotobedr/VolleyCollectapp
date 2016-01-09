package com.example.ryoma.volleycollectapp;

import android.provider.BaseColumns;

/**
 * Created by ryoma on 2015/12/09.
 */
public final class UserContract {

    public UserContract() {}

    public static abstract class Users implements BaseColumns { //_id
        public static final String TABLE_NAME = "users";
        public static final String COL_PLNUM = "number";
        public static final String COL_NAME = "name";


    }

}
