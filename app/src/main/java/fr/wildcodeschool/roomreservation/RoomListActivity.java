package fr.wildcodeschool.roomreservation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class RoomListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        ArrayList<RoomModel> roomModels = loadRoomsFromDB();

        RoomAdapter adapter = new RoomAdapter(this, 0, roomModels);
        ListView lvListRoom = findViewById(R.id.list_room);
        lvListRoom.setAdapter(adapter);
    }

    private ArrayList<RoomModel> loadRoomsFromDB() {
        ArrayList<RoomModel> roomModels = new ArrayList<>();

        // TODO : load rooms from database

        //Tu initialises l'accès à la base de données :
        DBhelper mDbHelper = new DBhelper(RoomListActivity.this);

        //Ouverture d'un acces en lecture
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Tu délimites dans une "projection" les colonnes de la table que tu vas récupérer :
        String[] projection = {
                DBcontract.RoomEntry._ID,
                DBcontract.RoomEntry.COLUMN_NAME_ROOMNAME
        };

        //ci, c'est la requête SQL qui va sélectionner tous les résultats de la table, en fonction des colonnes de la "projection".
        // Les résultats sont stockés dans un Cursor :
        Cursor cursor = db.query(
                DBcontract.RoomEntry.TABLE_NAME,
                projection,
                null, null, null, null, null
        );

        //Ici tu parcours chaque ligne de la table stockée dans le Cursor, et pour chacune d’entre-elles
        // tu récupères les valeurs des colonnes dont tu as besoin.
        // Enfin, tu créés une instance de la classe PersonModel,
        // pour ensuite l'ajouter à la liste qui sera plus tard envoyée à l'Adapter :
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(DBcontract.RoomEntry._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DBcontract.RoomEntry.COLUMN_NAME_ROOMNAME));


            RoomModel roomModel = new RoomModel(id, name);
            roomModels.add(roomModel);
        }
        cursor.close();


        return roomModels;
    }
}
