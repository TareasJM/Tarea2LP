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

	/******** Funcion: Nombre_Funcion **************
	Descripcion: Funcion en cargada de...
	Parametros:
	n1 entero
	n2 entero
	Retorno: Retorna...
	************************************************/
	public int Hability(){
		return habilidad.Hability();
	}

	/******** Funcion: Nombre_Funcion **************
	Descripcion: Funcion en cargada de...
	Parametros:
	n1 entero
	n2 entero
	Retorno: Retorna...
	************************************************/
	public int DestruirBloque(){
		return Hability();
	}
}