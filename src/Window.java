import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * 
 * @author Nicholas McCarty
 * @version 1.0
 * date 2/11/2017
 *
 */

public class Window {
	
	private JFrame frame;
	private JButton button;
	private JLabel label;
	private ArrayList<String> doggos;
	private int tracker;
	
	/*
	 * Window()
	 * Default constructor that sets all the values to their base value.
	 */
	public Window() {
		JFrame frame = new JFrame();
		JButton button = new JButton();
		ArrayList<String> doggos = new ArrayList<String>();
		setDoggos(doggos);
		setFrame(frame);
		setButton(button);
		setTracker(0);
		getFrame().setVisible(true);
	}
	
	/*
	 * CreateWindow()
	 * Sizes and positions the window while also making it visible
	 */
	public void createWindow() {
		getFrame().setTitle("Rare Puppers");
		getFrame().getContentPane().setLayout(null);
		getFrame().pack();
		getFrame().setSize(1000, 1000);
		getFrame().setLocationRelativeTo(null);
		getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/*
	 * createLabel(JFrame frame, ArrayList<String> links)
	 * @parameter frame - is a JFrame being passed to add a label to
	 * @parameter links - is an ArrayList of Strings that make up the URLs for the imgur jpgs.
	 * This creates the label which holds the picture when you first start the program.
	 * The only purpose of this method is to have a default picture before pressing the button.
	 */
	public void createLabel(JFrame frame, ArrayList<String> links) {
		
		try {
			String imageLink = links.get(0);
			URL url = new URL(imageLink);
			BufferedImage image = ImageIO.read(url);
			JLabel label = new JLabel();
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(550, 550, Image.SCALE_SMOOTH));
			label.setIcon(imageIcon);
			setTracker(getTracker() + 1);
			label.setBounds(0, 0, 875, 700);
			label.setLocation(50, 30);
			label.setHorizontalAlignment(JLabel.CENTER);
			setLabel(label);
			frame.add(label);
			frame.repaint();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * createButton(JFrame frame, JButton button, Picture i)
	 * @parameter frame - used in order to add a button to it
	 * @parameter button - used for setting the button's position and text and also actions
	 * @parameter i - takes in a Picture object which is used for the next list of imgur urls once tracker reaches end of ArrayList of doggos
	 * This method holds the main logic for setting the image to the imgur urls and also going to the next page once there are no more pictures to be used
	 */
	public void createButton(JFrame frame, JButton button, Picture i) {
		button.setText("Get Rare Puppers!");
		button.setBounds(0, 0, 200, 50);
		button.setLocation(400, 800);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String image;			
				if (getTracker() < getDoggoSize()){
					image = getDoggos().get(getTracker());
					setImage(image, getFrame(), getLabel());
					setTracker(getTracker() + 1);
				}
				else {
					setTracker(0);
					i.createHtmlDoc(i.findNextPage(i.getHtml()));
					setDoggos(i.parseHtmlString(i.getHtml()));
					image = doggos.get(getTracker());
					setImage(image, getFrame(), getLabel());
					setTracker(getTracker() + 1);
				}
			}
		});
		
		frame.add(button);
	}
	
	/*
	 * setImage(String imageLink, JFrame frame, JLabel label)
	 * @parameter imageLink - the URL to be used for the jpg image
	 * @parameter frame - frame to be used for repainting when there is a new picture to update with.
	 * @parameter label - where the picture image is placed to see
	 * Changes the label to match the image from the imgur link provided in imageLink.
	 */
	public void setImage(String imageLink, JFrame frame, JLabel label) {
		try {
			URL url = new URL(imageLink);
			BufferedImage image = ImageIO.read(url);
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(550, 550, Image.SCALE_SMOOTH));
			label.setIcon(imageIcon);
			frame.repaint();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * getDoggoSize()
	 * @return the size of doggo ArrayList as an int.
	 */
	public int getDoggoSize() {
		return getDoggos().size();
	}
	
	/*
	 * getTracker()
	 * @return tracker for ArrayList doggo.
	 */
	public int getTracker() {
		return tracker;
	}
	
	/*
	 * setTracker(int tracker)
	 * @parameter tracker - used to keep tracker at the correct index in the ArrayList
	 */
	public void setTracker(int tracker) {
		this.tracker = tracker;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
	
	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	public ArrayList<String> getDoggos() {
		return doggos;
	}

	public void setDoggos(ArrayList<String> doggos) {
		this.doggos = doggos;
	}
	

}
