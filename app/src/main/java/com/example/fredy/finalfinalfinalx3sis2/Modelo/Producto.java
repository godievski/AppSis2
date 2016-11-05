package com.example.fredy.finalfinalfinalx3sis2.Modelo;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.security.PublicKey;

/**
 * Created by fredy on 11/3/16.
 */

public class Producto implements Serializable{
    private String Identificador;
    private String Nombre;
    private Double Precio;
    private String Tipo;
    private String Unidad;
    private String Imagen;
    private String Categoria;


    public Producto(){

    }
    public Producto(String identificador,String nombre,Double precio,String tipo,String unidad,String imagen,String categoria){
        this.Identificador=identificador;
        this.Nombre=nombre;
        this.Precio=precio;
        this.Tipo=tipo;
        this.Unidad=unidad;
        this.Imagen=imagen;
        this.Categoria=categoria;

    }

    public String getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(String indentificador) {
        Identificador = indentificador;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getUnidad() {
        return Unidad;
    }

    public void setUnidad(String unidad) {
        Unidad = unidad;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
}
