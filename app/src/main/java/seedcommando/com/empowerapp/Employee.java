package seedcommando.com.empowerapp;

import java.io.Serializable;

class Employee implements Serializable {

    String name;
    String price;
    boolean isSelected;

    public Employee(String name, String price, boolean isSelected) {
        this.name = name;
        this.price = price;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}