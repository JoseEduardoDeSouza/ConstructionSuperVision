package com.example.ze.constructionsupervision;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;



public class TelaInicial extends Activity {

    String site,img;
    int foto=1;
    int adp=0;
    int padrao = 999999;
    TextView qntd, textProgresso;
    String url = "http://192.168.1.103:8081/qntd?";
    String cpf, parametros;
    int total;
    ImageView f;
    ProgressBar barra;
    int progresso=0;
    String urlProgresso="http://192.168.1.103:8081/porcentagem?porc=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        barra= (ProgressBar) findViewById(R.id.progressBar2);
        textProgresso = (TextView)findViewById(R.id.textView3);
        qntd=(TextView)findViewById(R.id.textView2);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if (b!=null){
            cpf=b.getString("CPF");

        }


        parametros="cpf="+cpf;
        new SolicitaDados().execute(url + parametros);
        img=""+foto+" de X";
        qntd.setText(img);

        urlProgresso+=cpf;
        new SolicitaPorcentagem().execute(urlProgresso);



        add();


    }
    public void atualizaProgresso(int progresso){
        barra.setProgress(progresso);
        textProgresso.setText(progresso+"% da obra concluída.");

    }
    public void add(){
        site = "http://192.168.1.103:8081/imagem?img=images/" + cpf + "/" + adp+ ".jpg";
        adicionarURL(site);

    }


    public void adicionarURL(String url) {
        f = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load(url).error(R.drawable.a).into(f);

    }

    public void ir (View view){
        if (adp<=total-2){
            foto++;
            img=""+foto+" de "+total;
            qntd.setText(img);
            adp++;
            add();

        }

    }

    public void voltar(View view){
        if (adp>=1){
            foto--;
            img=""+foto+" de "+total;
            qntd.setText(img);
            adp--;
            add();
        }

    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.GetHTTPData(urls[0]);

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String resultado) {


            if(!resultado.isEmpty()){
                int i = Integer.parseInt(resultado);
                total = i;
            }else{
                Toast.makeText(getApplicationContext(),"Null",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class SolicitaPorcentagem extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.GetHTTPData(urls[0]);

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String resultado) {
            Log.d("resposta",resultado);

            if(!resultado.isEmpty()){
                int i = Integer.parseInt(resultado);
                atualizaProgresso(i);
            }else{

                atualizaProgresso(0);
            }
        }
    }

}

