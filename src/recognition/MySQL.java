package recognition;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQL {
	String driver;// JDBCドライバの登録
    String server, dbname, url, user, password;// データベースの指定
    Connection con;
    Statement stmt;
    Map<String, Object> lng = new HashMap<>();
    
	public MySQL() {
		this.driver = "org.gjt.mm.mysql.Driver";
        this.server = "ms000.sist.ac.jp";
        this.dbname = "ms000";
        this.url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
        this.user = "ms000";
        this.password = "naisyo";
        try {
            this.con = DriverManager.getConnection(url, user, password);
            this.stmt = con.createStatement ();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Class.forName (driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
		
	//値の無い物全て
	public ResultSet getID() {
		ResultSet rs = null;
		String sql = "SELECT * FROM  `images` WHERE  `age_min` = -1";
		try {
			rs = stmt.executeQuery (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void updateImage(int age_min, int age_max, double age_score, String gender, double gender_score, int image_id) {
		//keywordテーブルへ格納
		StringBuffer buf = new StringBuffer();
		int gender_id = -1;
		if(gender.equals("MALE")) gender_id = 0;
		else if(gender.equals("FEMALE")) gender_id = 1;
		buf.append("UPDATE images SET  `age_min` = " + age_min + ", `age_max` = " + age_max + ", `age_score` = " + age_score + ", `gender` = " + gender_id + ", `gender_score` = " + gender_score + "   WHERE  id = " + image_id + ";");
		String sql = buf.toString();
		try {
			stmt.execute (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
