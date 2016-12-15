package net.ddns.esof.ekonomi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = new Intent(this, CidadeActivity.class);
        startActivity(intent);
    }

    public void startProduto(View view){
        Intent intent = new Intent(this, ProdutoActivity.class);
        startActivity(intent);
    }

    public void startCidade(View view){
        Intent intent = new Intent(this, CidadeActivity.class);
        startActivity(intent);
    }
}
