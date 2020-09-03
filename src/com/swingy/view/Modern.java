package com.swingy.view;

import com.swingy.Controller;
import com.swingy.Model;

import javax.swing.*;

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
        frame.setSize(600, 500); // calc dimensions based on map size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        header();
        stats();
        extraButtons();
        moveButtons();
        mapDisplay();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void header() {

    }

    private void stats() {

    }

    private void mapDisplay() {

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
        //set boundaries
        //show buttons
        quit.addActionListener(l -> {

        });
        menu.addActionListener(l -> {

        });
        console.addActionListener(l -> {

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
