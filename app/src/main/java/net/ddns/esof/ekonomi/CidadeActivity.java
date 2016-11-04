package net.ddns.esof.ekonomi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.ddns.esof.ekonomi.rest.RestClient;
import net.ddns.esof.ekonomi.rest.classes.Cidade;
import net.ddns.esof.ekonomi.rest.classes.Produto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CidadeActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener{
    private final String REQUEST_TAG = "CidadeActivity";
    private RestClient rest;
    private String array_spinner[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidade);

        rest = RestClient.getInstance(this.getApplicationContext());

        array_spinner=new String[5];
        array_spinner[0]="MG";
        array_spinner[1]="SP";
        array_spinner[2]="RJ";
        array_spinner[3]="PR";
        array_spinner[4]="GO";
        Spinner s = (Spinner) findViewById(R.id.cbEstados);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        rest.cancelAll(REQUEST_TAG);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        TextView textView = (TextView) findViewById(R.id.editText);
        String e = error.toString();
        textView.setText(e);
    }

    @Override
    public void onResponse(Object response) {
        Cidade cidade;
        List<Cidade> listaCidades;

        try {
            if (response instanceof JSONObject) {
                cidade = rest.convertJsonToCidade((JSONObject) response);
                TextView textView = ((TextView) findViewById(R.id.editText));
                textView.setText(cidade.toString());
            } else if (response instanceof JSONArray) {

                listaCidades = rest.convertJsonToListaCidades((JSONArray) response);
                TextView textView = ((TextView) findViewById(R.id.editText));
                textView.setText(listaCidades.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getJsonListaCidades(View view){
        String estado = (String)((Spinner)findViewById(R.id.cbEstados)).getSelectedItem();
        rest.getJsonListaCidades(estado,this,this,REQUEST_TAG);
    }
}
