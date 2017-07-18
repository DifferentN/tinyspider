package tinyspidercore;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * @author 
 *spiderQueue is designed to save vacant spider
 */
public class SpiderQueue {
	LinkedBlockingQueue<Spider> spiders;
	public SpiderQueue(){
		spiders=new LinkedBlockingQueue<Spider>();
	}
	public void addSpider(Spider spider){
		spiders.add(spider);
	}
	public Spider getSpider(){
		Spider spider=null;
		try {
			spider=spiders.take();
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return spider;
	}
	//get vacant spider num
	public int remainSpider(){
		return spiders.size();
	}
}
