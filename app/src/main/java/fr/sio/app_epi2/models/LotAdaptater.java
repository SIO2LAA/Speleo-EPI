package fr.sio.app_epi2.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import fr.sio.app_epi2.R;
import fr.sio.app_epi2.dao.Singleton;

public class LotAdaptater extends ArrayAdapter<Lot> {
    private ArrayList<Lot> listeLots;
    private Context mContext;
    private SQLiteDatabase db;

    public LotAdaptater(Context context, int ressource, ArrayList<Lot> objects) {
        super(context, ressource, objects);
        this.listeLots = objects;
        this.db = Singleton.getDB(context).getDbOpenHelper().getReadableDatabase();
    }

    @Override
    public int getCount() {
        return listeLots.size();
    }

    @Override
    public Lot getItem(int i) {
        return listeLots.get(i);
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listeview_lot, parent, false);
        }

        String libelleMateriel = "";
        Cursor cursor = db.rawQuery("SELECT * FROM materiel WHERE id = " + listeLots.get(position).getIdMateriel(), null);

        while (cursor.moveToNext()) {
            libelleMateriel = cursor.getString(1);
        }

        TextView libelle = convertView.findViewById(R.id.libelleMaterielLot);
        libelle.setText(libelleMateriel);
        TextView quantite = convertView.findViewById(R.id.quantiteMaterielLot);
        quantite.setText(String.valueOf(listeLots.get(position).getQuantite()));
        TextView date = convertView.findViewById(R.id.dateLot);
        Date dateAcquisition = listeLots.get(position).getDate();
        CharSequence dateformat = DateFormat.format("yyyy-mm-dd", dateAcquisition);
        date.setText(dateformat);
        return convertView;
    }
}
