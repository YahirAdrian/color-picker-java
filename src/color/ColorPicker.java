package color;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorPicker {

	static final int WIDTH = 500, HEIGHT = 300;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame mimarco = new JFrame("Color picker en java uwu");
		Lamina milamina = new Lamina();
		mimarco.add(milamina);
		mimarco.setBounds(200, 200, WIDTH, HEIGHT);
		mimarco.setVisible(true);
		mimarco.setResizable(false);
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class Lamina extends JPanel{
	JSlider redSlider, greenSlider, blueSlider;
	JTextField campo_rgb, campo_hex, campo_cmyk;
	
	public Lamina() {
		//	CREACION DE LOS SLIDERS
		redSlider = new JSlider(1,0, 255, 12);
		greenSlider = new JSlider(1,0, 255, 97);
		blueSlider = new JSlider(1,0, 255, 83);
		//CREACION DE LOS LABELS SEGUIDO DE SU TEXTFIELD
		campo_rgb = new JTextField(10);
		campo_rgb.setText("rgb(12, 97, 83)");
		campo_hex = new JTextField(10);
		campo_cmyk = new JTextField(20);
		campo_cmyk.setText("cmyk(88,0,14,62)");
		campo_hex.setText("Hex: #0c5153");
		//HACER QUE NO SE MUEVAN SI NO SE HA PULSADO EN EL CENTRO
		redSlider.setSnapToTicks(true);
		greenSlider.setSnapToTicks(true);
		blueSlider.setSnapToTicks(true);
		//AGREGARLES UN EVENT LISTENER
		redSlider.addChangeListener(new Cambio());
		greenSlider.addChangeListener(new Cambio());
		blueSlider.addChangeListener(new Cambio());
		//CAMBIAR EL COLOR DE FONDO AL INICIO
		cambiarFondo(12, 97, 83);
		redSlider.setBackground(new Color(12, 97, 83));
		greenSlider.setBackground(new Color(12, 97, 83));
		blueSlider.setBackground(new Color(12, 97, 83));
		//AGREGARLOS A LA LAMINA
		add(redSlider); add(greenSlider); add(blueSlider); 
		//add(etiquetaFuncion);
		add(campo_rgb);
		add(campo_hex);
		add(campo_cmyk);
		//PONER ESTILOS A LOS COMPONENTES
		campo_rgb.setEditable(false);
		campo_hex.setEditable(false);
		campo_cmyk.setEditable(false);
		campo_rgb.setBorder(null);
		campo_cmyk.setBorder(null);
		campo_rgb.setFont(new Font("sans-serif", Font.BOLD, 16));
		campo_cmyk.setFont(new Font("sans-serif", Font.BOLD, 16));
		campo_hex.setBorder(null);
		campo_hex.setFont(new Font("sans-serif", Font.BOLD, 16));
		
	}
	
	public void cambiarFondo(int red, int green, int blue) {
		cambiarCampo(red, green , blue);
		int gradient = 5;
		this.setBackground(new Color(red, green, blue));
		
		if(red <=5 || green <=5 || blue <=5) {
			 gradient = 0;
		}
		//CREANDO UN DEGRADADO PARA LOS COMPONEMNTES
		redSlider.setBackground(new Color(red -gradient, green -gradient, blue -gradient));
		greenSlider.setBackground(new Color(red -gradient, green -gradient, blue -gradient));
		blueSlider.setBackground(new Color(red -gradient, green -gradient, blue -gradient));
		campo_rgb.setBackground(new Color(red -gradient, green -gradient, blue -gradient));
		campo_hex.setBackground(new Color(red -gradient, green -gradient, blue -gradient));
		campo_cmyk.setBackground(new Color(red -gradient, green -gradient, blue -gradient));
	}
	
	public void cambiarCampo(int red, int green, int blue) {
		String color = "rgb(" + red + ","+ green + "," + blue + ")";
		int sumatoria_color = red + green + blue;
		campo_rgb.setText(color);
		campo_hex.setText("Hex: #" + rgb_to_hex(red) + rgb_to_hex(green) + rgb_to_hex(blue));
		campo_cmyk.setText(rgb_to_cmyk(red, green, blue));
		
		if(sumatoria_color <= 300) {
			campo_rgb.setForeground(Color.WHITE);
			campo_hex.setForeground(Color.WHITE);
			campo_cmyk.setForeground(Color.WHITE);
		}else {
			campo_rgb.setForeground(Color.BLACK);
			campo_hex.setForeground(Color.BLACK);
			campo_cmyk.setForeground(Color.BLACK);
		}
	}
	
	public String rgb_to_hex(int valor) {
		String [] valores = {
				"0","1", "2", "3", "4", "5", "6", "7","8",
				"9", "a", "b", "c", "d", "e", "f"};
		 
		int primer_valor = (int) Math.floor(valor/16);
		int segundo_valor = valor%16;
		return valores[primer_valor]  + valores[segundo_valor];
	}
	
	public String rgb_to_cmyk(double red, double green, double blue)
	{
		red/=255; green/=255; blue/=255;
		int key = (int) Math.round((1-max_value(red, green, blue))*100);
		int cyan, magenta, yellow;
		cyan = (int) Math.round(((1-red-(key/100))/(1-(key/100)))*100);
		magenta =(int) Math.round(((1-green-(key/100))/(1-(key/100)))*100);
		yellow = (int) Math.round(((1-blue-(key/100))/(1-(key/100)))*100);
		
		String cmyk = "cmyk(" + cyan + "%," + magenta + "%," + yellow + "%," + key + "%)";
		
		return cmyk;
	}
	
	public double max_value(double red, double green, double blue)
	{
		if(red>green)
		{
			if(green>blue)
			{
				return red;
			}else if(green>red)
			{
				return blue;
			}else{
				return red;
			}
		}else{
			if(red>blue)
			{
				return green;
			}else if(blue>green)
			{
				return blue;
			}else{
				return green;
			}
		}
	}
	
	private class Cambio implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			int r, g, b;
			r = redSlider.getValue();
			g = greenSlider.getValue();
			b = blueSlider.getValue();
			cambiarFondo(r, g, b);
		}
		
	}
	

}

