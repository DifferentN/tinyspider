package tinyspidercore;

import java.util.ArrayList;
/**
 * 
 * @author 
 * 
 * the handler be design to deal with the request data .you can do anything what you want to do.
 * it get a task ,and the get the object from task .And you can get the url which from the 
 *  spiderTeeth and fit your request
 * 
 */
public abstract class Handler {
	private TaskQueue taskQueue;
	private SpiderQueue spiders;
	private ArrayList<String> urlList;
	private Spider spider;
	public Handler(){
		taskQueue=TaskQueueFactory.getInstance();
		spiders=SpiderQueueFactory.getInstance();
		urlList=new ArrayList<String>();
	}
	//send the task to taskQueue 
	public void sendTask(Task t){
		taskQueue.add(t);
	}
	/**
	 * deal with task and call handlerRequestObject() and handlerRequestURL()
	 * @param t
	 */
	public void handlerTaskT(Task t){
		String url;
		handlerRequestObject(t.t);
		
		if(!urlList.isEmpty()){
			for(int i=0;i<t.urlList.size();i++){
				if(handlerRequestURL(t.urlList.get(i))){
					urlList.add(t.urlList.get(i));
				}
			}
		}
		
		//use vacant spider to search new url
		for(int i=0;i<spiders.remainSpider();i++){
			spider=spiders.getSpider();
			if(!urlList.isEmpty()){
				url=urlList.remove(0);
				spider.setUrl(url);
				Thread thread=new Thread(spider);
				thread.start();
			}else{
				break;
			}
		}
	} 
	/**
	 * 
	 * @param object object is the request data
	 * you can deal with the data
	 */
	public abstract void handlerRequestObject(Object object);
	/**
	 * 
	 * @param url search url
	 * @return if this url need be search return true or false (avoid search the same url again)
	 */
	public abstract boolean handlerRequestURL(String url);
}
