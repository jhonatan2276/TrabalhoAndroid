package br.edu.unidavi.trabalhoandroid.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.dados.CarroAdapter;
import br.edu.unidavi.trabalhoandroid.eventbus.Carro;
import br.edu.unidavi.trabalhoandroid.eventbus.Mensagem;
import br.edu.unidavi.trabalhoandroid.web.GerenciadorWebCarros;

public class PrincipalActivity extends AppCompatActivity {

    private String nome;
    private RecyclerView recyclerView;
    private CarroAdapter carroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        GerenciadorWebCarros gerenciadorWebCarros = new GerenciadorWebCarros(this, "todos");
        gerenciadorWebCarros.execute();

        recyclerView = findViewById(R.id.recycler_carros);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        carroAdapter = new CarroAdapter(new ArrayList<Carro>(),this);
        recyclerView.setAdapter(carroAdapter);
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
    public void onEvent(Mensagem mensagem){
        Log.d("TEXTO", "TESTE DE EVENTBUS");
        Toast.makeText(this, mensagem.getMensagem(), Toast.LENGTH_SHORT).show();
        this.nome = mensagem.getMensagem();
        TextView txtUsuario;
        txtUsuario = findViewById(R.id.txtUsuario);
        txtUsuario.setText(this.nome);
    }

    @Subscribe
    public void onEvent (List<Carro> carroLista) {
        int i;

        for (i = 0; i < carroLista.size(); i++) {
            Toast.makeText(this, "Posição: "+Integer.toString(i), Toast.LENGTH_SHORT).show();
        }
        Log.d("EVENTO ======= ", "TESTE DE CARROS");

        TextView txtStatus;
        Carro carro = carroLista.get(0);

        txtStatus = findViewById(R.id.txtStatus);
        txtStatus.setText(carro.getModelo());


        findViewById(R.id.recycler_carros).setVisibility(View.VISIBLE);

        carroAdapter.carroLista = carroLista;
        carroAdapter.notifyDataSetChanged();
    }
}