import java.sql.Connection;
import java.sql.Statement;

public class InterFace extends Database {
    private String username;	//��¼�û���
    private String passwd;		//��¼����
    private boolean isadmini;	//�Ƿ����Ա��¼
    private long userid=0;		//�û�ID��
    public InterFace() throws Exception{
        super();
        username = "";
        passwd = "";
        isadmini = false;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String newusername) {
        username = newusername;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String newpasswd) {
        passwd = newpasswd;
    }
    public boolean getIsadmini() {
        return isadmini;
    }
    public void setIsadmini(boolean newIsadmini) {
        isadmini = newIsadmini;
    }
    public long getUserid() {
        return userid;
    }
    public void setUserid (long uid) {
        userid = uid;
    }

    public String getSql() {
        if (isadmini) {
            sqlStr = "select * from admini where AdminUser = '" +
                    username + "' and adminpass = '" +
                    passwd + "'";
        }else {
            sqlStr = "select * from salesman where UserName = '" +
                    username + "' and password = '" + passwd + "'";
        }
        return sqlStr;
    }

    public boolean excute() throws Exception
    {

        boolean flag = false;
        Database db = new Database();
        Connection conn=db.connect();
        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery(getSql());
        if (rs.next()){
            if (!isadmini)
            {
                userid = rs.getLong("id");
            }
            flag = true;
        }
        rs.close();
        return flag;
    }

}

