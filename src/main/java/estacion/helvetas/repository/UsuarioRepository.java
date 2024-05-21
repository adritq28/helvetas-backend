package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import estacion.helvetas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByNombre(String nombre);

    Optional<Usuario> findById(int idUsuario);

    @Query("SELECT u.idUsuario AS idUsuario, " +
            "m.nombre AS minucipio, e.nombre AS estacion, e.tipoEstacion AS tipoestacion, " +
            "CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')) AS nombre, " +
            "u.telefono AS telefono " +
            "FROM Usuario u " +
            "JOIN Observador o ON u.idUsuario = o.idUsuario " +
            "JOIN Estacion e ON o.idEstacion = e.idEstacion " + // Agrega un espacio antes de JOIN
            "JOIN Municipio m ON e.idMunicipio = m.idMunicipio")

    List<Object[]> buscarUsuariosConEstacion();

}