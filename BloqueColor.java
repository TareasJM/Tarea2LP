import java.util.Random;
public class BloqueColor extends Bloque{
	
	private String color;
	
	public BloqueColor(){
		Random rand = new Random(); 
		int number = rand.nextInt(5);
		if (number == 0) {
			this.color = "R";
		}
		else if (number == 1) {
			this.color = "B";
		}
		else if (number == 2) {
			this.color = "O";
		}
		else if (number == 3) {
			this.color = "G";
		}
		else{
			this.color = "Y";
		}
	}

	public String getColor(){
		return this.color;
	}

}