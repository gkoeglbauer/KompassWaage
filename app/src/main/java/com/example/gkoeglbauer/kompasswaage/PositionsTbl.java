package com.example.gkoeglbauer.kompasswaage;

/**
 * Created by felixdeixler on 29.05.15.
 */
public class PositionsTbl {

    public static final String TABLE_NAME = "Positions";

    public static String Id = "Id";
    public final static String Name = "Name";
    public final static String Längengrad = "Laenge";
    public final static String Breitengrad = "Breite";

    public final static String[] ALL_COLUMNS = new String[]{Id + " AS _id", Name, Längengrad, Breitengrad};


    public final static String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    Id + " INTEGER PRIMARY KEY," +
                    Name + " TEXT NOT NULL," +
                    Längengrad + " DOUBLE NOT NULL," +
                    Breitengrad + " DOUBLE NOT NULL" +
                    ")";

    public static final String STMT_INSERT =
            "INSERT INTO " + TABLE_NAME +
                    "(" + Name + "," + Längengrad + "," + Breitengrad + ") " +
                    "VALUES (?,?,?)";


}
