package datenextraktion;

public class PostleitzahlenDatei extends TeildateinDATAFACTORY {

	public SqlTabellen aufrufTabelle(String tabellenname) {
		if("Z_GEMEINDEDATEN_LWV".equalsIgnoreCase(tabellenname)) {
			return new Z_GEMEINDEDATEN_LWV();
		}else {
			return null;
		}
	}

}