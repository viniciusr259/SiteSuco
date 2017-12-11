package br.com.sitesuco.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Detalhes extends AppCompatActivity {

    int id;
    String caminhoIMG;
    String nome;
    String descricao;
    String preco;
    String detalhes;
    Float avaliacao;

    ImageView txtcaminhoIMG;
    TextView txtnome;
    TextView txtdescricao;
    TextView txtpreco;
    TextView txtdetalhes;
    RatingBar tbAvaliacao;
    Float rbAv;




    String retornoJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Intent intent = getIntent();

        Bundle dados = intent.getExtras();
        id = dados.getInt("id");
        caminhoIMG = dados.getString("caminhoIMG");
        nome = dados.getString("nome");
        descricao = dados.getString("descricao");
        preco = dados.getString("preco");
        detalhes = dados.getString("detalhes");
        avaliacao = dados.getFloat("Av");


        txtcaminhoIMG = (ImageView) findViewById(R.id.ImgView);
        txtpreco = (TextView) findViewById(R.id.PrecoSuco);
        txtnome = (TextView) findViewById(R.id.NomeSuco);
        txtdetalhes = (TextView) findViewById(R.id.DetalhesSuco);

        tbAvaliacao = (RatingBar) findViewById(R.id.ratingBar);

        txtpreco.setText("R$ : "+preco);
        txtnome.setText(nome);
        txtdetalhes.setText(detalhes);


        Picasso.with(this).load("http://10.107.134.13/inf3m/TURMAB/Projeto%20WEB/"+caminhoIMG).into(txtcaminhoIMG);


        if (avaliacao != 0){
            tbAvaliacao.setRating(avaliacao);
            tbAvaliacao.setIsIndicator(true);
        }else{

            tbAvaliacao.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    rbAv = v;


                    new AsyncTask<Void, Void, Void>(){

                        @Override
                        protected Void doInBackground(Void... voids) {

                            retornoJson = Http.get("http://10.107.134.13/inf3m/Projeto%20WEB/API/inserir.php?id="+id+"&Av="+rbAv);
                            Log.d("Retorno :", retornoJson);
                            return null;
                        }


                    }.execute();
                }
            });

        }
    }

}
