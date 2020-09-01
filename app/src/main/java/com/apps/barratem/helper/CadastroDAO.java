package com.apps.barratem.helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apps.barratem.model.Cadastro;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastroDAO implements ICadastroDAO {

    private String urlCadastro = "https://www.barratem.com.br/api/index.php";
//    private String urlCadastro = "http://192.168.41.10/barratem.com.br/api/index.php";
    private List<Cadastro> cadastroList = new ArrayList<>();

    private Context context;

    @Override
    public void receberFormulario(Cadastro cadastro) throws JSONException {
        cadastroList.add(cadastro);
        String stringJson = new Gson().toJson(cadastroList);
        enviarFormulario(stringJson);
    }

    @Override
    public void enviarFormulario(final String stringJson) throws JSONException {
        RequestQueue requestQueue = Singleton.getInstance(this.context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlCadastro,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Singleton.getCtx(), response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", error.getMessage());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("script", "cadastro");
                        params.put("cadastro", stringJson);
                        return params;
                    }
                };
        Singleton.getInstance(this.context).addToRequestQueue(stringRequest);


    }


}
