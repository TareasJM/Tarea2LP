public class Principal{

	public static void main(String[ ] args)
	{

		Bloque bloque = new BloqueColor();

		if( bloque instanceof BloqueColor)
		{
			BloqueColor temp = (BloqueColor)bloque;
			System.out.println(temp.getColor());
		}
		else if (bloque instanceof BloqueComodin) 
		{
			BloqueComodin temp = (BloqueComodin)bloque;
			temp.Hability(0);
		}
		
	}
}