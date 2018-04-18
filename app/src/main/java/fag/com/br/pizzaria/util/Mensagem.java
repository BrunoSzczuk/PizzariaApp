package fag.com.br.pizzaria.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by marcelo.braga on 12/03/2018.
 */

public class Mensagem {

    public static void ExibirMensagem(Context context, String msg, int tipo){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        if (tipo == 1){
            alert.setTitle("Sucesso");
            alert.setIcon(android.R.drawable.ic_dialog_info);
        }
        alert.setTitle("Mensagem");
        alert.setMessage(msg);
        alert.setNeutralButton("Ok", null);
        alert.show();


    }

}
