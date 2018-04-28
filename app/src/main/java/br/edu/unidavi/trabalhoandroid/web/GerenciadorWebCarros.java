package br.edu.unidavi.trabalhoandroid.web;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.unidavi.trabalhoandroid.R;
import br.edu.unidavi.trabalhoandroid.eventbus.Carro;

public class GerenciadorWebCarros extends GerenciadorWeb {

    private static final String SERVICE_NAME = "carros";
    private String id;

    public GerenciadorWebCarros (Context context, String id) {
        super(context, SERVICE_NAME);
        this.id = id;
    }

    @Override
    public String getRequestBody() {
        Map<String,String> mapaItem = new HashMap<>();
        mapaItem.put("id", id);

        JSONObject requestJson = new JSONObject(mapaItem);

        return requestJson.toString();
    }

    @Override
    public void handleResponse(String response) {

        List<Carro> carroList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);

            for(int index= 0; index < jsonArray.length(); index++){
                JSONObject memeJSON = (JSONObject) jsonArray.get(index);
                Carro carro = new Carro();
                carro.setImagem(memeJSON.getString("imagem"));
                carro.setMarca(memeJSON.getString("marca"));
                carro.setModelo(memeJSON.getString("modelo"));
                carro.setAno(memeJSON.getString("ano"));
                carroList.add(carro);
            }

            EventBus.getDefault().post(carroList);

        } catch (JSONException e) {
            EventBus.getDefault().post(new Error(getContext().getString(R.string.error_request)));
        }
    }
}
