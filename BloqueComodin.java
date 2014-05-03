import java.util.Random;
public class BloqueComodin extends Bloque implements HabilityBehavior{

	private HabilityBehavior habilidad;

	public BloqueComodin(){
		Random rand = new Random(); 
		int number = rand.nextInt(2);
		if (number == 0) {
			this.habilidad = new HabilityT1();
		}
		else{
			this.habilidad = new HabilityT2();
		}
	}

	public int Hability(){
		return habilidad.Hability();
	}

	public int DestruirBloque(){
		return Hability();
	}
}