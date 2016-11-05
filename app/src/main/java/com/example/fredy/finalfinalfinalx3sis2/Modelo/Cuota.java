package com.example.fredy.finalfinalfinalx3sis2.Modelo;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by fredy on 11/3/16.
 */

public class Cuota  implements Serializable {
    private Double Monto;
    private String Fecha;
    private String Estado;

    public Cuota(){

    }
    public Cuota(Double monto,String fecha,String estado){
        this.Monto=monto;
        this.Fecha=fecha;
        this.Estado = estado;
    }

    public Double getMonto() {
        return Monto;
    }

    public void setMonto(Double monto) {
        Monto = monto;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getEstado(){
        return Estado;
    }
    public void setEstado(String estado){
        this.Estado = estado;
    }
}
