package com.example.demo2.service;

import com.example.demo2.domain.Direccion;
import com.example.demo2.domain.Estudiante;
import com.example.demo2.domain.Profesor;

public interface ServiceDireccionInt {

    Direccion encontrarDireccionEstudiante(Estudiante estudiante);

    Direccion encontrarDireccionProfesor(Profesor profesor);

    Direccion agregarDireccion(Direccion direccion);
}
