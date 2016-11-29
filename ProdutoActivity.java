package net.ddns.esof.ekonomi;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);


        rest = RestClient.getInstance(this.getApplicationContext());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        rest.cancelAll(REQUEST_TAG);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
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


    private ListView minhaLista;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Produto produtoSelecionado = (Produto) minhaLista.getAdapter().getItem(info.position);

        //final MenuItem itemLigar = menu.add("Ligar para Contato");
        //final MenuItem itemSMS = menu.add("Enviar SMS para Contato");
        //final MenuItem itemSite = menu.add("Ir para Site");
        final MenuItem itemApagar = menu.add("Apagar Produto");

        itemApagar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                new AlertDialog.Builder(ProdutoActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Apagar")
                        .setMessage("Deseja apagar o Produto selecionado?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ProdutoDAO dao= new ProdutoDAO(ProdutoActivity.this);
                                dao.apagarProduto(produtoSelecionado);
                                dao.close();
                                carregaLista();
                            }
                        }).setNegativeButton("Não", null).show();
                return false;
            }
        });
    }


    private void carregaLista() {
        ProdutoDAO dao = new ProdutoDAO(this);
        List<Produto> produto = dao.getLista();
        dao.close();
        ProdutoAdaptador adapter = new ProdutoAdaptador(this, produto);

        this.minhaLista.setAdapter(adapter);
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Produto Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }
}
