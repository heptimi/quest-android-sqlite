package fr.wildcodeschool.roomreservation;

import android.provider.BaseColumns;

public final class DBcontract {

    public static class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME = "Person";
        public static final String COLUMN_NAME_LASTNAME = "lastname";
        public static final String COLUMN_NAME_FIRSTNAME = "firstname";

    }

    public static class RoomEntry implements BaseColumns {
        public static final String TABLE_NAME = "Room";
        public static final String COLUMN_NAME_ROOMNAME = "name";
    }
}
