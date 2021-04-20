package com.ipt.dashboard.controller;

import com.ipt.dashboard.entity.Areas;
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

import java.awt.geom.Area;
import java.util.Optional;

@Controller
@RequestMapping("/areas")
public class AreasController {

    @Autowired
    AreasRepository areasRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value={"", "/listar"})
    public String listaAreas (Model model){
        model.addAttribute("lista", areasRepository.findAll());
        return "/areas/listaAreas";
    }

    @GetMapping("/nuevaArea")
    public String nuevaArea() {
        return "areas/newForm";
    }

    @PostMapping("/save")
    public String saveArea(Areas areas,
                              RedirectAttributes attr){
        //System.out.println("shipper name" + shipper.getCompanyname());
        //System.out.println("phone: " +shipper.getPhone());
        if (areas.getIdarea()==0){
            attr.addFlashAttribute("msg", "Área creada exitosamente");
        }else{
            attr.addFlashAttribute("msg", "Área actualizada exitosamente");
        }
        areasRepository.save(areas);
        return "redirect:/areas/listar";
    }

    @GetMapping("/edit")
    public String editarArea(@RequestParam("id") int id, Model model){
        Optional<Areas> shipperOpt = areasRepository.findById(id);
        if(shipperOpt.isPresent()){
            Areas area = shipperOpt.get();
            model.addAttribute("areaEditable",area);
            model.addAttribute("listaUsuarios", usuarioRepository.filtrarUsuariosPorId(id));
            return "areas/editForm"; //Ruta del directorio del html
        }else{
            return "redirect:/areas/listar"; //Redirige al controlador areas/
        }
    }

    @GetMapping("/delete")
    public String deleteShipper(@RequestParam("id") int id,
                                RedirectAttributes attr){
        Optional<Areas> shipperOpt = areasRepository.findById(id);

        if(shipperOpt.isPresent()) {
            areasRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Eliminado exitosamente");
        }
        return "redirect:/areas/listar";
    }

}
