package cl.edutecno.facade;

import java.sql.SQLException;
import java.util.List;

import cl.edutecno.dao.CursoDAO;
import cl.edutecno.dao.FormaDePagoDAO;
import cl.edutecno.dao.InscripcionDAO;
import cl.edutecno.model.CursoDTO;
import cl.edutecno.model.FormaDePagoDTO;
import cl.edutecno.model.InscripcionDTO;

public class Facade {
	
	public int registrarInscripcion(InscripcionDTO dto) throws ClassNotFoundException, SQLException {
		
		InscripcionDAO inscripcionDAO = new InscripcionDAO();
		return inscripcionDAO.insertarInscripcion(dto);
	}
	
	public List<CursoDTO> obtenerCursos() throws ClassNotFoundException, SQLException{
		
		CursoDAO cursoDAO = new CursoDAO();
		return cursoDAO.obtieneCursos();
	}
	
	public List<FormaDePagoDTO> obtenerFormasDePago() throws ClassNotFoundException, SQLException{
		
		FormaDePagoDAO formaDePagoDAO = new FormaDePagoDAO();
		return formaDePagoDAO.obtieneFormasDePago();
	}
}
