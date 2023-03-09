package cl.edutecno.main;

import java.sql.SQLException;

import cl.edutecno.dao.CursoDAO;
import cl.edutecno.facade.Facade;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
//		CursoDAO cursoDAO = new CursoDAO();
//		System.out.println(cursoDAO.obtieneCursos());
		
		Facade facade = new Facade();
		System.out.println(facade.obtenerCursos());

	}

}
