package com.example.listview_android_java;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {



    // Model: Record (intents=puntuació, nom)
    class Record {
        public int intents;
        public String nom;

        public Record(int _intents, String _nom ) {
            intents = _intents;
            nom = _nom;
        }
        public int getIntents(){
            return intents;
        }

    }
    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;


    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String>listaPersonas = new ArrayList<String>();
        listaPersonas.add("Irene");
        listaPersonas.add("Rafa");
        listaPersonas.add("Sergio");
        listaPersonas.add("Edu");
        listaPersonas.add("Isma");
        listaPersonas.add("Jordi");
        listaPersonas.add("Pablo");
        listaPersonas.add("Jandro");
        listaPersonas.add("Borja");
        listaPersonas.add("Erik");
        listaPersonas.add("David");
        listaPersonas.add("Marc");
        listaPersonas.add("Albert");
        listaPersonas.add("Ivan");
        listaPersonas.add("Enric");

        // Inicialitzem model
        records = new ArrayList<Record>();

        // Afegim alguns exemples
        records.add( new Record(33,"Manolo") );
        records.add( new Record(12,"Pepe") );
        records.add( new Record(42,"Laura") );

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));

                int[] images = {R.drawable.hombre,R.drawable.mujer,R.drawable.hombre2,R.drawable.mujer2,R.drawable.hombre3,R.drawable.mujer3,R.drawable.hombre4,R.drawable.mujer4, R.drawable.hombre5};
                Random rand = new Random();
                ImageView imagen = convertView.findViewById(R.id.imagen);
                imagen.setImageResource(images[rand.nextInt(images.length)]);
                return convertView;
            }

        };
        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);

        // botó per afegir entrades a la ListView
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<15;i++) {

                    int random1 = (int) (Math.random()*15)+1;
                    int random2 = (int) (Math.random()*15);
                    records.add(new Record(random1, listaPersonas.get(random2)));
                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });
        Button bu = (Button) findViewById(R.id.ordena);
        bu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Collections.sort(records, Comparator.comparing(Record::getIntents).thenComparing(Record::getIntents));
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });
    }

}