package com.ipt.dashboard.controller;


import com.ipt.dashboard.entity.Actividad;
import com.ipt.dashboard.entity.Areas;
import com.ipt.dashboard.entity.Proyecto;
import com.ipt.dashboard.entity.Usuario;
import com.ipt.dashboard.repository.ActividadRepository;
import com.ipt.dashboard.repository.AreasRepository;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/actividades")
public class ActividadesController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ActividadRepository actividadRepository;
    @Autowired
    ProyectoRepository proyectoRepository;

    @GetMapping({"","/list"})
    public String listarActividades(Model model){
        List<Actividad> lista = actividadRepository.findAll();

        model.addAttribute("listaActividades",lista);
        return "/proyecto/editarProyecto";
    }
    @GetMapping("/nuevo")
    public String nuevoActividad(Model model,@RequestParam("id") int id) {
        Optional<Proyecto> optionalProyecto=proyectoRepository.findById(id);
        Proyecto proyecto=optionalProyecto.get();
        model.addAttribute("proyecto",proyecto);
        List<Usuario> lista = usuarioRepository.findAll();
        model.addAttribute("listaUsuarios",lista);
        return "/actividad/nuevoActividad";
    }

    @PostMapping("/guardar")
    public String guardarActividad(Actividad actividad, RedirectAttributes attributes,
                                   Model model,@RequestParam("id") int id){
        if(actividad.getIdactividad()==0){
            attributes.addFlashAttribute("mensaje2","Actividad creada exitosamente");
        }else {
            attributes.addFlashAttribute("mensaje3","Actividad editada exitosamente");
        }
        Optional<Proyecto> optionalProyecto=proyectoRepository.findById(id);
        Proyecto proyecto= optionalProyecto.get();
        System.out.println(proyecto.getIdproyecto());
        actividadRepository.save(actividad);
        String url="redirect:/proyectos/editar?id=";
        String id2=null;
         id2= String.valueOf(proyecto.getIdproyecto());
        System.out.println(url+id2);
        return url+id2;
    }
    @GetMapping("/editar")
    public String editarActividad(Model model,
                                @RequestParam("id2") int id,@RequestParam("id") int id_proyecto){
        Optional<Actividad> optionalActividad=actividadRepository.findById(id);
        List<Usuario> lista = usuarioRepository.findAll();
        Optional<Proyecto> listproyecto =proyectoRepository.findById(id_proyecto);
        model.addAttribute("listaUsuarios",lista);
        if(optionalActividad.isPresent()){
            Actividad actividad = optionalActividad.get();
            Proyecto proyecto = listproyecto.get();
            model.addAttribute("actividad",actividad);
            model.addAttribute("proyecto",proyecto);
            return "actividad/editarActividad";
        }else {
            return "redirect:/proyectos/editar";
        }
    }
    @GetMapping("/eliminar")
    public String eliminarActividad(@RequestParam("id2") int id,Model model,
                                  RedirectAttributes attributes, @RequestParam("id") int id_pro){
        Optional<Actividad> optionalActividad=actividadRepository.findById(id);
        Optional<Proyecto> optionalProyecto=proyectoRepository.findById(id_pro);
        String id2=null;
        if(optionalActividad.isPresent()){
            Proyecto proyecto =optionalProyecto.get();
            actividadRepository.deleteById(id);
            model.addAttribute("proyecto",proyecto);
            attributes.addFlashAttribute("mensaje4","Actividad borrada exitosamente");
            id2= String.valueOf(proyecto.getIdproyecto());
        }
        String url="redirect:/proyectos/editar?id=";


        System.out.println(url+id2);
        return url+id2;
    }
}
