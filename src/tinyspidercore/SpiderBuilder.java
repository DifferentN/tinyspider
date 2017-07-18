package tinyspidercore;

/**
 * 
 * @author 
 * 
 * spiderBuilder is designed to build a suited spider 
 * First thing first,you need tell the spider the url which link the be searched page,and then
 * you need to tell spider which handler to deal with the task and which SpiderTeeth to filter
 * the data.
 */
public class SpiderBuilder {
	private Spider spider;
	private String url,content;
	private Handler handler;
	private SpiderTeeth teeth;
	public SpiderBuilder setUrl(String url) {
		this.url = url;
		return this;
	}

	public SpiderBuilder setContent(String content) {
		this.content = content;
		return this;
	}

	public SpiderBuilder setHandler(Handler handler) {
		this.handler = handler;
		return this;
	}

	public SpiderBuilder setTeeth(SpiderTeeth teeth) {
		this.teeth = teeth;
		return this;
	}

	public Spider createSpider(){
		spider=new Spider();
		spider.setUrl(url);
		spider.setContent(content);
		spider.setmHandler(handler);
		spider.setSpiderFilter(teeth);
		return spider;
	}
}
