package lab1;

import static org.junit.Assert.*;  
import java.io.BufferedReader;
import java.io.InputStreamReader;
import junit.framework.TestCase;
import lab1.ReadFromFile.ListNDG;  
public class Test extends TestCase{

	@org.junit.Test
	public void testReadFileByChars1() {
		String text[] = new String[100];
		int textSize;
		String fileName = "C:/temp/newTemp.txt";
		ReadFromFile.readFileByChars(fileName, text);
		for (textSize = 0; textSize < 100; textSize++) {
			if (text[textSize] == null) {
				break;
			}
		}
		ReadFromFile pg1 = new ReadFromFile();
		String[][] edges = new String[100][2];
		for (int j = 0; j <= textSize - 2; j++) {
			edges[j][0] = text[j];
			edges[j][1] = text[j + 1];
		}
		ListNDG pG = pg1.new ListNDG(text, textSize, edges);
		String word1="qwe";
		String word2="wer";
		String out=null;
		out = pG.queryBridgeWords(word1,word2);
		assertEquals("FUCK","No \"qwe\" and \"wer\" in the graph!",out);
	}
	public void testReadFileByChars2() {
		String text[] = new String[100];
		int textSize;
		String fileName = "C:/temp/newTemp.txt";
		ReadFromFile.readFileByChars(fileName, text);
		for (textSize = 0; textSize < 100; textSize++) {
			if (text[textSize] == null) {
				break;
			}
		}
		ReadFromFile pg1 = new ReadFromFile();
		String[][] edges = new String[100][2];
		for (int j = 0; j <= textSize - 2; j++) {
			edges[j][0] = text[j];
			edges[j][1] = text[j + 1];
		}
		ListNDG pG = pg1.new ListNDG(text, textSize, edges);
		String word1="qwe";
		String word2="most";
		String out=null;
		out = pG.queryBridgeWords(word1,word2);
		assertEquals("FUCK","No \"qwe\" in the graph!",out);
	}
	public void testReadFileByChars3() {
		String text[] = new String[100];
		int textSize;
		String fileName = "C:/temp/newTemp.txt";
		ReadFromFile.readFileByChars(fileName, text);
		for (textSize = 0; textSize < 100; textSize++) {
			if (text[textSize] == null) {
				break;
			}
		}
		ReadFromFile pg1 = new ReadFromFile();
		String[][] edges = new String[100][2];
		for (int j = 0; j <= textSize - 2; j++) {
			edges[j][0] = text[j];
			edges[j][1] = text[j + 1];
		}
		ListNDG pG = pg1.new ListNDG(text, textSize, edges);
		String word1="most";
		String word2="qwe";
		String out=null;
		out = pG.queryBridgeWords(word1,word2);
		assertEquals("FUCK","No \"qwe\" in the graph!",out);
	}
	public void testReadFileByChars4() {
		String text[] = new String[100];
		int textSize;
		String fileName = "C:/temp/newTemp.txt";
		ReadFromFile.readFileByChars(fileName, text);
		for (textSize = 0; textSize < 100; textSize++) {
			if (text[textSize] == null) {
				break;
			}
		}
		ReadFromFile pg1 = new ReadFromFile();
		String[][] edges = new String[100][2];
		for (int j = 0; j <= textSize - 2; j++) {
			edges[j][0] = text[j];
			edges[j][1] = text[j + 1];
		}
		ListNDG pG = pg1.new ListNDG(text, textSize, edges);
		String word1="the";
		String word2="people";
		String out=null;
		out = pG.queryBridgeWords(word1,word2);
		assertEquals("FUCK","The bridge words from \"the\" to \"people\" are: \"rich\",and\"poor\".",out);
	}
}
