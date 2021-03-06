package net.ddns.esof.ekonomi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.ddns.esof.ekonomi.R;
import net.ddns.esof.ekonomi.rest.model.PrecoSuperm;

import java.util.ArrayList;

/**
 * Created by Alexandre on 19/12/2016.
 */

public class SupermAdapter extends ArrayAdapter<PrecoSuperm> {
    private Context context;
    private ArrayList<PrecoSuperm> lista;

    public SupermAdapter(Context context, ArrayList<PrecoSuperm> lista){
        super(context, android.R.layout.simple_list_item_1, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        PrecoSuperm precoSuperm = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.resultado_supermercados, null);

        TextView textView = (TextView) convertView.findViewById(R.id.superm);
        textView.setText(precoSuperm.getSupermercado().getNome());

        TextView textView1 = (TextView) convertView.findViewById(R.id.valor);
        textView1.setText(String.format("R$ %.2f", precoSuperm.getPreco()));

        return convertView;
    }
}