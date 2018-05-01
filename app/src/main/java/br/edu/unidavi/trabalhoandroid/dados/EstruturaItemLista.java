package br.edu.unidavi.trabalhoandroid.dados;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.unidavi.trabalhoandroid.R;

public class EstruturaItemLista extends RecyclerView.ViewHolder {

    public TextView marca;
    public TextView modelo;
    public TextView ano;
    public ImageView imagem;

    public EstruturaItemLista (View itemView) {
        super(itemView);

        marca = itemView.findViewById(R.id.txtMarca);
        modelo = itemView.findViewById(R.id.txtModelo);
        ano = itemView.findViewById(R.id.txtAno);
        imagem = itemView.findViewById(R.id.imagem_icone);
    }
}