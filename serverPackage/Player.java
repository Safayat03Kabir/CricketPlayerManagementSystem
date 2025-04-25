package serverPackage;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Player implements Serializable {
    private String name, country, club, position;
    private int age, number;
    private double weeklySalary, height, price;
    private boolean isBeingSold;
    private String Link;
    public Player(String name, String country, String club, String position, int age, int number, double weeklySalary, double height, Boolean isBeingSold, double price, String Link) {
        this.name = name;
        this.country = country;
        this.club = club;
        this.position = position;
        this.age = age;
        this.number = number;
        this.weeklySalary = weeklySalary;
        this.height = height;
        this.isBeingSold = isBeingSold;
        this.price = price;
        this.Link = Link;

    }
    public Player(){}
    public String getName() {return name;}
    public String getCountry() {return country;}
    public String getClub(){return club;}
    public String getPosition() {return position;}
    public int getAge() {return age;}
    public int getNumber() {return number;}
    public double getWeeklySalary() {return weeklySalary;}
    public double getHeight() {return height;}
    public boolean getIsBeingSold() {return isBeingSold;}
    public void setClub(String club) {this.club = club;}
    public void setIsBeingSold(boolean isBeingSold) {this.isBeingSold = isBeingSold;}
    public void setPrice(Double price) {this.price = price;}
    public double getPrice(){return price;}
    public String getLink() {return Link;}
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat();
        return "Name: " + name + ", Country: " + country + ", Age: " + age + ", Height: " + height + ", Club: " + club + ", Positon: " + position + ", Number: " + number + ", Weekly Salary " + df.format(this.getWeeklySalary()) + " " +  isBeingSold + " " + price;
    }


}
