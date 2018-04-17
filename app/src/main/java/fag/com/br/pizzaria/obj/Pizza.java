package fag.com.br.pizzaria.obj;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Bruno on 04/04/2018.
 */

public class Pizza extends SugarRecord implements Serializable{

    @Unique
    private String cdProduto;

    private String dsProduto;

    private boolean stAtivo;

    private double psBruto;

    private double vlProduto;

    public double getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(double vlProduto) {
        this.vlProduto = vlProduto;
    }

    public String getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(String cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getDsProduto() {
        return dsProduto;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public boolean isStAtivo() {
        return stAtivo;
    }

    public void setStAtivo(boolean stAtivo) {
        this.stAtivo = stAtivo;
    }

    public double getPsBruto() {
        return psBruto;
    }

    public void setPsBruto(double psBruto) {
        this.psBruto = psBruto;
    }

    public Pizza(String cdProduto, String dsProduto, boolean stAtivo, double psBruto) {

        this.cdProduto = cdProduto;
        this.dsProduto = dsProduto;
        this.stAtivo = stAtivo;
        this.psBruto = psBruto;
    }

    public Pizza() {
    }

    @Override
    public String toString() {
        return "Pizza - "+ cdProduto + " - Descrição - " +dsProduto + " - Valor: " + NumberFormat.getCurrencyInstance().format(vlProduto);
    }
}
