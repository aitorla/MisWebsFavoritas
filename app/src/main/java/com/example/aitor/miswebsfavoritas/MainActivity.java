package com.example.aitor.miswebsfavoritas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ListView lvDirecciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazamos la listView
        lvDirecciones=(ListView) findViewById(R.id.lvDirecciones);


        // Creamos el Array
        ArrayList<Webs>  arraywebs= new ArrayList<Webs> ();
        Webs web;
        // Cargamos los datos
        // La primera línea está obsoleta desde la API 22 lo sustituímos por la segunda
        // web= new Webs ("Google","http://www.google.com", getContext().getDrawable(R.drawable.google),3);
        // web= new Webs ("Google","http://www.google.com", ContextCompat.getDrawable(this, R.drawable.google),3);
        web= new Webs ("Bing","http://www.bing.com", ContextCompat.getDrawable(this, R.drawable.bing),1);
        arraywebs.add(web);
        web= new Webs ("Yahoo","http://www.yahoo.com", ContextCompat.getDrawable(this,R.drawable.yahoo),2);
        arraywebs.add(web);
        web= new Webs ("Google","http://www.google.com", ContextCompat.getDrawable(this, R.drawable.google),3);
        arraywebs.add(web);
        web= new Webs ("Bing-es","http://www.bing.es", ContextCompat.getDrawable(this,R.drawable.bing),4);
        arraywebs.add(web);
        web= new Webs ("Yahoo-es","http://www.yahoo.es", ContextCompat.getDrawable(this,R.drawable.yahoo),5);
        arraywebs.add(web);
        web= new Webs ("Google-es","http://www.google.es", ContextCompat.getDrawable(this,R.drawable.google),6);
        arraywebs.add(web);

        // Creamos el adaptador personalizado

        AdaptadorWebs adapter = new AdaptadorWebs (this, arraywebs);

        // Aplico el adaptador a la lista
        lvDirecciones.setAdapter(adapter);

        // Creamos el listener para que cuando clickemos sobre la lista enlace con la url mediante un intend
        lvDirecciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
               String urldestino =
                        ((TextView) v.findViewById(R.id.tvUrl)).getText().toString();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urldestino));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public class AdaptadorWebs extends BaseAdapter { // Implementamos los métodos getCount, getItem, getView

        protected Activity activity;
        protected ArrayList<Webs> items;

        // Creamos el constructor
        public AdaptadorWebs(Activity activity, ArrayList<Webs> items) {
            this.activity = activity;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);

        }

        @Override
        public long getItemId(int position) {
            return items.get(position).getIdweb();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            // Cremos el objeto HolderView Optimizacion 2 ****
            ViewHolder holder;
            // Creamos su clase
            // public class ViewHolder {
            //TextView nombre;
            //TextView url;
            //ImageView logotipo;
            //}
            //
            // Generamos una convertView por motivos de eficiencia **** Optimización 1
            View item = convertView;
            // Asociamos el Layout con la listView
            if (item == null) {
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                item = inflater.inflate(R.layout.itemlista, null);


                ////****** Añadido Optimización 2 lo sustituimos por lo de abajo

                holder = new ViewHolder();
                holder.nombre = (TextView) item.findViewById(R.id.tvNombre);
                holder.url = (TextView) item.findViewById(R.id.tvUrl);
                holder.logotipo = (ImageView) item.findViewById(R.id.ivLogo);
                item.setTag (holder);
            }
            else
            {
                holder = (ViewHolder)item.getTag();
            }
                //Creamos un objeto web y rellenamos
                Webs web = items.get(position);
                holder = (ViewHolder) item.getTag();
                holder.nombre.setText(web.getNombre());
                holder.url.setText(web.getEnlace());
                holder.logotipo.setImageDrawable(web.getLogotipo());

                //***** Fin Añadido HolderView Optimizacion 2 *****/

            /**** Esto lo hemos eliminado para que esté optimizado con el ViewHolder (Optimización2)
            //Creamos un objeto web y rellenamos
            Webs web = items.get(position);
            // Rellenamos la fotografía
            ImageView logo = (ImageView) item.findViewById(R.id.ivLogo);
            logo.setImageDrawable(web.getLogo());
            // Rellenamos el nombre y la url
            TextView nombre = (TextView) item.findViewById(R.id.tvNombre);
            nombre.setText(web.getNombre());
            TextView url = (TextView) item.findViewById(R.id.tvUrl);
            url.setText(web.getEnlace());
             ***///

            // Devolvemos la lista
            return item;
        }
    }
}
