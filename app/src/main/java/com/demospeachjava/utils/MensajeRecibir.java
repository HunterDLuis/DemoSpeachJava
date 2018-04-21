package com.demospeachjava.utils;

import com.demospeachjava.model.ObjetoMensaje;

public class MensajeRecibir extends ObjetoMensaje {

    private long hora;

    public MensajeRecibir() {
    }

    public MensajeRecibir(long hora) {
        this.hora = hora;
    }

    public MensajeRecibir(String mensaje, String urlFoto, String nombre, String fotoPerfil, String type_mensaje, long hora) {
        super(mensaje, urlFoto, nombre, fotoPerfil, type_mensaje);
        this.hora = hora;
    }

    public long getHora() {
        return hora;
    }

    public void setHora(long hora) {
        this.hora = hora;
    }
}
