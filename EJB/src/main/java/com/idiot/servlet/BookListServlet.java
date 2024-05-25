package com.idiot.servlet;

import com.idiot.ejb.BookManager;
import com.idiot.model.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {

    @EJB
    private BookManager bookManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Ensure that bookManager is injected correctly
        if (bookManager == null) {
            throw new ServletException("BookManager instance not injected");
        }

        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        List<Book> books = bookManager.getAllBooks();

        pw.println("<html><head><title>Book List</title></head><body>");
        pw.println("<h2>Book List</h2>");
        pw.println("<table border='1' align='center'>");
        pw.println("<tr>");
        pw.println("<th>Book Id</th>");
        pw.println("<th>Book Name</th>");
        pw.println("<th>Book Edition</th>");
        pw.println("<th>Book Price</th>");
        pw.println("<th>Edit</th>");
        pw.println("<th>Delete</th>");
        pw.println("</tr>");

        for (Book book : books) {
            pw.println("<tr>");
            pw.println("<td>" + book.getId() + "</td>");
            pw.println("<td>" + book.getBookName() + "</td>");
            pw.println("<td>" + book.getBookEdition() + "</td>");
            pw.println("<td>" + book.getBookPrice() + "</td>");
            pw.println("<td><a href='editScreen?id=" + book.getId() + "'>Edit</a></td>");
            pw.println("<td><a href='deleteurl?id=" + book.getId() + "'>Delete</a></td>");
            pw.println("</tr>");
        }

        pw.println("</table>");
        pw.println("<a href='home.html'>Home</a>");
        pw.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}