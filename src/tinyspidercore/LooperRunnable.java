package tinyspidercore;

/**
 * 
 * @author 
 *the truely loop ,it constantly obtain task and deal with task
 */
public class LooperRunnable implements Runnable{
	private TaskQueue queue;
	public LooperRunnable(TaskQueue queue){
		this.queue=queue;
	}
	private Task task;
	private Handler handler;
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
			task=queue.getTask();
			handler=task.handler;
			handler.handlerTaskT(task);
		}
	}

}
