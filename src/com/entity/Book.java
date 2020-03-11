package com.entity;

public class Book {
		private int id;				//编号
		private String name;		//书名
		private String author;		//作者
		private String pub_date;	//出版时间
		private int store;			//库存数量
		private double price;		//价格
		private int num = 1;		//购买数量 
			
		/**
		 * 计算价钱
		 * @return
		 */
		public double cost() {
			return price * num;//返回书的价格
		}
		
		public void setPrice(double price) {
			this.price = price;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getPub_date() {
			return pub_date;
		}
		public void setPub_date(String pubDate) {
			pub_date = pubDate;
		}
		public int getStore() {
			return store;
		}
		public void setStore(int store) {
			this.store = store;
		}
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public double getPrice() {
			return price;
		}
		
}
