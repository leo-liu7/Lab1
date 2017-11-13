package lab1;

//import java.io.FileWriter;
//import java.io.IOException;
//import java.io .File;
import java.util.Random;
import java.io.*;
//import java.lang.*;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReadFromFile {
	public static void readFileByChars(String fileName, String[] text) {
		File file = new File(fileName);
		Reader reader = null;
		try {
			System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");// һ�ζ�һ���ַ�
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			int a = 1, i = 0;
			while ((tempchar = reader.read()) != -1) {
				if (((char) tempchar >= 'a' && (char) tempchar <= 'z')
						|| ((char) tempchar >= 'A' && (char) tempchar <= 'Z')) {
					if ((char) tempchar <= 'Z') {
						tempchar = tempchar + 32;
					}
					if (text[i] == null) {
						text[i] = String.valueOf((char) tempchar);
					} else {
						text[i] = text[i] + (char) tempchar;
					}
					a = 0;
				} else {
					if (a == 0) {
						System.out.print(text[i] + " ");
						i++;
					}
					a = 1;
				}
			}
			System.out.println();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class ListNDG {
		Vertex[] vertexLists;// �ڽӱ�����
		int size;// ���鳤��

		class Vertex {// �ڽӱ�ڵ��࣬���������ݽṹ
			String ch;
			Vertex next;
			int weight = 0;

			Vertex(String ch) {// ��ʼ������
				this.ch = ch;
			}

			void add(String ch) {// �ӵ�����β
				Vertex node = this;
				while (node.next != null) {
					if (node.next.ch.equals(ch)) {
						node.next.weight++;
						return;
					}
					node = node.next;
				}
				node.next = new Vertex(ch);
				node.next.weight = 1;
			}
		}

		public ListNDG(String[] vertexs, int length, String[][] edges) {
			size = length;
			// System.out.print(size);
			for (int i = 0; i < length; i++) {
				// System.out.print(vertexs[i]+" ");
				for (int j = 0; j < i; j++) {
					if (vertexs[j].equals(vertexs[i])) {
						// System.out.print(vertexs[i]);
						// System.out.print(vertexs[j]);
						size--;
						break;
					}
				}
			}
			// System.out.print(size);
			// System.out.print("asd");
			this.vertexLists = new Vertex[size];// ȷ���ڽӱ��С
			// �����ڽӱ�ͷ�ڵ�
			int newsize = 0;
			for (int i = 0; i < length; i++) {
				int count = 1;
				for (int j = 0; j < i; j++) {
					if (vertexs[j].equals(vertexs[i])) {
						count = 0;
						break;
					}
				}
				if (count == 1) {
					this.vertexLists[newsize] = new Vertex(vertexs[i]);
					// System.out.print(vertexs[i]+"wer ");
					newsize++;
				}
			}
			// �洢����Ϣ
			for (int c = 0; c < length - 1; c++) {
				int p = getPosition(edges[c][0]);
				// System.out.print(p);
				vertexLists[p].add(edges[c][1]);
			}
		}

		// ���ݶ������ƻ�ȡ�����±�
		private int getPosition(String c) {
			for (int i = 0; i < size; i++) {
				if (vertexLists[i].ch.equals(c)) {
					return i;
				}
			}
			return -1;

		}

		// ��������ڽӱ�
		public void print() {
			for (int i = 0; i < size; i++) {
				Vertex temp = vertexLists[i];
				while (temp != null) {
					if (temp.next == null) {
						System.out.print(temp.ch);
					} else {
						System.out.print(temp.ch + "->");
					}
					temp = temp.next;
				}
				System.out.println();
			}
		}

		String queryBridgeWords1(String word1, String word2) {
			int i, j, count = 1;
			String s = null;
			for (i = 0; i < size; i++) {
				if (word1.equals(vertexLists[i].ch)) {
					for (j = 0; j < size; j++) {
						if (word2.equals(vertexLists[j].ch)) {
							count = 0;
							break;
						}
					}
				}
				if (count == 0) {
					break;
				}

			}
			if (count == 1) {
				// System.out.println("No "+word1+" or "+word2+" in the graph!");
				return s;
			}
			int x1 = chaxundanchibianhao(word1);
			count = 1;
			Vertex node1 = vertexLists[x1];
			Vertex qwe = node1;
			int wsx = 0;
			while (qwe.next != null) {
				wsx++;
				qwe = qwe.next;
			}
			while (wsx != 0) {
				if (node1.next.ch.equals(word2)) {
				} else {
					int x = chaxundanchibianhao(node1.next.ch);
					Vertex node2 = vertexLists[x];
					Vertex asd = node2;
					while (asd.next != null) {
						if (asd.next.ch.equals(word2)) {
							count = 0;
							if (s != null) {
								s = s + node2.ch + " ";
							} else {
								s = node2.ch + " ";
							}
							// System.out.println(s);
							// System.out.println("ujn");
							break;
						}
						asd = asd.next;
					}
				}
				node1 = node1.next;
				wsx--;
			}
			if (count == 0) {
				return s;
			} else {
				return null;
			}
		}

		// int A[MAXUNM][MAXNUM];
		int chaxundanchibianhao(String x) {
			for (int i = 0; i < size; i++) {
				if (vertexLists[i].ch.equals(x)) {
					return i;
				}
			}
			return -1;
		}

		String Dijkstra( String tr) {
			int v1;
			int[] dist = new int[size];
			int[] prev = new int[size];
			boolean[] s = new boolean[size]; // �ж��Ƿ��Ѵ���õ㵽s������
			int n = size;
			String out="";
			String[] str =null;
			//try {
				str = tr.split("\\s+");
			/*} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			int v0 = chaxundanchibianhao(str[0]);
			if(v0==-1) {
				return "no this word";
			}
			for (int i = 0; i < n; i++) {
				Vertex node = vertexLists[v0];
				while (node.next != null) {
					if (node.next.ch.equals(vertexLists[i].ch)) {
						dist[i] = node.next.weight;
						// System.out.println(node.next.ch+" "+i);
						// System.out.println(dist[i]+"edc");
					}
					node = node.next;
				}
				// dist[i] = A[v0][i];
				s[i] = false; // ��ʼ��δ�ù��õ�
				if (dist[i] == 0) {
					prev[i] = -1;
					dist[i] = 100;
				} else {
					prev[i] = v0;
				}
			}
			dist[v0] = 0;
			s[v0] = true;
			for (int i = 1; i < n; i++) {
				int mindist = 100;
				int u = v0;// �ҳ���ǰδʹ�õĵ�j��dist[j]��Сֵ
				for (int j = 0; j < n; j++) {
					if ((!s[j]) && dist[j] < mindist) {
						u = j; // u���浱ǰ�ڽӵ��о�����С�ĵ�ĺ���
						mindist = dist[j];
					}
				}
				s[u] = true;
				for (int j = 0; j < n; j++) {
					Vertex node = vertexLists[u];
					while (node.next != null) {
						if (node.next.ch.equals(vertexLists[j].ch)) {
							if (!s[j]) {
								if (dist[u] + node.next.weight < dist[j]) 
									// ��ͨ���¼����u��·���ҵ���v0����̵�·��
								{
									dist[j] = dist[u] + node.next.weight; // ����dist
									prev[j] = u; // ��¼ǰ������
									// System.out.println(j+"tgb"+prev[j]+"wer"+dist[j]);
								}
							}
						}
						node = node.next;
					}
				}
			}
			if (str.length == 2) {
				v1 = chaxundanchibianhao(str[1]);
				if (dist[v1] == 100) {
					System.out.println("���ɴ�");
					return "null";
				}
				int longs = 0;
				String[] outpot = new String[size];
				if (v1 != v0) {
					outpot[longs] = vertexLists[v1].ch;
					longs++;
				}
				if (prev[v1] > -1) {
					int d = prev[v1];
					while (d != v0) {
						outpot[longs] = vertexLists[d].ch;
						longs++;
						d = prev[d];
					}
				}
				outpot[longs] = vertexLists[v0].ch;
				for (int j = longs; j >= 0; j--) {

					System.out.print(outpot[j] + " ");
					if(j!=0) {
					out=out+outpot[j]+" ";
					}
					else
					{
						out=out+outpot[j];
					}
				}
				System.out.println();
				return out;
			} else {
				/*for (int i = 0; i < size; i++) {
					int longs = 0;
					String[] outpot = new String[size];
					if (i != v0) {
						outpot[longs] = vertexLists[i].ch;
						longs++;
					}
					if (prev[i] > -1) {
						int d = prev[i];
						while (d != v0) {
							outpot[longs] = vertexLists[d].ch;
							longs++;
							d = prev[d];
						}
					}
					outpot[longs] = vertexLists[v0].ch;
					for (int j = longs; j >= 0; j--) {

						System.out.print(outpot[j] + " ");
					}
					System.out.println();
				}*/
						return "null";}
		}

		String queryBridgeWords(String word1,String word2) {
			String[] qiaojieci = new String[size];
			int count = 0;
			int i, j, sign1 = 1, sign2 = 1;
			for (i = 0; i < size; i++) {
				if (word1.equals(vertexLists[i].ch)) {
					sign1 = 0;
					break;
				}
			}
			for (j = 0; j < size; j++) {
				if (word2.equals(vertexLists[j].ch)) {
					sign2 = 0;
					break;
				}
			}
			if (sign1 == 1 && sign2 == 1) {
				System.out.println("No \"" + word1 + "\" and \"" + word2 + "\" in the graph!");
				
				return "No \"" + word1 + "\" and \"" + word2 + "\" in the graph!";
			}
			if (sign1 == 1) {
				System.out.println("No \"" + word1 + "\" in the graph!");
				return "No \"" + word1 + "\" in the graph!";
			}
			if (sign2 == 1) {
				System.out.println("No \"" + word2 + "\" in the graph!");
				return "No \"" + word2 + "\" in the graph!";
			}
			int x1 = chaxundanchibianhao(word1);
			sign1 = 1;
			sign2 = 0;
			int wsx = 0;
			Vertex node1 = vertexLists[x1];
			Vertex qaz = node1;
			while (qaz.next != null) {
				wsx++;
				qaz = qaz.next;
			}
			while (wsx != 0) {
				if (!(node1.next.ch.equals(word2))) {
					int x = chaxundanchibianhao(node1.next.ch);
					Vertex node2 = vertexLists[x];
					Vertex qwe = node2;

					while (qwe.next != null) {
						if (qwe.next.ch.equals(word2)) {
							sign1 = 0;
							qiaojieci[count] = node2.ch;
							count++;
							break;
						}
						qwe = qwe.next;
					}
				}
				node1 = node1.next;
				// System.out.print(wsx);
				wsx--;
			}
			if (sign1 == 1) {
				System.out.println("No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!");
				return "No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!";
			}
			String x="The bridge words from \"" + word1 + "\" to \"" + word2 + "\" are: ";
			if (count > 1) {
				System.out.print("The bridge words from \"" + word1 + "\" to \"" + word2 + "\" are: ");
				for (int k = 0; k < count - 1; k++) {
					System.out.print("\"" + qiaojieci[k] + "\",");
					x=x+"\"" + qiaojieci[k] + "\",";
				}
				System.out.print("and\"" + qiaojieci[count - 1] + "\".");
				x=x+"and\"" + qiaojieci[count - 1] + "\".";
				return x;
			} else {
				System.out.print("The bridge words from \"" + word1 + "\" to \"" + word2 + "\" are: " + "\""
						+ qiaojieci[0] + "\"");
				x=x+"The bridge words from \"" + word1 + "\" to \"" + word2 + "\" are: " + "\""+ qiaojieci[0] + "\"";
				return x;
			}
		

		}

		void generateNewText() {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] str = null;
			String s = "";
			try {
				str = br.readLine().split("\\s+");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.print(str.length);
			// for(int i=0;i<str.length;i++){
			// System.out.print(str[i] + "asd");
			// }
			// System.out.print(str.length);
			String[] q = new String[100];
			int newLength = str.length;
			int wer = 1;
			for (int i = 0; i < str.length - 1; i++) {
				s = queryBridgeWords1(str[i], str[i + 1]);
				int k = i;
				// System.out.print(s);
				// System.out.print("qwe");
				if (s != null) {
					newLength++;
					k++;
					String[] b = s.split("\\s+");
					Random random = new Random();
					int x = random.nextInt(b.length);
					// System.out.print(x+"rty");
					// q=new String [q.length+1];
					if (wer == 1) {
						for (int j = 0; j < i + 1; j++) {
							q[j] = str[j];
						}
						for (int j = i + 2; j < newLength; j++) {
							q[j] = str[j - 1];
						}
						q[i + 1] = b[x];
						wer = 0;
					} else {
						// System.out.println("tgb");
						for (int j = k + 2; j < newLength; j++) {
							q[j] = str[k];
							k++;
							// System.out.println(q[j]);
						}
						q[i + 2] = b[x];
					}

					for (int m = 0; m < newLength; m++) {
						// System.out.println(q.length);
						// System.out.print(q[m]+" ");
					}
				}
			}
			if (q[0] != null) {
				for (int v = 0; v < newLength; v++) {
					System.out.print(q[v] + " ");
				}
			} else {
				for (int v = 0; v < str.length; v++) {
					System.out.print(str[v] + " ");
				}
			}

		}

		void randomWalk() throws IOException {
			Random random = new Random();
			File file = new File("c:/Temp/Result.txt");
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);

			int x = random.nextInt(size - 1);
			// System.out.print(x+" ");
			int i = 0;
			String[] qwe = new String[size];
			qwe[0] = vertexLists[x].ch;
			fileWriter.write(qwe[0] + " ");
			fileWriter.flush();
			int node = x;
			System.out.print(vertexLists[node].ch + " ");
			while (vertexLists[node].next != null) {

				int wer = 0;
				Vertex s = vertexLists[node];
				while (s.next != null) {
					wer++;
					s = s.next;
				}

				// System.out.print(wer+"ert");
				Random qaz = new Random();
				Vertex q = vertexLists[node];
				node = qaz.nextInt(wer) + 1;
				for (int y = 0; y < node; y++) {
					q = q.next;
				}
				for (int j = 0; j <= i; j++) {
					// System.out.print( "tyu");
					if (q.ch.equals(qwe[j])) {
						// System.out.print("zxc");
						fileWriter.close();
						return;
					}
				}
				i++;
				qwe[i] = q.ch;
				fileWriter.write(qwe[i] + " ");
				fileWriter.flush();
				System.out.print(q.ch + " ");
				node = chaxundanchibianhao(q.ch);
			}
			fileWriter.close();
		}
	}

	public static void main(String[] args) throws IOException {
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
		// pG = new ListNDG(s, edges);
		// pG.print(); // ��ӡͼ
		System.out.println("��ѡ���ܣ���������2��7��2��չʾ����ͼ��");
		System.out.println("3����ѯ�ŽӴʡ�");
		System.out.println("4������bridge word�������ı���");
		System.out.println("5��������������֮������·����");
		System.out.println("6��������ߡ�");
		System.out.println("7���˳�");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();	
		Proba a = new Proba();
		do {
			//int stop = 0;
			switch (option) {
			case 2:
				System.out.println("��������ͼ");
				pG.print();

				a.showgraph(pg1, text, textSize, edges);
				break;
			case 3:
				Scanner sc1 = new Scanner(System.in);
				System.out.println("��������Ҫ��ѯ�ĵ�һ�����ʣ�");
				String word1 = sc1.nextLine();
				System.out.println("��������Ҫ��ѯ�ĵڶ������ʣ�");
				String word2 = sc1.nextLine();
				sc1.close();
				pG.queryBridgeWords(word1,word2);
				break;
			case 4:
				pG.generateNewText();
				break;
			case 5:
				String[] outpot = new String[100];
				String out;
				System.out.println("��������Ҫ��ѯ�ĵ��ʣ�");
				BufferedReader tr = new BufferedReader(new InputStreamReader(System.in));
				String x=tr.readLine();
				out = pG.Dijkstra(x);
				outpot = out.split("\\s+");
				break;
			case 6:
				pG.randomWalk();
				break;
			case 7:
			//	int stop = 1;
				break;
			}
			System.out.println("��ѡ���ܣ���������2��7��2��չʾ����ͼ��");
			System.out.println("3����ѯ�ŽӴʡ�");
			System.out.println("4������bridge word�������ı���");
			System.out.println("5��������������֮������·����");
			System.out.println("6��������ߡ�");
			System.out.println("7���˳�");

			option = sc.nextInt();
		} while (option != 7);
		 sc.close();
	}
}
