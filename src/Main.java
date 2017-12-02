
public class Main {
	
	public static void main(String[] args) {
				Picture i = new Picture();
				i.setHtml("https://www.reddit.com/r/rarepuppers/");
				i.createHtmlDoc(i.getHtml());
				i.findNextPage(i.getHtml());
				Window window = new Window();
				window.createWindow();
				window.setDoggos(i.parseHtmlString(i.getHtml()));
				window.createLabel(window.getFrame(), window.getDoggos());
				window.createButton(window.getFrame(), window.getButton(), i);
			}
}
