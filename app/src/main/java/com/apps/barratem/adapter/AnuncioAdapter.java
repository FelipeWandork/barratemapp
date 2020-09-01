package com.apps.barratem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.apps.barratem.R;
import com.apps.barratem.model.Anuncio;

import java.util.List;

public class AnuncioAdapter extends RecyclerView.Adapter<AnuncioAdapter.MyViewHolder> {

    private final List<Anuncio> listaAnuncios;

    public AnuncioAdapter(List<Anuncio> lista) {

        this.listaAnuncios = lista;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemAnuncios = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_anuncios, parent, false);

        return new MyViewHolder(itemAnuncios);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Anuncio anuncio = listaAnuncios.get(position);

        holder.empresa.setText(anuncio.getEmpresa());
        holder.endereco.setText(anuncio.getEndereco() + ", "+ anuncio.getNumero() + " - " + anuncio.getBairro());
        holder.complemento.setText(anuncio.getComplemento());
//        holder.bairro.setText(anuncio.getBairro());
//        holder.cep.setText(anuncio.getCep());
        holder.email.setText(anuncio.getEmail());
        holder.telefone.setText(anuncio.getTelefone1());

    }

    @Override
    public int getItemCount() {

        return this.listaAnuncios.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final TextView empresa;
        final TextView endereco;
        final TextView complemento;
//        TextView bairro;
//        TextView cep;
final TextView telefone;
        final TextView email;

        public MyViewHolder(View itemView) {
            super(itemView);

            empresa = itemView.findViewById(R.id.textEmpresa);
            endereco = itemView.findViewById(R.id.textEndereco);
            complemento = itemView.findViewById(R.id.textComplemento);
  //          bairro = itemView.findViewById(R.id.textBairro);
  //          cep = itemView.findViewById(R.id.textCep);
            telefone = itemView.findViewById(R.id.textTelefone);
            email = itemView.findViewById(R.id.textEmail);
        }
    }

}
