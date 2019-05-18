package com.exemplo.biblioteca.servlet;

import com.exemplo.biblioteca.dao.LivrosDao;
import com.exemplo.biblioteca.entidades.Livro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "PesquisaLivroServlet", urlPatterns = {"/PesquisaLivroServlet"})
public class PesquisaLivroServlet extends HttpServlet {
    
    @Inject
    private LivrosDao dao;
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         
         out.println("<!DOCTYPE html>");   
         out.println("<html>");
         out.println("<body>");
         out.println("<ul>");
         
         String titulo = req.getParameter("titulo");
         List<Livro> livros;
         
         if(titulo==null||titulo.isEmpty()){
         livros = dao.buscaTodosLivros();
         }else{
         livros = dao.buscaLivroPorTitulo(titulo);
         }
         for(Livro livro : livros){
         out.println("<li>"+livro.getTitulo()+"</li>");
         }
         out.println("<a href='index.html'>Biblioteca</a>");
         out.println("</ul>");
         out.println("</body>");
         out.println("</html>");
        }
    }
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(req, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(req, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}