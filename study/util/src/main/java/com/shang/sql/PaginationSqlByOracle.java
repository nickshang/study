package com.shang.sql;

/**
 * 功能描述:对Oracle查询语句进行分页
 * 时间：2009-7-24
 * @author NICK
 * 
 */
public class PaginationSqlByOracle {

	/**
	 * 转换分页SQL
	 * 
	 * @param sql
	 *            查询SQL
	 * @param page
	 *            第几页面
	 * @param rownum
	 *            单页数目
	 * @return
	 */
	public static String regxSql(String sql, int pagesize, int rownum) {

		// 组合SQL
		String r_sql = "";
		
		// 第1段 SELECT * FROM
		r_sql += " SELECT  ";
		r_sql += " TABLE3.*  FROM  ( ";
		r_sql += " SELECT TABLE2.* , ROWNUM RN ";
		r_sql += " FROM ";
		r_sql += " ( ";

		// 第二段
		r_sql += " SELECT TABLE1.*  ,  ROWNUM  ";
		r_sql += " FROM  ( " + sql + " )  TABLE1 ";

		// 第3段
		r_sql += " WHERE ROWNUM <=  " + pagesize * rownum;
		r_sql += " ) TABLE2  ";
		r_sql += " ) TABLE3  ";
		r_sql += " WHERE RN >  " + (pagesize - 1) * rownum;

	
		return r_sql;
	}
	
}
