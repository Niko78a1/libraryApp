package dtu.library.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryApp {

	private boolean adminLoggedInAtm = false; 

	public void addBook(Book book) throws OperationNotAllowedException {
	}

	public boolean containsBookWithSignature(String signature) {
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

}
