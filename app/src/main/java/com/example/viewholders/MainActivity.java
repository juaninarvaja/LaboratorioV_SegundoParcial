package com.example.viewholders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MyOnItemClick, Handler.Callback, LifecycleObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("miConf", MODE_PRIVATE);
        String nombre = preferences.getString("name", "none"); //el s1 es el valor por defecto
        if (nombre.equals("none")) {
            Log.d("Shared", "No hay ningun nombre");
            TextView contactos = super.findViewById(R.id.tvContactos);
            contactos.setText("no hay contactos en la lista");
        } else {

            TextView contactos = super.findViewById(R.id.tvContactos);
            contactos.setText(nombre);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        super.getMenuInflater().inflate(R.menu.menu, m);

        MenuItem mi = m.findItem(R.id.miBuscar);
        android.widget.SearchView sw = (android.widget.SearchView) mi.getActionView();
        sw.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("log", s);
                TextView contactos = findViewById(R.id.tvContactos);
                String contactosString = (String) contactos.getText();
                //Log.d("el string","de contactos es"+contactosString);
                try {
                    if(!contactosString.isEmpty()){
                        JSONArray array = new JSONArray(contactosString);
                        Log.d("el array",array.toString());
                        for(int i=0;i<array.length(); i++){
                            JSONObject jsonObject = array.getJSONObject(i);
                            if (jsonObject.has("nombre")) {
                                String valor =  jsonObject.getString("nombre").toLowerCase();
                               // Log.d("el valor","el valor del nombre"+ valor);
                                if(valor.equals(s.toLowerCase())){
                                    String resupuesta = "El numero de la persona: " + valor + ", es: " + jsonObject.getString("telefono");;

                                    DialogoGenerico miDialogo = new DialogoGenerico(MainActivity.this,"Persona encontrada",resupuesta);
                                    miDialogo.show(MainActivity.super.getSupportFragmentManager(), "Confirm");
                                    return true;
                                }
                            }
                        }
                        DialogoGenerico miDialogo = new DialogoGenerico(MainActivity.this,"No enconrtada","la persona que busco no esta dentro de la lista");
                        miDialogo.show(MainActivity.super.getSupportFragmentManager(), "Confirm");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //Log.d("log", s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(m);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

         if (item.getItemId() == R.id.dialog) {
            MiDialogo miDialogo = new MiDialogo(this);
            miDialogo.show(super.getSupportFragmentManager(), "Confirm");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int postion) {
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {

        return false;
    }

    public void setContactos(String s){
        TextView contactos = super.findViewById(R.id.tvContactos);
        //String aux = (String) contactos.getText();
        contactos.setText(s);
    }


}
