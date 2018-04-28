package br.edu.unidavi.trabalhoandroid.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.eventbus.Mensagem;

public class PrincipalActivity extends AppCompatActivity {

    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

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
}
