package edu.gmu.cs477.fall2020.finalproject_tnguye87;

public final class DBParam {

    //Constructor
    DBParam() {}

    public static abstract class CarsParam {
        final static String CAR_DB_NAME = "Cars.db";
        final static String CAR_TABLE_NAME = "Cars_table";
        final static String C_NAME = "c_name";
        final static String C_TYPE = "c_type";
        final static String C_MAKE = "c_make";
        final static String C_MODEL = "c_model";
        final static String C_NOTE = "c_note";
        final static String C_YEAR = "c_year";
    }

    public static abstract class ServiceParam {
        final static String SERVICE_DB_NAME = "Services.db";
        final static String SERVICE_TABLE_NAME = "Services_table";
        final static String S_NAME = "s_name";
        final static String S_CUR_DATE = "s_cur_date";
        final static String S_CUR_MILES = "c_cur_miles";
        final static String S_NEXT_DATE = "s_next_date";
        final static String S_NEXT_MILES = "s_next_miles";
        final static String S_NOTE = "s_note";
    }
}
