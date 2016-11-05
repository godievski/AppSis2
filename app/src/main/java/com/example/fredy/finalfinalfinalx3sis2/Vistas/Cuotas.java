package com.example.fredy.finalfinalfinalx3sis2.Vistas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fredy.finalfinalfinalx3sis2.Controller.GestorCliente;
import com.example.fredy.finalfinalfinalx3sis2.Controller.GestorProducto;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.Cliente;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.Pedido;
import com.example.fredy.finalfinalfinalx3sis2.R;
import com.google.common.util.concurrent.ExecutionError;

/**
 * Created by fredy on 11/4/16.
 */

public class Cuotas extends AppCompatActivity{
    private GestorCliente gestorCliente;
    private GestorProducto gestorProducto;
    private Cliente cl;
    private String id;
    private Context context;
    private Pedido ped;
    private Integer numCuotas_valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuotas);
        context = this;
        this.InicializarAtributos();
        this.Inicializar_menu(context);

        Button siguiente = (Button) findViewById(R.id.cuotas_siguiente);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarPedido();
                Intent perfil = new Intent(context, Perfil.class);
                perfil.putExtra("id_usuario",id);
                startActivity(perfil);
            }
        });
        Button anterior = (Button) findViewById(R.id.cuotas_anterior);
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(context, ConfirmarPedido.class);
                perfil.putExtra("id_usuario",id);
                perfil.putExtra("pedido",ped);
                startActivity(perfil);
            }
        });

    }

    private void agregarPedido(){
        ped.setNumCuotas(numCuotas_valor);
        this.cl.gPedidos.AgregarPedido(ped);
        this.gestorCliente.serializar(context);
    }

    private void InicializarAtributos(){
        try{
            Bundle datos = this.getIntent().getExtras();
            id = datos.getString("id_usuario");
            ped = (Pedido) datos.getSerializable("pedido");
        } catch(Exception ex){
            CharSequence text = "No se paso el id por Intent";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        this.gestorCliente = new GestorCliente();
        gestorCliente.deserializar(context);
        this.cl = gestorCliente.obtenerClientePorID(id);
        if (cl == null) {
            CharSequence text = "Cliente error Perfil";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        TextView montoTotal= (TextView) findViewById(R.id.cuotas_total);
        montoTotal.setText(String.format("%.2f",ped.getMonto()));

        final EditText numCuotas = (EditText) findViewById(R.id.cuotas_numero_cuotas);
        final TextView montoCuota = (TextView) findViewById(R.id.cuotas_monto_de_cuota);
        montoCuota.setText(String.format("%.2f",ped.getMonto()));
        numCuotas.setText("1");
        numCuotas_valor = 1;
        numCuotas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                numCuotas_valor = Integer.parseInt(numCuotas.getText().toString());
                if(numCuotas_valor>1){
                    Double mi_cuota = ped.getMonto() / numCuotas_valor;
                    montoCuota.setText(String.format("%.2f",mi_cuota));
                }}
                catch (Exception ex){}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void Inicializar_menu(final Context context) {

        ImageButton boton_perfil = (ImageButton) findViewById(R.id.perfil_button);
        boton_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(context, Perfil.class);
                perfil.putExtra("id_usuario",id);
                startActivity(perfil);

            }
        });

        ImageButton boton_catalogo = (ImageButton) findViewById(R.id.catalogo_button);

        boton_catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent catalogo = new Intent(context, Catalogo.class);
                catalogo.putExtra("id_usuario",id);
                startActivity(catalogo);

            }
        });


        ImageButton boton_hacer_pedido = (ImageButton) findViewById(R.id.hacer_pedido_button);
        boton_hacer_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hacer_pedido = new Intent(context, HacerPedido.class);
                hacer_pedido.putExtra("id_usuario",id);
                startActivity(hacer_pedido);

            }
        });


        ImageButton boton_administrar_pedido = (ImageButton) findViewById(R.id.administrar_pedido_button);
        boton_administrar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent administrarPedido = new Intent(context, AdministrarPedido.class);
                administrarPedido.putExtra("id_usuario",id);
                startActivity(administrarPedido);

            }
        });


        ImageButton boton_historial = (ImageButton) findViewById(R.id.historial_button);
        boton_historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historialPedido = new Intent(context, HistorialPedido.class);
                historialPedido.putExtra("id_usuario",id);
                startActivity(historialPedido);

            }
        });

    }

}
