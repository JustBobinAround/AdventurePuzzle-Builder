package justbobinaround.adventure_builder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class AddChoiceForm {
	
	private AdventureBuilder ab;
	private int outcomeSelection = -1;
	private int choiceSelection = -1;
	
	@FXML
	private TextField infoField;
	@FXML
	protected TableView outcomesTable;
	@FXML
	private ChoiceBox<String> requiredItemsChoiceBox;
	@FXML
	private TextField givenItemField;
	@FXML
	protected void onAddChoiceAction() throws IOException {
		String info;
		int pointer;
		String requiredItem;
		String givenItem = "NO_ITEM";
		
		info = infoField.getText();
		pointer = ((Outcome)(outcomesTable.getSelectionModel().getSelectedItem())).getId();
		requiredItem = requiredItemsChoiceBox.getSelectionModel().getSelectedItem();
		if(givenItemField.getText().length()>0){
			givenItem = givenItemField.getText();
		}
		if(choiceSelection>-1){
			ab.story.outcomes.get(outcomeSelection).setChoice(choiceSelection, info,pointer,requiredItem,givenItem);
		}else{
			ab.story.outcomes.get(outcomeSelection).addChoice(info,pointer,requiredItem,givenItem);
		}
		if(!ab.story.requiredItems.contains(givenItem)){
			ab.story.requiredItems.add(givenItem);
		}
		ab.setSceneToStoryEditorForm(outcomeSelection);
	}
	@FXML
	protected void onCancelAction() throws IOException {
		if(outcomeSelection>-1){
			ab.setSceneToStoryEditorForm(outcomeSelection);
		}
		ab.setSceneToStoryEditorForm(outcomeSelection);
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
	
	public void initTables() {
		outcomesTable.getColumns().clear();
		outcomesTable.setItems(ab.story.getObservableOutcomeList());
		outcomesTable.getColumns().addAll(makeDefaultOutcomeColumns());
		outcomesTable.getSelectionModel().select(outcomesTable.getItems().size() - 1);
		outcomesTable.scrollTo(outcomesTable.getSelectionModel().getSelectedItem());
	}
	public void setAdventureBuilder(AdventureBuilder ab, int outcomeSelection){
		this.ab = ab;
		this.outcomeSelection = outcomeSelection;
		this.initTables();
		requiredItemsChoiceBox.setItems(ab.story.getObservableItemList());
		requiredItemsChoiceBox.getSelectionModel().select(0);
	}
	public void setAdventureBuilder(AdventureBuilder ab, int outcomeSelection, int choiceSelection){
		this.ab = ab;
		this.outcomeSelection = outcomeSelection;
		this.choiceSelection = choiceSelection;
		this.initTables();
		this.infoField.setText(ab.story.outcomes.get(outcomeSelection).choices.get(choiceSelection).info);
		this.outcomesTable.getSelectionModel().select(ab.story.outcomes.get(outcomeSelection).choices.get(choiceSelection).outcomePointer);
		requiredItemsChoiceBox.setItems(ab.story.getObservableItemList());
		requiredItemsChoiceBox.getSelectionModel().select(ab.story.outcomes.get(outcomeSelection).choices.get(choiceSelection).requiredItem);
		this.givenItemField.setText(ab.story.outcomes.get(outcomeSelection).choices.get(choiceSelection).givenItem);
		
	}

	
}
