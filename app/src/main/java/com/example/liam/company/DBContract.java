package com.example.liam.company;

import android.net.Uri;
import android.provider.BaseColumns;

public final class DBContract {

    public static final String AUTHORITY = "com.example.liam.providers.dataProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static class Company implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DBContract.CONTENT_URI, "companies");

        public static final String TABLE = "companies";

        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String WEBSITE_URL = "website_url";

        public static final String SORT_ORDER_DEFAULT = NAME + " DESC";
    }

    public static class Office implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DBContract.CONTENT_URI, "offices");

        public static final String TABLE = "offices";

        public static final String COMPANY_ID = "company_id";
        public static final String NAME = "name";
        public static final String SPACE_AMOUNT = "space_amount";
        public static final String PHONE_NUM = "phone_num";
        public static final String ADDRESS = "address";
        public static final String LON = "lon";
        public static final String LAT = "lat";

        public static final String SORT_ORDER_DEFAULT = NAME + " DESC";
    }
}
