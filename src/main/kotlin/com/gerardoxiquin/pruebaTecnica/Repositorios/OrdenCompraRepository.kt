package com.gerardoxiquin.pruebaTecnica.Repositorios

import com.gerardoxiquin.pruebaTecnica.Modelos.OrdenCompra
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrdenCompraRepository : JpaRepository<OrdenCompra, Long>