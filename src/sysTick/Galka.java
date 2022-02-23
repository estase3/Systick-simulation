package sysTick;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class Galka extends JComponent implements MouseListener, MouseMotionListener{
	double x=0; //wsp kola o promieniu 1
	double y=1;
	double kat = 0;
	int res=0;
	
	public void setX(double x) {
		this.x=x;
		repaint();
	}
	public void setY(double y) {
		this.y=y;
		repaint();
	}
	public Galka() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d=getSize();
		g.setColor(Color.blue);
		int promien=Math.min(d.width/2,d.height/2) ;
		g.fillOval(d.width/2-promien,d.height/2-promien,2*promien,2*promien);
		
		g.setColor(Color.black);
		int p=promien/10;
		
		int xx=(int)(x*3*promien/4);
		int yy=(int)(y*3*promien/4);
		
		g.fillOval(d.width/2-p + xx, d.height/2-p - yy, 2*p, 2*p);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		

	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Point p = arg0.getPoint();
				Dimension d=getSize();
				int promien=Math.min(d.width/2,d.height/2) ;
				
				if(p.distance(d.width/2, d.height/2)<promien) {
					int a=arg0.getX() - d.width/2;
					int b=arg0.getY() - d.height/2 ;		
					kat=Math.atan2((double)b, (double)a);
					y=-Math.sin(kat);
					x=Math.cos(kat);
					res=(int) Math.round(Math.toDegrees(kat)+90);
					if(res<0)
						res+=360;
					
					repaint();
					}
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
