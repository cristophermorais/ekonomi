package net.ddns.esof.ekonomi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.ddns.esof.ekonomi.R;
import net.ddns.esof.ekonomi.rest.model.PrecoProd;

import java.util.ArrayList;

/**
 * Created by Alexandre on 19/12/2016.
 */

public class PrecoProdAdapter extends ArrayAdapter<PrecoProd> {
    private Context context;
    private ArrayList<PrecoProd> lista;

    public PrecoProdAdapter(Context context, ArrayList<PrecoProd> lista){
        super(context, android.R.layout.simple_list_item_1, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        PrecoProd precoProd = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.resultado_supermercados, null);

        TextView textView = (TextView) convertView.findViewById(R.id.superm);
        textView.setText(precoProd.getDescricao());

        TextView textView1 = (TextView) convertView.findViewById(R.id.valor);
        textView1.setText(String.format("R$ %.2f", precoProd.getPrecoProd()));

        return convertView;
    }
}
