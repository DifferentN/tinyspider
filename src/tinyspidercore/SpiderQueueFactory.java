package tinyspidercore;

/**
 * 
 * @author 
 * create the taskQueue and be sure taskQueue is the only
 */
public class SpiderQueueFactory {
	private static SpiderQueue spiders;
	public static SpiderQueue getInstance(){
		if(spiders==null){
			spiders=new SpiderQueue();
		}
		return spiders;
	}
}
