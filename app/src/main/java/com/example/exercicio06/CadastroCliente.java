package com.example.exercicio06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroCliente extends AppCompatActivity {

    private EditText nome;
    private EditText matricula;
    private EditText endereco;
    private EditText numero;
    private EditText complemento;
    private EditText cidade;
    private ClienteDAO dao;
    private Cliente cliente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        nome = (EditText) findViewById(R.id.ET_nome);
        matricula = (EditText) findViewById(R.id.ET_matricula);
        endereco = (EditText) findViewById(R.id.ET_endereco);
        numero = (EditText) findViewById(R.id.ET_numero);
        complemento = (EditText) findViewById(R.id.ET_complemento);
        cidade = (EditText) findViewById(R.id.ET_cidade);
        dao = new ClienteDAO(this);


        Intent it = getIntent();
        if (it.hasExtra("cliente")) {
            cliente = (Cliente) it.getSerializableExtra("cliente");
            nome.setText(cliente.getNome());
            matricula.setText(String.valueOf( cliente.getMatricula()));
            endereco.setText(cliente.getEndereco());
            numero.setText(String.valueOf( cliente.getNumero()));
            complemento.setText(cliente.getComplemento());
            cidade.setText(cliente.getCidade());

        }
    }
    public void salvar(View view){
        if (cliente == null) {


            Cliente c = new Cliente();
            c.setNome(nome.getText().toString());
            c.setMatricula(Integer.valueOf(matricula.getText().toString()));
            c.setEndereco(endereco.getText().toString());
            c.setNumero(Integer.valueOf(numero.getText().toString()));
            c.setComplemento(complemento.getText().toString());
            c.setCidade(cidade.getText().toString());

            long id = dao.inserir(c);
            Toast.makeText(this, "Cliente Cadastrado com id: " + id, Toast.LENGTH_SHORT).show();
        } else {
            cliente.setNome(nome.getText().toString());
            cliente.setMatricula(Integer.valueOf(matricula.getText().toString()));
            cliente.setEndereco(endereco.getText().toString());
            cliente.setNumero(Integer.valueOf(numero.getText().toString()));
            cliente.setComplemento(complemento.getText().toString());
            cliente.setCidade(cidade.getText().toString());
            dao.atualizar(cliente);
        }
    }
}
