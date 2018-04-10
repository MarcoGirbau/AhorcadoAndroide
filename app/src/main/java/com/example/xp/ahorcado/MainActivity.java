package com.example.xp.ahorcado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String palabraOculta = "CETYS";
    int numerosFallos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.ventanaJuego, new VentanaAhorcado())
                    .commit();
        }
        palabraOculta = escogerPalabra();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        palabraOculta = escogerPalabra();
        String barras = "";
        for(int i = 0; i < palabraOculta.length(); i++)
        {
            barras += "_ ";
        }
        ((TextView) findViewById(R.id.palabraConGuiones)).setText(barras);
    }
    public void botonPulsado (View vista)
    {
        Button boton = (Button) findViewById(vista.getId());
        boton.setVisibility(View.INVISIBLE);
        chequeaLetra(boton.getText().toString());
    }

    private void chequeaLetra (String letra)
    {
        letra = letra.toUpperCase();
        ImageView imagenAhorcado = ((ImageView) findViewById(R.id.imagenAhorcado));
        TextView textoGuiones = ((TextView) findViewById(R.id.palabraConGuiones));
        String palabrasConGuiones = textoGuiones.getText().toString();

        boolean acierto = false;

        for(int i = 0; i < palabraOculta.length(); i++)
        {
            if(palabraOculta.charAt(i) == letra.charAt(0))
            {
                //Quita el guin bajo de la letra correspondiente
                palabrasConGuiones = palabrasConGuiones.substring(0,2*i) + letra + palabrasConGuiones.substring(2*i + 1);
                acierto = true;
            }
        }

        if(!palabrasConGuiones.contains("_"))
        {
            imagenAhorcado.setImageResource(R.drawable.acertastetodo);
        }

        textoGuiones.setText(palabrasConGuiones);

        if(!acierto)
        {
            numerosFallos++;
            switch (numerosFallos)
            {
                case 0: imagenAhorcado.setImageResource(R.drawable.ahorcado_0);
                break;
                case 1: imagenAhorcado.setImageResource(R.drawable.ahorcado_1);
                break;
                case 2: imagenAhorcado.setImageResource(R.drawable.ahorcado_2);
                break;
                case 3: imagenAhorcado.setImageResource(R.drawable.ahorcado_3);
                break;
                case 4: imagenAhorcado.setImageResource(R.drawable.ahorcado_4);
                break;
                case 5: imagenAhorcado.setImageResource(R.drawable.ahorcado_5);
                break;
                default: imagenAhorcado.setImageResource(R.drawable.ahorcado_fin);
                break;
            }
        }
    }

    private String escogerPalabra()
    {
        String auxiliar = "CETYS";
        String[] listaPalabras = {"CETYS", "hola", "adios", "cono", "desconozco", "icono", "diacono",
                                "conocida", "conocimiento", "imbecil", "imperativo"};
        Random r = new Random();
        auxiliar = listaPalabras[ r.nextInt(listaPalabras.length)];
        auxiliar = auxiliar.toUpperCase();
        return auxiliar;
    }

    public void restarto (View v)
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
