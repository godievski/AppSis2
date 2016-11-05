package com.example.fredy.finalfinalfinalx3sis2.Vistas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fredy.finalfinalfinalx3sis2.Controller.GestorCliente;
import com.example.fredy.finalfinalfinalx3sis2.Controller.GestorProducto;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.Cliente;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.Cuota;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.DetallePedido;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.Pedido;
import com.example.fredy.finalfinalfinalx3sis2.R;

/**
 * Created by fredy on 11/4/16.
 */

public class ConfirmarPedido extends AppCompatActivity {
    private GestorCliente gestorCliente;
    private GestorProducto gestorProducto;
    private Cliente cl;
    private String id;
    private Context context;
    private Pedido ped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_pedido);
        context = this;
        this.InicializarAtributos();
        this.Inicializar_menu(context);

        Button siguiente = (Button) findViewById(R.id.confirmar_pedido_siguiente);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(context, Cuotas.class);
                perfil.putExtra("id_usuario",id);
                perfil.putExtra("pedido",ped);
                startActivity(perfil);
            }
        });
        Button anterior = (Button) findViewById(R.id.confirmar_pedido_anterior);
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(context, HacerPedidoCatalogo.class);
                perfil.putExtra("id_usuario",id);
                startActivity(perfil);
            }
        });
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
        LinearLayout padre = (LinearLayout) findViewById(R.id.confirmar_pedido_content);
        LinearLayout botones = (LinearLayout) padre.getChildAt(0);
        padre.removeViewAt(0);

        TextView numeroPedido = new TextView(this);
        numeroPedido.setTextSize(25);
        LinearLayout.LayoutParams  layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        numeroPedido.setLayoutParams(layoutParams);
        layoutParams.setMargins(20,20,20,0);
        numeroPedido.setText("Nro Pedido: " + ped.getIdentificador());

        padre.addView(numeroPedido);
        for(int i = 0; i < ped.cantidadPed(); i++){
            DetallePedido dp = ped.getDetallePedido(i);
            TextView nombreProd = new TextView(this);
            nombreProd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            nombreProd.setTextSize(20);
            nombreProd.setPadding(25,0,0,0);
            nombreProd.setText("Nombre: " + dp.getProducto().getNombre());

            TextView cantidad = new TextView(this);
            cantidad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            cantidad.setPadding(30,0,0,0);
            cantidad.setText("Cantidad: " + dp.getCantidad().toString());

            TextView subtotal = new TextView(this);
            subtotal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            subtotal.setPadding(30,0,0,0);
            subtotal.setText("Subtotal: " + dp.getSubTotal().toString());

            TextView unidad = new TextView(this);
            unidad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            unidad.setPadding(30,0,0,0);
            unidad.setText("Unidad: " + dp.getProducto().getUnidad());

            TextView trash = new TextView(this);
            trash.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            trash.setText("    -----------------------------------------------------");

            padre.addView(trash);
            padre.addView(nombreProd);
            padre.addView(unidad);
            padre.addView(cantidad);
            padre.addView(subtotal);
        }
        padre.addView(botones);

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
