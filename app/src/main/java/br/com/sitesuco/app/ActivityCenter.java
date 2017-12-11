package br.com.sitesuco.app;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityCenter extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    ProdutoAdapter adapter;
    String retornoJson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        listView = (ListView) findViewById(R.id.list_view);

        /* Criando adater*/
        adapter = new ProdutoAdapter(this,new ArrayList<Produto>());

        /*definir adapter na lista*/
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Produto p = (Produto) listView.getItemAtPosition(i);

                //Toast.makeText(ActivityCenter.this, p.getNome(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),Detalhes.class);
                intent.putExtra("id",p.getId());
                intent.putExtra("caminhoIMG",p.getCaminhoIMG());
                intent.putExtra("nome",p.getNome());
                intent.putExtra("descricao",p.getDescricao());
                intent.putExtra("detalhes",p.getDetalhes());
                intent.putExtra("preco",p.getPreco());
                intent.putExtra("Av",p.getAvalicao());
                startActivity(intent);

            }
        }
        );


        /*Preencher Lista*/

        //Roda o comando em segundo plano.
        new AsyncTask<Void, Void, Void>(){

            ArrayList<Produto> lstContatos = new ArrayList<Produto>();

            @Override
            protected Void doInBackground(Void... voids) {
                retornoJson = Http.get("http://10.107.134.13/inf3m/TURMAB/Projeto%20WEB/API/selecionar.php");

                Log.d("Retorno :", retornoJson);

                try {
                    JSONArray jsonArray = new JSONArray(retornoJson);

                    for (int i=0; i < jsonArray.length(); i++){
                        JSONObject item = jsonArray.getJSONObject(i);
                        Produto c = Produto.create(
                                item.getString("nome"),
                                item.getString("caminhoIMG"),
                                item.getString("desccricao"),
                                item.getString("detalhes"),
                                item.getString("preco"),
                                item.getInt("idProd"),
                                (float) item.getLong("Av"));
                        lstContatos.add(c);
                    }

                } catch (Exception e) {
                    // Log.e("ERRO :",e.getMessage());
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.addAll(lstContatos);
            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_center, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Uri uri = Uri.parse("tel:"+"11971276378");
            Intent intent = new Intent(Intent.ACTION_DIAL,uri);

            startActivity(intent);
        }
        if(id == R.id.nav_share){
            startActivity(new Intent(this,MapsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
