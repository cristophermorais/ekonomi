package net.ddns.esof.ekonomi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.ddns.esof.ekonomi.rest.classes.PrecoProd;
import net.ddns.esof.ekonomi.rest.classes.Produto;

import java.util.ArrayList;
import java.util.List;



public class ProdutoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "produto";
    private static final String DATABASE =  "app";

    public ProdutoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public void inserirProduto (Produto produto, PrecoProd prod){


        ContentValues values = new ContentValues();

        values.put("nome", produto.getNome());
        values.put("Medida", String.valueOf(produto.getMedida()));


        getWritableDatabase().insert(TABELA, null, values);
    }

    public void apagarProduto (Produto produto){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {TABELA.toString()};
        db.delete("produtos", "id=?", args);
    }

    public void alteraProduto(Produto produto){

        ContentValues values = new ContentValues();

        values.put("nome", produto.getNome());
        values.put("Medida", produto.getMedida().toString());


        String[] idParaSerAlterado = {TABELA.toString()};

        getWritableDatabase().update(TABELA, values, "id=?", idParaSerAlterado);
    }

    public void onCreate(SQLiteDatabase db) {
        String ddl = "Insert Into " + TABELA
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " nome TEXT NOT NULL, "
                + " unMedida TEXT);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Produto> getLista(){

        List<Produto> produtos = new ArrayList<Produto>();
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA + ";", null);

        while ((c.moveToNext())) {
            Produto produto= new Produto();
            produto.setId((int) c.getLong(c.getColumnIndex("id")));
            produto.setNome(c.getString(c.getColumnIndex("nome")));
            //produto.setMedida(c.getUnMedida(c.getColumnIndex("medida")));

            produtos.add(produto);
        }
        c.close();
        return produtos;
    }




}
