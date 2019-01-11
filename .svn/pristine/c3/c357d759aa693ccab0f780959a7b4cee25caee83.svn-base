package com.zlzkj.app.util;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * 简单的hql构造器 ，主要用于where like 这种需要判断参数多少来组装hql的情景
 * 参数一律String类型，参入传入前先转换
 * @author PJL
 *
 */
public class HqlBuilder2 {

	private String select;
	private String from;
	private List<String> where;
	private List<String> like;
	private List<String> orlike;
	private List<String> join;
	private String orderby;
	private String between;
	private List<String> in;
	
	private boolean clean(){
		try {
			select = null;
			from = null;
			orderby = null;
			between = null;
			where.clear();
			like.clear();
			join.clear();
			orlike.clear();
			in.clear();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public HqlBuilder2(){
		where = new ArrayList<String>();
		like = new ArrayList<String>();
		orlike = new ArrayList<String>();
		join = new ArrayList<String>();
		in  = new ArrayList<String>();
	}
	
	public HqlBuilder2 select(String s){
		if(s != null && !s.trim().equals(""))
			select = " select " + s + " ";
		return this;
	}
	
	public HqlBuilder2 from(String f){
		if(f != null && !f.trim().equals(""))
			from = " from " + f + " ";
		return this;
	}
	
	public HqlBuilder2 where (String left,String right){
		if(left == null || left.trim().equals("")){
			return this;
		}
		if(right != null && !right.trim().equals("")){
			where.add(" "+ left + " = '" + right + "' ");
		}
		return this;
	}
	
	public HqlBuilder2 where (String left,String symbol,String right){
		if(left == null || left.trim().equals("") 
				|| (symbol == null || symbol.trim().equals("")) ){
			return this;
		}
		if(right != null && !right.trim().equals("")){
			where.add(" "+ left + " " + symbol + " '" + right + "' ");
		}
		return this;
	}
	
	
	public HqlBuilder2 like (String left , String right){
		if(left == null || left.trim().equals("")){
			return this;
		}
		if(right != null && !right.trim().equals("")){
			like.add(" "+ left + " like '%" + right + "%' ");
		}
		return this;
	}
	public HqlBuilder2 orlike (String left , String right){
		if(left == null || left.trim().equals("")){
			return this;
		}
		if(right != null && !right.trim().equals("")){
			orlike.add(" "+ left + " like '%" + right + "%' ");
		}
		return this;
	}
	
	public HqlBuilder2 join(String j,String lor){
		if(j != null && !j.trim().equals("") && lor != null && !lor.trim().equals("")){
			join.add(" " + lor + " join " + j + " ");
		}
		return this;
	}
	
	public HqlBuilder2 orderby(String order,String by){
		if(order != null && !order.trim().equals("")){
			if(by != null && !by.trim().equals("")){
				orderby = " order by " + order  + " " + by;
			}
		}
		return this;
	}
	
	public HqlBuilder2 between(String target,String left,String right){
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
	
	public HqlBuilder2 in(String left,String right){
		if(left != null && !left.trim().equals("") && right != null && !right.trim().equals("")){
			in.add(" " + left + " in( :" + right + ") ");
		}
		return this;
	}
	
	public HqlBuilder2 in(String left,List<String> list){
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
		this.clean();
		return sql;
	}
}
