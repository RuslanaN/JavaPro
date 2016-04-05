package net.ukr.ruslana;

import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "file.txt")
public class TextContainer {
	private String text;

	public TextContainer(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Saver
	public void save(String path) throws IOException {
		FileWriter fw = new FileWriter(path);
		try {
			fw.write(text);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			fw.close();
		}
	}
}
