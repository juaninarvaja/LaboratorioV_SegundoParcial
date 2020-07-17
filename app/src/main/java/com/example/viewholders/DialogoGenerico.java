package com.example.viewholders;

import android.app.Dialog;
import android.os.Bundle;
import android.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoGenerico extends DialogFragment {
    MainActivity mainActivity;
    String titulo;
    String mensaje;
    public DialogoGenerico(MainActivity activity){
        this.mainActivity = activity;
    }
    public DialogoGenerico(MainActivity main, String titulo, String mensaje){
        //usar estos valores para setear en el oncreate
        this.mainActivity = main;
        this.titulo = titulo;
        this.mensaje = mensaje;

    }
    public Dialog onCreateDialog(Bundle bundle) {
        /*Dialog generico*/

        AlertDialog.Builder builder = new AlertDialog.Builder(super.getActivity());
        builder.setTitle(this.titulo);
        builder.setMessage(this.mensaje);
        ClickDialogGenerico clickDialogGenerico = new ClickDialogGenerico();
        builder.setPositiveButton("OK",clickDialogGenerico);
        return builder.create();
        }

}
