package fag.com.br.pizzaria.obj;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;

/**
 * Created by Bruno on 16/04/2018.
 */

public class Tamanho extends SugarRecord implements Serializable{
    @Unique
    private String cdTamanho;
    private String dsTamanho;

    public Tamanho() {
    }

    public Tamanho(String cdTamanho) {

        this.cdTamanho = cdTamanho;
    }

    @Override
    public String toString() {
        return "Tamanho: " + cdTamanho;
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
}
