package com.example.fredy.finalfinalfinalx3sis2.Modelo;

import com.example.fredy.finalfinalfinalx3sis2.Controller.GestorPedido;
import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fredy on 11/3/16.
 */

public class Cliente implements Serializable{
    private String Nombre_de_usuario;
    private String Contraseña;
    private Boolean Estado_deuda;
    private Double Linea_credito;
    private String Razon_social;
    private String Ruc;
    ArrayList<DatosDeContacto> ListaDatosDeContacto;

    /*Pedidos */
    public GestorPedido gPedidos;

    public Cliente(){

    }
    public Cliente(String nombre_de_usuario, String contraseña, Boolean estado_deuda, Double linea_credito, String razon_social, String ruc){
        this.Nombre_de_usuario=nombre_de_usuario;
        this.Contraseña=contraseña;
        this.Estado_deuda=estado_deuda;
        this.Linea_credito=linea_credito;
        this.Razon_social=razon_social;
        this.Ruc= ruc;
        ListaDatosDeContacto=new ArrayList<DatosDeContacto>();
        this.gPedidos = new GestorPedido();
    }

    public void AgregarDatosContacto(String email,String numero){
        DatosDeContacto datContact= new DatosDeContacto(email,numero);
        ListaDatosDeContacto.add(datContact);
    }
    public void EliminarDatosClientxOrden(Integer numPos){
        ListaDatosDeContacto.remove(numPos);
    }

    /*Metodo pedido*/

    public String getNombre_de_usuario() {
        return Nombre_de_usuario;
    }

    public void setNombre_de_usuario(String nombre_de_usuario) {
        Nombre_de_usuario = nombre_de_usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public Boolean getEstado_deuda() {
        return Estado_deuda;
    }

    public void setEstado_deuda(Boolean estado_deuda) {
        Estado_deuda = estado_deuda;
    }

    public Double getLinea_credito() {
        return Linea_credito;
    }

    public void setLinea_credito(Double linea_credito) {
        Linea_credito = linea_credito;
    }

    public String getRazon_social() {
        return Razon_social;
    }

    public void setRazon_social(String razon_social) {
        Razon_social = razon_social;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }
}
