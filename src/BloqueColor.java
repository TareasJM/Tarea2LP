import java.util.Random;
public class BloqueColor extends Bloque{
	
	private String color;
	
	/******** Funcion: BloqueColor ********************************************************************
	Descripcion: Constructor que genera un bloque de color aleatorio
	Parametros: ninguno
	Retorno: Bloque
	***************************************************************************************************/
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

	/******** Funcion: getColor ***********************************************************************
	Descripcion: retorna el color de este bloque
	Parametros: ninguno
	Retorno: String
	***************************************************************************************************/
	public String getColor(){
		return this.color;
	}

	/******** Funcion: DestruirBloque *****************************************************************
	Descripcion: destruye el bloque y setea el color a * para su posterior eliminación en el tablero
	Parametros: ninguno
	Retorno: entero
	***************************************************************************************************/
	public int DestruirBloque(){
		this.color = "*";
		return 0;
	}

}