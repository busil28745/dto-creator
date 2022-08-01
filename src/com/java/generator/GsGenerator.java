package com.java.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GsGenerator {

	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public List<GsDto> connectDB(String dbType, String dbHost, String dbPort, String dbUser, String dbPswd, String schema, String dbNm,
			String tableNm) {
		String dbDriverClass = null;
		String dbUrl = null;

		if ("mariadb".equals(dbType)) {
			dbDriverClass = "org." + dbType + ".jdbc.Driver";
			dbUrl = "jdbc:" + dbType + "://" + dbHost + ":" + dbPort + "/" + schema;
		} else if ("mysql".equals(dbType)) {
			dbDriverClass = "com." + dbType + ".cj.jdbc.Driver";
			dbUrl = "jdbc:" + dbType + "://" + dbHost + ":" + dbPort + "/" + schema;
		} else if ("postgresql".equals(dbType)) {
			dbDriverClass = "org.postgresql.Driver";
			dbUrl = "jdbc:" + dbType + "://" + dbHost + ":" + dbPort + "/" + dbNm;
		}
		System.out.println(dbType);
//		dbUser = "uvms_gw_dev";
//		dbPswd = "p@ssw0rd!Q@W";
//		dbDriverClass = "org.mariadb.jdbc.Driver";
//		dbUrl = "jdbc:mariadb://192.168.0.100:3306/uvms2_dev";

		List<GsDto> gsDtoList = new ArrayList<>();

		try {
			Class.forName(dbDriverClass);
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPswd);
			System.out.println("연결성공");

			StringBuffer sql = new StringBuffer();
			if (!dbType.equals("postgresql")) {
				sql.append("SELECT c.ORDINAL_POSITION, c.COLUMN_NAME, c.COLUMN_COMMENT, c.COLUMN_TYPE"
						+ ", c.CHARACTER_MAXIMUM_LENGTH AS DATA_LENGTH, c.IS_NULLABLE , c.COLUMN_KEY "
						+ "FROM information_schema.columns as c WHERE c.table_schema = '" + schema + "' AND c.TABLE_NAME = '" + tableNm
						+ "' ORDER BY c.ORDINAL_POSITION");
			} else {
				sql.append(
						"SELECT DISTINCT col.attnum AS ORDINAL_POSITION, col.attname AS COLUMN_NAME, col_dec.description AS COLUMN_COMMENT, col_att.udt_name AS COLUMN_TYPE, col_att.character_maximum_length AS DATA_LENGTH, col_att.is_nullable AS IS_NULLABLE "
								+ "FROM ( SELECT * FROM PG_STAT_USER_TABLES WHERE 1 = 1 " + "AND schemaname = '" + schema
								+ "') tbl LEFT JOIN PG_DESCRIPTION AS tbl_dec "
								+ "ON tbl_dec.objsubid = 0 AND tbl.relid = tbl_dec.objoid LEFT JOIN PG_ATTRIBUTE col ON tbl.relid = col.ATTRELID LEFT JOIN PG_DESCRIPTION col_dec ON col_dec.objsubid <> 0 AND col_dec.objoid  = tbl.relid AND col_dec.objoid = col.attrelid AND col_dec.objsubid = col.attnum "
								+ "LEFT JOIN INFORMATION_SCHEMA.COLUMNS col_att " + "ON " + "col_att.table_schema = tbl.schemaname "
								+ "AND col_att.table_name = tbl.relname " + "AND col_att.column_name = col.attname "
								+ "AND col_att.ordinal_position = col.attnum " + "LEFT JOIN  " + "( " + "SELECT "
								+ "t_con.table_schema    table_schema " + ",t_con.table_name      table_name " + ",t_colu.column_name   column_name "
								+ ",t_con.constraint_name constraint_name " + ",t_con.constraint_type   constraint_type " + "FROM "
								+ "INFORMATION_SCHEMA.TABLE_CONSTRAINTS t_con " + ", " + "INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE t_colu "
								+ "WHERE " + "t_con.table_catalog    = t_colu.table_catalog " + "AND t_con.table_schema    = t_colu.table_schema "
								+ "AND t_con.table_name       = t_colu.table_name " + "AND t_con.constraint_name = t_colu.constraint_name) t_index "
								+ "ON " + "t_index.table_schema        = tbl.schemaname " + "AND t_index.table_name    = tbl.relname "
								+ "AND t_index.column_name = col.attname " + "WHERE " + "1 = 1 AND tbl.relname = '" + tableNm
								+ "' AND col.attstattarget = '-1' " + "ORDER BY col.attnum;");
			}

			pstmt = connection.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (!dbType.equals("postgresql")) {
				while (rs.next()) {
//					System.out.println("mysql 연결함");
					GsDto gsdto = new GsDto();
					int index = 1;
					gsdto.setOrder(rs.getInt(index++));
					gsdto.setColumnName(rs.getString(index++));
					gsdto.setColumnComment(rs.getString(index++));
					gsdto.setColumnType(rs.getString(index++));
					gsdto.setDataLength(rs.getInt(index++));
					gsdto.setNullable(rs.getString(index++));
					gsdto.setSuccess(true);
					gsDtoList.add(gsdto);
				}
			} else { //postsql일때
				while (rs.next()) {
//					System.out.println("postsql 연결함");
					GsDto gsdto = new GsDto();
					gsdto.setOrder(rs.getInt("ORDINAL_POSITION"));
					gsdto.setColumnName(rs.getString("COLUMN_NAME"));
					gsdto.setColumnComment(rs.getString("COLUMN_COMMENT"));
					gsdto.setColumnType(rs.getString("COLUMN_TYPE"));
					gsdto.setDataLength(rs.getInt("DATA_LENGTH"));
					gsdto.setNullable(rs.getString("IS_NULLABLE"));
					gsdto.setSuccess(true);
					gsDtoList.add(gsdto);
				}
			}
//			System.out.println("gsDtoList : " + gsDtoList.toString());

		} catch (ClassNotFoundException e) {
			System.out.println("드라이브 로딩 실패");
			GsDto gsdto = new GsDto();
			gsdto.setErrorReason("드라이브 로딩 실패");
			gsdto.setSuccess(false);
			gsDtoList.add(gsdto);
		} catch (SQLException e) {
			System.out.println("SQL 에러");
			e.printStackTrace();
			GsDto gsdto = new GsDto();
			gsdto.setErrorReason("SQL 에러");
			gsdto.setSuccess(false);
			gsDtoList.add(gsdto);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return gsDtoList;
	}

	public List<String> gsGenerator(List<GsDto> gsDto) {
		List<String> lineList = new ArrayList<>();

		for (GsDto columns : gsDto) {
			if (columns.isSuccess()) {

				lineList.add("//" + columns.getColumnComment());
				//타입 설정
//			System.out.println("타입설정중");
				String dataType = columns.getColumnType();
				if (dataType.contains("int") || "integer".equals(dataType) || "numeric".equals(dataType) || "bigint".equals(dataType)) {
					dataType = "Integer";
				} else if ("date".equals(dataType) || "timestamp".equals(dataType)) {
					dataType = "Date";
//			} else if (dataType.contains("character") || "varchar".equals(dataType)) {
//				dataType = "String";
				} else {
					dataType = "String";
				}

				//필드 명 camelcase로 변환
				String clmnNm = columns.getColumnName().toLowerCase();
				int clmnLen = clmnNm.length();
				for (int i = 0; i < clmnLen; i++) {
					int startNum = clmnNm.indexOf("_");
					if (startNum != -1) {
						String change = clmnNm.substring(startNum, startNum + 2);
						String upperCase = clmnNm.substring(startNum + 1, startNum + 2).toUpperCase();
						clmnNm = clmnNm.replaceFirst(change, upperCase);
					}
				}
				String dtoField = "private " + dataType + " " + clmnNm + ";";
				lineList.add(dtoField);
				lineList.add("");
			} else {
				lineList.add(columns.getErrorReason());
			}
		}
		return lineList;
	}

}
