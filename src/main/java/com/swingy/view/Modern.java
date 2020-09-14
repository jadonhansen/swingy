package com.swingy.view;

import com.swingy.artifacts.Artifact;
import com.swingy.characters.heroes.Hero;
import com.swingy.gameplay.Move;
import com.swingy.Controller;
import com.swingy.Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Modern {

    private JPanel panel;
    private JFrame frame;
    private final JLabel info = new JLabel();
    private final JLabel stats = new JLabel();
    private final JLabel extra = new JLabel();
    private final JLabel question = new JLabel();
    private final JButton up = new JButton("Up");
    private final JButton down = new JButton("Down");
    private final JButton left = new JButton("Left");
    private final JButton right = new JButton("Right");
    private final JButton run = new JButton("Run");
    private final JButton fight = new JButton("Fight");
    private final JButton take = new JButton("Take");
    private final JButton drop = new JButton("Drop");
    private final JButton console = new JButton("Console");
    private final JButton menu = new JButton("Quit to Menu");
    private final ArrayList<JLabel> labelMapArr = new ArrayList<>();
    private final Model model;
    private final Move moving;
    private final Controller controller;
    private final int roof;

    public Modern(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
        this.moving = new Move();
        roof = 180 + model.getMap().length * 20;
    }

    public void guiGenerate() {
        frame = new JFrame();
        frame.setSize(800, roof + 130);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        question.setBounds(10, roof + 40, 500, 20);
        info.setBounds(10, roof + 40, 400, 20);
        extra.setBounds(10, roof + 20, 600, 20);

        header();
        stats();
        moveButtons();
        fightRunButtons();
        takeDropButtons();
        extraButtons();
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

    private void stats() {
        try {
            Hero temp = model.getChosenHero();
            String text = "<html>Hero: "+ temp.getName() + " (" + temp.getType() + ")" + "<br/>" +
                    "Level: " + temp.getLevel() + "<br/>" +
                    "Experience: " + temp.getExperience() + "<br/>" +
                    "Attack: " + temp.getAttack() + "<br/>" +
                    "Defense: " + temp.getDefence() + "<br/>" +
                    "Hit Points: " + temp.getHitPoints() + "<br/>";
            if (temp.getArtifacts() != null) {
                text += "Artifacts: " + temp.getArtifacts().size() + "</html>";
            } else {
                text += "Artifacts: 0</html>";
            }
            stats.setText(text);
            stats.setBounds(10, 60, 250, 110);
            panel.add(stats);
            render();
        } catch (NullPointerException e) {
            System.out.println("Error while creating stats -> Modern.java -> stats() -> NullPointerException " + e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error while creating stats -> Modern.java -> stats() -> IndexOutOfBoundsException " + e);
        }
    }

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
            stats();
            displayMap(firstPaint, mapArr);
            borderCheck();
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
                    label.setBounds(10, 180 + (i * 20), mapArr.size() * 20, 20);
                    labelMapArr.add(label);
                    panel.add(label);
                }
            } else {
                for (int i = 0; i < mapArr.size(); i++) {
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

    private void fightRunButtons() {
        fight.setBounds(10, roof + 70, 70, 30);
        run.setBounds(80, roof + 70, 70, 30);
        fight.addActionListener(e -> {
            panel.remove(fight);
            panel.remove(run);
            panel.remove(question);
            render();
            fighting();
        });

        run.addActionListener(e -> {
            panel.remove(fight);
            panel.remove(run);
            panel.remove(question);
            render();
            if (controller.run()) {
                extra.setText("You're one lucky guy!");
                panel.add(extra);
                addMoveButtons();
                createMap(false);
            } else {
                fighting();
            }
        });
    }

    private void fighting() {
        if (controller.fight()) {
            extra.setText("You won this battle...but I'll be back!");
            panel.add(extra);
            artifactView();
        } else {
            lose();
        }
    }

    private void artifactView() {
        if (model.getArtifactDrop() != null) {
            Artifact art = model.getArtifactDrop();

            question.setText("An artifact has been dropped! Do you want to take it or drop it?");
            panel.add(question);

            extra.setText("Artifact: " + art.toString() + ", Attack Increase: " + art.getAttack() +
                    ", Defence Increase: " + art.getDefense() + ", Hit Points Increase: " + art.getHitPoints());
            panel.add(extra);

            addTakeOrDropButtons();
        } else {
            addMoveButtons();
            createMap(false);
        }
    }

    private void takeDropButtons() {
        take.setBounds(10, roof + 70, 70, 30);
        drop.setBounds(80, roof + 70, 70, 30);
        take.addActionListener(e -> {
            controller.addArtifact(model.getArtifactDrop());
            takeDropBtnTasks("You obtained an artifact!");
        });

        drop.addActionListener(e -> {
            takeDropBtnTasks("No artifact obtained!");
        });
    }

    private void takeDropBtnTasks(String infoStr) {
        panel.remove(take);
        panel.remove(drop);
        panel.remove(question);
        addMoveButtons();

        info.setText(infoStr);
        panel.add(info);
        render();
        createMap(false);
    }

    private void moveButtons() {
        up.setBounds(10, roof + 70, 70, 30);
        down.setBounds(80, roof + 70, 70, 30);
        left.setBounds(150, roof + 70, 70, 30);
        right.setBounds(220, roof + 70, 70, 30);

        addMoveButtons();

        up.addActionListener(a -> {
            makeMove("w");
        });
        down.addActionListener(a -> {
            makeMove("s");
        });
        left.addActionListener(a -> {
            makeMove("a");
        });
        right.addActionListener(a -> {
            makeMove("d");
        });
    }

    private void makeMove(String move) {
        panel.remove(info);
        panel.remove(extra);
        panel.remove(question);
        render();

        if (!(controller.move(move))) {
            displayPrepFightRun();
        } else {
            createMap(false);
        }
    }

    private void displayPrepFightRun() {
        panel.remove(up);
        panel.remove(down);
        panel.remove(left);
        panel.remove(right);

        addFightOrRunButtons();

        question.setText("You have encountered " + model.getVillainToFight() + "! Do you want to fight or run?");
        panel.add(question);

        render();
    }

    private void borderCheck() {
        if (moving.reachedBorder(this.model)) {
            win();
            controller.save(false);
        }
    }

    private void win() {
        remove();
        JLabel win = new JLabel("You won this round!");
        win.setOpaque(true);
        win.setBackground(Color.GREEN);
        win.setBounds(10, roof + 40, 130, 20);
        panel.add(win);
    }

    private void lose() {
        remove();
        JLabel lose = new JLabel("You lost this round, next time!");
        lose.setOpaque(true);
        lose.setBackground(Color.RED);
        lose.setBounds(10, roof + 40, 200, 20);
        panel.add(lose);
    }

    private void addTakeOrDropButtons() {
        panel.add(take);
        panel.add(drop);
        render();
    }

    private void addFightOrRunButtons() {
        panel.add(fight);
        panel.add(run);
        render();
    }

    private void addMoveButtons() {
        panel.add(up);
        panel.add(down);
        panel.add(left);
        panel.add(right);
        render();
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
        render();
    }

    private void render() {
        frame.validate();
        frame.repaint();
    }
}
