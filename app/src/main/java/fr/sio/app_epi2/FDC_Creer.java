package fr.sio.app_epi2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FDC_Creer extends AppCompatActivity implements View.OnClickListener {

    private Button annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdc_creer);

        annuler = findViewById(R.id.btnAnnuler);

        annuler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (annuler.isPressed()){
            this.finish();
        }
    }
}