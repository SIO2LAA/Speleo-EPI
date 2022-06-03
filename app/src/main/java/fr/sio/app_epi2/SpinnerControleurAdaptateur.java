package fr.sio.app_epi2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import fr.sio.app_epi2.models.Controleur;

public class SpinnerControleurAdaptateur extends ArrayAdapter<Controleur> {
    private ArrayList<Controleur> listeControleurs;
    private Context mContext;

    public SpinnerControleurAdaptateur(Context context, int resource, ArrayList<Controleur> objects) {
        super(context, resource);
        this.listeControleurs = objects;
    }

    @Override
    public int getCount() {
        return listeControleurs.size();
    }

    @Override
    public Controleur getItem(int i) {
        return listeControleurs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        int phraseIndex = position;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_controleurs, parent, false);
        }
        TextView libelle = convertView.findViewById(R.id.libelleControleur);

        libelle.setText(String.format("%s %s", listeControleurs.get(position).getNom(), listeControleurs.get(position).getPrenom()));

        return convertView;
    }
}
