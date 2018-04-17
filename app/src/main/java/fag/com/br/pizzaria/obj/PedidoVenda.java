package fag.com.br.pizzaria.obj;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Bruno on 09/04/2018.
 */

@Table(name = "pedidovenda")
public class PedidoVenda extends SugarRecord implements Serializable{
    @Unique
    private int nrPedido;
    private Date dtEmissao;
    private double vlPedido;
    private double psPedido;
    private boolean stCancelado;
    private List<ItemPedido> itens;

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "PedidoVenda{" +
                "nrPedido=" + nrPedido +
                ", dtEmissao=" + dtEmissao +
                ", vlPedido=" + vlPedido +
                ", psPedido=" + psPedido +
                ", stCancelado=" + stCancelado +
                '}';
    }

    public int getNrPedido() {
        return nrPedido;
    }

    public void setNrPedido(int nrPedido) {
        this.nrPedido = nrPedido;
    }

    public Date getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(Date dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public double getVlPedido() {
        return vlPedido;
    }

    public void setVlPedido(double vlPedido) {
        this.vlPedido = vlPedido;
    }

    public double getPsPedido() {
        return psPedido;
    }

    public void setPsPedido(double psPedido) {
        this.psPedido = psPedido;
    }

    public boolean isStCancelado() {
        return stCancelado;
    }

    public void setStCancelado(boolean stCancelado) {
        this.stCancelado = stCancelado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PedidoVenda that = (PedidoVenda) o;

        if (nrPedido != that.nrPedido) return false;
        if (Double.compare(that.vlPedido, vlPedido) != 0) return false;
        if (Double.compare(that.psPedido, psPedido) != 0) return false;
        if (stCancelado != that.stCancelado) return false;
        if (dtEmissao != null ? !dtEmissao.equals(that.dtEmissao) : that.dtEmissao != null)
            return false;
        return itens != null ? itens.equals(that.itens) : that.itens == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = nrPedido;
        result = 31 * result + (dtEmissao != null ? dtEmissao.hashCode() : 0);
        temp = Double.doubleToLongBits(vlPedido);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(psPedido);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (stCancelado ? 1 : 0);
        result = 31 * result + (itens != null ? itens.hashCode() : 0);
        return result;
    }
}
