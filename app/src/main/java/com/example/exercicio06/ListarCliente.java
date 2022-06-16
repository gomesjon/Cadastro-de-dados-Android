package com.example.exercicio06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
public class ListarCliente extends AppCompatActivity {
    private ListView listView;
    private ClienteDAO dao;
    private List<Cliente> clientes;
    private List<Cliente> clientesFiltrados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente);

        Intent it = getIntent();
        String name = it.getStringExtra("p_nome");
        TextView tv = (TextView) findViewById(R.id.tv_bemVindo);
        EditText ET_login = (EditText) findViewById(R.id.ET_login);
        tv.setText("Usuario " + name + " seja bem vindo!!!");

        listView = findViewById(R.id.lista_clientes);
        dao = new ClienteDAO(this);
        clientes = dao.obterTodos();
        clientesFiltrados.addAll(clientes);
        ArrayAdapter<Cliente> adaptador = new ArrayAdapter<Cliente>(this,
                android.R.layout.simple_list_item_1, clientesFiltrados);
        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);
        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                procuraCliente(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                System.out.println("Digitou " + s);
                return false;
            }
        });
        return true;
    }
    public void procuraCliente(String nome) {
        clientesFiltrados.clear();
        for (Cliente c : clientes) {
            if (c.getNome().toLowerCase().contains(nome.toLowerCase())) {
                clientesFiltrados.add(c);
            }
        }
        listView.invalidateViews();
    }


    public void Cadastrar(MenuItem item) {
        Intent it_telaCadastro = new Intent(this, CadastroCliente.class);
        startActivity(it_telaCadastro);
    }

//-----------------------------------------------------------

    public void onCreateContextMenu(ContextMenu menu, android.view.View v,
                                    android.view.ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        final Cliente clienteExcluir = clientesFiltrados.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja mesmo excluir o cliente?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clientesFiltrados.remove(clienteExcluir);
                        clientes.remove(clienteExcluir);
                        dao.excluir(clienteExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }
//-------------------------------------------------------------------------------

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Cliente clienteAtualizar = clientesFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastroCliente.class);
        it.putExtra("cliente", clienteAtualizar);
        startActivity(it);
    }

//--------------------------------------------------------------------------------

    @Override
    public void onResume() {
        super.onResume();
        clientes = dao.obterTodos();
        clientesFiltrados.clear();
        clientesFiltrados.addAll(clientes);
        listView.invalidateViews();
    }
}