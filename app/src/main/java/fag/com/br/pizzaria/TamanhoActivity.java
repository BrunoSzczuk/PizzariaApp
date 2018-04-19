package fag.com.br.pizzaria;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import fag.com.br.pizzaria.obj.Entity.Produto;
import fag.com.br.pizzaria.obj.Entity.Tamanho;
import fag.com.br.pizzaria.util.Mensagem;

public class TamanhoActivity extends AppCompatActivity {

    EditText etCodigo, etDescricao, etValorTamanho;
    Button btSalvar, btNovo, btVoltar;
    ArrayAdapter arrayAdapter;
    ListView lvtamanho;
    Tamanho tamanho = new Tamanho();
    List<Tamanho> tamanhoList = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamanho);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findComponents();
        events();
        carregaLista();
    }

    private void events() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( etCodigo.getText().toString() == null){
                    Mensagem.ExibirMensagem(TamanhoActivity.this,"É necessário informar um código",1);
                }
                if (etDescricao.getText().toString() == null){
                    Mensagem.ExibirMensagem(TamanhoActivity.this,"É ncessário informar uma descrição",1);
                }
                if (etValorTamanho.getText().toString() == null){
                    Mensagem.ExibirMensagem(TamanhoActivity.this,"É necessário informar um valor",1);
                }
                if (tamanho == null) tamanho = new Tamanho();
                tamanho.setCdTamanho(etCodigo.getText().toString().toUpperCase());
                tamanho.setDsTamanho(etDescricao.getText().toString());
                tamanho.setVlTamanho(Double.parseDouble(etValorTamanho.getText().toString()));
                tamanho.save();
                Mensagem.ExibirMensagem(TamanhoActivity.this,"Salvo com sucesso",1);
                novo();
                carregaLista();
            }
        });
        btNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                novo();
            }
        });
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TamanhoActivity.super.onBackPressed();
            }
        });

        lvtamanho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tamanho =(Tamanho)lvtamanho.getItemAtPosition(position);
                exibetamanho(tamanho);
            }
        });
        lvtamanho.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(TamanhoActivity.this);
                alert.setTitle("Confirmacao");
                alert.setIcon(android.R.drawable.ic_delete);
                alert.setMessage("Deseja Realmente Excluir o Registro?");
                alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletaProduto((Tamanho) lvtamanho.getItemAtPosition(position));
                    }
                });
                alert.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.show();
                return true;
            }
        });
    }

    private void deletaProduto(Tamanho tamanho) {
        tamanho.delete();//Apaga o Registro
        carregaLista();//Carrega a Lista novamente
        Mensagem.ExibirMensagem(TamanhoActivity.this, "Apagado com sucesso!", 1);

    }

    private void findComponents() {
        etCodigo = findViewById(R.id.etCodigo);
        etValorTamanho = findViewById(R.id.etVlTamanho);
        etDescricao = findViewById(R.id.etDescricao);
        btSalvar = findViewById(R.id.btSalvar);
        lvtamanho = findViewById(R.id.lvLista);
        btVoltar = findViewById(R.id.btVoltar);
        btNovo = findViewById(R.id.btNovo);
    }

    private void exibetamanho(Tamanho tamanho) {
        etDescricao.setText(tamanho.getDsTamanho());
        etCodigo.setText(tamanho.getCdTamanho());
        etValorTamanho.setText(String.valueOf(tamanho.getVlTamanho()));
    }

    private void novo(){
        tamanho = new Tamanho();
        exibetamanho(tamanho);
    }
    private void carregaLista() {
        tamanhoList = Tamanho.listAll(Tamanho.class);//Lista com Ordenacao
        arrayAdapter = new ArrayAdapter(this,R.layout.item_exibicao, tamanhoList);
        lvtamanho.setAdapter(arrayAdapter);//Amarro a ListView com o Adapter criado
        calculaTamanhoAdapater();
    }

    private void calculaTamanhoAdapater(){
        int totalHeight =0;
        ListAdapter adapter = lvtamanho.getAdapter();
        int length = adapter.getCount();
        for (int i =0; i< length; i++){
            View listView = adapter.getView(i, null, lvtamanho);
            listView.measure(0,0);
            totalHeight+= listView.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lvtamanho.getLayoutParams();
        params.height = totalHeight + (lvtamanho.getDividerHeight() * (adapter.getCount() -1) +10);
        lvtamanho.setLayoutParams(params);
        lvtamanho.requestLayout();
    }
}
