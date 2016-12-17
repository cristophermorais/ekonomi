package net.ddns.esof.ekonomi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import net.ddns.esof.ekonomi.rest.classes.Produto;

import java.util.ArrayList;

/**
 * Created by alexandre on 17/12/2016.
 */

public class ListaComprasAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Produto> produtos;

    public ListaComprasAdapter(Context context, ArrayList<Produto> arrayList){
        super(context, android.R.layout.simple_list_item_1, arrayList);
        this.produtos = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int index) {
        return super.getItem(index);
    }

    @Override
    public long getItemId(int index) {
        return produtos.get(index).getId();
    }

    @Override
    public void add(Object p){
        if(p instanceof Produto && !produtos.contains(p)){
            super.add(p);
        }
    }
}
