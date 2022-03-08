/*package fr.sio.app_epi2.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

import fr.sio.app_epi2.R;

public class MaterielAdaptater extends ArrayAdapter<Materiel> {
    private Context mContext;

    private static final String TAG = "MaterielAdaptater";

    public MaterielAdaptater(Context context, int ressource, ArrayList<Materiel> objects) {
        super(context, ressource, objects);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //https://youtu.be/E6vE8fqQPTE

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String libelle = getItem(position);
        String modele = getItem(position);
        return null;
    }
}
*/