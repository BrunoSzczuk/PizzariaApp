package fag.com.br.pizzaria.obj;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

import java.io.Serializable;

import fag.com.br.pizzaria.obj.PedidoVenda;
import fag.com.br.pizzaria.obj.Produto;

/**
 * Created by Bruno on 09/04/2018.
 */

@MultiUnique("pedido_venda, produto")
public class ItemPedido extends SugarRecord implements Serializable {
    private PedidoVenda pedidoVenda;
    private double qtProduto;
    private double vlProduto;
    private Produto produto;

    @Override
    public String toString() {
        return "ItemPedido{" +
                "pedidoVenda=" + pedidoVenda +
                ", qtProduto=" + qtProduto +
                ", vlProduto=" + vlProduto +
                ", produto=" + produto +
                '}';
    }

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
    public int hashCode() {
        int result;
        long temp;
        result = pedidoVenda != null ? pedidoVenda.hashCode() : 0;
        temp = Double.doubleToLongBits(qtProduto);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(vlProduto);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (produto != null ? produto.hashCode() : 0);
        return result;
    }
}
