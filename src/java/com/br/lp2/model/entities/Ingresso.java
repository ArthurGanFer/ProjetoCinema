package com.br.lp2.model.entities;

import java.io.Serializable;

/**
 *
 * @author 1147106
 */
public class Ingresso implements Serializable{
    private int id_ingresso, cadeira, tipo;

    public Ingresso() {
    }

    public Ingresso(int cadeira, int tipo) {
        this.id_ingresso = -1;
        this.cadeira = cadeira;
        this.tipo = tipo;
    }

    public int getId_ingresso() {
        return id_ingresso;
    }

    public void setId_ingresso(int id_ingresso) {
        this.id_ingresso = id_ingresso;
    }

    public int getCadeira() {
        return cadeira;
    }

    public void setCadeira(int cadeira) {
        this.cadeira = cadeira;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Ingresso{" + "id_ingresso=" + id_ingresso + ", sala=" + cadeira + ", tipo=" + tipo + '}';
    }
}
