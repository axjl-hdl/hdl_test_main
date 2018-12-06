package cn.thread;
/*
 * 消费者类
 * @author hudl
 * */
public class Consumer extends Thread{
	BufferCase bufferCase = null;
	
	int id;//消费者id
	int type;//消费者类型 1-apple 2-orange
	
	int time     = 2000;//运行周期
	boolean flag = true;//是否暂停	
	
	public void setFlag(boolean flag){
		this.flag = flag;
	}
	public void setTime(int time){
		this.time = time;
	}
	
	//构造函数
	public Consumer(BufferCase bf,int id,int type){
		bufferCase = bf;
		this.id = id;
		this.type = type;
	}
	
	@Override
	public void run() {
		while (true) {
			//设定运行周期
			try {
				sleep(time);//消费所需要时间
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			synchronized(bufferCase){
				if(flag == false){
					try {
						wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				String goods = null;
				//消费
				goods = bufferCase.outBuffer(type);
				if(goods == null){
					bufferCase.inLog(goods+"消费者"+id+"消费"+goods+"失败");
				}else {
					bufferCase.inLog(goods+"消费者"+id+"消费"+goods+"成功");
				}
				System.out.println(bufferCase.outNewLog());
				
			}
			super.run();
		}
	}
	
	
	
	
	
	
	
}
