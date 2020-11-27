/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author steve
 */
public class parser {
    public static String[] InternParseUrl(HttpServletRequest request){
        
        String url = request.getRequestURI();
        if(url != null){
            String[] parsingUrl = url.split("/");
            return parsingUrl;
        }
        return null;
    }
    
    public static int getIdFromUrl (HttpServletRequest request, HttpServletResponse response) throws IOException{
        int id = -1;
        String idString = request.getParameter("id");
        try{
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException ex)
        {
            response.sendRedirect(request.getContextPath()+ "/interns");
        }
        return id;
    }
}
