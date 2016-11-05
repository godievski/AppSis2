package com.example.fredy.finalfinalfinalx3sis2.Modelo;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by fredy on 11/3/16.
 */

public class DetallePedido implements Serializable {
    private String Identificador;
    private Integer Cantidad;
    private Double SubTotal;
    private Producto Producto;

    public DetallePedido(){

    }
    public DetallePedido(String  identificador, Integer cantidad,Double subTotal,Producto producto){
        this.Identificador=identificador;
        this.Cantidad=cantidad;
        this.SubTotal=subTotal;
        this.Producto = producto;
    }

    public String getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(String indentificador) {
        Identificador = indentificador;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer cantidad) {
        Cantidad = cantidad;
    }

    public Double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(Double subTotal) {
        SubTotal = subTotal;
    }

    public Producto getProducto() {
        return Producto;
    }

    public void setProducto(Producto producto) {
        Producto = producto;
    }
}
