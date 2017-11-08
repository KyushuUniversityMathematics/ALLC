package sub;
import java.awt.*;
import javax.swing.*;

public class number extends JPanel{
    int width,height;
    int mat[][];
    int n,m;
	public number(int a[][]){
        n = a.length;
        m = a[0].length;
        height=n+2;
        width=m+2;
        mat = new int[n+2][m+2];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                if(i==0||i==n+1||j==0||j==m+1){
                    mat[i][j]=-1; 
                }else{
                    mat[i][j]= a[i-1][j-1];
                }
            }
        }
		setBackground(Color.white);
		setPreferredSize(new Dimension(width*75,height*75));
	}
	
	protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g2);
        double w =getWidth();
        double h =getHeight();
        int mas;
        
        int  wmas = (int) w/width;
        int  hmas = (int) h/height;
        if(wmas<=hmas){
            mas = wmas;
        }else{
            mas = hmas;
        }
        int color[][]={{255,0,0},{0,255,0},{0,0,255},{255,255,0},{255,0,255},{0,255,255},{127,0,0},{0,127,0},{0,0,127},{127,127,0},{127,0,127},{0,127,127}};
        
        //線の太さ
        float s1 = (float)(mas/10.0);
        BasicStroke wideStroke = new BasicStroke(s1);
        
        //枠の作成
        for(int i=1;i<height;i++ ){
                g2.drawLine(mas, i*mas, mas*(width-1), i*mas);
                g2.drawLine(i*mas, mas, i*mas, mas*(height-1));
        }
        
        //フォント文字の大きさ
        Font font = new Font("Arial", Font.PLAIN, mas);
        g2.setFont(font);
        
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                //色の変更
                Color c = new Color(color[mat[i][j]%12][0],color[mat[i][j]%12][1],color[mat[i][j]%12][2]);
                g2.setColor(c);
                
                byte flag = 0;
                if(mat[i][j]!=0){
                    int f[][] = new int[4][3];
                    if(mat[i-1][j]==mat[i][j]){
                        f[0][0] =mas*(2*j+1)/2;
                        f[0][1] =mas*i;
                        f[0][2] = 1;
                        flag+=1;
                    }
                    if(mat[i][j-1]==mat[i][j]){
                        f[1][0]=mas*j;
                        f[1][1]=mas*(2*i+1)/2;
                        f[1][2] = 1;
                        flag+=1;
                    }
                    if(mat[i][j+1]==mat[i][j]){
                        f[2][0]=mas*(j+1);
                        f[2][1]=mas*(2*i+1)/2;
                        f[2][2] = 1;
                        flag+=1;
                    }
                    if(mat[i+1][j]==mat[i][j]){
                        f[3][0]=mas*(2*j+1)/2;
                        f[3][1]=mas*(i+1);
                        f[3][2] = 1;
                        flag+=1;
                    }
                    
                    if(flag==1) {
                        g2.drawString(String.valueOf(mat[i][j]),(j)*mas+2*mas/10,(i+1)*mas-mas/10);
                    }else if(flag==0&&mat[i][j]!=0){
                        g2.drawString(String.valueOf(mat[i][j]),(j)*mas+2*mas/10,(i+1)*mas-mas/10);
                    }else{
                        for(int k=0;k<4;k++){
                            if(f[k][2] ==1){
                                g2.setStroke(wideStroke);
                                g2.drawLine(mas*(2*j+1)/2, mas*(2*i+1)/2,f[k][0] ,f[k][1] );
                            }
                        }
                    }
                }
            }
        }
        
	}
}
