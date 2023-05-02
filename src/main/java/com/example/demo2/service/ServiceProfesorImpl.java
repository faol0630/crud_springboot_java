package com.example.demo2.service;

import com.example.demo2.dao.RepoProfesor;
import com.example.demo2.domain.Curso;
import com.example.demo2.domain.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceProfesorImpl implements ServiceProfesorInt{

    @Autowired
    private RepoProfesor repoProfesor;

    @Override
    public Profesor GuardarProfesor(Profesor profesor) {
        return repoProfesor.save(profesor);
    }

    @Override
    public List<Profesor> obtenerTodosProfesores() {
        return repoProfesor.findAll();
    }

    @Override
    public Profesor obtenerProfesorPorId(Integer id) {
        return repoProfesor.getReferenceById(id);
    }

    @Override
    public void eliminarProfesorPorId(Integer id) {
        repoProfesor.deleteById(id);
    }

    @Override
    public void eliminarTodosProfesores() {
        repoProfesor.deleteAll();
    }

    @Override
    public Long cantidadProfesores() {
        return repoProfesor.count();
    }

    @Override
    public Profesor encontrarProfesor(Profesor profesor) {
        return repoProfesor.findById(profesor.getId()).orElse(null);
    }

    @Override
    public List<Profesor> profesoresOrdenadosPorId() {
        return repoProfesor.profesoresOrdenadosPorId();
    }

    @Override
    public List<Profesor> profesoresOrdenadosPorNombre() {
        return repoProfesor.profesoresOrdenadosPorNombre();
    }

    @Override
    public List<Profesor> profesoresOrdenadosPorApellido() {
        return repoProfesor.profesoresOrdenadosPorApellido();
    }

    @Override
    public List<Profesor> profesoresOrdenadosPorCorreo() {
        return repoProfesor.profesoresOrdenadosPorCorreo();
    }

    @Override
    public List<Profesor> buscarPorId(Profesor profesor) {
        return repoProfesor.buscarPorId(profesor.getId());
    }

    @Override
    public List<Profesor> buscarPorNombre(Profesor profesor) {
        return repoProfesor.buscarPorNombre(profesor.getNombre());
    }

    @Override
    public void agregarProfesorACurso(Integer id_profesor, Integer id_curso) {
        repoProfesor.agregarProfesorACurso(id_profesor, id_curso);
    }

    @Override
    public void borrarCursoAProfesor(Integer id_profesor, Integer id_curso) {
        repoProfesor.borrarCursoAProfesor(id_profesor, id_curso);
    }

    @Override
    public List<Curso> cursosAsignadosAProfesor(Integer id_profesor) {
        return repoProfesor.cursosAsignadosAProfesor(id_profesor);
    }

    //NO SE HA IMPLEMENTADO EN THYMELEAF:
    @Override
    public Profesor buscarProfesorDelCurso(Integer id_curso) {
        return repoProfesor.buscarProfesorDelCurso(id_curso);
    }
}
