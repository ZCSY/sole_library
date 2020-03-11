package com.demo;

import java.util.Scanner;

import com.authority.Normal;
import com.authority.StoreMgr;
import com.authority.impl.DefaultCustomer;
import com.authority.impl.DefaultStoreMgr;
import com.biz.BookBiz;
import com.entity.Book;
import com.entity.ex.Bag;
import com.entity.ex.CD;
import com.entity.ex.EX;
import com.entity.ex.Pen;
import com.role.Customer;
import com.role.Role;
import com.role.StoreManager;
import com.user.User;

/**
 * 程序入口
 */
public class DemoStart {

	Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		DemoStart ds = new DemoStart();
		User user = new User();		//创建用户
		System.out.println("欢迎使用网上图书销售管理系统");
		ds.accreditRole(user);		//授权角色
		BookBiz.makeData();  		//初始化图书信息
		if(user.getRole() instanceof StoreManager) {
			ds.doStoreMgr(user);		//执行管理员操作
		}else if(user.getRole() instanceof Customer) {
			ds.doCustomer(user);		//执行顾客操作
		}
	}

	//授权角色（判断登陆用户，授权相应角色）
	public void accreditRole(User user) {
		System.out.println("请输入用户名：");
		String username = input.next();
		System.out.println("请输入密码：");
		String password = input.next();
		if(user.login(username, password)) {
			if(username.equals("admin")) {
				Role storeManager = new StoreManager();	//创建管理员角色
				StoreMgr dfStoreMgr = new DefaultStoreMgr();	//创建管理员权限
				storeManager.setStoreMgr(dfStoreMgr); 	//分配权限
				user.setRole(storeManager); 			//为用户授权角色
				
				System.out.println("登陆成功");
				System.out.println("*******欢迎进入库存管理员系统*******");
			}else {
				Role customer = new Customer();		//创建用户角色
				Normal dfNormal = new DefaultCustomer();	//创建用户权限
				customer.setNormal(dfNormal);		 		//分配权限
				user.setRole(customer); 			//为用户授权角色
				
				System.out.println("登陆成功");
				System.out.println("*******欢迎进入网上书店系统*******");
			}
		}
	}
		
	//执行管理员操作
	private void doStoreMgr(User user) {
		
		BookBiz bz = new BookBiz();
		bz.query();
		int i ;
		boolean flag = true;
		
		while(flag) {
			System.out.println("请选择即将进行的操作：0.退出\t1.图书入库\t2.图书出库\t3.查询全部图书\t4.新增图书");
			i = input.nextInt();
			
			switch(i) {
				case 0:{
					System.out.println("谢谢使用!!!");
					flag = false;
					break;
					//System.exit(0);
				}
				case 1:{
					System.out.println("请输入入库图书ID：");
					int bookId = input.nextInt();
					System.out.println("请输入图书入库数量：");
					int num = input.nextInt();
					
					bz.inBook(bookId, num);
					break;
				}
				case 2:{
					System.out.println("请输入出库图书ID：");
					int bookId = input.nextInt();
					System.out.println("请输入图书出库数量：");
					int num = input.nextInt();
					
					bz.outBook(bookId, num);
					break;
				}
				case 3:bz.query();break;
				case 4:{
					System.out.println("请输入新增图书书名：");
					String name = input.next();		
					System.out.println("请输入新增图书作者：");
					String author = input.next();	
					System.out.println("请输入新增图书出版时间：");
					String pub_date = input.next();	
					System.out.println("请输入新增图书库存数量：");
					int store = input.nextInt();			
					System.out.println("请输入新增图书价格：");
					double price = input.nextDouble();		
									
					Book book = new Book();
					book.setName(name);
					book.setAuthor(author);
					book.setPub_date(pub_date);
					book.setPrice(price);
					book.setStore(store);
					
					bz.saveBook(book);
					break;
				}
				default:{
					System.out.println("输入有误~~~请重新输入~~~");
					break;
				}
			}
		}		
	}
	
	//执行顾客操作
	@SuppressWarnings("finally")
	private void doCustomer(User user) {
		BookBiz bz = new BookBiz();
		int i ;
		boolean flag = true;
		double total = 0.0;		//总价格
		
		while(flag) {
			System.out.println("请选择即将进行的操作：0.退出\t1.查询图书\t2.购买图书");
			i = input.nextInt();

			switch(i) {
				case 0:{
					System.out.println("谢谢使用!!!");
					flag = false;
					break;
					//System.exit(0);
				}
				case 1:{
					bz.query();
					break;
				}
				case 2:{
					System.out.println("请输入购买图书ID：");
					int bookId = input.nextInt();
					System.out.println("请输入购买图书的数量：");
					int num = input.nextInt();
					bz.buyBook(bookId,num);
					try {
						System.out.println("所购图书结账中...");
						Thread.sleep(1000*1);			  // 休眠1秒
						Book book=bz.findBookById(bookId);
						bz.checkout(book);	
						
						Thread.sleep(500);			  // 休眠500毫秒
						
						//判断是否购买附赠品
						System.out.println("请问是否需要购买附赠品：0->否\t1->购买CD\t2->购买pen\t3->购买Bag");
						int exCode = input.nextInt();
						bz.buyEx(exCode);
						
						EX cd = new CD();
						EX pen = new Pen();
						EX bag = new Bag();
						Thread.sleep(500);			  // 休眠500毫秒
						switch(exCode) {
							case 0: break;
							case 1: {								
								total += cd.cost();
								break;
							}
							case 2:{								
								total += pen.cost();
								break;
							}
							case 3:{
								total += bag.cost();
								break;
							}
							default:{
								System.out.println("输入有误 ! ! ! 请重新输入... ");
								break;
							}
						}
						
						total += book.cost();
						Thread.sleep(1000);			  // 休眠1秒
						System.out.println("总价格为："+ total);
						System.out.println("**欢迎下次继续采购** ");				
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					finally {
						break;
					}					
				}
				default:{
					System.out.println("输入有误~~~请重新输入~~~");
					break;
				}
			}
		}				
	}
	
	
}
