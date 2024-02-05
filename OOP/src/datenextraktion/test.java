package datenextraktion;
public class test {
	public static void main(String[] args) {
		System.out.println(args);
		// TODO: Dateien liegen irgendwo, hier müssen wir den Pfad von außen mitgeben können!
		String plzString = "C:\\Users\\Z1323012\\Desktop\\B2109244.DAT";
		String zielString = "C:\\Users\\Z1323012\\Desktop\\postdateiExtrahierteDaten.txt";
		ExtraktionDatafactory test1 = new ExtraktionDatafactory("PL",plzString,zielString);
		test1.startProcess();

	}

}