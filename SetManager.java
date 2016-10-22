import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

/* Date started: 10/19/16
 * 
 * Currently working on:
 * Pokemon class & convert()
 * 
 * Warnings:
 * Set the static path variable to your project folder.
 * 
 * Ideas for the future:
 * Trimming the standard files so they don't have retarded data. (worth while?)
 */

public abstract class SetManager {
	
	//static String path = "C:/Users/Micha/Documents/Jafa/Pokemon/";
	static String path = "C:/Users/gds14_000/Downloads/programming/programming/Pokemon/src/";
	public static String truncate(String path) {
		String line = "";
		String p = path.substring(0, path.length()-4) + "2" + ".txt";
		int counter = 0;
		try {
			FileReader FR = new FileReader(path);
			BufferedReader BR = new BufferedReader(FR);
			FileWriter FW = new FileWriter(p);
			BufferedWriter BW = new BufferedWriter(FW);
			
			while((line = BR.readLine()) != null) {
				if(!line.subSequence(0, 1).equals("\t")) {
					System.out.println("Could not truncate!\n" + p + " is corrupt!");
					System.out.println("broke at " + counter + "\nv this line is lying!!! v");
					break;
				} else {
					BW.write(line.substring(1) + System.lineSeparator());
				}
				counter++;
			}
			
			System.out.println("Truncate Successful!");
			BR.close();
			BW.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return p;
	}
	
	//generates a preliminary text file for the set of allowed mons in a game mode
	public static void newSet(String p, String name) {
		String path = SetManager.path + "sets/";
		path = path + name + ".txt";
		String line = "";
		ArrayList<String> mistakes = new ArrayList<String>();
		
		try {
			FileReader FR = new FileReader(p);
			BufferedReader BR = new BufferedReader(FR);
			FileWriter FW = new FileWriter(path);
			BufferedWriter BW = new BufferedWriter(FW);
			InputStreamReader ISR = new InputStreamReader(System.in);
			BufferedReader input = new BufferedReader(ISR);
			
			String pokemon;
			String left = "in";
			String right = "out";
			String in; //input
			
			while(line != null) {
				line = BR.readLine();
				if(line.substring(0,1).equals("\t") || line.substring(0,1).equals("}")) {		//finds pokemon names
					continue;
				} else {
					pokemon = line.substring(0,line.length()-3);
				}
				System.out.print(left);
				for(int i=0; i < 2 - left.length()/8; i++) {		//im slick
					System.out.print("\t");
				}
				System.out.print(right);
				for(int i=0; i < 2 - right.length()/8; i++) {
					System.out.print("\t");
				}
				System.out.print(pokemon);
				
				in = input.readLine();
				//System.out.println();
				
				if(in.equals("w"))
					mistakes.add(pokemon);

				switch(in) {
				
				case "a":
					left = pokemon;
					right = " ";
					while(!line.substring(0,1).equals("}")) {
						 BW.write(line + System.lineSeparator());
						 line = BR.readLine();
					}
					BW.write(line + System.lineSeparator());
					break;
					
				case "d":
					left = " ";
					right = pokemon;
					line = BR.readLine();
					break;
					
				default:
					System.out.println("a = in\nd = out\nw = mistake");
					break;
				}
			}
			BW.close();
			BR.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Pokemon> convert(String mode) {
		//String path = SetManager.path + "sets/";
		//path = path + mode + ".txt";
		String path = SetManager.path+"Pokedex.txt";
		String line = "";
		ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
		
		try {
			FileReader FR = new FileReader(path);
			BufferedReader BR = new BufferedReader(FR);
			Pokemon temp = null;
			line = BR.readLine();
			while(line != null) {
				
				System.out.println(line);
				if(line.charAt(line.length()-1) == '{' ){
					temp = new Pokemon();
				}
				else if(line.charAt(2) == 'n'){
					temp.num = Integer.parseInt(line.substring(7,line.length()-1));
				}
				else if(line.charAt(2) == 's'){
					temp.name = line.substring(12,line.length()-2);
				}
				else if(line.charAt(2) == 't'){
					String types = line.substring(11,line.length()-3);
					types = types.replace('\"', ' ');
					if(types.contains(",")){
						temp.type[0] = types.split(",")[0].trim();
						temp.type[1] = types.split(",")[1].trim();
					}
					else{
						temp.type[0] = types.split(",")[0].trim();
					}
				}
				else if(line.charAt(2) == 'a'){
					String abilites = line.substring(13).replaceAll("(.:)", "");
					abilites = abilites.replaceAll("[{}\"]", "");
					String [] a = abilites.split(",");
					temp.possibleAbilities = new ArrayList<String>();
					for (int i = 0; i < a.length; i++) {
						temp.possibleAbilities.add(a[i].trim());
						System.out.println(temp.possibleAbilities.get(i));
					}
					
				}
				else if(line.charAt(line.length()-1) == ','&& line.length() == 3){
					pokemon.add(temp);
					temp = new Pokemon();
				}
				else if(line.substring(2,8).equals("baseSt")){
					String stats = line.replaceAll("([atk :hpdefs}bS{])", "");
					String [] a = stats.split(",");
					for(int i=0;i!=a.length;i++){
						temp.stats[i] = Integer.parseInt(a[i].trim());
					}
				}
				
				if(line.substring(0,1) != "\t" || line.substring(0,1) != "}") {
					//Pokemon temp = new Pokemon(line.substring(0,line.length()));
					
					//System.out.println(temp.name);
					
//					while(line.substring(0,6) != "randomB") { //finds the randomBattleMoves
//						line = BR.readLine();
//					}
					
					
				}
				line = BR.readLine();
			}
		}catch (Exception e){
			e.printStackTrace();
			}
		return pokemon;
	}
	
	public static void main(String args[]) {
		String original = SetManager.path + "Pokemon.txt";
		//String orig = SetManager.truncate(original); //drops the first line of tabs
		//SetManager.newSet(orig,"Randoms");
		SetManager.convert("Randoms");
	}
}
