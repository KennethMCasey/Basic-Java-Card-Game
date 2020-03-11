package daProject;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameJPannel extends JPanel implements ActionListener {
	
int numAttempts;
Card[][] cards;
private static int numOfMatches=0;
private static int numOfAttempts=0;

JButton continueJB;
JLabel attempts;
JLabel matches;
JLabel status;
JButton quitGame;

private Card lastCardFlipped;
private Card wrongCard;
private boolean hasWrongCard = false;


private String imageOneFileLovation = "./src/daProject/imagesForProject/imageOne.jpeg";
private String imageTwoFileLovation =  "./src/daProject/imagesForProject/imageTwo.jpeg";
private String imageThreeFileLovation =  "./src/daProject/imagesForProject/imageThree.jpeg";
private String imageFourFileLovation =  "./src/daProject/imagesForProject/imageFour.jpeg";
private String imageFiveFileLovation =  "./src/daProject/imagesForProject/imageFive.jpeg";
private String imageSixFileLovation =  "./src/daProject/imagesForProject/imageSix.jpeg";
	
JPanel quitJP;
JFrame quitJF;
JButton yesQuit;
JButton noQuit;
JLabel displayQuit;


public GameJPannel() {
	lastCardFlipped = null;
		
		//this is the main jPanel
	JPanel mainJP = new JPanel();
	mainJP.setLayout(new BorderLayout());
	
	
	
	
	//this is the cards JPannel
	JPanel cardsJP = new JPanel();
	cardsJP.setLayout(new GridLayout(3, 4));
	
	
	//create and assign card objects
	cards = new Card[3][4];
	for (int i = 0;  i < 3; i++) {
		String one = (i == 0) ? imageOneFileLovation : (i==1) ? imageTwoFileLovation : imageThreeFileLovation;
		String two = (i == 0) ? imageFourFileLovation : (i==1) ? imageFiveFileLovation : imageSixFileLovation;
		for (int q = 0; q < 4; q++) {
			cards[i][q] = new Card(((q%2 == 0) ? one : two));
		} }
	
	
	//sets to back of card
	for (Card[] x : cards) {
		for (Card q : x) {q.setSize(200, 200);   q.setIcon(q.hideCardInital());}
	}
	
	
	
	//add action listeners to card objects
	for (Card[] x : cards) {
		for (Card q : x) {q.addActionListener(this);}
	}
	
	//add cards to cardsJP
	for (Card[] x : cards) {
		for (Card q : x) {cardsJP.add(q);}	
	}
	
	
	//add to main JP
	mainJP.add(cardsJP, BorderLayout.CENTER);
	
	
	
	
	//functionalityJP
	JPanel functionalityJP = new JPanel();
	
	//create all buttons and labels
	 continueJB = new JButton("Continue");
	 attempts = new JLabel("Number of attemps: " + numOfAttempts);
	 matches = new JLabel("Number of Matches: " + numOfMatches);
	 status = new JLabel("Welcome. Begin.");
	quitGame = new JButton("Quit???");
	
	continueJB.addActionListener(this);
	quitGame.addActionListener(this);
	
	
	
	//add all buttons and labels
	functionalityJP.add(continueJB);
	functionalityJP.add(attempts);
	functionalityJP.add(matches);
	functionalityJP.add(status);
	functionalityJP.add(quitGame);
	
	
	//add functionalityJP to the main JP
	mainJP.add(functionalityJP, BorderLayout.NORTH
			);
	
	
	
	//main JP add
	add(mainJP);
	}
	
	
	
	@Override
		public void actionPerformed(ActionEvent e) {
			
		if (e.getSource() instanceof Card && hasWrongCard == false) {
			Card temp = ((Card) e.getSource());
			
	if (temp.isHidden == true) {
		
		temp.setIcon(temp.showCard());
		
		if ((Card.getNumCardsFaceUp() % 2 != 0) || (lastCardFlipped == null)) {lastCardFlipped = temp; status.setText("select second card");}
		
		else if (Card.getNumCardsFaceUp() % 2 == 0) {
			numOfAttempts++;
			attempts.setText("Number of attemps: " + numOfAttempts);
			
			if (temp.imagePath.equals(lastCardFlipped.imagePath)) {
				status.setText("yay a match!");
				if (Card.getNumCardsFaceUp() == 12) status.setText("Press Continue to play again.");
			numOfMatches++;  matches.setText("Number of Matches: " + numOfMatches);} 
			
			
			else {
				status.setText("Press continue.");
		hasWrongCard = true;
		wrongCard = temp;
				
				
		
			}
		} }
		
		
	}
		
		
		
		if(e.getSource().equals(yesQuit)) {
			numOfAttempts = numOfAttempts == 0 ? 1 : numOfAttempts;
			
			String text = ((numOfMatches/numOfAttempts) * 100 >= 97) ? "AMAZING" : ((numOfMatches/numOfAttempts) * 100 >= 80) ? "Exclente" : ((numOfMatches/numOfAttempts) * 100 >= 60) ? "OK": ((numOfMatches/numOfAttempts) * 100 >= 30) ? "Lacking" : ((numOfMatches/numOfAttempts) * 100 >= 10) ? "Suffering" :"mind has escped" ;
			
			displayQuit.setText("Score: " + text);
			
			
		}
		
		if (e.getSource().equals(noQuit)) {
			GameJPannel.this.setVisible(true);
			quitJF.dispose();
			quitJF = null;
		}
		
		if (e.getSource().equals(quitGame)) {
			if (quitJF == null) {
			
			quitJF = new JFrame();
			quitJP = new JPanel();
			yesQuit = new JButton("yes");
			noQuit = new JButton("no");
			
			yesQuit.addActionListener(this);
			noQuit.addActionListener(this);
		
			displayQuit = new JLabel("Really quit?");
			quitJP.add(yesQuit);
			quitJP.add(noQuit);
			quitJP.add(displayQuit);
			quitJF.add(quitJP);
			quitJF.setSize(new Dimension(300, 600));
			quitJF.setVisible(true);
			GameJPannel.this.setVisible(false);
			}
		}
		
		
		if (e.getSource().equals(continueJB)) {
			if (hasWrongCard) {	
				wrongCard.setIcon(wrongCard.hideCard());
				lastCardFlipped.setIcon(lastCardFlipped.hideCard());
				lastCardFlipped = null;
				hasWrongCard = false;
			}
			
			
			if (Card.getNumCardsFaceUp() == 12) {
				for (Card[] x : cards) {
					for (Card q : x) {q.setIcon(q.hideCard()); }	
				}
				numOfAttempts = 0; numOfMatches = 0; 
				matches.setText("Number of Matches: " + numOfMatches);
				attempts.setText("Number of attemps: " + numOfAttempts);
				status.setText("Welcome.");
				
			}
			
		}
			
		}
	
	
}
