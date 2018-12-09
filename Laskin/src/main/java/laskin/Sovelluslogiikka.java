package laskin;

public class Sovelluslogiikka {
 
    private int edellinenTulos;
    private int tulos;
 
    public void plus(int luku) {
        edellinenTulos = tulos;
        tulos += luku;
    }
     
    public void miinus(int luku) {
        edellinenTulos = tulos;
        tulos -= luku;
    }
 
    public void nollaa() {
        tulos = 0;
    }
    
    public void undo() {
        tulos = edellinenTulos;
    }
 
    public int tulos() {
        return tulos;
    }
    
    public int edellinenTulos() {
        return edellinenTulos;
    }
}