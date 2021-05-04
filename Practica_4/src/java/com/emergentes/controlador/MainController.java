package com.emergentes.controlador;

import com.emergentes.ConexionBD;
import com.emergentes.modelo.Tarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String op;
            op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
            ArrayList<Tarea> lista = new ArrayList<Tarea>();
            ConexionBD canal = new ConexionBD();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;

            if (op.equals("list")) {

                //Listar los datos
                String sql = "select * from tareas";

                //Consulta de seleccion y almacenar en una coleccion
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Tarea tarea = new Tarea();

                    tarea.setId(rs.getInt("id"));
                    tarea.setTarea(rs.getString("tarea"));
                    tarea.setPrioridad(rs.getInt("prioridad"));
                    tarea.setCompletado(rs.getInt("completado"));

                    lista.add(tarea);

                }
                request.setAttribute("lista", lista);

                //Enviar al index.jsp para mostrar la informacion
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            if (op.equals("nuevo")) {
                //Instanciar un objeto de la clase Tarea
                Tarea ta = new Tarea();

                //El objeto se pone como atributo de request
                request.setAttribute("tarea", ta);

                //Redireccionar a esitar.jsp
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }

            if (op.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));

                String sql = "select * from tareas where id=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                Tarea taux = new Tarea();

                while (rs.next()) {
                    Tarea ta = new Tarea();
                    ta.setId(rs.getInt("id"));
                    ta.setTarea(rs.getString("tarea"));
                    ta.setPrioridad(rs.getInt("prioridad"));
                    ta.setCompletado(rs.getInt("completado"));

                    taux = ta;
                }
                request.setAttribute("tarea", taux);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }

            if (op.equals("eliminar")) {
                //Obtener el id
                int id = Integer.parseInt(request.getParameter("id"));

                //Realizar la eliminacion de la base de datos
                String sql = "delete from tareas where id=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println("Error al conectar " + ex.getMessage());
        }
        //Redireccionar a MainController
        response.sendRedirect("MainController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tarea = request.getParameter("tarea");
        int prioridad = Integer.parseInt(request.getParameter("prioridad"));
        int completado = Integer.parseInt(request.getParameter("completado"));

        //Crear objeto Tarea
        Tarea t = new Tarea();
        t.setId(id);
        t.setTarea(tarea);
        t.setPrioridad(prioridad);
        t.setCompletado(completado);

        ConexionBD canal = new ConexionBD();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;

        try {
            if (id == 0) {
                //Nuevo ingreso

                String sql = "insert into tareas(tarea,prioridad,completado) values (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, t.getTarea());
                ps.setInt(2, t.getPrioridad());
                ps.setInt(3, t.getCompletado());

                ps.executeUpdate();

            } else {
                String sql = "update tareas set tarea=?, prioridad=?, completado=? where id=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, t.getTarea());
                ps.setInt(2, t.getPrioridad());
                ps.setInt(3, t.getCompletado());
                ps.setInt(4, id);

                ps.executeUpdate();

            }
            response.sendRedirect("MainController");
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
