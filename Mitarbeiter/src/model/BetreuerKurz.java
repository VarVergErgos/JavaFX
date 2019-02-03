package model;
import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
  
public class BetreuerKurz {
	private IntegerProperty persnr;
	private StringProperty vorname;
	private final ObjectProperty<LocalDate> eintrittsdatum;
	private DoubleProperty gehalt;

	public BetreuerKurz(Integer persnr, String vorname, 
                LocalDate eintrittsdatum, Double gehalt) {
		super();
		this.persnr = new SimpleIntegerProperty(persnr);
		this.vorname = new SimpleStringProperty(vorname);
		this.eintrittsdatum = 
                        new SimpleObjectProperty<LocalDate>(eintrittsdatum);
		this.gehalt = new SimpleDoubleProperty(gehalt);
	}
	public BetreuerKurz() {
		this(0, null, LocalDate.of(1900, 1, 1), 0.00);
	}
	public Integer getPersnr() {return persnr.get();}
	public void setPersnr(Integer persnr) {this.persnr.set(persnr);}
	public IntegerProperty persnrProperty() {return persnr;}

	public String getVorname() {return vorname.get();}
	public void setVorname(String vorname) {this.vorname.set(vorname);}
	public StringProperty vornameProperty() {return vorname;}

	public LocalDate getEintrittsdatum() {return eintrittsdatum.get();}
	public void setEintrittsdatum(LocalDate eintrittsdatum) {
		this.eintrittsdatum.set(eintrittsdatum);}
	public ObjectProperty<LocalDate> eintrittsdatumProperty() {
		return eintrittsdatum;}

	public Double getGehalt() {return gehalt.get();	}
	public void setGehalt(Double gehalt) {this.gehalt.set(gehalt);}
	public DoubleProperty gehaltProperty() { return gehalt;}
	
        @Override
	public String toString() {
		return "Mitarbeiter [persnr=" + persnr.get() 
                        + ", vorname=" + vorname.get() + ", eintritt="
			+ eintrittsdatum.get() + ", gehalt=" 
                        + gehalt.get() + "]\n";
	}

}
