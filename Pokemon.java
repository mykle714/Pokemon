

import java.util.ArrayList;

/*
 * This class contains the data for a pokemon in a more code friendly format.
 * As opposed to digging through a text file for everything.
 * Since the Pokemon class is more of an outline for each pokemon, rather than
 * the actualy pokemon you will use to battle (this will be a small text segment
 * that showdown reads), this class will have no methods. The game modes will be the
 * ones generating the sets and selecting the pokemon.
 * 
 * Ideas:
 * have a dialogue box asking for information about the pokemon such as typing
 * for the sake of later game modes. This box should then modify the original text
 * file to store the data.
 */
public class Pokemon {
	public String name;
	public ArrayList<String> possibleMoves;
	public String tier;
	public ArrayList<String> possibleAbilities;
	public String[] type;
	public int num;
	public int []stats;
	
	
	public Pokemon(){
		type = new String[2];
		stats= new int[6];
	}
	public Pokemon(String name) {
		this.name = name;
		type = new String[2];
		stats= new int[6];
	}
	
	public Pokemon(String name, ArrayList<String> possible, String tier) {
		this.name = name;
		this.possibleMoves = possible;
		this.tier = tier;
		type = new String[2];
		stats = new int[6];
	}
}
