package com.apps.barratem.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.apps.barratem.R;
import com.apps.barratem.helper.CadastroDAO;
import com.apps.barratem.helper.Singleton;
import com.apps.barratem.model.Cadastro;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.json.JSONException;

public class CadastroAnunciante extends AppCompatActivity {

    private TextView anunciante;
    private TextView descricao;
    private TextView endereco;
    private TextView numero;
    private TextView bairro;
    private TextView cep;
    private TextView telefone1;
    private TextView telefone2;
    private SwitchMaterial whatapp1;
    private SwitchMaterial whatapp2;
    private TextView email;
    private TextView website;
    private TextView facebook;
    private TextView instagram;

    CadastroDAO cadastroDAO = new CadastroDAO();
    Cadastro cadastro = new Cadastro();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_anunciante);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_cadastrar, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @SuppressLint("WrongViewCast")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        anunciante = findViewById(R.id.textAnunciante);
        descricao = findViewById(R.id.textDescricao);
        endereco = findViewById(R.id.textEndereco);
        numero = findViewById(R.id.textNumero);
        bairro = findViewById(R.id.textBairro);
        cep = findViewById(R.id.textCep);
        telefone1 = findViewById(R.id.textTelefone1);
        telefone2 = findViewById(R.id.textTelefone2);
        whatapp1 = findViewById(R.id.whatsappTelefone1);
        whatapp2 = findViewById(R.id.whatsappTelefone2);
        email = findViewById(R.id.textEmail);
        website = findViewById(R.id.textWebsite);
        facebook = findViewById(R.id.textFacebook);
        instagram = findViewById(R.id.textInstagram);

        cadastro.setAnunciante(anunciante.getText().toString());
        cadastro.setDescricao(descricao.getText().toString());
        cadastro.setEndereco(endereco.getText().toString());
        cadastro.setNumero(numero.getText().toString());
        cadastro.setBairro(bairro.getText().toString());
        cadastro.setCep(cep.getText().toString());
        cadastro.setTelefone1(telefone1.getText().toString());
        cadastro.setTelefone2(telefone2.getText().toString());
        cadastro.setWhatsapp1(String.valueOf(whatapp1.isChecked()));
        cadastro.setWhatsapp2(String.valueOf(whatapp2.isChecked()));
        cadastro.setEmail(email.getText().toString());
        cadastro.setWebsite(website.getText().toString());
        cadastro.setFacebook(facebook.getText().toString());
        cadastro.setInstagram(instagram.getText().toString());

        try {
            cadastroDAO.receberFormulario(cadastro);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(Singleton.getCtx(), MainActivity.class);
        startActivity(intent);


        return super.onOptionsItemSelected(item);
    }
}