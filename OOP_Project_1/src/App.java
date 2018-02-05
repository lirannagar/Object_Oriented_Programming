import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application  implements IFinalContact {

	static Controller con = new Controller(); 

	public static void main(String[] args) throws IOException {
		launch(args);
	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		IContactView view = new  ViewJFX(primaryStage);
	
		
		ContactsManager cm = null; 
		ContactsManagerFrame cmf = null;
	
		
		try {
			cm = new ContactsManager(NAME_FILE);
			cmf = new ContactsManagerFrame();
			cmf.init();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();

		}
		
		con.addView(view);
		con.setModel(cm);
		con.addView( cmf);
		//Start the first contact
		con.init();
	}




}
