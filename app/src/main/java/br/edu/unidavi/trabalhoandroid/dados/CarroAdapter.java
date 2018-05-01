package br.edu.unidavi.trabalhoandroid.dados;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.activitys.CarroDetalheActivity;
import br.edu.unidavi.trabalhoandroid.eventbus.Carro;

public class CarroAdapter extends RecyclerView.Adapter<EstruturaItemLista> {

    public List<Carro> carroLista;
    private Context context;

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

        //IMAGEM
        Picasso.with(context)
                .load(carro.getImagem())
                .into(estruturaItemLista.imagem);

        //AO CLICAR NO ITEM
        estruturaItemLista.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String teste = carro.getModelo();
                Log.d("MODELO ==== ", teste);

                Carro carroDet = new Carro();
                carroDet.setMarca(carro.getMarca());
                carroDet.setModelo(carro.getModelo());
                carroDet.setAno(carro.getAno());
                carroDet.setImagem(carro.getImagem());
                EventBus.getDefault().postSticky(carroDet);

                final Intent carroDetalhe;
                carroDetalhe =  new Intent(context, CarroDetalheActivity.class);
                context.startActivity(carroDetalhe);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carroLista.size();
    }
}