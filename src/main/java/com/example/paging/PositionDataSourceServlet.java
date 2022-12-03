package com.example.paging;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



@WebServlet(name = "PositionDataSourceServlet", value = "/PositionDataSourceServlet")
public class PositionDataSourceServlet extends HttpServlet {
    private static final String TAG ="PositionDataSourceServlet-";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http://localhost:8080/pagingserver/pds.do?start=0&count=8
        System.out.println(TAG + "doGet");
        int start = Integer.parseInt(request.getParameter("start"));
        int count = Integer.parseInt(request.getParameter("count"));
        System.out.println(TAG + "start:" + start + ",count=" + count);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("count", count);
        jsonObject.addProperty("start", start);
        jsonObject.addProperty("total", ServerStartupListener.getMovieSize());

        Gson gson = new Gson();
        List<Movie> searchList = ServerStartupListener.getSearchList(start, start + count);

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
