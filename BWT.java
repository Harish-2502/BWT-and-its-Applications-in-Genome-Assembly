package misics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BWT {
	static List<String> A = new ArrayList<String>();
	static List<String> A1;
	static List<String> A2 = new ArrayList<String>();
	static String S = "";
	static String b = "";
	static String c = "";
	static String tem = "";
	static String data = "panamabananas";
	static List<String> temp = new ArrayList<String>();
	 private static  String repeatString(String s,int count){//function used to repeat a string given int times.
		    StringBuilder r = new StringBuilder();
		    for (int i = 0; i < count; i++) {
		        r.append(s);
		    }
		    return r.toString();
		}
public static List<String> unsorted(String a){//This function is used to read a file which contains the Dna sequence and it appends the string with a $ symbol and finally returns us a list of cyclic rotations of that sequence. 
	
	
	String ch = "$";
	a = data + ch;
	A.add(a);
	for(int i = 0;i<a.length();i++) {
		//temp = A.get(0).substring(a.length(),a.length()+1);
		A.add(A.get(i).substring(A.get(i).length()-1,A.get(i).length())+A.get(i).substring(0,A.get(i).length()-1));
		}
	A.remove(a);
	System.out.println("The List of complete rotations of the sequence are \n");
	System.out.println(A);
	return A;
	}
	public static List<String> sorted(List<String> A){//This function sorts the above list in a lexicographic order
	List<String> sorted = unsorted(data);
	String Temp = "";
	A1 = A;
	for (int i=0;i<A.size();i++) {//bubble sort
		for (int j =i+1;j<A.size();j++) {
			
			if(A1.get(i).compareTo(A1.get(j))>0) {
			
				 Temp = A1.get(i);
				A1.set(i, A1.get(j));
				A1.set(j, Temp);
			}
		}
	}
	System.out.println("The List of complete rotations of the sequence after sorting are \n");
	System.out.println(A1);
	return A1;
	}
	
	public static String bwt(List<String> A){
		List<String> bwt = sorted(A);
	for(int i = 0;i<A1.size();i++) {
		S = S + (A1.get(i).substring(A1.get(i).length()-1, A1.get(i).length()));
		
	}
	System.out.println("The bwt sequence is \n");
	System.out.println(S);
	try {
	      FileWriter myWriter = new FileWriter("Encoded.txt");
	      myWriter.write(S);
	      myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }

	return S;
	
}

	public static String encode(String b) {
		String encode = bwt(A);
		b = S;
		int i = 0;

		while (i <= b.length()-1) {
		    int count = 1;
		   // ch = message[i] 
		   int j = i ;
		    while (j < b.length()-1) {
		        if (b.charAt(j)==b.charAt(j+1)) {
		            count = count+1;
		            j = j+1;
		        }
		        else {
		            break;
		        }
		    }
		    tem = tem + (count) + b.charAt(i);
		    i = j+1;
		    }
		System.out.println("The Encoded bwt sequence is \n");
		System.out.println(tem);
		return tem;
	}
	public static String decode(String c) {
		String decode = encode(b);
		c = tem;
		String Temp,TEMP = "";
		for(int k = 0;k<c.length();k++) {
			A2.add(c.substring(k, k+1));
		}
		for(int g = 0;g<A2.size();g++) {
			if(Character.isDigit(A2.get(g).charAt(0))) {
				Temp = repeatString(A2.get(g+1),(Integer.parseInt(A2.get(g))-1));
				A2.set(g,Temp);
			}
			TEMP = TEMP + A2.get(g);
		}
		//System.out.println("The Decoded bwt sequence is \n");
		//System.out.println(TEMP);
		return TEMP;
	}
	public static void ibwt(String a){
		String ibwt = decode(c);
		a = S;
		 List<String> C = new ArrayList<String>();
		for(int i = 0;i<a.length();i++) {
			C.add(a.substring(i, i+1));
			temp.add(a.substring(i, i+1));
			
		}
		Collections.sort(temp);
		for(int i = 0;i<C.size()-1;i++) {
		  for(int j = 0;j<C.size();j++) {
			temp.set(j,C.get(j)+temp.get(j));
			}
		Collections.sort(temp);
		}
		System.out.println("The inverted(sorted) list from the bwt sequence is");
		System.out.println(temp);
		
	}


public static void main(String[] args) {
BWT x = new BWT();
ibwt(S);
//System.out.println(A1==temp);
//System.out.println("The initial sequence is");
//System.out.println(temp.get(0).substring(1, temp.get(0).length()));
File myObj1 = new File("C:\\Users\\haris\\eclipse-workspace\\misass\\encoded.txt");
System.out.println("File size in bytes " + myObj1.length() + " bytes");

}
}