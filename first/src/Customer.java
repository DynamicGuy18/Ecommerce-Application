public class Customer {
    private int id;
    private String name , mob, username;
    public Customer(int id, String name, String mob, String username) {
        this.id = id;
        this.name = name;
        this.mob = mob;
        this.username = username;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMob() {
        return mob;
    }
    public void setMob(String mob) {
        this.mob = mob;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    

    

}
