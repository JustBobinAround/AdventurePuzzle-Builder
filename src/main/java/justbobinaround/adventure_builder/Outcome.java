package justbobinaround.adventure_builder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import com.google.gson.Gson;
public class Outcome implements Serializable {
	public int id = 0;
	public String info = "";
	public ArrayList<Choice> choices = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public ArrayList<Choice> getChoices() {
		return choices;
	}
	
	public void setChoices(ArrayList<Choice> choices) {
		this.choices = choices;
	}
	
	public Outcome(int id, String info){
		this.id = id;
		this.info = info;

	}
	public Outcome(String info){
		this.id = id;
		this.info = info;
	}
	public void addChoice(String info, int pointer, String requiredItem, String givenItem){
		Choice c = new Choice(choices.size(), info, pointer, requiredItem,givenItem);
		choices.add(c);
	}
	public void setChoice(int id,String info,int pointer,String requiredItem,String givenItem){
		choices.get(id).info = info;
		choices.get(id).outcomePointer = pointer;
		choices.get(id).requiredItem = requiredItem;
		choices.get(id).givenItem = givenItem;
	}
	public ObservableList<Choice> getObservableList(){
		return FXCollections.observableList(choices);
	}
	
	public String toJSON(){
		return new Gson().toJson(this);
	}
}
