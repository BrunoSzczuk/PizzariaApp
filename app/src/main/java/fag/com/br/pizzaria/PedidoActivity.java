package fag.com.br.pizzaria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fag.com.br.pizzaria.adapter.AdapterProduto;
import fag.com.br.pizzaria.adapter.AdapterProdutoSabor;
import fag.com.br.pizzaria.obj.Entity.ItemPedido;
import fag.com.br.pizzaria.obj.Entity.PedidoVenda;
import fag.com.br.pizzaria.obj.Entity.Produto;
import fag.com.br.pizzaria.obj.Entity.Tamanho;
import fag.com.br.pizzaria.obj.ProdutoSabor;
import fag.com.br.pizzaria.util.Mensagem;

public class PedidoActivity extends AppCompatActivity {

    ArrayAdapter adapterTamanho;
    List<Tamanho> tamanhoList = Tamanho.listAll(Tamanho.class);
    Spinner spTamanho;
    PedidoVenda pedidoVenda = new PedidoVenda();
    ListView lvProduto;
    AdapterProdutoSabor adapterProduto;
    List<ProdutoSabor> saborList = new ArrayList<>();

    Button btSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spTamanho = findViewById(R.id.spTamanho);
        lvProduto = findViewById(R.id.lvSabores);
        btSalvar  = findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Produto> produtosSelecionados = ((AdapterProdutoSabor) lvProduto.getAdapter()).getProdutosSelecionados();
                if (produtosSelecionados.size() == 0){
                    Mensagem.ExibirMensagem (PedidoActivity.this, "É necessário selecionar ao menos um sabor",1);
                }else {
                    PedidoVenda outropedido = PedidoVenda.last(PedidoVenda.class);
                    List<ItemPedido> itens = new ArrayList<>();
                    if (outropedido == null){
                        outropedido = new PedidoVenda();
                        outropedido.setNrPedido(0);
                    }
                    pedidoVenda.setDtEmissao(new Date());
                    pedidoVenda.setNrPedido(outropedido.getNrPedido() +1);
                    pedidoVenda.setStCancelado(false);
                    pedidoVenda.setTamanho((Tamanho)spTamanho.getSelectedItem());
                    double peso = 0, valor = pedidoVenda.getTamanho().getVlTamanho();

                    for (Produto p : produtosSelecionados){
                        ItemPedido itemPedido = new ItemPedido();
                        itemPedido.setPedidoVenda(pedidoVenda);
                        itemPedido.setProduto(p);
                        itemPedido.setQtProduto(1);
                        itemPedido.setVlProduto(p.getVlProduto());
                        peso += p.getPsBruto();
                        valor += p.getVlProduto();

                        itens.add(itemPedido);
                    }
                    pedidoVenda.setVlPedido(valor);
                    pedidoVenda.setPsPedido(peso);
                    pedidoVenda.setItens(itens);
                    pedidoVenda.save();
                    for (ItemPedido i : pedidoVenda.getItens()){
                        i.save();
                    }
                    Mensagem.ExibirMensagem (PedidoActivity.this, "Pedido salvo com sucesso",1);
                    pedidoVenda = new PedidoVenda();
                }
            }
        });
        carregaLista();
    }
    private void carregaLista() {
        List<Produto> produtos = Produto.listAll(Produto.class);//Lista com Ordenacao;
        if (saborList.size() == 0) {
            for (Produto p : produtos) {
                saborList.add(new ProdutoSabor(false, p));
            }
        }
        adapterProduto = new AdapterProdutoSabor(this, saborList);
        lvProduto.setAdapter(adapterProduto);//Amarro a ListView com o Adapter criado

        lvProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProdutoSabor model = saborList.get(i);

                if (model.isSelected())
                    model.setSelected(false);

                else
                    model.setSelected(true);

                saborList.set(i, model);

                //now update adapter
                adapterProduto.updateRecords(saborList);
            }
        });
        adapterTamanho = new ArrayAdapter(this,R.layout.item_exibicao, tamanhoList);
        spTamanho.setAdapter(adapterTamanho);
        calculaTamanhoAdapater();
    }
    private void calculaTamanhoAdapater(){
        int totalHeight =0;
        ListAdapter adapter = lvProduto.getAdapter();
        int length = adapter.getCount();
        for (int i =0; i< length; i++){
            View listView = adapter.getView(i, null, lvProduto);
            listView.measure(0,0);
            totalHeight+= listView.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lvProduto.getLayoutParams();
        params.height = totalHeight + (lvProduto.getDividerHeight() * (adapter.getCount() -1) +10);
        lvProduto.setLayoutParams(params);
        lvProduto.requestLayout();
    }
}
