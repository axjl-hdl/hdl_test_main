package cn.thread;
/**
 * 生产者类
 * @author hudl
 */
public class Producer extends Thread{
	BufferCase bufferCase = null;
	
	int time     = 2000;//运行周期
	boolean flag = true;//是否暂停 
	
	int id;//生产者id
	int type;//生产类型 1-apple 2-orange
	
	public void setFlag(boolean flag){
		this.flag = flag;
	}
	public void setTime(int time){
		this.time = time;
	}
	
	public Producer(BufferCase bf,int id,int type){
		bufferCase = bf;
		this.id = id;
		this.type = type;
	}
	
	@Override
	public void run() {
		while (true) {
			//设定运行周期
			try {
				sleep(time);//生产所需要时间
			} catch (Exception e) {
				e.printStackTrace();
			}
			//生产苹果
			if (flag == true) {
				synchronized (bufferCase) {
					String goods = new String((type==1)?"apple":"orange");
					if (bufferCase.inBuffer(goods)) {
						bufferCase.inLog(goods+"生产者"+id+"放入"+goods+"成功");
					}else {
						bufferCase.inLog(goods+"生产者"+id+"放入"+goods+"失败");
					}
				}
				System.out.println(bufferCase.outNewLog());
			}
			super.run();
		}
	}
	
	
}
