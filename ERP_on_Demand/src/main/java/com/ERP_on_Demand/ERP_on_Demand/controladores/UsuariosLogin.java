package com.ERP_on_Demand.ERP_on_Demand.controladores;



import com.ERP_on_Demand.ERP_on_Demand.Repositorios.UsuariosRepo;
import com.ERP_on_Demand.ERP_on_Demand.modelos.Usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController 
@RequestMapping("/login")


public class UsuariosLogin{

 @Autowired
 private UsuariosRepo usuariosRepo;
 
 @PostMapping
 public boolean login(@RequestBody Usuarios usuarios) {
     List<Usuarios> usuariosList = usuariosRepo.findAll();
     for (Usuarios usuario : usuariosList) {
        if (usuario.getNombre().equals(usuarios.getNombre()) && usuario.getContrasena().equals(usuarios.getContrasena()))
        {
return true;
        }else{
            return false;
        }
     }
         return false;

 }
}

