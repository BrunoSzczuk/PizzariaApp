package fag.com.br.pizzaria;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import fag.com.br.pizzaria.obj.Sabor;
import fag.com.br.pizzaria.util.Mensagem;

public class SaborActivity extends AppCompatActivity {

    EditText etCodigo, etDescricao;
    Button btSalvar;
    ArrayAdapter arrayAdapter;
    ListView lvsabor;
    Sabor sabor = new Sabor();
    List<Sabor> saborList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sabor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etCodigo = findViewById(R.id.etCodigo);
        etDescricao = findViewById(R.id.etDescricao);
        btSalvar = findViewById(R.id.btSalvar);
        lvsabor = findViewById(R.id.lvLista);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sabor == null) sabor = new Sabor();
                sabor.setCdSabor(Integer.parseInt(etCodigo.getText().toString()));
                sabor.setDescricao(etDescricao.getText().toString());
                sabor.save();
                Mensagem.ExibirMensagem(SaborActivity.this,"Salvo com sucesso",1);
                sabor = new Sabor();
                carregaLista();
            }
        });

        lvsabor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sabor =(Sabor)lvsabor.getItemAtPosition(position);
                exibesabor(sabor);
            }
        });
        carregaLista();
    }

    private void exibesabor(Sabor sabor) {
        etDescricao.setText(sabor.getDescricao());
        etCodigo.setText(sabor.getCdSabor());
    }

    private void carregaLista() {
        saborList = Sabor.listAll(Sabor.class);//Lista com Ordenacao
        arrayAdapter = new ArrayAdapter(this,R.layout.item_exibicao, saborList);
        lvsabor.setAdapter(arrayAdapter);//Amarro a ListView com o Adapter criado
        calculaTamanhoAdapater();
    }

    private void calculaTamanhoAdapater(){
        int totalHeight =0;
        ListAdapter adapter = lvsabor.getAdapter();
        int length = adapter.getCount();
        for (int i =0; i< length; i++){
            View listView = adapter.getView(i, null, lvsabor);
            listView.measure(0,0);
            totalHeight+= listView.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lvsabor.getLayoutParams();
        params.height = totalHeight + (lvsabor.getDividerHeight() * (adapter.getCount() -1) +10);
        lvsabor.setLayoutParams(params);
        lvsabor.requestLayout();
    }
}
