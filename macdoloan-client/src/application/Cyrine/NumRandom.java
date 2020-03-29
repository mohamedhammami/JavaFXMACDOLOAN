package application.Cyrine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NumRandom{

	boolean exist;
	boolean repeat;
	long number;
	FileReader fr;
	String StringNumber;
	static File f = new File("src/main/resources/RandomNumbers.txt");

	public String ValidUniqueNumber() {
		exist = false;
		repeat = true;
		number = (long) Math.floor(Math.random() * 9000_000_000_000L) + 1000_000_000_000L;
		StringNumber = "" + number;

		while (repeat == true) {

			while (StringNumber.length() != 13) {
				number = (long) Math.floor(Math.random() * 9000_000_000_000L) + 1000_000_000_000L;
				StringNumber = "" + number;
				System.out.println("chacun " + StringNumber);
			}

			try {
				fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				try {
					String line = br.readLine();
					while (line != null) {
						if (StringNumber.equals(line)) {
							exist = true;
							break;
						} else
							exist = false;
						System.out.println("line = " + line);
						line = br.readLine();
					}
					br.close();
					fr.close();
				} catch (IOException e) {
					System.out.println("PROBLEM READING THE TEXTE FILE: LINES ");
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				System.out.println("PROBLEME IN READING FILE TEXT");
				e.printStackTrace();
			}
			System.out.println("exist= " + exist);
			if (exist == false) {
				repeat = false;
			} else {
				number = (long) Math.floor(Math.random() * 9000_000_000_000L) + 1000_000_000_000L;
				StringNumber = "" + number;
			}
		}

		return StringNumber;
	}
	
	public void Writenumber (String uniquenumber){
		try {
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bf = new BufferedWriter(fw);

			bf.write(uniquenumber + "\n");
			bf.flush();

			bf.close();
		} catch (IOException e) {
			System.out.println("hereeeeeeee");
			e.printStackTrace();
		}
	}

}	    




