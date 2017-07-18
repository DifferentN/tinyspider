package tinyspidercore;

/**
 * 
 * @author 
 *the spiderManager be used to manager the spider and you can get idle spider from it
 */
public class SpiderManager {
	private Spider spider,templateSpider;
	private SpiderQueue spiders;
	private int num=0;
	private TaskQueue taskQueue;
	private Looper looper;
	public SpiderManager(){
		this(0,null);
	}
	/**
	 * 
	 * @param num   the max number of spider(some spider is same,but also can be different)
	 * @param spider    the spider to search the special page 
	 */
	public SpiderManager(int num,Spider spider){
		this.num=num;
		this.spider=spider;
		spiders=SpiderQueueFactory.getInstance();
		taskQueue=TaskQueueFactory.getInstance();
		looper=new Looper(taskQueue);
		
		setSpiderQueue();
	}
	// add s spider to vacant spiderQueue
	public void add(Spider spider){
		Thread thread=new Thread(spider);
		thread.start();
	}
	//initiate the SpiderQueue
	private void setSpiderQueue(){
		if(spider!=null){
			for(int i=0;i<num;i++){
				try {
					templateSpider=(Spider) spider.clone();
					spiders.addSpider(templateSpider);
				} catch (CloneNotSupportedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		}
		
	}
	//start spider and looper
	public void startWork(){
		spider=spiders.getSpider();
		if(spider!=null){
			Thread thread=new Thread(spider);
			thread.start();
		}
		looper.start();
	}
}
