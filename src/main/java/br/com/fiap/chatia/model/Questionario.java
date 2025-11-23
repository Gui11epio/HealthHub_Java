package br.com.fiap.chatia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "TB_QUESTIONARIO")
public class Questionario {
    @Min(0) @Max(10)
    private int ansiedade;

    @Min(0) @Max(24)
    private int horasSono;

    @Min(0) @Max(10)
    private int estresse;

    @Min(0) @Max(10)
    private int sobrecarga;
    @Id
    private Long id;

    public Questionario() {
    }

    public Questionario(int ansiedade, int horasSono, int estresse, int sobrecarga) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
