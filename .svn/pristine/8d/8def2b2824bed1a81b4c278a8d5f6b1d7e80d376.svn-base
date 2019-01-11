package cn.edu.hdu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlzkj.app.util.HqlBuilder;
import com.zlzkj.core.sql.Row;

import cn.edu.hdu.dao.impl.PriceconfigDaoImpl;
import cn.edu.hdu.pojo.PriceConfig;
import cn.edu.hdu.service.PriceconfigService;
import cn.edu.hdu.util.DataUtil;

@Service("priceconfigService")
public class PriceconfigServiceImpl extends GenericService<PriceConfig>
		implements PriceconfigService {

	public PriceconfigDaoImpl getPriceconfigDao() {
        return (PriceconfigDaoImpl)this.getGenericDao();
    }

	@Autowired
	public void setPriceconfigDao(PriceconfigDaoImpl pcDao) {
	    this.setGenericDao(pcDao);
	}
	
	@Override
	public boolean addPrice(PriceConfig priceconfig) {
		try {
			this.save(priceconfig);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Row getScoreList(int page, int rowNum) {
		HqlBuilder hb = new HqlBuilder();
		String hql_data = hb.from("PriceConfig pc").buildHql();
		String hql_sum = hb.select("count(*)")
				.from("PriceConfig pc").buildHql();
		List<PriceConfig> accList = DataUtil.getPages(this.getQuery(hql_data), page, rowNum).list();
		List<PriceConfig> accSum = this.findByHql(hql_sum, null);
		List<Row> rows = new ArrayList<Row>();
		for(PriceConfig acc:accList){
			Row row = new Row();
			row.put("id", acc.getId());
			row.put("start_price", acc.getStartPrice());
			row.put("end_price", acc.getEndPrice());
			row.put("change_score", acc.getChangeScore());
			rows.add(row);
		}
		Row row = new Row();
		row.put("total", accSum);
		row.put("rows", rows);
		return row;
	}

	@Override
	public void updatePrice(PriceConfig pc) {
		this.getPriceconfigDao().updatePrice(pc);		
	}

	@Override
	public PriceConfig getPrice(String id) {
		return this.getPriceconfigDao().getPrice(id);
	}

	@Override
	public Long getSuitPCByPrice(Long price) {
		List<PriceConfig> pcList = this.getPriceconfigDao().getSuitPCByPrice(price);
		Long score = 0L;
		for(PriceConfig pc:pcList){
			if(pc.getChangeScore() > score){
				score = pc.getChangeScore();
			}
		}
		return score;
	}
}
