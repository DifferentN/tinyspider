package tinyspidercore;

import java.util.concurrent.LinkedBlockingQueue;
/**
 * 
 * @author 
 *a TaskQueue contain the task which need to deal
 */
public class TaskQueue {
	private LinkedBlockingQueue<Task> queue;
	public TaskQueue(){
		queue=new LinkedBlockingQueue<Task>();
	}
	public void add(Task t){
		queue.add(t);
	}
	public Task getTask(){
		Task task=null;
		try {
			task=queue.take();
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return task;
	}
}
