Feature: Overdue book
    Description: A book is overdue if it is borrowed for more than 28 days (4 weeks). 
                 A user with an overdue book For each overdue book the user has to pay a fine of 100 DKK."
    Actors: User


Scenario: Overdue book after 28 days
    # Setup:
    Given that the administrator is logged in
	And there is a user with CPR "260699-1307", name "Nikolai K. Skarum", e-mail "nikolai.kurt.skarum@gmail.com"
	And the user is registered with the library
    And there is a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
    And the book is added to the library
    And the user with CPR "260699-1307" borrows the book with the signature "Beck99"

    # Handling:
    When 29 days have passed

    # Verifikation:
    Then the user with CPR "260699-1307" has overdue books
    And the user with CPR "260699-1307" has to pay a fine of 100 DKK