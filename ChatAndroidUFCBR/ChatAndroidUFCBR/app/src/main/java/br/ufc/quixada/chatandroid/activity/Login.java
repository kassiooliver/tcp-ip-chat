package br.ufc.quixada.chatandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import br.ufc.quixada.chatandroid.R;


public class Login extends AppCompatActivity {

    private String mensagem;
    private String mensagemEntrada;
    private EditText x;
    private EditText Eip;
    private TextView t1;
    private TextView t2;
    private Socket cliente;
    private String ip = "192.168.1.6";
    private int porta = 5551;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Eip = (EditText)findViewById(R.id.editIP);

        t1 = (TextView) findViewById(R.id.textView3);
        t2 = (TextView) findViewById(R.id.textView4);
        new Thread(new LoginThread()).start();



    }

    public void enderecarSocket(View v){
        try{
             ip = Eip.getText().toString();
            new Thread(new LoginThread()).start();
            t2.setText(mensagemEntrada);
            Toast.makeText(getApplicationContext(),"Socket Enderecado", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Nao conseguiu!", Toast.LENGTH_SHORT).show();

        }
    }


    public void onClick(View view) {
        t2.setText(mensagemEntrada);
        try {
            EditText x = (EditText) findViewById(R.id.editText1);
            String mensagem = x.getText().toString();
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream())), true);
            out.println(mensagem);
            t1.setText("Voce diz > "+mensagem);
            } catch (IOException e) {
            e.printStackTrace();
        }

    }


    class LoginThread implements Runnable{
        public void run(){
            try{
                InetAddress ipServidor = InetAddress.getByName(ip);
                cliente = new Socket(ip,porta);
                BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                while(cliente.isConnected()){
                    mensagemEntrada = in.readLine();
                    }
            } catch (UnknownHostException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }



















}