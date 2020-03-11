package com.authority.impl;

import com.authority.StoreMgr;
import com.biz.BookBiz;
import com.entity.Book;

/**
 * 默认库管
 * 完成实现类相关函数。
 */
public class DefaultStoreMgr implements StoreMgr {
	BookBiz bookBiz=new BookBiz();

	/**
	 * 重写图书入库
	 */
	public void in(int bookId, int num) {
		
		bookBiz.inBook(bookId, num);	
	}
	/**
	 * 重写新增图书
	 */
	public void save(Book book) {
		
		bookBiz.saveBook(book);
	}

	/**
	 * 重写出库
	 */
	public void out(int bookId, int num) {
		
		bookBiz.outBook(bookId, num);
	}
	/**
	 * 查询图书
	 */
	public String query() {
		return String.valueOf(bookBiz.query());
		
	}
}
