package fag.com.br.pizzaria.obj;

import fag.com.br.pizzaria.obj.Entity.Produto;

public class ProdutoSabor {
    boolean isSelected;
    Produto produto;

    public ProdutoSabor() {
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

    public ProdutoSabor(boolean isSelected, Produto produto) {
        this.isSelected = isSelected;
        this.produto = produto;
    }
}
