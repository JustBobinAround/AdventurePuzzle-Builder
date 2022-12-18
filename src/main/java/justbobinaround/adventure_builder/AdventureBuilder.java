package justbobinaround.adventure_builder;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class AdventureBuilder extends Application {
	public static final String TITLE = "Adventure Story Builder";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public Stage stage;
	
	public Story story;
	public static String lastSave = "";
	//TODO: implement this...
	public static boolean wasEdited = false;
	
	
	@Override
	public void start(Stage stage) throws IOException {
		story = new Story();
		System.out.println(story.toJSON());
		stage.setTitle(TITLE);
		stage.setMinWidth(WIDTH);
		stage.setMinHeight(HEIGHT);
		
		
		this.stage = stage;
		setSceneToStoryEditorForm();
		//new Story();
	}
	
	@FXML
	protected void setSceneToStoryEditorForm() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(AdventureBuilder.class.getResource("StoryEditor-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
		scene.setOnKeyPressed(event -> {
			if(event.isControlDown()&&event.getCode()==KeyCode.S){
				if(!lastSave.equals("") && wasEdited){
					story.saveAsSerialized(lastSave);
					this.stage.setTitle(TITLE + " - Saved:" + lastSave);
				}
			}
		});
		stage.setScene(scene);
		stage.show();
		
		StoryEditorForm storyEditorForm = fxmlLoader.getController();
		storyEditorForm.setAdventureBuilder(this);
	}
	@FXML
	protected void setSceneToStoryEditorForm(int outcomeSelection) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(AdventureBuilder.class.getResource("StoryEditor-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
		scene.setOnKeyPressed(event -> {
			if(event.isControlDown()&&event.getCode()==KeyCode.S){
				if(!lastSave.equals("") && wasEdited){
					story.saveAsSerialized(lastSave);
					this.stage.setTitle(TITLE + " - Saved:" + lastSave);
					wasEdited = false;
				}
			}
		});
		stage.setScene(scene);
		stage.show();
		
		StoryEditorForm storyEditorForm = fxmlLoader.getController();
		storyEditorForm.setAdventureBuilder(this,outcomeSelection);
	}
	@FXML
	protected void setSceneToAddOutcomeForm() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(AdventureBuilder.class.getResource("addOutcome-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
		this.stage.setTitle(TITLE + " - Unsaved:" + lastSave);
		wasEdited = true;
		stage.setScene(scene);
		stage.show();
		
		AddOutcomeForm storyEditorForm = fxmlLoader.getController();
		storyEditorForm.setAdventureBuilder(this);
	}
	@FXML
	protected void setSceneToAddOutcomeForm(int selection) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(AdventureBuilder.class.getResource("addOutcome-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
		this.stage.setTitle(TITLE + " - Unsaved:" + lastSave);
		wasEdited = true;
		stage.setScene(scene);
		stage.show();
		
		AddOutcomeForm outcomeEditorForm = fxmlLoader.getController();
		outcomeEditorForm.setAdventureBuilder(this, selection);
	}
	protected void setSceneToAddChoiceForm(int outcomeSelection) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(AdventureBuilder.class.getResource("addChoice-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
		this.stage.setTitle(TITLE + " - Unsaved:" + lastSave);
		wasEdited = true;
		stage.setScene(scene);
		stage.show();
		
		AddChoiceForm choiceEditorForm = fxmlLoader.getController();
		choiceEditorForm.setAdventureBuilder(this, outcomeSelection);
	}
	protected void setSceneToAddChoiceForm(int outcomeSelection, int choiceSelection) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(AdventureBuilder.class.getResource("addChoice-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
		this.stage.setTitle(TITLE + " - Unsaved:" + lastSave);
		wasEdited = true;
		stage.setScene(scene);
		stage.show();
		
		AddChoiceForm choiceEditorForm = fxmlLoader.getController();
		choiceEditorForm.setAdventureBuilder(this, outcomeSelection, choiceSelection);
	}
	protected void setSceneToStoryInfoForm() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(AdventureBuilder.class.getResource("storyInfo-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
		this.stage.setTitle(TITLE + " - Unsaved:" + lastSave);
		wasEdited = true;
		stage.setScene(scene);
		stage.show();
		
		StoryInfoForm storyInfoForm = fxmlLoader.getController();
		storyInfoForm.setAdventureBuilder(this);
	}
	public static void main(String[] args) {
		
		launch(args );
	}
}