package com.example.demo2.service;

import com.example.demo2.domain.Curso;
import com.example.demo2.domain.Profesor;

import java.util.List;

public interface ServiceProfesorInt {

    Profesor GuardarProfesor(Profesor profesor);

    List<Profesor> obtenerTodosProfesores();

    Profesor obtenerProfesorPorId(Integer id);

    void eliminarProfesorPorId(Integer id);

    void eliminarTodosProfesores();

    Long cantidadProfesores();

    Profesor encontrarProfesor(Profesor profesor);

    List<Profesor> profesoresOrdenadosPorId();

    List<Profesor> profesoresOrdenadosPorNombre();

    List<Profesor> profesoresOrdenadosPorApellido();
    List<Profesor> profesoresOrdenadosPorCorreo();

    List<Profesor> buscarPorId(Profesor profesor);

    List<Profesor> buscarPorNombre(Profesor profesor);

    void agregarProfesorACurso(Integer id_profesor, Integer id_curso);

    void borrarCursoAProfesor(Integer id_profesor, Integer id_curso);

    List<Curso> cursosAsignadosAProfesor(Integer id_profesor);

    //NO SE HA IMPLEMENTADO EN THYMELEAF:
    Profesor buscarProfesorDelCurso(Integer id_curso);
}
