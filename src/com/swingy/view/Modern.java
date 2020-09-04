package com.swingy.view;

import com.swingy.Controller;
import com.swingy.Model;

import javax.swing.*;
import java.awt.*;

public class Modern {

    private JPanel panel;
    private JFrame frame;
    private JLabel header;
    private JLabel stats;
    private JLabel question;
    private JLabel info;
    private final JButton up = new JButton("Up");
    private final JButton down = new JButton("Down");
    private final JButton left = new JButton("Left");
    private final JButton right = new JButton("Right");
    private final JButton yes = new JButton("Yes");
    private final JButton no = new JButton("No");
    private final Model model;
    private final Controller controller;
    private boolean choiceClicked = false;

    public Modern(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
    }

    public void guiGenerate() {
        frame = new JFrame();
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        header();
        stats();
        extraButtons();
        moveButtons();
        mapDisplay();

        frame.setPreferredSize(new Dimension(800, 700)); // calc dimensions based on map size
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void header() {
        header = new JLabel("Jadon Hansen - SWINGY - 2020");
        header.setBounds(10,0,250,50);
        panel.add(header);
    }

    private void stats() {
        stats = new JLabel("");
    }

    private void mapDisplay() {
        // loop through map and display each line as a new label
    }

    private void choiceButtons() {
        choiceClicked = false;
        //set boundaries
        //show buttons
        yes.addActionListener(e -> {
            choiceClicked = true;
        });
        no.addActionListener(e -> {
            choiceClicked = true;
        });
    }

    private void extraButtons() {
        JButton quit = new JButton("Quit");
        JButton menu = new JButton("Quit to Menu");
        JButton console = new JButton("Console");

        console.setBounds(400, 15, 80, 30);
        menu.setBounds(480, 15, 130, 30);
        quit.setBounds(610, 15, 70, 30);

        panel.add(quit);
        panel.add(menu);
        panel.add(console);

        quit.addActionListener(l -> {
            frame.dispose();
        });
        menu.addActionListener(l -> {
            frame.dispose();
            // go to menu
        });
        console.addActionListener(l -> {
            frame.dispose();
            // reset option to 0
            // go to menu
        });
    }

    private void moveButtons() {
        //set boundaries
        //show buttons
        up.addActionListener(a -> {

        });
        down.addActionListener(a -> {

        });
        left.addActionListener(a -> {

        });
        right.addActionListener(a -> {

        });
    }
}
