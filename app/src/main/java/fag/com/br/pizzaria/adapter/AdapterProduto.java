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
import fag.com.br.pizzaria.obj.Entity.Produto;

/**
 * Created by Bruno on 02/04/2018.
 */

public class AdapterProduto extends BaseAdapter {
    List<Produto> pizzaList;
    LayoutInflater inflater;

    public AdapterProduto(Context context, List<Produto> pizzaList) {
        this.pizzaList = pizzaList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pizzaList.size();
    }

    @Override
    public Object getItem(int position) {
        return pizzaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Produto p = pizzaList.get(position);
        view =  inflater.inflate(R.layout.item_produto, null);

        ((TextView) view.findViewById(R.id.tvCdProduto)).setText(String.valueOf(p.getCdProduto()));
        ((TextView) view.findViewById(R.id.tvDsProduto)).setText(p.getDsProduto().toString());
        ((TextView) view.findViewById(R.id.etVlProduto)).setText(NumberFormat.getCurrencyInstance().format(p.getVlProduto()));
        ((TextView) view.findViewById(R.id.etPsBruto)).setText(new DecimalFormat("##,#00").format(p.getPsBruto()));
        return view;
    }

    public int getPosition(Produto p){
        return pizzaList.indexOf(p);
    }
}
