package cl.edutecno.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.edutecno.model.*;
import cl.edutecno.facade.*;

@WebServlet("/posInscripcion")
public class PosInscripcion extends HttpServlet
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
   {
      // obtengo los datos del formulario
      String nombre = request.getParameter("nombre");
      String celular = request.getParameter("telefono");
      int idCurso = Integer.parseInt(request.getParameter("idCurso"));
      int idFormaDePago = Integer.parseInt(request.getParameter("idFormaPago"));
       // instancio el DTO y le asigno los datos
      InscripcionDTO dto = new InscripcionDTO();
      dto.setNombre(nombre);
      dto.setCelular(celular);
      dto.setIdCurso(idCurso);
      dto.setIdFormaDePago(idFormaDePago);
      // invoco al facade para procesar la inscripcion
      Facade facade = new Facade();
      int idInsc = 0;
		try {
				idInsc = facade.registrarInscripcion(dto);//registrar inscripcion
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
       // el resultado lo adjunto como atributo en el request
      request.setAttribute("idInsc", idInsc);
      
       // redirecciono el control hacia la siguiente vista,
       // es decir: hacia su servlet de pre-confirmacion
      request.getRequestDispatcher("/preConfirmacion").forward(request, response);
   }
}
