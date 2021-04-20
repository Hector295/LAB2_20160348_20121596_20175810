package com.ipt.dashboard.controller;


import com.ipt.dashboard.entity.Proyecto;
import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.ActividadRepository;
import com.ipt.dashboard.repository.ProyectoRepository;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/proyectos")
public class ProyectosController {

    @Autowired
    ProyectoRepository proyectoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ActividadRepository actividadRepository;



    @GetMapping("/listar")
    public String proyectoList(Model model) {
        model.addAttribute("proyectoList", proyectoRepository.findAll());
        return "proyecto/listaProyectos";
    }

    @GetMapping("/nuevo")
    public String nuevoProyecto(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "proyecto/nuevoProyecto";
    }

    @PostMapping("/guardar")
    public String guardarProyecto(Proyecto proyecto,
                                  RedirectAttributes attributes) {
        System.out.println(proyecto.getIdproyecto());
        if (proyecto.getIdproyecto() == 0) {
            attributes.addFlashAttribute("mensaje", "Proyecto creado exitosamente");
        } else {
            attributes.addFlashAttribute("mensaje", "Proyecto editado exitosamente");
        }
        proyectoRepository.save(proyecto);
        return "redirect:/proyectos/listar";
    }

    @GetMapping("/editar")
    public String editarProyecto(@RequestParam("id") int id,
                                    Model model) {
        Optional<Proyecto> optionalProyecto = proyectoRepository.findById(id);

        if (optionalProyecto.isPresent()) {
            Proyecto proyecto = optionalProyecto.get();
            model.addAttribute("proyecto", proyecto);
            model.addAttribute("listaUsuario", usuarioRepository.findAll());
            model.addAttribute("listaActividades",actividadRepository.findAll());
            return "/proyecto/editarProyecto";
        } else {
            return "redirect:/proyectos";
        }
    }


    @GetMapping("/eliminar")
    public String eliminarProyecto(@RequestParam("id") int id,
                                   RedirectAttributes attributes) {
        Optional<Proyecto> optionalProyecto = proyectoRepository.findById(id);
        if (optionalProyecto.isPresent()) {
            proyectoRepository.deleteById(id);
            attributes.addFlashAttribute("mensaje1", "Proyecto borrado exitosamente");
        }
        return "redirect:/proyectos/listar";
    }

}