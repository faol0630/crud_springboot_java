package com.example.demo2.service;

import com.example.demo2.dao.RepoDireccionInt;
import com.example.demo2.domain.Direccion;
import com.example.demo2.domain.Estudiante;
import com.example.demo2.domain.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDireccionImpl implements ServiceDireccionInt{

    @Autowired
    private RepoDireccionInt repoDireccionInt;

    @Override
    public Direccion encontrarDireccionEstudiante(Estudiante estudiante) {
        return repoDireccionInt.encontrarDireccionEstudiante(estudiante);
    }

    @Override
    public Direccion encontrarDireccionProfesor(Profesor profesor) {
        return repoDireccionInt.encontrarDireccionProfesor(profesor);
    }

    @Override
    public Direccion agregarDireccion(Direccion direccion) {
        return repoDireccionInt.save(direccion);
    }
}
