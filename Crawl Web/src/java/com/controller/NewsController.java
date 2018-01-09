/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.NewsDao;
import com.model.Tintuc;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Son
 */
public class NewsController extends HttpServlet {

//    private static final int DEFAULT_BUFFER_SIZE = 10240;
    private String INSERT = "/addNews.jsp";
    private String LIST_NEWS = "/listNews.jsp";
    private String EDIT = "/editNews.jsp";
    private final NewsDao dao = new NewsDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("listNews")) {
            try {
                a = LIST_NEWS;
                List<Tintuc> news = dao.getAllTintuc();
                request.setAttribute("news", news);
            } catch (Exception ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("editNews")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Tintuc news = dao.getTinByID(id);
                a = EDIT;
                request.setAttribute("news", news);
            } catch (Exception ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("delete")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                a = LIST_NEWS;
                List<Tintuc> news = dao.getAllTintuc();
                request.setAttribute("news", news);
            } catch (Exception ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            a = INSERT;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(a);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
