package edu.gmu.cs477.fall2020.finalproject_tnguye87;

public class Cars {

    private String name;
    private String type;
    private String make;
    private String model;
    private String note;
    private int year;

    //Constructor for this class
    public Cars (String name, String type, String make, String model, String note, int year ) {
        this.setName(name);
        this.setType(type);
        this.setMake(make);
        this.setModel(model);
        this.setNote(note);
        this.setYear(year);
    }
    /** All setter methods */
    public void setName(String name) { this.name = name; }

    public void setType(String type) { this.type = type; }

    public void setMake(String make) { this.make = make; }

    public void setModel(String model) { this.model = model; }

    public void setNote(String note) { this.note = note; }

    public void setYear(int year) { this.year = year; }

    /** All getter methods */
    public String getName() { return name; }

    public String getType() { return type; }

    public String getMake() { return make; }

    public String getModel() { return model; }

    public String getNote() { return note; }

    public int getYear() { return year; }
}
