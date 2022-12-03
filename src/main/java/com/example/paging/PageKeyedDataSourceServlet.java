package com.example.paging;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "PageKeyedDataSourceServlet", value = "/PageKeyedDataSourceServlet")
public class PageKeyedDataSourceServlet extends HttpServlet {
    private static final String TAG ="PageKeyedDataSourceServlet-";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http://localhost:8080/pagingserver/pkds.do?page=1&pagesize=8
        System.out.println(TAG + "doGet");
        int page = Integer.parseInt(request.getParameter("page"));
        int pagesize = Integer.parseInt(request.getParameter("pagesize"));
        System.out.println(TAG + "page:" + page + ",pagesize=" + pagesize);

        int end = page * pagesize;
        int start = end - pagesize;
        List<Movie> searchList = ServerStartupListener.getSearchList(start, end);
        boolean hasMore = end < ServerStartupListener.getMovieSize();
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("has_more", hasMore);
        JsonArray jsonArray = (JsonArray) gson.toJsonTree(searchList);
        jsonObject.add("subjects", jsonArray);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
