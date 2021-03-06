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

	/******** Funcion:Hability ***************************************************
	Descripcion: llama a la habilidad de la interfaz HabilityBehavior
	Parametros: ninguno
	Retorno: entero
	******************************************************************************/
	public int Hability(){
		return habilidad.Hability();
	}

	/******** Funcion: DestruirBloque ********************************************
	Descripcion: Destruye el bloque comodin y retorna el valor de la habilidad
	Parametros: ninguno
	Retorno: entero
	******************************************************************************/
	public int DestruirBloque(){
		return Hability();
	}
}