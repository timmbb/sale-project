import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InterFace extends HttpServlet{
    /**
     * @param request servlet request
     * @param response servlet response
     * @throws Exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        String mesg = "";
        PrintWriter out = response.getWriter();
        if(
                request.getParameter("username")!=null && !request.getParameter("username").equals("")){
            String username =request.getParameter("username");
            String passwd = request.getParameter("passwd");
            HttpSession session=request.getSession(true);
            username = new String(username.getBytes("ISO8859-1"));
            passwd = new String(passwd.getBytes("ISO8859-1"));
            loginManage login = new loginManage ();
            login.setUsername(username);
            login.setPasswd(passwd);
            System.out.println(username+passwd);
            if (login.excute()){
                session.setAttribute("username",username);
                String userid = Long.toString(login.getUserid());
                session.setAttribute("userid",userid);
                response.sendRedirect("bookshop/booklist.jsp");
            }else {
                mesg = "�˺Ż��������"	;
            }
        }
        if (!mesg.equals("")){
            out.println("<p ><font color=#ff0000>" + mesg +"</font></p>");}
    }

    /**
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     */
    public String getServletInfo(){
        return "Short description";
    }

}