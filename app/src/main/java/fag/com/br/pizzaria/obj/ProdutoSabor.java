package fag.com.br.pizzaria.adapter;

import fag.com.br.pizzaria.obj.Produto;

public class AdapterProdutoSabor {
    boolean isSelected;
    Produto produto;

    public AdapterProdutoSabor() {
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public AdapterProdutoSabor(boolean isSelected, Produto produto) {
        this.isSelected = isSelected;
        this.produto = produto;
    }
}
