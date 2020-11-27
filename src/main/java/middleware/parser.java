/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author steve
 */
public class parser {
    public static String[] InternParseUrl(HttpServletRequest request){
        String url = request.getPathInfo();
        if(url != null){
            String[] parsingUrl = url.split("/");
            return parsingUrl;
        }
        return null;
    }
}
