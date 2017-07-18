package tinyspider.sample;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tinyspidercore.Handler;
import tinyspidercore.Spider;
import tinyspidercore.SpiderBuilder;
import tinyspidercore.SpiderManager;
import tinyspidercore.SpiderTeeth;

public class BlockSpider {
	private Spider spider;
	private SpiderManager manager;
	private SpiderBuilder builder;
	private MyHandler myHandler;
	private MyTeeth teeth;
	public BlockSpider(){
		init();
	}
	private void init() {
		// TODO 自动生成的方法存根
		builder =new SpiderBuilder();
		myHandler=new MyHandler();
		teeth=new MyTeeth();
		spider=builder.setHandler(myHandler)
				.setTeeth(teeth)
				.setUrl("http://www.weather.com.cn/weather/101120101.shtml")
				.createSpider();
		manager=new SpiderManager(1,spider);
	}
	public void startSearch(){
		manager.startWork();
	}
	
}
class MyHandler extends Handler{

	@Override
	public void handlerRequestObject(Object object) {
		// TODO 自动生成的方法存根
		System.out.println((String)object);
	}

	@Override
	public boolean handlerRequestURL(String url) {
		// TODO 自动生成的方法存根
		return false;
	}
	
}
class MyTeeth implements SpiderTeeth<String>{
	Pattern mPattern;
	Matcher matcher;
	@Override
	public String pickInformation(String paged) {
		// TODO 自动生成的方法存根
		mPattern=Pattern.compile("<ul.*</ul>");
		matcher=mPattern.matcher(paged);
		String temp,strr[];
		if(matcher.find()){
			temp=matcher.group(0);
			mPattern=Pattern.compile("<ul class=\"t clearfix\">");
			strr=mPattern.split(temp);
			mPattern=Pattern.compile("</ul>");
			strr=mPattern.split(strr[1]);
			System.out.println(strr[0]);
			return strr[0];
			
		}
		return null;
	}

	@Override
	public ArrayList pickurl(String paged) {
		// TODO 自动生成的方法存根
		return null;
	}
	
}
