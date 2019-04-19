package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class MainControl {

	@FXML
	private Label label;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button login;
	@FXML
	private Button signup;
	@FXML
	private TextField user;
	@FXML
	private TextField email;
	@FXML
	private PasswordField pass;
	@FXML
	private PasswordField pass2;
	@FXML
	private Button create;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label signupLabel;
	@FXML
	private Label botLabel;
	@FXML
	private Label botCheck;
	@FXML
	private TextField botText;
		
	
	
	public void Login (ActionEvent event) throws Exception {
		Socket s = new Socket("127.0.0.1", 1927);
	    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	    DataInputStream dis = new DataInputStream(s.getInputStream());
	    
		if(event.getSource()==signup) { 
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/sign_up.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		}
		if(event.getSource()==login) {
			dos.writeUTF("LOGIN"+"/"+username.getText() + "/" + password.getText());
			String res = dis.readUTF();
			if(res.equals("invalid username"))
				label1.setText(res);
			else if(res.equals("invalid password")) {
				label1.setText(" ");
				label2.setText(res);
			}
			else {
				label1.setText(" ");
				label2.setText(" ");
				((Node)(event.getSource())).getScene().getWindow().hide();
				Stage controlStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/application/controller.fxml"));
				Scene scene = new Scene(root,600,600);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				controlStage.setScene(scene);
				controlStage.show();
			} 

		}
		
	}
	
	 
	public String randomGenString() {
		 String word="";
		    Random random = new Random();
		      
		        for(int j = 0; j < 3; j++)
		        {
		            word = word + (char)('a' + random.nextInt(26)) + (char)('A' + random.nextInt(26));
		        }
		    return word;
	}
	public void Signup(ActionEvent event) throws Exception {
		Socket s = new Socket("127.0.0.1", 1927);
	    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	    DataInputStream dis = new DataInputStream(s.getInputStream());
		
		if(event.getSource()==create) {
			dos.writeUTF("CREATE"+"/"+user.getText() + "/" + pass.getText() +"/" + pass2.getText() + "/" + email.getText() + "/" + botText.getText() + "/" + botLabel.getText());
			String res = dis.readUTF();
			if(!res.equals(" ") && !res.equals("Invalid expression")) {
				signupLabel.setText(res);
			}
			else if (res.equals("Invalid expression")) {
				String w = randomGenString();
				botLabel.setText(w);
			}
			else {
				((Node)(event.getSource())).getScene().getWindow().hide();
				Stage controlStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
				Scene scene = new Scene(root,400,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				controlStage.setScene(scene);
				controlStage.setResizable(false);
				controlStage.show();
			}
		}
	}
	
	
	
	
}
