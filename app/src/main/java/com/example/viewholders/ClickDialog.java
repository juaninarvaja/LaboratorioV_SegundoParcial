package com.example.viewholders;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClickDialog  implements DialogInterface.OnClickListener {
    View v;
    MainActivity mainActivity;
    public ClickDialog(){}
    public ClickDialog(View view, MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.v = view;
        }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == Dialog.BUTTON_POSITIVE){
            //si hago click  en el positico pasa esto
            EditText edNombre = v.findViewById(R.id.txtNombre);
            EditText edNumero = v.findViewById(R.id.txtNumero);
            JSONArray array = new JSONArray();
            if(!edNombre.getText().toString().isEmpty() && !edNumero.getText().toString().isEmpty()){
                JSONObject jsonObject= new JSONObject();
                try {
                    jsonObject.put("nombre", edNombre.getText().toString());
                    jsonObject.put("telefono", edNumero.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Log.d("el json",jsonObject.toString());
//                array.put(jsonObject);
//                array.put(jsonObject);
                Log.d("el array",array.toString());
                SharedPreferences preferences = mainActivity.getSharedPreferences("miConf", Context.MODE_PRIVATE);
                try {

                    if(preferences.getString("name", null) != null){
                        Log.d("lo q llega aca es",preferences.getString("name", null));
                        array = new JSONArray(preferences.getString("name",null));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SharedPreferences.Editor editor = preferences.edit();
                array.put(jsonObject);
                editor.putString("name",array.toString());
                editor.commit();
                Log.d("ARRAY","valor " + array.toString());
                mainActivity.setContactos(array.toString());
            }
            else{
                Log.d("click di","no entro");
            }
        }
        Log.d("click di","click en el dialogo" + i);
    }
}
