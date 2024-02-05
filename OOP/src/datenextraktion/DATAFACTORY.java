package datenextraktion;

public class DATAFACTORY{

	public TeildateinDATAFACTORY createTeildatei(String dateiKennung) {
		if("PL".equalsIgnoreCase(dateiKennung)) {
			return new PostleitzahlenDatei();
		}else {
			return null;
		}
	}
}