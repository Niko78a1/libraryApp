package dtu.library.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryApp {

	private boolean adminLoggedInAtm = false; // Er admin logget ind?
	private List<Book> books = new ArrayList<>(); // Liste med alle bøger

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

}
