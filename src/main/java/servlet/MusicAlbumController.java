package servlet;

import model.MusicAlbum;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AlbumServlet")
public class MusicAlbumController extends HttpServlet {

    private final String ALBUM_SESSION_NAME = "albums";
    private final String ALBUM_PAGE_PATH = "/albums.jsp";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if(req.getSession().getAttribute(ALBUM_SESSION_NAME) == null){
            req.getSession().setAttribute(ALBUM_SESSION_NAME,new ArrayList<MusicAlbum>());
        }

        getServletContext().getRequestDispatcher(ALBUM_PAGE_PATH).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String artist = req.getParameter("artist");
        String genre = req.getParameter("genre");
        int year = 0;
        List<MusicAlbum> albums;
        List <String> errorLog = new ArrayList<>();


        try {
            year = Integer.valueOf(req.getParameter("year"));
        } catch (Exception e){
            errorLog.add("Given Year is not a valid number");
        }




        errorLog = albumDetailsValidator(errorLog,title,artist,genre,year);

        if( errorLog == null || errorLog.isEmpty()){
            MusicAlbum newMusicAlbum = new MusicAlbum(title,artist,genre,year);

            albums = (List<MusicAlbum>) req.getSession().getAttribute(ALBUM_SESSION_NAME);

            albums.add(newMusicAlbum);

            req.getSession().setAttribute("errorLog", null);

            req.getSession().setAttribute(ALBUM_SESSION_NAME,albums);
        } else {
            req.getSession().setAttribute("errorLog", errorLog);
        }

        getServletContext().getRequestDispatcher(ALBUM_PAGE_PATH).forward(req, resp);

    }

    public List<String> albumDetailsValidator (List<String> errorList, String title, String artist, String genre, int year){


        if(title.isEmpty() || title == null){
            errorList.add("Title of Album is empty!");
        }
        if(artist.isEmpty() || artist == null){
            errorList.add("Artist of Album is empty!");
        }
        if(genre.isEmpty() || genre == null){
            errorList.add("Genre of Album is empty!");
        }
        if(year < 1200){
            errorList.add("Year of Album is less than 1200!");
        }

        return errorList;


    }


}
