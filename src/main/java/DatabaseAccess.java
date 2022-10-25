import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/DatabaseAccess")
public class DatabaseAccess extends HttpServlet{

	 // JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/coursework3?&useSSL=false&serverTimezone=UTC";
    
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static final String USER = "root";
    static final String PASS = "95279426txy%";
    String sqlStr = "select ID,pName,Department from person";
    String sqlInsert = "insert into person " +"VALUES (79, 'Mike', 'R&D')";
    String sqlUpdate = "update person " +"set Department = 'QA' where ID in (1,3)";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseAccess() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        // ������Ӧ��������
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Servlet MySQL Connection";
        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
        "<html>\n" +
        "<head><title>" + title + "</title></head>\n" +
        "<body bgcolor=\"#f0f0f0\">\n" +
        "<h1 align=\"center\">" + title + "</h1>\n");
        try{
        	Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println( "打开数据库!" );

            Connection con = DriverManager.getConnection(DB_URL, USER,PASS );

            System.out.println( "打开表" );

            Statement st = con.createStatement();
            System.out.println( "����Statement�ɹ�!" );
            System.out.println( "��ʼ��ѯ����" );
          //�������
//            st.executeUpdate(sqlInsert);
//            System.out.println("插入");
            
            //��������
            st.executeUpdate(sqlUpdate);
            System.out.println("更新");

            //��ѯ����
            ResultSet rs = st.executeQuery( sqlStr );
            System.out.println( "查询!" );
            System.out.println( "----------------!" );

           
            while(rs.next()){
                // ͨ���ֶμ���
                int id  = rs.getInt("ID");
                String pname = rs.getString("pName");
                String dept = rs.getString("Department");
    
                // �������
                out.println("ID: " + id);
                out.println(", name: " + pname);
                out.println(", department: " +dept );
                out.println("<br />");
            }
            out.println("</body></html>");

            // ��ɺ�ر�
         
        } catch(SQLException se) {
            // ���� JDBC ����
            se.printStackTrace();
        } catch(Exception e) {
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // ��������ڹر���Դ�Ŀ�
            try{
                if(stmt!=null)
                stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
       
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}