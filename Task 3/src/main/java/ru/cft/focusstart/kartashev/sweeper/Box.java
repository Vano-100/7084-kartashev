package ru.cft.focusstart.kartashev.sweeper;

import javax.swing.*;

public enum Box {
    zero(new ImageIcon(Box.class.getResource("/img/zero.png"))),
    num1(new ImageIcon(Box.class.getResource("/img/num1.png"))),
    num2(new ImageIcon(Box.class.getResource("/img/num2.png"))),
    num3(new ImageIcon(Box.class.getResource("/img/num3.png"))),
    num4(new ImageIcon(Box.class.getResource("/img/num4.png"))),
    num5(new ImageIcon(Box.class.getResource("/img/num5.png"))),
    num6(new ImageIcon(Box.class.getResource("/img/num6.png"))),
    num7(new ImageIcon(Box.class.getResource("/img/num7.png"))),
    num8(new ImageIcon(Box.class.getResource("/img/num8.png"))),
    bomb(new ImageIcon(Box.class.getResource("/img/bomb.png"))),
    opened(new ImageIcon(Box.class.getResource("/img/opened.png"))),
    closed(new ImageIcon(Box.class.getResource("/img/closed.png"))),
    flaged(new ImageIcon(Box.class.getResource("/img/flaged.png"))),
    bombed(new ImageIcon(Box.class.getResource("/img/bombed.png"))),
    nobomb(new ImageIcon(Box.class.getResource("/img/nobomb.png")));

    public ImageIcon imageIcon;

    Box(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public Box getNextNumberBox() {
        return Box.values()[this.ordinal() + 1];
    }

    int getNumber() {
        return this.ordinal();
    }


}


