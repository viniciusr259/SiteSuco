package br.com.sitesuco.app;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String retornoJson;
    JSONObject item;
    public static local c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void call(View view) {

        Uri uri = Uri.parse("tel:"+"1147733319");
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);

        startActivity(intent);
    }

    public void comecar(View view) {
        //Roda o comando em segundo plano.
        new AsyncTask<Void, Void, Void>(){

            ArrayList<local> lstContatos = new ArrayList<>();

            @Override
            protected Void doInBackground(Void... voids) {
                retornoJson = Http.get("http://10.107.134.13/inf3m/Projeto%20WEB/API/maps.php");

                Log.d("Retorno :", retornoJson);

                try {
                    JSONArray jsonArray = new JSONArray(retornoJson);

                    for (int i=0; i < jsonArray.length(); i++){
                        item = jsonArray.getJSONObject(i);

                        c = local.create(
                                item.getLong("latitude"),
                                item.getLong("longitude"));
                        lstContatos.add(c);

                    }

                } catch (Exception e) {
                    // Log.e("ERRO :",e.getMessage());
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();
        startActivity(new Intent(this,ActivityCenter.class));
    }
}
