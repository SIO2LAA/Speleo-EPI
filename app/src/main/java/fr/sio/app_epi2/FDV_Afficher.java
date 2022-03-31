package fr.sio.app_epi2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FDV_Afficher extends AppCompatActivity implements View.OnClickListener {

    private Button annuler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdv_afficher);

        annuler = findViewById(R.id.fdv_creer_annuler);

        annuler.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (annuler.isPressed()){
            this.finish();
        }
    }
}
