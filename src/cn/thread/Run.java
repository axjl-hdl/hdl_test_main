package cn.thread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Run {
	/*
	 * 问题如下：
	 * 有苹果橘子生产者各20个，有苹果橘子消费者各20个，他们公用20个缓存区。
	 * 要求能随时查看缓存区内容，随时查看生产消费内容情况，随时暂停生产开始生产。
	 * */
	public static void main(String[] args){
//		BufferCase bf = new BufferCase();
//		Producer pro = new Producer(bf, 1, 1);
//		pro.run();
				
		Thread main = new Thread(new Runnable() {
			AllRoles allRoles = new AllRoles();
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					allRoles.applePro[i].start();
					allRoles.orangePro[i].start();
					allRoles.appleCon[i].start();
					allRoles.orangeCon[i].start();
				}
			}
		});
		main.start();
	}

	
	public void start(AllRoles allRoles){//启动
		for (int i = 0; i < 20; i++) {
			allRoles.applePro[i].start();
			allRoles.orangePro[i].start();
			allRoles.appleCon[i].start();
			allRoles.orangeCon[i].start();
		}
	}

	public void suspend(AllRoles allRoles){//暂停
		for (int i = 0; i < 20; i++) {
			allRoles.applePro[i].setFlag(false);
			allRoles.orangePro[i].setFlag(false);
			allRoles.appleCon[i].setFlag(false);
			allRoles.orangeCon[i].setFlag(false);
		}
	}
	
	public void restart(AllRoles allRoles){//重启
		for (int i = 0; i < 20; i++) {
			allRoles.applePro[i].setFlag(true);
			allRoles.orangePro[i].setFlag(true);
			allRoles.appleCon[i].setFlag(true);
			allRoles.orangeCon[i].setFlag(true);
		}
	}
	
}

