package com.example.demo2.controller;


import com.example.demo2.domain.Estudiante;
import com.example.demo2.service.ServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private ServiceInt serviceInt;

    @GetMapping("/nuevo_estudiante")
    public String agregarEstudiante(Model model){ //el parametro es obligatorio

        Estudiante estudiante = new Estudiante();
        model.addAttribute("estudiante", estudiante);

        return "estudiante/nuevo_estudiante";
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(@Valid Estudiante estudiante, Errors errors, Model model){
        //respetar el orden de los parametros: Entity, Errors, Model
        //@Valid y Errors hacen parte de validation
        if (errors.hasErrors()){
           return "estudiante/nuevo_estudiante";
        }
        serviceInt.guardarEstudiante(estudiante);
        return "redirect:/nueva_direccion/" + estudiante.getId();

    }


    //llevar la info desde la lista al html modificar
    @GetMapping("/editar/{id}")
    public String editarEstudiante(Estudiante estudiante, Model model){
        estudiante = serviceInt.encontrarEstudiante(estudiante);
        model.addAttribute("estudiante", estudiante);
        return "estudiante/modificar";
    }

    @PostMapping("/guardar_editado")
    public String guardarEstudianteEditado(@Valid Estudiante estudiante, Errors errors, Model model){
        if (errors.hasErrors()){
            return "estudiante/modificar";
        }
        serviceInt.guardarEstudiante(estudiante);
        return "redirect:/lista_index";

    }

    @GetMapping({"/lista_index", "", "/"})
    public String estudiantesDesdeSQL(Model model){

        Long totalEstudiantes = serviceInt.cantidadEstudiantes();
        model.addAttribute("totalEstudiantes", totalEstudiantes);

        List<Estudiante> miLista;
        miLista = serviceInt.obtenerTodos();
        model.addAttribute("miLista", miLista);
        return "estudiante/lista";
    }



    @GetMapping("/eliminar/{id}")
    public String eliminar(Estudiante estudiante){
        serviceInt.eliminar(estudiante.getId());
        return "redirect:/lista_index";
    }

    @GetMapping("/eliminar_lista")
    public String borrarTodo(){

        serviceInt.borrarTodo();
        return "redirect:/lista_index";
    }

    @GetMapping("/totalEstudiantes")
    public Long cantidadEstudiantes(){
        return serviceInt.cantidadEstudiantes();
    }

    @GetMapping("/estudiante_n")
    public String buscarEstudiante(Estudiante estudiante){
        return "estudiante/buscar";
    }

    @GetMapping("/estudiante_n/{nombre}")
    public String buscarEstudiantePorNombre(Estudiante estudiante, Model model){

        List<Estudiante> miLista = serviceInt.buscarPorNombre(estudiante);
        model.addAttribute("miLista", miLista);
        return "estudiante/buscar";

    }

    @GetMapping("/estudiante_a/{apellido}")
    public String buscarEstudiantePorApellido(Estudiante estudiante, Model model){

        List<Estudiante> miLista = serviceInt.buscarPorApellido(estudiante);
        model.addAttribute("miLista", miLista);
        return "estudiante/buscar";

    }

    @GetMapping("/estudiante_i/{id}")
    public String buscarEstudiantePorId(Estudiante estudiante, Model model){

        if (estudiante.getId() == null){
            return "estudiante/buscar";
        }

        List<Estudiante> miLista = serviceInt.buscarPorId(estudiante);
        model.addAttribute("miLista", miLista);
        return "estudiante/buscar";

    }

    @GetMapping("/lista_id")
    public String estudiantesOrdenadosPorId(Model model){

        Long totalEstudiantes = serviceInt.cantidadEstudiantes();
        model.addAttribute("totalEstudiantes", totalEstudiantes);

        List<Estudiante> miLista;
        miLista = serviceInt.estudiantesOrdenadosPorId();
        model.addAttribute("miLista", miLista);

        return "estudiante/lista";
    }

    @GetMapping("/lista_nombre")
    public String estudiantesOrdenadosPorNombre(Model model){

        Long totalEstudiantes = serviceInt.cantidadEstudiantes();
        model.addAttribute("totalEstudiantes", totalEstudiantes);

        List<Estudiante> miLista;
        miLista = serviceInt.estudiantesOrdenadosPorNombre();
        model.addAttribute("miLista", miLista);

        return "estudiante/lista";
    }

    @GetMapping("/lista_apellido")
    public String estudiantesOrdenadosPorApellido(Model model){

        Long totalEstudiantes = serviceInt.cantidadEstudiantes();
        model.addAttribute("totalEstudiantes", totalEstudiantes);

        List<Estudiante> miLista;
        miLista = serviceInt.estudiantesOrdenadosPorApellido();
        model.addAttribute("miLista", miLista);

        return "estudiante/lista";
    }

    @GetMapping("/lista_correo")
    public String estudiantesOrdenadosPorCorreo(Model model){

        Long totalEstudiantes = serviceInt.cantidadEstudiantes();
        model.addAttribute("totalEstudiantes", totalEstudiantes);

        List<Estudiante> miLista;
        miLista = serviceInt.estudiantesOrdenadosPorCorreo();
        model.addAttribute("miLista", miLista);

        return "estudiante/lista";
    }

}
