package fag.com.br.pizzaria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import fag.com.br.pizzaria.adapter.AdapterProduto;
import fag.com.br.pizzaria.obj.Pizza;
import fag.com.br.pizzaria.util.Mensagem;

public class ProdutoActivity extends AppCompatActivity {


    EditText etCodigo, etDescricao, etPsBruto, etVlProduto;
    ToggleButton tgAtivo;
    List<Pizza> pizzaList = new ArrayList<>();
    AdapterProduto adapterProduto;
    Button btSalvar;
    Pizza pizza = new Pizza();
    ListView lvProduto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etCodigo = findViewById(R.id.etCodigo);
        etDescricao = findViewById(R.id.etDescricao);
        etPsBruto = findViewById(R.id.etPsBruto);
        etVlProduto = findViewById(R.id.etVlProduto);
        btSalvar = findViewById(R.id.btSalvar);
        tgAtivo = findViewById(R.id.tgAtivo);
        lvProduto = findViewById(R.id.lvProduto);

        carregaLista();
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCodigo.getText().toString() == null || etCodigo.getText().toString().trim().length() < 1){
                    Mensagem.ExibirMensagem(ProdutoActivity.this,"É necessário preencher um código",1);
                }else if ( etDescricao.getText().toString() == null || etDescricao.getText().toString().trim().length() < 1) {
                    Mensagem.ExibirMensagem(ProdutoActivity.this, "É necessário preencher uma descrição", 1);
                } else if (etPsBruto.getText().toString() == null || etPsBruto.getText().toString().trim().length() < 1){
                    Mensagem.ExibirMensagem(ProdutoActivity.this,"É necessário preencher um peso para o pizza",1);
                } else if (etVlProduto.getText().toString() == null || etVlProduto.getText().toString().trim().length() < 1){
                    Mensagem.ExibirMensagem(ProdutoActivity.this,"É necessário preencher um valor para o pizza",1);
                } else {
                    pizza.setCdProduto(etCodigo.getText().toString());
                    pizza.setDsProduto(etDescricao.getText().toString());
                    pizza.setPsBruto(Double.parseDouble(etPsBruto.getText().toString()));
                    pizza.setVlProduto(Double.parseDouble(etVlProduto.getText().toString()));
                    pizza.setStAtivo(tgAtivo.isChecked());
                    pizza.save();
                    Mensagem.ExibirMensagem(ProdutoActivity.this,"Salvo com sucesso",1);
                    pizza = new Pizza();
                    carregaLista();
                }
            }
        });
        lvProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pizza = (Pizza)lvProduto.getItemAtPosition(position);
                exibeProduto(pizza);
            }
        });
        carregaLista();
    }

    private void exibeProduto(Pizza pizza) {
        etDescricao.setText(pizza.getDsProduto());
        etCodigo.setText(pizza.getCdProduto());
        etPsBruto.setText(String.valueOf(pizza.getPsBruto()));
        etVlProduto.setText(String.valueOf(pizza.getVlProduto()));
        tgAtivo.setChecked(pizza.isStAtivo());
    }
    private void carregaLista() {
        pizzaList = Pizza.listAll(Pizza.class);//Lista com Ordenacao
        adapterProduto = new AdapterProduto(this, pizzaList);
        lvProduto.setAdapter(adapterProduto);//Amarro a ListView com o Adapter criado
    }
}
