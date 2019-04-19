package application;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Server {
    private int port = 1927;
    private ServerSocket serverSocket;
    static final String databaseURL = "jdbc:mysql://localhost/clients";
    public Server(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void acceptConnections(){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Failed to instantiate server socket.");
            e.printStackTrace();
            System.exit(0);
        }
        while(true){
            try {
                Socket newConnection = serverSocket.accept();
                ServerThread st = new ServerThread(newConnection);
                System.out.println("Connected to Client...");
                new Thread(st).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.acceptConnections();
    }
    public class ServerThread implements Runnable {
        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;

        public ServerThread(Socket socket){
            this.socket = socket;
        }
        

    	public boolean ValidEmail (String email) {
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
        
        public void run(){
            String clientCommand = "";
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(databaseURL,"root","");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            } catch (IOException e) {
                System.err.println("Failed to get input/output streams");
                e.printStackTrace();
            }
            try {
                clientCommand = dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean conversationActive = true;
            while (conversationActive){
                String result = " good ";
                ArrayList<String> commandArguments = new ArrayList<>(Arrays.asList(clientCommand.split("/")));
                
                if(commandArguments.get(0).equals("Q")){
                    conversationActive = false;
                }
                else if (commandArguments.get(0).toUpperCase().equals("LOGIN")){
                	try {
						Statement statement=connection.createStatement();
						ResultSet r = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.username = '"+commandArguments.get(1)+"'");
						if(!r.next()) {
							result="invalid username";
						}
						else {
							ResultSet r2 = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.password = '"+commandArguments.get(2)+"' and t.username = '"+commandArguments.get(1)+"'");;
							if(!r2.next()) {
								result="invalid password";
							}
							else {
								result ="verified";
							}
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                }
                else if(commandArguments.get(0).toUpperCase().equals("CREATE")){
                	boolean unique = false;
        			int ID = 0;
        			Statement statement = null;
        			try {
        				statement = connection.createStatement();
        			
	        			while(unique==false) {
	        				Random rand = new Random();
	        				ID = 1000 + rand.nextInt(5000);
	        				ResultSet t = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.ID = '"+ID+"'");
	        				if(!t.next())
	        					unique = true;
	        			}
        			}
        			catch(SQLException e) {}
	        			
        			
        			boolean info = false; 
						try {
							
							ResultSet t = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.ID = '"+ID+"'");
	        				if(!t.next())
	        					unique = true; 
	        				ResultSet r = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.username = '"+commandArguments.get(1)+"'");
	        				
	        				if(!r.next()) {
	        					r = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.email = '"+commandArguments.get(4)+"'");
	        					if(!r.next()) {
	        						if(!ValidEmail(commandArguments.get(4))) 
	        							result = "Email not valid!";
	        							
	        						else if(commandArguments.get(2).equals(commandArguments.get(3))) {
	        							info = true;
	        						}
	        						else {
	        							result = "Passwords don't match!";
	        						}
	        					}
	        					else {
	        						result = "an account with this email already exists!";
	        					}
	        				}
	        				else {
	        					result = "username already exists!";
	        				}
	        				
	        				boolean bot = true; 
	        				
	        				if(info==true) {
	        					if(commandArguments.get(5).equals(commandArguments.get(6))) {
		        					bot = false;
		        				}
	        					else {
	        						result = "Invalid expression";
	        					}
	        					if(bot==false) {
		        					statement.executeUpdate("INSERT INTO clientinfo (username,email,password,ID) VALUE ('"+commandArguments.get(1)+"','"+commandArguments.get(4)+"','"+commandArguments.get(2)+"','"+ID+"')");
		        					result = " ";
	        					}
	        				}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				
        			
                }
                clientCommand = "";
                try {
                    dataOutputStream.writeUTF(result);
                    dataOutputStream.flush();
                } catch (IOException e) {
                    conversationActive = false;
                    //e.printStackTrace();
                }
            }
            try {
                dataOutputStream.close();
                dataInputStream.close();
                socket.close();
            } catch (IOException e) {
            }
            finally{
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
