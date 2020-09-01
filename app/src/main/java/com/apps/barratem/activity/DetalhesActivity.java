package com.apps.barratem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.apps.barratem.R;
import com.apps.barratem.model.Anuncio;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetalhesActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Double latitude;
    private Double longitude;
    private String markerLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);


        TextView textEmpresa = findViewById(R.id.textViewEmpresa);
        TextView textDescricao = findViewById(R.id.textViewDescricao);
        TextView textEndereco = findViewById(R.id.textViewEndereco);
        TextView textCidade = findViewById(R.id.textViewCidade);
        TextView textCep = findViewById(R.id.textViewCep);
        TextView textTelefones = findViewById(R.id.textViewTelefones);
        TextView textCelulares = findViewById(R.id.textViewCelulares);
        TextView textEmail = findViewById(R.id.textViewEmail);
        TextView textWebsite = findViewById(R.id.textViewWebsite);


        Anuncio anuncioAtual = (Anuncio) getIntent().getSerializableExtra("anuncioSelecionado");

        latitude  = Double.valueOf(anuncioAtual.getLatitude());
        longitude = Double.valueOf(anuncioAtual.getLongitude());
        markerLocal = anuncioAtual.getEmpresa();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // Formatar TELEFONES, CELULARES, ENDEREÇO

        String endereco = anuncioAtual.getEndereco() + ", "
                + anuncioAtual.getNumero() + " - "
                + anuncioAtual.getBairro();

        if(anuncioAtual.getTelefone1() == null) {
            anuncioAtual.setTelefone1("");
        }

        if(anuncioAtual.getTelefone2() == null) {
            anuncioAtual.setTelefone2("");
        }

        if(anuncioAtual.getTelefone3() == null) {
            anuncioAtual.setTelefone3("");
        }

        if(anuncioAtual.getTelefone4() == null) {
            anuncioAtual.setTelefone4("");
        }



        String telefones = anuncioAtual.getTelefone1() + "  " + anuncioAtual.getTelefone2();
        String celulares = anuncioAtual.getTelefone3() + "  " + anuncioAtual.getTelefone4();


        textEmpresa.setText(anuncioAtual.getEmpresa());
        textDescricao.setText(anuncioAtual.getDescricao());
        textEndereco.setText(endereco);
        String cidade = "Barra do Piraí";
        textCidade.setText(cidade);
        textCep.setText(anuncioAtual.getCep());
        textTelefones.setText(telefones);
        textCelulares.setText(celulares);
        textEmail.setText(anuncioAtual.getEmail());
        textWebsite.setText(anuncioAtual.getWebsite());

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

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add a marker in ***** and move the camera
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(markerLocal));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}