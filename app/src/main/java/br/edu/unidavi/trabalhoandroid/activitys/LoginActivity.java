package br.edu.unidavi.trabalhoandroid.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.dados.Session;
import br.edu.unidavi.trabalhoandroid.eventbus.Mensagem;
import br.edu.unidavi.trabalhoandroid.eventbus.Usuario;
import br.edu.unidavi.trabalhoandroid.web.GerenciadorWebUsuario;

public class LoginActivity extends AppCompatActivity {

    private String nome;
    private String senha;
    private EditText log_edtNome;
    private EditText log_edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log_edtNome = findViewById(R.id.log_edtNome);
        log_edtSenha = findViewById(R.id.log_edtSenha);
        Button log_btnEntrar = findViewById(R.id.log_btnEntrar);

        //Resgata (do SharedPreferences - se houver) dados salvos do Usuário
        Session session = new Session(this);
        log_edtNome.setText(session.retornaUsuarioSession());

        log_btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });
    }

    public void logar () {
        //Salva no SharedPreferences) os dados digitados no campo Nome
        log_edtNome = findViewById(R.id.log_edtNome);

        Session session = new Session(this);
        session.salvaUsuarioSession(log_edtNome.getText().toString());

        //Passa o texto do campo
        Mensagem mensagem = new Mensagem();
        mensagem.setMensagem(log_edtNome.getText().toString());
        EventBus.getDefault().postSticky(mensagem);

        Intent principal = new Intent(this, PrincipalActivity.class);
        startActivity(principal);
        finish();

        nome = log_edtNome.getText().toString();
        senha = log_edtSenha.getText().toString();

        GerenciadorWebUsuario gerenciadorWebUsuario = new GerenciadorWebUsuario(this, nome, senha);
        gerenciadorWebUsuario.execute();
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

        Log.d("TEXTO", "EVENTO DE USUÁRIO");
        String texto = usuario.getNome()+" - "+usuario.getEmail();
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();

        //hideDialog();

        /*Session session = new Session(this);
        session.saveEmailInSession(user.getEmail());
        session.savePhotoUrlInSession(user.getProfile_img_url());
        session.saveTokenInSession(user.getToken());*/
        //goToHome();
    }

    @Subscribe
    public void onEvent(Error error){

        //hideDialog();

        /*Snackbar.make(findViewById(R.id.container),
                error.getMessage(), Snackbar.LENGTH_LONG)
                .show();*/

        Log.d("DEU ERRO =========== ", error.getMessage());
    }
}