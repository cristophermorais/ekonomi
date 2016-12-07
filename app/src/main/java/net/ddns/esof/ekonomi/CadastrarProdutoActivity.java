package net.ddns.esof.ekonomi;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.ddns.esof.ekonomi.rest.RestClient;


import static net.ddns.esof.ekonomi.R.id.btProduto;
import static net.ddns.esof.ekonomi.R.id.editText;
import static net.ddns.esof.ekonomi.R.id.editTextNome;
import static net.ddns.esof.ekonomi.R.id.editTextPreco;
import static net.ddns.esof.ekonomi.R.id.editUnMedida;
import static net.ddns.esof.ekonomi.R.id.id;

public class CadastrarProdutoActivity extends Activity{

    private final String REQUEST_TAG = "CadastrarProdutoActivity";
    private RestClient rest;

    private EditText nomeProd;
    private EditText precoProd;
    private EditText unMedidaProd;
    private Button cad;
    private ListView l;

    private SQLiteDatabase app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_cadastrar_produto);
       // rest = RestClient.getInstance(this.getApplicationContext());


        nomeProd = (EditText) findViewById(R.id.editTextNome);
        precoProd = (EditText) findViewById(R.id.editTextPreco);
        unMedidaProd = (EditText) findViewById(R.id.editUnMedida);

        cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {


                    app = openOrCreateDatabase("ekonomi", MODE_PRIVATE, null);
                    app.execSQL("CREATE TABLE IF NOT EXIST produto(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(50), preco double, unidade_Medida VARCHAR(10))");

                    String produto = nomeProd.getText().toString();
                    String preco = precoProd.getText().toString();
                    String unMedida = unMedidaProd.getText().toString();


                    app.execSQL("INSERT INTO produto (nome, preco, unidade_Medida) VALUES ('" + nomeProd + precoProd + unMedidaProd + "') ");

                    Cursor cursor = app.rawQuery("SELECT * FROM produto", null);
                    int indiceColunaID = cursor.getColumnIndex("id");
                    int indiceColunaNome = cursor.getColumnIndex("nome");
                    int indiceColunaPreco = cursor.getColumnIndex("preco");
                    int indiceColunaUnMedida = cursor.getColumnIndex("unidade_Medida");

                    cursor.moveToFirst();
                    while (cursor != null) {
                        Log.i("LogX", "ID: " + cursor.getString(indiceColunaID) + "Nome: " + cursor.getString(indiceColunaNome) + "preco: " + cursor.getString(indiceColunaPreco) + "unMedida: " + cursor.getString(indiceColunaUnMedida));

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }



            }
        });





    }



}
