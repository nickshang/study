package com.shang.sql;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 功能描述 ：针对oracle，分页处理 
 * 时间：2009-7-17
 * @author NICK
 * 
 */
public class PaginationManager {

	// 总记录
	private int rsCount = 0;

	// 总页数
	private int pageCount = 0;

	// 当前页数
	private int pageCurr = 0;

	// 单页记录数
	private int pageSize = 10;

	/**
	 * 获取总记录条数
	 * 
	 * @param rsCount
	 *            总记录条数
	 */
	public int getRsCount() {
		return rsCount;
	}

	/**
	 * 设置当前页数
	 * 
	 * @return 当前页数
	 */
	public int getPageCurr() {
		return pageCurr;
	}

	/**
	 * 获取页数记录数数量
	 * 
	 * @return 页数记录数数量
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置当页数
	 * 
	 * @param pageCurr
	 *            当页数
	 */
	public void setPageCurr(int pageCurr) {
		this.pageCurr = pageCurr;
	}

	/**
	 * 设置页面记录数
	 * 
	 * @param pageSize
	 *            页面记录数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取总页数
	 * 
	 * @return 总页数
	 */
	public int getPageCount() {
		try {
			this.pageCount = ((this.rsCount - 1) / this.pageSize) + 1;
		} catch (Exception e) {
			this.pageCount = 0;
		}
		return this.pageCount;
	}

	/**
	 * 分页处理函数,返回分页SQL
	 */
	public String Pagination(String sql, String pageCurr, int pageSize,Connection conn) {
		// 总记录数
		getRsCount(sql,conn);

		// 单页记录数
		this.pageSize = pageSize;

		// 当前页
		int pageCurrNum = ConvertStringToInt(pageCurr);
		this.pageCurr = pageCurrNum;

		String PaginationSql = PaginationSqlByOracle.regxSql(sql, pageCurrNum,
				pageSize);

		return PaginationSql;
	}

	/**
	 * 字符转数字
	 * 
	 * @param str
	 * @return
	 */
	public int ConvertStringToInt(String str) {
		int i = 1;

		if (str == null || "".equals(str)) {
			return i;
		}

		try {
			i = Integer.valueOf(str);
		} catch (Exception e) {
			i = 1;
		}

		if (i > this.getPageCount()) {
			i = this.getPageCount();
		}
		return i;
	}

	/**
	 * 获取查询总记录数
	 * 
	 * @param sql
	 */
	private void getRsCount(String sql,Connection conn) {
		try {
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(sql);

			rs.last();
			this.rsCount = rs.getRow();

			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.out.print("----------action:ORACLE分页,获取总记录出错-----------");
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
