 package fag.com.br.pizzaria.obj;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;

/**
 * Created by Bruno on 16/04/2018.
 */

public class Sabor extends SugarRecord implements Serializable{
    @Unique
    private int cdSabor;
    private String dsDescricao;

    public int getCdSabor() {
        return cdSabor;
    }

    @Override
    public String toString() {
        return "Codigo Sabor: " + cdSabor + " - " + dsDescricao;
    }

    public void setCdSabor(int cdSabor) {
        this.cdSabor = cdSabor;
    }

    public String getDescricao() {
        return dsDescricao;
    }

    public void setDescricao(String descricao) {
        this.dsDescricao = descricao;
    }

    public Sabor() {

    }
}
