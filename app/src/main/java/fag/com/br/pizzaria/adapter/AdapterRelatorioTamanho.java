package fag.com.br.pizzaria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import fag.com.br.pizzaria.R;
import fag.com.br.pizzaria.obj.Entity.ItemPedido;
import fag.com.br.pizzaria.obj.Entity.PedidoVenda;

/**
 * Created by Bruno on 17/04/2018.
 */

public class AdapterRelatorioTamanho extends BaseAdapter {
    LayoutInflater inflater;
    List<PedidoVenda> pedidoVendaList;

    public AdapterRelatorioTamanho(Context context, List<PedidoVenda> pedidoVendaList) {
        this.inflater = LayoutInflater.from(context);
        this.pedidoVendaList = pedidoVendaList;
    }

    @Override
    public int getCount() {
        return pedidoVendaList.size();
    }

    @Override
    public Object getItem(int position) {
        return pedidoVendaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        PedidoVenda p = pedidoVendaList.get(position);
        List<ItemPedido> itens = ItemPedido.find(ItemPedido.class, "pedido_venda = ?", new String[]{p.getId().toString()});
        view =  inflater.inflate(R.layout.item_relatorio_tamanho, null);

        ((TextView) view.findViewById(R.id.etNrPedido)).setText(String.valueOf(p.getNrPedido()));
        ((TextView) view.findViewById(R.id.etVlPedido)).setText(NumberFormat.getCurrencyInstance().format(p.getVlPedido()));
        ((TextView) view.findViewById(R.id.etQtdItens)).setText(String.valueOf(itens == null? 0: itens.size()));
        ((TextView) view.findViewById(R.id.etItens)).setText(String.valueOf(itens == null? "" : itens.toString()));
        return view;
    }
}
