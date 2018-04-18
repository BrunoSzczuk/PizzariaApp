package fag.com.br.pizzaria.obj.Entity;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;
import com.orm.dsl.Unique;

import java.io.Serializable;

/**
 * Created by Bruno on 09/04/2018.
 */

@MultiUnique("pedido_venda, produto ")
public class ItemPedido extends SugarRecord implements Serializable {
    @Unique
    private PedidoVenda pedidoVenda;
    private double qtProduto;
    private double vlProduto;
    @Unique
    private Produto produto;

    public PedidoVenda getPedidoVenda() {
        return pedidoVenda;
    }

    public void setPedidoVenda(PedidoVenda pedidoVenda) {
        this.pedidoVenda = pedidoVenda;
    }

    public double getQtProduto() {
        return qtProduto;
    }

    public void setQtProduto(double qtProduto) {
        this.qtProduto = qtProduto;
    }

    public double getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(double vlProduto) {
        this.vlProduto = vlProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return produto.getDsProduto() + " ";
    }
}
