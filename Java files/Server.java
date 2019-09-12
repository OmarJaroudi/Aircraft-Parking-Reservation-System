package application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;




public class Server {
	
	
	
	String username;
	int IDnum;
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
    	
    	public boolean EmptyEmail (String email) {
    		if(email.length()==0)
    		   return true;
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

            }
            boolean conversationActive = true;
            while (conversationActive){
                String result = " good ";
                
                ArrayList<String> commandArguments = new ArrayList<>(Arrays.asList(clientCommand.split("/")));
 //**********************************************************************************************************************
                if(commandArguments.get(0).toUpperCase().equals("REFRESH")) {
                	try {
                		result ="";
                		Statement statement=connection.createStatement();
						
						int i=1;
						while(i<=10) {
							ResultSet r = statement.executeQuery("SELECT * FROM data WHERE `Gate Number");
							while(r.next()){
								if(r.getBoolean(2)||r.getBoolean(3)||r.getBoolean(4)||r.getBoolean(5)||r.getBoolean(6)||r.getBoolean(7)||r.getBoolean(8)||r.getBoolean(9)||r.getBoolean(10)||r.getBoolean(11) ||r.getBoolean(12)||r.getBoolean(13)  ) {
									result = result + "Available/";
								}
								else
									result = result + "Unavailable/";
			
							}
							
							i++;
						}
						Clock clock = new Clock();
						String d = clock.getDateString();
						String h = clock.getTimeString();
						statement.executeUpdate("INSERT INTO history (username,ID,Action,Date,Time) VALUE ('"+username+"','"+IDnum+"','view_all_gates','"+d+"','"+h+"')");
                	}
                	
                	catch(SQLException e) {
                		System.out.println("Exception "+e);
                		e.printStackTrace();
                		
                	}
                	
                }
                
                else if(commandArguments.get(0).toUpperCase().equals("LOGOUT")) {
                	try {
                		Statement statement=connection.createStatement();
                		Clock clock = new Clock();
            			String d = clock.getDateString();
            			String h = clock.getTimeString();
                		statement.executeUpdate("INSERT INTO history (username,ID,Action,Date,Time) VALUE ('"+username+"','"+IDnum+"','logout','"+d+"','"+h+"')");
                	}
                	catch(SQLException e) {}
                }
                
                else if(commandArguments.get(0).toUpperCase().equals("REGISTRATION")) {
                	try {
                		result = "Hey";
                		Statement statement=connection.createStatement();
                		int g = Integer.parseInt(commandArguments.get(1));
                		int t = Integer.parseInt(commandArguments.get(2));
                		if(g<1 || g>10 || t<1 || t>12)
                			result = "error!";
                		else {
							ResultSet r = statement.executeQuery("SELECT * FROM data WHERE `Gate Number` = '"+g+"'");
							
							if(r.next() &&r.getBoolean("Time Slot " + t)) {
								Clock clock = new Clock();
								String d = clock.getDateString();
								String h = clock.getTimeString();
								statement.executeUpdate("UPDATE `data` SET `Time Slot "+t+"` = 0 WHERE `Gate Number`= '"+g+"'");
								String reserveInfo = "reserve_gate_" + g + "_timeSlot_" +t;
								statement.executeUpdate("INSERT INTO history (username,ID,Action,Date,Time) VALUE ('"+username+"','"+IDnum+"','"+reserveInfo+"','"+d+"','"+h+"')");
								statement.executeUpdate("INSERT INTO reservehistory (ClientUsername, GateReserved, TimeSlot) VALUE('"+username+"','"+g+"','"+t+"')");
								result = "success";
							}
							else
								result = "unavailable"; 
                		}
                		System.out.println(result);
                	}
                	
                	catch(SQLException e) {
                		System.out.println("Exception "+e);
                		e.printStackTrace();
                		
                	}
                	
                }
                
                else if(commandArguments.get(0).toUpperCase().equals("CANCEL")) {
                	try {
                		result = "Hey";
                		Statement statement=connection.createStatement();
                		int g = Integer.parseInt(commandArguments.get(1));
                		int t = Integer.parseInt(commandArguments.get(2));
                		if(g<1 || g>10 || t<1 || t>12)
                			result = "error!";
                		else {
							ResultSet r = statement.executeQuery("SELECT * FROM reservehistory WHERE `ClientUsername`='"+username+"'and `GateReserved`='"+g+"' and `TimeSlot`='"+t+"'");
							
							if(r.next()) {
								Clock clock = new Clock();
								String d = clock.getDateString();
								String h = clock.getTimeString();
								statement.executeUpdate("UPDATE `data` SET `Time Slot "+t+"` = 1 WHERE `Gate Number`= '"+g+"'");
								String reserveInfo = "cancel_gate_" + g + "_timeSlot_" +t;
								statement.executeUpdate("INSERT INTO history (username,ID,Action,Date,Time) VALUE ('"+username+"','"+IDnum+"','"+reserveInfo+"','"+d+"','"+h+"')");
								statement.executeUpdate("DELETE FROM reservehistory WHERE ClientUsername='"+username+"'and GateReserved='"+g+"' and TimeSlot='"+t+"'");
								result = "success";
							}
							else
								result = "You haven't reserved this slot!"; 
                		}
        
                	}
                	
                	catch(SQLException e) {
                		System.out.println("Exception "+e);
                		e.printStackTrace();
                		
                	}
                	
                }
                
                else if(commandArguments.get(0).toUpperCase().equals("UPDATE")) {
                	try {
                		result = "Hey";
                		Statement statement=connection.createStatement();
                		int g = Integer.parseInt(commandArguments.get(1));
                		if(g<1 || g>10 )
                			result = "error!";
                		else {
                			
                			ResultSet r2 = statement.executeQuery("SELECT * FROM gates WHERE `Gate_Number` = '"+g+"'");
                			r2.next();
							String x = r2.getString(2);
							int y = Integer.parseInt(x);
							y++;
							x = Integer.toString(y);
							ResultSet r = statement.executeQuery("SELECT * FROM data WHERE `Gate Number` = '"+g+"'");
							
							if(r.next() && !r.getBoolean("Time Slot " + 1)&& !r.getBoolean("Time Slot " + 2)&&!r.getBoolean("Time Slot " + 3)&&!r.getBoolean("Time Slot " + 4 )&&!r.getBoolean("Time Slot " + 5) &&!r.getBoolean("Time Slot " + 6) && !r.getBoolean("Time Slot " + 7)&& !r.getBoolean("Time Slot " + 8)&&!r.getBoolean("Time Slot " + 9)&&!r.getBoolean("Time Slot " + 10 )&&!r.getBoolean("Time Slot " + 11) &&!r.getBoolean("Time Slot " + 12)) {
								Clock clock = new Clock();
								String d = clock.getDateString();
								String h = clock.getTimeString();
								
								statement.executeUpdate("UPDATE `gates` SET `Waiting_list_size`= " + x + " WHERE `Gate_Number`= '"+g+"'");
								String reserveInfo = "is_added_to_WL_at_gate" + g;
								statement.executeUpdate("INSERT INTO history (username,ID,Action,Date,Time) VALUE ('"+username+"','"+IDnum+"','"+reserveInfo+"','"+d+"','"+h+"')");
								statement.executeUpdate("INSERT INTO waitinglist (Username,ID,Gate) VALUE ('"+username+"','"+IDnum+"','"+g+"')");
								result = "success";
							}
							else
								result = "This_gate_is_not_totally_reserved!"; 
                		}
                		System.out.println(result);
                	}
                	
                	catch(SQLException e) {
                		System.out.println("Exception "+e);
                		e.printStackTrace();
                		
                	}
                	
                }
                else if(commandArguments.get(0).toUpperCase().equals("WLREFRESH")) {
                	try {
                		result = "Hey";
                		Statement statement=connection.createStatement();
                		
							ResultSet r = statement.executeQuery("SELECT * FROM gates");
							
							while(r.next()){
								
								String x = r.getString(2);
								dataOutputStream.writeUTF(x);	
					
							}
                		
                		System.out.println(result);
                	}
                	
                	catch(SQLException e) {
                		System.out.println("Exception "+e);
                		e.printStackTrace();
                		
                	} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                	
                }
                
                else if(commandArguments.get(0).toUpperCase().equals("OPTIONTWO")) {
                	try {
                		result = "me";
                		Statement statement=connection.createStatement();
						ResultSet r = statement.executeQuery("SELECT * FROM data");
						
						while(r.next()){
							if(r.getString(1).contentEquals(commandArguments.get(1))) {
							
								for(int i=2;i<=13;i++)
								{
									if(r.getBoolean(i))
									{
										dataOutputStream.writeUTF(Integer.toString(i));
									}
					
								}
								break;
							}
		
						}
						Clock clock = new Clock();
						String d = clock.getDateString();
						String h = clock.getTimeString();
						statement.executeUpdate("INSERT INTO history (username,ID,Action,Date,Time) VALUE ('"+username+"','"+IDnum+"','search_gates','"+d+"','"+h+"')");
                	}
                	
                	catch(SQLException e) {
                		System.out.println("Exception "+e);
                		e.printStackTrace();
                		
                	} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                }
                
                
                else if(commandArguments.get(0).toUpperCase().equals("OPTIONTHREE")) {
                	try {
                		result = "me3";
                		Statement statement=connection.createStatement();
						ResultSet r = statement.executeQuery("SELECT * FROM data");
						int x = Integer.parseInt(commandArguments.get(1));
						
						int i=1;
						
						while(r.next()){
							
							        
									if(r.getBoolean(x+1))
									{
										dataOutputStream.writeUTF(Integer.toString(i));
									}
									i++;
									
		
		
						}
						Clock clock = new Clock();
						String d = clock.getDateString();
						String h = clock.getTimeString();
						statement.executeUpdate("INSERT INTO history (username,ID,Action,Date,Time) VALUE ('"+username+"','"+IDnum+"','search_slots','"+d+"','"+h+"')");
                	}
                	
                	catch(SQLException e) {
                		System.out.println("Exception "+e);
                		e.printStackTrace();
                		
                	} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                }
                
                else if(commandArguments.get(0).toUpperCase().equals("HISTORY")) {
                	try {
                		result = "";
                		Statement statement=connection.createStatement();
						ResultSet r = statement.executeQuery("SELECT * FROM clientinfo as t WHERE t.username = '"+username+"' and t.password = '"+commandArguments.get(1)+"'");
						if(!r.next() ||  commandArguments.get(1).isEmpty()) {
							result = "Invalid password";
						}
						else {
								r = statement.executeQuery("SELECT * FROM history WHERE username = '"+username+"'");
								while(r.next()) {
								result = result + r.getString("Action") + "/" + r.getString("Date")  +"/" +r.getString("Time") +"/";
								}
								
						}
							 
                	}
                	catch (SQLException e) {
                		e.printStackTrace();
                		} 
                }
                
                
 //**********************************************************************************************************************               
                else if(commandArguments.get(0).equals("Q")){
                	try {
	                	Statement statement=connection.createStatement();
	                	Clock clock = new Clock();
	        			String d = clock.getDateString();
	        			String h = clock.getTimeString();
	                	statement.executeUpdate("INSERT INTO history (username,ID,Action,Date,Time) VALUE ('"+username+"','"+IDnum+"','force_logout','"+d+"','"+h+"')");
                	} catch (SQLException e) {}
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
								IDnum = r2.getInt("ID");
								username = r2.getString("username");
								Clock clock = new Clock();
								String d = clock.getDateString();
								String h = clock.getTimeString();
		        				statement.executeUpdate("INSERT INTO history (username,ID,Action,Date,Time) VALUE ('"+commandArguments.get(1)+"','"+IDnum+"','login','"+d+"','"+h+"')");
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
	        						
	        						if(commandArguments.get(4).isEmpty() || commandArguments.get(1).isEmpty() || commandArguments.get(2).isEmpty() || commandArguments.get(3).isEmpty()) 
	        						{
	        							result = "Please fill all the fields.";
	        						}
	        						else if(!ValidEmail(commandArguments.get(4))) 
	        							result = "Invalid email!";
	        							
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
	        				Clock clock = new Clock();
	        				String d = clock.getDateString();
	        				String h = clock.getTimeString();
	        				statement.executeUpdate("INSERT INTO history (username,ID,Action,Date,Time) VALUE ('"+commandArguments.get(1)+"','"+ID+"','sign_up','"+d+"','"+h+"')");
	        				
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













