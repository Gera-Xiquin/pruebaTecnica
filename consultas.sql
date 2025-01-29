a)

SELECT 
    p.nombre AS proyecto,
    (SUM(o.monto) / SUM(d.monto)) * 100 AS porcentaje_ejecucion
FROM proyectos p
LEFT JOIN donaciones d ON p.id = d.proyecto_id
LEFT JOIN ordenes o ON p.id = o.proyecto_id
GROUP BY p.id;

b)

SELECT 
    r.nombre AS rubro,
    COALESCE(SUM(d.monto), 0) - COALESCE(SUM(o.monto), 0) AS fondos_disponibles
FROM rubros r
LEFT JOIN donaciones d ON r.id = d.rubro_id
LEFT JOIN ordenes o ON r.id = o.rubro_id
WHERE r.proyecto_id = (SELECT id FROM proyectos WHERE nombre = 'X')
GROUP BY r.id;
