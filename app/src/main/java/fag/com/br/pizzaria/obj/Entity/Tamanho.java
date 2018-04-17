package fag.com.br.pizzaria.obj.Entity;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.text.NumberFormat;

/**
 * Created by Bruno on 16/04/2018.
 */

public class Tamanho extends SugarRecord implements Serializable{
    @Unique
    private String cdTamanho;
    private String dsTamanho;
    private double vlTamanho;

    public Tamanho() {
    }

    public Tamanho(String cdTamanho) {

        this.cdTamanho = cdTamanho;
    }

    @Override
    public String toString() {
        return "Tamanho: " + cdTamanho + " - Valor: " + NumberFormat.getCurrencyInstance().format(vlTamanho);
    }

    public String getCdTamanho() {
        return cdTamanho;
    }

    public void setCdTamanho(String cdTamanho) {
        this.cdTamanho = cdTamanho;
    }

    public String getDsTamanho() {
        return dsTamanho;
    }

    public void setDsTamanho(String dsTamanho) {
        this.dsTamanho = dsTamanho;
    }

    public double getVlTamanho() {
        return vlTamanho;
    }

    public void setVlTamanho(double vlTamanho) {
        this.vlTamanho = vlTamanho;
    }
}
