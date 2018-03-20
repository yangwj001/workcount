package wc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileWriter;


public class workcount {
	public static void main(String[] args) throws Exception {
		 try {if (args.length == 0) {                                    
	           System.out.println("without arguments！");  
	           return;  }
		      String name =args[args.length-1];   
		      String except= null;
			  String output="result.txt";
			  String str = System.getProperty("line.separator");
			  int Emptyline = 0;
			  int Codeline = 0;
			  int Explainline =0;
			  int Codeflag = 0;
			  int CharNum = 0;
			  int wordNum = 0;
			  int lineNum = 0;
			   for(int m=0;m<args.length-1;m++){
			    	  
				  if(args[m].equals("-o")) {
					  output=args[m+1];  }
				  if(args[m].equals("-e")) {
					  
					  except=args[m+1];  }
				  }
			  FileInputStream fis = new FileInputStream(name);
			  BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			 
			  while(br.read()!=-1)
			  {
			   String s = br.readLine();
			   CharNum += s.length();
			   if (s.length()==0) Emptyline++;
			   String[] aa=s.split(" |,");
			   
			   
			   lineNum++;
			   for (int i = 0 ; i <aa.length ; i++ ) {
                      
				      if(!(aa[i] == null || aa[i].isEmpty()))
				      {wordNum++;
				       Codeflag++;
				       
				       if(!(except==null||except.isEmpty()))
				       {  FileInputStream ff = new FileInputStream(except);
						  BufferedReader bc = new BufferedReader(new InputStreamReader(ff));
						  String ss = bc.readLine();
				    	   String[] bb=ss.split(" ");
				    	   for(int k=0;k<bb.length;k++)
				    	   {
				    		   if(aa[i].equals(bb[k])) {wordNum--;}
				    	   }
				       }
				      }
				      if(aa[i].equals("//")) {Explainline++;}
				      
				    }
			   if(Codeflag>1) lineNum++;
			   Codeflag=0;
			  }
			      
			   FileWriter fw = new FileWriter(output);
			  for(int m=0;m<args.length-1;m++)
			  {   
				  if (args[m].equals("-c")){ 
					  fw.write("字符数： "+CharNum);
			          fw.write(str);
			          }
				  if (args[m].equals("-w")){
			          fw.write("单词数： "+wordNum);
			          fw.write(str);
			          }
				  if (args[m].equals("-l")) {
			          fw.write("行数：    "+lineNum);
			          fw.write(str);}
				  if(args[m].equals("-a")) {
					  fw.write("代码行数：    "+Codeline);
			          fw.write(str);
			          fw.write("空行数：    "+Emptyline);
			          fw.write(str);
			          fw.write("注释行：    "+Explainline);
			          fw.write(str);
				  }
			  }
			  fw.close();
			  br.close();
			  
		 }
		 catch(FileNotFoundException e){
			 System.out.println("don't find file!");
		 }
	}

}
