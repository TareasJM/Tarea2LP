import java.util.Random;
public class BloqueColor extends Bloque{
	
	private String color;
	
	/******** Funcion: Nombre_Funcion **************
	Descripcion: Funcion en cargada de...
	Parametros:
	n1 entero
	n2 entero
	Retorno: Retorna...
	************************************************/
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

	/******** Funcion: Nombre_Funcion **************
	Descripcion: Funcion en cargada de...
	Parametros:
	n1 entero
	n2 entero
	Retorno: Retorna...
	************************************************/
	public String getColor(){
		return this.color;
	}

	/******** Funcion: Nombre_Funcion **************
	Descripcion: Funcion en cargada de...
	Parametros:
	n1 entero
	n2 entero
	Retorno: Retorna...
	************************************************/
	public int DestruirBloque(){
		this.color = "*";
		return 0;
	}

}