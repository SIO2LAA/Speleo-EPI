package fr.sio.app_epi2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class FDV_Creer extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog datePickerDialog;
    private DatePickerDialog datePickerDialog2;
    private DatePickerDialog datePickerDialog3;
    private DatePickerDialog datePickerDialog4;
    private DatePickerDialog datePickerDialog5;

    private Button fab_date;
    private Button acq_date;
    private Button uti_date;
    private Button reb_date;
    private Button red_date;
    private Button valider;
    private Button annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdv_creer);
        initDatePicker();
        fab_date = findViewById(R.id.fab_datePickerButton);
        acq_date = findViewById(R.id.acq_datePickerButton);
        uti_date = findViewById(R.id.uti_datePickerButton);
        reb_date = findViewById(R.id.reb_datePickerButton);
        red_date = findViewById(R.id.reb_datePickerButton);
        valider = findViewById(R.id.fdv_afficher_valider);
        annuler = findViewById(R.id.fdv_afficher_annuler);

        fab_date.setText(getTodaysDate());
        acq_date.setText(getNextDate());

        annuler.setOnClickListener(this);
        valider.setOnClickListener(this);
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
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                fab_date.setText(date);
            }
        };

        DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                acq_date.setText(date);
            }
        };

        DatePickerDialog.OnDateSetListener dateSetListener3 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                uti_date.setText(date);
            }
        };

        DatePickerDialog.OnDateSetListener dateSetListener4 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                reb_date.setText(date);
            }
        };

        DatePickerDialog.OnDateSetListener dateSetListener5 = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                red_date.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog2 = new DatePickerDialog(this, style, dateSetListener2, year, month, day);
        datePickerDialog3 = new DatePickerDialog(this, style, dateSetListener3, year, month, day);
        datePickerDialog4 = new DatePickerDialog(this, style, dateSetListener4, year, month, day);
        datePickerDialog5 = new DatePickerDialog(this, style, dateSetListener5, year, month, day);
        //Règle le maximum possible pour date
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return year + "-" + getMonthFormat(month) + "-" + getDayFormat(day);
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "01";
        if(month == 2)
            return "02";
        if(month == 3)
            return "03";
        if(month == 4)
            return "04";
        if(month == 5)
            return "05";
        if(month == 6)
            return "06";
        if(month == 7)
            return "07";
        if(month == 8)
            return "08";
        if(month == 9)
            return "09";
        if(month == 10)
            return "10";
        if(month == 11)
            return "11";
        if(month == 12)
            return "12";

        //default should never happen
        return "01";
    }

    private String getDayFormat(int day)
    {
        if(day == 1)
            return "01";
        if(day == 2)
            return "02";
        if(day == 3)
            return "03";
        if(day == 4)
            return "04";
        if(day == 5)
            return "05";
        if(day == 6)
            return "06";
        if(day == 7)
            return "07";
        if(day == 8)
            return "08";
        if(day == 9)
            return "09";

        //default should never happen
        return "01";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void openDatePicker2(View view)
    {
        datePickerDialog2.show();
    }

    public void openDatePicker3(View view)
    {
        datePickerDialog3.show();
    }

    public void openDatePicker4(View view)
    {
        datePickerDialog4.show();
    }

    public void openDatePicker5(View view)
    {
        datePickerDialog5.show();
    }



    @Override
    public void onClick(View v) {

        if (annuler.isPressed()){
            this.finish();
        }

        if (valider.isPressed()){
            //String text = fab_date + " " + acq_date ; //+ " " + uti_date + " " + reb_date + " " + red_date;
            Toast toast;
            int duree = Toast.LENGTH_LONG;
            toast = Toast.makeText(this.getApplicationContext(), fab_date.getText() ,duree);
            toast.show();
        }
    }
}
