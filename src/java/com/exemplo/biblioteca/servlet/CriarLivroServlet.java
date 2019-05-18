package com.exemplo.biblioteca.servlet;

import com.exemplo.biblioteca.dao.LivrosDao;
import com.exemplo.biblioteca.entidades.Livro;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CriarLivroServlet", urlPatterns = {"/CriarLivroServlet"})
public class CriarLivroServlet extends HttpServlet{
    @Inject
    private LivrosDao dao;
   
    protected void processRequest(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    
        try(PrintWriter out = response.getWriter();){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<body>");
        String titulo = req.getParameter("titulo");
        String autor = req.getParameter("autor");
        String numPaginas = req.getParameter("paginas");
        try{
        Livro novoLivro = new Livro();
        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setNumPaginas(Integer.valueOf(numPaginas));
        dao.adicionaLivro(novoLivro);
        out.println("<h1>Livro adicionado com sucesso</h1>");
        }catch(Exception ex){
        out.println("<h1>Erro ao adicionar um novo livro</h1>");
        }
        
        out.println("<a href='index.html'>Biblioteca</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}


