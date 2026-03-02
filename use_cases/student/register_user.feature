Feature: Register user 
	Description: The administrator registers a user of the library
	Actors: Administrator
	
Scenario: The administrator registers a new user
	Given that the administrator is logged in 
	And there is a user with CPR "020563-4886", name "Helena M. Bertelsen", e-mail "HelenaMBertelsen@rhyta.com " 
	And the user has address street "Slåenhaven 50", post code 4560, and city "Vig" 
	When the administrator registers the user 
	Then the user is a registered user of the library
	
Scenario: Register a user when not logged in as administrator
 	Given that the administrator is not logged in
 	And there is a user with CPR "020563-4886", name "Helena M. Bertelsen", e-mail "HelenaMBertelsen@rhyta.com " 
 	And the user has address street "Slåenhaven 50", post code 4560, and city "Vig" 
 	When the administrator registers the user 
 	Then the error message "Administrator login required" is given
 	
Scenario: Register a user that is already registered
 	Given that the administrator is logged in
	And there is a user with CPR "260699-1307", name "Nikolai K. Skarum", e-mail "nikolai.kurt.skarum@gmail.com"
	And the user is registered with the library
	When the administrator registers the user
	Then the error message "User is already registered" is given