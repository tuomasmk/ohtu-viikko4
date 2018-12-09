package statistics.matcher;


public class Querybuilder {
    Matcher matcher;
    
    public Querybuilder() {
        matcher = new All();
    }
    
    public Matcher build() {
        return matcher;
    }
    
    public Querybuilder playsIn(String team) {
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }
    
    public Querybuilder hasAtLeast(int amount, String category) {
        this.matcher = new And(this.matcher, new HasAtLeast(amount, category));
        return this;
    }
    
    public Querybuilder hasFewerThan(int amount, String category) {
        this.matcher = new And(this.matcher, new HasFewerThan(amount, category));
        return this;
    }
    
    public Querybuilder oneOf(Matcher m1, Matcher m2) {
        this.matcher = new Or(m1, m2);
        return this;
    }
}
