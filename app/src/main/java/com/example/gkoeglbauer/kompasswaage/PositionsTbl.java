package com.example.gkoeglbauer.kompasswaage;

/**
 * Created by felixdeixler on 29.05.15.
 */
public class PositionsTbl {

    public static final String TABLE_NAME = "Positions";

    public final static String id = "id";
    public final static String Name= "name";
    public final static String Längengrad = "laenge";
    public final static String Breitengrad= "breite";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    id + " INTEGER PRIMARY KEY," +
                    Name + " TEXT NOT NULL," +
                    Längengrad + " DOUBLE NOT NULL," +
                    Breitengrad + " DOUBLE NOT NULL," +
                    ")";

    public static final String STMT_INSERT =
            "INSERT INTO " + TABLE_NAME +
                    "(" + Name + "," + Längengrad + "," + Breitengrad + ")" +
                    "VALUES (?,?,?)";


}
