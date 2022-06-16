package com.example.exercicio06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ET_login;
    private EditText ET_senha;
    private Button BTN_acessar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ET_senha = (EditText) findViewById(R.id.ET_senha);
        ET_login = (EditText) findViewById(R.id.ET_login);
        BTN_acessar = (Button) findViewById(R.id.BTN_acessar);
    }
    public void abrir_tela_lista(View v){

        String etUsuario = ET_login.getText().toString();
        String etSenha = ET_senha.getText().toString();

        if(ET_login.getText().length() == 0 || ET_senha.getText().length() == 0) {
            Toast.makeText(this, "Os campos de login e senha são obrigatórios!",
                    Toast.LENGTH_LONG).show();
        }
        else {
            if(etUsuario.equals("jonata") && etSenha.equals("jon123")){
                Intent it_telaLista = new Intent(this, ListarCliente.class);
                it_telaLista.putExtra("p_nome", ET_login.getText().toString());
                startActivity(it_telaLista);
            }
            else{
                Toast.makeText(this, "LOGIN OU SENHA INVÁLIDA", Toast.LENGTH_LONG).show();
            }
        }
    }
}
