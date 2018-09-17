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
		for (int i =0; i< this.height; i++){
			for (int j=0; i<this.width; j++){
				this.imageData[i][j]= this.imageData[i][j].Negative();
			}
		}
		
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		for (int i =0; i< this.height; i++){
			for (int j=0; i<this.width; j++){
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
		for (int j=0; i<this.width; j++){
			this.imageData[i][j]= this.imageData[i][j].toGrayPixel();
		}
	}


	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		for (int i =0; i< this.height; i++){
			for (int j=0; i<this.width; j++){
				this.imageData[i][j]= this.imageData[i][j].toColorPixel();
		}
	}


	}
	
	public void convertToTransparentImage()
	{
		for (int i =0; i< this.height; i++){
		for (int j=0; i<this.width; j++){
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

		AbstractPixel[][] newImage = new AbstractPixel[h][w];

		int indexW = 0;
		int indexH = 0;

		if (w<width){
			double ratioW = width/w;
			double ratioH = height/h;
			for (int i = 0; i<this.height; i++){
				if(i%ratioH == 0) {
					for (int j = 0; j<this.width; j++) {
						if(j%ratioW == 0) {
							newImage[indexH][indexW++] = this.imageData[i][j];
						}
					}
					indexW = 0;
					indexH++;
				}
			}
		}

		this.imageData = newImage;
		this.width = w;
		this.height = h;
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void insert(PixelMap pm, int row0, int col0)
	{
		// compl�ter
		
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		// compl�ter		
		
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// compl�ter		
		
	}
	
}
