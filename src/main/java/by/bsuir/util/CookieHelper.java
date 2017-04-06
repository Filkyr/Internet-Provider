package by.bsuir.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides a work with cookie
 */
public class CookieHelper {
    /**
     * Variable, which shows that age of cookie won't be specified
     */
    public static final int UNDEFINED_AGE = -1;

    /**
     * Checks cookie in the request and removes it, setting an attribute in request
     * @param request user request, which expected contains needed cookie
     * @param response user response, in which the age of cookie will be set in zero
     * @param key the name of cookie
     */
    public static void checkCookie(HttpServletRequest request, HttpServletResponse response, String key){
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals(key)){
                cookie.setValue(null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                request.setAttribute(key, true);
            }
        }
    }

    /**
     * Finds a cookie with given name and setting the value of found cookie
     * an attribute in request
     * @param request user's request, where cookie will be searched
     * @param key cookie name, which value searching
     */
    public static void findInCookie(HttpServletRequest request, String key){
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals(key)){
                request.setAttribute(key, cookie.getValue());
            }
        }
    }

    /**
     * Adds cookie in a response
     * @param name of cookie
     * @param value of cookie
     * @param age how much (in seconds) cookie will live (if the age of cookie
     *        doesn't matter for you, use {@link CookieHelper#UNDEFINED_AGE} variable)
     * @param response which will be send to user
     */
    public static void addCookie(String name, String value, int age, HttpServletResponse response){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(age);
        response.addCookie(cookie);
    }
}
