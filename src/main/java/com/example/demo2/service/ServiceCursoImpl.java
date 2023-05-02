package com.example.demo2.service;

import com.example.demo2.dao.RepoCursoInt;
import com.example.demo2.domain.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCursoImpl implements ServiceCursoInt{

    @Autowired
    private RepoCursoInt repoCursoInt;

    @Override
    public Curso GuardarCurso(Curso curso) {
        return repoCursoInt.save(curso);
    }

    @Override
    public List<Curso> obtenerTodosCursos() {
        return repoCursoInt.findAll();
    }

    @Override
    public Curso obtenerCurso(Integer id) {
        return repoCursoInt.getReferenceById(id);
    }

    @Override
    public void actualizarCurso(Curso curso) {
        repoCursoInt.save(curso);
    }

    @Override
    public void eliminarCurso(Integer id) {
        repoCursoInt.deleteById(id);
    }

    @Override
    public void eliminarTodosCursos() {
        repoCursoInt.deleteAll();
    }

    @Override
    public Long cantidadCursos() {
        return repoCursoInt.count();
    }

    @Override
    public Curso encontrarCurso(Curso curso) {
        return repoCursoInt.findById(curso.getId_curso()).orElse(null);
    }

    @Override
    public List<Curso> buscarPorId(Curso curso) {
        return repoCursoInt.buscarPorId(curso.getId_curso());
    }

    @Override
    public List<Curso> buscarPorNombre(Curso curso) {
        return repoCursoInt.buscarPorNombre(curso.getNombre());
    }

    @Override
    public List<Curso> cursosOrdenadosId() {
        return repoCursoInt.cursosOrdenadosPorId();
    }

    @Override
    public List<Curso> cursosOrdenadosNombre() {
        return repoCursoInt.cursosOrdenadosPorNombre();
    }

    @Override
    public List<Curso> cursosOrdenadosDuracion() {
        return repoCursoInt.cursosOrdenadosPorDuracion();
    }


}
