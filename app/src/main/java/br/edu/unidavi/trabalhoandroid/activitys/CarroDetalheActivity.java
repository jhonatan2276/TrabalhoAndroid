package br.edu.unidavi.trabalhoandroid.activitys;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.eventbus.Carro;

public class CarroDetalheActivity extends AppCompatActivity {

    private Context context;
    private TextView marca;
    private TextView modelo;
    private TextView ano;
    private ImageView imagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_detalhe);

        Button det_btnVoltar;

        marca = findViewById(R.id.det_txtMarca);
        modelo = findViewById(R.id.det_txtModelo);
        ano = findViewById(R.id.det_txtAno);
        imagem = findViewById(R.id.det_imagem_icone);
        det_btnVoltar = findViewById(R.id.det_btnVoltar);

        det_btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent (Carro carro) {
        marca.setText(carro.getMarca());
        modelo.setText(carro.getModelo());
        ano.setText(carro.getAno());

        Picasso.with(context)
                .load(carro.getImagem())
                .into(imagem);
    }
}