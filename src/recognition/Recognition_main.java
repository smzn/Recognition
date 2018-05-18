package recognition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

public class Recognition_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Recognition_lib rlib = new Recognition_lib();
		MySQL mysql = new MySQL();
		ResultSet rs = mysql.getID();
		try {
			while(rs.next()){
				int id = rs.getInt("id");
				//String path = "/home/ms000/www/oc/app/webroot/img/image/" + rs.getString("picture");
				String path = "img/" + rs.getString("picture");
				rlib.getFaceRecognition(path);
				MySQL mysql1 = new MySQL();
				mysql1.updateImage(rlib.getAge_min(), rlib.getAge_max(), rlib.getAge_score(), rlib.getGender(), rlib.getGender_score(), id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
