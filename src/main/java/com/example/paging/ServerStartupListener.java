package com.example.paging;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ServerStartupListener implements ServletContextListener{
    private static final String TAG ="ServerStartupListener-";
    private static final List<Movie> MOVIES = new ArrayList<>();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //构建一个内存数据集合
        URL url = getClass().getClassLoader().getResource("movies.json");
        File file = new File(url.getFile());
        String json = Utils.loadJson(file.getAbsolutePath());
        System.out.println(TAG + json);
        JsonObject rootElement = (JsonObject) new JsonParser().parse(json);
        JsonArray subjects = rootElement.get("subjects").getAsJsonArray();
        for (int i = 0; i < subjects.size(); i++) {
            JsonObject element = (JsonObject) subjects.get(i);
            Movie movie = new Movie();
            movie.setNo(element.get("no").getAsInt());
            movie.setId(element.get("id").getAsInt());
            movie.setTitle(element.get("title").getAsString());
            movie.setRate(element.get("rate").getAsString());
            movie.setCover(element.get("cover").getAsString());
            MOVIES.add(movie);
        }
        System.out.println(TAG + MOVIES.size());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public static int getMovieIndexById(int id) {
        int size = MOVIES.size();
        int index = (id == 0 ? 0 : -1);
        for (int i = 0; i < size; i++) {
            if (MOVIES.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        System.out.println(TAG + "getMovieIndexById:" + index);
        return index;
    }

    public static List<Movie> getSearchList(int start, int end) {
        List<Movie> searchList = new ArrayList<>();
        int size = MOVIES.size();
        for (int i = start; i < end && i>= 0 && i < size; i++) {
            searchList.add(MOVIES.get(i));
        }
        System.out.println(TAG + "getSearchList:" + searchList.size());
        return searchList;
    }

    public static int getMovieSize() {
        return MOVIES.size();
    }
}
