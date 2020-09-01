package com.apps.barratem.helper;

import android.content.ContentValues;

import com.apps.barratem.model.Anuncio;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public interface IAnuncioDAO {

    public ContentValues preencherTabelas(String string) throws JSONException;

    public List<Anuncio> listarAnuncios(JSONArray jsonArray) throws JSONException;

    public List<Anuncio> listarOffline() throws JSONException;


}
