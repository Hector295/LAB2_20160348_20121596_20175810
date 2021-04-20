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
        return "/P/nuevoUsuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(Usuario usuario, RedirectAttributes attributes){
        if(usuario.getCorreo()==null){
            attributes.addFlashAttribute("mensaje","Usuario creado exitosamente");
        }else {
            attributes.addFlashAttribute("mensaje","Usuario editado exitosamente");
        }
        usuarioRepository.save(usuario);
        return "redirect:/usuario/list";
    }
    @GetMapping("/editar")
    public String editarUsuario(Model model,
                                @RequestParam("correo") String correo){
        Optional<Usuario> optionalUsuario=usuarioRepository.findById(correo);
        List<Areas> lista = areasRepository.findAll();
        model.addAttribute("listaAreas",lista);
        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            model.addAttribute("usuario",usuario);
            return "usuario/editarUsuario";
        }else {
            return "redirect:/usuario";
        }
    }
    @GetMapping("/eliminar")
    public String eliminarUsuario(@RequestParam("correo") String correo,
                                  RedirectAttributes attributes){
        Optional<Usuario> optionalUsuario=usuarioRepository.findById(correo);
        if(optionalUsuario.isPresent()){
            usuarioRepository.deleteById(correo);
            attributes.addFlashAttribute("mensaje","Usuario borrado exitosamente");
        }
        return "redirect:/usuario";
    }
}
