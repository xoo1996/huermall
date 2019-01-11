package cn.edu.hdu.service;

import java.util.List;

import com.zlzkj.core.sql.Row;

public interface PublicService {

	/**
	 * 返回数学符号列表，>=,<=,=
	 * @return
	 */
	public List<Row> getMathSymbolList();
	
	/**
	 * 返回积分事件所有类型
	 * @return
	 */
	public List<Row> getScoreEventTypeList();
}
