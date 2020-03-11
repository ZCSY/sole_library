package com.role;

import com.authority.Normal;
import com.authority.StoreMgr;
import com.entity.Book;
import com.entity.ex.EX;

public class Role {

	private String description;			// 角色名
	private StoreMgr storeMgr = null;	//管理员权限
	private Normal normal = null;		//顾客权限
	
	/**
	 * 入库
	 * @param bookId
	 * @param num
	 */
	public void in(int bookId,int num){
		if(storeMgr == null){
			System.out.println("您没有库存管理员权限");
			return;
		}
		storeMgr.in(bookId,num);
	}
	
	/**
	 * 出库
	 * @param bookId
	 * @param num
	 */
	public void out(int bookId,int num){
		if(storeMgr == null){
			System.out.println("您没有库存管理员权限");
			return;
		}
		storeMgr.out(bookId,num);
	}
	
	/**
	 * 新增图书
	 * @param book
	 */
	public void save(Book book) {
		if(storeMgr == null){
			System.out.println("您没有库存管理员权限");
			return;
		}
		storeMgr.save(book);
	}
	
	/**
	 * 查询图书
	 */
	public void query(){
		if(normal == null&&storeMgr==null){
			System.out.println("您还没有登录，请登陆后再操作");
			return;
		}else if(normal!=null){	
			normal.query();
		}else{
			storeMgr.query();
		}
	}
	
	/**
	 * 结账
	 * @param book
	 */
	public void checkout(Book book){
		if(normal == null){
			System.out.println("您还没有登录，请登陆后再操作");
			return;
		}
		normal.chekout(book);
	}
	
	/**
	 * 购买图书
	 * @param bookId
	 * @param num
	 * @return
	 */
	public Book buy(int bookId,int num){
		if(normal == null){
			System.out.println("您还没有登录，请登陆后再操作");
			return null;
		}
		return normal.buy(bookId, num);
	}
	
	/**
	 * 购买附赠品
	 * @param exCode
	 * @return
	 */
	public EX buyEx(int exCode){
		if(normal == null){
			System.out.println("您还没有登录，请登陆后再操作");
			return null;
		}
		return normal.buyEx(exCode);
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StoreMgr getStoreMgr() {
		return storeMgr;
	}
	public void setStoreMgr(StoreMgr storeMgr) {
		this.storeMgr = storeMgr;
	}
	public void setNormal(Normal normal) {
		this.normal = normal;
	}
	
	public Normal getNormal() {
		return normal;
	}

}
