package tinyspidercore;

import java.util.ArrayList;
/**
 * 
 * @author 
 *
 * @param <T> t is the requested data,you can make it by yourself
 * list : urlList is the url which you can obtain from the searched page
 */
public class Task {
	public Object t;
	public ArrayList<String> urlList;
	public Handler handler;
	public Task(Object t,ArrayList<String> list){
		this.t=t;
		this.urlList=list;
	}
}
