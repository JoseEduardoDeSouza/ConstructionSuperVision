package com.example.ze.constructionsupervision;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class criar_conta extends Activity {
    EditText ednome,edcpf,edsenha,edcsenha;
    Button btncancelar,btnregistrar;

    String url ="";
    String parametros="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        ednome=(EditText)findViewById(R.id.nomeCadastro);
        edcpf=(EditText)findViewById(R.id.cpfCadastro);
        edsenha=(EditText)findViewById(R.id.senhaCadastro);
        edcsenha=(EditText)findViewById(R.id.senhaConfirma);
        btnregistrar=(Button)findViewById(R.id.registrar);
        btncancelar=(Button)findViewById(R.id.cancelar);

    }

    public void cancelar(View view){
        finish();
    }
    public void registrar(View view){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            String nome = ednome.getText().toString();
            String email = edcpf.getText().toString();
            String senha = edsenha.getText().toString();
            String senha2 = edcsenha.getText().toString();

            if (email.isEmpty() || senha.isEmpty()|| nome.isEmpty() || senha2.isEmpty()){
                Toast.makeText(getApplicationContext(),"Campo não preenchido",Toast.LENGTH_SHORT).show();
            }else{
                if(senha.equals(senha2)){
                    url = "http://192.168.1.103/html/registrar.php?";
                    parametros ="nome="+nome+"&email="+email+"&senha="+senha;
                    new SolicitaDados().execute(url+parametros);
                    


                }else {
                    Toast.makeText(getApplicationContext(),"Senhas não conferem",Toast.LENGTH_SHORT).show();
                }

            }



        } else {
            Toast.makeText(getApplicationContext(),"Nenhuma Conexão foi detectada",Toast.LENGTH_SHORT).show();
        }

    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0],parametros);

        }
        @Override
        protected void onPostExecute(String resultado) {
            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
