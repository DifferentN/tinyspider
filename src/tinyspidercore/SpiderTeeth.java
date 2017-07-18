package tinyspidercore;

import java.util.ArrayList;

/**
 * 
 * @author Administrator
 * 
 * @param <T> the type of you requested data
 */
public interface SpiderTeeth<T> {
	/**
	 * find the request data from the page .And transform it to the T
	 * @param paged
	 * @return the suited form which contain requested data
	 */
	public T pickInformation(String paged);
	/**
	 * you can select url which fit you request
	 * @param paged
	 * @return a list which contain the suited url
	 */
	public ArrayList<String> pickurl(String paged);
}
