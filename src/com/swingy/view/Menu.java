package com.swingy.view;

import com.swingy.Controller;
import com.swingy.Model;
import com.swingy.characters.heroes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu implements ActionListener {

    private static JPanel panel;
    private static JFrame frame;
    private static JLabel heading;
    private Controller controller;
    private Model model;

    public void displayMenu(ArrayList<Hero> heroes, Controller controller, Model model) {

        this.controller = controller;
        this.model = model;

        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JScrollPane scrollingArea = new JScrollPane(panel);
        scrollingArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollingArea);

        heading = new JLabel("SWINGY");
        heading.setBounds(10, 10, 100, 30);
        panel.add(heading);

        JButton thor = new JButton("Thor");
        JButton batman = new JButton("Batman");
        JButton jedi = new JButton("Jedi");
        JLabel create = new JLabel("Create a new character:");
        JLabel exist = new JLabel("Load an existing character:");
        if (heroes.size() > 0) {
            create.setBounds(250, 30, 150, 30);
            exist.setBounds(10, 30, 200, 30);
            thor.setBounds(250, 60, 100, 30);
            batman.setBounds(250, 90, 100, 30);
            jedi.setBounds(250, 120, 100, 30);
        } else {
            create.setBounds(250, 30, 150, 30);
            exist.setBounds(10, 30, 200, 30);
            thor.setBounds(10, 60, 150, 30);
            batman.setBounds(10, 90, 150, 30);
            jedi.setBounds(10, 120, 150, 30);
        }
        panel.add(create);
        panel.add(exist);
        panel.add(thor);
        panel.add(batman);
        panel.add(jedi);

        try {
            for (int i = 0; i < heroes.size(); i++) {
                JLabel label = new JLabel("<html>Name: " + heroes.get(i).name + "<br/>" +
                        "Type: " + heroes.get(i).type + "<br/>" +
                        "Level: " + heroes.get(i).level + "<br/>" +
                        "Experience: " + heroes.get(i).experience + "<br/>" +
                        "Attack Increase: " + heroes.get(i).attack + "<br/>" +
                        "Defence Increase: " + heroes.get(i).defence + "<br/>" +
                        "Hit Points Increase: " + heroes.get(i).hitPoints + "<br/>" +
                        "</html>");

                int k = i + 1;
                int y = (150 * k) - 80;

                label.setBounds(10, y, 200, 110);
                panel.add(label);

                JButton button = new JButton(heroes.get(i).name + " ID: " + i + heroes.get(i).experience);
                button.addActionListener(this);
                button.setBounds(10, y + 110, 150, 30);
                panel.add(button);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error while making labels in Menu.java: " + e);
        }

        thor.addActionListener(e -> {
            // contacts controller to set chosen hero
            this.controller.setChosenHero(new Thor());
            frame.dispose();
        });
        jedi.addActionListener(e -> {
            // contacts controller to set chosen hero
            this.controller.setChosenHero(new Jedi());
            frame.dispose();
        });
        batman.addActionListener(e -> {
            // contacts controller to set chosen hero
            this.controller.setChosenHero(new Batman());
            frame.dispose();
        });

        frame.setPreferredSize(new Dimension(600, 700));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] btnText = e.getSource().toString().split(",");
        String[] text = btnText[btnText.length - 2].split(" ");
        String chosen = text[2];

        int index = Integer.parseInt(String.valueOf(chosen.charAt(0)));

        // contacts controller to set chosen hero
        this.controller.setChosenHero(model.getHeroes().get(index));
        frame.dispose();
    }
}