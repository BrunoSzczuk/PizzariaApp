package fag.com.br.pizzaria;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import fag.com.br.pizzaria.obj.Entity.Tamanho;
import fag.com.br.pizzaria.util.Mensagem;

public class TamanhoActivity extends AppCompatActivity {

    EditText etCodigo, etDescricao;
    Button btSalvar;
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

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tamanho == null) tamanho = new Tamanho();
                tamanho.setCdTamanho(etCodigo.getText().toString());
                tamanho.setDsTamanho(etDescricao.getText().toString());
                tamanho.save();
                Mensagem.ExibirMensagem(TamanhoActivity.this,"Salvo com sucesso",1);
                tamanho = new Tamanho();
                carregaLista();
            }
        });

        lvtamanho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tamanho =(Tamanho)lvtamanho.getItemAtPosition(position);
                exibetamanho(tamanho);
            }
        });
        carregaLista();
    }

    private void findComponents() {
        etCodigo = findViewById(R.id.etCodigo);
        etDescricao = findViewById(R.id.etDescricao);
        btSalvar = findViewById(R.id.btSalvar);
        lvtamanho = findViewById(R.id.lvLista);
    }

    private void exibetamanho(Tamanho tamanho) {
        etDescricao.setText(tamanho.getDsTamanho());
        etCodigo.setText(tamanho.getCdTamanho());
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
