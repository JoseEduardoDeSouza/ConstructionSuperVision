package com.example.ze.constructionsupervision;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText editEmail1, editSenha1;
    Button btnLogar;
    String email,senha;

    String url = "";
    String parametros ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail1 = (EditText) findViewById(R.id.editEmail1);
        editSenha1 = (EditText) findViewById(R.id.editSenha1);
        btnLogar = (Button) findViewById(R.id.btnLogar);
    }
    public void btnlog(View view){


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            email = editEmail1.getText().toString();
            senha = editSenha1.getText().toString();
            if (email.isEmpty() || senha.isEmpty()){
                Toast.makeText(getApplicationContext(),"Campo não preenchido",Toast.LENGTH_SHORT).show();
            } else{
                url = "http://192.168.1.103/html/logar.php?";
                parametros = "email="+email+"&senha="+senha;
                Intent i = new Intent(this, TelaInicial.class);

                new SolicitaDados().execute(url+parametros);

            }



        } else {
            Toast.makeText(getApplicationContext(),"Nenhuma Conexão foi detectada",Toast.LENGTH_SHORT).show();
        }
    }
    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String resultado) {
            Log.d("resposta",resultado);

            if(!resultado.isEmpty()){
                Intent abreInicio = new Intent(MainActivity.this, TelaInicial.class);
                abreInicio.putExtra("CPF", email);
                startActivity(abreInicio);

            }else{
                Toast.makeText(getApplicationContext(),"Usuário ou senha incorreto",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
