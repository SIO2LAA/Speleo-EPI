package fr.sio.app_epi2.models;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fr.sio.app_epi2.R;

public class MaterielAdaptater extends ArrayAdapter<Materiel> {
    private ArrayList<Materiel> listeMateriels;
    private Context mContext;

    public MaterielAdaptater(Context context, int ressource, ArrayList<Materiel> objects) {
        super(context, ressource, objects);
        this.listeMateriels = objects;
    }

    @Override
    public int getCount() {
        return listeMateriels.size();
    }

    @Override
    public Materiel getItem(int i) {
        return listeMateriels.get(i);
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listeview_item, parent, false);
        }
        TextView libelle = convertView.findViewById(R.id.libelleMateriel);
        libelle.setText(listeMateriels.get(position).getLibelle());
        TextView modele = convertView.findViewById(R.id.modelMateriel);
        modele.setText(listeMateriels.get(position).getModele());
        TextView date = convertView.findViewById(R.id.dateMateriel);
        Date dateAcquisition = listeMateriels.get(position).getDateAcquisition();
        CharSequence dateformat = DateFormat.format("yyyy-mm-dd", dateAcquisition);
        date.setText(dateformat);
        return convertView;
    }
}
