/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package online_voting_system;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Online_voting_system extends Application {

    private RadioButton party1RadioButton;
    private RadioButton party2RadioButton;
    private RadioButton party3RadioButton;
    private Button voteButton;
    private Button showResultsButton;
    private ToggleGroup partyToggleGroup;
    private int party1Votes;
    private int party2Votes;
    private int party3Votes;
    private TextField nameField;
    private TextField mobileField;
    private Label titleLabel;

    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setTitle("Online Voting System");

        titleLabel = new Label("Online Voting System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        nameField = new TextField();
        mobileField = new TextField();

        VBox inputBox = new VBox(10);
        inputBox.getChildren().addAll(
                titleLabel,
                new Label("Name:"),
                nameField,
                new Label("Mobile:"),
                mobileField
        );

        Button startButton = new Button("Start Voting");
        startButton.setOnAction(event -> showVotingScene());

        inputBox.getChildren().addAll(startButton);

        Scene inputScene = new Scene(inputBox, 300, 250);
        mainStage.setScene(inputScene);
        mainStage.show();
    }
    // It shows all the radio buttons with all the fields.
    private void showVotingScene() {
        VBox voteBox = new VBox(10);

        party1RadioButton = new RadioButton("Party 1");
        party2RadioButton = new RadioButton("Party 2");
        party3RadioButton = new RadioButton("Party 3");

        partyToggleGroup = new ToggleGroup();
        party1RadioButton.setToggleGroup(partyToggleGroup);
        party2RadioButton.setToggleGroup(partyToggleGroup);
        party3RadioButton.setToggleGroup(partyToggleGroup);

        voteButton = new Button("Vote");
        voteButton.setOnAction(event -> castVote());

        showResultsButton = new Button("Show Results");
        showResultsButton.setOnAction(event -> showResults());

        voteBox.getChildren().addAll(
                titleLabel,
                new Label("Name: " + nameField.getText()),
                new Label("Mobile: " + mobileField.getText()),
                party1RadioButton,
                party2RadioButton,
                party3RadioButton,
                voteButton,
                showResultsButton
        );

        Scene voteScene = new Scene(voteBox, mainStage.getWidth(), mainStage.getHeight());
        mainStage.setScene(voteScene);
    }
    // It increments party vote when user select any radio button to give the vote.
    private void castVote() {
        RadioButton selectedRadioButton = (RadioButton) partyToggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            if (selectedRadioButton == party1RadioButton) {
                party1Votes++;
            } else if (selectedRadioButton == party2RadioButton) {
                party2Votes++;
            } else if (selectedRadioButton == party3RadioButton) {
                party3Votes++;
            }
            selectedRadioButton.setSelected(false);
        }
    }

    // Shows the result that this party has won the election.
    private void showResults() {
        Stage resultsStage = new Stage();
        resultsStage.setTitle("Election Results");

        String results = "Party 1: " + party1Votes + " votes\n" +
                "Party 2: " + party2Votes + " votes\n" +
                "Party 3: " + party3Votes + " votes";

        String winner;
        if (party1Votes > party2Votes && party1Votes > party3Votes) {
            winner = "Party 1 wins!";
        } else if (party2Votes > party1Votes && party2Votes > party3Votes) {
            winner = "Party 2 wins!";
        } else if (party3Votes > party1Votes && party3Votes > party2Votes) {
            winner = "Party 3 wins!";
        } else {
            winner = "No clear winner.";
        }

        Label resultLabel = new Label(results + "\n\n" + winner);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> resultsStage.close());

        VBox resultLayout = new VBox(10);
        resultLayout.getChildren().addAll(resultLabel, closeButton);
        Scene resultScene = new Scene(resultLayout, 250, 200);

        resultsStage.setScene(resultScene);
        resultsStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



