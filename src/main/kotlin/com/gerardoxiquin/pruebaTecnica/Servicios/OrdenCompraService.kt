package com.gerardoxiquin.pruebaTecnica.Servicios

import com.gerardoxiquin.pruebaTecnica.Modelos.OrdenCompra
import com.gerardoxiquin.pruebaTecnica.Repositorios.OrdenCompraRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrdenCompraService(@Autowired private val ordenCompraRepository: OrdenCompraRepository) {

    fun getAllOrdenes(): List<OrdenCompra> {
        return ordenCompraRepository.findAll()
    }

    fun getOrdenById(id: Long): Any? {
        return ordenCompraRepository.findById(id)
    }

    fun saveOrden(ordenCompra: OrdenCompra): OrdenCompra {
        return ordenCompraRepository.save(ordenCompra)
    }

    fun deleteOrden(id: Long) {
        ordenCompraRepository.deleteById(id)
    }
}
