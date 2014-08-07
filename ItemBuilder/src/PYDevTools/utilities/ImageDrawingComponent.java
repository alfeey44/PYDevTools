package PYDevTools.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class ImageDrawingComponent extends Component {

    private BufferedImage bi;
    private ArrayList<JLabel> labelList = new ArrayList<JLabel>();
    private int labelListSize = 0;
    private int w, h;
 
    public ImageDrawingComponent(URL imageSrc) {
        try {
            bi = ImageIO.read(imageSrc);
            w = bi.getWidth(null);
            h = bi.getHeight(null);
            if (bi.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi2 =
                    new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi2.getGraphics();
                big.drawImage(bi, 0, 0, null);
                bi = bi2;
            }
        } catch (IOException e) {
            System.out.println("Image could not be read");
        }
    }
    
    public static BufferedImage resizeBI(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
    
    public void resize(int width, int height) {
    	w = width;
    	h = height;
    	bi = resizeBI(bi, width, height);
    }
 
    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }
    
    public void paint(Graphics g) {
 
        Graphics2D g2 = (Graphics2D) g;
        
        if (labelList.size() == 0)
        	g2.drawImage(bi, 0, 0, null);
        else {
        	this.resize(bi.getWidth(), bi.getHeight() + (labelListSize*20));
        	g2.drawImage(bi, 0, 0, null);
        	g2.scale(1.5, 1.5);
        }
        
        for (JLabel l: labelList) {
        	g2.setColor(l.getForeground());
        	g2.drawChars(l.getText().toCharArray(), 0, l.getText().length(), l.getX(), l.getY());
        }
    }

	public void add(JLabel l) {
		labelList.add(l);
		labelListSize += 1;
	}
	
	public void setImage(String path) {
		try {
			bi = ImageIO.read((new File(path)).toURI().toURL());
			repaint();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}