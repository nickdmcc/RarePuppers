import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Picture {
	
	private String html;
	
	public void createHtmlDoc(String html){
		Document doc = null;
		try {
			doc = Jsoup.connect(html).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String title = doc.toString();
		setHtml(title);
		
	}
	
	public String findNextPage(String html) {
		String[] split = html.split(" ");
		String temp = "";
		int start;
		
		for(int i = 0; i < split.length; i++) {
			if(split[i].contains("www.reddit.com/r/rarepuppers/") && split[i+2].contains("next")) {
				temp = split[i];
				start = temp.indexOf("rarepuppers/");
				temp = temp.substring(start);
				if(temp.contains("&amp;")) {
					temp = temp.replace("amp;", "");
				}
				temp = temp.replace("\"", "");
				temp = "https://www.reddit.com/r/" + temp;
			}
		}
		
		return temp;
		
	}
	
	public ArrayList<String> parseHtmlString(String html) {
		Set<String> set = new HashSet<String>();
		ArrayList<String> list = new ArrayList<String>();
		String[] split = html.split(" ");
		String temp;
		int start;
		int end;
		for(int i = 0; i < split.length; i++) {
			if(split[i].contains(".jpg") && split[i].contains("http://i.imgur.com")) {
				temp = split[i];
				start = temp.indexOf("http://i.imgur.com");
				end = temp.indexOf(".jpg");
				temp = temp.substring(start, end);
				temp = temp + ".jpg";
				set.add(temp);
			}
		}
		
		list.addAll(set);
		return list;
	}
	
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
}
