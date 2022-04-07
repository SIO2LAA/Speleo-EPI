package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import fr.sio.app_epi2.models.Materiel;

public class FDC_Afficher extends AppCompatActivity {

    private TextView date;
    private TextView observation;
    private SQLiteDatabase db = MainActivity.dbOpenHelper.getReadableDatabase();
    private Materiel materiel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdc_afficher);

        date = findViewById(R.id.view_date_fdca);
        observation = findViewById(R.id.view_observ_fdca);
    }
}