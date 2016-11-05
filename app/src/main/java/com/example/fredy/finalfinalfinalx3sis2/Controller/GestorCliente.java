package com.example.fredy.finalfinalfinalx3sis2.Controller;

import android.content.Context;

import com.example.fredy.finalfinalfinalx3sis2.Modelo.Cliente;
import com.orm.SugarRecord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fredy on 11/3/16.
 */

public class GestorCliente {
    private ArrayList<Cliente> Clientes;

    public GestorCliente(){
        this.Clientes = new ArrayList<Cliente>();

        /*DiegoG*/
        this.nuevoCliente("godievski","asd",false,1500.0,"Ka-Tet Corp.","12345");
        this.nuevoCliente("ruggi","asd",true,00000.0,"NanDroid","12345");
        this.nuevoCliente("ggina","asd",false,1000.0,"ggProductions","12345");
    }

    public Cliente obtenerClientePorID(String id){
        for(int i = 0; i < this.Clientes.size(); i++){
            if(this.Clientes.get(i).getNombre_de_usuario().compareTo(id)==0)
                return this.Clientes.get(i);
        }
        return null;
    }


    public Boolean comprobarLogin(String id, String pass){
        Cliente cl = this.obtenerClientePorID(id);
        if(cl != null){
            if(cl.getContraseña().compareTo(pass)==0)
                return true;
            else
                return false;
        } else
            return false;
    }
    public void nuevoCliente(String nombre_de_usuario, String contraseña, Boolean estado_deuda, Double linea_credito, String razon_social,String ruc){
        this.Clientes.add(new Cliente(nombre_de_usuario,contraseña,estado_deuda,linea_credito,razon_social,ruc));
    }
    public void serializar(final Context context){
        try {
            FileOutputStream fileOut= context.openFileOutput("clientes.bin",Context.MODE_PRIVATE);
            ObjectOutputStream salida=new ObjectOutputStream(fileOut);
            salida.writeObject(this.Clientes);
            salida.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deserializar(final Context context){
        try {
            FileInputStream fis = context.openFileInput("clientes.bin");
            ObjectInputStream entrada = new ObjectInputStream(fis);
            this.Clientes = (ArrayList<Cliente>) entrada.readObject();
            entrada.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            this.serializar(context);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
