package eu.alfo.rtu_pit.db;

public class   Category {
    public static final int PROGRAMMING = 1;
    public static final int GEOGRAPHY = 2;
    public static final int MATH = 3;
    public static final int M4 = 4;
    public static final int M5 = 5;
    public static final int M6 = 6;
    public static final int M7 = 7;
    public static final int M8 = 8;
    public static final int M9 = 9;


    private int id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return getName();
    }
}