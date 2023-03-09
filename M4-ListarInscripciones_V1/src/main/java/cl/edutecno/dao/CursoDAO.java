package cl.edutecno.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.edutecno.model.CursoDTO;

/**
* Metodo encargado de obtener todos los cursos disponibles
* @return Lista de cursos CursoDTO
* @throws SQLException
* @throws ClassNotFoundException
* @autor developer
*/
public class CursoDAO {
	
	//metodo encargado de obtener los cursos consultando a la base de datos
	public List<CursoDTO> obtieneCursos() throws SQLException, ClassNotFoundException {
		
		//creamos la lista de objetos que devolveran los resultados
		List<CursoDTO> listaDeCursos = new ArrayList<CursoDTO>();
		
		//creamos la consulta a la base de datos
		String consultaSql = " SELECT id_curso, descripcion, precio " 
				   		   + " FROM curso ";
		
//		Oracle
		//conexion a la base de datos y ejecucion de la sentencia
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection  conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","usuario","admin");
		
//		MySQL
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/cursojsp?user=root&password=12345");
		
		
		try(PreparedStatement stmt = conexion.prepareStatement(consultaSql)){//se prepara la query de ejecución
	
			ResultSet resultado = stmt.executeQuery(); //se ejecuta la query
			while(resultado.next()) { //mientras existan datos o filas en la tabla
				CursoDTO cursoDto = new CursoDTO();
				cursoDto.setIdCurso(resultado.getInt("id_curso"));
				cursoDto.setDescripcion(resultado.getString("descripcion"));
				cursoDto.setPrecio(resultado.getDouble("precio"));
				listaDeCursos.add(cursoDto);
			}	
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return listaDeCursos;
	}
}
