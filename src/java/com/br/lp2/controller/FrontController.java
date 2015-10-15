/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.controller;

import com.br.lp2.model.dao.IngressoDAO;
import com.br.lp2.model.entities.Ingresso;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1147106
 */
//@WebServlet( urlPatterns = "/FrontController")
public class FrontController extends HttpServlet {
    private int cadeira;
    private String command;

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
            out.println("<title>Servlet FrontController</title>");            
            out.println("</head>");
            out.println("<body>");
            
            List<Integer> cadeiras = new ArrayList<>();
            if(request.getSession().getAttribute("cadeiras") == null)
            {
                for (int i = 0; i < 60; i++) {
                    cadeiras.add(1);
                }
                request.getSession().setAttribute("cadeiras", cadeiras);
            } else {
                cadeiras = (List<Integer>)request.getSession().getAttribute("cadeiras");
            }
            
            IngressoDAO dao = new IngressoDAO();
            if(command.equals("init")){
                System.out.println("INIT");
                
                List<Ingresso> lista = dao.read();
                for (Ingresso ingresso : lista) {
                    int pos = ingresso.getCadeira();
                    cadeiras.set(pos, 3);
                }
                
            }else if(command.equals("selecionar")){
                cadeira = Integer.parseInt(request.getParameter("cadeira"));
                System.out.println("SELECIONAR "+cadeira);
                if(cadeiras.get(cadeira)!=3) cadeiras.set(cadeira, (cadeiras.get(cadeira)==1)?2:1 );
            } else if(command.equals("comprar")){
                System.out.println("COMPRAR");
                int tipo = Integer.parseInt(request.getParameter("tipo"));
                for (int i = 0; i < cadeiras.size(); i++) {
                    if(cadeiras.get(i)==2) {
                        cadeiras.set(i,3);
                        dao.insert(new Ingresso(i,tipo));
                    }
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            
            
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
        
        command = request.getParameter("command");
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
