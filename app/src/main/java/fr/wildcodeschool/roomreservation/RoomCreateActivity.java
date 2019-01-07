package fr.wildcodeschool.roomreservation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RoomCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        Button btCreate = findViewById(R.id.button_create_room);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etName = findViewById(R.id.edit_name);

                String name = etName.getText().toString();

                addRoomToDB(name);

                Intent intent = new Intent(RoomCreateActivity.this, MainActivity.class);
                RoomCreateActivity.this.startActivity(intent);
            }
        });
    }

    private void addRoomToDB(String name) {
        // TODO : add room into database

        //Initialisation de l'accès à la base de données :
        DBhelper mDbHelper = new DBhelper(RoomCreateActivity.this);

        //Ouverture d'un accès en écriture :
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        //Une "carte" vide de l'objet à insérer est créée :
        ContentValues room = new ContentValues();

        //Ici sont associées les valeurs aux colonnes de la table
        room.put(DBcontract.RoomEntry.COLUMN_NAME_ROOMNAME, name);


        //ne fois remplie, la "carte" de la personne est insérée dans la table correspondante.
        //Un identifiant unique est alors retourné :
        long newRoomId = db.insert(DBcontract.RoomEntry.TABLE_NAME, null, room);

        Toast.makeText(this, String.valueOf(newRoomId), Toast.LENGTH_SHORT).show();
    }
}
