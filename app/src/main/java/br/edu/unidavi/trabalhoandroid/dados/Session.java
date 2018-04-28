package br.edu.unidavi.trabalhoandroid.dados;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private final String CAMPO_USUARIO = "usuario";
    private final String CATEGORY_SESSION = "session";

    private SharedPreferences sharedPreferences;

    public Session (Context context) {
        sharedPreferences = context.getSharedPreferences(CATEGORY_SESSION, Context.MODE_PRIVATE);
    }

    public void salvaUsuarioSession (String usuarioNome) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CAMPO_USUARIO, usuarioNome);
        editor.commit();
    }

    public String retornaUsuarioSession () {
        return sharedPreferences.getString(CAMPO_USUARIO, "");
    }
}

/*import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    private final String FIELD_USERNAME = "username";
    private final String FIELD_PHOTO_URL = "photo_url";
    private final String FIELD_TOKEN = "token";
    private final String CATEGORY_SESSION = "session";

    private SharedPreferences sharedPreferences;

    public Session(Context context){
        sharedPreferences =
                context.getSharedPreferences(CATEGORY_SESSION,
                        Context.MODE_PRIVATE);
    }


    public void saveEmailInSession(String emailValue){
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(FIELD_USERNAME, emailValue);
        editor.commit();
    }

    public String getEmailInSession(){
        return sharedPreferences.getString(FIELD_USERNAME,"");
    }

    public void savePhotoUrlInSession(String url){
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(FIELD_PHOTO_URL, url);
        editor.commit();
    }

    public String getPhotoUrlInSession(){
        return sharedPreferences.getString(FIELD_PHOTO_URL,
                "");
    }

    public void saveTokenInSession(String tokenValue){
        SharedPreferences.Editor editor =
                sharedPreferences.edit();
        editor.putString(FIELD_TOKEN, tokenValue);
        editor.commit();
    }

    public String getTokenInSession(){
        return sharedPreferences.getString(FIELD_TOKEN,"");
    }

}*/