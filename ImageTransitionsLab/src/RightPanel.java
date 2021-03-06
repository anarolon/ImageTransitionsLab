import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

public class RightPanel extends JPanel {
	private static final long serialVersionUID = -1073170544184446100L;
	private BufferedImage img;

	public RightPanel(BufferedImage img){
		this.img = img;
	}

	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(this.img, 0, 0 , null);		
	}

	public void clearImage(Graphics g) {
		int width = img.getWidth();
		int height = img.getHeight();
		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++){
				img.setRGB(i,j,0xffffffff);
			}
		}
	}
	
	//
	// Transition by columns top to bottom left to right
	public void transitionTBLR(Graphics g, BufferedImage leftImage) {
		// Lab Q1: Your Code Here
		int width = leftImage.getWidth();
		int height = leftImage.getHeight();
		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++){
				int pixelColor= leftImage.getRGB(i,j);
				img.setRGB(i, j, pixelColor);
			}
			repaint();
			try { Thread.sleep(10); } catch (InterruptedException e) { };
		}
	}

	//
	// Transition by rows left to right top to bottom
	public void transitionLRTB(Graphics g, BufferedImage leftImage) {
		int width = leftImage.getWidth();
		int height = leftImage.getHeight();
		for (int j=0; j<height; j++){
			for (int i=0; i<width; i++){
				int pixelColor= leftImage.getRGB(i,j);
				img.setRGB(i, j, pixelColor);
			}
			repaint();
			try { Thread.sleep(10); } catch (InterruptedException e) { };
		}
	}
	
	// Displays the picture on the frame using randomly generated pixel coords
	public void transitionRandom(Graphics g, BufferedImage leftImage) {
		int width = leftImage.getWidth();
		int height = leftImage.getHeight();
		
		for(int i = 0; i < height; i++) {
			Random randY = new Random();
			int randomY = randY.nextInt(height);
			for(int j = 0; j < width; j++) {
				Random randX = new Random();
				int randomX = randX.nextInt(width);
				int pixelColor = leftImage.getRGB(randomX, randomY); // Random pixel
				int pixelColor2 = leftImage.getRGB(j, i);			// Fixed pixel
				img.setRGB(randomX, randomY, pixelColor);
				img.setRGB(j, i, pixelColor2);
			}
			repaint();
			try { Thread.sleep(10); } catch (InterruptedException e) {};
		}
		
	}
	
	//
	// Transition by diagonals bottom to top left to right
	public void transitionDiagonal45LR(Graphics g, BufferedImage leftImage) {
		int width = leftImage.getWidth();
		int height = leftImage.getHeight();

		if (width > height) {
			// Image with larger width than height		
			for (int row=0; row<height; row++){
				int diagonalRow = row;
				for (int col=0; col<=row; col++) {
					int pixelColor= leftImage.getRGB(col,diagonalRow);
					img.setRGB(col, diagonalRow, pixelColor);
					diagonalRow--;
				}
				repaint();
				try { Thread.sleep(10); } catch (InterruptedException e) { };
			}
			for (int i=0; i<(width-height); i++) {
				int row = height-1;
				int col = i+1;
				for (int j=0; j<height; j++) {
					int pixelColor= leftImage.getRGB(col,row);
					img.setRGB(col, row, pixelColor);
					col++; row--;
				}
				repaint();
				try { Thread.sleep(10); } catch (InterruptedException e) { };
			}
			for (int i=0; i<=(width); i++) {
				int diagonalRow = height-1;
				for (int col=(width-height+i); col<width; col++) {
					int pixelColor= leftImage.getRGB(col,diagonalRow);
					img.setRGB(col, diagonalRow, pixelColor);
					diagonalRow--;
				}
				repaint();
				try { Thread.sleep(10); } catch (InterruptedException e) { };
			}
		}
		else {
			// Lab Q3: Add code to consider image with larger height than width
				// Image with larger width than height		
				for (int col=0; col<width; col++){
					int diagonalRow = col;
					for (int row=0; row<=col; row++) {
						int pixelColor= leftImage.getRGB(row, diagonalRow);
						img.setRGB(row, diagonalRow, pixelColor);
						diagonalRow--;
					}
					repaint();
					try { Thread.sleep(10); } catch (InterruptedException e) { };
				}
				for (int i=0; i<(height - width); i++) {
					int row = width-1;
					int col = i+1;
					for (int j=0; j<width; j++) {
						int pixelColor= leftImage.getRGB(row, col);
						img.setRGB(row, col, pixelColor);
						col++; row--;
					}
					repaint();
					try { Thread.sleep(10); } catch (InterruptedException e) { };
				}
				for (int i=0; i<=(height); i++) {
					int diagonalRow = width-1;
					for (int row=(height - width+i);row<height; row++) {
						int pixelColor= leftImage.getRGB(diagonalRow, row);
						img.setRGB(diagonalRow, row, pixelColor);
						diagonalRow--;
					}
					repaint();
					try { Thread.sleep(10); } catch (InterruptedException e) { };
				}
			
		}
	}	
}
