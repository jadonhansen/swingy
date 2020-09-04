package com.swingy.view;

import com.swingy.Controller;
import com.swingy.Model;
import com.swingy.artifacts.Artifact;
import com.swingy.gameplay.Move;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Modern {

    private JPanel panel;
    private JFrame frame;
    private JLabel stats;
    private JLabel question;
    private JLabel info;
    private final JButton menu = new JButton("Quit to Menu");
    private final JButton console = new JButton("Console");
    private final JButton up = new JButton("Up");
    private final JButton down = new JButton("Down");
    private final JButton left = new JButton("Left");
    private final JButton right = new JButton("Right");
    private final JButton fight = new JButton("Fight");
    private final JButton run = new JButton("Run");
    private final JButton take = new JButton("Take");
    private final JButton drop = new JButton("Drop");
    private final Model model;
    private final Controller controller;
    private final int roof;

    public Modern(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
        roof = 60 + model.getMap().length * 20;
    }

    public void guiGenerate() {
        frame = new JFrame();
        frame.setSize(800, roof + 60);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        header();
//        stats();
        extraButtons();
        moveButtons();
        mapDisplay();

        frame.setPreferredSize(new Dimension(800, roof + 100));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void header() {
        JLabel header = new JLabel("Jadon Hansen - SWINGY - 2020");
        header.setBounds(10,0,250,50);
        panel.add(header);
    }

    private void stats() {
        stats = new JLabel("");
        render();
    }

    private void mapDisplay() {
        char[][] temp = model.getMap();
        ArrayList<String> mapArr = new ArrayList<>();
        String line;

        try {
            for (char[] chars : temp) {
                line = Arrays.toString(chars)
                        .replace('[', ' ')
                        .replace(',', ' ')
                        .replace(']', ' ');

                if (line.contains("J")) {
                    String format = "";
                    for (char aChar : chars) {
                        if (aChar == 'J') {
                            format += "  " + aChar + " ";
                        } else {
                            format += " " + aChar + " ";
                        }
                    }
                    mapArr.add(format);
                } else {
                    mapArr.add(line);
                }
            }
            for (int i = 0; i < mapArr.size(); i++) {
                JLabel label = new JLabel();
                label.setText(mapArr.get(i));
                label.setBounds(10, 60 + (i * 20), mapArr.size() * 20, 20);
                label.setOpaque(true);
                label.setBackground(Color.GREEN);
                panel.add(label);
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Error while displaying game interface -> Modern.java -> mapDisplay(): " + e);
        }
        render();
    }

    private void artifactView() {
        if (model.getArtifactDrop() != null) {
            Artifact art = model.getArtifactDrop();

            question = new JLabel("An artifact has been dropped! Do you want to take it or drop it?");
            question.setBounds(10, roof + 20, 400, 20);
            panel.add(question);

            render();
            takeOrDrop();
        }
    }

    private void takeOrDrop() {
        take.setBounds(10, roof + 50, 70, 30);
        drop.setBounds(80, roof + 50, 70, 30);
        panel.add(take);
        panel.add(drop);

        render();

        take.addActionListener(e -> {
            panel.remove(take);
            panel.remove(drop);
            panel.remove(question);
            panel.add(up);
            panel.add(down);
            panel.add(left);
            panel.add(right);
            // display info that he got artifact
            render();
        });
        drop.addActionListener(e -> {
            panel.remove(take);
            panel.remove(drop);
            panel.remove(question);
            panel.add(up);
            panel.add(down);
            panel.add(left);
            panel.add(right);
            // display info that he didnt want one
            render();
        });
    }

    private void fightOrRun() {
        panel.remove(up);
        panel.remove(down);
        panel.remove(left);
        panel.remove(right);

        fight.setBounds(10, roof + 50, 70, 30);
        run.setBounds(80, roof + 50, 70, 30);

        question = new JLabel("You have encountered a villain! Do you want to fight or run?");
        question.setBounds(10, roof + 20, 400, 20);
        panel.add(question);
        panel.add(fight);
        panel.add(run);

        render();

        fight.addActionListener(e -> {
            panel.remove(fight);
            panel.remove(run);
            panel.remove(question);
            panel.add(up);
            panel.add(down);
            panel.add(left);
            panel.add(right);
            render();
            if (controller.fight()) {
                // display info msg that he won fight
                artifactView();
            } else {
                lose();
            }
            render();
        });
        run.addActionListener(e -> {
            panel.remove(fight);
            panel.remove(run);
            panel.remove(question);
            panel.add(up);
            panel.add(down);
            panel.add(left);
            panel.add(right);
            render();
            if (controller.run()) {
                // display info msg that he run away
            } else {
                if (controller.fight()) {
                    // display info msg that he won fight
                    artifactView();
                } else {
                    lose();
                }
            }
            render();
        });
    }

    private void extraButtons() {
        JButton quit = new JButton("Quit");

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
            controller.displayOptions();
        });
        console.addActionListener(l -> {
            frame.dispose();
            controller.swapView();
        });
    }

    private void moveButtons() {
        up.setBounds(10, roof + 50, 70, 30);
        down.setBounds(80, roof + 50, 70, 30);
        left.setBounds(150, roof + 50, 70, 30);
        right.setBounds(220, roof + 50, 70, 30);

        panel.add(up);
        panel.add(down);
        panel.add(left);
        panel.add(right);

        up.addActionListener(a -> {
            panel.remove(info);
            render();
            makeMove("w");
        });
        down.addActionListener(a -> {
            panel.remove(info);
            render();
            makeMove("s");
        });
        left.addActionListener(a -> {
            panel.remove(info);
            render();
            makeMove("a");
        });
        right.addActionListener(a -> {
            panel.remove(info);
            render();
            makeMove("d");
        });
    }

    private void makeMove(String move) {
        Move moving = new Move();

        if (moving.reachedBorder(this.model)) {
            stats();
            header();
            win();
        } else {
            if (!(controller.move(move))) {
                fightOrRun();
            } else {
                mapDisplay();
            }
        }
    }

    private void win() {
        remove();
        JLabel win = new JLabel("You won this round!");
        win.setBounds(10, roof + 20, 200, 20);
        panel.add(win);
        controller.save();
    }

    private void lose() {
        remove();
        JLabel lose = new JLabel("You lost this round, next time!");
        lose.setBounds(10, roof + 20, 200, 20);
        panel.add(lose);
    }

    private void remove() {
        panel.remove(up);
        panel.remove(down);
        panel.remove(left);
        panel.remove(right);
        panel.remove(take);
        panel.remove(drop);
        panel.remove(fight);
        panel.remove(run);
        panel.remove(console);
        panel.remove(menu);
        panel.remove(info);
        panel.remove(question);
        render();
    }

    private void render() {
        frame.validate();
        frame.repaint();
    }
}
