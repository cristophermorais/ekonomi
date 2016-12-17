package net.ddns.esof.ekonomi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.ddns.esof.ekonomi.rest.RestClient;
import net.ddns.esof.ekonomi.rest.classes.Cidade;
import net.ddns.esof.ekonomi.rest.volley.JsonConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CidadeActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener {
    private final String REQUEST_TAG = "CidadeActivity";
    private RestClient rest;
    private String array_spinner[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidade);

        rest = RestClient.getInstance(this.getApplicationContext());

        array_spinner = new String[5];
        array_spinner[0] = "MG";
        array_spinner[1] = "SP";
        array_spinner[2] = "RJ";
        array_spinner[3] = "PR";
        array_spinner[4] = "GO";
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
        Toast.makeText(this.getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onResponse(Object response) {
        try {
            if (response instanceof JSONArray) {

                List<Cidade> listaCidades = JsonConverter.convertJsonToListaCidades((JSONArray) response);
                final ListView listView = (ListView) findViewById(R.id.listCidades);
                ArrayAdapter<Cidade> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice, listaCidades);
                listView.setAdapter(arrayAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                        Cidade selected = (Cidade) listView.getItemAtPosition(position);

                        Intent intent = new Intent(CidadeActivity.this, TestActivity.class);
                        intent.putExtra("cidade", selected);

                        setResult(0, intent);
                        finish();
                    }
                });
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getJsonListaCidades(View view) {
        String estado = (String) ((Spinner) findViewById(R.id.cbEstados)).getSelectedItem();
        rest.getJsonListaCidades(estado, this, this, REQUEST_TAG);
    }
}
