package com.apps.barratem.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apps.barratem.R;
import com.apps.barratem.adapter.AnuncioAdapter;
import com.apps.barratem.helper.AnuncioDAO;
import com.apps.barratem.helper.RecyclerItemClickListener;
import com.apps.barratem.helper.SQLHelper;
import com.apps.barratem.helper.Singleton;
import com.apps.barratem.model.Anuncio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity  extends AppCompatActivity  {

    private static final String DEBUG_TAG = "NetworkStatusExample";
    private SQLiteDatabase con;
    private SQLHelper bd;
    private EditText pesquisa;
    private RecyclerView recyclerView;
    private AnuncioAdapter anuncioAdapter;
    private List<Anuncio> listaAnuncios = new ArrayList<>();
    private Anuncio anuncioSelecionado;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter anunciosAdapter;
    private TextView empresa, endereco, complemento, bairro, cep, email, telefone;

    public String urlAnuncios = "https://www.barratem.com.br/api/index.php";
    // public String urlAnuncios = "http://192.168.41.10/barratem.com.br/api/index.php";

    public RequestQueue requestQueue;
    public AnuncioDAO anuncioDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // PEGANDO DATA DO SISTEMA PARA ARMAZENAR NO PRÉ CADASTRO
//        SimpleDateFormat time_formatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm");
//        String current_time_str = time_formatter.format(System.currentTimeMillis());
//        Log.i("tempo", "current_time_str:" + current_time_str);

        Button botaoPesquisar = findViewById(R.id.botaoPesquisar);
        pesquisa = findViewById(R.id.textPesquisa);
        recyclerView = findViewById(R.id.recyclerAnuncios);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Anuncio anuncioSelecionado = listaAnuncios.get(position);
                                Intent intent = new Intent(getApplicationContext(), DetalhesActivity.class);

                                // passar os dados do anúncio clicado
                                intent.putExtra("anuncioSelecionado", anuncioSelecionado );
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {


                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

        botaoPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String palavra = pesquisa.getText().toString().trim();


                if(palavra.isEmpty()){
                    try {
                        listarAnuncios();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listarPesquisa(palavra);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemCadastrar:
                Intent intent = new Intent(getApplicationContext(), CadastroAnunciante.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void listarPesquisa(final String palavra) {
        RequestQueue requestQueue = Singleton.getInstance(this.getApplicationContext()).getRequestQueue();
        anuncioDAO = new AnuncioDAO(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,  urlAnuncios,
           new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    responseRequest(response);
                }
        }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("ERROR", error.getMessage());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("script", "pesquisa");
                    params.put("palavra", palavra);
                    return params;
                }
            };
        Singleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public List<Anuncio> downloadAnuncios(){
        RequestQueue requestQueue = Singleton.getInstance(this.getApplicationContext()).getRequestQueue();
        anuncioDAO = new AnuncioDAO(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,  urlAnuncios,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String dados = response.substring(0);
                        try {
                            JSONObject jsonObject = new JSONObject(dados);
                            JSONArray anuncios = jsonObject.getJSONArray("anuncios");
                            listaAnuncios = anuncioDAO.listarAnuncios(anuncios);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage());
            }
        });
        Singleton.getInstance(this).addToRequestQueue(stringRequest);
        return listaAnuncios;
    }

    public void listarAnuncios() throws JSONException {
        // AQUI DENTRO VOU ANALISAR O ESTADO DA CONEXÃO E EM SEGUIDA DECIDIR SE UTILIZO O BANCO ONLINE OU OFFLINE
        boolean retorno = isOnline();
        if (retorno) {
            RequestQueue requestQueue = Singleton.getInstance(this.getApplicationContext()).getRequestQueue();
            anuncioDAO = new AnuncioDAO(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlAnuncios,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                anuncioDAO.preencherTabelas(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            responseRequest(response);
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
                            params.put("script", "principal");
                            return params;
                        }
                    };
            Singleton.getInstance(this).addToRequestQueue(stringRequest);
        } else {
            AnuncioDAO anuncioDAO = new AnuncioDAO(getApplicationContext());

            listaAnuncios = anuncioDAO.listarOffline();

            anuncioAdapter = new AnuncioAdapter(listaAnuncios);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            this.recyclerView.setLayoutManager(layoutManager);
            this.recyclerView.setHasFixedSize(true);
            this.recyclerView.setAdapter(anuncioAdapter);
        }
    }


    @Override
    protected void onStart() {
        try {
            listarAnuncios();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onStart();
    }

    protected void responseRequest(String response) {

        JSONArray anuncios = null;
        // O PHP está colocando alguns caracteres antes da mensagem JSON, por isso estou pegando a string JSON a partir da posição 3
        // Ficar atento ao passar para o servidor da Locaweb, pois ele retorna 3 caracteres antes do JSON
        String dados = response.substring(0);

        try {
            JSONObject jsonObject = new JSONObject(dados);
            anuncios = jsonObject.getJSONArray("anuncios");

            listaAnuncios = anuncioDAO.listarAnuncios(anuncios);
            anuncioAdapter = new AnuncioAdapter(listaAnuncios);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(anuncioAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnline() {

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());

    }
}