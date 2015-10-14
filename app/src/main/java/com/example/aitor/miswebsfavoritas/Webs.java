package com.example.aitor.miswebsfavoritas;

import android.graphics.drawable.Drawable;

/**
 * Created by aitor on 12/10/15.
 */
public class Webs {

    protected String nombre;
    protected Drawable logotipo;
    protected long idweb;
    protected String enlace;

    public Webs (String nom, String url, Drawable logo) {
        super();
        this.nombre=nom;
        this.enlace=url;
        this.logotipo=logo;
    }
    public Webs (String nom, String url, Drawable logo, long id) {
        super();
        this.nombre=nom;
        this.enlace=url;
        this.logotipo=logo;
        this.idweb=id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public Drawable getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(Drawable logotipo) {
        this.logotipo = logotipo;
    }

    public long getIdweb() {
        return idweb;
    }

    public void setIdweb(long idweb) {
        this.idweb = idweb;
    }
}
