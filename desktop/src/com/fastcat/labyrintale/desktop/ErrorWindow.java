package com.fastcat.labyrintale.desktop;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorWindow extends JFrame {

    public ErrorWindow(Throwable e) {
        StringWriter stackTrace = new StringWriter();
        e.printStackTrace(new PrintWriter(stackTrace));

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JLabel mainLabel = new JLabel("Game Crashed");
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainLabel.setFont(new Font("Ariel", Font.PLAIN, 20));
        mainLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.add(mainLabel);

        JLabel randomText = new JLabel("That is sad.");
        randomText.setAlignmentX(Component.CENTER_ALIGNMENT);
        randomText.setFont(new Font("Ariel", Font.PLAIN, 14));
        randomText.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        this.add(randomText);

        JTextArea editArea = new JTextArea(10, 60);
        editArea.setText(stackTrace.toString());
        editArea.setEditable(false);

        this.add(editArea);
        this.add(new JScrollPane(editArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
