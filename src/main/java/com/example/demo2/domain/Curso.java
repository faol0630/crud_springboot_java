package com.example.demo2.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "curso")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Integer id_curso;

    @Column(name="cname", nullable = false)
    @NotBlank(message= "Debe ingresar un nombre")
    private String nombre;

    @Column(name="hours", nullable = false)
    @NotNull(message="Debe ingresar la cantidad de horas")
    private Integer duracion;

    @ManyToMany(mappedBy = "cursos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Estudiante> estudiantes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor") //crea en esta tabla, en SQL(teacher), un campo llamado id_profesor
    private Profesor profesor;

    public Curso() {
    }

    public Curso(String nombre, Integer duracion) {
        this.nombre = nombre;
        this.duracion = duracion;

    }

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }


    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id_curso=" + id_curso +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", estudiantes=" + estudiantes +
                ", profesor=" + profesor +
                '}';
    }
}
