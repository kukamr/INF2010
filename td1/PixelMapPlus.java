import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		for (int i = 0; i < this.height; i++){
			for (int j = 0; j <this.width; j++){
				this.imageData[i][j]= this.imageData[i][j].Negative();
			}
		}
		
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		for (int i = 0; i < this.height; i++){
			for (int j= 0; j < this.width; j++){
				this.imageData[i][j]= this.imageData[i][j].toBWPixel();
			}
		}


	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		for (int i =0; i< this.height; i++){
			for (int j = 0; j < this.width; j++){
				this.imageData[i][j]= this.imageData[i][j].toGrayPixel();
		}
	}


	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		for (int i = 0; i < this.height; i++){
			for (int j = 0; j < this.width; j++){
				this.imageData[i][j]= this.imageData[i][j].toColorPixel();
		}
	}


	}
	
	public void convertToTransparentImage()
	{
		for (int i = 0; i < this.height; i++){
			for (int j = 0; j <this.width; j++){
			this.imageData[i][j]= this.imageData[i][j].toTransparentPixel();
		}
	}


	}
	
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();

		double ratioH = this.height/h;
		double ratioW = this.width/w;

		AbstractPixel[][] newImage = new AbstractPixel[h][w];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				newImage[i][j] = this.imageData[(int)(i*ratioH)][(int)(j*ratioW)];
			}
		}

		this.height = h;
		this.width = w;
		this.imageData = newImage;

	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void insert(PixelMap pm, int row0, int col0)
	{
		for(int i =0; i < pm.height; i++){
			for(int j = 0; j < pm.width; j++) {
				this.imageData[row0 + i][col0 + j] = pm.imageData[i][j];
			}

		}
		
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		AbstractPixel[][] newImageData = new AbstractPixel[h][w];

		for(int i = 0; i < h; i++) {
			for(int j =0; j < w; j++){
				if(i < this.height && j < this.width){
					newImageData[i][j] = this.imageData[i][j];
				}
				else {
					newImageData[i][j] = new ColorPixel();
				}
			}
		}

		this.imageData = newImageData;
		this.width = w;
		this.height = h;
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{

		for(int i = this.height - 1; i != -1; i--){
			for(int j = this.width - 1; j != -1; j--){
				if((i - rowOffset >= 0) && (j - colOffset >= 0 )) {
					this.imageData[i][j] = this.imageData[i-rowOffset][j- colOffset];
				}
				else {
					this.imageData[i][j] = new ColorPixel();
				}
			}
		}

		
	}
	
}
