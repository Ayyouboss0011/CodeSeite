package datenextraktion;

public class Z_GEMEINDEDATEN_LWV extends SqlTabellen{
	
	public Z_GEMEINDEDATEN_LWV(String plz, String strPf, String ortsname, String alort, String gemeindeschluessel,
			String geland, String gekreis) {
		this.plz = plz;
		this.strPf = strPf;
		this.ortsname = ortsname;
		this.alort = alort;
		this.gemeindeschluessel = gemeindeschluessel;
		this.geland = geland;
		this.gekreis = gekreis;
	}
	public Z_GEMEINDEDATEN_LWV() {
		this.plz = "";
		this.strPf = "";
		this.ortsname = "";
		this.alort = "";
		this.gemeindeschluessel = "";
		this.geland = "";
		this.gekreis = "";
	}

	String plz;		//inhalt sql-Tabelle
    String strPf;
    String ortsname;
    String alort;
    String gemeindeschluessel;
    String geland;
    String gekreis;
  
	public void setValuesToTeildateienzeile(String dateiKennung, String zeile) {
		if("PL".equalsIgnoreCase(dateiKennung)) {
			setValuesToPostleitzahlendatei(zeile);
		}
	};
	
	public void pfDatPFVERZ(char number) {
		switch(number) {
		case '0': 
			this.strPf=" ";
			break;
		case '1':
			this.strPf="P";
			break;
		case '2':
			this.strPf="S";
			break;
		default:
			this.strPf="-1";
		}
	}
	
	public void setValuesToEmpty() {
		this.plz = "";
		this.strPf = "";
		this.ortsname = "";
		this.alort = "";
		this.gemeindeschluessel = "";
		this.geland = "";
		this.gekreis = "";
	}
	
	public void setGelandGekreisFromGemSchl(String gemeindeschl) {
		this.gekreis= gemeindeschl.charAt(0)+""+gemeindeschl.charAt(1)+""+gemeindeschl.charAt(2)+""+gemeindeschl.charAt(3)+""+gemeindeschl.charAt(4);
		this.geland = gemeindeschl.charAt(0)+""+gemeindeschl.charAt(1);// + gemeindeschluessel.charAt(1)+""; 

	}
	
	
	public void setValuesToPostleitzahlendatei(String zeilePlDat) {
		boolean ortVorbei=false;
		setValuesToEmpty();
		for(int i=0; i<zeilePlDat.length(); i++) {
			if(i>=17&&i<=21) {
				this.plz+=zeilePlDat.charAt(i);
			}
			if(i>=22&&i<=29) {
				this.alort+=zeilePlDat.charAt(i);
			}
			if(i==33) {
				pfDatPFVERZ(zeilePlDat.charAt(i));
			}
			if(i>=34&&i<=73&&!ortVorbei) {
				if(zeilePlDat.charAt(i)==' ') {
					ortVorbei=true;
				}else {
					this.ortsname+=zeilePlDat.charAt(i);
				}
			}
			if(i>=146&&i<=153) {
				this.gemeindeschluessel+=zeilePlDat.charAt(i);
			}
		} 
		setGelandGekreisFromGemSchl(this.gemeindeschluessel);
	}
    
	public String getPlz() {
		return plz;
	}

	public String getStrPf() {
		return strPf;
	}

	public String getOrtsname() {
		return ortsname;
	}

	public String getAlort() {
		return alort;
	}

	public String getGemeindeschluessel() {
		return gemeindeschluessel;
	}

	public String getGeland() {
		return geland;
	}

	public String getGekreis() {
		return gekreis;
	}
	

	public String valuesToSqlInsertStatement() {
		return "Insert into Z_GEMEINDEDATEN_LWV (PLZ,STR_PF,ONAME24,ALORT,GESCHL,GELAND,GEKREIS) values ("+this.plz+","+this.strPf+","+this.ortsname+","+this.alort+","+this.gemeindeschluessel+","+this.geland+","+this.gekreis+");";
	}
}