// specify the package
package userinterface;

// system imports

import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Properties;
import java.util.Vector;

// project imports
import impresario.IModel;
import model.Scout;
import model.ScoutCollection;
import model.TreeCollection;
import model.TreeTypeCollection;

/** The view that has the first name, last name and email the user uses to search for a scout collection */
//==============================================================
public class SearchTreeType extends View {

    // Model

    // GUI components
    //Text Fields
    private Properties prop;
    private TextField barcodePrefixTF;

    //Buttons
    private Button cancelButton;
    private Button searchButton;
    private TreeTypeCollection treeTypeList = new TreeTypeCollection();



    // For showing error message
    private userinterface.MessageView statusLog;


    // constructor for this class -- takes a model object
    //----------------------------------------------------------
    public SearchTreeType(IModel updateTreeType) {
        super(updateTreeType, "SearchTreeType");

        // create a container for showing the contents
        VBox container = new VBox(10);
        container.setPadding(new Insets(15, 5, 5, 5));

        // create our GUI components, add them to this panel
        container.getChildren().add(createTitle());
        container.getChildren().add(createFormContent());

        // Error message area
        container.getChildren().add(createStatusLog("                          "));

        getChildren().add(container);

        populateFields();
    }


    // Create the label (Text) for the title
    //-------------------------------------------------------------
    private Node createTitle() {

        Text titleText = new Text("Update Tree Type");
        titleText.setWrappingWidth(300);
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.BLACK);

        return titleText;
    }

    // Create the main form content
    //-------------------------------------------------------------
    private VBox createFormContent() {
        VBox vbox = new VBox(30);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        //TextFields
        barcodePrefixTF = new TextField();
        //Labels
        Label barcodePrefixLabel = new Label("Barcode Prefix: ");
        Label prompt = new Label("Enter Barcode Prefix");
        searchButton = new Button("Search");

        grid.add(prompt, 0, 0);
        grid.add(barcodePrefixLabel, 0, 1);
        grid.add(barcodePrefixTF, 1, 1);



        searchButton.setOnAction(e -> processAction(e));


        cancelButton = new Button("Back");
        cancelButton.setOnAction(e -> myModel.stateChangeRequest("Return", null));

        HBox btnContainer = new HBox(100);
        btnContainer.setAlignment(Pos.CENTER);
        btnContainer.getChildren().add(cancelButton);
        btnContainer.getChildren().add(searchButton);


        vbox.getChildren().add(grid);
        vbox.getChildren().add(btnContainer);

        return vbox;
    }

    // Create the status log field
    //-------------------------------------------------------------
    private userinterface.MessageView createStatusLog(String initialMessage) {
        statusLog = new userinterface.MessageView(initialMessage);

        return statusLog;
    }

    //-------------------------------------------------------------
    public void populateFields() {


    }

    // process events generated from our GUI components
    //-------------------------------------------------------------
    public void processAction(Event evt) {
        //myModel.statechangeRequest goes to the ScoutEditTransaction Class ScoutCollectionTransaction

        clearErrorMessage();

        if(barcodePrefixTF.getText().length() == 0){
            displayErrorMessage("Enter Barcode Prefix");
        }
        else{
            myModel.stateChangeRequest("UpdateTreeType", barcodePrefixTF.getText());
        }
    }






    /**
     * Required by interface, but has no role here
     */
    //---------------------------------------------------------
    public void updateState(String key, Object value) {

    }

    /**
     * Display error message
     */
    //----------------------------------------------------------
    public void displayErrorMessage(String message) {
        statusLog.displayErrorMessage(message);
    }

    public void displayMessage(String message) {
        statusLog.displayMessage(message);
    }



    /**
     * Clear error message
     */
    //----------------------------------------------------------
    public void clearErrorMessage() {
        statusLog.clearErrorMessage();
    }
}

