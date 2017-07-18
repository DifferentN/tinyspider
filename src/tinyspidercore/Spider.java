package tinyspidercore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author 
 *this is the "spider" ,it search special page,and send the desired data to dealing queue.
 *the "desired data" can be obtain by a spiderTeeth ,which can be implemented by yourself.
 *
 *spider is the handler to deal with the requested data
 *spiderFilter is the filter to filter the useless data and get the your requested data
 */
public class Spider implements Runnable ,Cloneable{
	private Handler mHandler;
	private String url,content,rawPage,paged;
	private HttpURLConnection conn;
	private URL myURL;
	private InputStream input;
	private SpiderTeeth spiderFilter;
	private ArrayList<String> listurl;
	private Object t;
	public SpiderQueue spiders;
	public Spider(){
		spiders=SpiderQueueFactory.getInstance();
	}
	public void setmHandler(Handler mHandler) {
		this.mHandler = mHandler;
	}
	public void setUrl(String url) {
		this.url = url; 
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setSpiderFilter(SpiderTeeth spiderFilter) {
		this.spiderFilter = spiderFilter;
	}
	public void setSpiders(SpiderQueue spiders) {
		this.spiders = spiders;
	}
	private void setConnContent(String content){
		
	}
	private String getRawPage(InputStream input){
		StringBuffer buffer=new StringBuffer();
		String result = null;
		BufferedReader reader=new BufferedReader(new InputStreamReader(input));
		char cha[]=new char[1024];
		try {
			while(reader.read(cha)>-1){
				buffer.append(cha);
			}
			result=new String(buffer);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * eliminate the '\n' '\r'  '\f' in a page
	 * @param rawPage a page which need be cleaned 
	 * @return a page which not include '\n' '\r' '\f' 
	 */
	private String trimsS(String rawPage){
		StringBuffer buf=new StringBuffer();
		Pattern mPattern=Pattern.compile("[^\\n\\r\\f]*");
		Matcher matcher=mPattern.matcher(rawPage);
		while(matcher.find()){
			buf.append(matcher.group(0).trim());
		}
		return new String(buf);
	}
	/**
	 * get a page by the url and send it to the dealing queue
	 */
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			myURL=new URL(url);
			conn=(HttpURLConnection) myURL.openConnection();
			setConnContent(content);
			conn.connect();
			if(conn.getResponseCode()==200){
				input=conn.getInputStream();
			}else{
				return ;
			}
			rawPage=getRawPage(input);
			paged=trimsS(rawPage);
			
			sendTask(paged);
			
			finish();
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	/**
	 * get request data and encapsulate the data in a task,and then send it 
	 * @param paged2
	 * @throws Exception
	 */
	private void sendTask(String paged2) throws Exception {
		// TODO 自动生成的方法存根
		if(spiderFilter==null){
			throw new Exception("spiderFilter is null");
		}
		t=spiderFilter.pickInformation(paged);
		listurl=spiderFilter.pickurl(paged);
		Task task=new Task(t,listurl);
		task.handler=mHandler;
		mHandler.sendTask(task);
	}
	/**
	 * the spider finished its job ,and put it to the idle spider queue
	 */
	private void finish() {
		// TODO 自动生成的方法存根
		spiders.addSpider(this);
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO 自动生成的方法存根
		return super.clone();
	}
	
}
