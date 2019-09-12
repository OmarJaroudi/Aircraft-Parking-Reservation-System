package application;

public class Parse {
	String raw;
	String processed[];
	
	public Parse(String r) {
		this.raw = r;
		this.processed = new String[100];
	}
	int j = 0;
	public String[] process() {
		String delim = "[/]";
		processed = raw.split(delim);
		return processed; 
	}
	
}
