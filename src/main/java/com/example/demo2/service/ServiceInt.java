package com.example.demo2.service;

import com.example.demo2.domain.Estudiante;

import java.util.List;

public interface ServiceInt {

    void guardarEstudiante(Estudiante estudiante);

    List<Estudiante> obtenerTodos();

    Estudiante obtenerEstudiante(Integer id);

    void actualizar(Estudiante estudiante);

    void eliminar(Integer id);

    Long cantidadEstudiantes();

    void borrarTodo();

    Estudiante encontrarEstudiante(Estudiante estudiante);

    List<Estudiante> buscarPorNombre(Estudiante estudiante);
    List<Estudiante> buscarPorApellido(Estudiante estudiante);
    List<Estudiante> buscarPorId(Estudiante estudiante);
    List<Estudiante> estudiantesOrdenadosPorId();
    List<Estudiante> estudiantesOrdenadosPorNombre();
    List<Estudiante> estudiantesOrdenadosPorApellido();
    List<Estudiante> estudiantesOrdenadosPorCorreo();
    List<Estudiante> buscarPorNombreYApellido(String nombre,String apellido);
    List<Estudiante> buscarPorNombreOApellido(String nombre,String apellido);

    void agregarCursoAEstudiante(Integer estudiante_id, Integer curso_id);

    void borrarCursoAEstudiante(Integer estudiante_id, Integer curso_id);


}
