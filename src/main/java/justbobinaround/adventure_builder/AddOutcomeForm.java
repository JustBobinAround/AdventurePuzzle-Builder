package justbobinaround.adventure_builder;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class AddOutcomeForm {
	private AdventureBuilder ab;
	private int editingIndex = -1;
	@FXML
	private TextArea infoArea;
	@FXML
	protected void onAddOutcomeAction() throws IOException {
		if(editingIndex>=0){
		ab.story.setOutcomeInfo(editingIndex,infoArea.getText());
		}else{
			ab.story.addOutcome(infoArea.getText());
		}

		ab.setSceneToStoryEditorForm(editingIndex);
	}
	@FXML
	protected void onCancelAction() throws IOException {
		ab.setSceneToStoryEditorForm(editingIndex);
	}
	public void setAdventureBuilder(AdventureBuilder ab){
		this.ab = ab;
	}
	public void setAdventureBuilder(AdventureBuilder ab, int outcomeIndex){
		this.ab = ab;
		editingIndex = outcomeIndex;
		infoArea.setText(ab.story.outcomes.get(outcomeIndex).info);
	}
}
