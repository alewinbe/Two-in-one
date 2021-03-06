package harjoitustyö;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
/**
 * Luokka sisältää High-Low arvauspelin peli arpoo arvon 1-100 väliltä, ja
 * pelaajan tulee arvata oikea numero. Peli kertoo jos pelaajaan arvaama numero
 * on liian alhainen/iso. kun pelaajaa arvaa oikein, peli kertoo kuinka monta
 * yritystä pelaaja käytti arvatakseen oikein. Pelaajalta kysytään jos hän
 * haluaa pelata uudelleen tai palata aloitusvalikkoon.
 * 
 * @author Alexander
 *
 */
public class Arvauspeli extends JFrame {
	private JTextField txtGuess;
	private JLabel lblOutput;
	private static int theNumber;
	private static JButton btnPlayAgain;
	private static JButton btnGuess;
	private static JButton btnStartMenu;
	private static JButton btnCorrectNumbers;
	private static JLabel lblCorrectNumbers;
	static int numberofTries = 0; // muuttuja sisältää yritysten määrän
	// taulokkolista pitää sisällään pelatut oikeat numerot
	private static ArrayList<String> correctNumbers = new ArrayList<String>();

	/**
	 * metodi tarkistaa pelaajan arvauksen
	 */
	public void checkGuess() {
		numberofTries++; // lisätään 1 yritys
		// tallennetaan käyttäjän antama arvaus muuttuujaan guesstText String tyyppinä
		String guessText = txtGuess.getText();
		String message = "";
		try {
			// muutetaan arvaus int tyyppiin
			int guess = Integer.parseInt(guessText);
			// ohjelmaa kertoo käyttäjälle jos hän arvasi oikein
			if (guess < theNumber)
				message = guess + " is too low. Try again.";
			else if (guess > theNumber)
				message = guess + " is too high. Try again.";
			// käyttäjä arvasi oikein, näytetään arvausmäärä ja Play again/Start menu
			// painikkeet.
			else {
				message = guess + " is correct! You won in" + " " + numberofTries + " " + "tries" + ". Play again!";
				btnPlayAgain.setVisible(true);
				btnStartMenu.setVisible(true);
				btnGuess.setVisible(false);
				btnCorrectNumbers.setVisible(true);
				lblCorrectNumbers.setVisible(true);
			}
		} catch (Exception e) { // näytetään error viesti jos käyttäjä syöttää väärän merkin
			message = "Enter a whole number between 1 and 100.";
		} finally {
			lblOutput.setText(message); /* näytetään viesti */
			txtGuess.requestFocus();
			txtGuess.selectAll();
		}
	}

	/**
	 * uusi peli metodi, määrittää muuttujat aloitusarvoihinsa
	 */
	public static void newGame() {
		theNumber = (int) (Math.random() * 100 + 1);
		correctNumbers.add(Integer.toString(theNumber));
		numberofTries = 0;
		btnGuess.setVisible(true);
		btnPlayAgain.setVisible(false);
		btnStartMenu.setVisible(false);
		btnCorrectNumbers.setVisible(false);
		lblCorrectNumbers.setVisible(false);
	}

	/**
	 * metodi sisältää graafisen käyttöliittymän muuttujia
	 */
	public Arvauspeli() {
		setForeground(Color.LIGHT_GRAY);
		// ikkunasta poistutaan jos se suljetaan
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ikkunan nimi
		setTitle("High-Low Guessing Game");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("High-Low Guessing Game"); /* otsikko */
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 21, 564, 40);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Guess a number between 1 and 100: ");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(178, 120, 227, 23);
		getContentPane().add(lblNewLabel_1);

		txtGuess = new JTextField();
		txtGuess.setHorizontalAlignment(SwingConstants.CENTER);
		txtGuess.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		// 'textInput' tekstikenttä johon käyttäjä syöttää tietoa. Tekstikenttä reagoi
		// jos käyttäjää painaa
		// 'Enter' painiketta, näppäimistöllään.
		txtGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkGuess();
			}
		});
		txtGuess.setBounds(241, 153, 100, 26);
		getContentPane().add(txtGuess);
		txtGuess.setColumns(10);

		btnGuess = new JButton("Guess!");
		btnGuess.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		// 'Guess' painike kutsuu checkGuess metodia
		btnGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkGuess();
			}
		});
		btnGuess.setVisible(true); /* painike näytetään oletuksena */
		btnGuess.setBounds(241, 213, 101, 38);
		getContentPane().add(btnGuess);

		// tulostusteksti, lblOutput muuttuuja määrittää tekstiä käyttäjälle näytetään
		lblOutput = new JLabel("Enter a number above and click Guess!");
		lblOutput.setFont(new Font("Yu Gothic", Font.PLAIN, 13));
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setBounds(80, 277, 414, 33);
		getContentPane().add(lblOutput);

		btnPlayAgain = new JButton("Play again?");
		btnPlayAgain.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		// 'Play Again' painike kutsuu newGame() muuttujaa
		btnPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Enter a number above and click Guess!");
				newGame();
			}
		});
		btnPlayAgain.setBounds(113, 213, 122, 56);
		getContentPane().add(btnPlayAgain);
		btnPlayAgain.setVisible(false); // painike on oletuksena piilossa

		btnStartMenu = new JButton("Start Menu");
		// 'Start Menu' painike kutsuu aloitusnäyttö objektia ja sulkee nykyisen ikkunan
		btnStartMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aloitusnäyttö.main(null);
				CloseJframe();
			}
		});
		btnStartMenu.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		btnStartMenu.setBounds(348, 213, 122, 56);
		getContentPane().add(btnStartMenu);

		btnCorrectNumbers = new JButton("Check correct numbers so far");
		// 'Correct Numbers' painike tulostaa näytölle kaikki tähän asti pelatut oikeat
		// numerot
		btnCorrectNumbers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numbers = "";
				for (String i : correctNumbers) { // correctNumbers taulukkolistan alkiot
					numbers += ", " + i; // käydään läpi ja lisätään String muuttujaan
				}
				numbers = numbers.substring(2); // poistetaan ", " merkkijonon alusta.
				lblCorrectNumbers.setText(numbers); // tulostetaan tähän asti olleet oikeat numerot näytölle
			}
		});
		btnCorrectNumbers.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		btnCorrectNumbers.setBounds(10, 321, 221, 29);
		getContentPane().add(btnCorrectNumbers);

		lblCorrectNumbers = new JLabel("");
		lblCorrectNumbers.setFont(new Font("Yu Gothic", Font.PLAIN, 13));
		lblCorrectNumbers.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorrectNumbers.setBounds(241, 321, 333, 29);
		getContentPane().add(lblCorrectNumbers);
	}

	/**
	 * metodilla suljetaan ikkuna
	 */
	public void CloseJframe() {
		super.dispose();
	}

	/**
	 * main metodi avaa uuden ikkunan ja määrittää sen mittasuhteet
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Arvauspeli theGame = new Arvauspeli();
		Arvauspeli.newGame(); // Aloitetaan uusi peli
		theGame.setSize(new Dimension(600, 400)); // annetaan ohjelman ikkunalle mittasuhteet
		theGame.setVisible(true); // varmistetaan että pelin ikkuna näkyy
	}
}
