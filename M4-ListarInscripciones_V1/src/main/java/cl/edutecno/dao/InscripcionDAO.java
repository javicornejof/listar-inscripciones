package cl.edutecno.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import cl.edutecno.model.InscripcionDTO;

public class InscripcionDAO {
    public int insertarInscripcion(InscripcionDTO dto) throws SQLException, ClassNotFoundException {
        int max = 0;
        //Query para obtener una secuencia y asignar un id. La funcion MAX solo obtiene el valor de id_inscripcion
        //y le suma 1, con eso hacemos el incremento
        String consultaProximoId = " SELECT MAX(id_inscripcion)+1 FROM inscripcion ";
        //Query que insertara el valor
        String insertarInscripcion = " INSERT INTO inscripcion("
                                   + " id_inscripcion, nombre, telefono, id_curso, id_forma_pago )"
                                   + " VALUES (?,?,?,?,?) ";
        //conexion a la base de datos y ejecucion de la sentencia
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection  conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","usuario","admin");
        try(
                PreparedStatement stmt1 = conexion.prepareStatement(consultaProximoId);
                PreparedStatement stmt2 = conexion.prepareStatement(insertarInscripcion);
           ){
            ResultSet resultado = stmt1.executeQuery();
            if(resultado.next()) {
                max = resultado.getInt(1);
                stmt2.setInt(1, max);
                stmt2.setString(2, dto.getNombre());
                stmt2.setString(3, dto.getCelular());
                stmt2.setInt(4, dto.getIdCurso());
                stmt2.setInt(5, dto.getIdFormaDePago());
                if(stmt2.executeUpdate() != 1) {
                    throw new RuntimeException("A ocurrido un error inesperado");
                }
            }   
        }catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("A ocurrido un error inesperado" + ex);
        }
        return max;
    }
}
