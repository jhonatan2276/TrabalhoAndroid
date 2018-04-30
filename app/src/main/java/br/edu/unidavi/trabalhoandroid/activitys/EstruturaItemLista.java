package br.edu.unidavi.trabalhoandroid.activitys;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.unidavi.trabalhoandroid.R;

public class EstruturaItemLista extends RecyclerView.ViewHolder {

    public ImageView imagem;
    public TextView marca;
    public TextView modelo;
    public TextView ano;

    public EstruturaItemLista (View itemView) {
        super(itemView);

        imagem = itemView.findViewById(R.id.imagem_icone);
        marca = itemView.findViewById(R.id.txtMarca);
        modelo = itemView.findViewById(R.id.txtModelo);
        ano = itemView.findViewById(R.id.txtAno);
    }
}