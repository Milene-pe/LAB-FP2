public class exercise5 {
	public static void main(String[] args) {

		String[] equipment1 = createTeam();
		String[] equipment2 = createTeam();

		System.out.println("Equipment 1:");
		mostrar(equipment1);

		System.out.println("Equipment 2:");
		mostrar(equipment2);

		System.out.println();
		ganador(equipment1, equipment2);
	}
	public static String[] createTeam() {
		String [] players = new String [21];
		for (int i=0; i<players.length;i++){
			players[i] = "Player "+ (i+1);
		}

		return players;
	}
	public static void elegir(String[] players) {
		int i=0;
		while (i<12){
			int n = (int) (Math.random()*players.length+1);
	
		}
	}

	public static void mostrar(String[] players) {
		for (int i = 0; i < players.length; i++)
			System.out.println("- Jugadores Nro " + (i + 1) + ": " + jugadores[i]);
	}

	public static void ganador(String[] Equipo1, String[] Equipo2) {
		int goles1= (int)(Math.random()*10+1);
		int goles2= (int)(Math.random()*10+1);
		if (goles1 > goles2)
			System.out.println("El ganador es: Equipo 1");
		else if (goles1 < goles2)
			System.out.println("El ganador es: Equipo 2");
		else
			System.out.println("Empate");
	}
}