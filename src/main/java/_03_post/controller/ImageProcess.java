package _03_post.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/*
 *  ImageProcessDemo.java 有兩個功能:
 *  
 *  (1)將大圖縮為小圖，並將小圖另存新檔
 *     processImage(int width, int height);// width, height為縮圖的寬與高
 *        
 *  (2)取大圖的一部分，取出之後另存新檔
 *     clipImage(int x, int y, int width, int height);  
 *     x,y     : 開始裁切的座標
 *     newWidth: 新圖的寬
 *	   newHeight: 新圖的高	
 * 
 */
public class ImageProcess {
    File file, outFile;
    String formatName, outFileName;
    BufferedImage buffered;
    InputStream is;
    InputStream old_is;
    long sizeInBytes;

    public ImageProcess(String inFile, String saveFolder) {
	// saveFolder: 儲存目的圖檔的資料夾
	File save = new File(saveFolder);
	if (!save.exists()) {
	    save.mkdirs();
	}
	// 建立來源圖檔的File物件
	file = new File(inFile);
	// 取出來源圖檔的副檔名，寫出圖檔時會用到圖檔的格式: png, jpeg, gif.....
	formatName = inFile.substring(inFile.lastIndexOf(".") + 1);
	// 產生目的圖檔的檔名，以利寫出圖檔
	outFileName = "out_" + inFile.substring(inFile.lastIndexOf("/") + 1);
	// 準備寫出的File物件
	outFile = new File(save, outFileName);
    }

    public ImageProcess(InputStream old_is) {
	super();
	this.old_is = old_is;
    }
    
    // 縮小圖片：
    // newWidth: 新圖的寬
    // newHeight: 新圖的高
    public void processImage(int newWidth, int newHeight) {
	try {
	    Image image = ImageIO.read(file);
	    BufferedImage buffered = (BufferedImage) image;
	    // 呼叫resize()進行縮圖
	    BufferedImage newImage = resize(buffered, newWidth, newHeight);
	    // 寫出縮小後的圖檔
	    ImageIO.write(newImage, formatName, outFile);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // 裁切圖片：
    // x,y : 開始裁切的座標
    // newWidth: 新圖的寬
    // newHeight: 新圖的高
    public void clipImage(int x, int y, int width, int height, int browserW, int browserH) {
	try {
	    Image image = ImageIO.read(file);
	    BufferedImage buffered = (BufferedImage) image;
	    double r = ((double) buffered.getHeight()) / ((double) browserH);
	    // int r = buffered.getWidth() / browserW;
	    x = (int) ((double) x * r);
	    y = (int) (y * r);
	    width = (int) (width * r);
	    height = (int) (height * r);
	    // 呼叫clip()進行裁切
	    BufferedImage newImage = clip(buffered, x, y, width, height);
	    // 寫出裁切後的圖檔
	    ImageIO.write(newImage, formatName, outFile);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void clipImageToInputStream(int x, int y, int width, int height, int browserW, int browserH) {
	try {
	    // Image image = ImageIO.read(file);
//	    BufferedImage buffered = (BufferedImage) image;
	    BufferedImage buffered = ImageIO.read(old_is);
	    double r = ((double) buffered.getHeight()) / ((double) browserH);
	    x = (int) ((double) x * r);
	    y = (int) (y * r);
	    width = (int) (width * r);
	    height = (int) (height * r);
	    BufferedImage newImage = clip(buffered, x, y, width, height);
	    ByteArrayOutputStream os = new ByteArrayOutputStream();

	    ImageIO.write(newImage, "jpg", os);
	    sizeInBytes = os.size();
	    System.out.println(sizeInBytes);
	    is = new ByteArrayInputStream(os.toByteArray());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public BufferedImage clip(BufferedImage original, int x, int y, int dstWidth, int dstHeight) {
	BufferedImage dstImage = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);

	Graphics2D g2d = (Graphics2D) original.getGraphics();

	g2d.clip(new Rectangle2D.Double(x, y, dstWidth, dstHeight));
	dstImage.getGraphics().drawImage(original, -x, -y, null);
	return dstImage;
    }

    public BufferedImage resize(BufferedImage original, int dstWidth, int dstHeight) {
	BufferedImage dstImage = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
	dstImage.getGraphics().drawImage(original, 0, 0, dstWidth, dstHeight, null);
	return dstImage;
    }

}
