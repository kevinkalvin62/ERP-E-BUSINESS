package com.ERP_on_Demand.ERP_on_Demand.Repositorios;

import com.ERP_on_Demand.ERP_on_Demand.modelos.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepo extends JpaRepository<Usuarios, Long> {
}
