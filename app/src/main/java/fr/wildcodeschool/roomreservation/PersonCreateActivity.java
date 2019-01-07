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

public class PersonCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_create);

        Button btCreate = findViewById(R.id.button_create_person);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etFirstname = findViewById(R.id.edit_firstname);
                EditText etLastname = findViewById(R.id.edit_lastname);

                String firstname = etFirstname.getText().toString();
                String lastname = etLastname.getText().toString();

                addPersonToDB(firstname, lastname);

                Intent intent = new Intent(PersonCreateActivity.this, MainActivity.class);
                PersonCreateActivity.this.startActivity(intent);
            }
        });
    }

    private void addPersonToDB(String firstname, String lastname) {
        // TODO : add person into database

            //Initialisation de l'accès à la base de données :
        DBhelper mDbHelper = new DBhelper(PersonCreateActivity.this);

            //Ouverture d'un accès en écriture :
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        //Une "carte" vide de l'objet à insérer est créée :
        ContentValues person = new ContentValues();

        //Ici sont associées les valeurs aux colonnes de la table
        person.put(DBcontract.PersonEntry.COLUMN_NAME_FIRSTNAME, firstname);
        person.put(DBcontract.PersonEntry.COLUMN_NAME_LASTNAME, lastname);

        //ne fois remplie, la "carte" de la personne est insérée dans la table correspondante.
        //Un identifiant unique est alors retourné :
        long newPersonId = db.insert(DBcontract.PersonEntry.TABLE_NAME, null, person);

        Toast.makeText(this, String.valueOf(newPersonId), Toast.LENGTH_SHORT).show();
    }
}
