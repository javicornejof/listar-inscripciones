package cl.edutecno.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.edutecno.facade.Facade;
import cl.edutecno.model.CursoDTO;
import cl.edutecno.model.FormaDePagoDTO;

@WebServlet("/preInscripcion")
public class PreInscripcion extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//obtenemos el facade
		Facade facade = new Facade();
		try {
			//obtenemos las listas
			List<CursoDTO> listaCursos = null;
			try {
				listaCursos = facade.obtenerCursos();//obtener los cursos
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			List<FormaDePagoDTO> listaFormasPago = null;
			try {
				listaFormasPago = facade.obtenerFormasDePago();//obtener las formas de pago
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			//guardo las listas al request, y enviarlas a la vista inscripcion.jsp
			request.setAttribute("cursos", listaCursos);
			request.setAttribute("formasPago", listaFormasPago);
			
			//mandamos el request a la pagina jsp
			request.getRequestDispatcher("inscripcion.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
