package fag.com.br.pizzaria;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import fag.com.br.pizzaria.adapter.AdapterRelatorioTamanho;
import fag.com.br.pizzaria.obj.Entity.PedidoVenda;
import fag.com.br.pizzaria.obj.Entity.Tamanho;
import fag.com.br.pizzaria.util.Mensagem;

public class RelPizzaActivity extends AppCompatActivity {
    ArrayAdapter adapterTamanho;
    AdapterRelatorioTamanho adapterRelatorioTamanho;
    Spinner spTamanho;
    List<Tamanho> tamanhoList = Tamanho.listAll(Tamanho.class);
    List<PedidoVenda> pedidos;
    ListView lvPedidos;
    Button btGerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rel_pizza);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        findComponents();
        btGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spTamanho.getSelectedItem() == null){
                    Mensagem.ExibirMensagem(RelPizzaActivity.this, "É necessário ter um tamanho cadastrado",1);
                }else{
                    pedidos = PedidoVenda.find(PedidoVenda.class," tamanho = ?", new String[]{((Tamanho)spTamanho.getSelectedItem()).getId().toString()});
                    adapterRelatorioTamanho = new AdapterRelatorioTamanho(RelPizzaActivity.this, pedidos);
                    lvPedidos.setAdapter(adapterRelatorioTamanho);
                    calculaTamanhoAdapater();
                }
            }
        });
        tamanhoList = Tamanho.listAll(Tamanho.class);
        carregaLista();
        
    }

    private void findComponents() {
        spTamanho = findViewById(R.id.spTamanho);
        lvPedidos = findViewById(R.id.lvLista);
        btGerar = findViewById(R.id.btGerar);
    }

    private void carregaLista(){
        adapterTamanho = new ArrayAdapter(this,R.layout.item_exibicao, tamanhoList);
        spTamanho.setAdapter(adapterTamanho);
    }

    private void calculaTamanhoAdapater(){
        int totalHeight =0;
        ListAdapter adapter = lvPedidos.getAdapter();
        int length = adapter.getCount();
        for (int i =0; i< length; i++){
            View listView = adapter.getView(i, null, lvPedidos);
            listView.measure(0,0);
            totalHeight+= listView.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lvPedidos.getLayoutParams();
        params.height = totalHeight + (lvPedidos.getDividerHeight() * (adapter.getCount() -1) +10);
        lvPedidos.setLayoutParams(params);
        lvPedidos.requestLayout();
    }
    
}
