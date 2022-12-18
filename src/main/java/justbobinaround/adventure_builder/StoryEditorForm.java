package justbobinaround.adventure_builder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;



public class StoryEditorForm {
	private AdventureBuilder ab;
	private String lastSave = "";
	@FXML
	private TableView outcomesTable;
	
	@FXML
	private TableView choicesTable;
	
	@FXML
	protected void onAddOutcomeAction(ActionEvent actionEvent) throws IOException {
		ab.setSceneToAddOutcomeForm();
	}
	@FXML
	protected void onEditOutcomeAction(ActionEvent actionEvent) throws IOException {
		int selection =outcomesTable.getSelectionModel().getSelectedIndex();
		if(selection >=0 && selection<ab.story.outcomes.size()){
			ab.setSceneToAddOutcomeForm(selection);
		}
	}
	@FXML
	protected void onDeleteOutcomeAction(ActionEvent actionEvent) throws IOException {
		int selection =outcomesTable.getSelectionModel().getSelectedIndex();
		if(selection >=0 && selection<ab.story.outcomes.size()){
			ab.story.outcomes.get(selection).choices.remove(selection);
			
		}
	}
	
	@FXML
	protected void onAddChoiceAction(ActionEvent actionEvent) throws IOException {
		int selection = outcomesTable.getSelectionModel().getSelectedIndex();
		if(selection >=0 && selection<ab.story.outcomes.size()){
			ab.setSceneToAddChoiceForm(selection);
		}
	}
	@FXML
	protected void onEditChoiceAction(ActionEvent actionEvent) throws IOException {
		int selection =outcomesTable.getSelectionModel().getSelectedIndex();
		if(selection >=0 && selection<ab.story.outcomes.size()){
			int choiceSelection = choicesTable.getSelectionModel().getSelectedIndex();
			if(choiceSelection>=0 && choiceSelection<ab.story.outcomes.get(selection).choices.size()){
				ab.setSceneToAddChoiceForm(selection,choiceSelection);
			}
		}
	}
	@FXML
	protected void onDeleteChoiceAction(ActionEvent actionEvent) throws IOException {
		int selection =outcomesTable.getSelectionModel().getSelectedIndex();
		if(selection >=0 && selection<ab.story.outcomes.size()){
			int choiceSelection = choicesTable.getSelectionModel().getSelectedIndex();
			if(choiceSelection>=0 && choiceSelection<ab.story.outcomes.get(selection).choices.size()){
				ab.story.outcomes.get(selection).choices.remove(choiceSelection);
				for(int i = 0; i < ab.story.outcomes.get(selection).choices.size(); i++){
					ab.story.outcomes.get(selection).choices.get(i).id = i;
				}
				this.initTables();
			}
		}
	}
	@FXML
	protected void onExportJSONAction(ActionEvent actionEvent){
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("JSON", "*.json")
		);
		fileChooser.setTitle("Export Story as JSON File");
		File file = fileChooser.showSaveDialog(ab.stage);
		if (file != null) {
			ab.story.saveToJSON(file.getAbsolutePath());
		}
	}
	@FXML
	protected void onSaveAction(ActionEvent actionEvent){
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("STORY", "*.story")
		);
		fileChooser.setTitle("Save Story File");
		File file = fileChooser.showSaveDialog(ab.stage);
		if (file != null) {
			ab.story.saveAsSerialized(file.getAbsolutePath());
			AdventureBuilder.lastSave = file.getAbsolutePath();
			AdventureBuilder.wasEdited = false;
		}
	}
	@FXML
	protected void onLoadAction(ActionEvent actionEvent){
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("STORY", "*.story")
		);
		fileChooser.setTitle("Open Story File");
		File file = fileChooser.showOpenDialog(ab.stage);
		if (file != null) {
			ab.story.loadSerialized(file.getAbsolutePath());
			this.initTables();
			AdventureBuilder.lastSave = file.getAbsolutePath();
			AdventureBuilder.wasEdited = false;
		}
	}
	@FXML
	protected void onEditStoryInfoAction(ActionEvent actionEvent) throws IOException {
		ab.setSceneToStoryInfoForm();
	}
	
	private ObservableList<TableColumn<Outcome, ?>> makeDefaultOutcomeColumns(){
		TableColumn<Outcome, Integer> idTable = new TableColumn<>("ID");
		idTable.setMinWidth(30);
		idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Outcome, String> infoTable = new TableColumn<>("Outcome Information");
		infoTable.setMinWidth(200);
		infoTable.setCellValueFactory(new PropertyValueFactory<>("info"));
		
		ObservableList<TableColumn<Outcome, ?>> columns = FXCollections.observableArrayList();
		columns.add(idTable);
		columns.add(infoTable);

		return columns;
	}
	
	private int lastSelection = -1;
	@FXML
	protected void onOutcomeSelectionAction() throws IOException {
		int selection = outcomesTable.getSelectionModel().getSelectedIndex();
		
		if(selection>=0) {
			choicesTable.setItems(ab.story.getObservableOutcomeList().get(selection).getObservableList());
			if(lastSelection == selection){
				onEditOutcomeAction(new ActionEvent());
			}else{
				lastSelection = selection;
			}
		}
		if(choicesTable.getItems().size()>=0){
			choicesTable.getSelectionModel().select(0);
		}
	}
	@FXML
	protected void onChoiceSelectionAction() throws IOException {
		int selection = choicesTable.getSelectionModel().getSelectedIndex();
		
		if(selection>=0) {
			if (lastSelection == selection) {
				onEditChoiceAction(new ActionEvent());
			} else {
				lastSelection = selection;
			}
		}
	}
	
	private ObservableList<TableColumn<Choice, ?>> makeDefaultChoiceColumns(){
		TableColumn<Choice, Integer> idTable = new TableColumn<>("ID");
		idTable.setMinWidth(30);
		idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<Choice, Integer> pidTable = new TableColumn<>("PID");
		pidTable.setMinWidth(30);
		pidTable.setCellValueFactory(new PropertyValueFactory<>("outcomePointer"));
		
		TableColumn<Choice, String> infoTable = new TableColumn<>("Choice Information");
		infoTable.setMinWidth(200);
		infoTable.setCellValueFactory(new PropertyValueFactory<>("info"));
		
		TableColumn<Choice, String> requiredItemTable = new TableColumn<>("Required Item");
		requiredItemTable.setMinWidth(50);
		requiredItemTable.setCellValueFactory(new PropertyValueFactory<>("requiredItem"));
		
		TableColumn<Choice, String> givenItemTable = new TableColumn<>("Given Item");
		givenItemTable.setMinWidth(50);
		givenItemTable.setCellValueFactory(new PropertyValueFactory<>("givenItem"));
		
		ObservableList<TableColumn<Choice, ?>> columns = FXCollections.observableArrayList();
		columns.add(idTable);
		columns.add(pidTable);
		columns.add(infoTable);
		columns.add(requiredItemTable);
		columns.add(givenItemTable);
		
		return columns;
	}
	
	public void initTables() {
		outcomesTable.getColumns().clear();
		outcomesTable.setItems(ab.story.getObservableOutcomeList());
		outcomesTable.getColumns().addAll(makeDefaultOutcomeColumns());
		outcomesTable.getSelectionModel().select(outcomesTable.getItems().size() - 1);
		outcomesTable.scrollTo(outcomesTable.getSelectionModel().getSelectedItem());
		
		choicesTable.getColumns().clear();
		choicesTable.getColumns().addAll(makeDefaultChoiceColumns());
		choicesTable.getSelectionModel().select(choicesTable.getItems().size() - 1);
		choicesTable.scrollTo(choicesTable.getSelectionModel().getSelectedItem());
	}
		
	public void setAdventureBuilder(AdventureBuilder ab){
		this.ab = ab;
		initTables();
		//this.initTables();
	}
	public void setAdventureBuilder(AdventureBuilder ab, int outcomeSelection) throws IOException {
		this.ab = ab;
		initTables();
		if(outcomeSelection>0) {
			choicesTable.setItems(ab.story.getObservableOutcomeList().get(outcomeSelection).getObservableList());
		}
		outcomesTable.getSelectionModel().select(outcomeSelection);
		onOutcomeSelectionAction();
		
		//this.initTables();
	}

	
}