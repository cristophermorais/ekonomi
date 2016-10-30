package net.ddns.esof.ekonomi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.ddns.esof.ekonomi.rest.RestClient;

import org.json.JSONArray;
import org.json.JSONObject;

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
    protected void onStart() {
        super.onStart();
        rest = RestClient.getInstance(this.getApplicationContext());
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

        if (response instanceof JSONObject) {
            ((TextView) findViewById(R.id.editText)).setText(response.toString());
        } else if (response instanceof JSONArray) {
            ((TextView) findViewById(R.id.editText)).setText((response).toString());
        }
    }


    public void getJsonProdutos(View view) {
        rest.getProdutos(this, this, REQUEST_TAG);
    }

    public void getJsonProduto(View view) {
        rest.getProduto(id++, this, this, REQUEST_TAG);
    }
}
