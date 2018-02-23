package moudle.service;

import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import moudle.TestScp;
import moudle.bean.BulletinBean;
import moudle.dao.BulletinDAO;

public class BulletinService {

	public int insertBulletin(String sbeginAt, String sendAt, String srank, String picturePath) throws SQLException {
		BulletinDAO dao = new BulletinDAO();
		java.sql.Timestamp beginAt = null;
		java.sql.Timestamp endAt = null;
		int rank = 0;
		try {
			// 去除全部空白
			sbeginAt = sbeginAt.replaceAll("\\s+", "");
			sendAt = sendAt.replaceAll("\\s+", "");
			// 將日期的/換成-
			sbeginAt = sbeginAt.replaceAll("/", "-");
			sendAt = sendAt.replaceAll("/", "-");
			beginAt = Timestamp.valueOf(sbeginAt + " 06:00:00");
			endAt = Timestamp.valueOf(sendAt + " 06:00:00");
			rank = Integer.parseInt(srank);
		} catch (NumberFormatException e) {
			// 轉換order時出錯
			return 0;
		} catch (IllegalArgumentException e) {
			// 轉換日期時出錯
			return 0;
		}

		if (endAt.compareTo(beginAt) <= 0) {
			// 截止日期比開始日期早
			return 3;
		}

		int result = dao.insertBulletin(beginAt, endAt, rank, picturePath);
		if (result != 1) {
			// sql有錯
			return 2;
		}
		return 1;
	}

	public ArrayList<BulletinBean> selectAllBulletins() throws SQLException {
		BulletinDAO dao = new BulletinDAO();
		ArrayList<BulletinBean> ab = null;
		ab = dao.selectAllBulletins();

		return ab;
	}

	public int updateBulletin(String sid, String sbeginAt, String sendAt, String srank, String picturePath)
			throws SQLException {
		BulletinDAO dao = new BulletinDAO();
		java.sql.Timestamp beginAt = null;
		java.sql.Timestamp endAt = null;
		int rank = 0;
		int id = 0;
		try {
			// 去除全部空白
			sbeginAt = sbeginAt.replaceAll("\\s+", "");
			sendAt = sendAt.replaceAll("\\s+", "");
			// 將日期的/換成-
			sbeginAt = sbeginAt.replaceAll("/", "-");
			sendAt = sendAt.replaceAll("/", "-");
			beginAt = Timestamp.valueOf(sbeginAt + " 06:00:00");
			endAt = Timestamp.valueOf(sendAt + " 06:00:00");
			rank = Integer.parseInt(srank);
			id = Integer.parseInt(sid);
		} catch (NumberFormatException e) {
			// 轉換rank,id時出錯
			return 0;
		} catch (IllegalArgumentException e) {
			// 轉換日期時出錯
			return 0;
		}

		if (endAt.compareTo(beginAt) <= 0) {
			// 截止日期比開始日期早
			return 3;
		}

		int result = dao.updateBulletin(id, beginAt, endAt, rank, picturePath);
		if (result != 1) {
			// sql有錯
			return 2;
		}
		return 1;
	}

	public int deleteBulletin(String sid) throws SQLException {
		BulletinDAO dao = new BulletinDAO();
		int result = 0;
		int id = 0;
		try {
			String[] ids = sid.split("e");
			id = Integer.parseInt(ids[ids.length - 1]);
			System.out.println("id=" + id);
		} catch (NumberFormatException e) {
			// 轉換rank,id時出錯
			return 0;
		}

		result = dao.deleteBulletin(id);
		return result;
	}

	public String selectBulletinPicturePath(String sid) throws SQLException {
		BulletinDAO dao = new BulletinDAO();
		String result = "";
		int id = 0;
		try {
			String[] ids = sid.split("e");
			id = Integer.parseInt(ids[ids.length - 1]);
			System.out.println(id);
			result = dao.selectBulletinPicturePath(id);
			String[] picturePath = result.split("/");
			System.out.println(picturePath);
			System.out.println(picturePath[picturePath.length - 1]);
			result = picturePath[picturePath.length - 1];
		} catch (NumberFormatException e) {
			// 轉換rank,id時出錯
			return null;
		}

		return result;
	}

	public int deleteBulletinByExpired() throws SQLException {
		BulletinDAO dao = new BulletinDAO();
		ArrayList<String> list = new ArrayList<String>();

		int result = 0;
		Timestamp ExpiredTime = new Timestamp(System.currentTimeMillis() - (24 * 60 * 60 * 1000));
		System.out.println("截止時間=" + ExpiredTime);
		list = dao.selectBulletinPicturePathByExpired(ExpiredTime);
		result = dao.deleteBulletinByExpired(ExpiredTime);
       //database刪除成功後,把遠端的資料刪掉
		if (result > 0) {
			
			if (list.size() > 0) {
				for (String s : list) {
					String[] picturePath = s.split("/");
					String fileName = picturePath[picturePath.length - 1];
					TestScp.rmFile("/home/johnny/img/" + fileName);
					File file = new File("/home/johnny/tomcat/webapps/img/" + fileName);
					//System.out.println(fileName);
					file.delete();
				}
			}
		}

		return result;
	}

}
