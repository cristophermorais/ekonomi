package net.ddns.esof.ekonomi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.ddns.esof.ekonomi.R;
import net.ddns.esof.ekonomi.rest.RestClient;
import net.ddns.esof.ekonomi.rest.classes.Produto;

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

        findViewById(R.id.inputId).requestFocus();
    }

    // metodo chamado quando a requisição recebe uma resposta
    @Override
    public void onResponse(Object response) {
        Produto produto;
        List<Produto> listaProdutos;

        try {
            if (response instanceof JSONObject) {
                produto = rest.convertJsonToProduto((JSONObject) response);
                TextView textView = ((TextView) findViewById(R.id.editText));
                textView.setText(produto.toString());
            } else if (response instanceof JSONArray) {

                listaProdutos = rest.convertJsonToListaProdutos((JSONArray) response);
                TextView textView = ((TextView) findViewById(R.id.editText));
                textView.setText(listaProdutos.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        findViewById(R.id.inputId).requestFocus();
    }

    //metodo para enviar requisição
    public void getJsonProdutos(View view) {
        rest.getJsonListaProdutos(this, this, REQUEST_TAG);
    }

    //meto para envivar requisição
    public void getJsonProdutoById(View view) {
        findViewById(R.id.inputId).clearFocus();
        TextView textView = (TextView) findViewById(R.id.inputId);
        int id = Integer.parseInt(textView.getText().toString());
        rest.getJsonProduto(id, this, this, REQUEST_TAG);

    }
}
