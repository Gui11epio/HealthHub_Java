package br.com.fiap.chatia.service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class QuestionarioRequest {
    @Min(0) @Max(10)
    private int ansiedade;

    @Min(0) @Max(24)
    private int horasSono;

    @Min(0) @Max(10)
    private int estresse;

    @Min(0) @Max(10)
    private int sobrecarga;

    public QuestionarioRequest() {
    }

    public QuestionarioRequest(int ansiedade, int horasSono, int estresse, int sobrecarga) {
        this.ansiedade = ansiedade;
        this.horasSono = horasSono;
        this.estresse = estresse;
        this.sobrecarga = sobrecarga;
    }

    public int getAnsiedade() {
        return ansiedade;
    }

    public void setAnsiedade(int ansiedade) {
        this.ansiedade = ansiedade;
    }

    public int getHorasSono() {
        return horasSono;
    }

    public void setHorasSono(int horasSono) {
        this.horasSono = horasSono;
    }

    public int getEstresse() {
        return estresse;
    }

    public void setEstresse(int estresse) {
        this.estresse = estresse;
    }

    public int getSobrecarga() {
        return sobrecarga;
    }

    public void setSobrecarga(int sobrecarga) {
        this.sobrecarga = sobrecarga;
    }
}
