package com.example.paging;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "CoroutineDataSourceServlet", value = "/CoroutineDataSourceServlet")
public class CoroutineDataSourceServlet extends HttpServlet {
    private static final String TAG ="CoroutineDataSourceServlet-";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http://localhost:8080/pagingserver/cds.do?since=0&pagesize=8
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        System.out.println(TAG + "doGet");
        int since = Integer.parseInt(request.getParameter("since"));
        int pagesize = Integer.parseInt(request.getParameter("pagesize"));
        System.out.println(TAG + "since:" + since + ",pagesize=" + pagesize);

        List<Movie> searchList = ServerStartupListener.getSearchList(since, since + pagesize);
        Gson gson = new Gson();
        JsonArray jsonArray = (JsonArray) gson.toJsonTree(searchList);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
