/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bacalá
 */
@WebServlet(urlPatterns = {"/msg"})
public class MsgServlet extends HttpServlet {

     

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        ArrayList<Mensagem> lista = new ArrayList<>();
        getServletContext().setAttribute("lista", lista);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Usuario usr = (Usuario) request.getSession().getAttribute("usr");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<Mensagem> lista = (ArrayList<Mensagem>) getServletContext().getAttribute("lista");
            String texto = request.getParameter("texto");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mini Whats Web</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de Mensagens</h1>");
            out.println("<h1> Mensagens</h1>");
            if (texto != null) {
                String rem = request.getParameter("remetente");
                Mensagem m = new Mensagem(texto, rem);
                lista.add(m);
            }
            for (Mensagem mx : lista) {
                out.println("<p> <b>" + mx.getRemetente() + "</b> - " + mx.getTexto());
            }
            out.println("<form action=\"msg\">\n"
                    + "            <table border=\"0\">\n"
                    + "                <thead>\n"
                    + "                    <tr>\n"
                    + "                        <th></th>\n"
                    + "                        <th></th>\n"
                    + "                    </tr>\n"
                    + "                </thead>\n"
                    + "                <tbody>\n"
                    + "                    <tr>\n"
                    + "                        <td> </td>\n"
                    + "                        <td><input type=\"hidden\" name=\"remetente\" value=\"" + usr.getNome() + "\" size=\"60\" /></td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td>Texto da Mensagem</td>\n"
                    + "                        <td><textarea name=\"texto\" rows=\"4\" cols=\"60\">\n</textarea></td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td><input type=\"submit\" value=\"OK\" /></td>\n"
                    + "                        <td><input type=\"reset\" value=\"Limpar\" /></td>\n"
                    + "                    </tr>\n"
                    + "                </tbody>\n"
                    + "            </table></form>");
            out.println("<form action=\"logout\">\n"
                    + "<input type=\"submit\" value=\"LOGOUT\" /></form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
