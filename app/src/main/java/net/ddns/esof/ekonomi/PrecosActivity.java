package net.ddns.esof.ekonomi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.ddns.esof.ekonomi.adapters.PrecoProdAdapter;
import net.ddns.esof.ekonomi.adapters.SupermAdapter;
import net.ddns.esof.ekonomi.rest.model.PrecoProd;
import net.ddns.esof.ekonomi.rest.model.PrecoSuperm;
import net.ddns.esof.ekonomi.rest.model.Supermercado;

import java.util.ArrayList;

public class PrecosActivity extends AppCompatActivity {
    private Supermercado supermercado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precos);

        Intent intent = this.getIntent();
        ArrayList<PrecoProd> precos = (ArrayList<PrecoProd>) intent.getSerializableExtra("lista");

        supermercado = precos.get(0).getSuperm();

        populateView(precos);
    }


    private void populateView(ArrayList<PrecoProd> precos){
        TextView textView = (TextView) findViewById(R.id.supermNome);
        textView.setText(precos.get(0).getSuperm().getNome());

        PrecoProdAdapter precoProdAdapter = new PrecoProdAdapter(this, precos);

        final ListView listView = (ListView) findViewById(R.id.listaPrecos);
        listView.setAdapter(precoProdAdapter);
    }

    public void startMapa(View view) {
        Intent intent = new Intent(PrecosActivity.this, MapsActivity.class);
        intent.putExtra("supermercado", supermercado);
        startActivity(intent);
    }
}
