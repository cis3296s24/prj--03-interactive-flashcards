import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeckMenu2 extends JDialog {

    private JButton createNewDeckButton;
    private JPanel MainPanel;
    private JLabel IconImage;
    private JComboBox comboBox1;
    private ArrayList<Deck> decks;
    private Deck curr_deck;
    private DeckDatabase database = new DeckDatabase();

    public DeckMenu2(JFrame parent){

        super(parent);
        setTitle("Interactive Flashcards");
        setContentPane(MainPanel);
        setMinimumSize(new Dimension(800, 800));
        setModal(true);
        setLocationRelativeTo(parent);

        decks = database.read();
        addDecks();
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDeck = (String) comboBox1.getSelectedItem();
                if (selectedDeck.equals("+ Create New Deck")) {
                    createPopup();
                } else {
                    curr_deck = getDeck(selectedDeck);
                    // Perform actions based on the selected deck
                    // For example, displayCards(curr_deck);
                }
            }
        });
        setVisible(true);
    }

    // Method to populate JComboBox with deck names
    private void addDecks() {
        comboBox1.addItem("Select Deck...");
        for (Deck deck : decks) {
            comboBox1.addItem(deck.deck_name);
        }
        comboBox1.addItem("+ Create New Deck");
    }

    // Method to get Deck object given deck name
    private Deck getDeck(String name) {
        for (Deck deck : decks) {
            if (deck.deck_name.equals(name)) {
                return deck;
            }
        }
        return null;
    }

    // Method to create a new deck
    private void createPopup() {

        String inputText = JOptionPane.showInputDialog(this, "Enter the name of the new deck:");
        if (inputText != null && !inputText.isEmpty()) {
            if (getDeck(inputText) != null) {
                JOptionPane.showMessageDialog(this, "A deck with this name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Create a new deck
                decks.add(new Deck(inputText));
                // Update the JComboBox
                comboBox1.addItem(inputText);
                comboBox1.setSelectedItem(inputText);
                // Perform actions for the new deck
                curr_deck = getDeck(inputText);
                // For example, displayCards(curr_deck);
            }
        }

    }

    public static void main(String[] args){

        DeckMenu2 menu = new DeckMenu2(null);


    }

}
