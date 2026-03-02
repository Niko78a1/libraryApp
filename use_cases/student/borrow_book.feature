Feature: Borrow book
    Description: A user borrows a book from the library
    Actros: User

Scenario: A registered user tries to borrow a book that exist in the library
    # 1. Setup:
    Given that the administrator is logged in
    And there is a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
    And the book is added to the library
    And there is a user with CPR "260699-1307", name "Nikolai K. Skarum", e-mail "nikolai.kurt.skarum@gmail.com"
    And the user is registered with the library

    # 2. Handling:
    When the user with CPR "260699-1307" borrows the book with the signature "Beck99"

    # 3. Verifikation:
    Then the book with signature "Beck99" is borrowed by the user with CPR "260699-1307"

Scenario: A unregistered user tries to borrow a book that exist in the library
    # 1. Setup:
    Given that the administrator is logged in
    And there is a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
    And the book is added to the library
    And there is a user with CPR "260699-1307", name "Nikolai K. Skarum", e-mail "nikolai.kurt.skarum@gmail.com"

    # 2. Handling:
    When the user with CPR "260699-1307" borrows the book with the signature "Beck99"

    # 3. Verifikation:
    Then the error message "User is not registered in the library" is given