package function;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageScale {
    //
	public ImageIcon getScaledImage(int width, int height, String path) {
		ImageIcon myImage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(path)));
		Image img1 = myImage.getImage();
		
		Image img2 = img1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon i = new ImageIcon(img2);
		return i;
	}
        
        // can chinh image
public Icon load(String linkImage, int k, int m) {/*linkImage là tên icon, k kích thước chiều rộng mình muốn,m chiều dài và hàm này trả về giá trị là 1 icon.*/
    try {
        BufferedImage image = ImageIO.read(new File(linkImage));//đọc ảnh dùng BufferedImage
 
        int x = k;
        int y = m;
        int ix = image.getWidth();
        int iy = image.getHeight();
        int dx = 0, dy = 0;
 
        if (x / y > ix / iy) {
            dy = y;
            dx = dy * ix / iy;
        } else {
            dx = x;
            dy = dx * iy / ix;
        }
 
        return new ImageIcon(image.getScaledInstance(dx, dy,
                image.SCALE_SMOOTH));
 
    } catch (IOException e) {
 
        e.printStackTrace();
    }
 
    return null;
}
}
