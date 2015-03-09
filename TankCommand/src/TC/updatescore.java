package TC;

import java.io.*;
import java.util.Scanner;

public class updatescore {

	public int score; 
	public int highestscore; 
	String line; 
	File file = new File("blah.txt");

	public updatescore(int score) {
		try {
			if(file.createNewFile()){
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw); 
			bw.write("2");
			bw.close();}
			else {}; 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.score=score; 
	}
	
	
	public void changescore(int score){
		this.score=score; 
	}
	
	public void getscore(){
		try {
            Scanner scanner = new Scanner(file);
            line = scanner.nextLine();
            //System.out.println(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
		highestscore = Integer.parseInt(line);
	}
	
	public void uploadscore(){
		if(score>highestscore){
			try {
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw); 
				bw.write(String.valueOf(score));
				bw.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
	}

}
