package net.ddns.esof.ekonomi.rest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import net.ddns.esof.ekonomi.rest.volley.MyJSONArrayRequest;
import net.ddns.esof.ekonomi.rest.volley.MyJSONObjectRequest;
import net.ddns.esof.ekonomi.rest.volley.MyVolleyRequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

public class RestClient {
    private final String baseUrl = "http://192.168.0.101:8080/ekonomi/rest";
    private static RestClient mInstance;
    private Context mContext;
    private MyVolleyRequestQueue mQueue;

    private RestClient(Context context) {
        mQueue = MyVolleyRequestQueue.getInstance(context);
        mContext = context;
    }

    public static synchronized RestClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RestClient(context);
        }
        return mInstance;
    }


    public void getProdutos(Response.ErrorListener errorListener,
                            Response.Listener<JSONArray> listener,
                            String REQUEST_TAG) {

        RequestQueue q = MyVolleyRequestQueue.getInstance(mContext).getRequestQueue();
        String url = getAbsoluteUrl("/produto/listar");
        final MyJSONArrayRequest jsonRequest = new MyJSONArrayRequest(Request.Method.GET,
                url, new JSONArray(), listener, errorListener);
        jsonRequest.setTag(REQUEST_TAG);
        q.add(jsonRequest);
    }

    public void getProduto(int id, Response.ErrorListener errorListener,
                           Response.Listener<JSONObject> listener,
                           String REQUEST_TAG) {

        RequestQueue q = MyVolleyRequestQueue.getInstance(mContext).getRequestQueue();
        String url = getAbsoluteUrl("/produto/" + id);
        final MyJSONObjectRequest jsonRequest = new MyJSONObjectRequest(Request.Method.GET,
                url, new JSONObject(), listener, errorListener);
        jsonRequest.setTag(REQUEST_TAG);
        q.add(jsonRequest);
    }


    public void cancelAll(String REQUEST_TAG) {
        mQueue.cancelAll(REQUEST_TAG);
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return baseUrl + relativeUrl;
    }
}
