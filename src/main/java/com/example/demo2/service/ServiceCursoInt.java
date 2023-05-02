package com.example.demo2.service;

import com.example.demo2.domain.Curso;

import java.util.List;

public interface ServiceCursoInt {

    Curso GuardarCurso(Curso curso);

    List<Curso> obtenerTodosCursos();

    Curso obtenerCurso(Integer id);

    void actualizarCurso(Curso curso);

    void eliminarCurso(Integer id);

    void eliminarTodosCursos();

    Long cantidadCursos();

    Curso encontrarCurso(Curso curso);

    List<Curso> buscarPorId(Curso curso);

    List<Curso> buscarPorNombre(Curso curso);

    List<Curso>cursosOrdenadosId();

    List<Curso>cursosOrdenadosNombre();

    List<Curso>cursosOrdenadosDuracion();

}
