package br.com.sitesuco.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.sitesuco.app.Produto;

import static java.lang.System.load;

/**
 * Created by 16254866 on 13/11/2017.
 */

public class ProdutoAdapter extends ArrayAdapter<Produto> {

    TextView txt_nome ;
    TextView txt_desc ;
    TextView preco ;
    int id;
    ImageView imgFoto;


    public ProdutoAdapter(Context context,ArrayList<Produto> lstContato){
        super(context, 0, lstContato);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){

        View v = convertView;

        if (v == null){

            v = LayoutInflater.from(getContext()).inflate(R.layout.list_view,null);

        }
        Produto item = getItem(position);

        txt_nome = v.findViewById(R.id.nome);
        txt_desc = v.findViewById(R.id.desc);
        preco = v.findViewById(R.id.preco);
        imgFoto = v.findViewById(R.id.imgFoto);


        txt_nome.setText(item.getNome());
        preco.setText("R$ : "+item.getPreco());
        txt_desc.setText(item.getDescricao());

        Picasso.with(getContext()).load("http://10.107.134.13/inf3m/TURMAB/Projeto%20WEB/"+item.getCaminhoIMG()).into(imgFoto);

        return v;
    }
}
