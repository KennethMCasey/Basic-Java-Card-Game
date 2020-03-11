package daProject;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Card extends JButton {
ImageIcon imageIcon;
ImageIcon backOfCard;
String imagePath;
boolean isHidden;
private static int numCardsFaceUp = 0;


private Card() {
	this.backOfCard = new ImageIcon("./src/daProject/imagesForProject/back.jpg");
	this.backOfCard =  new ImageIcon (((Image) (backOfCard.getImage())).getScaledInstance(70, 70, Image.SCALE_FAST));
	
}

public Card(String imagePath) {
this();
this.imagePath = imagePath;
this.imageIcon = new ImageIcon(imagePath);
this.imageIcon = new ImageIcon (((Image) (imageIcon.getImage())).getScaledInstance(70, 70, Image.SCALE_FAST));
}
	
public ImageIcon hideCard() {
	isHidden = true;
	numCardsFaceUp--;
	return backOfCard;
}


public ImageIcon hideCardInital() {
	isHidden = true;
	return backOfCard;
}

public ImageIcon showCard() {
	numCardsFaceUp++;
	isHidden = false;
	return imageIcon;
}
 
public static int getNumCardsFaceUp() {return numCardsFaceUp;} 


public boolean equals(Object o) {
	return ((!(o instanceof Card)) ? false :  (this.imageIcon.equals(((Card)o).imageIcon)) ? true : false) ;}
}
