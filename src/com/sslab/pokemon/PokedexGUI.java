package com.sslab.pokemon;

import com.sslab.pokemon.sprite.PokemonSprite;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import  com.sslab.pokemon.Pokedex;

/**
 * Created by jerry on 2017/3/19.
 */
public class PokedexGUI {
    private JLabel hpLabel;
    private JLabel attackLabel;
    private JLabel defenseLabel;
    private JComboBox speciesComboBox;
    private JPanel root;
    private JLabel imageLabel;
    private JLabel spatkLabel;
    private JLabel spdefLabel;
    private JLabel speedLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;

    private ArrayList<JLabel> statLabels;
    Pokedex pokedex;
    public PokedexGUI(){
        statLabels = new ArrayList<>();
        //TODO: Add the "stat" labels into statLabels
        statLabels.add(imageLabel);
        statLabels.add(hpLabel);
        statLabels.add(attackLabel);
        statLabels.add(defenseLabel);
        statLabels.add(spatkLabel);
        statLabels.add(spdefLabel);
        statLabels.add(speedLabel);
        statLabels.add(nameLabel);
        statLabels.add(typeLabel);
        //TODO: Use Pokedex to get pokemon species data
        Pokedex pokedex = new Pokedex("bin/pokemonData.json");
        //TODO: Add items into combobox. Each item should be a concat string of pokemon id and name from pokedex
        for(int i = 0; i <= 20; i++) {
            String name = new String();
            name = i+1 + ": " + pokedex.getPokemonData(i).getSpeciesName();
            speciesComboBox.addItem(name);
        }

        speciesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO update fields when select items in combobox
                JComboBox combo = (JComboBox) e.getSource();
                PokemonSpeciesData pokemon = pokedex.getPokemonData(combo.getSelectedIndex());
                nameLabel.setText(pokemon.getSpeciesName());
                typeLabel.setText(pokemon.getType());
                int[] arr = pokemon.getSpeciesValue().getValArray();
                for(int i = 0; i < 6; i++) {
                    Integer value = arr[i];
                    statLabels.get(i+1).setText(value.toString());
                }
                setPokemonIcon(combo.getSelectedIndex(), imageLabel);
            }
        });
    }

    //set the icon of a label of pokemon according to the id
    private void setPokemonIcon(int id,JLabel label)
    {
        ImageIcon icon = new ImageIcon(PokemonSprite.getSprite(id));
        label.setIcon(icon);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("PokedexGUI");
        frame.setContentPane(new PokedexGUI().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

