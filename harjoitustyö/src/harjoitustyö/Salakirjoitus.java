package harjoitustyö;

import javax.swing.JFrame;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
/**
 * Tämä luokka sisältää salakirjoitusohjelman lähdekoodin, ohjelma kysyy
 * käyttäjältä salakirjoitusavaimen/muutosarvon, joka päättää kuinka monella
 * asteella käyttäjän antama merkkijono salakirjoitetaan. Sen jälkeen käyttäjä
 * syöttää haluamansa merkkijonon ja ohjelma salakirjoittaa sen. Lopuksi
 * kysytään jos pelaaja haluaa pelata uudestaan tai palata aloitusvalikkoon.
 * Pelatut merkkijonot ja niiden salakirjoitetut versiot tallennetaan
 * tekstitiedostoon "annetutMerkkijonot.txt"
 * 
 * @author Alexander
 *
 */
public class Salakirjoitus extends JFrame {
	public static Scanner scan = new Scanner(System.in);
	private static JTextField textInput;
	private static JLabel lblCipherOutput;
	private static JButton btnCPlayAgain;
	private static JButton btnKey;
	private static JButton btnCipher;
	private static JButton btnReturnToStart;
	private static int key = 0; // avaimen muuttuja
	private static int enterCheckValue = 0;
	private static ArrayList<String> annetutMerkkijonot = new ArrayList<String>();

	/**
	 * metodi palauttaa käyttäjän antaman 'avaimen'
	 * 
	 * @return avaimen arvon
	 */
	public static int keyValue() {
		// tallennetaan käyttäjän antama teksti muuttujaan
		String getKey = textInput.getText();
		int keyVal = 0;

		try {
			keyVal = Integer.parseInt(getKey);
			textInput.setText("");
		} catch (Exception e) { // jos käyttäjää antaa vääränlaisen merkin, aloitetaan alusta
			lblCipherOutput.setText("Invalid input, try again.");
			textInput.setText("");
			keyValue();
		}
		if (keyVal < -25 || keyVal > 25) {
			lblCipherOutput.setText("Invalid input, try again.");
			keyValue();
		}
		btnKey.setVisible(false);
		btnCipher.setVisible(true);
		// muutetaan ohjelman näyttämää tekstiä, pyydetään salattavaa merkkijonoa
		lblCipherOutput.setText("Enter the message you would like to cipher:");
		enterCheckValue = 1;
		// palautetaan avaimen arvo
		return keyVal;
	}

	/**
	 * metodi salakirjoittaa käyttäjän antaman merkkijonon, avaimen perusteella.
	 * 
	 * @param keyVal avaimen arvo parametrinä
	 */
	public static void cipher(int keyVal) {

		// Käyttäjän syöttämä merkkijono tallennetaan muuttujaan cipherMessage
		String cipherMessage = textInput.getText();
		String output = "";
		annetutMerkkijonot.add(cipherMessage);

		try {
			char key = (char) keyVal;
			// silmukka käy läpi merkkijonon jokaisen kirjaimen ja salakirjoittaa ne.
			for (int i = 0; i < cipherMessage.length(); i++) {
				char input = cipherMessage.charAt(i);

				if (input >= 'A' && input <= 'Z') {
					input += key;
					if (input > 'Z')
						input -= 26;
					if (input < 'A')
						input += 26;
				} else if (input >= 'a' && input <= 'z') {
					input += key;
					if (input > 'z')
						input -= 26;
					if (input < 'a')
						input += 26;
				} else if (input >= '0' && input <= '9') {
					input += (keyVal % 10);
					if (input > '9')
						input -= 10;
					if (input < '0')
						input += 10;
				}
				output += input;
			}
		} catch (Exception y) {
			// näytetään error viesti jos käyttäjä antaa viallisen arvon
			lblCipherOutput.setText("Error in input, try again.");
		} finally {
			lblCipherOutput.setText("Your encoded message is:");
			textInput.setText(output); /* näytetään salakirjoitettu merkkijono tekstikentässä */
			// salakirjoitetut merkkijonot lisätään taulukkoon
			annetutMerkkijonot.add(output);
			try {
				tiedostoonKirjoitus(); // kutsutaan tiedostoonKirjoitus metodia
			} catch (IOException e) {
				// e.printStackTrace();
			}
			annetutMerkkijonot.remove(0); // poistetaan vanhat merkkijonot taulukosta, jotta ei saada kopioita
			annetutMerkkijonot.remove(0);
			// näytetään play again ja start menu painikkeet.
			btnCPlayAgain.setVisible(true);
			btnReturnToStart.setVisible(true);
		}
	}

	/**
	 * metodi lisää annetutMerkkijonot taulukon sisällön tekstitiedostoon
	 * "annetutMerkkijonot.txt"
	 * 
	 * @throws IOException
	 */
	public static void tiedostoonKirjoitus() throws IOException {
		String textToAppend = "";
		for (String i : annetutMerkkijonot) { // annetutMerkkijonot taulukkolistan alkiot
			textToAppend += i + " "; // käydään läpi ja lisätään String muuttujaan
		}
		FileWriter fileWriter = new FileWriter("annetutMerkkijonot.txt", true); // Set true for append mode
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println(textToAppend); // New line
		printWriter.close();
	}

	/**
	 * uusi peli metodi, määrittää muuttujat aloitusarvoihinsa
	 */
	public static void newCGame() {
		textInput.setText("");
		lblCipherOutput.setText("Enter your cipher key, a number from 25 to -25:");
		btnReturnToStart.setVisible(false);
		btnKey.setVisible(true);
		btnCipher.setVisible(false);
		btnCPlayAgain.setVisible(false);
		textInput.setVisible(true);
		enterCheckValue = 0;
	}

	/**
	 * metodi sisältää graafisen käyttöliittymän muuttujia
	 */
	public Salakirjoitus() {
		// ikkunan nimi
		setTitle("Caesars Cipher");
		// ikkunasta poistutaan jos se suljetaan
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Otsikkoteksti
		JLabel lblNewLabel = new JLabel("Caesars cipher");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 31, 584, 29);
		getContentPane().add(lblNewLabel);

		// lblCipherOutput muuttujalla määräätään mitä tekstiä ohjelma näyttää.
		lblCipherOutput = new JLabel("Enter your cipher key, a number from 25 to -25:");
		lblCipherOutput.setFont(new Font("Yu Gothic", Font.PLAIN, 13));
		lblCipherOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblCipherOutput.setBounds(0, 110, 584, 22);
		getContentPane().add(lblCipherOutput);

		textInput = new JTextField();
		// 'textInput' tekstikenttä johon käyttäjä syöttää tietoa. Tekstikenttä reagoi
		// jos käyttäjää painaa
		// 'Enter' painiketta, näppäimistöllään.
		textInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (enterCheckValue == 0) { // Jos kysytään avainta, painamalla enteriä kutsutaan keyValue metodia
					key = keyValue();
					btnKey.setVisible(false);
					btnCipher.setVisible(true);
					lblCipherOutput.setText("Enter the message you would like to cipher:");
					enterCheckValue = 1;
				} else if (enterCheckValue == 1) { // kutsutaan cipher metodia jos kysytään salattavaa merkkijonoa,
													// painamalla enteriä.
					cipher(key);
					btnCipher.setVisible(false);
				}
			}
		});
		textInput.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		textInput.setHorizontalAlignment(SwingConstants.CENTER);
		textInput.setBounds(96, 153, 403, 28);
		getContentPane().add(textInput);
		textInput.setColumns(10);

		btnKey = new JButton("Give key");
		// 'Give key' painike tallentaa key muuttujaan avaimen arvo, kutsumalla
		// keyValue() metodia
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key = keyValue();
			}
		});
		btnKey.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		btnKey.setBounds(240, 213, 101, 38);
		getContentPane().add(btnKey);

		btnCipher = new JButton("Cipher!");
		// 'Cipher!' painike kutsuu cipher metodia, 'key' eli avaimen arvo toimii
		// parametrinä
		// painike piilottaa itsensä kun sitä painetaan tai painetaan enter.
		btnCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cipher(key);
				btnCipher.setVisible(false);
			}
		});
		btnCipher.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		btnCipher.setBounds(240, 213, 101, 38);
		getContentPane().add(btnCipher);

		btnCPlayAgain = new JButton("Play again?");
		// 'Play again?' painike kutsee newCGame metodia, joka aloittaa pelin alusta.
		btnCPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newCGame();
			}
		});
		btnCPlayAgain.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		btnCPlayAgain.setBounds(113, 213, 122, 56);
		getContentPane().add(btnCPlayAgain);
		btnCPlayAgain.setVisible(false);

		btnReturnToStart = new JButton("Start menu");
		// 'Start Menu' painike kutsuu aloitusnäyttö objektia ja sulkee
		// salakirjoituspelin
		btnReturnToStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aloitusnäyttö.main(null);
				CloseJframe();
			}
		});
		btnReturnToStart.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		btnReturnToStart.setBounds(348, 213, 122, 56);
		getContentPane().add(btnReturnToStart);
		btnReturnToStart.setVisible(false);

	}

	/**
	 * metodilla suljetaan ikkuna
	 */
	public void CloseJframe() {
		super.dispose();
	}

	/**
	 * Main metodi avaa ikkunan ja antaa sille mittasuhteet
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Salakirjoitus cipherGame = new Salakirjoitus();
		Salakirjoitus.newCGame(); // Aloitetaan uusi peli
		cipherGame.setSize(new Dimension(600, 400)); // annetaan ohjelman ikkunalle mittasuhteet
		cipherGame.setVisible(true); // varmistetaan että pelin ikkuna näkyy
	}
}