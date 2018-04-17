package fag.com.br.pizzaria;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import fag.com.br.pizzaria.obj.Tamanho;
import fag.com.br.pizzaria.obj.Sabor;

public class PedidoActivity extends AppCompatActivity {

    ArrayAdapter adapterTamanho;
    List<Tamanho> tamanhoList = Tamanho.listAll(Tamanho.class);
    Spinner spTamanho;

    ListView lvSabor;
    ArrayAdapter adapterSabor;
    List<Sabor> saborList = Sabor.listAll(Sabor.class);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spTamanho = findViewById(R.id.spTamanho);
        lvSabor = findViewById(R.id.lvSabores);

        carregaLista();
    }
    private void carregaLista() {
        saborList = Sabor.listAll(Sabor.class);//Lista com Ordenacao
        adapterSabor = new ArrayAdapter(this, R.layout.item_exibicao, saborList);
        lvSabor.setAdapter(adapterSabor);//Amarro a ListView com o Adapter criado

        adapterTamanho = new ArrayAdapter(this,R.layout.item_exibicao, tamanhoList);
        spTamanho.setAdapter(adapterTamanho);
        calculaTamanhoAdapater();

    }
    private void calculaTamanhoAdapater(){
        int totalHeight =0;
        ListAdapter adapter = lvSabor.getAdapter();
        int length = adapter.getCount();
        for (int i =0; i< length; i++){
            View listView = adapter.getView(i, null, lvSabor);
            listView.measure(0,0);
            totalHeight+= listView.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lvSabor.getLayoutParams();
        params.height = totalHeight + (lvSabor.getDividerHeight() * (adapter.getCount() -1) +10);
        lvSabor.setLayoutParams(params);
        lvSabor.requestLayout();
    }
}
