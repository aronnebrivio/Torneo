import java.util.*;
import java.io.*;

public class Torneo 
{
	public static void main(String[] args) throws Exception
	{
		Vector<Squadra> t = new Vector<Squadra>();
		File f = new File("giocatori.txt"); // Leggo da file
		Scanner s = new Scanner(f);
		String g = s.next();
		String n = "";
		String c = "";
		String sq = "";
		String ruolo = "";
		double h = 0;
		Giocatore player = null;
		Squadra squadra = null;
		boolean fine = false;
		boolean primoCiclo = true;
		while(!fine) //N.B. con un semplice do while o for "mangia" l'ultima riga del file di testo, con questo rude metodo no
		{
			Scanner tokenizer = new Scanner(g);
			tokenizer.useDelimiter(",");
			n=tokenizer.next();
			System.out.println("DEBUG: nome = "+n);
			c=tokenizer.next();
			System.out.println("DEBUG: cognome = "+c);
			h=Double.parseDouble(tokenizer.next());
			System.out.println("DEBUG: altezza = "+h);
			sq=tokenizer.next();
			System.out.println("DEBUG: squadra = "+sq);
			ruolo=tokenizer.next();
			System.out.println("DEBUG: ruolo = "+ruolo);

			squadra = new Squadra(sq);
			t.add(squadra);

			if(primoCiclo = false) //fa questo dal secondo giocatore in poi 
			{
				squadra = new Squadra(sq);
				t.add(squadra);
				for(Squadra x : t) //controlla che la squadra non esista gia'
				{
					if(x.equals(squadra))
						t.remove(squadra);
				}
			}

			if (ruolo.equals("T")) //crea Titolare o Riserva
			{
				player = new Titolare(n, c, h, squadra);
				System.out.println("DEBUG: giocatore creato = "+player);
			}
			else
			{
				player = new Riserva(n, c, h, squadra);
				System.out.println("DEBUG: giocatore creato = "+player);
			}
			squadra.add(player); //aggiunge il giocatore appena creato alla squadra

			System.out.println();
			
			primoCiclo = false;

			if(!s.hasNext())
				fine = true;
			else
				g = s.next();
		}//END while

		//ORA STAMPO TUTTO
		//Stampo le squadre...	
		for (Squadra x : t) 
		{
			System.out.println(x.getNomeSquadra() + ":");
			x.sort();
			for (Giocatore y : x) 
			{
			    System.out.println(y);
			}
			System.out.println();
		}
	
		System.out.println();

		//Stampo i giocatori
		System.out.println("GIOCATORI: "); 
		for (Squadra x : t)
		{
			for (Giocatore y : x)
				System.out.println(y);
		}

		System.out.println();		

		//Stampo i titolari...
		System.out.println("TITOLARI: "); 
		for (Squadra x : t) 
		{
			for (Giocatore y : x) 
			{
			    if (y instanceof Titolare)
				System.out.println(y);
			}
		}

		System.out.println();
	
		//Stampo le riserve...
		System.out.println("RISERVE: "); 
		for (Squadra x : t) 
		{
			for (Giocatore y : x) 
			{
			    if (y instanceof Riserva)
				System.out.println(y);
			}
		}
		
		System.out.println();

		System.out.println("DEBUG: grandezza vettore Torneo = " + t.size());
		
	}//END main
		
	
}//END class
