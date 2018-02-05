import java.io.IOException;

import javafx.scene.paint.Color;

public interface IListener {

	void responsibleContactsChanges(String firstName,String lastName,String phoneNumber, String opertion) throws IOException ;
	
    void responsibleContactSort(String oprtionToSort,String fieldOptionSort, String fieldToSort, String organization) throws IOException;
    
    void exportContactToFile(String format ,String firstName ,String lastName, String phoneNumber  )throws IOException;
    
    void importFromFile(int id, String format) throws ClassNotFoundException, IOException;
    
   // void chackIfFileExist(int id, String getFormateChoosen);
    
    void chackIfFileEmpty() throws IOException;
    
    void printMsgWrongInput(String input);
    
    void inputContactField(String firstName,String lastName, String phoneNumber);
    
    void showToField(String firstName,String lastName, String phoneNumber);
    
    void getContactValues(String firstName,String lastName, String phoneNumber);

	void chackIfFileExist();
	
	void getTheResoult(boolean resoult);
	
	void timerStart(String organization) throws IOException;

	void chackIfTheObjectExist(int convertIdToInteger, String getFormateChoosen);

	void getResultIfExist(boolean ifExist);
	
	void updateColor(Color c);

	void getBoolenTrue();

	void getBoolenFalse();
  
}
