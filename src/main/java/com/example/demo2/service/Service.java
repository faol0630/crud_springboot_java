package com.example.demo2.service;

import com.example.demo2.dao.RepositoryInt;
import com.example.demo2.domain.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service implements ServiceInt {

    @Autowired
    private RepositoryInt repositoryInt;

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        repositoryInt.save(estudiante);
    }

    @Override
    public List<Estudiante> obtenerTodos() {
        return repositoryInt.findAll();
    }

    @Override
    public Estudiante obtenerEstudiante(Integer id) {
        return repositoryInt.getReferenceById(id);
    }

    @Override
    public void actualizar(Estudiante estudiante) {
        repositoryInt.save(estudiante);
    }

    @Override
    public void eliminar(Integer id) {
        repositoryInt.deleteById(id);
    }

    @Override
    public Long cantidadEstudiantes() {
        return repositoryInt.count();
    }

    @Override
    public void borrarTodo() {
        repositoryInt.deleteAll();
    }

    @Override
    public Estudiante encontrarEstudiante(Estudiante estudiante) {
        return repositoryInt.findById(estudiante.getId()).orElse(null);
    }

    @Override
    public List<Estudiante> buscarPorNombre(Estudiante estudiante) {
        return repositoryInt.buscarPorNombre(estudiante.getNombre());
    }

    @Override
    public List<Estudiante> buscarPorApellido(Estudiante estudiante) {
        return repositoryInt.buscarPorApellido(estudiante.getApellido());
    }

    @Override
    public List<Estudiante> buscarPorId(Estudiante estudiante) {
        return repositoryInt.buscarPorId(estudiante.getId());
    }

    @Override
    public List<Estudiante> estudiantesOrdenadosPorId() {
        return repositoryInt.estudiantesOrdenadosPorId();
    }

    @Override
    public List<Estudiante> estudiantesOrdenadosPorNombre() {
        return repositoryInt.estudiantesOrdenadosPorNombre();
    }

    @Override
    public List<Estudiante> estudiantesOrdenadosPorApellido() {
        return repositoryInt.estudiantesOrdenadosPorApellido();
    }

    @Override
    public List<Estudiante> estudiantesOrdenadosPorCorreo() {
        return repositoryInt.estudiantesOrdenadosPorCorreo();
    }

    @Override
    public List<Estudiante> buscarPorNombreYApellido(String nombre, String apellido) {
        return repositoryInt.buscarPorNombreYApellido(nombre, apellido);
    }

    @Override
    public List<Estudiante> buscarPorNombreOApellido(String nombre, String apellido) {
        return repositoryInt.buscarPorNombreOApellido(nombre, apellido);
    }

    @Override
    public void agregarCursoAEstudiante(Integer estudiante_id, Integer curso_id) {
        repositoryInt.agregarCursoAEstudiante(estudiante_id, curso_id);
    }

    @Override
    public void borrarCursoAEstudiante(Integer estudiante_id, Integer curso_id) {
        repositoryInt.borrarCursoAEstudiante(estudiante_id, curso_id);
    }


}
