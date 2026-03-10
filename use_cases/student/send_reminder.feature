Feature: Overdue book
    Description: The administrator can choose to send an e-mail reminder to all those users who
                have overdue books. The subject of the e-mail should be "Overdue book(s)" and the text should
                be "You have <n> overdue book(s)", where <n> is the number of overdue books, the user has.
    Actors: User


Scenario: The administrator tries to send a reminder to all the users whom has overdue books
    # Setup:
    Given that the administrator is logged in
    # user1
	And there is a user with CPR "260699-1307", name "Nikolai K. Skarum", e-mail "nikolai.kurt.skarum@gmail.com"
	And the user is registered with the library
    And there is a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
    And the book is added to the library
    And the user with CPR "260699-1307" borrows the book with the signature "Beck99"
    # user2
	And there is a user with CPR "123456-1307", name "Carl Isak", e-mail "carl.isak@gmail.com"
	And the user is registered with the library
    And there is a book with title "Extreme Programming2", author "Kent Beck2", and signature "Beck992"
    And the book is added to the library
    And the user with CPR "123456-1307" borrows the book with the signature "Beck992"
    # tid går
    And 29 days have passed

    # Handling:
    When the administrator sends a reminder to all the users with overdue books

    # Verifikation:
    Then the user with CPR "260699-1307" has received an email with the subject "Overdue book(s)" and the text "You have 1 overdue book(s)"
    And the user with CPR "123456-1307" has received an email with the subject "Overdue book(s)" and the text "You have 1 overdue book(s)"