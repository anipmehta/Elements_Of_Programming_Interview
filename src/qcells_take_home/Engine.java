package qcells_take_home;

public class Engine {
    private double hpw;
    private double mpg;
    public Engine(double hpw, double mpg){
        this.setHpw(hpw);
        this.setMpg(mpg);
    }

    public double getHpw() {
        return hpw;
    }

    public void setHpw(double hpw) {
        this.hpw = hpw;
    }

    public double getMpg() {
        return mpg;
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }
}
