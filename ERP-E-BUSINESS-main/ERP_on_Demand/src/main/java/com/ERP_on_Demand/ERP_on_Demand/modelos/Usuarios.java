package com.ERP_on_Demand.ERP_on_Demand.modelos;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Usuarios {
    @Id
    
    @Column(name = "id_usuario", nullable = false)
    private String idUsuario; // No incremental, se le asigna un ID al registrarlo
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    
    @Column(name = "permisos", nullable = false)
    private String permisos; // Define el cargo en la app



    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public Usuarios get() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    } 

}
