package com.apps.barratem.helper;

import com.apps.barratem.model.Cadastro;

import org.json.JSONException;

public interface ICadastroDAO {

    public void receberFormulario(Cadastro cadastro) throws JSONException;

    public void enviarFormulario(String jsonString) throws JSONException;

}
