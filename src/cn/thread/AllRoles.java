package cn.thread;

public class AllRoles {
	public static Producer[] applePro = new Producer[20];
	public static Producer[] orangePro = new Producer[20];
	
	public static Consumer[] appleCon = new Consumer[20];
	public static Consumer[] orangeCon = new Consumer[20];
	
	static{
		BufferCase bf = new BufferCase();
		for (int i = 0; i < 20; i++) {//初始化生产者消费者线程
			applePro[i] = new Producer(bf, i, 1);
			orangePro[i] = new Producer(bf, i, 2);
			
			appleCon[i] = new Consumer(bf, i, 1);
			orangeCon[i] = new Consumer(bf, i, 2);
		}
	}
}
