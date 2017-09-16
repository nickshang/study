package com.shang.web.bean;

import java.util.List;


/**
 * 功能描述：表单
 * 时间：2010-04-12
 * @author Nick
 *
 */
public class FormBean {

	/**
	 * 是否成功
	 */
	private boolean success;
	
	/**
	 * 信息
	 */
	private String  message;
	
	/**
	 * 备用1
	 */
	private String  remark1;
	
	
	/**
	 * 备用2
	 */
	private String  remark2;
	
	/**
	 * 信息
	 */
	private int  total;
	
	
	/**
	 * 单个数据对象
	 */
	private Object data;

	
	/**
	 * 多个数据对象
	 */
	private List<?> datas;

	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
}
