package com.nerv.sai.seguridad;

import java.io.IOException;

import javax.faces.FacesException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet Filter implementation class LoginFilter
 * @author Santiago
 */

public class Filtro implements Filter {

    /**
     * Default constructor. 
     */
    public Filtro() {

    }

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {

    }

    /**
     * @param fConfig
     * @throws javax.servlet.ServletException
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    /**
     * @param request
     * @param response
     * @param chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;

            Login loginBean = (Login) req.getSession().getAttribute("MbLogin");

              //Proceso la URL que está requiriendo el cliente
              String urlStr = "";

              try{
                urlStr = req.getRequestURL().toString();//.toLowerCase();
                boolean noProteger = noProteger(urlStr);
                System.out.println(urlStr + " - Acceso desprotegido? [" + noProteger + "]");

                //Si no requiere protección continúo normalmente.
                if (noProteger(urlStr)) {
                    System.out.println("Si no requiere protección continúo normalmente.");
                    chain.doFilter(request, response);
                  return;
                }
              }catch(FacesException ex){
                System.out.println("Error Servidor -> "+ex);
                res.sendRedirect(req.getContextPath() + "/pagina-error/500.xhtml");
                return; 
              }

              //El usuario no está logueado

              if(loginBean != null){
                if (loginBean.isLoggedIn() == false) {
                    System.out.println("El usuario no está logueado Caso-A");
                    res.sendRedirect(req.getContextPath() + "/login.xhtml");
                  return;
                }
              }else{
                System.out.println("El usuario no está logueado Caso-B");
                res.sendRedirect(req.getContextPath() + "/login.xhtml");
                return;  
              }
              //El recurso requiere protección, pero el usuario ya está logueado.

            chain.doFilter(request, response);
    }

    /**
     * Método que permite identificar que recurso no esta protegido y permite el acceso.
     * @param urlStr
     * @return [true-false]
     */
    private boolean noProteger(String urlStr) {

            /*
             * Este es un buen lugar para colocar y programar todos los patrones que
             * creamos convenientes para determinar cuales de los recursos no
             * requieren protección. Sin duda que habrá que crear un mecanizmo tal
             * que se obtengan de un archivo de configuración o algo que no requiera
             * compilación.
             */
              if (urlStr.indexOf("/javax.faces.resource/") != -1)
                    return true;
              if (urlStr.indexOf("org.primefaces.extensions.showcase.util.CustomExporterFactory") != -1)
                        return true;
              if (urlStr.indexOf("/resources/") != -1)
                        return true;
              if (urlStr.indexOf("/SistemaProcesoImportacion-war/login.xhtml") != -1)
                        return true;              
              if (urlStr.indexOf("/pagina-error/") != -1)
                        return true;
              /*if (urlStr.indexOf("/ImplementacionFiltro/login.xhtml") != -1)
                        return true;
              if (urlStr.indexOf("/ImplementacionFiltro/plantilla.xhtml") != -1)
                        return true;
              if (urlStr.indexOf("/ImplementacionFiltro/menu.xhtml") != -1)
                        return true;
              if (urlStr.indexOf("/ImplementacionFiltro/sessionAlertTimeOut.xhtml") != -1)
                        return true;*/
              return false;
    }
}
