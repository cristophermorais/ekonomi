package net.ddns.esof.ekonomi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.ddns.esof.ekonomi.rest.RestClient;
import net.ddns.esof.ekonomi.rest.classes.Produto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class TestActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener {
    private final String REQUEST_TAG = "TestActivity";
    private RestClient rest;
    private int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        rest = RestClient.getInstance(this.getApplicationContext());
    }

    @Override
    protected void onStop() {
        super.onStop();
        rest.cancelAll(REQUEST_TAG);
    }

    // metodo chamado quando ocorre erro no processamento da requisição
    @Override
    public void onErrorResponse(VolleyError error) {
        TextView textView = (TextView) findViewById(R.id.editText);
        String e = error.toString();
        textView.setText(e);

    }

    // metodo chamado quando a requisição recebe uma resposta
    @Override
    public void onResponse(Object response) {
        Produto produto;
        List<Produto> listaProdutos;

        if (response instanceof JSONObject) {
            produto = rest.convertJsonToProduto((JSONObject) response);
            TextView textView = ((TextView) findViewById(R.id.editText));
            textView.setText(produto.toString());
        } else if (response instanceof JSONArray) {
            listaProdutos = rest.convertJsonToListaProdutos((JSONArray) response);
            TextView textView = ((TextView) findViewById(R.id.editText));
            textView.setText(listaProdutos.toString());
        }
    }


    //metodo para enviar requisição
    public void getJsonProdutos(View view) {
        rest.getJsonListaProdutos(this, this, REQUEST_TAG);
    }

    //meto para envivar requisição
    public void getJsonProduto(View view) {
        rest.getJsonProduto(id++, this, this, REQUEST_TAG);
    }
}
