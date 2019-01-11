package cn.edu.hdu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.zlzkj.core.sql.Row;

import cn.edu.hdu.Code.GlobalStatic;
import cn.edu.hdu.Code.ScoreEventType;
import cn.edu.hdu.service.PublicService;

@Service
public class PublicServiceImpl implements PublicService {

	@Override
	public List<Row> getMathSymbolList() {
		Map<String,String> map = GlobalStatic.getMap();
		List<String> set = GlobalStatic.getSet();
		List<Row> rows = new ArrayList<Row>();
		for(String s:set){
			Row r = new Row();
			r.put("id", s);
			r.put("text", map.get(s));
			rows.add(r);
		}
		return rows;
	}

	@Override
	public List<Row> getScoreEventTypeList() {
		Map<String,String> map = ScoreEventType.getMap();
		Set<String> set = map.keySet();
		List<Row> rows = new ArrayList<Row>();
		for(String s:set){
			Row r = new Row();
			r.put("id", s);
			r.put("text", map.get(s));
			rows.add(r);
		}
		return rows;
	}

}
