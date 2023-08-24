package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Interest;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class InterestTableGUI extends Application {
	
    private TextField principleTextField, ratePercentageTextField;
    private Slider yearsSlider;
    private TextArea displayArea;

	@Override
	public void start(Stage primaryStage) {
		int sceneWidth = 600, sceneHeight = 400;
		int verSpaceBetweenNodes = 8, horSpaceBetweenNodes = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;
		
		// set pane properties
		GridPane pane = new GridPane();
		pane.setHgap(horSpaceBetweenNodes);
		pane.setVgap(verSpaceBetweenNodes);
		pane.setPadding(new Insets(paneBorderTop, paneBorderRight, 
					    paneBorderBottom, paneBorderLeft));
		
		// add labels and corresponding text fields
		Label principleLabel = new Label("Principle:");
		Label ratePercentageLabel = new Label("Rate(Percentage):");
		Label numberOfYearsLabel = new Label("Number of Years:");
		principleTextField = new TextField();
		ratePercentageTextField = new TextField();
		pane.add(principleLabel, 0, 27);
		pane.add(principleTextField, 1, 27);				
		pane.add(ratePercentageLabel, 2, 27);
		pane.add(ratePercentageTextField, 3, 27);
		pane.add(numberOfYearsLabel, 0, 30);
		
		// add years slider
		yearsSlider = new Slider();
		yearsSlider.setMin(1);
		yearsSlider.setMax(25);
		yearsSlider.setValue(1);
        yearsSlider.setMajorTickUnit(4);
        yearsSlider.setSnapToTicks(true);
        yearsSlider.setShowTickMarks(true);
        yearsSlider.setShowTickLabels(true);
        yearsSlider.setPrefWidth(120);

		// add years slider to pane
        GridPane.setConstraints(yearsSlider, 1, 35, 3, 1);
        pane.add(yearsSlider, 1, 30);
   
		// create display area
        displayArea = new TextArea();
		displayArea.setEditable(false);
		displayArea.setWrapText(true);	
			
		// add display area to pane
		GridPane.setColumnSpan(displayArea, 4);
		GridPane.setRowSpan(displayArea, 25);
		pane.add(displayArea, 0, 0);

		// add buttons
		Button button1 = new Button("SimpleInterest");
		Button button2 = new Button("CompoundInterest");
		Button button3 = new Button("BothInterests");
		
		// add a box to hold all buttons
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(10);
		buttonBox.getChildren().addAll(button1, button2, button3);
		
		// add button box to pane
		GridPane.setColumnSpan(buttonBox, 4);
		pane.add(buttonBox, 0, 35);
		
		// Using non-anonymous inner class to handle button 1
		button1.setOnAction(new ButtonHandler());
		
		// Using anonymous inner class to handle button 2
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				double principle = Double.parseDouble(principleTextField.getText());
				double ratePercentage = Double.parseDouble(ratePercentageTextField.getText());
		    	int years = (int) yearsSlider.getValue();
		       	Interest interest = new Interest (principle, ratePercentage, years);
				displayArea.setText(
					interest.getCompoundInterest(principle, ratePercentage, years));
			}
		});
		
		// Using lambda expression to handle button 3
		button3.setOnAction(e -> {
			double principle = Double.parseDouble(principleTextField.getText());
			double ratePercentage = Double.parseDouble(ratePercentageTextField.getText());
	    	int years = (int) yearsSlider.getValue();
	       	Interest interest = new Interest (principle, ratePercentage, years);
			displayArea.setText(interest.getBothInterests(principle, ratePercentage, years));
		});	
		
		// Display the stage
		Scene scene = new Scene(pane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {	
			double principle = Double.parseDouble(principleTextField.getText());
			double ratePercentage = Double.parseDouble(ratePercentageTextField.getText());
	    	int years = (int) yearsSlider.getValue();
	       	Interest interest = new Interest (principle, ratePercentage, years);
			displayArea.setText(interest.getSimpleInterest(principle, ratePercentage, years));
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
