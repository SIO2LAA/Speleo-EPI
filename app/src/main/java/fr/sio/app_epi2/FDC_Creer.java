package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;
import java.util.List;

import fr.sio.app_epi2.dao.Singleton;
import fr.sio.app_epi2.models.Controleur;
import fr.sio.app_epi2.models.Materiel;

public class FDC_Creer extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog2;
    private Button dateButton;
    private Button nextdateButton;
    private EditText observation;
    private EditText nature;
    private EditText lieu;
    private Spinner listeControleur;
    private Button annuler;
    private Button valider;
    private SQLiteDatabase maBD = Singleton.getDB(this).getDbOpenHelper().getReadableDatabase();
    //private SQLiteDatabase writeBD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdc_creer);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        nextdateButton = findViewById(R.id.nextdatePickerButton);
        observation = findViewById(R.id.inputObservation);
        nature = findViewById(R.id.inputNatureControle);
        lieu = findViewById(R.id.inputLieuControle);
        listeControleur = findViewById(R.id.selectionControleur);

        dateButton.setText(getTodaysDate());
        nextdateButton.setText(getNextDate());

        Intent intent = getIntent();

        int id = intent.getIntExtra("idItemMateriel", 1);

        // affichage du layout
        annuler = findViewById(R.id.btnAnnuler);
        valider = findViewById(R.id.btnValider);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateTXT = dateButton.getText().toString();
                String observTXT = observation.getText().toString();
                String natureTXT = nature.getText().toString();
                String lieuTXT = lieu.getText().toString();

                long checkinsertdata;
                ContentValues content = new ContentValues();
                content.put("date", dateTXT);
                content.put("observation", observTXT);
                content.put("nature", natureTXT);
                content.put("idMateriel", id);
                content.put("lieu", lieuTXT);

                checkinsertdata = maBD.insert("controle", null, content);
                if (checkinsertdata == 1) {
                    Toast.makeText(FDC_Creer.this, "Nouvelles données insérées", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(FDC_Creer.this, "Données non insérées", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //ACTION BOUTON ANNULER
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        /*// creation de la base de donnees et récupération des script sql
        String stringVersion = getString(R.string.version);

        int version = Integer.parseInt(stringVersion);

        String insert = "";
        insert = getString(R.string.insert);

        this.maBD = new DBOpenHelper(this,"EPI2.db",version, null, insert);*/
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String getNextDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                nextdateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog2 = new DatePickerDialog(this, style, dateSetListener2, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return year + "-" + getMonthFormat(month) + "-" + getDayFormat(day);
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "01";
        if (month == 2)
            return "02";
        if (month == 3)
            return "03";
        if (month == 4)
            return "04";
        if (month == 5)
            return "05";
        if (month == 6)
            return "06";
        if (month == 7)
            return "07";
        if (month == 8)
            return "08";
        if (month == 9)
            return "09";
        if (month == 10)
            return "10";
        if (month == 11)
            return "11";
        if (month == 12)
            return "12";

        //default should never happen
        return "01";
    }

    private String getDayFormat(int day) {
        if (day == 1)
            return "01";
        if (day == 2)
            return "02";
        if (day == 3)
            return "03";
        if (day == 4)
            return "04";
        if (day == 5)
            return "05";
        if (day == 6)
            return "06";
        if (day == 7)
            return "07";
        if (day == 8)
            return "08";
        if (day == 9)
            return "09";
        if (day == 10)
            return "10";
        if (day == 11)
            return "11";
        if (day == 12)
            return "12";
        if (day == 13)
            return "13";
        if (day == 14)
            return "14";
        if (day == 15)
            return "15";
        if (day == 16)
            return "16";
        if (day == 17)
            return "17";
        if (day == 18)
            return "18";
        if (day == 19)
            return "19";
        if (day == 20)
            return "20";
        if (day == 21)
            return "21";
        if (day == 22)
            return "22";
        if (day == 23)
            return "23";
        if (day == 24)
            return "24";
        if (day == 25)
            return "25";
        if (day == 26)
            return "26";
        if (day == 27)
            return "27";
        if (day == 28)
            return "28";
        if (day == 29)
            return "29";
        if (day == 30)
            return "30";
        if (day == 31)
            return "31";

        //default should never happen
        return "01";
    }


    public void openDatePicker(View view) { datePickerDialog.show();
    }

    public void openDatePicker2(View view) {
        datePickerDialog2.show();
    }




}
