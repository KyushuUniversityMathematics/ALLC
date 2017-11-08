package sub;

import sub.number;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class bf1 extends JFrame implements ActionListener{
	
    int[][] initial,result;
	
	JButton mybtn1;
    
    String CurrentDirectoryPath;
    
    JPanel p;
	
    public bf1(){
        setTitle("melon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new JPanel();
        mybtn1 =new JButton("初期値を選択");
        mybtn1.addActionListener(this);
        
        p.setBackground(Color.ORANGE);
        p.add(mybtn1,BorderLayout.LINE_START);
        add(p);
        pack();
        
    }
    
    public void actionPerformed(ActionEvent e){
    	//mybtn1を押した時の処理
        if (e.getSource() == mybtn1) {
        	JFileChooser chooser = new JFileChooser(CurrentDirectoryPath);
        	int retVal = chooser.showOpenDialog(this);
        	if (retVal == JFileChooser.APPROVE_OPTION){
        		File file = chooser.getSelectedFile();
        		String fname = file.getPath();
                CurrentDirectoryPath = fname.substring(0,1+fname.lastIndexOf("/"));

        		try {
        			ArrayList<Integer> list = NumberImport(fname);
        			int secondlength=NumberImportLength(fname);
                    int row = list.size()/secondlength;
                    int c = secondlength;
                    
                    initial = new int[row][c];
                    for (int i = 0; i < row; i++) {
						for(int j=0;j<c;j++){
							initial[i][j]=list.get(i*row+j);
                        }
					}
                    number h = new number(initial);
                    add(h, BorderLayout.CENTER);
                    add(p, BorderLayout.SOUTH);
                    pack();
				} catch (Exception e2) {
					System.out.println(e);
				}
        	}
            
        
        }
        

    }
    
    //区切り文字は空白と仮定
    public static ArrayList<Integer> NumberImport(String filename) throws IOException{
    	
        ArrayList<Integer> list = new ArrayList<Integer>();
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        
        while (sc.hasNextInt()) {
            list.add(sc.nextInt());
        }
        sc.close();
        return list;
    }

    //2次元配列の2番目の長さを取得(a[2][3]だったら3を取得)
    public static int NumberImportLength(String fname)throws IOException{
        
	   int temp=0;
        
       BufferedReader inb = new BufferedReader(new FileReader(fname));
       String line;
       if((line = inb.readLine()) != null) {
           String[] Temp = line.split(" ", 0);
           temp=Temp.length;
       }
       inb.close();
 	   return temp;
   }
}
