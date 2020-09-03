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

    private JPanel panel;
    private JFrame frame;
    private JLabel heading;
    private final Controller controller;
    private final Model model;
    private final ArrayList<Hero> heroes;

    public Menu(ArrayList<Hero> heroes, Controller controller, Model model) {
        this.controller = controller;
        this.model = model;
        this.heroes = heroes;
    }

    public void displayMenu() {
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
        if (heroes.size() > 0) {
            JLabel exist = new JLabel("Load an existing character:");
            exist.setBounds(10, 30, 200, 30);
            panel.add(exist);

            create.setBounds(250, 30, 150, 30);
            thor.setBounds(250, 60, 100, 30);
            batman.setBounds(250, 90, 100, 30);
            jedi.setBounds(250, 120, 100, 30);
        } else {
            create.setBounds(10, 30, 150, 30);
            thor.setBounds(10, 60, 150, 30);
            batman.setBounds(10, 90, 150, 30);
            jedi.setBounds(10, 120, 150, 30);
        }
        panel.add(create);
        panel.add(thor);
        panel.add(batman);
        panel.add(jedi);

        try {
            for (int i = 0; i < heroes.size(); i++) {
                JLabel label = new JLabel("<html>Name: " + heroes.get(i).getName() + "<br/>" +
                        "Type: " + heroes.get(i).getType() + "<br/>" +
                        "Level: " + heroes.get(i).getLevel() + "<br/>" +
                        "Experience: " + heroes.get(i).getExperience() + "<br/>" +
                        "Attack Increase: " + heroes.get(i).getAttack() + "<br/>" +
                        "Defence Increase: " + heroes.get(i).getDefence() + "<br/>" +
                        "Hit Points Increase: " + heroes.get(i).getHitPoints() + "<br/>" +
                        "</html>");

                int k = i + 1;
                int y = (150 * k) - 80;

                label.setBounds(10, y, 200, 110);
                panel.add(label);

                JButton button = new JButton(heroes.get(i).getName() + " ID: " + heroes.get(i).getID());
                button.addActionListener(this);
                button.setBounds(10, y + 110, 150, 30);
                panel.add(button);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error while making labels -> Menu.java -> displayMenu(): " + e);
        }

        thor.addActionListener(e -> {
            frame.dispose();
            this.controller.setChosenHero(new Thor());
        });
        jedi.addActionListener(e -> {
            frame.dispose();
            this.controller.setChosenHero(new Jedi());
        });
        batman.addActionListener(e -> {
            frame.dispose();
            this.controller.setChosenHero(new Batman());
        });

        frame.setPreferredSize(new Dimension(600, 700));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String[] btnText = e.getSource().toString().split(",");
            String[] text = btnText[btnText.length - 2].split(" ");
            String chosen = text[2];
            int index = Integer.parseInt(chosen);
            boolean notFound = true;

            frame.dispose();

            for (Hero heroTobe: heroes) {
                if (heroTobe.getID() == index) {
                    this.controller.setChosenHero(heroTobe);
                    notFound = false;
                    break;
                }
            }
            if (notFound) {
                System.out.println("Character with ID of '" + index + "' was not found! -> Menu.java -> actionPerformed()");
            }
        } catch (IndexOutOfBoundsException | NullPointerException r) {
            System.out.println("Error while assigning chosen character -> Menu.java -> actionPerformed(): " + r);
        }

    }
}
