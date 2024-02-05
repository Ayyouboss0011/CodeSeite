package datenextraktion;

public abstract class SqlTabellen {

	public abstract String valuesToSqlInsertStatement();
	public abstract void setValuesToTeildateienzeile(String dateiKennung, String zeile);
}