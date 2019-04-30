package application;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MainControl {

	
	@FXML
	private Label	G1;
	@FXML
	private Label	G2;
	@FXML
	private Label	G3;
	@FXML
	private Label	G4;
	@FXML
	private Label	G5;
	@FXML
	private Label	G6;
	@FXML
	private Label	G7;
	@FXML
	private Label	G8;
	@FXML
	private Label	G9;
	@FXML
	private Label	G10;
	
	@FXML
	private Label	error_label;
	
	@FXML
	private TextField wl_gate;
	
	@FXML
	private Label	Gate1;
	@FXML
	private Label	Gate2;
	@FXML
	private Label	Gate3;
	@FXML
	private Label	Gate4;
	@FXML
	private Label	Gate5;
	@FXML
	private Label	Gate6;
	@FXML
	private Label	Gate7;
	@FXML
	private Label	Gate8;
	@FXML
	private Label	Gate9;
	@FXML
	private Label	Gate10;
	
	@FXML
	private Label	cancel_label;
	
	@FXML
	private Button Refresh;
	
	@FXML
	private Button	pickTime;
	
	
	@FXML
	private Label label;
	@FXML
	private TextField username;
	@FXML
	private TextField gate;
	@FXML
	private TextField time;
	@FXML
	private PasswordField password;
	@FXML
	private Button login;
	@FXML
	private Button m1;
	@FXML
	private Button back;
	@FXML
	private Button m2;
	@FXML
	private Button t1;
	@FXML
	private Button t2;
	@FXML
	private Button t3;
	@FXML
	private Button t4;
	@FXML
	private Button t5;
	@FXML
	private Button t6;
	@FXML
	private Button wl_refresh;
	@FXML
	private Button t7;
	@FXML
	private Button t8;
	@FXML
	private Button t9;
	@FXML
	private Button t10;
	@FXML
	private Button t11;
	@FXML
	private Button t12;
	
	@FXML
	private Label l1;
	@FXML
	private Label l2;
	@FXML
	private Label l3;
	@FXML
	private Label l4;
	@FXML
	private Label l5;
	@FXML
	private Label l6;
	@FXML
	private Label l7;
	@FXML
	private Label l8;
	@FXML
	private Label l9;
	@FXML
	private Label l10;
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
	@FXML
	private TextField gate_number;	
	@FXML
	private TextField cancel_gate;
	@FXML
	private TextField cancel_ts;	
	
	@FXML
	private Button ok;
	
	@FXML
	private Button gotoregister;
	
	@FXML
	private Button Register_Button;
	
	@FXML
	private Label slot1;
	
	@FXML
	private Label slot2;
	@FXML
	private Label slot3;
	@FXML
	private Label slot4;
	@FXML
	private Label slot5;
	@FXML
	private Label slot6;
	@FXML
	private Label slot7;
	@FXML
	private Label slot8;
	@FXML
	private Label slot9;
	@FXML
	private Label slot10;
	@FXML
	private Label slot11;
	@FXML
	private Label slot12;
	// options scene
	@FXML
	private Button o1;
	
	@FXML
	private Button o2;
	
	@FXML
	private Button o3;
	
	@FXML
	private Button o4;
	
	@FXML
	private Button o5;
	
	@FXML
	private Button o6;
	
	@FXML
	private Button cancel;
	
	@FXML
	private Button Update;
	
	@FXML
	private Button Add;
	
	@FXML
	private	Button Confirm;
	@FXML
	private PasswordField EnterPass;
	
	@FXML
	private TextField add_gate;
	
	@FXML
	private	ScrollPane	Pane;
	
	@FXML
	private VBox vBox;
	
	@FXML
	private Label RegFail;
	
	@FXML
	private Label PassCheck;
	
	
	@FXML
	private Label wl_label1;
	@FXML
	private Label wl_label2;
	@FXML
	private Label wl_label3;
	@FXML
	private Label wl_label4;
	@FXML
	private Label wl_label5;
	@FXML
	private Label wl_label6;
	@FXML
	private Label wl_label7;
	@FXML
	private Label wl_label8;
	@FXML
	private Label wl_label9;
	@FXML
	private Label wl_label10;
	
	
	boolean[] array = new boolean[14];
	
	public void Available(Label L) {
    	L.setTextFill(Color.GREEN);
    	L.setText("Available");
    }
    public void Unavailable(Label L) { 
    	L.setTextFill(Color.RED);
    	L.setText("Unavailable");
    }
	
    String Name = "";
    public static void close() throws UnknownHostException, IOException {
    	Socket s = new Socket("127.0.0.1", 1927);
	    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	    dos.writeUTF("Q");
    }
	public void option1(ActionEvent event) throws Exception {
		
		 
		
		Socket s = new Socket("127.0.0.1", 1927);
	    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	    DataInputStream dis = new DataInputStream(s.getInputStream());
	    
	    if(event.getSource()==o4) {
	    	((Node)(event.getSource())).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/option4.fxml"));
			Scene scene = new Scene(root,940,644);
			scene.getStylesheets().add(getClass().getResource("new.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> {
				try {
					MainControl.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
	    }
	    
	    if(event.getSource()==o5) {
	    	((Node)(event.getSource())).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/option5.fxml"));
			Scene scene = new Scene(root,940,644);
			scene.getStylesheets().add(getClass().getResource("new.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> {
				try {
					MainControl.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
	    }
	    
	    if(event.getSource()==o6) {
	    	((Node)(event.getSource())).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/option6.fxml"));
			Scene scene = new Scene(root,940,644);
			scene.getStylesheets().add(getClass().getResource("new.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> {
				try {
					MainControl.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
	    }
	    
	   
	    
	    if(event.getSource()==Confirm) {
	    	if(!EnterPass.getText().isEmpty()) {
	        	 dos.writeUTF("HISTORY/" + EnterPass.getText() + "/");
	        	 String res = dis.readUTF();
	        	 ArrayList<String> split = new ArrayList<>(Arrays.asList(res.split("/")));
	        	 if(res.equals("Invalid password"))
	        		 PassCheck.setText("Incorrect");
	        	 else {
	            
	             
		             for(int i = 0;i<split.size();i=i+3) {
		            	 PassCheck.setText(" ");
		            	 Label label1 = new Label("Action:    " +split.get(i));
		            	 Label label2 = new Label("Date:      " + split.get(i+1));
		            	 Label label3 = new Label("Time:     " +split.get(i+2));
		            	 Label label4 = new Label(" ");
		            	 vBox.getChildren().add(label1);
		            	 vBox.getChildren().add(label2);
		            	 vBox.getChildren().add(label3);
		            	 vBox.getChildren().add(label4);
		            	
		             }
			         ((Node)(event.getSource())).getScene().getWindow().setOnCloseRequest(e -> {
							try {
								MainControl.close(); 
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});
			         return;
	        	 }
	    	}
	    	else
	    		PassCheck.setText("error!");
	    } 
	    	
	    if(event.getSource()==pickTime) {
	    	((Node)(event.getSource())).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/option2.fxml"));
			Scene scene = new Scene(root,940,644);
			scene.getStylesheets().add(getClass().getResource("new.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> {
				try {
					MainControl.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			return;
	    }
	    
	    if(event.getSource()==Refresh) {
	    	dos.writeUTF("REFRESH/");
	    	String res = dis.readUTF();
	    	ArrayList<String> gates = new ArrayList<>(Arrays.asList(res.split("/")));
	    	
	    	if(gates.get(0).equals("Available")) 
	    		Available(G1);
	    	else
	    		Unavailable(G1);
	    	
	    	if(gates.get(1).equals("Available")) 
	    		Available(G2);
	    	else
	    		Unavailable(G2);
	    	
	    	if(gates.get(2).equals("Available")) 
	    		Available(G3);
	    	else
	    		Unavailable(G3);
	    	
	    	if(gates.get(3).equals("Available")) 
	    		Available(G4);
	    	else
	    		Unavailable(G4);
	    	
	    	if(gates.get(4).equals("Available")) 
	    		Available(G5);
	    	else
	    		Unavailable(G5);
	    	
	    	if(gates.get(5).equals("Available")) 
	    		Available(G6);
	    	else
	    		Unavailable(G6);
	    	
	    	if(gates.get(6).equals("Available")) 
	    		Available(G7);
	    	else
	    		Unavailable(G7);
	    	
	    	if(gates.get(7).equals("Available")) 
	    		Available(G8);
	    	else
	    		Unavailable(G8);
	    	
	    	if(gates.get(8).equals("Available")) 
	    		Available(G9);
	    	else
	    		Unavailable(G9);
	    	
	    	if(gates.get(9).equals("Available")) 
	    		Available(G10);
	    	else
	    		Unavailable(G10);
	    		
	    	return;
	    }
		if(event.getSource()==ok) {
			
			((Node)(event.getSource())).getScene().getWindow().setOnCloseRequest(e -> {
				try {
					MainControl.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			int g = Integer.parseInt(gate_number.getText());
			
			if(g<=10 || g>=0) {
				
				dos.writeUTF("OPTIONTWO" + "/" + gate_number.getText());
				int k=0;
				
				String x = dis.readUTF();
				
				for(int i=1;i<13;i++)
				{
					array[i]=false;
				}
				
				
				while(x!=null && k<13) {
				
					
					if(x.contentEquals("2")) { 
						slot1.setTextFill(Color.GREEN);
						slot1.setText("Available");
						array[1] = true;
					}
					if(x.contentEquals("3")) {
						slot2.setTextFill(Color.GREEN);
						slot2.setText("Available");
						array[2] = true;
					}
					if(x.contentEquals("4")) {
						slot3.setTextFill(Color.GREEN);
						slot3.setText("Available");
						array[3]=true;
					}
					if(x.contentEquals("5")) {
						slot4.setTextFill(Color.GREEN);
						slot4.setText("Available");
						array[4]=true;
					}
					if(x.contentEquals("6")) {
						slot5.setTextFill(Color.GREEN);
						slot5.setText("Available");
						array[5]=true;
					}
					if(x.contentEquals("7")) {
						slot6.setTextFill(Color.GREEN);
						slot6.setText("Available");
						array[6]=true;
					}
					if(x.contentEquals("8")) {
						slot7.setTextFill(Color.GREEN);
						slot7.setText("Available");
						array[7]=true;
					}
					if(x.contentEquals("9")) {
						slot8.setTextFill(Color.GREEN);
						slot8.setText("Available");
						array[8]=true;
					}
					if(x.contentEquals("10")) {
						slot9.setTextFill(Color.GREEN);
						slot9.setText("Available");
						array[9]=true;
					}
					if(x.contentEquals("11")) {
						slot10.setTextFill(Color.GREEN);
						slot10.setText("Available");
						array[10]=true;
					}
					if(x.contentEquals("12")) {
						slot11.setTextFill(Color.GREEN);
						slot11.setText("Available");
						array[11]=true;
					}
					if(x.contentEquals("13")) {
						slot12.setTextFill(Color.GREEN);
						slot12.setText("Available");
						array[12]=true;
					}
					
					k++;
					x = dis.readUTF();
				}
				
				
				
				if(!array[1]) { slot1.setTextFill(Color.RED); slot1.setText("Unavailable");}
				if(!array[2]) {slot2.setTextFill(Color.RED); slot2.setText("Unavailable");}
				if(!array[3]) {slot3.setTextFill(Color.RED); slot3.setText("Unavailable");}
				if(!array[4]) {slot4.setTextFill(Color.RED); slot4.setText("Unavailable");}
				if(!array[5]) {slot5.setTextFill(Color.RED); slot5.setText("Unavailable");}
				if(!array[6]) {slot6.setTextFill(Color.RED); slot6.setText("Unavailable");}
				if(!array[7]) {slot7.setTextFill(Color.RED); slot7.setText("Unavailable");}
				if(!array[8]) {slot8.setTextFill(Color.RED); slot8.setText("Unavailable");}
				if(!array[9]) {slot9.setTextFill(Color.RED); slot9.setText("Unavailable");}
				if(!array[10]) {slot10.setTextFill(Color.RED); slot10.setText("Unavailable");}
				if(!array[11]) {slot11.setTextFill(Color.RED); slot11.setText("Unavailable");}
				if(!array[12]) {slot12.setTextFill(Color.RED); slot12.setText("Unavailable");}	
				
			}
		}
if(event.getSource()==wl_refresh) {
			
			
			dos.writeUTF("WLREFRESH");
			int k=1;
			
			String x = dis.readUTF();
			while(x!=null && k<13) {
			
				if(k==1) {wl_label1.setText(x);}
				if(k==2) {wl_label2.setText(x);}
				if(k==3) {wl_label3.setText(x);}
				if(k==4) {wl_label4.setText(x);}
				if(k==5) {wl_label5.setText(x);}
				if(k==6) {wl_label6.setText(x);}
				if(k==7) {wl_label7.setText(x);}
				if(k==8) {wl_label8.setText(x);}
				if(k==9) {wl_label9.setText(x);}
				if(k==10) {wl_label10.setText(x);}
				
				k++;
				x = dis.readUTF();
			}
			
		}
if(event.getSource()==cancel) {
			
			dos.writeUTF("CANCEL"+"/" + cancel_gate.getText() + "/" + cancel_ts.getText());
			String res = dis.readUTF();
			if(res.equals("You haven't reserved this slot!") || res.equals("error!"))
				cancel_label.setText(res);
			else {
				((Node)(event.getSource())).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/application/END2.fxml"));
				Scene scene = new Scene(root,940,644);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				primaryStage.setOnCloseRequest(e -> {
				try {
					MainControl.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				});
			}
		}

if(event.getSource()==Update) {
	dos.writeUTF("UPDATE"+"/" + wl_gate.getText());
	String res = dis.readUTF();
	if(res.equals("This_gate_is_not_totally_reserved!") || res.equals("error!"))
		error_label.setText(res);
	else {
		((Node)(event.getSource())).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/END3.fxml"));
		Scene scene = new Scene(root,940,644);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
		try {
			MainControl.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		});
	}
	
}
		
		
		if(event.getSource()==Register_Button) {
			if(gate.getText().isEmpty() || time.getText().isEmpty())
				RegFail.setText("error!");
			else {
				int g = Integer.parseInt(gate.getText());
				int t = Integer.parseInt(time.getText());
				if(g<=10 && g>=0) {
					dos.writeUTF("REGISTRATION"+"/" + gate.getText() + "/" + time.getText());
					String res = dis.readUTF();
					if(res.equals("unavailable") || res.equals("error!"))
						RegFail.setText(res);
					else {
						((Node)(event.getSource())).getScene().getWindow().hide();
						Stage primaryStage = new Stage();
						Parent root = FXMLLoader.load(getClass().getResource("/application/END.fxml"));
						Scene scene = new Scene(root,940,644);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.show();
						primaryStage.setOnCloseRequest(e -> {
						try {
							MainControl.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
						});
					}
				}
			}
		}
		
		if(event.getSource()==o1) {
			((Node)(event.getSource())).getScene().getWindow().hide();
			//dos.writeUTF("OPTIONONE");
			
			
			
			
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/o1.fxml"));
			Scene scene = new Scene(root,940,644);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> {
				try {
					MainControl.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}
	
		if(event.getSource()==m1) {
			dos.writeUTF("LOGOUT/");
			
			((Node)(event.getSource())).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/login.fxml")); // 
			Scene scene = new Scene(root,940,644);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.setResizable(false);
			//primaryStage.setMaximized(true);
			primaryStage.show();
			
			}	
		if(event.getSource()==o2) {
			((Node)(event.getSource())).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/option2.fxml"));
			Scene scene = new Scene(root,940,644);
			scene.getStylesheets().add(getClass().getResource("new.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> {
				try {
					MainControl.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			
		}
	
	if(event.getSource()==o3) {
		((Node)(event.getSource())).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/option3.fxml"));
		Scene scene = new Scene(root,940,644);
		scene.getStylesheets().add(getClass().getResource("new.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			try {
				MainControl.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
	}
	
	if(event.getSource()==t1) {
		dos.writeUTF("OPTIONTHREE"+"/"+"1");
		
		
	}
	
	if(event.getSource()==t2) {
		dos.writeUTF("OPTIONTHREE"+"/"+"2");
		
		
	}
	
	if(event.getSource()==t3) {
		dos.writeUTF("OPTIONTHREE"+"/"+"3");
		
		
	}
	
	if(event.getSource()==t4) {
		dos.writeUTF("OPTIONTHREE"+"/"+"4");
		
		
	}
	
	if(event.getSource()==t5) {
		dos.writeUTF("OPTIONTHREE"+"/"+"5");
		
		
	}
	if(event.getSource()==t6) {
		dos.writeUTF("OPTIONTHREE" +"/"+"6");
		
		
	}
	if(event.getSource()==t7) {
		dos.writeUTF("OPTIONTHREE"+"/"+"7");
		
		
	}
	
	if(event.getSource()==t8) {
		dos.writeUTF("OPTIONTHREE"+"/"+"8");
		
		
	}
	
	if(event.getSource()==t9) {
		dos.writeUTF("OPTIONTHREE"+"/"+"9");
		
		
	}
	
	if(event.getSource()==t10) {
		dos.writeUTF("OPTIONTHREE"+"/"+"10");
		
		
	}
	
	if(event.getSource()==t11) {
		dos.writeUTF("OPTIONTHREE"+"/"+"11");
		
		
	}
	
	if(event.getSource()==t12) {
		dos.writeUTF("OPTIONTHREE"+"/"+"12");
		
		
	}
	
	if(event.getSource()==t1 || event.getSource()==t2 ||event.getSource()==t3||event.getSource()==t4||event.getSource()==t5||event.getSource()==t6||event.getSource()==t7||event.getSource()==t8||event.getSource()==t9||event.getSource()==t10||event.getSource()==t11||event.getSource()==t12) {
		
		
		for(int i=1;i<11;i++)
		{
			array[i]=false;
		}
		
		String x = dis.readUTF();
		int k=0;
		while(x!=null && k<12) {
		
			
			if(x.contentEquals("1")) { 
				l1.setTextFill(Color.GREEN);
				l1.setText("Available");
				array[1] = true;
			}
			if(x.contentEquals("2")) {
				l2.setTextFill(Color.GREEN);
				l2.setText("Available");
				array[2] = true;
			}
			if(x.contentEquals("3")) {
				l3.setTextFill(Color.GREEN);
				l3.setText("Available");
				array[3]=true;
			}
			if(x.contentEquals("4")) {
				l4.setTextFill(Color.GREEN);
				l4.setText("Available");
				array[4]=true;
			}
			if(x.contentEquals("5")) {
				l5.setTextFill(Color.GREEN);
				l5.setText("Available");
				array[5]=true;
			}
			if(x.contentEquals("6")) {
				l6.setTextFill(Color.GREEN);
				l6.setText("Available");
				array[6]=true;
			}
			if(x.contentEquals("7")) {
				l7.setTextFill(Color.GREEN);
				l7.setText("Available");
				array[7]=true;
			}
			if(x.contentEquals("8")) {
				l8.setTextFill(Color.GREEN);
				l8.setText("Available");
				array[8]=true;
			}
			if(x.contentEquals("9")) {
				l9.setTextFill(Color.GREEN);
				l9.setText("Available");
				array[9]=true;
			}
			if(x.contentEquals("10")) {
				l10.setTextFill(Color.GREEN);
				l10.setText("Available");
				array[10]=true;
			}
			x = dis.readUTF();
			k++;
	}
		
		if(!array[1]) { l1.setTextFill(Color.RED); l1.setText("Unavailable");}
		if(!array[2]) {l2.setTextFill(Color.RED); l2.setText("Unavailable");}
		if(!array[3]) {l3.setTextFill(Color.RED); l3.setText("Unavailable");}
		if(!array[4]) {l4.setTextFill(Color.RED); l4.setText("Unavailable");}
		if(!array[5]) {l5.setTextFill(Color.RED); l5.setText("Unavailable");}
		if(!array[6]) {l6.setTextFill(Color.RED); l6.setText("Unavailable");}
		if(!array[7]) {l7.setTextFill(Color.RED); l7.setText("Unavailable");}
		if(!array[8]) {l8.setTextFill(Color.RED); l8.setText("Unavailable");}
		if(!array[9]) {l9.setTextFill(Color.RED); l9.setText("Unavailable");}
		if(!array[10]) {l10.setTextFill(Color.RED); l10.setText("Unavailable");}
	
	}
	if(event.getSource()==gotoregister) {
		((Node)(event.getSource())).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Registration.fxml"));
		Scene scene = new Scene(root,940,644);
		scene.getStylesheets().add(getClass().getResource("new.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			try {
				MainControl.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		}
	
	}
    
	public void Login (ActionEvent event) throws Exception {
		Socket s = new Socket("127.0.0.1", 1927);
	    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
	    DataInputStream dis = new DataInputStream(s.getInputStream());
	    
		if(event.getSource()==signup) { 
			((Node)(event.getSource())).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/sign_up.fxml"));
			Scene scene = new Scene(root,940,644);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		}
		if(event.getSource()==login) {
			if(username.getText().isEmpty() || password.getText().isEmpty())
				label2.setText("Empty field(s)!");
			else  {
				dos.writeUTF("LOGIN"+"/"+username.getText() + "/" + password.getText());
				String res = dis.readUTF();
				if(res.equals("invalid username"))
					label1.setText(res);
				else if(res.equals("invalid password") || res.equals("please fill all fields")) {
					label1.setText(" ");
					label2.setText(res);
				}
				else {
					label1.setText(" ");
					label2.setText(" ");
					((Node)(event.getSource())).getScene().getWindow().hide();
					Stage controlStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/application/controller.fxml"));
					Scene scene = new Scene(root,940,644);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					controlStage.setScene(scene);
					controlStage.show();
					Name = username.getText();
					controlStage.setOnCloseRequest(e -> {
						try {
							MainControl.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					});
				}
		}

		}
		
	}
	
	public void Options(ActionEvent event) throws Exception {
		
		
		
		if(event.getSource()==m2) {
			
			((Node)(event.getSource())).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/controller.fxml")); // 
			Scene scene = new Scene(root,940,644);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.setResizable(false);
			//primaryStage.setMaximized(true);
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> {
				try {
					MainControl.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
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
		
	    if(event.getSource()==back) {
			
	    	((Node)(event.getSource())).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/login.fxml")); // 
			Scene scene = new Scene(root,940,644);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			primaryStage.setResizable(false);
			//primaryStage.setMaximized(true);
			primaryStage.show();
			
		}
		

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
				Scene scene = new Scene(root,940,644);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				controlStage.setScene(scene);
				controlStage.setResizable(false);
				controlStage.show();
		
				
			}
		}
	}

	
	
}



















