package dtu.library.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Calendar;


public class LibraryApp {

	private boolean adminLoggedInAtm = false; // Er admin logget ind?
	private List<Book> books = new ArrayList<>(); // Liste med alle bøger
	private List<User> users = new ArrayList<>(); // Liste med alle registrerede brugere
	private DateServer dateServer = new DateServer(); // dateServer objekt der giver os vores ur eller falske ur

	// Setter for vores dateServer objekt, så vi kan bytte det rigtige ur ud med det falske ur under test
	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;
	}

	public void addBook(Book book) throws OperationNotAllowedException {
		// 1. Tjek om Admin er logget ind
		if(!adminLoggedInAtm){
			// 2. Hvis admin ikke er logget ind smiddet vi en Exception m. fejlbesked
			throw new OperationNotAllowedException("Administrator login required");
		}

		// 3. Hvis Admin er logget ind tilføjer vi bogen
		books.add(book);
	}

	public boolean containsBookWithSignature(String signature) {

		for (Book book : books) {
			if (book.getSignature().equals(signature)){ // Tjekker om signatur passer
				return true;
			}

		}
		return false;
	}

    public boolean adminLoggedIn() {
        return adminLoggedInAtm; 
    }

	public boolean adminLogin(String password) {

		if(password.equals("adminadmin")){
			this.adminLoggedInAtm = true;
			return true;
		}

		return false;
	}

	public void adminLogout() {
		adminLoggedInAtm = false;
	}

	public List<Book> search(String searchText) {
		List<Book> foundBooks = new ArrayList<>(); // Liste med fundne bøger
		
		for(Book book : books){
			if(book.getSignature().contains(searchText) || book.getAuthor().contains(searchText) || book.getTitle().contains(searchText)){
				foundBooks.add(book);
			}
		}

		return foundBooks;
	}

	public void registerUser(User user)  throws OperationNotAllowedException {
		// 1. Tjek om admin er logget ind
		if(!adminLoggedInAtm){
			// 2. Hvis admin ikke er logget ind smider vi en error
			throw new OperationNotAllowedException("Administrator login required");
		}

		// 1. Tjek om brugeren allerede findes på listen
		if(users.contains(user)){
			// 2. Hvis bruger findes i forvejen, smid exception
			throw new OperationNotAllowedException("User is already registered");
		}


		// 1. Løber hele listen af brugere igennem
		for(User u : users){
			// 2. Tjek om cpr nummer er det samme?
			if(u.getCpr().equals(user.getCpr())){
				// 3. Smid fejl hvis CPR nummer eksistere hos anden bruger
				throw new OperationNotAllowedException("User with the same cpr-number is already registered");
			}
		}

		users.add(user);
	}

	public boolean isUser(User user) {
		return users.contains(user);
	}

	public boolean hasBorrowed(String cpr, String signature) {
		for(User u : users) {
			if(u.getCpr().equals(cpr)) {
				List<Book> borrowedBooks = u.getBorrowedBooks();

				for(Book b : borrowedBooks) {
					if(b.getSignature().equals(signature)) {
						return true;
					}
				}
			}
		}

		return false;
	}

    public void borrowBook(String cpr, String signature) throws OperationNotAllowedException{
		User borrower = null;
		Book bookToBorrow = null;

		// 1. Find brugeren
        for(User u : users) {
			if(u.getCpr().equals(cpr)) {
				borrower = u;
				break; // Vi har fundet brugeren, ingen grund til at lede videre
			}
		}

		// 2. Tjek om brugeren blev fundet - hvis ikke, kast fejl
		if (borrower == null) {
			throw new OperationNotAllowedException("User is not registered in the library");
		}

		// 3. Find bogen
		for (Book book : books) {
			if(book.getSignature().equals(signature)) {
				bookToBorrow = book;
				break; // ingen grund til at lede videre efter bog er fundet
			}
		}

		// 4. Tjek om bogen blev fundet, hvis ikke, kast fel
		if (bookToBorrow == null) {
			throw new OperationNotAllowedException("The book that the user is trying to borrow does not exist");
		}

		// 5. Tjek om brugeren allerede har lånt 10 (eller flere) bøger
		if (borrower.getBorrowedBooks().size() >= 10) {
			throw new OperationNotAllowedException("The user has reached the maximum number of borrowed items (10)");
		}

		// 6. Udfør lånet
		borrower.borrowNewBook(bookToBorrow, dateServer.getDate());
    }

	public void returnBook(String cpr, String signature) throws OperationNotAllowedException {
		User borrower = null; 
		Book bookToReturn = null;
		boolean bookBelongsToLibrary = false;

		for (Book b : books) {
			if(b.getSignature().equals(signature)) {
				bookBelongsToLibrary = true;
				break; // Vi har fundet bogen, stop loopet
			}
		}

		// 2. Hvis vi har ledt alle bøger igennem, og den stadig er false
		if (!bookBelongsToLibrary) {
			throw new OperationNotAllowedException("This book does not belong to the library");
		}

		// 1. find bruger
		for (User u : users) {
			if (u.getCpr().equals(cpr)) { // Hvis vi finder brugeren
				borrower = u; // Gem brugeren som låner
				if (!isBorrowed(cpr, signature)) { // Tjekker om brugeren faktisk har lånt bogen...
					throw new OperationNotAllowedException("Book is not borrowed by user");
				}
				borrower.returnBook(signature);
				break; // Ingen grund til at lede videre
			}
		}

		if (borrower == null) {
			throw new OperationNotAllowedException("User is not registered in the library");
		}
	}

    public boolean isBorrowed(String cpr, String signature) {
		User user = null;
		List<Book> borrowedBooks = new ArrayList<>();

        // 1. find bruger
		for (User u : users) {
			if (u.getCpr().equals(cpr)) {
				user = u;
				borrowedBooks = u.getBorrowedBooks(); // 2. Få liste over bøger
				break;
			}
		}

		// 3. gå liste igennem for at se om bog findes
		for (Book b : borrowedBooks) {
			if(b.getSignature().equals(signature)) {
				return true;
			}
		}

		// 4. Hvis bogen ikke findes returner false
		return false;

    }

	public boolean hasOverdueBooks(String cpr) {
		User user = null;

		for (User u : users) {
			if (u.getCpr().equals(cpr)) {
				user = u;
				break;
			}
		}

		if (user == null) return false; // Hvis brugeren ikke eksistere kan det ikke lade sig gøre at have udlånte bøger

		Calendar today = dateServer.getDate();
		for (Book book : user.getBorrowedBooks()) {
			Calendar borrowDate = user.getBorrowDate(book);

			if (borrowDate != null) { // Så længe at borrowdate ikke er en tom værdi
				Calendar dueDate = (Calendar) borrowDate.clone(); // Lav kopi af den dato bogen blev lånt
				dueDate.add(Calendar.DAY_OF_YEAR, 28); // Lig 28 dage til denne kopi

				if (today.after(dueDate)) {
					return true;
				}
			}
		}
		return false;
	}

    public int getFine(String cpr) {
        User user = null;

		// Find brugeren
		for (User u : users) {
			if (u.getCpr().equals(cpr)) {
				user = u;
				break;
			}
		}

		if (user == null) return 0; // når brugeren ikke kan findes --> skal laves som exception

		int totalFine = 0;
		Calendar today = dateServer.getDate(); // den nuværende dato

		//  Gå alle brugerens bøger igennem
		for (Book book : user.getBorrowedBooks()) {
			Calendar borrowDate = user.getBorrowDate(book);

			if (borrowDate != null) {
				Calendar dueDate = (Calendar) borrowDate.clone(); // kopi af lånedato
				dueDate.add(Calendar.DAY_OF_YEAR, 28); // lig 28 dage til lånedato

				// Hvis vi er over 28 dages fristen ligger vi 100 kr til for denne bog
				if (today.after(dueDate)) {
					totalFine += 100;
				}
			}
		}
		return totalFine; 
    }

}
