a)

SELECT 
    p.nombre AS proyecto,
    ((SUM(o.monto)*100)/(SUM(d.monto))) AS porcentaje_ejecucion
FROM proyectos p
LEFT JOIN (
    SELECT proyecto_id, SUM(monto) AS monto
    FROM donaciones
    GROUP BY proyecto_id
) d ON p.id = d.proyecto_id
LEFT JOIN (
    SELECT proyecto_id, SUM(monto) AS monto
    FROM ordenes_compra
    GROUP BY proyecto_id
) o ON p.id = o.proyecto_id
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
