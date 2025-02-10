package com.gerardoxiquin.pruebaTecnica.Modelos

import jakarta.persistence.*


@Entity
@Table(name = "donaciones")
data class Donacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(nullable = false)
    val fecha: String = "",

    @Column(nullable = false)
    val donante: String = "",

    @Column(nullable = false)
    val monto: Double = 0.0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    val proyecto: Proyecto? = Proyecto(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rubro_id", nullable = false)
    val rubro: Rubro? = Rubro()
)