package net.ddns.esof.ekonomi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.ddns.esof.ekonomi.rest.RestClient;
import net.ddns.esof.ekonomi.rest.classes.Cidade;
import net.ddns.esof.ekonomi.rest.classes.Produto;
import net.ddns.esof.ekonomi.rest.volley.JsonConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ProdutoActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener {
    private final String REQUEST_TAG = "ProdutoActivity";
    private RestClient rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        rest = RestClient.getInstance(this.getApplicationContext());

        Intent intent = getIntent();

        if(intent.hasExtra("listaProdutos")){
            populateListView((List<Produto>)intent.getSerializableExtra("listaProdutos"));
        }else{
            rest.getJsonListaProdutos(this, this, REQUEST_TAG);
        }
    }

    public void onBackPressed() {
        rest.cancelAll(REQUEST_TAG);
        setResult(-1);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        rest.cancelAll(REQUEST_TAG);
    }

    // metodo chamado quando ocorre erro no processamento da requisição
    @Override
    public void onErrorResponse(VolleyError error) {
     /*   TextView textView = (TextView) findViewById(R.id.editText);
        String e = error.toString();
        textView.setText(e);

        findViewById(R.id.inputId).requestFocus();*/
    }

    // metodo chamado quando a requisição recebe uma resposta
    @Override
    public void onResponse(Object response) {
        Produto produto;
        List<Produto> listaProdutos;

        try {
            if (response instanceof JSONObject) {
                produto = JsonConverter.convertJsonToProduto((JSONObject) response);

            } else if (response instanceof JSONArray) {
                listaProdutos = JsonConverter.convertJsonToListaProdutos((JSONArray) response);
                populateListView(listaProdutos);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



/*
        try {
            if (response instanceof JSONObject) {
                produto = JsonConverter.convertJsonToProduto((JSONObject) response);
                TextView textView = ((TextView) findViewById(R.id.editText));
                textView.setText(produto.toString());
            } else if (response instanceof JSONArray) {

                listaProdutos = JsonConverter.convertJsonToListaProdutos((JSONArray) response);
                TextView textView = ((TextView) findViewById(R.id.editText));
                textView.setText(listaProdutos.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        findViewById(R.id.inputId).requestFocus();*/
    }

    //metodo para enviar requisição
    public void getJsonProdutos(View view) {

        rest.getJsonListaProdutos(this, this, REQUEST_TAG);
    }

    private void populateListView(List<Produto> listaProdutos){
        final ListView listView = (ListView) findViewById(R.id.listViewProdutos);
        ArrayAdapter<Produto> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, listaProdutos);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Produto selected = (Produto) listView.getItemAtPosition(position);

                Intent intent = new Intent(ProdutoActivity.this, TestActivity.class);
                intent.putExtra("produto", selected);
                setResult(0, intent);
                finish();
            }
        });
    }

}
