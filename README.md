# Aircraft-Parking-Reservation-System
This project implements an aircraft parking reservation system. A "client" is able to search and reserve the available aircraft gates for a defined period of time (time slot) depending on the available number of gates and their corresponding reservations. Also, each client should be able to track and log his activity while interacting with the server. More details can be found in the attached pdf description.
The project is implemented using javafx and scene builder to design the GUI, and mySQL to store information in a database
Each fxml file contains the design of the scene as it appears when running the project
The mainController.java contains the implementation of each function used (pushing buttons etc.)


Programming language: Java
IDE: Eclipse
GUI Platform: Javafx (with Scene Builder)
Database: MySQL
Model: Client/Server
OS: Windows 10


Description: There are 10 gates, each gate having 12 time slots, with each slot being 2 hours

///////////////////////////////////////////////////////////////////////////////////

                                      **************** RUNNING THE SERVER ****************

1. Run the SQL database server (using platform like xampp if running on windows)
2. Run Server.main in java project
3. Server is always on from here on out, until connection is terminated by closing the program

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                                       *************** RUNNING THE CLIENT ****************

1. Run the server
2. Run Main.main
3. Client will be prompted to enter login credentials (username and password). After this , they press login.
	If this information is valid, the user will be logged in.
	If not, an error will show on the screen.
		--> Invalid username    <=======> attempting to login with a username that doesn't exist in the clients database
		--> Invalid password    <=======> if the password entered doesn;t match the corresponding username's password in the database 
	If the client doesn't have an account, they can press signup, which directs them to a scene to create an account

4. Sign up: The user is prompted to enter all necessary fields (username, email, password, confirm password), and to confirm
   they are not a bot by completing a CAPTCHA.
   Errors that might pop up on the screen:
   --> "username already exists" <======> if the client attempts to create an account with a username that is already registered
   --> "passwords don't match"   <======> if the 'password' field doesn't match the 'confirm password' field
   --> "Invalid email"           <======> if the client enters an email with an improper format
					  the server has a functionality that checks the validity of emails
						--> email.length > threshhold
						--> email contains an @ symbol
						--> email contains a . symbol after the @ symbol
  -->Invalid expression          <======> if the CAPTCHA value entered by the user is incorrect
					  the user is then presented with a new randomly generated CAPTCHA

  There is a button in the lower right corner that enables the user to go back to the mian menu and cancel the signup process

5. Successful Login: The client is presented with 6 options and a logout option
	Option 1: "View available gates"
	--> The client can view whether each of the 10 gates is available or not
	    The user is presented with the 10 gates, and prompted to press "Refresh"
	    This is to make sure that the results are the most up to date
	    The client is then able to view the status of each gate as "Available" or "Unavailable"
	    NOTE: A gate is available if it contains at least 1 free time slot
	    The user can either go back to main menu (lower right corner), or proceed to reserve a time slot(lower left corner)
		
	Option 2: "View time slots for a gate"
	--> The client can view the availability of all time slots for a certain gate 
	    The client is prompted to enter a valid gate number and press confim
	    Then they will be able to view the status of each time slot for this gate, as "Available" or "Unavailable"
	    The user can either go back to main menu (lower right corner), or proceed to reserve a time slot(lower left corner)

	Option 3: "Search for a specific time slot"
	--> The client can search for a specific time slot and view its availability across all gates
	    The client is prompted to select one of the 12 time slots in front of them
	    Upon doing so, they will be able to view whether the status of this slot at each gate, as "available" or "Unavailable"
	    The user can either go back to main menu (lower right corner), or proceed to reserve a time slot(lower left corner)

	Option 4:"view history of interactions with server"
	--> The client can view their past interactions with server (logins, signups, logouts, reservations, searches)
	    The client is prompted to confirm their identity by entering their valid password and pressing confirm
	    Upon doing so, the client is presented with all transactions recorded on the server since they have signed up
	    Each transaction has a description, timestamp, and date
	     The user can go back to main menu (lower right corner)

	Option 5:"cancel a reservation"
	--> The client can cancel a reservation they have previously made
	    The client is prompted to ennter  a valid gate number and time slot
	    Then the server will verify if the client has previously made a resevration with this info before
    	    If so, the client will have successfully canceled the reservation, and the time slot is made available 
	    for new reservations
            If not, the client is told that they haven't reserved this slot before
	    The user can go back to main menu (lower right corner)

	Option 6:"waiting list"
	--> Client can view and update a waiting list for the gates (in case resevrations are not possible)


	LOGOUT: When in main menu scene, the client has the option to log out (lower right corner)
		By doing so, the client will return to the login scene


/////////////////////////////////////////////////////////////////////////////////////////////////////////////


                                  ***************** BEHIND THE CURTAINS ******************

Client and server communicate  through sockets
Each socket takes a tuple <IP, port>.
In this case IP = 127.0.0.1 and port = 1927
Throughout testing and debugging, the client and server where operated on the same host
It is possible to locate the server on a remote host, but you would have to specify the IP and set up the remote SQL database


                                        *************** VISUALISATION **************

            OUTPUTSTREAM ------------------------>         ---------------------------> INPUTSTREAM  
CLIENT                                           SOCKET(IP,port)                                        SERVER
	    INPUTSTREAM  <------------------------         <--------------------------- OUTPUTSTREAM


The client initiates first contact with the Server by attempting to login ("login" button)
Socket is created at IP = 127.0.0.1 4 , port = 1927
Password and username values are fed to socket output stream
Server socket side receives password and username as input stream 
Server contacts SQL database and verifies the validity of the username and password
Server feeds the result on socket output stream
Client receives result from socket input stream  
Client acts based on result
If result = positive feedback --> login successfull
If result = negative feedback --> display error message

ALL transactions done by the client are also executed accordingly
1. establish connection on socket
2. client sends ouput stream
3. server read input stream
4. server does operations and prepares result
5. server sends result in output stream
6. client reads result from input stream
7. connection closes
8. server waits for next contact


