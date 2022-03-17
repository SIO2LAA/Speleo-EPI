package fr.sio.app_epi2.models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.sio.app_epi2.R;

public class FiltreAdaptater extends ArrayAdapter<Filtre> {
    private ArrayList<Filtre> listeFiltres;
    private Context mContext;

    public FiltreAdaptater(Context context, int ressource, ArrayList<Filtre> objects) {
        super(context, ressource, objects);
        this.listeFiltres = objects;
    }

    @Override
    public int getCount() {
        return listeFiltres.size();
    }

    @Override
    public Filtre getItem(int i) {
        return listeFiltres.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //https://youtu.be/E6vE8fqQPTE

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int phraseIndex = position;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listefiltres, parent, false);
        }
        TextView libelle = convertView.findViewById(R.id.libelleFiltre);
        libelle.setText(listeFiltres.get(position).getLibelle());
        return convertView;
    }
}
