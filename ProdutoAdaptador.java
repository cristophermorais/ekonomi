package net.ddns.esof.ekonomi;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.ddns.esof.ekonomi.rest.classes.Produto;

import java.util.List;


public class ProdutoAdaptador extends BaseAdapter {

    private final List<Produto> produtos;
    private final Activity activity;

    public ProdutoAdaptador(Activity activity, List<Produto> produtos) {
        this.produtos = produtos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.produtos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View linha = convertView;
        Produto produto = produtos.get(position);


        if (linha == null) {
            linha = this.activity.getLayoutInflater().inflate(R.layout.celula_layout, parent, false);
        }

        TextView nome = (TextView) linha.findViewById(R.id.inputId);
        TextView unMedida = (TextView) linha.findViewById(R.id.inputId);

/*
        if(position%2 == 0){
            linha.setBackgroundColor(activity.getResources().getColor(R.color.corPar));
        }else {
            linha.setBackgroundColor(activity.getResources().getColor(R.color.corImpar));
        }
*/
        nome.setText(produto.getNome());


        if (nome != null) {
            nome.setText(produto.getNome());
        }
        if (unMedida != null) {
            unMedida.setText((CharSequence) produto.getMedida());
        }


        return linha;

    }
}
