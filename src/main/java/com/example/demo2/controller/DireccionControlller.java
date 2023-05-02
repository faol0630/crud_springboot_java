package com.example.demo2.controller;

import com.example.demo2.domain.Direccion;
import com.example.demo2.domain.Estudiante;
import com.example.demo2.domain.Profesor;
import com.example.demo2.service.ServiceDireccionInt;
import com.example.demo2.service.ServiceInt;
import com.example.demo2.service.ServiceProfesorInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DireccionControlller {

    @Autowired
    private ServiceDireccionInt serviceDireccionInt;

    @Autowired
    private ServiceInt serviceEstudianteInt;

    @Autowired
    private ServiceProfesorInt serviceProfesorInt;

    @GetMapping("/direccion_estudiante/{estudiante}")
    public String direccionEstudiante(Estudiante estudiante, Model model){

        Direccion direccion = serviceDireccionInt.encontrarDireccionEstudiante(estudiante);
        model.addAttribute("direccion", direccion);

        estudiante = serviceEstudianteInt.obtenerEstudiante(estudiante.getId());
        model.addAttribute("estudiante", estudiante);

        return "direccion/direccion";
    }

    @GetMapping("/direccion_profesor/{profesor}")
    public String direccionProfesor(Model model, Profesor profesor){

        Direccion direccion = serviceDireccionInt.encontrarDireccionProfesor(profesor);
        model.addAttribute("direccion", direccion);

        profesor = serviceProfesorInt.obtenerProfesorPorId(profesor.getId());
        model.addAttribute("profesor", profesor);

        return "direccion/direccion_profesor";
    }

    @GetMapping("/direccion_intro/{estudiante}")
    public String direccionIntro(Model model, Estudiante estudiante){ //el parametro es obligatorio

        estudiante = serviceEstudianteInt.obtenerEstudiante(estudiante.getId());
        model.addAttribute("estudiante", estudiante);

        Direccion direccion = serviceDireccionInt.encontrarDireccionEstudiante(estudiante);
        model.addAttribute("direccion", direccion);

        return "direccion/modificar_direccion";
    }

    @GetMapping("/direccion_intro_profesor/{profesor}")
    public String direccionIntroProfesor(Model model, Profesor profesor){

        profesor = serviceProfesorInt.obtenerProfesorPorId(profesor.getId());
        model.addAttribute("profesor", profesor);

        Direccion direccion = serviceDireccionInt.encontrarDireccionProfesor(profesor);
        model.addAttribute("direccion", direccion);

        return "direccion/modificar_direccion_profesor";
    }



    @GetMapping("/nueva_direccion/{id}")
    public String direccionNueva(@PathVariable("id")Integer id, Model model){

        Estudiante estudiante = serviceEstudianteInt.obtenerEstudiante(id);
        Direccion direccion = new Direccion();
        direccion.setEstudiante(estudiante);
        model.addAttribute("direccion", direccion);

        return "direccion/nueva_direccion";
    }

    @GetMapping("/nueva_direccion_profesor/{id}")
    public String direccionNuevaProfesor(@PathVariable("id")Integer id, Model model){

        Profesor profesor = serviceProfesorInt.obtenerProfesorPorId(id);
        Direccion direccion = new Direccion();
        direccion.setProfesor(profesor);
        model.addAttribute("direccion", direccion);

        return "direccion/nueva_direccion_profesor";
    }

    @PostMapping("/guardar_direccion")
    public String agregarDireccion(@Valid Direccion direccion, Errors errors, Model model){

        if (errors.hasErrors()){
            return "direccion/modificar_direccion";
        }

        direccion = serviceDireccionInt.agregarDireccion(direccion);
        model.addAttribute("direccion", direccion);

        return "redirect:/lista_index";
    }

    @PostMapping("/guardar_direccion_profesor")
    public String agregarDireccionProfesor(@Valid Direccion direccion, Errors errors, Model model){

        if (errors.hasErrors()){
            return "direccion/modificar_direccion_profesor";
        }

        direccion = serviceDireccionInt.agregarDireccion(direccion);
        model.addAttribute("direccion", direccion);

        return "redirect:/lista_profesores";
    }
}
