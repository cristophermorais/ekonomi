package net.ddns.esof.ekonomi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.ddns.esof.ekonomi.rest.classes.Cidade;
import net.ddns.esof.ekonomi.rest.classes.Produto;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    Cidade cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        cidade = null;

        final ListView listView = (ListView) findViewById(R.id.listViewLista);
        ListaComprasAdapter listaComprasAdapter = new ListaComprasAdapter(this, new ArrayList<Produto>());
        listView.setAdapter(listaComprasAdapter);

        startActivityForResult(new Intent(this, CidadeActivity.class), 1);
    }

    @Override
    public void onBackPressed(){

    }

    @Override
    public void onActivityResult(int id, int codResposta, Intent intent){
        switch(id) {
            case 1:
                if(codResposta == 0) {
                    cidade = (Cidade) intent.getSerializableExtra("cidade");
                    TextView textView = (TextView) findViewById(R.id.cidadeView);
                    textView.setText(cidade.getNome());
                }
                break;

            case 2:
                if(codResposta == 0) {
                    Produto p = (Produto) intent.getSerializableExtra("produto");
                    ListView listView = (ListView) findViewById(R.id.listViewLista);

                    ArrayAdapter<Produto> adapter = (ArrayAdapter<Produto>) listView.getAdapter();
                    adapter.add(p);
                }
                break;

            default:
                break;
        }
    }

    public void calcPrecos(View view){
        ListView listView = (ListView) findViewById(R.id.listViewLista);
        ArrayList<Produto> listaProdutos = ((ListaComprasAdapter)listView.getAdapter()).cloneProdutos();

        if(listView.getAdapter().getCount() > 0){
            Intent intent = new Intent(this, ResultadoActivity.class);
            intent.putExtra("listaProdutos", listaProdutos);
            intent.putExtra("idCidade", cidade.getId());
            startActivity(intent);
        }else{
            Toast.makeText(this, "Lista de compras vazia!", Toast.LENGTH_LONG).show();
        }

    }

    public void startProduto(View view){
        startActivityForResult(new Intent(this, ProdutoActivity.class), 2);
    }
}
