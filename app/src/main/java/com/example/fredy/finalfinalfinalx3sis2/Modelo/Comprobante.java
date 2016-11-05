package com.example.fredy.finalfinalfinalx3sis2.Modelo;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fredy on 11/3/16.
 */

public class Comprobante  implements Serializable{
    private String FechaEmision;
    private Double Total;
    ArrayList<Cuota> Cuotas;

    public Comprobante(){

    }
    /*constructor*/
    public Comprobante(String fechaEmision, Double total, Integer numCuotas) {
        this.FechaEmision=fechaEmision;
        this.Total=total;
        if(numCuotas <= 0)
            this.Cuotas = null;
        else{
            this.Cuotas = new ArrayList<Cuota>();
            for(int i = 0; i < numCuotas; i++){
                this.Cuotas.add(new Cuota(total/numCuotas, fechaEmision,"Por Cancelar"));
            }
        }
    }

    public String getFechaEmision() {
        return FechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        FechaEmision = fechaEmision;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }
}
