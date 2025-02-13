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

SET @nombre = 'proyecto 1';
SET @proyecto = (select p.id from proyectos p where p.nombre = @nombre);
SELECT 
    r.nombre AS rubro,
   ( COALESCE(d.total_donaciones, 0) -
    COALESCE(o.total_ordenes, 0) )AS fondo
FROM rubros r
LEFT JOIN (
    SELECT proyecto_id, rubro_id, SUM(monto) AS total_donaciones
    FROM donaciones
    WHERE proyecto_id =  @proyecto
    GROUP BY rubro_id, proyecto_id
) d ON r.id = d.rubro_id
LEFT JOIN (
    SELECT proyecto_id, rubro_id, SUM(monto) AS total_ordenes
    FROM ordenes_compra
    WHERE proyecto_id =  @proyecto
    GROUP BY rubro_id, proyecto_id
) o ON r.id = o.rubro_id
WHERE r.proyecto_id = @proyecto;

