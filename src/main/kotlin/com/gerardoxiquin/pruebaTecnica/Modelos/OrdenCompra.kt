package com.gerardoxiquin.pruebaTecnica.Modelos

import jakarta.persistence.*

@Entity
@Table(name = "ordenes_compra")
data class OrdenCompra(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(nullable = false)
    val fecha: String,

    @Column(nullable = false)
    val proveedor: String,

    @Column(nullable = false)
    val monto: Double,

    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    val proyecto: Proyecto?,

    @ManyToOne
    @JoinColumn(name = "rubro_id", nullable = false)
    val rubro: Rubro?
)