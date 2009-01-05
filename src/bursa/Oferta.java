package bursa;

public class Oferta {
	private String numeCompanie;
	private int nrActiuni;
	private int pret;
	
	public Oferta(String numeCompanie, int nrActiuni, int pret) {
		this.numeCompanie = numeCompanie;
		this.nrActiuni = nrActiuni;
		this.pret = pret;
	}

	public String getNumeCompanie() {
		return numeCompanie;
	}
	
	public int getNrActiuni() {
		return nrActiuni;
	}
	
	public int getPret() {
		return pret;
	}

	public void setPret(int pretNou) {
		pret = pretNou;		
	}

	public void setNrActiuni(int nrActiuni) {
		this.nrActiuni = nrActiuni;
	}
}
