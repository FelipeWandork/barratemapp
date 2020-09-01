package com.apps.barratem.model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BancoDeDados {

    // private String urlAnuncios = "https://www.barratem.com.br/api/index.php";
    private String urlAnuncios = "http://192.168.41.10/barratem.com.br/api/index.php";


    public String preencherTabelas() {

        String sql = "";


        final StringRequest stringRequest = new StringRequest(Request.Method.POST, urlAnuncios,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


                        try {
                            Log.i("DADOS", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray anuncios = jsonObject.getJSONArray("anuncios");
                            String sql = "Vamos ver";

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", error.getMessage());
                    }
                });

        return sql;
    }
}
