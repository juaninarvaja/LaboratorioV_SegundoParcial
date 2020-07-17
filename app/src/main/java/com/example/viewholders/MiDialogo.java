package com.example.viewholders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

public class MiDialogo extends DialogFragment {
    MainActivity mainActivity;
    public MiDialogo(MainActivity activity){
        this.mainActivity = activity;
    }
    public MiDialogo(MainActivity main,String titulo,String mensaje){
        //usar estos valores para setear en el oncreate
    }
    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        /*Dialog generico*/
        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(super.getActivity());
        builder.setTitle("Titulo del mensaje");
        builder.setMessage("Mesnaje para el usuario");
        ClickDialog clickDialog = new ClickDialog();
        builder.setPositiveButton("OK",clickDialog);
        builder.setNegativeButton("Cancel",clickDialog);
        builder.setNeutralButton("Neutral",clickDialog);
        */
        //Dialogo personalizado
        AlertDialog.Builder builder = new AlertDialog.Builder(super.getActivity());
        LayoutInflater li = LayoutInflater.from(getActivity());
        View view = li.inflate(R.layout.dialog_custom, null);
        builder.setView(view);
        //úedo usar botones del basico tambien en el perwosnalizado
        builder.setTitle("Nuevo Contacto");
        ClickDialog clickDialog = new ClickDialog(view,mainActivity);
        builder.setPositiveButton("Guardar",clickDialog);
        builder.setNeutralButton("Cancelar",clickDialog);
        return builder.create();
    }
}
