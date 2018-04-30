package br.edu.unidavi.trabalhoandroid.dados;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.activitys.EstruturaItemLista;
import br.edu.unidavi.trabalhoandroid.eventbus.Carro;

public class CarroAdapter extends RecyclerView.Adapter<EstruturaItemLista> {

    public List<Carro> carroLista;
    Context context;

    public CarroAdapter (List<Carro> carroLista, Context context) {
        this.carroLista = carroLista;
        this.context = context;
    }

    @Override
    public EstruturaItemLista onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_estrutura_item_lista, parent,false);

        EstruturaItemLista estruturaItemLista = new EstruturaItemLista(view);

        return estruturaItemLista;
    }

    @Override
    public void onBindViewHolder(EstruturaItemLista estruturaItemLista, int posicao) {
        final Carro carro = carroLista.get(posicao);

        estruturaItemLista.marca.setText(carro.getMarca());
        estruturaItemLista.modelo.setText(carro.getModelo());
        estruturaItemLista.ano.setText(carro.getAno());

        //ESTRUTURA DE IMAGEM E TOQUE
        //Picasso.with(context).load(myMeme.getPhotoUrl()).into(holder.thumbnail);

        /*estruturaItemLista.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(myMeme.getUrl()));
                context.startActivity(webIntent);

                return true;
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return carroLista.size();
    }
}