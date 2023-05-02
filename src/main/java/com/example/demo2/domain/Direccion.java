package com.example.demo2.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "address")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "city", nullable = false)
    @NotBlank(message= "Debe ingresar una ciudad")
    String ciudad;

    @Column(name = "street", nullable = false)
    @NotBlank(message = "Debe ingresar una calle")
    String calle;

    @Column(name = "number", nullable = false)
    @NotBlank(message = "Debe ingresar un numero")
    String numero;

    @Column(name = "postal_code", nullable = false)
    @NotBlank(message = "Debe ingresar un codigo postal")
    String codigo_postal;

    @OneToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @OneToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    public Direccion() {
    }

    public Direccion(String ciudad, String calle, String numero, String codigo_postal) {
        this.ciudad = ciudad;
        this.calle = calle;
        this.numero = numero;
        this.codigo_postal = codigo_postal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", ciudad='" + ciudad + '\'' +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", codigo_postal='" + codigo_postal + '\'' +
                ", estudiante=" + estudiante +
                '}';
    }
}
