package datenextraktion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class ExtraktionDatafactory {
	
	private static final String TABELLENNAME = "Z_GEMEINDEDATEN_LWV";

	public ExtraktionDatafactory(String dateiKennung, String quellDatei, String zielDatei) {
		this.dateiKennung = dateiKennung;
		this.quellDatei = quellDatei;
		this.zielDatei = zielDatei;
	}

	String dateiKennung;
	String quellDatei;
	String zielDatei;

	public BufferedReader openReader() throws IOException {
		return new BufferedReader(new FileReader(this.quellDatei));
	}
	
	public BufferedWriter openWriter() throws IOException { //writer öffnen
		return new BufferedWriter(new FileWriter(this.zielDatei,true));
	}

	
	
	public void startProcess() {						//neuer Name
		try (BufferedReader reader = openReader()) {		//methode write öffnen/schließen
			BufferedWriter writer = openWriter();
			TeildateinDATAFACTORY df = new DATAFACTORY().createTeildatei(this.dateiKennung);
			SqlTabellen daten = df.aufrufTabelle(ExtraktionDatafactory.TABELLENNAME); 
			processFile(reader, writer, daten);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void processFile(BufferedReader reader, BufferedWriter writer, SqlTabellen daten) {
		String line;
		ArrayList<String> insert = new ArrayList<String>();
		try {
			while((line=reader.readLine())!=null) {
				if(line.startsWith(this.dateiKennung)){
					daten.setValuesToTeildateienzeile(this.dateiKennung, line);
					String sqlInsert = daten.valuesToSqlInsertStatement();
					try {
						writer.flush();
						insert.add(sqlInsert);
						writer.write(sqlInsert);
						writer.newLine();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			writer.close();
			reader.close();
			System.out.print(insert);
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
}
	
