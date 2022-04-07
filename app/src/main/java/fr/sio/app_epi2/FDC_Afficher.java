package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.sio.app_epi2.models.Controle;
import fr.sio.app_epi2.models.Materiel;

public class FDC_Afficher extends AppCompatActivity {

    private TextView date;
    private TextView observation;
    private SQLiteDatabase db = MainActivity.dbOpenHelper.getReadableDatabase();
    private Controle controle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdc_afficher);

        date = findViewById(R.id.view_date_fdca);
        observation = findViewById(R.id.view_observ_fdca);

        Intent intent = getIntent();
        Log.i("id", "id = " + intent.getIntExtra("FK_idMateriel", 1));
        int id = intent.getIntExtra("FK_idMateriel", 1);
        Cursor cursor = db.rawQuery("SELECT * FROM controle WHERE idMateriel = " + id, null);
        SimpleDateFormat format = new SimpleDateFormat("y-m-d");

        while(cursor.moveToNext()) {
            Date date_controle = new Date();

            try {
                date_controle = format.parse(cursor.getString(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            controle = new Controle (cursor.getInt(0), date_controle, cursor.getString(2),cursor.getString(3), cursor.getString(4));

        }

        if (controle.getDate().toString() == ""){
            date.setText((CharSequence) controle.getDate());
        }else{
            date.setText("Pas de date enregistrée");
        }
        if (controle.getObservation() == ""){
            observation.setText(controle.getObservation());
        }else{
            observation.setText("Pas d'observation enregistrée");
        }

    }
}