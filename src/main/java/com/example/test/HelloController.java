package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label jmenoLabel, predmetLabel, hodnoceniLabel, errLabel;
    @FXML
    private TextField jmenoField, predmetField, hodnoceniField;
    @FXML
    private ListView<Ucitel> uciteleListWiew;
    @FXML
    private Button dokoncitButton;

    @FXML
    public void initialize() {
        uciteleListWiew.getItems().add(new Ucitel("Jan","Zeměpis",8));
        uciteleListWiew.getItems().add(new Ucitel("Petr","Dějepis",10));
        uciteleListWiew.getItems().add(new Ucitel("Honza","Fyzika",7));
        uciteleListWiew.getItems().add(new Ucitel("Tereza","Matika",6));
        uciteleListWiew.getItems().add(new Ucitel("Adéla","Čeština",8));
        uciteleListWiew.getItems().add(new Ucitel("Marta","Literatura",5));
    }
    @FXML
    protected void handleSelect() {
        Ucitel selectedUcitel = uciteleListWiew.getSelectionModel().getSelectedItem();
        jmenoLabel.setText(selectedUcitel.getJmeno());
        predmetLabel.setText(selectedUcitel.getPredmet());
        hodnoceniLabel.setText(String.valueOf(selectedUcitel.getHodnoceni()));
    }
    @FXML
    protected void onUpravitButtonClick() {
        try{
            Ucitel selectedUcitel = uciteleListWiew.getSelectionModel().getSelectedItem();
            dokoncitButton.setDisable(false);
            errLabel.setText("");
            jmenoField.setText(selectedUcitel.getJmeno());
            predmetField.setText(selectedUcitel.getPredmet());
            hodnoceniField.setText(String.valueOf(selectedUcitel.getHodnoceni()));
        }catch(NullPointerException e){
            errLabel.setText("No item selected");
        }
    }
    @FXML
    protected void onDokoncitButtonClick() {
        try {
            Ucitel selectedUcitel = uciteleListWiew.getSelectionModel().getSelectedItem();
            dokoncitButton.setDisable(true);
            selectedUcitel.setJmeno(jmenoField.getText());
            selectedUcitel.setPredmet(predmetField.getText());
            if (Integer.valueOf(hodnoceniField.getText()) < 1  || Integer.valueOf(hodnoceniField.getText()) > 10) {
                throw new numberNotInRangeExeption("");
            }else {
                selectedUcitel.setHodnoceni(Integer.valueOf(hodnoceniField.getText()));
            }
            jmenoField.clear();
            predmetField.clear();
            hodnoceniField.clear();
        }catch(numberNotInRangeExeption e){
            errLabel.setText("Number not in range (1-10)");
        }catch(NumberFormatException e){
            errLabel.setText("Invalid input");
        }catch(NullPointerException e){
            errLabel.setText("No item selected");
        }
        uciteleListWiew.refresh();
    }
}
