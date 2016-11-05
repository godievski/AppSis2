package com.example.fredy.finalfinalfinalx3sis2.Vistas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.LayoutDirection;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fredy.finalfinalfinalx3sis2.Controller.GestorCliente;
import com.example.fredy.finalfinalfinalx3sis2.Controller.GestorProducto;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.Cliente;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.DetallePedido;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.Pedido;
import com.example.fredy.finalfinalfinalx3sis2.Modelo.Producto;
import com.example.fredy.finalfinalfinalx3sis2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fredy on 11/4/16.
 */

public class HacerPedidoCatalogo extends AppCompatActivity {
    private GestorCliente gestorCliente;
    private GestorProducto gestorProducto;
    private Cliente cl;
    private String id;
    private Context context;
    private Pedido ped ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hacer_pedido_catalogo);
        context = this;
        this.InicializarAtributos();
        this.Inicializar_menu(context);

        Button siguiente = (Button) findViewById(R.id.hacer_pedido_catalogo_siguiente);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: SACAR VALRES DE CANTIDAD DE CADA PRODUCTO
                actualizarPedido();
                Intent perfil = new Intent(context, ConfirmarPedido.class);
                perfil.putExtra("id_usuario",id);
                perfil.putExtra("pedido",ped);
                startActivity(perfil);
            }
        });
        Button anterior = (Button) findViewById(R.id.hacer_pedido_catalogo_anterior);
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil = new Intent(context, HacerPedido.class);
                perfil.putExtra("id_usuario",id);
                startActivity(perfil);
            }
        });
    }

    private void actualizarPedido(){
        LinearLayout padre = (LinearLayout) findViewById(R.id.hacer_pedido_catalogo_content);
        int id = 1;
        Double monto = 0.0;
        for(int i = 0; i < this.gestorProducto.size(); i++){
            LinearLayout child = (LinearLayout) padre.getChildAt(i);
            child = (LinearLayout) child.getChildAt(0);
            LinearLayout right = (LinearLayout) child.getChildAt(1);
            EditText cajitaNum = (EditText) right.getChildAt(0);
            Integer num = Integer.parseInt(cajitaNum.getText().toString());

            if (num > 0){
                /*CREO DETALLE PEDIDO*/
                Producto p = this.gestorProducto.getProd(i);
                /*String  identificador, Integer cantidad,Double subTotal,Producto producto*/
                DetallePedido dp = new DetallePedido(id+"",num,num*p.getPrecio(),p);
                this.ped.AgregarDetallePedido(dp);
                monto += num*p.getPrecio();
                id += 1;
            }
        }
        this.ped.setMonto(monto);
    }

    private void InicializarAtributos(){
        try{
            Bundle datos = this.getIntent().getExtras();
            id = datos.getString("id_usuario");
        } catch(Exception ex){
            CharSequence text = "No se paso el id por Intent";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        this.gestorProducto=new GestorProducto();
        this.gestorCliente = new GestorCliente();
        gestorCliente.deserializar(context);
        this.cl = gestorCliente.obtenerClientePorID(id);
        if (cl == null) {
            CharSequence text = "Cliente error Perfil";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        Integer numPedido = this.cl.gPedidos.size();
        /*String identificador, Double monto, String estado, Integer numCuotas, String tracking,String fecha*/
        String currentDateTimeString= DateFormat.getDateFormat(this).format(new Date());
        ped= new Pedido(String.valueOf(numPedido + 1),0.0,"Por Cancelar",0,"Procesando",currentDateTimeString);

        //CARGAR PANTALLA CON PRODUCTOS SEGUN CATALOGO
        ScrollView main = (ScrollView) findViewById(R.id.hacer_pedido_catalogo_sw);
        LinearLayout padre = (LinearLayout) findViewById(R.id.hacer_pedido_catalogo_content);
        LinearLayout botones = (LinearLayout) padre.getChildAt(1);
        LinearLayout template = (LinearLayout) padre.getChildAt(0);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        padre.removeViewAt(0);
        padre.removeViewAt(0);
        /*hacer_pedido_catalogo_content*/
        LinearLayout linearItem;
        for(int i = 0; i < this.gestorProducto.size(); i++){
            Producto p = gestorProducto.getProd(i);
            LinearLayout child = (LinearLayout) inflater.inflate(R.layout.template,null);
            modificarItem((LinearLayout) child.getChildAt(0),p);
            padre.addView(child);
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

    private void modificarItem(LinearLayout child, Producto p){
        LinearLayout left = (LinearLayout) child.getChildAt(0);
        LinearLayout right = (LinearLayout) child.getChildAt(1);
        /*LEFT*/
        ImageView imagen = (ImageView) left.getChildAt(0);
        TextView nombre = (TextView) left.getChildAt(1);
        nombre.setText(p.getNombre());
        TextView precio = (TextView) left.getChildAt(2);
        precio.setText(p.getPrecio().toString());
        TextView unidad = (TextView) left.getChildAt(3);
        unidad.setText(p.getUnidad());
    }
}
