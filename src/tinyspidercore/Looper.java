package tinyspidercore;

/**
 * 
 * @author 
 *Looper is designed to handler the task.So we need to start it in SpiderManager
 */
public class Looper{
	private TaskQueue queue;
	private static LooperRunnable loop;
	public Looper(){
		this(null);
	}
	public Looper(TaskQueue queue){
		this.queue=queue;
		if(loop==null){
			loop=new LooperRunnable(queue);
		}
	}
	public void start(){
		Thread handlerThread=new Thread(loop);
		handlerThread.start();
	}

	
	
}
