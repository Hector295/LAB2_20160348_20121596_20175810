package com.ipt.dashboard.controller;


import com.ipt.dashboard.entity.Actividad;
import com.ipt.dashboard.entity.Areas;
import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.ActividadRepository;
import com.ipt.dashboard.repository.AreasRepository;
import com.ipt.dashboard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/actividades")
public class ActividadesController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ActividadRepository actividadRepository;

    @GetMapping({"","/list"})
    public String listarActividades(Model model){
        List<Actividad> lista = actividadRepository.findAll();
        model.addAttribute("listaActividades",lista);
        return "/proyecto/editarProyecto";
    }
    @GetMapping("/nuevo")
    public String nuevoActividad(Model model) {
        List<Usuario> lista = usuarioRepository.findAll();
        model.addAttribute("listaUsuarios",lista);
        return "/actividad/nuevoActividad";
    }

    @PostMapping("/guardar")
    public String guardarActividad(Actividad actividad, RedirectAttributes attributes){
        if(actividad.getIdactividad()==0){
            attributes.addFlashAttribute("mensaje2","Usuario creado exitosamente");
        }else {
            attributes.addFlashAttribute("mensaje3","Usuario editado exitosamente");
        }
        actividadRepository.save(actividad);
        return "redirect:/proyecto/editarProyecto";
    }
    @GetMapping("/editar")
    public String editarActividad(Model model,
                                @RequestParam("id") int id){
        Optional<Actividad> optionalActividad=actividadRepository.findById(id);
        List<Usuario> lista = usuarioRepository.findAll();
        model.addAttribute("listaUsuarios",lista);
        if(optionalActividad.isPresent()){
            Actividad actividad = optionalActividad.get();
            model.addAttribute("usuario",actividad);
            return "actividad/editarActividad";
        }else {
            return "redirect:/proyectos/editar";
        }
    }
    @GetMapping("/eliminar")
    public String eliminarActividad(@RequestParam("id") int id,
                                  RedirectAttributes attributes){
        Optional<Actividad> optionalActividad=actividadRepository.findById(id);
        if(optionalActividad.isPresent()){
            actividadRepository.deleteById(id);
            attributes.addFlashAttribute("mensaje4","Usuario borrado exitosamente");
        }
        return "redirect:/proyectos/editar";
    }
}
