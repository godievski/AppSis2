package com.example.fredy.finalfinalfinalx3sis2.Vistas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fredy.finalfinalfinalx3sis2.Controller.GestorCliente;
import com.example.fredy.finalfinalfinalx3sis2.Controller.GestorProducto;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.Cliente;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.DetallePedido;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.Pedido;
import com.example.fredy.finalfinalfinalx3sis2.R;

import java.util.Date;

/**
 * Created by fredy on 11/4/16.
 */

public class HistorialPedido extends AppCompatActivity {
    private GestorCliente gestorCliente;
    private GestorProducto gestorProducto;
    private Cliente cl;
    private String id;
    private Context context;
    private Pedido pedHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial_pedido);
        context = this;
        this.InicializarAtributos();
        this.Inicializar_menu(context);

        try {
            Bundle datos = this.getIntent().getExtras();
            id = datos.getString("id_usuario");
        } catch (Exception ex) {
            CharSequence text = "No se paso el id por Intent";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


    }

    private void InicializarAtributos() {
        try {
            Bundle datos = this.getIntent().getExtras();
            id = datos.getString("id_usuario");
        } catch (Exception ex) {
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

        final EditText numeroPedido = (EditText) findViewById(R.id.historial_numero);

        numeroPedido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pedHistorial = cl.gPedidos.ObtenerPedidoPorIdent(numeroPedido.getText().toString());
                if (pedHistorial==null){
                    return;
                }
                TextView fecha = (TextView) findViewById(R.id.historial_fecha);
                fecha.setPadding(25,0,0,0);
                fecha.setText("Fecha : "+ pedHistorial.getFecha());
                TextView monto = (TextView) findViewById(R.id.historial_monto);
                monto.setPadding(25,0,0,0);
                monto.setText("Monto : "+String.format("%.2f",pedHistorial.getMonto()));
                TextView estado = (TextView) findViewById(R.id.historial_estado);
                estado.setPadding(25,0,0,0);
                estado.setText("Estado :"+ pedHistorial.getEstado());
                /*DETALLE PEDIDO**/
                LinearLayout padre = (LinearLayout) findViewById(R.id.historial_detalle_pedido);
                for(int i = 0; i < pedHistorial.cantidadPed(); i++){
                    DetallePedido dp = pedHistorial.getDetallePedido(i);

                    TextView nombreProd = new TextView(context);
                    nombreProd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    nombreProd.setPadding(25,0,0,0);
                    nombreProd.setText("Nombre: " + dp.getProducto().getNombre());

                    TextView cantidad = new TextView(context);
                    cantidad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    cantidad.setPadding(25,0,0,0);
                    cantidad.setText("Cantidad: " + dp.getCantidad().toString());

                    TextView subtotal = new TextView(context);
                    subtotal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    subtotal.setPadding(25,0,0,0);
                    subtotal.setText("Subtotal: " + dp.getSubTotal().toString());

                    TextView unidad = new TextView(context);
                    unidad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    unidad.setPadding(25,0,0,0);
                    unidad.setText("Unidad: " + dp.getProducto().getUnidad());

                    TextView trash = new TextView(context);
                    trash.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    trash.setText("    -----------------------------------------------------");

                    padre.addView(trash);
                    padre.addView(nombreProd);
                    padre.addView(unidad);
                    padre.addView(cantidad);
                    padre.addView(subtotal);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Button usar_pedido = (Button) findViewById(R.id.historial_usar_pedido);

        usar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(context, ConfirmarPedido.class);
                perfil.putExtra("id_usuario",id);
                Pedido pedido = pedHistorial.copiarPedido(cl.gPedidos.size()+1,context);
                perfil.putExtra("pedido",pedido);
                startActivity(perfil);
            }
        });

    }

    private void Inicializar_menu(final Context context) {

        ImageButton boton_perfil = (ImageButton) findViewById(R.id.perfil_button);
        boton_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(context, Perfil.class);
                perfil.putExtra("id_usuario", id);
                startActivity(perfil);

            }
        });

        ImageButton boton_catalogo = (ImageButton) findViewById(R.id.catalogo_button);

        boton_catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent catalogo = new Intent(context, Catalogo.class);
                catalogo.putExtra("id_usuario", id);
                startActivity(catalogo);

            }
        });


        ImageButton boton_hacer_pedido = (ImageButton) findViewById(R.id.hacer_pedido_button);
        boton_hacer_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hacer_pedido = new Intent(context, HacerPedido.class);
                hacer_pedido.putExtra("id_usuario", id);
                startActivity(hacer_pedido);

            }
        });


        ImageButton boton_administrar_pedido = (ImageButton) findViewById(R.id.administrar_pedido_button);
        boton_administrar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent administrarPedido = new Intent(context, AdministrarPedido.class);
                administrarPedido.putExtra("id_usuario", id);
                startActivity(administrarPedido);

            }
        });


        ImageButton boton_historial = (ImageButton) findViewById(R.id.historial_button);
        boton_historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historialPedido = new Intent(context, HistorialPedido.class);
                historialPedido.putExtra("id_usuario", id);
                startActivity(historialPedido);

            }
        });

    }

}
