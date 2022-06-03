package fr.sio.app_epi2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.sio.app_epi2.models.Controleur;

public class SimpleSpinnerDropdownItem extends ArrayAdapter<Controleur> {
    private ArrayList<Controleur> listeControleurs;
    private Context mContext;

    public SimpleSpinnerDropdownItem(Context context, int resource, ArrayList<Controleur> objects) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        TextView libelle = convertView.findViewById(R.id.libelleControleurListe);

        libelle.setText(String.format("%s %s", listeControleurs.get(position).getNom(), listeControleurs.get(position).getPrenom()));

        return convertView;
    }
}
