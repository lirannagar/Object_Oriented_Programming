import javafx.scene.paint.Color;

public interface IContactView {
	void showWordContact(String[] words);
	void showOnTextField(String[] words);
	void valuesContact(String[] words);
	void printMsg(String str);
	void ifEmpty(boolean EmptyOrNot);
	void ifExist(boolean existOrNot);
	void showColor(Color c);
	
	void booleanFlaseAndEditAction(boolean falseB);
	void booleanTrueAndUpdateAction(boolean trueB);
	
}
