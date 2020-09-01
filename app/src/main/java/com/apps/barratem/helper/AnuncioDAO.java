package com.apps.barratem.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.recyclerview.widget.RecyclerView;

import com.apps.barratem.adapter.AnuncioAdapter;
import com.apps.barratem.model.Anuncio;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AnuncioDAO implements IAnuncioDAO {

    // private String urlAnuncios = "https://www.barratem.com.br/api/index.php";
    private String urlAnuncios = "http://192.168.41.10/barratem.com.br/api/index.php";

    private SQLiteDatabase preenche;
    private SQLiteDatabase recupera;

    private RecyclerView recyclerView;
    private AnuncioAdapter anuncioAdapter;
    private List<Anuncio> listaAnuncios = new ArrayList<>();

    public AnuncioDAO(Context context) {
        SQLHelper db = new SQLHelper(context);
        preenche = db.getWritableDatabase();
        recupera = db.getReadableDatabase();
    }

    @Override
    public ContentValues preencherTabelas(String dados) throws JSONException {

        JSONObject jsonObject = new JSONObject(dados);
        JSONArray anuncios = jsonObject.getJSONArray("anuncios");

        ContentValues cv = new ContentValues();


        for(int i = 0 ; i < anuncios.length() ; i++) {
            JSONObject anuncio = anuncios.getJSONObject(i);
            int id = anuncio.getInt("id_anuncio");
            String empresa = anuncio.getString("empresa_anuncio");
            String descricao = anuncio.getString("descricao_anuncio");
            String endereco = anuncio.getString("endereco_anuncio");
            String numero = anuncio.getString("num");
            String complemento = anuncio.getString("complemento");
            String bairro = anuncio.getString("bairro");
            String cidade = anuncio.getString("cidade");
            String uf = anuncio.getString("uf");
            String cep = anuncio.getString("cep");
            String telefone1 = anuncio.getString("tel1_anuncio");
            String telefone2 = anuncio.getString("tel2_anuncio");
            String telefone3 = anuncio.getString("telefoneclaro");
            String telefone4 = anuncio.getString("telefonevivo");
            String whatsapp1 = anuncio.getString("whatsapp_oi");
            String whatsapp2 = anuncio.getString("whatsapp_claro");
            String whatsapp3 = anuncio.getString("whatsapp_vivo");
            String whatsapp4 = anuncio.getString("whatsapp_tim");
            String email = anuncio.getString("email_anuncio");
            String website = anuncio.getString("site");
            String latitude = anuncio.getString("latitude");
            String longitude = anuncio.getString("longitude");
            String facebook = anuncio.getString("facebook");
            String instagram = anuncio.getString("instagram");
            String status = anuncio.getString("status");
            String categoria = anuncio.getString("categoria1");

            cv.put("id", id);
            cv.put("empresa", empresa);
            cv.put("descricao", descricao);
            cv.put("endereco", endereco);
            cv.put("numero", numero);
            cv.put("complemento", complemento);
            cv.put("cidade", cidade);
            cv.put("bairro", bairro);
            cv.put("uf", uf);
            cv.put("cep", cep);
            cv.put("email", email);
            cv.put("website", website);
            cv.put("facebook", facebook);
            cv.put("instagram", instagram);
            cv.put("latitude", latitude);
            cv.put("longitude", longitude);
            cv.put("categoria", categoria);
            cv.put("telefone1", telefone1);
            cv.put("telefone2", telefone2);
            cv.put("telefone3", telefone3);
            cv.put("telefone4", telefone4);
            cv.put("whatsapp_1", whatsapp1);
            cv.put("whatsapp_2", whatsapp2);
            cv.put("whatsapp_3", whatsapp3);
            cv.put("whatsapp_4", whatsapp4);
            cv.put("status", status);

            try {
                preenche.insert(SQLHelper.TABELA_ANUNCIOS, null, cv);
            } catch (Exception e){
                e.printStackTrace();
            }

/*
            itemAnuncio.setId(id);
            itemAnuncio.setEmpresa(empresa);
            itemAnuncio.setDescricao(descricao);
            itemAnuncio.setEndereco(endereco);
            itemAnuncio.setNumero(numero);
            itemAnuncio.setComplemento(complemento);
            itemAnuncio.setBairro(bairro);
            itemAnuncio.setCep(cep);
            itemAnuncio.setUf(uf);
            itemAnuncio.setCidade(cidade);
            itemAnuncio.setLatitude(latitude);
            itemAnuncio.setLongitude(longitude);
            itemAnuncio.setEmail(email);
            itemAnuncio.setTelefone1(telefone1);
            itemAnuncio.setTelefone2(telefone2);
            itemAnuncio.setTelefone3(telefone3);
            itemAnuncio.setTelefone4(telefone4);
            itemAnuncio.setWhatsapp1(whatsapp1);
            itemAnuncio.setWhatsapp2(whatsapp2);
            itemAnuncio.setWhatsapp3(whatsapp3);
            itemAnuncio.setWhatsapp4(whatsapp4);
            itemAnuncio.setFacebook(facebook);
            itemAnuncio.setInstagram(instagram);
            itemAnuncio.setWebsite(website);
            itemAnuncio.setStatus(status);
            itemAnuncio.setCategoria(categoria);
*/

        }
        return cv;
    }

    @Override
    public List<Anuncio> listarAnuncios(JSONArray anuncios) throws JSONException {

        List<Anuncio> listaAnuncios = new ArrayList<>();

        for (int i = 0; i < anuncios.length(); i++) {
            Anuncio itemAnuncio = new Anuncio();
            JSONObject anuncio = anuncios.getJSONObject(i);

            Long id = anuncio.getLong("id_anuncio");
            itemAnuncio.setId(id);

            String empresa = anuncio.getString("empresa_anuncio");
            itemAnuncio.setEmpresa(empresa);

            String descricao = anuncio.getString("descricao_anuncio");
            itemAnuncio.setDescricao(descricao);

            String endereco = anuncio.getString("endereco_anuncio");
            itemAnuncio.setEndereco(endereco);

            String numero = anuncio.getString("num");
            itemAnuncio.setNumero(numero);

            String bairro = anuncio.getString("bairro");
            itemAnuncio.setBairro(bairro);

            String complemento = anuncio.getString("complemento");
            itemAnuncio.setComplemento(complemento);

            String cidade = anuncio.getString("cidade");
            itemAnuncio.setCidade(cidade);

            String cep = anuncio.getString("cep");
            itemAnuncio.setCep(cep);

            String uf = anuncio.getString("uf");
            itemAnuncio.setUf(uf);

            String telefone1 = anuncio.getString("tel1_anuncio");
            itemAnuncio.setTelefone1(telefone1);

            String telefone2 = anuncio.getString("tel2_anuncio");
            itemAnuncio.setTelefone2(telefone2);

            String latitude = anuncio.getString("latitude");
            itemAnuncio.setLatitude(latitude);

            String longitude = anuncio.getString("longitude");
            itemAnuncio.setLongitude(longitude);

            String email = anuncio.getString("email_anuncio");
            itemAnuncio.setEmail(email);

            listaAnuncios.add(itemAnuncio);
        }

        return listaAnuncios;
    }


    // Quando o app não detecta conexão com a internet ele lista os anúncios que estão no SQLite
    @Override
    public List<Anuncio> listarOffline() throws JSONException {

        JSONArray listaoffline = new JSONArray();

        String sql = "SELECT * FROM " + SQLHelper.TABELA_ANUNCIOS +";";
        Cursor c = recupera.rawQuery(sql, null);

        while (c.moveToNext()){
            Anuncio anuncio = new Anuncio();

            anuncio.setId(c.getLong(c.getColumnIndex("id")));
            anuncio.setEmpresa(c.getString(c.getColumnIndex("empresa")));
            anuncio.setDescricao(c.getString(c.getColumnIndex("descricao")));
            anuncio.setEndereco(c.getString(c.getColumnIndex("endereco")));
            anuncio.setNumero(c.getString(c.getColumnIndex("numero")));
            anuncio.setComplemento(c.getString(c.getColumnIndex("complemento")));
            anuncio.setBairro(c.getString(c.getColumnIndex("bairro")));
            anuncio.setCidade(c.getString(c.getColumnIndex("cidade")));
            anuncio.setUf(c.getString(c.getColumnIndex("uf")));
            anuncio.setCep(c.getString(c.getColumnIndex("cep")));
            anuncio.setEmail(c.getString(c.getColumnIndex("email")));
            anuncio.setTelefone1(c.getString(c.getColumnIndex("telefone1")));
            anuncio.setTelefone2(c.getString(c.getColumnIndex("telefone2")));
            anuncio.setTelefone3(c.getString(c.getColumnIndex("telefone3")));
            anuncio.setTelefone4(c.getString(c.getColumnIndex("telefone4")));
            anuncio.setWhatsapp1(c.getString(c.getColumnIndex("whatsapp_1")));
            anuncio.setWhatsapp2(c.getString(c.getColumnIndex("whatsapp_2")));
            anuncio.setWhatsapp3(c.getString(c.getColumnIndex("whatsapp_3")));
            anuncio.setWhatsapp4(c.getString(c.getColumnIndex("whatsapp_4")));
            anuncio.setLatitude(c.getString(c.getColumnIndex("latitude")));
            anuncio.setLongitude(c.getString(c.getColumnIndex("longitude")));
            anuncio.setCategoria(c.getString(c.getColumnIndex("categoria")));
            anuncio.setFacebook(c.getString(c.getColumnIndex("facebook")));
            anuncio.setInstagram(c.getString(c.getColumnIndex("instagram")));
            anuncio.setWebsite(c.getString(c.getColumnIndex("website")));
            anuncio.setStatus(c.getString(c.getColumnIndex("status")));

            listaoffline.put(anuncio);


        }


        String jsonLista = new Gson().toJson(listaoffline);
        JSONObject jsonObject = new JSONObject(jsonLista);
        JSONArray anuncios = jsonObject.getJSONArray("values");

        for (int i = 0; i < anuncios.length(); i++) {
            Anuncio itemAnuncio = new Anuncio();
            JSONObject anuncio = anuncios.getJSONObject(i);

            Long id = anuncio.getLong("id");
            itemAnuncio.setId(id);

            String empresa = anuncio.getString("empresa");
            itemAnuncio.setEmpresa(empresa);

            String descricao = anuncio.getString("descricao");
            itemAnuncio.setDescricao(descricao);

            String endereco = anuncio.getString("endereco");
            itemAnuncio.setEndereco(endereco);

            String numero = anuncio.getString("numero");
            itemAnuncio.setNumero(numero);

            String bairro = anuncio.getString("bairro");
            itemAnuncio.setBairro(bairro);

            String complemento = anuncio.getString("complemento");
            itemAnuncio.setComplemento(complemento);

            String cidade = anuncio.getString("cidade");
            itemAnuncio.setCidade(cidade);

            String cep = anuncio.getString("cep");
            itemAnuncio.setCep(cep);

            String uf = anuncio.getString("uf");
            itemAnuncio.setUf(uf);

            String telefone1 = anuncio.getString("telefone1");
            itemAnuncio.setTelefone1(telefone1);

            String telefone2 = anuncio.getString("telefone2");
            itemAnuncio.setTelefone2(telefone2);

            String latitude = anuncio.getString("latitude");
            itemAnuncio.setLatitude(latitude);

            String longitude = anuncio.getString("longitude");
            itemAnuncio.setLongitude(longitude);

            String email = anuncio.getString("email");
            itemAnuncio.setEmail(email);

            listaAnuncios.add(itemAnuncio);
        }



       return listaAnuncios;


    }
}
