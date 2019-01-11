package cn.edu.hdu.dao;

import java.util.List;

public interface ScoreDao {

	/**
	 * 执行sql语句
	 * @param sql
	 * @return
	 */
	public List<Object[]> executeSqlList(String sql,Object...objects);
	
	/**
	 * 执行sql语句
	 * @param sql
	 * @return  bigdecimal
	 */
	public String executeSqlCount(String sql);
	
	/**
	 * 执行sql语句
	 * @param sql
	 * @return  bigdecimal
	 */
	public List<Object[]> executeSqlData(String sql,String page,String rowNumber);
}
