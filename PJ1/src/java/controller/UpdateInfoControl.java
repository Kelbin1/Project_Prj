/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateInfoControl", urlPatterns = {"/update-info"})
public class UpdateInfoControl extends HttpServlet {
    private static final String PHONE_VALID = "^\\d{1,15}$";
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateInfoControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateInfoControl at " + request.getContextPath() + "</h1>");
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
               UserDAO userDAO = new UserDAO();
//        String id_user = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        java.sql.Date dob = null;
//        try {
//            dob = new java.sql.Date((new SimpleDateFormat("mm/dd/yyyy").parse(request.getParameter("dob"))).getTime());
//        } catch (ParseException e) {
//
//        }
        
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        HttpSession se = request.getSession(true);
        User acc = (User) se.getAttribute("acc");
        if(!username.isEmpty()) acc.setUsername(username);
        if(!password.isEmpty()) acc.setPassword(password);      
        if(!gender.isEmpty()) acc.setGender(gender);
//                           acc.setDob(dob);
        if(checkInputPhone(phone)) acc.setPhone(phone);
         if(!email.isEmpty()) acc.setEmail(email);
        if(!address.isEmpty()) acc.setAddress(address);
        String err = "";
        if (password.equals("")) {
            err += "Phải nhập đầy đủ thông tin!!";
        }
        if (err.length() > 0) {
            request.setAttribute("err", err);
        }
        try {
            if (err.length() == 0) {
//                User a = new User(Integer.parseInt(id_user), username, password, dob, gender, email, phone, address, "1");
                 userDAO.updateAcc(acc);
              response.sendRedirect("myaccount");
            }
           
             
        } catch (Exception e) {
               System.out.println(e);
        }
    }
    private boolean checkInputPhone(String txt) {
        if (txt.matches(PHONE_VALID)) {
            return true;
        } else {
            return false;
        }
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
