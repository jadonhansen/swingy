package com.swingy.view;

import com.swingy.characters.heroes.*;
import com.swingy.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu implements ActionListener {

    private JPanel panel;
    private JFrame frame;
    private final Controller controller;
    private final ArrayList<Hero> heroes;
    private final int roof;

    public Menu(ArrayList<Hero> heroes, Controller controller) {
        this.controller = controller;
        this.heroes = heroes;
        roof = 150 + heroes.size() * 190;
    }

    public void displayMenu() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        heading();
        newCharacters();
        savedCharacters();

        if (heroes.size() > 0) {
            frame.setPreferredSize(new Dimension(600, roof));
        } else {
            frame.setPreferredSize(new Dimension(600, 150));
        }

//        JScrollPane scrollingArea = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollingArea.setPreferredSize(new Dimension(600, roof));
//        frame.add(scrollingArea);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void heading() {
        JLabel heading = new JLabel(" SWINGY");
        heading.setBounds(10, 10, 56, 20);
        heading.setOpaque(true);
        heading.setBackground(Color.CYAN);
        panel.add(heading);
    }

    public void newCharacters() {
        JLabel create = new JLabel("Create a new character:");
        create.setBounds(10, 40, 150, 20);

        JButton thor = new JButton("Thor");
        JButton batman = new JButton("Batman");
        JButton jedi = new JButton("Jedi");
        thor.setBounds(10, 70, 100, 30);
        batman.setBounds(120, 70, 100, 30);
        jedi.setBounds(230, 70, 100, 30);

        panel.add(create);
        panel.add(thor);
        panel.add(batman);
        panel.add(jedi);

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
    }

    public void savedCharacters() {
        if (heroes.size() > 0) {
            JLabel exist = new JLabel("Load an existing character:");
            exist.setBounds(10, 120, 200, 20);
            panel.add(exist);
        }

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

                if (i == 0) {
                    label.setBounds(10, 150, 200, 130);
                } else {
                    label.setBounds(10, 150 + (i * 30) + (i * 130), 200, 130);
                }
                panel.add(label);

                JButton button = new JButton(heroes.get(i).getName() + " ID: " + heroes.get(i).getID());
                button.addActionListener(this);
                if (i == 0) {
                    button.setBounds(10, 150 + 130, 150, 30);
                } else {
                    button.setBounds(10, 150 + 130 + (i * 30) + (i * 130), 150, 30);
                }
                panel.add(button);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error while making labels -> Menu.java -> displayMenu(): " + e);
        }
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
