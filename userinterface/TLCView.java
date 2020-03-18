
// specify the package
package userinterface;

// system imports

import impresario.IModel;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

// project imports


/**
 * The class containing the Teller View  for the ATM application
 */
//==============================================================
public class TLCView extends View {

    // GUI stuff
    //private TextField userid;
    //private PasswordField password;
    private Button insertScout;
    private Button updateScout;
    private Button deleteScout;
    private Button submitButton;

    // For showing error message
    //Dispaly area on the bottom of the screen
    private userinterface.MessageView statusLog;

    // constructor for this class -- takes a model object
    //----------------------------------------------------------
    public TLCView(IModel tlc) {
        //Every view class has a model/controller class
        //model/controller is the Teller
        //The root node is the node in which every other element is under


        super(tlc, "TLCView");

        // create a container for showing the contents
        VBox container = new VBox(10);
        //VBox is added to getChildren.add(container)
        //container is a VBox
        //VBox is a vertical Box
        container.setPadding(new Insets(15, 5, 5, 5));

        // create a Node (Text) for showing the title
        container.getChildren().add(createTitle());

        // create a Node (GridPane) for showing data entry fields
        //UserId and Password TF
        container.getChildren().add(createFormContents());

        // Error message area
        //Every GUI screen has a title
        container.getChildren().add(createStatusLog("                          "));
        //Add container to the group
        getChildren().add(container);


        //populateFields();

        // STEP 0: Be sure you tell your model what keys you are interested in
        myModel.subscribe("LoginError", this);
    }

    // Create the label (Text) for the title of the screen
    //-------------------------------------------------------------
    private Node createTitle() {

        Text titleText = new Text("Boy Scout Troop 209");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.DARKGREEN);

        //Title gets added to the container
        return titleText;
    }

    // Create the main form contents
    //-------------------------------------------------------------
    private GridPane createFormContents() {
        //Gridpane is a grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        //H V is horizontal and vertical gaps
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // data entry fields
        //Label userName = new Label("User ID:");



        //Buttons
        insertScout = new Button("Add New Scout");
        insertScout.setOnAction(e -> myModel.stateChangeRequest("NewScout", null));
        updateScout = new Button("Search All Scouts");
        updateScout.setOnAction(e -> myModel.stateChangeRequest("SearchScout", null));



        submitButton = new Button("Done");
        submitButton.setOnAction(e -> Platform.exit());

        grid.add(insertScout, 0, 0);
        grid.add(updateScout, 0, 1);
        grid.add(submitButton, 0, 4);

        return grid;
    }


    // Create the status log field
    //-------------------------------------------------------------
    private userinterface.MessageView createStatusLog(String initialMessage) {

        statusLog = new userinterface.MessageView(initialMessage);

        return statusLog;
    }

    //-------------------------------------------------------------
    // This method processes events generated from our GUI components.
    // Make the ActionListeners delegate to this method
    //-------------------------------------------------------------
    public void processAction(Event evt) {
        // DEBUG: System.out.println("TellerView.actionPerformed()");

        clearErrorMessage();


    }



    /**
     * Process userid and pwd supplied when Submit button is hit.
     * Action is to pass this info on to the teller object
     */
    //----------------------------------------------------------


    //---------------------------------------------------------
    public void updateState(String key, Object value) {
        // STEP 6: Be sure to finish the end of the 'perturbation'
        // by indicating how the view state gets updated.
        if (key.equals("LoginError") == true) {
            // display the passed text
            displayErrorMessage((String) value);
        }

    }

    /**
     * Display error message
     */
    //----------------------------------------------------------
    public void displayErrorMessage(String message) {
        statusLog.displayErrorMessage(message);
    }

    /**
     * Clear error message
     */
    //----------------------------------------------------------
    public void clearErrorMessage() {
        statusLog.clearErrorMessage();
    }

}
