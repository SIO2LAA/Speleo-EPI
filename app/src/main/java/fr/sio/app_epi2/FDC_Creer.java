package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;

public class FDC_Creer extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private EditText observation;
    private Button annuler;
    private Button valider;
    // private DBOpenHelper maBD;
    // private SQLiteDatabase writeBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdc_creer);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        // affichage du layout
        annuler = findViewById(R.id.btnAnnuler);
        valider = findViewById(R.id.btnValider);

        // boutons sur écoute
        annuler.setOnClickListener(this);
        valider.setOnClickListener(this);

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

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return day + "/" + getMonthFormat(month) + "/" + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }



    @Override
    public void onClick(View v) {

        if (annuler.isPressed()){
            this.finish();
        }

        if (valider.isPressed()){
            // ajout du message dans la BD
           /* ContentValues values = new ContentValues();
            date = date.get
            values.put("sujet", this.date.get;
            values.put("message", this.eMessage.getText().toString());
            long res = this.writeBD.insert("ListeMess", "cle", values);

            if (res>0){
                Toast toast1 = Toast.makeText(this,"Le message a été ajouté dans la base de données",Toast.LENGTH_LONG);
                toast1.show();
            }*/
        }
    }
}