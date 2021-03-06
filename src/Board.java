import java.io.Console;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;


public class Board{

	private Bloque[][] board;
	private int emptyBlocks;
	private int[] meta;
	private int time;
	private Interface window;
	private int col,row;
	private boolean painting;
	private boolean initializing;
	private boolean consoleMode;

	public Board(boolean cm){
		this.meta = new int[] {0,0,0,0,0};
		this.board = new Bloque[15][15]; //[y][x] -> [rows][cols]
		for (int row=0; row<15; row++) {
			for (int col=0; col<15; col++) {
				setBlock(row,col,null);
			}
		}
		this.emptyBlocks = 225;
		this.row = this.col = -1;
		this.painting = false;
		this.time = 0;
		this.consoleMode = cm;
		if(!cm){
			this.window = new Interface();
			handleClick(this.window.main, true);
		}
		this.initializing = true;
	}

	/******** Funcion: checkMoves ***********************************************************************************************
	Descripcion: revisa si queda algun movimiento posible en el tablero
	Parametros: ninguno
	Retorno: true-> si hay movimientos, false -> si no
	*****************************************************************************************************************************/
	public boolean checkMoves()
	{
		for(int row = 0; row < 15; row++)//iz a derecha ultimo
		{

			for(int col = 0; col < 13; col ++)
			{

				Bloque temp = getBlock(row,col);
				Bloque tempc1 = getBlock(row,col+1);
				Bloque tempc2 = getBlock(row+1,col+2);

				if(temp instanceof BloqueColor && tempc1 instanceof BloqueColor && tempc2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempc1;
					BloqueColor temp4 = (BloqueColor)tempc2;	

					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}

		for(int row = 0; row < 15; row++)//derecha a iz ultimo 
		{

			for(int col = 14; col < 1; col--)
			{

				Bloque temp = getBlock(row,col);
				Bloque tempc1 = getBlock(row,col-1);
				Bloque tempc2 = getBlock(row+1,col-2);

				if(temp instanceof BloqueColor && tempc1 instanceof BloqueColor && tempc2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempc1;
					BloqueColor temp4 = (BloqueColor)tempc2;	

					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}

		for(int row = 0; row < 14; row++)//derecha a iz medio
		{

			for(int col = 14; col < 1; col--)
			{

				Bloque temp = getBlock(row,col);
				Bloque tempc1 = getBlock(row+1,col-1);
				Bloque tempc2 = getBlock(row,col-2);

				if(temp instanceof BloqueColor && tempc1 instanceof BloqueColor && tempc2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempc1;
					BloqueColor temp4 = (BloqueColor)tempc2;	

					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}

		for(int col = 0; col < 15; col++)//arriba abajo ultimo iz der
		{	
			for(int row = 0; row < 13; row++)
			{	
				Bloque temp = getBlock(row,col);
				Bloque tempr1 = getBlock(row+1,col);
				Bloque tempr2 = getBlock(row+2,col+1);

				if( temp instanceof BloqueColor && tempr1 instanceof BloqueColor && tempr2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempr1;
					BloqueColor temp4 = (BloqueColor)tempr2;
				
					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}

		for(int col = 14; col < 0; col--)//arriba abajo ultimo der iz
		{	
			for(int row = 0; row < 13; row++)
			{	
				Bloque temp = getBlock(row,col);
				Bloque tempr1 = getBlock(row+1,col);
				Bloque tempr2 = getBlock(row+2,col+1);

				if( temp instanceof BloqueColor && tempr1 instanceof BloqueColor && tempr2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempr1;
					BloqueColor temp4 = (BloqueColor)tempr2;
				
					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}

		for(int col = 0; col < 14; col++)//arriba abajo medio iz der
		{	
			for(int row = 0; row < 13; row++)
			{	
				Bloque temp = getBlock(row,col);
				Bloque tempr1 = getBlock(row+1,col+1);
				Bloque tempr2 = getBlock(row+2,col);

				if( temp instanceof BloqueColor && tempr1 instanceof BloqueColor && tempr2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempr1;
					BloqueColor temp4 = (BloqueColor)tempr2;
				
					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}

		for(int col = 14; col < 0; col++)//arriba abajo medio der iz
		{	
			for(int row = 0; row < 13; row++)
			{	
				Bloque temp = getBlock(row,col);
				Bloque tempr1 = getBlock(row+1,col+1);
				Bloque tempr2 = getBlock(row+2,col);

				if( temp instanceof BloqueColor && tempr1 instanceof BloqueColor && tempr2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempr1;
					BloqueColor temp4 = (BloqueColor)tempr2;
				
					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}

		for(int col = 0; col < 15; col++)//abajo arriba ultimo der iz
		{	
			for(int row = 14; row < 1; row--)
			{	
				Bloque temp = getBlock(row,col);
				Bloque tempr1 = getBlock(row-1,col);
				Bloque tempr2 = getBlock(row-2,col+1);

				if( temp instanceof BloqueColor && tempr1 instanceof BloqueColor && tempr2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempr1;
					BloqueColor temp4 = (BloqueColor)tempr2;
				
					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}

		for(int col = 14; col < 0; col--)//abajo arriba ultimo iz der vertical
		{	
			for(int row = 14; row < 1; row--)
			{	
				Bloque temp = getBlock(row,col);
				Bloque tempr1 = getBlock(row-1,col);
				Bloque tempr2 = getBlock(row-2,col+1);

				if( temp instanceof BloqueColor && tempr1 instanceof BloqueColor && tempr2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempr1;
					BloqueColor temp4 = (BloqueColor)tempr2;
				
					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}

		for(int col = 0; col < 13; col++)//abajo arriba medio iz der vertical
		{	
			for(int row = 14; row < 0; row--)
			{	
				Bloque temp = getBlock(row,col);
				Bloque tempr1 = getBlock(row-1,col+1);
				Bloque tempr2 = getBlock(row,col+2);

				if( temp instanceof BloqueColor && tempr1 instanceof BloqueColor && tempr2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempr1;
					BloqueColor temp4 = (BloqueColor)tempr2;
				
					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}


		for(int col = 0; col < 13; col++)//abajo arriba ultimo iz der horizontal
		{	
			for(int row = 14; row < 1; row--)
			{	
				Bloque temp = getBlock(row,col);
				Bloque tempr1 = getBlock(row,col+1);
				Bloque tempr2 = getBlock(row-1,col+2);

				if( temp instanceof BloqueColor && tempr1 instanceof BloqueColor && tempr2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempr1;
					BloqueColor temp4 = (BloqueColor)tempr2;
				
					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}

		for(int col = 14; col < 1; col--)//abajo arriba ultimo der iz horizontal
		{	
			for(int row = 14; row < 1; row--)
			{	
				Bloque temp = getBlock(row,col);
				Bloque tempr1 = getBlock(row,col-1);
				Bloque tempr2 = getBlock(row-1,col-2);

				if( temp instanceof BloqueColor && tempr1 instanceof BloqueColor && tempr2 instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					BloqueColor temp3 = (BloqueColor)tempr1;
					BloqueColor temp4 = (BloqueColor)tempr2;
				
					if( temp2.getColor().equals(temp3.getColor()) && temp2.getColor().equals(temp4.getColor()))
						{
							return true;
						}
				}
			}
		}
		return false;

	}

	/******** Funcion: setBlock **************
	Descripcion: se encarga de setear un bloque a cierta posición del tablero
	Parametros:
	row entero
	col entero
	bloque Bloque
	Retorno: void
	*****************************************************************************************************************************/
	public void setBlock(int row, int col, Bloque bloque){
		this.board[row][col] = bloque;
	}

	/******** Funcion: getBlock **************
	Descripcion: retorna el bloque en cierta posición del tablero
	Parametros:
	row entero
	col entero
	Retorno: Bloque
	*****************************************************************************************************************************/
	public Bloque getBlock(int row, int col){
		return this.board[row][col];
	}

	/******** Funcion: getEmptyBlocks **************
	Descripcion: retorna el número de bloques vacios en el tablero
	Parametros: ninguno
	Retorno: entero
	*****************************************************************************************************************************/
	public int getEmptyBlocks(){
		return this.emptyBlocks;
	}

	/******** Funcion: getDone **************
	Descripcion: verifica si se ha alcanzado el número de bloques a destruir
	Parametros: ninguno
	Retorno: boolean: true -> terminado, false-> aun no
	*****************************************************************************************************************************/
	public boolean getDone(){
		for (int i = 0; i<5; i++) {
			if (this.meta[i]>0){
				return false;
			}
		}
		return true;
	}

	/******** Funcion: getMeta **************
	Descripcion: retorna cuantos bloques faltan para alcanzar el objetivo
	Parametros:
	Retorno: arreglo de enteros, {rojo,naranjo,azul,verde,amarillo}
	*****************************************************************************************************************************/
	public int[] getMeta(){
		return this.meta;
	}

	/******** Funcion: setMeta **************
	Descripcion: setea los bloques a destruir
	Parametros: 
	meta arreglo de enteros: {rojo,naranjo,azul,verde,amarillo}
	Retorno: void
	*****************************************************************************************************************************/
	public void setMeta(int[] meta){
		this.meta = meta;
	}

	/******** Funcion: getTime **************
	Descripcion: Funcion en cargada de...
	Parametros:
	n1 entero
	n2 entero
	Retorno: Retorna...
	*****************************************************************************************************************************/
	public int getTime(){
		return this.time;
	}

	/******** Funcion: setTime **************
	Descripcion: Funcion en cargada de...
	Parametros:
	n1 entero
	n2 entero
	Retorno: Retorna...
	*****************************************************************************************************************************/
	public void setTime(int time){
		this.time = time;
	}

	/******** Funcion: getConsoleMode **************
	Descripcion: retorna si esta en modo consola o no
	Parametros:ninguno
	Retorno: Boolean
	*****************************************************************************************************************************/
	public boolean getConsoleMode(){
		return this.consoleMode;
	}

	/******** Funcion: setConsoleMode **************
	Descripcion: setea si se activa el modo consola.
	Parametros:
	cm boolean
	Retorno: void
	*****************************************************************************************************************************/
	public void setConsoleMode(boolean cm){
		this.consoleMode = cm;
	}

	/******** Funcion: showBoard **************
	Descripcion: actualiza el tablero en pantalla
	Parametros:
	clear entero: cuando imprime en consola, setea si limpiar la consola antes de imprimir o no
	Retorno: vois
	*****************************************************************************************************************************/
	public void showBoard(int clear){
		painting = true;
		
		if (this.getConsoleMode()) {	
			if (clear > 0) {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}else{
				System.out.print("\n");
			}
			System.out.println("   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4\n");
			for (int row=0; row<15; row++) {
				System.out.print(row%10+"  ");
				for (int col=0; col<15; col++) {

					Bloque temp = getBlock(row,col);
					if (temp == null) {
						System.out.print("  ");
					}
					else if( temp instanceof BloqueColor)
					{
						BloqueColor temp2 = (BloqueColor)temp;
						System.out.print(temp2.getColor()+" ");
					}
					else if (temp instanceof BloqueComodin) 
					{
						System.out.print("& ");
					}
				}
				System.out.println(" "+row%10);
			}
			System.out.println("\n   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4");

			System.out.print("Te quedan R: "+this.meta[0]);
			System.out.print(", B: "+this.meta[1]);
			System.out.print(", O: "+this.meta[2]);
			System.out.print(", G: "+this.meta[3]);
			System.out.println(", Y: "+this.meta[4]);
		}
		else{
			this.window.updateBoard(this);
		}

		try{
			Thread.sleep(time);
		}catch ( InterruptedException e){
			System.out.print("No se puede esperar");
		}
		painting = false;
	}

	/******** Funcion: bloqueRandom **************
	Descripcion: genera un bloque al azar
	Parametros: ninguno
	Retorno: Bloque generado
	*****************************************************************************************************************************/
	public Bloque bloqueRandom(){
		Random rand = new Random(); 
		int number = rand.nextInt(100);
		Bloque bloque;
		if (number < 95){
			bloque = new BloqueColor();
		}
		else{
			bloque = new BloqueComodin();
		}
		return bloque;
	}

	/******** Funcion: fillBoard **************
	Descripcion: rellena los espacios vacios del tablero
	Parametros: ninguno
	Retorno: vois
	*****************************************************************************************************************************/
	public void fillBoard(){
		while(this.emptyBlocks > 0){

			for (int row=0; row<15; row++) {
				for (int col=0; col<15; col++) {
					if (getBlock(row,col) == null && row > 0) {
						setBlock(row,col,getBlock(row-1,col));
						setBlock(row-1,col,null);
					}
					else if(getBlock(row,col) == null && row == 0){
						setBlock(row,col,bloqueRandom());
						this.emptyBlocks--;
					}
				}
			}
			showBoard(1);
		}
	}

	/******** Funcion: moveBlock **************
	Descripcion: hace un cambio temporal entre dos bloques, si no se genera la destruccion de mas bloques,
	se vuelve al estado original
	Parametros:
	row entero
	newRow entero
	col entero
	newCol entero
	Retorno: void
	*****************************************************************************************************************************/
	public void moveBlock(int row, int col, int newRow, int newCol){
        if((Math.abs(newCol-col) == 1 && newRow == row) || (Math.abs(newRow-row) == 1 && newCol == col))
        {

			Bloque temp1 = getBlock(row,col);
			Bloque temp2 = getBlock(newRow, newCol);
			
			
			setBlock(row, col, temp2);
			setBlock(newRow, newCol, temp1);
			showBoard(1);

			// try {
			// 	java.applet.AudioClip bite =
			// 	java.applet.Applet.newAudioClip(
			// 	new java.net.URL("file:///home/jose/Google Drive/Usm/2014-1/LP/Tarea2LP/resources/bite.wav"));
			// 	bite.play();
			// } catch (java.net.MalformedURLException murle) {
			// 	System.out.println(murle);
			// }

			int destroyed = checkBoard();

			if (destroyed == 0) {
				setBlock(row, col, temp1);
				setBlock(newRow, newCol, temp2);
				showBoard(1);
			}
		}
	}

	/******** Funcion: destroyCol **************
	Descripcion: destruye la columna entera en el tablero (Hability T2)
	Parametros:
	col entero
	Retorno: void
	*****************************************************************************************************************************/
	private void destroyCol(int col){
		for (int i = 0 ; i<15; i++) {

			Bloque temp = getBlock(i,col);

			if (temp != null) {
				if( temp instanceof BloqueColor)
				{
					BloqueColor temp2 = (BloqueColor)temp;
					 
					if (temp2.getColor().equals("R")) {
						this.meta[0]--;
					}else if (temp2.getColor().equals("B")) {
						this.meta[1]--;
					}else if (temp2.getColor().equals("O")) {
						this.meta[2]--;
					}else if (temp2.getColor().equals("G")) {
						this.meta[3]--;
					}else if (temp2.getColor().equals("Y")) {
						this.meta[4]--;
					}
				}
				int x = board[i][col].DestruirBloque();
				if (x > 0) {
					board[i][col] = null;
				}
			}
		}
		showBoard(1);
	}

	/******** Funcion: Nombre_Funcion **************
	Descripcion: destruye la fila entera en el tablero (Hability T1)
	Parametros:
	row entero
	Retorno: void
	*****************************************************************************************************************************/
	private void destroyRow(int row){
		for (int i = 0 ; i<15; i++) {
			Bloque temp = getBlock(row, i);

			if (temp != null) {
				if( temp instanceof BloqueColor)
				{
						
					BloqueColor temp2 = (BloqueColor)temp;
					 
					if (temp2.getColor().equals("R")) {
						this.meta[0]--;
					}else if (temp2.getColor().equals("B")) {
						this.meta[1]--;
					}else if (temp2.getColor().equals("O")) {
						this.meta[2]--;
					}else if (temp2.getColor().equals("G")) {
						this.meta[3]--;
					}else if (temp2.getColor().equals("Y")) {
						this.meta[4]--;
					}
				}
				int x = board[row][i].DestruirBloque();
				if (x > 0) {
					board[row][i] = null;
				}
			}
		}
		showBoard(1);
	}

	/******** Funcion: checkCol **************
	Descripcion: revisa si hay bloques para destruir en la columna
	Parametros:
	col entero
	Retorno: entero: número de bloques destruidos
	*****************************************************************************************************************************/
	private int checkCol(int col){
		int destroyed = 0;
		int adjoining = 1;
		String prev = "";
		String collect = "";
	
		for (int i=14; i>=0; i--) {
			Bloque temp = getBlock(i, col);
			int b = 0;

			if( temp instanceof BloqueColor)
			{
				BloqueColor temp2 = (BloqueColor)temp;
				 

				if (temp2.getColor() == "*") {
					adjoining = 1;
					prev = "*";
					continue;
				}
		
				if (temp2.getColor() == prev ) {
					adjoining++;
				}

				if (i == 0 && temp2.getColor() == prev) {
					b = 1;
					prev = "*";
				}
				if (temp2.getColor() != prev) {
		
					if (adjoining > 2) {
						if (collect == "R") {
							this.meta[0] -=adjoining;
						}else if (collect == "B") {
							this.meta[1] -=adjoining;
						}else if (collect == "O") {
							this.meta[2] -=adjoining;
						}else if (collect == "G") {
							this.meta[3] -=adjoining;
						}else if (collect == "Y") {
							this.meta[4] -=adjoining;
						}
						for (int j=adjoining-b; 0-b<j && i+j < 15; j--) {
							if (board[i+j][col] != null) {	
								int x = board[i+j][col].DestruirBloque();
								if (x==0) {
									destroyed++;
								}
								else if (x==1) {
									destroyRow(i);
									destroyed+=15;
									board[i+j][col] = null;
								}
								else if (x==2) {
									destroyCol(col);
									destroyed+=15;
									board[i+j][col] = null;
									return destroyed;
								}
							}
						}
					}
					adjoining = 1;
					collect = prev = temp2.getColor();
				}
			}
			else if (temp instanceof BloqueComodin){
				adjoining++;
			}
		}
		return destroyed;
	}

	/******** Funcion: checkRow **************
	Descripcion: revisa si hay bloques para destruir en la fila
	Parametros:
	row entero
	Retorno: entero: número de bloques destruidos
	*****************************************************************************************************************************/
	private int checkRow(int row){
		int destroyed = 0;
		int adjoining = 1;
		String prev = "";
		String collect = "";
	
		for (int i=14; i>=0; i--) {
			Bloque temp = getBlock(row, i);
			int b = 0;

			if( temp instanceof BloqueColor)
			{
				BloqueColor temp2 = (BloqueColor)temp;
				 

				if (temp2.getColor() == "*") {
					adjoining = 1;
					prev = "*";
					continue;
				}
		
				if (temp2.getColor() == prev ) {
					adjoining++;
				}

				if (i == 0 && temp2.getColor() == prev) {
					b = 1;
					prev = "*";
				}
				if (temp2.getColor() != prev) {
		
					if (adjoining > 2) {
						if (collect == "R") {
							this.meta[0] -=adjoining;
						}else if (collect == "B") {
							this.meta[1] -=adjoining;
						}else if (collect == "O") {
							this.meta[2] -=adjoining;
						}else if (collect == "G") {
							this.meta[3] -=adjoining;
						}else if (collect == "Y") {
							this.meta[4] -=adjoining;
						}
						for (int j=adjoining-b; 0-b<j && i+j < 15; j--) {
							if (board[row][i+j] != null) {	
								int x = board[row][i+j].DestruirBloque();
								if (x==0) {
									destroyed++;
								}
								else if (x==1) {
									destroyRow(row);
									destroyed+=15;
									board[row][i+j] = null;
									return destroyed;
								}
								else if (x==2) {
									destroyCol(i);
									board[row][i+j] = null;
									destroyed+=15;
								}
							}
						}
					}
					adjoining = 1;
					collect = prev = temp2.getColor();
				}
			}
			else if (temp instanceof BloqueComodin){
				adjoining++;
			}
		}
		return destroyed;
	}

	/******** Funcion: destroyCheck **************
	Descripcion: vacia los cuadros destruidos del tablero
	Parametros: ninguno
	Retorno: void
	*****************************************************************************************************************************/
	private void destroyCheck(){
		boolean destroyed = false;
		for (int row=0; row<15; row++) {
			for (int col=0; col<15; col++) {
				Bloque temp = getBlock(row,col);
				if (temp == null){
					emptyBlocks++;
					destroyed = true;
				}
				else if( temp instanceof BloqueColor){
					BloqueColor temp2 = (BloqueColor)temp;

					if (temp2.getColor() == "*") {
						setBlock(row,col,null);
						emptyBlocks++;
						destroyed = true;
					}
				}
			}
		}
		if (destroyed) {
			if (!this.initializing) {
				if(!this.getConsoleMode())
				{
					try {
						java.applet.AudioClip bite =
						java.applet.Applet.newAudioClip(
						new java.net.URL("file://"+System.getProperty("user.dir")+"/resources/bite.wav"));
						bite.play();
					} catch (java.net.MalformedURLException murle) {
						System.out.println(murle);
					}
				}
			}
			showBoard(1);
			fillBoard();
		}
	}

	/******** Funcion:checkBoard **************
	Descripcion: revisa si hay bloques por destruir en el tablero, usando checkCol y checkRow
	Parametros: ninguno
	Retorno: entero: bloques destruidos
	*****************************************************************************************************************************/
	public int checkBoard(){
		int destroyed = 0;
		int destTemp = 0;
		boolean col = true, row = true;
		while(col || row){
			col = row = false;
			destTemp = 1;
			while(destTemp > 0){
				destTemp = 0;
				for (int i=0; i<15; i++) {
					destTemp += checkCol(i);
					destroyCheck();
				}
				destroyed += destTemp;
				if (destTemp > 0) {
					col = true;
				}
			}
			destTemp = 1;
			while(destTemp > 0){
				destTemp = 0;
				for (int i=14; i>=0; i--) {
					destTemp += checkRow(i);
					destroyCheck();
				}
				destroyed += destTemp;
				if (destTemp > 0) {
					row = true;
				}
			}
		}
		return destroyed;
	}

	/******** Funcion: handleClick **************
	Descripcion: lee los click desde la interfaz y los imprime en consola
	Parametros:
	i JPanel
	boolean add
	Retorno: void
	*****************************************************************************************************************************/
	public void handleClick(JPanel i, boolean add)
	{
    	final Board b = this;
    	final JPanel y = i;

    	if (add)
    	{
    		i.addMouseListener(new MouseAdapter()
    		{   
    			@Override	
	            public void mouseClicked(MouseEvent e) 
	            {

	                if(painting == false)
	                {
	                	e.translatePoint(e.getComponent().getLocation().x, e.getComponent().getLocation().y);
	                 
	                	System.out.println(""+e.getY()/30+"-"+e.getX()/30);
	                	// return ola;
	                }
	                // return null;
	             }

	        });
	       // return chao;
	    }
		else
		{
			i.removeMouseListener(i.getMouseListeners()[0]);		
			// return null;
		}
    }

	/******** Funcion: read **************
	Descripcion: lee los clicks desde consola
	Parametros: ninguno
	Retorno: arreglo de enteros: {clickX, clickY}
	*****************************************************************************************************************************/
 	static String[] read() throws IOException{
			

        String c = "";
        PipedOutputStream pipeOut = new PipedOutputStream();
        PipedInputStream pipeIn = new PipedInputStream(pipeOut);
        System.setOut(new PrintStream(pipeOut));
        Scanner sc = new Scanner(pipeIn);

        c = sc.nextLine();
        return c.split("-");
        
    }


	/******** Funcion: main **************
	Descripcion: funcion principal del juego, lo inicializa y ejecuta
	Parametros:
	args arreglo de String
	Retorno: void
	*****************************************************************************************************************************/
	public static void main(String[] args){
		String[] click;
		int[] oldClick = new int[] {0,0};
		int[] meta = {100,100,100,100,100};

		Board board;
		
		if(args[0].equals("c"))
		{	
			board  = new Board(true);
			for(int i=0;i<5;i++)
			{	
				if(!args[i+1].equals(""))
				{
					meta[i]=Integer.parseInt(args[i+1]);
				}
			}
		}
		else 
		{
			board  = new Board(false);
			for(int i=0;i<5;i++)
			{	
				if(!args[i+1].equals(""))
				{
					meta[i]=Integer.parseInt(args[i+1]);
				}
			}
		}

		// if (args.length == 1 && args[0].equals("-c")) {
		// 	board = new Board(true);	
		// }
		// else if(args.length == 6 && args[0].equals("-c")){
		// 	board = new Board(true);	
		// 	for(int i=1; i < 6; i++){
		// 		meta[i-1] = Integer.parseInt(args[i]);
		// 	}
		// }
		// else if(args.length == 5){
		// 	board  = new Board(false);
		// 	for(int i=0; i < 5; i++){
		// 		meta[i] = Integer.parseInt(args[i]);
		// 	}
		// }
		// else{
		// 	board  = new Board(false);
		// }
		board.fillBoard();
 		board.checkBoard();
 		board.setMeta(meta);
 		board.showBoard(1);
 		board.setTime(100);
 		board.initializing = false;
		while(!board.getDone()){

			if(board.checkMoves() == false)
			{
				JOptionPane.showMessageDialog(null,"No hay mas movimientos");
			}
	 		board.showBoard(1);
			if(!board.painting){
				if (board.getConsoleMode()) {
		 			Console console = System.console();
					String input = console.readLine("Ingrese movimiento\nEj: row1-col1 row2-col2:\n");
					String[] bloques = input.split(" ");
					int row1 = Integer.parseInt(bloques[0].split("-")[0]);
					int col1 = Integer.parseInt(bloques[0].split("-")[1]);
					int row2 = Integer.parseInt(bloques[1].split("-")[0]);
					int col2 = Integer.parseInt(bloques[1].split("-")[1]);
					board.moveBlock(row1,col1,row2,col2);	
		 		}
				else{
					try{
						click = read();
						board.moveBlock(oldClick[0],oldClick[1],Integer.parseInt(click[0]),Integer.parseInt(click[1]));
						oldClick[0] = Integer.parseInt(click[0]);
						oldClick[1] = Integer.parseInt(click[1]);
					}
					catch(IOException e){
						
					}
				}
			}
		}
		JOptionPane.showMessageDialog(null,"Ganaste Chancho Crash");
		board.window.close();
	}
}