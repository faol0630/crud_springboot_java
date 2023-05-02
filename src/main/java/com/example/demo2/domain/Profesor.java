package com.example.demo2.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Debe ingresar su nombre")
    private String nombre;

    @Column(name = "lastname", nullable = false)
    @NotBlank(message = "Debe ingresar un apellido")
    private String apellido;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "Debe ingresar un email")
    @Email
    private String correo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profesor", cascade = CascadeType.ALL)
    private List<Curso> cursos;

    @OneToOne(mappedBy = "profesor", cascade = CascadeType.ALL)
    private Direccion direccion;

    public Profesor() {
    }

    public Profesor(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cursos=" + cursos +
                ", correo='" + correo + '\'' +
                '}';
    }
}
