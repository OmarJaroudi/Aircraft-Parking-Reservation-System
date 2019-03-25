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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			
			String u = username.getText();
			String p = password.getText();
			ConnectionClass connect = new ConnectionClass();
			Connection connection = connect.getConnection();
			Statement statement=connection.createStatement();
			ResultSet r = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.username = '"+u+"'");
			if(!r.next()) {
				label1.setText("Username doesn't exist!");
			}
			else {
				label1.setText(" ");
				r = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.password = '"+p+"' and t.username = '"+u+"'");
				if(!r.next()) {
					label2.setText("Wrong password!");
				}
				else {
					label2.setText(" ");
					((Node)(event.getSource())).getScene().getWindow().hide();
					Stage controlStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/application/controller.fxml"));
					Scene scene = new Scene(root,400,400);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					controlStage.setScene(scene);
					controlStage.setResizable(false);
					controlStage.show();
				}
			}
		}
	}
	public static boolean ValidEmail (String email) {
		if(email.length()<=8)
			return false;
		   for(int i=0;i<email.length();i++) {
			   if(email.charAt(i)=='@' && i!=0 && i!=email.length()-3) {
				   for(int j=i+1;j<email.length();j++) {
					   if(email.charAt(j)=='.' && j!=email.length()-1)
						   return true;
				   }
			   }
				   
		   }
		   return false;
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
		
		
		if(event.getSource()==create) {
			ConnectionClass connect = new ConnectionClass();
			Connection connection = connect.getConnection();
			Statement statement=connection.createStatement();
			String u = user.getText();
			String p = pass.getText();
			String e = email.getText();
			boolean unique = false;
			int ID = 0;
			while(unique==false) {
				Random rand = new Random();
				ID = 1000 + rand.nextInt(5000);
				ResultSet t = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.ID = '"+ID+"'");
				if(!t.next())
					unique = true;
			}
			
			
			boolean info = false;
			ResultSet r = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.username = '"+u+"'");
			
			if(!r.next()) {
				r = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.email = '"+e+"'");
				if(!r.next()) {
					if(!ValidEmail(e)) 
						signupLabel.setText("Email not valid!");
						
					else if(pass2.getText().equals(pass.getText())) {
						signupLabel.setText(" ");
						info = true;
					}
					else {
						signupLabel.setText("Passwords don't match!");
					}
				}
				else {
					signupLabel.setText("an account with this email already exists!");
				}
			}
			else {
				signupLabel.setText("username already exists!");
			}
			
			boolean bot = true; 
			if(botText.getText().equals(botLabel.getText())) {
				bot = false;
				botCheck.setText(" ");
			}
			if(info==true && bot==false) {
				statement.executeUpdate("INSERT INTO clientinfo (username,email,password,ID) VALUE ('"+u+"','"+e+"','"+p+"','"+ID+"')");
				((Node)(event.getSource())).getScene().getWindow().hide();
			}
			else {
				String w = randomGenString();
				botLabel.setText(w);
				botCheck.setText("Invalid expression");
			}
			
		}
	}
	
	
	
}
