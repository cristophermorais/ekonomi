package net.ddns.esof.ekonomi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.ddns.esof.ekonomi.rest.RestClient;
import net.ddns.esof.ekonomi.rest.classes.PrecoProd;
import net.ddns.esof.ekonomi.rest.classes.Produto;
import net.ddns.esof.ekonomi.rest.classes.Supermercado;
import net.ddns.esof.ekonomi.rest.volley.JsonConverter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultadoActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener {
    private final String REQUEST_TAG = "ResultadoActivity";
    private RestClient rest;
    private HashMap<Supermercado, HashMap<Produto, PrecoProd>> produtosEscolhidos;
    private HashMap<Supermercado, Double>  precos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        rest = RestClient.getInstance(this.getApplicationContext());

        Intent intent = this.getIntent();
        ArrayList<Produto> produtos = (ArrayList<Produto>) intent.getSerializableExtra("listaProdutos");
        int idCidade = intent.getIntExtra("idCidade", -1);
        ArrayList<Integer> idsProdutos = new ArrayList<>(produtos.size());

        for (Produto produto: produtos) {
            idsProdutos.add(produto.getId());
        }

        try {
            rest.getJsonPrecoProdutos(idsProdutos, idCidade,this,this,REQUEST_TAG);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(Object response) {
        List<PrecoProd> listaPrecos = null;

        try {
                listaPrecos = JsonConverter.converJsonToListaPrecos((JSONArray) response);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

        produtosEscolhidos = escolheProdutos(listaPrecos);
        precos = calculaPreco(produtosEscolhidos);
        populateListView();
    }

    private HashMap<Supermercado, HashMap<Produto, PrecoProd>> escolheProdutos(List<PrecoProd> listaPrecos){
        HashMap<Supermercado, HashMap<Produto, PrecoProd>> produtosEscolhidos = new HashMap<>();

        for(PrecoProd pp : listaPrecos){
            Supermercado superm = pp.getSuperm();
            Produto prod = pp.getProduto();

            if(!produtosEscolhidos.containsKey(superm)){
                HashMap<Produto, PrecoProd> aux = new HashMap<>();
                produtosEscolhidos.put(superm,aux);
            }

            if(produtosEscolhidos.get(superm).containsKey(prod)){
                PrecoProd aux = produtosEscolhidos.get(superm).get(prod);
                double antigo = aux.getPrecoProd() / aux.getQtUnPad();
                double novo = pp.getPrecoProd() / pp.getQtUnPad();

                if(novo < antigo){
                    produtosEscolhidos.get(superm).remove(prod);
                    produtosEscolhidos.get(superm).put(prod, pp);
                }

            }else{
                produtosEscolhidos.get(superm).put(prod, pp);
            }

        }
        return produtosEscolhidos;
    }

    private HashMap<Supermercado, Double> calculaPreco(
            HashMap<Supermercado, HashMap<Produto, PrecoProd>> produtosEscolhidos){

        HashMap<Supermercado, Double> precos = new HashMap<>();

        for (Supermercado superm : produtosEscolhidos.keySet()){
            double total = 0;
            HashMap<Produto, PrecoProd> aux = produtosEscolhidos.get(superm);

            for(Produto p : aux.keySet()){
                PrecoProd pp = aux.get(p);
                total += pp.getPrecoProd();
            }

            precos.put(superm, total);
        }

        return precos;
    }

    private void populateListView() {
        ArrayList<PrecoSuperm> precoSuperms = new ArrayList<>(precos.size());

        for(Supermercado superm : precos.keySet()){
            precoSuperms.add(new PrecoSuperm(superm, precos.get(superm)));
        }

        SupermAdapter supermAdapter = new SupermAdapter(this, precoSuperms);

        ListView listView = (ListView) findViewById(R.id.listViewSuperm);
        listView.setAdapter(supermAdapter);

    }

    public class PrecoSuperm{
        private double preco;
        private Supermercado supermercado;

        public PrecoSuperm(Supermercado supermercado, Double preco){
            this.preco = preco;
            this.supermercado = supermercado;
        }

        public double getPreco(){
            return preco;
        }

        public Supermercado getSupermercado(){
            return supermercado;
        }
    }

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
            textView1.setText(String.format("%.2f", precoSuperm.getPreco()));

            return convertView;
        }
    }
}
