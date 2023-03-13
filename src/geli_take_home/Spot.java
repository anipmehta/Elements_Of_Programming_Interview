package geli_take_home;

public class Spot {
    private String id;

    public Spot(String id){
        this.setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id: " + this.getId());
        return stringBuilder.toString();
    }
}
