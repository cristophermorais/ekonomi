package net.ddns.esof.ekonomi.rest.volley;

import com.google.gson.Gson;

import net.ddns.esof.ekonomi.rest.model.Cidade;
import net.ddns.esof.ekonomi.rest.model.PrecoProd;
import net.ddns.esof.ekonomi.rest.model.Produto;
import net.ddns.esof.ekonomi.rest.model.Supermercado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexa on 09/11/2016.
 */

public class JsonConverter {
    private static  final Gson gson = new Gson();


    public static Produto convertJsonToProduto(JSONObject json) {
        return gson.fromJson(json.toString(), Produto.class);
    }

    public static List<Produto> convertJsonToListaProdutos(JSONArray array) throws JSONException {
        ArrayList<Produto> lista = new ArrayList<>();

        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject json = (JSONObject) array.get(i);
                lista.add(convertJsonToProduto(json));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw e;
        }

        return lista;
    }

    public static Cidade convertJsonToCidade(JSONObject json) {
        return gson.fromJson(json.toString(), Cidade.class);
    }

    public static  List<Cidade> convertJsonToListaCidades(JSONArray array) throws JSONException {
        ArrayList<Cidade> lista = new ArrayList<>();

        try{
            for(int i = 0; i < array.length(); i++){
                JSONObject json = (JSONObject) array.get(i);
                lista.add(convertJsonToCidade(json));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw e;
        }

        return lista;
    }

    public static List<Supermercado> convertJsonToListaSupermercados(JSONArray array) throws JSONException {
        ArrayList<Supermercado> lista = new ArrayList<>();

        try{
            for(int i = 0; i < array.length(); i++){
                JSONObject json = (JSONObject) array.get(i);
                Supermercado supermercado = gson.fromJson(json.toString(), Supermercado.class);
                lista.add(supermercado);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw e;
        }

        return lista;
    }

    public static List<PrecoProd> converJsonToListaPrecos(JSONArray array) throws JSONException {
        ArrayList<PrecoProd> lista = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject json = (JSONObject) array.get(i);
                PrecoProd precoProd = gson.fromJson(json.toString(), PrecoProd.class);
                lista.add(precoProd);
            }
        }catch (JSONException e){
            e.printStackTrace();
            throw e;
        }

        return lista;
    }
}
