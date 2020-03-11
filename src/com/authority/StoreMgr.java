package com.authority;

import com.entity.Book;
/**
 * 管理员权限
 * 需具有in入库,out出库，save增加新书的功能
 */
public interface StoreMgr {
	public void in(int bookId,int num);
	public void out(int bookId,int num);
	public void save(Book book);
	public String query();
}