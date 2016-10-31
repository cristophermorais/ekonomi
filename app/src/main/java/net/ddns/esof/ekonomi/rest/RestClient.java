package net.ddns.esof.ekonomi.rest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.google.gson.Gson;

import net.ddns.esof.ekonomi.rest.classes.Produto;
import net.ddns.esof.ekonomi.rest.volley.MyJSONArrayRequest;
import net.ddns.esof.ekonomi.rest.volley.MyJSONObjectRequest;
import net.ddns.esof.ekonomi.rest.volley.MyVolleyRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestClient {
    private Gson gson;
    private final String baseUrl = "http://192.168.0.101:8080/ekonomi/rest";
    private static RestClient mInstance;
    private Context mContext;
    private MyVolleyRequestQueue mQueue;

    private RestClient(Context context) {
        mQueue = MyVolleyRequestQueue.getInstance(context);
        mContext = context;
        gson = new Gson();
    }

    public static synchronized RestClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RestClient(context);
        }
        return mInstance;
    }

    public void getJsonListaProdutos(Response.Listener<JSONArray> listener,
                                     Response.ErrorListener errorListener,
                                     String REQUEST_TAG) {

        RequestQueue q = MyVolleyRequestQueue.getInstance(mContext).getRequestQueue();
        String url = getAbsoluteUrl("/produto/listar");
        final MyJSONArrayRequest jsonRequest = new MyJSONArrayRequest(Request.Method.GET,
                url, new JSONArray(), listener, errorListener);
        jsonRequest.setTag(REQUEST_TAG);
        q.add(jsonRequest);
    }

    public void getJsonProduto(int id,
                               Response.Listener<JSONObject> listener,
                               Response.ErrorListener errorListener,
                               String REQUEST_TAG) {

        RequestQueue q = MyVolleyRequestQueue.getInstance(mContext).getRequestQueue();
        String url = getAbsoluteUrl("/produto/" + id);
        final MyJSONObjectRequest jsonRequest = new MyJSONObjectRequest(Request.Method.GET,
                url, new JSONObject(), listener, errorListener);
        jsonRequest.setTag(REQUEST_TAG);
        q.add(jsonRequest);
    }

    public Produto convertJsonToProduto(JSONObject json) {
        return gson.fromJson(json.toString(), Produto.class);
    }

    public List<Produto> convertJsonToListaProdutos(JSONArray array) {
        ArrayList<Produto> lista = new ArrayList<>();

        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject json = (JSONObject) array.get(i);
                lista.add(convertJsonToProduto(json));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return lista;
    }


    public void cancelAll(String REQUEST_TAG) {
        mQueue.cancelAll(REQUEST_TAG);
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return baseUrl + relativeUrl;
    }
}
