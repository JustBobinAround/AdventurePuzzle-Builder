package justbobinaround.adventure_builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StoryInfoForm {
	private AdventureBuilder ab;
	
	
	@FXML
	private TextField titleField;
	@FXML
	private TextArea infoArea;
	
	
	@FXML
	protected void onAddOutcomeAction(ActionEvent actionEvent) throws IOException {
		ab.story.title = titleField.getText();
		ab.story.info = infoArea.getText();
		ab.setSceneToStoryEditorForm();
	}
	
	@FXML
	protected void onCancelAction(ActionEvent actionEvent) throws IOException {
		ab.setSceneToStoryEditorForm();
	}
	public void setAdventureBuilder(AdventureBuilder ab){
		this.ab = ab;
		titleField.setText(ab.story.title);
		infoArea.setText(ab.story.info);
	}
}
