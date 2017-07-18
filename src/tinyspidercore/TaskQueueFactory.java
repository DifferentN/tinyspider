package tinyspidercore;

/**
 * 
 * @author 
 * create a TaskQueue and be sure it is  the only
 */
public class TaskQueueFactory {
	private static TaskQueue queue;
	public static TaskQueue getInstance(){
		if(queue==null){
			queue=new TaskQueue();
		}
		return queue;
	}
}
