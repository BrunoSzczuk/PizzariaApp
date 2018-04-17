package fag.com.br.pizzaria.obj;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

import java.io.Serializable;

/**
 * Created by Bruno on 09/04/2018.
 */

@MultiUnique("pedido_venda, pizza")
public class ItemPedido extends SugarRecord implements Serializable {
    private PedidoVenda pedidoVenda;
    private double qtProduto;
    private double vlProduto;
    private Pizza pizza;

    @Override
    public String toString() {
        return "ItemPedido{" +
                "pedidoVenda=" + pedidoVenda +
                ", qtProduto=" + qtProduto +
                ", vlProduto=" + vlProduto +
                ", pizza=" + pizza +
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

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
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
        result = 31 * result + (pizza != null ? pizza.hashCode() : 0);
        return result;
    }
}
