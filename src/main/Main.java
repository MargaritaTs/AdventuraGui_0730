/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.Mapa;
import UI.MenuPole;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.*;

/**
 *
 * @author Rita
 */
public class Main extends Application {
    
    private Mapa mapa;
    private MenuPole menu;
    
    @Override
    public void start(Stage primaryStage) {
        
        IHra hra = new Hra();
        mapa = new Mapa(hra);
        menu = new MenuPole();
        
        BorderPane borderPane = new BorderPane();
                
        TextArea centerText = new TextArea();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false);
        borderPane.setCenter(centerText);
                
        Label zadejPrikazLabel = new Label("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                
      //TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
      //textoveRozhrani.hraj();
                
         TextField zadejPrikazTextField = new TextField("Sem zadej prikaz");
         zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {
                
         @Override
         public void handle(ActionEvent event){
         String zadanyPrikaz = zadejPrikazTextField.getText();
         String odpoved = hra.zpracujPrikaz(zadanyPrikaz);
                  
         centerText.appendText("\n" + zadanyPrikaz + "\n");
         centerText.appendText("\n" + odpoved + "\n");
                  
         zadejPrikazTextField.setText("");
                  
         if(hra.konecHry()){
            zadejPrikazTextField.setEditable(false);
         }
                }
         });
                
         FlowPane dolniPanel = new FlowPane();
         dolniPanel.setAlignment(Pos.CENTER);
         dolniPanel.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
         
         //panel prikaz     
         borderPane.setBottom(dolniPanel);
         
         borderPane.setLeft(mapa);
         //menu adventury
         borderPane.setTop(menu);
         
         Scene scene = new Scene(borderPane, 800, 650);
        
         primaryStage.setTitle("Moje Adventura");
         primaryStage.setScene(scene);
         primaryStage.show();
         
         zadejPrikazTextField.requestFocus();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length == 0){
            launch(args);
            
        } else {
            if (args[0].equals("-text")){
            IHra hra = new Hra();
             TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
             textoveRozhrani.hraj();
          
        }else{
                System.out.println("Neplatny parametr");
                System.exit(1);
                
                }
        }
        
    }
    
}