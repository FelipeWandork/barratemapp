package com.apps.barratem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.apps.barratem.R;
import com.apps.barratem.model.Anuncio;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
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

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        TextView textEmpresa = findViewById(R.id.textViewEmpresa);
        TextView textDescricao = findViewById(R.id.textViewDescricao);
        TextView textEndereco = findViewById(R.id.textViewEndereco);
        TextView textCidade = findViewById(R.id.textViewCidade);
        TextView textCep = findViewById(R.id.textViewCep);
        TextView textTelefones = findViewById(R.id.textViewTelefones);
        TextView textCelulares = findViewById(R.id.textViewCelulares);
        TextView textEmail = findViewById(R.id.textViewEmail);
        TextView textWebsite = findViewById(R.id.textViewWebsite);
        TextView textComplemento = findViewById(R.id.textComplemento);

        Anuncio anuncioAtual = (Anuncio) getIntent().getSerializableExtra("anuncioSelecionado");

        if(anuncioAtual.getLatitude() == ""){
            anuncioAtual.setLatitude("-22.4708306");
        }

        if(anuncioAtual.getLongitude() == ""){
            anuncioAtual.setLongitude("-43.8259889");
        }

        latitude  = Double.valueOf(anuncioAtual.getLatitude());
        longitude = Double.valueOf(anuncioAtual.getLongitude());
        markerLocal = anuncioAtual.getEmpresa();

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
       Log.i("INFO", "complemento: " + anuncioAtual.getComplemento());
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
        LatLng latLng = new LatLng(latitude, longitude);
        GoogleMapOptions options = new GoogleMapOptions();
        options.zoomControlsEnabled(false)
                .camera(CameraPosition.fromLatLngZoom(latLng, (float) 17.0))
                .zoomControlsEnabled(true)
                .mapType(1)
                .minZoomPreference(17);

        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(markerLocal));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));


    }
}