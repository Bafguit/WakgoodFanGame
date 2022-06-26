package com.fastcat.labyrintale.desktop;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorWindow extends JFrame {

    public ErrorWindow(Throwable e) {
        final StringWriter stackTrace = new StringWriter();
        stackTrace.append("--------------------GAME CRASHED--------------------\n\n");
        e.printStackTrace(new PrintWriter(stackTrace));

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JLabel mainLabel = new JLabel("Game Crashed");
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainLabel.setFont(new Font("Ariel", Font.PLAIN, 20));
        mainLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.add(mainLabel);

        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        final StringSelection selection = new StringSelection("```" + stackTrace + "```");
        clipboard.setContents(selection, null);

        JButton button = new JButton("Copy to clipboard");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clipboard.setContents(selection, null);
            }
        });
        this.add(button);

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
