package edu.vit.gravitas.Object;

public class SettingsObject {
    int img;
    String text;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SettingsObject(int img, String text) {
        this.img = img;
        this.text = text;
    }
}
