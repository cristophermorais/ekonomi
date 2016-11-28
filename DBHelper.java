package net.ddns.esof.ekonomi.rest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import net.ddns.esof.ekonomi.rest.classes.Produto;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Silvano Jr on 20/11/2016.
 */

public class DBHelper {
    private static final String DATABASE_NAME = "ekonomi.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "lista";

    private Context context;
    private SQLiteDatabase db;


    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " + TABLE_NAME + " (campos a serem inseridos) values (?,?,?,?,?)";

    public DBHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);


    }

    public long insert(Produto p) {
        //this.insertStmt.bindString(1, nome);
        //this.insertStmt.bindString(2, empresa);
        //this.insertStmt.bindString(3, endereco);
        this.insertStmt.bindString(1, p.toString());

        return this.insertStmt.executeInsert();

    }


    public void deleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }

    /*
    public List queryGetAll(){
        List<Lista> list = new ArrayList<Lista>;

        try{
            Cursor cursor = this.db.query(TABLE_NAME, new String [] { "campo 1", "campo 2", "campo 3", "campo 4"} null, null, null, null, null, null);

            int nregistros = cursor.getCount();

            if(nregistros != 0){
                do{
                    Lista lista = new Lista (cursor.getString(1), cursor.getString(2), cursor.getString(3));
                    lista.add(Lista);

                }while(cursor.moveToText());


                if(cursor != null && ! cursor.isClosed())
                    cursor.cloe();
                return list;
            }else
                return null;


        }catch(Exception err){
            return null;
        }


    }
    */

    private static class OpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "ekonomi.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "lista";

        private Context context;
        private SQLiteStatement db;

        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, endereco TEXT, empresa TEXT );";
            db.execute();
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS \" + TABLE_NAME");
            db.execute();
        }
    }
}




