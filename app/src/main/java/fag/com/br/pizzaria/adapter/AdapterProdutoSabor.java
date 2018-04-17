package fag.com.br.pizzaria.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fag.com.br.pizzaria.R;
import fag.com.br.pizzaria.obj.Entity.Produto;
import fag.com.br.pizzaria.obj.ProdutoSabor;

public class AdapterProdutoSabor extends BaseAdapter {
    Activity activity;
    List<ProdutoSabor> sabores;
    LayoutInflater inflater;

    public AdapterProdutoSabor(Activity activity, List<ProdutoSabor> sabores) {
        this.activity = activity;
        this.sabores = sabores;
        inflater     = activity.getLayoutInflater();
    }
    @Override
    public int getCount() {
        return sabores.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public List<Produto> getProdutosSelecionados(){
        List<Produto> list = new ArrayList<>();
        for (ProdutoSabor p : sabores){
            if (p.isSelected()){
                list.add(p.getProduto());
            }
        }
        return list;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (view == null){

            view = inflater.inflate(R.layout.list_view_item, viewGroup, false);

            holder = new ViewHolder();

            holder.tvDsProduto = (TextView)view.findViewById(R.id.tv_ds_produto);
            holder.ivCheckBox = (ImageView) view.findViewById(R.id.iv_check_box);

            view.setTag(holder);
        }else
            holder = (ViewHolder)view.getTag();

        ProdutoSabor model = sabores.get(i);

        holder.tvDsProduto.setText(model.getProduto().getDsProduto());

        if (model.isSelected())
            holder.ivCheckBox.setBackgroundResource(R.drawable.checked);

        else
            holder.ivCheckBox.setBackgroundResource(R.drawable.check);

        return view;
    }

    public void updateRecords(List<ProdutoSabor> sabores){
        this.sabores = sabores;

        notifyDataSetChanged();
    }

    class ViewHolder{

        TextView tvDsProduto;
        ImageView ivCheckBox;

    }
}
