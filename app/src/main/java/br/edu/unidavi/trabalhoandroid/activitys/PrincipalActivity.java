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

import java.util.ArrayList;
import java.util.List;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.dados.CarroAdapter;
import br.edu.unidavi.trabalhoandroid.eventbus.Carro;
import br.edu.unidavi.trabalhoandroid.eventbus.Usuario;
import br.edu.unidavi.trabalhoandroid.web.GerenciadorWebCarros;

public class PrincipalActivity extends AppCompatActivity {
    private TextView prp_txtUsuario;
    private TextView prp_txtEmail;
    private RecyclerView recyclerView;
    private CarroAdapter carroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        prp_txtUsuario = findViewById(R.id.prp_txtUsuario);
        prp_txtEmail = findViewById(R.id.prp_txtEmail);

        GerenciadorWebCarros gerenciadorWebCarros = new GerenciadorWebCarros(this, "todos");
        gerenciadorWebCarros.execute();

        recyclerView = findViewById(R.id.prp_recyclerCarros);
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

    @Subscribe
    public void onEvent(Usuario usuario){
        Toast.makeText(this, "Bem Vindo "+usuario.getNome(), Toast.LENGTH_SHORT).show();

        prp_txtUsuario.setText(usuario.getNome());
        prp_txtEmail.setText(usuario.getEmail());

        Log.d("EVENTO ======= ", "RECEBENDO USUÁRIO");

        //hideDialog();

        /*Session session = new Session(this);
        session.saveEmailInSession(user.getEmail());
        session.savePhotoUrlInSession(user.getProfile_img_url());
        session.saveTokenInSession(user.getToken());*/
        //goToHome();
    }

    @Subscribe
    public void onEvent (List<Carro> carroLista) {
        findViewById(R.id.prp_recyclerCarros).setVisibility(View.VISIBLE);

        carroAdapter.carroLista = carroLista;
        carroAdapter.notifyDataSetChanged();

        Log.d("EVENTO ======= ", "RECEBENDO CARROS");
    }
}