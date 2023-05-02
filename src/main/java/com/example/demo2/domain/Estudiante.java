package com.example.demo2.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "estudiante")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Estudiante {

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

    //CascadeType.All borra el contenido de la base de datos
    //los demas al borrar el curso de la lista de cursos disponibles, borran al estudiante tambien
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "estudiante_curso",
            joinColumns = @JoinColumn(name = "estudiante_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id", referencedColumnName = "id_curso")
    )
    private List<Curso> cursos;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private Direccion direccion;

    public Estudiante() {
    }

    public Estudiante( String nombre, String apellido, String correo) {

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
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", cursos=" + cursos +
                ", direccion=" + direccion +
                '}';
    }
}
