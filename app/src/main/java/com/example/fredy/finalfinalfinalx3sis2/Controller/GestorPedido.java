package com.example.fredy.finalfinalfinalx3sis2.Controller;

import com.example.fredy.finalfinalfinalx3sis2.Modelo.Pedido;
import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by fredy on 11/3/16.
 */

public class GestorPedido   implements Serializable{
    private ArrayList<Pedido> pedidos;

    public GestorPedido(){
        this.pedidos = new ArrayList<Pedido>();
    }
    public void AgregarPedido(Pedido ped){
        this.pedidos.add(ped);
    }

    public int CancelarPorId(String identificador){
        for(int i = 0; i < this.pedidos.size(); i++){
            Pedido ped = this.pedidos.get(i);
            if(ped.getIdentificador() == identificador && ped.getTracking() == "Procesando"){
                ped.setEstado("Cancelado");
                return 0;
            }
        }
        return 1;
    }
    public int CancelarPorOrden(int index){
        Pedido ped = this.pedidos.get(index);
        if(ped.getTracking() == "Procesando") {
            ped.setEstado("Cancelado");
            return 0;
        }
        return 1;
    }
    public ArrayList<Pedido> ConsultarHistorialPedido(){
        Collections.sort(this.pedidos,new Comparator<Pedido>(){
           public int compare(Pedido p1, Pedido p2){
               return p1.getFecha().compareTo(p2.getFecha());
           }
        });
        return this.pedidos;
    }
    public Pedido ObtenerPedidoPorIdent(String identificador){
        for(int i = 0; i < this.pedidos.size(); i++){
            Pedido ped = this.pedidos.get(i);
            if(ped.getIdentificador().compareTo( identificador )==0){
                return ped;
            }
        }
        return null;
    }
    public Integer size(){
        return this.pedidos.size();
    }
    public Pedido ObtenerPedidoPorOrden(int index){
        return this.pedidos.get(index);
    }
}
