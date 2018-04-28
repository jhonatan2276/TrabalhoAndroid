package br.edu.unidavi.trabalhoandroid.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.dados.Session;
import br.edu.unidavi.trabalhoandroid.eventbus.Mensagem;
import br.edu.unidavi.trabalhoandroid.eventbus.Usuario;
import br.edu.unidavi.trabalhoandroid.web.GerenciadorUsuario;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Resgata (do SharedPreferences - se houver) dados salvos do Usuário
        Session session = new Session(this);
        EditText edtLoginNome = findViewById(R.id.edtLoginNome);
        edtLoginNome.setText(session.retornaUsuarioSession());

        Button btnLoginEntrar = findViewById(R.id.btnLoginEntrar);

        btnLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });
    }

    public void logar () {
        /*//Salva (no SharedPreferences) os dados digitados no campo Nome
        EditText edtLoginNome = findViewById(R.id.edtLoginNome);

        Session session = new Session(this);
        session.salvaUsuarioSession(edtLoginNome.getText().toString());

        //Passa o texto do campo
        Mensagem mensagem = new Mensagem();
        mensagem.setMensagem(edtLoginNome.getText().toString());
        EventBus.getDefault().postSticky(mensagem);

        Intent login = new Intent(this, PrincipalActivity.class);
        startActivity(login);
        finish();*/

        GerenciadorUsuario gerenciadorUsuario = new GerenciadorUsuario(this, "admin", "admin");
        gerenciadorUsuario.execute();
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

        Log.d("TEXTO", "TESTE DE EVENTBUS");
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