package ru.testfield.web.model.cms;

public class MenuItem {

    private MenuItem parent;
    private String link;
    private String name;
    private String icon;
    private boolean active;

    public MenuItem(String link, String name, String icon, String servletPath) {
        this.link = link;
        this.name = name;
        this.icon = icon;
        this.active = link!=null && link.equals(servletPath);
    }

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public boolean isActive() {
        return active;
    }
}
