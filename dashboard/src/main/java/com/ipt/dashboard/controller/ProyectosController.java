package com.ipt.dashboard.controller;


import com.ipt.dashboard.entity.Proyecto;
import com.ipt.dashboard.repository.ProyectoRepository;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/proyectos")
public class ProyectosController {

    @Autowired
    ProyectoRepository proyectoRepository;


    @GetMapping("/listar")
    public String proyectoList(Model model) {
        model.addAttribute("proyectoList", proyectoRepository.findAll());
        return "proyecto/listaProyectos";
    }

}
