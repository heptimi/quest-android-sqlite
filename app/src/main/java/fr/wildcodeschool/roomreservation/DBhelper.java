package fr.wildcodeschool.roomreservation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    //Ici tu vas définir la version de ta base de données et le nom de cette dernière.
    //La version est importante : à chaque modification de la structure de la base
    // (ex : ajout, modification, suppression de tables ou de champs), il faudra incrémenter DATABASE_VERSION (l'augmenter de 1) :

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "database.db";

    //Voici le constructeur qui initialise le lien avec la base de données, en spécifiant son nom et sa version.
    public DBhelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    //déclarer des constantes contenant le code SQL de création de chaque table et de chaque champ associé.
    // Ici, prenons le cas de la table Person

    public static final String SQL_CREATE_PERSON_ENTRIES =
            "CREATE TABLE " + DBcontract.PersonEntry.TABLE_NAME + " (" +
                    DBcontract.PersonEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBcontract.PersonEntry.COLUMN_NAME_LASTNAME + " TEXT," +
                    DBcontract.PersonEntry.COLUMN_NAME_FIRSTNAME + " TEXT);";

    public static final String SQL_CREATE_ROOM_ENTRIES =
            "CREATE TABLE " + DBcontract.RoomEntry.TABLE_NAME + " (" +
                    DBcontract.RoomEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBcontract.RoomEntry.COLUMN_NAME_ROOMNAME + " TEXT);";

    //Pour la suppression de la table
    public static final String SQL_DELETE_PERSON_ENTRIES =
            "DROP TABLE IF EXISTS " + DBcontract.PersonEntry.TABLE_NAME;

    public static final String SQL_DELETE_ROOM_ENTRIES =
            "DROP TABLE IF EXISTS " + DBcontract.RoomEntry.TABLE_NAME;


    //Ici tu as défini la méthode appelée à la création de la base de données.
    // On remarque que la requête de création de la table Person est exécutée.
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PERSON_ENTRIES);
        db.execSQL(SQL_CREATE_ROOM_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PERSON_ENTRIES);
        db.execSQL(SQL_DELETE_ROOM_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
