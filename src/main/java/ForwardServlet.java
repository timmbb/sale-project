import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ForwardServlet")
public class ForwardServlet extends HttpServlet {

	private static final long serialVersionUID = -291840563095094360L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String destination = request.getParameter("destination");
		
		// 跳转到 /WEB-INF/web.xml。通过地址栏输入网址是不能访问到该文件的，但是 forward 可以
		if("file".equals(destination)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/web.xml");
			dispatcher.forward(request, response);
		}
		// 跳转到 /hello.html
		else if("html".equals(destination)){

			RequestDispatcher dispatcher = request.getRequestDispatcher("/hello.html");
			dispatcher.forward(request, response);
		}
		// 跳转到 Servlet
		else if("servlet".equals(destination)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/LifeCycleServlet");
			dispatcher.forward(request, response);
		}
		else{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println("缺少参数。用法：" + request.getRequestURL() + "?destination=jsp 或者 file 或者 servlet ");
		}
		
	}
	public void doPut(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		this.doGet(request, response);
	}

}
