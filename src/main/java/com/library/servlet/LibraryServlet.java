package com.library.servlet;

import com.library.dao.BookDAO;
import com.library.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/library")
public class LibraryServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        bookDAO = new BookDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if ("search".equals(action)) {
                String query = request.getParameter("query");
                List<Book> books = bookDAO.searchBooks(query);
                request.setAttribute("books", books);
                request.getRequestDispatcher("/WEB-INF/views/bookList.jsp").forward(request, response);
            } else if ("overdue".equals(action)) {
                List<Book> overdueBooks = bookDAO.getOverdueBooks();
                request.setAttribute("books", overdueBooks);
                request.getRequestDispatcher("/WEB-INF/views/bookList.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
