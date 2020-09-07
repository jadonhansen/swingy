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
    private JLabel question = new JLabel();
    private JLabel extra = new JLabel();
    private JLabel info = new JLabel();
    private final ArrayList<JLabel> labelMapArr = new ArrayList<>();
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
        frame.setSize(800, roof + 130);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        header();
//        stats();
        extraButtons();
        moveButtons();
        createMap(true);

        frame.setPreferredSize(new Dimension(800, roof + 130));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void header() {
        JLabel header = new JLabel("Jadon Hansen - SWINGY - 2020");
        header.setBounds(10,0,250,50);
        panel.add(header);
    }

//    private void stats() {
//        stats = new JLabel("");
//        render();
//    }

    private void createMap(boolean firstPaint) {
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
            displayMap(firstPaint, mapArr);
        } catch (NullPointerException e) {
            System.out.println("Error while creating map array -> Modern.java -> createMap() -> NullPointerException " + e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error while creating map array -> Modern.java -> createMap() -> IndexOutOfBoundsException " + e);
        }
    }

    private void displayMap(boolean firstPaint, ArrayList<String> mapArr) {
        try {
            if (firstPaint) {
                for (int i = 0; i < mapArr.size(); i++) {
                    JLabel label = new JLabel();
                    label.setText(mapArr.get(i));
                    label.setBounds(10, 60 + (i * 20), mapArr.size() * 20, 20);
                    label.setOpaque(true);
                    label.setBackground(Color.GREEN);
                    labelMapArr.add(label);
                    panel.add(label);
                }
            } else {
                for (int i = 0; i < mapArr.size(); i++) {
//                    System.out.println(mapArr.get(i));
                    labelMapArr.get(i).setText(mapArr.get(i));
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Error while displaying game interface -> Modern.java -> mapDisplay(): -> NullPointerException " + e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error while displaying game interface -> Modern.java -> mapDisplay() -> IndexOutOfBoundsException " + e);
        }
        render();
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

    private void displayAndBorderCheck() {
        if (!borderCheck()) {
            addMoveButtons();
        }
        createMap(false);
    }

    private void artifactView() {
        if (model.getArtifactDrop() != null) {
            Artifact art = model.getArtifactDrop();

            question.setText("An artifact has been dropped! Do you want to take it or drop it?");
            question.setBounds(10, roof + 40, 400, 20);
            panel.add(question);

            render();
            takeOrDrop();
        } else {
            displayAndBorderCheck();
        }
    }

    private void takeOrDrop() {
        take.setBounds(10, roof + 70, 70, 30);
        drop.setBounds(80, roof + 70, 70, 30);
        panel.add(take);
        panel.add(drop);

        render();

        take.addActionListener(e -> {
            controller.addArtifact(model.getArtifactDrop());

            panel.remove(take);
            panel.remove(drop);
            panel.remove(question);
            addMoveButtons();

            info = new JLabel("You obtained an artifact!");
            info.setBounds(10, roof + 40, 400, 20);
            panel.add(info);
            createMap(false);
            displayAndBorderCheck();
        });

        drop.addActionListener(e -> {
            panel.remove(take);
            panel.remove(drop);
            panel.remove(question);
            addMoveButtons();

            info = new JLabel("No artifact obtained!");
            info.setBounds(10, roof + 40, 400, 20);
            panel.add(info);
            displayAndBorderCheck();
        });
    }

    private void displayPrepFightRun() {
        panel.remove(up);
        panel.remove(down);
        panel.remove(left);
        panel.remove(right);

        fight.setBounds(10, roof + 70, 70, 30);
        run.setBounds(80, roof + 70, 70, 30);

        question = new JLabel("You have encountered a villain! Do you want to fight or run?");
        question.setBounds(10, roof + 40, 400, 20);
        panel.add(question);
        panel.add(fight);
        panel.add(run);

        render();
    }

    private void fightOrRun() {
        displayPrepFightRun();

        fight.addActionListener(e -> {
            panel.remove(fight);
            panel.remove(run);
            panel.remove(question);
            render();
            if (controller.fight()) {
                extra = new JLabel("You won this battle...but I'll be back!");
                extra.setBounds(10, roof + 20, 400, 20);
                panel.add(extra);
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
            render();
            if (controller.run()) {
                extra = new JLabel("You're one lucky guy!");
                extra.setBounds(10, roof + 20, 400, 20);
                panel.add(extra);
                addMoveButtons();
            } else {
                if (controller.fight()) {
                    extra = new JLabel("You won this battle...but I'll be back!");
                    extra.setBounds(10, roof + 20, 400, 20);
                    panel.add(extra);
                    artifactView();
                } else {
                    lose();
                }
            }
            render();
        });
    }

    private void moveButtons() {
        up.setBounds(10, roof + 70, 70, 30);
        down.setBounds(80, roof + 70, 70, 30);
        left.setBounds(150, roof + 70, 70, 30);
        right.setBounds(220, roof + 70, 70, 30);

        addMoveButtons();

        up.addActionListener(a -> {
            panel.remove(info);
            panel.remove(extra);
            render();
            makeMove("w");
        });
        down.addActionListener(a -> {
            panel.remove(info);
            panel.remove(extra);
            render();
            makeMove("s");
        });
        left.addActionListener(a -> {
            panel.remove(info);
            panel.remove(extra);
            render();
            makeMove("a");
        });
        right.addActionListener(a -> {
            panel.remove(info);
            panel.remove(extra);
            render();
            makeMove("d");
        });
    }

    private void makeMove(String move) {

        if (!borderCheck()) {
            if (!(controller.move(move))) {
                fightOrRun();
            }
            createMap(false);
            borderCheck();
        }
    }

    private boolean borderCheck() {
        Move moving = new Move();
        if (moving.reachedBorder(this.model)) {
            // stats();
            win();
            return true;
        }
        return false;
    }

    private void win() {
        remove();
        JLabel win = new JLabel("You won this round!");
        win.setBounds(10, roof + 40, 200, 20);
        panel.add(win);
        controller.save();
    }

    private void lose() {
        remove();
        JLabel lose = new JLabel("You lost this round, next time!");
        lose.setBounds(10, roof + 40, 200, 20);
        panel.add(lose);
    }

    private void remove() {
        panel.remove(info);
        panel.remove(extra);
        panel.remove(question);
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
        render();
    }

    private void addMoveButtons() {
        panel.add(up);
        panel.add(down);
        panel.add(left);
        panel.add(right);
        render();
    }

    private void render() {
        frame.validate();
        frame.repaint();
    }
}
