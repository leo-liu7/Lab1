package labs1;

import java.io.File;

import labs1.ReadFromFile.ListNDG;
public class Proba
{
    void showgraph(ReadFromFile pg1,String[] text,int textSize,String[][] edges)
   { 
    	ListNDG pG = pg1.new ListNDG(text,textSize,edges);
      //Proba p = new Proba();
      this.start(pG,null);
//      p.start2();
   }

/**
    * Construct a DOT graph in memory, convert it
    * to image and store the image in the file system.
    */
    void start(ListNDG pG,String[] outpot)
   {
      GraphViz gv = new GraphViz();
      gv.addln(gv.start_graph());
      for(int i=0;i<pG.size;i++){
    	  ReadFromFile.ListNDG.Vertex node=pG.vertexLists[i];
    	  if(outpot==null){
    		  ReadFromFile.ListNDG.Vertex x=pG.vertexLists[i];
    	  while(x.next!=null){
    		  
    		  gv.addln(node.ch+" -> "+x.next.ch);
    		  x=x.next;
    	  }}
    	  else{
    		  int count,sign=0;
    		  for(count=0;count<100;count++){
    			  if(outpot[count]==null)
    				  break;
    		  }
    		  ReadFromFile.ListNDG.Vertex x=node; 
    		  while(x.next!=null){
    			  sign=0;
        		  for(int j=count-1;j>0;j--){
        			  if(node.ch.equals(outpot[j])&&x.next.ch.equals(outpot[j-1])){
        				  gv.addln(node.ch+" -> "+x.next.ch+"[color=\"red\"];");
                		  x=x.next;
                		  sign=1;
                		  break;
        			  }
        		  }
        		  if(sign==0){
        		  gv.addln(node.ch+" -> "+x.next.ch+";");
        		  x=x.next;}
        		  
        		  
        	  }
    	  }
      }
      gv.addln(gv.end_graph());
      System.out.println(gv.getDotSource());
      
      String type = "gif";
//      String type = "dot";
//      String type = "fig";    // open with xfig
//      String type = "pdf";
//      String type = "ps";
//      String type = "svg";    // open with inkscape
//      String type = "png";
//      String type = "plain";
      File out = new File("c:/Temp/out." + type);   // Linux
//      File out = new File("c:/eclipse.ws/graphviz-java-api/out." + type);    // Windows
      gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
   }
   
   /**
    * Read the DOT source from a file,
    * convert to image and store the image in the file system.
    */
   private void start2()
   {
 //     String dir = "/home/jabba/eclipse2/laszlo.sajat/graphviz-java-api";     // Linux
 //     String input = dir + "/sample/simple.dot";
    String input = "c:/eclipse.ws/graphviz-java-api/sample/simple.dot";    // Windows
    
    GraphViz gv = new GraphViz();
    gv.readSource(input);
    System.out.println(gv.getDotSource());
     
      String type = "gif";
//    String type = "dot";
//    String type = "fig";    // open with xfig
//    String type = "pdf";
//    String type = "ps";
//    String type = "svg";    // open with inkscape
//    String type = "png";
//      String type = "plain";
    File out = new File("c:/Temp/simple." + type);   // Linux
//    File out = new File("c:/eclipse.ws/graphviz-java-api/tmp/simple." + type);   // Windows
    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
   }
}

