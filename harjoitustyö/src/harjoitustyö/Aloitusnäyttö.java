/* Tämä on pieni ohjelma jossa pelaaja saa valita 
 * kahden eri pelin välillä.
 * Yksi peleistä on simppeli numeronarvauspeli jossa pelaaja arvaa 
 * jos numero on pienempi vai suurempi.
 * Toinen peleistä on salakirjoituspeli jossa pelaaja saa 
 * salakirjoittaa haluamansa lauseen, tai ratkaista valmiiksi 
 * salakirjoitetun lauseen.
 * Pelin graafinenkäyttöliittymä on tehty JFrame luokkaa käyttäen 
 * ja eclipsen windowbuilderiä hyödyntäen.
 * Peli on englanniksi.
 * 
 *  @author Alexander
 */

package harjoitustyö;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Aloitusnäyttö extends JFrame {
	/**
	 * metodi määrittää miten graafinen käyttöliittymä käyttäytyy
	 */
	public Aloitusnäyttö() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ikkunasta poistutaan jos se suljetaan
		setTitle("Two for one"); // ikkunan nimi
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Two for one"); // otsikko
		lblNewLabel.setBounds(0, 11, 584, 29);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 20));
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Which game would you like to play?");
		lblNewLabel_1.setBounds(0, 64, 584, 28);
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.PLAIN, 13));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_1);

		JButton btnHighLow = new JButton("High-Low game");
		// 'high-low' painike avaa arvauspelin ja sulkee nykyisen ikkunan
		btnHighLow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arvauspeli.main(null);
				CloseJframe();
			}
		});
		btnHighLow.setBounds(341, 124, 144, 106);
		btnHighLow.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		getContentPane().add(btnHighLow);

		JButton btnCaesarsCypher = new JButton("Caesars Cipher");
		// 'Caesars Cipher' painike valitsee salakirjoituspelin ja sulkee nykyisen
		// ikkunan
		btnCaesarsCypher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Salakirjoitus.main(null);
				CloseJframe();
			}
		});
		btnCaesarsCypher.setBounds(99, 124, 144, 106);
		btnCaesarsCypher.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		getContentPane().add(btnCaesarsCypher);

		JButton btnExit = new JButton("Exit");
		// 'Exit' painike sulkee ohjelman
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseJframe();
			}
		});
		btnExit.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		btnExit.setBounds(251, 299, 89, 29);
		getContentPane().add(btnExit);
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
		Aloitusnäyttö startScreen = new Aloitusnäyttö();
		startScreen.setSize(new Dimension(600, 400)); // annetaan ohjelman ikkunalle mittasuhteet
		startScreen.setVisible(true); // varmistetaan että pelin ikkuna näkyy
	}

}
