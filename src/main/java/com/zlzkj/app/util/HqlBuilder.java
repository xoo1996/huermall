package com.zlzkj.app.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
/**
 * 
 * 简单的hql构造器 ，主要用于where like 这种需要判断参数多少来组装hql的情景
 * 参数一律String类型，参入传入前先转换
 * @author PJL
 *
 */
public class HqlBuilder {

	private String select;
	private String from;
	private List<String> where;
	private List<String> like;
	private List<String> orlike;
	private List<String> join;
	private String orderby;
	private String between;
	private List<String> in;
	private List<Object> whereParam;
	private List<String> likeParam;
	private String groupBy;
	
	private boolean clean(){
		try {
			whereParam.clear();
			likeParam.clear();
			select = null;
			from = null;
			orderby = null;
			between = null;
			where.clear();
			like.clear();
			join.clear();
			orlike.clear();
			in.clear();
			groupBy = null;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public HqlBuilder(){
		likeParam = new ArrayList<String>();
		whereParam = new ArrayList<Object>();
		where = new ArrayList<String>();
		like = new ArrayList<String>();
		orlike = new ArrayList<String>();
		join = new ArrayList<String>();
		in  = new ArrayList<String>();
	}
	
	public HqlBuilder select(String s){
		this.clean();
		if(s != null && !s.trim().equals(""))
			select = " select " + s + " ";
		return this;
	}
	
	public HqlBuilder from(String f){
//		this.clean();
		if(f != null && !f.trim().equals(""))
			from = " from " + f + " ";
		return this;
	}
	
	public HqlBuilder where (String leftjoin){
		where.add(leftjoin);
		return this;
	}
	
	public HqlBuilder where (String left,String right){
		if(left == null || left.trim().equals("")){
			return this;
		}
		if(right != null && !right.trim().equals("")){
			where.add(" "+ left + " = ? ");
			whereParam.add(right);
		}
		return this;
	}
	public HqlBuilder where (String left,Long right){
		if(left == null || left.trim().equals("")){
			return this;
		}
		if(right != null){
			where.add(" "+ left + " = ? ");
			whereParam.add(right);
		}
		return this;
	}
	
	public HqlBuilder where (String left,String symbol,String right){
		if(left == null || left.trim().equals("") 
				|| (symbol == null || symbol.trim().equals("")) ){
			return this;
		}
		if(right != null && !right.trim().equals("")){
			where.add(" "+ left + " " + symbol + " ? ");
			whereParam.add(right);
		}
		return this;
	}
	
	public HqlBuilder where (String left,String symbol,Long right){
		if(left == null || left.trim().equals("") 
				|| (symbol == null || symbol.trim().equals("")) ){
			return this;
		}
		if(right != null){
			where.add(" "+ left + " " + symbol + " ? ");
			whereParam.add(right);
		}
		return this;
	}
	
	
	public HqlBuilder like (String left , String right){
		if(left == null || left.trim().equals("")){
			return this;
		}
		if(right != null && !right.trim().equals("")){
			like.add(" "+ left + " like ? ");
			likeParam.add(right);
		}
		return this;
	}
	public HqlBuilder orlike (String left , String right){
		if(left == null || left.trim().equals("")){
			return this;
		}
		if(right != null && !right.trim().equals("")){
			orlike.add(" "+ left + " like '%" + right + "%' ");
		}
		return this;
	}
	
	public HqlBuilder join(String j,String lor){
		if(j != null && !j.trim().equals("") && lor != null && !lor.trim().equals("")){
			join.add(" " + lor + " join " + j + " ");
		}
		return this;
	}
	
	public HqlBuilder orderby(String order,String by){
		if(order != null && !order.trim().equals("")){
			if(by != null && !by.trim().equals("")){
				orderby = " order by " + order  + " " + by;
			}
		}
		return this;
	}
	
	public HqlBuilder between(String target,String left,String right){
		if(left != null && !left.trim().equals("")){  //左边不为空
			between = target + " > :start "  ;
			if(right != null && !right.trim().equals("")){  //同时右边不为空
				between += " and " + target + " < :end ";
			}
		}else{  //左边为空
			if(right != null && !right.trim().equals("")){    //右边为空
				between = target + " < :end ";
			}
		}
		return this;
	}
	
	public HqlBuilder between(String target,Date left,Date right){
		if(left != null ){  //左边不为空
			between = target + " > :start "  ;
			if(right != null ){  //同时右边不为空
				between += " and " + target + " < :end ";
			}
		}else{  //左边为空
			if(right != null ){    //右边为空
				between = target + " < :end ";
			}
		}
		return this;
	}
	
	public HqlBuilder in(String left,String right){
		if(left != null && !left.trim().equals("") && right != null && !right.trim().equals("")){
			in.add(" " + left + " in( :" + right + ") ");
		}
		return this;
	}
	
	public HqlBuilder in(String left,List<String> list){
		if(left != null && !left.trim().equals("") && list != null && list.size()>0){
			String s = "";
			s += "'" + list.get(0) + "'";
			for(int i = 1;i<list.size();i++){
				s += ",'" + list.get(i) + "'";
			}
			in.add(" " + left + " in(" + s + ") ");
		}
		return this;
	}
	
	public HqlBuilder groupBy(String target){
		StringBuffer sb = new StringBuffer();
		sb.append(target);
		groupBy = " group by " + target + " ";
		return this;
	}
	
	
	public String buildHql(){
		int haveWhere = 0;
		String sql = "";
		if(select != null){
			sql += select;
		}
		if(from != null){
			sql += from;
		}
		if(join != null){
			for(int i = 0;i < join.size();i++){
				sql += join.get(i);
			}
		}
		if(where.size() > 0 ){
			for(int i = 0;i < where.size();i++){
				if(i == 0){
					haveWhere += 1;
					sql +=" where ";
					sql += where.get(i);
				}else{
					sql +=" and ";
					sql += where.get(i);
				}
			}
		}
		if(between != null){
			if(haveWhere  == 0){
				haveWhere += 1;
				sql +=" where ";
			}
			else{
				sql +=" and ";
			}
			sql += between;		
		}
		if(like.size() > 0){
			for(int i = 0;i < like.size();i++){
				if(i == 0){
					if(haveWhere == 0){
						haveWhere += 1;
						sql +=" where ";
					}
					else
						sql +=" and ";
					sql += like.get(i);
				}else{
					sql +=" and ";
					sql += like.get(i);
				}
			}
		}
		if(orlike.size() > 0){
			for(int i = 0;i < orlike.size();i++){
				if(i == 0){
					if(haveWhere == 0){
						haveWhere += 1;
						sql +=" where ";
					}
					else
						sql +=" or ";
					sql += orlike.get(i);
				}else{
					sql +=" or ";
					sql += orlike.get(i);
				}
			}
		}
		
		if(in.size() > 0){
			for(int i = 0;i < in.size();i++){
				if(i == 0){
					if(haveWhere == 0){
						haveWhere += 1;
						sql +=" where ";
					}
					else
						sql +=" and ";
					sql += in.get(i);
				}else{
					sql +=" and ";
					sql += in.get(i);
				}
			}
		}
		if(orderby != null)
			sql += orderby;
		
		if(groupBy != null){
			sql += groupBy;
		}
		return sql;
	}
	
	public Query setParam(Query query){
		int total = 0;
		for(int i = 0;i < whereParam.size();i++ ){
			query.setParameter(total, whereParam.get(i));
			total++;
		}
		for(int i = 0;i < likeParam.size();i++ ){
			query.setParameter(total, "%" + likeParam.get(i) + "%");
			total++;
		}
		this.clean();
		return query;
	}
}
