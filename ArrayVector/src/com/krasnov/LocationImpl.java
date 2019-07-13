package com.krasnov;

public class LocationImpl implements Location {
    private Type type;
    private String name;
    private Location parent;
    public LocationImpl() {
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void setParent(Location parent) {
        this.parent = parent;
    }

    @Override
    public String getParentName() {
        if (parent == null) return "--";
        return parent.getName();
    }

    @Override
    public Location getTopLocation() {
        if (parent == null)
            return this;
        else
            return parent.getTopLocation();
    }

    @Override
    public boolean isCorrect() {
        return false;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, type.toString());
    }
}
