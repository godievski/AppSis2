package com.example.fredy.finalfinalfinalx3sis2.Controller;

import android.content.Context;

import com.example.fredy.finalfinalfinalx3sis2.Modelo.Cliente;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.Producto;
import com.orm.SugarRecord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fredy on 11/4/16.
 */

public class GestorProducto   implements Serializable{
    ArrayList<Producto> productos;

    public GestorProducto (){
        this.productos = new ArrayList<Producto>();
        /*String identificador,String nombre,Double precio,String tipo,String unidad,String imagen,String categoria*/
        this.productos.add(new Producto("1","Producto 1",10.0,"Tipo 1","Docena","@drawable/prod","Categoria 1"));
        this.productos.add(new Producto("2","Producto 2",10.0,"Tipo 2","Docena","@drawable/prod","Categoria 1"));
        this.productos.add(new Producto("3","Producto 3",10.0,"Tipo 1","Docena","@drawable/prod","Categoria 1"));
        this.productos.add(new Producto("4","Producto 4",10.0,"Tipo 2","Docena","@drawable/prod","Categoria 1"));
        this.productos.add(new Producto("5","Producto 5",10.0,"Tipo 1","Docena","@drawable/prod","Categoria 1"));
        this.productos.add(new Producto("6","Producto 6",10.0,"Tipo 3","Docena","@drawable/prod","Categoria 1"));
        this.productos.add(new Producto("7","Producto 7",10.0,"Tipo 3","Docena","@drawable/prod","Categoria 1"));
        this.productos.add(new Producto("8","Producto 8",10.0,"Tipo 5","Docena","@drawable/prod","Categoria 1"));
        this.productos.add(new Producto("9","Producto 9",10.0,"Tipo 1","Docena","@drawable/prod","Categoria 1"));
            }
    public ArrayList<Producto> obtenerProductos(){
        return this.productos;
    }
    public void serializar(final Context context){
        try {
            FileOutputStream fileOut= context.openFileOutput("productos.bin",Context.MODE_PRIVATE);
            ObjectOutputStream salida=new ObjectOutputStream(fileOut);
            salida.writeObject(this.productos);
            salida.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Producto getProd(int index){
        return this.productos.get(index);
    }
    public Integer size(){
        return this.productos.size();
    }
    public void deserializar(final Context context){
        try {
            FileInputStream fis = context.openFileInput("productos.bin");
            ObjectInputStream entrada = new ObjectInputStream(fis);
            this.productos = (ArrayList<Producto>) entrada.readObject();
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
