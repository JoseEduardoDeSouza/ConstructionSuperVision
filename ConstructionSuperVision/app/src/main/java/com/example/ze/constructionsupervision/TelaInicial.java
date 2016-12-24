package com.example.ze.constructionsupervision;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends AppCompatActivity {

    String site,parametros,img;
    int foto=0;
    TextView qntd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);
        img=""+foto;
        add();
        qntd=(TextView)findViewById(R.id.textView2);



    }

    public void adicionarURL(String url){
        ImageView f = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load(url).into(f);

    }
    public void ir (View view){

        foto++;
        img=""+foto;
        qntd.setText(img);
        add();


    }
    public void voltar(View view){
        if (foto>0){
            foto--;
            img=""+foto;
            qntd.setText(img);
            add();
        }

    }

    public void add(){
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if (b!=null) {
            String j = b.getString("CPF");
            site = "http://192.168.1.103:8081/imagem?img=images/" + j + "/" + img + ".jpg";
            adicionarURL(site);
        }
    }



}

