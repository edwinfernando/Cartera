package com.cartera.masterkey.cartera.models;

/**
 * @author: Edwin Muñoz
 * @fecha: 05/04/16
 * @descripcion: Esta activity maneja la informacion de la impresora
 * @copyright: CODESA
 */
public class Impresora {
    private String name, address;

    public Impresora(String name, String address) {
        super();
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
