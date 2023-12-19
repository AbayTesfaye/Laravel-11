import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Clerk {
    private String name;
    private String password;

    public Clerk(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}

class AdminPortal {
    private Clerk currentClerk;

    public AdminPortal(Clerk clerk) {
        this.currentClerk = clerk;
    }

    public Clerk getCurrentClerk() {
        return currentClerk;
    }

    // Add additional administrative functions here
}

class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

public class Main {
    private JFrame frame;
    private JTextField usernameTextField;
    private JPasswordField passwordField;

  //  private LibraryManagementSystem libraryManagementSystem;
    private AdminPortal adminPortal;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel welcomePanel = new JPanel();
        welcomePanel.add(new JLabel("Welcome to the Library Management System!"));

        JPanel loginPanel = new JPanel();
        loginPanel.add(new JLabel("Username:"));
        usernameTextField = new JTextField(20);
        loginPanel.add(usernameTextField);
        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordField);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        loginPanel.add(loginButton);

        panel.add(welcomePanel, BorderLayout.NORTH);
        panel.add(loginPanel, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void login() {
        String username = usernameTextField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        // Validate credentials (you may want to replace this with a more secure authentication mechanism)
        if (username.equals("admin") && password.equals("adminpass")) {
            showAdminPortal();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
        }
    }

    private void showAdminPortal() {
        Clerk clerk = new Clerk("Admin", "adminpass");
        adminPortal = new AdminPortal(clerk);

        frame.getContentPane().removeAll();

        JPanel adminPanel = new JPanel(new BorderLayout());
        adminPanel.add(new JLabel("Welcome, " + clerk.getName() + " to the Admin Portal!"), BorderLayout.NORTH);

        JPanel adminFunctionsPanel = new JPanel();
        JButton addClerkButton = new JButton("Add Clerk");
        addClerkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement add clerk functionality
            }
        });
        adminFunctionsPanel.add(addClerkButton);

        JButton addLibrarianButton = new JButton("Add Librarian");
        addLibrarianButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement add librarian functionality
            }
        });
        adminFunctionsPanel.add(addLibrarianButton);

        JButton viewIssuedHistoryButton = new JButton("View Issued Book History");
        viewIssuedHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement view issued book history functionality
            }
        });
        adminFunctionsPanel.add(viewIssuedHistoryButton);

        JButton viewAllBooksButton = new JButton("View All Books in Library");
        viewAllBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement view all books in the library functionality
                List<Book> books = getBooksFromLibrary();

                StringBuilder bookList = new StringBuilder();
                for (Book book : books) {
                    bookList.append("Title: ").append(book.getTitle()).append(", Author: ").append(book.getAuthor()).append("\n");
                }

                JTextArea textArea = new JTextArea(bookList.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                JOptionPane.showMessageDialog(frame, scrollPane, "All Books", JOptionPane.INFORMATION_MESSAGE);
            }

            private List<Book> getBooksFromLibrary() {
                // Implement the code to get books from the library
                List<Book> books = new ArrayList<>();
                // Add 10 books to the library
                books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
                books.add(new Book("To Kill a Mockingbird", "Harper Lee"));
                books.add(new Book("1984", "George Orwell"));
                books.add(new Book("Pride and Prejudice", "Jane Austen"));
                books.add(new Book("The Catcher in the Rye", "J.D. Salinger"));
                books.add(new Book("Moby-Dick", "Herman Melville"));
                books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien"));
                books.add(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling"));
                books.add(new Book("The Chronicles of Narnia", "C.S. Lewis"));
                books.add(new Book("Brave New World", "Aldous Huxley"));
                for (int i = 1; i <= 10; i++) {
                    Book book = new Book("Book " + i, "Author " + i);
                    books.add(book);
                }
                return books;
            }
        });
        adminFunctionsPanel.add(viewAllBooksButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAndShowGUI(); // Go back to the login screen
            }
        });
        adminFunctionsPanel.add(logoutButton);

        adminPanel.add(adminFunctionsPanel, BorderLayout.CENTER);

        frame.getContentPane().add(adminPanel);
        frame.pack();
        frame.setVisible(true);
    }
}