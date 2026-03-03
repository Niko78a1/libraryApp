Feature: Return book
	Description: The user return an borrowed book
	Actors: User

Scenario: A registered user tries to return borrowed book that belongs to the library
    # 1. Setup:
    Given that the administrator is logged in
    And there is a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
    And the book is added to the library
    And there is a user with CPR "260699-1307", name "Nikolai K. Skarum", e-mail "nikolai.kurt.skarum@gmail.com"
    And the user is registered with the library
    And the user with CPR "260699-1307" borrows the book with the signature "Beck99"

    # 2. Handling:
    When the user with CPR "260699-1307" returns the book with the signature "Beck99"

    # 3. Verifikation:
    Then the book with signature "Beck99" is no longer borrowed by the user with CPR "260699-1307"



Scenario: A registered user tries to return a borrowed book that does not belong to the library
    # 1. Setup:
    Given that the administrator is logged in
    And there is a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
    And there is a user with CPR "260699-1307", name "Nikolai K. Skarum", e-mail "nikolai.kurt.skarum@gmail.com"
    And the user is registered with the library

    # 2. Handling:
    When the user with CPR "260699-1307" returns the book with the signature "Beck99"

    # 3. Verifikation:
    Then the error message "This book does not belong to the library" is given

Scenario: A unregistered user tries to return borrowed book that belongs to the library
    # 1. Setup:
    Given that the administrator is logged in
    And there is a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
    And the book is added to the library
    And there is a user with CPR "260699-1307", name "Nikolai K. Skarum", e-mail "nikolai.kurt.skarum@gmail.com"

    # 2. Handling:
    When the user with CPR "260699-1307" returns the book with the signature "Beck99"

    # 3. Verifikation:
    Then the error message "User is not registered in the library" is given

Scenario: A registered user tries to return book that the user has not borrowed
    # 1. Setup:
    Given that the administrator is logged in
    And there is a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
    And the book is added to the library
    And there is a user with CPR "260699-1307", name "Nikolai K. Skarum", e-mail "nikolai.kurt.skarum@gmail.com"
    And the user is registered with the library

    # 2. Handling:
    When the user with CPR "260699-1307" returns the book with the signature "Beck99"

    # 3. Verifikation:
    Then the error message "Book is not borrowed by user" is given
