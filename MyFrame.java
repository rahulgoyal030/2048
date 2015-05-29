import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.BorderFactory;


class MyFrame implements KeyListener,ActionListener
{
    JFrame f;
    JLabel  pos[]= new JLabel[18];
    static int randomNum=0;
    int posValue[]= new int[18];
    int flag[] = new int[18];
    int scoreValue=0;
    JButton start;
    JLabel score,name;

    MyFrame()
    {
        f= new JFrame("2048");
        f.setSize(600,750);
		f.setLayout(null);

		//setContentPane(new JLabel( new ImageIcon("pic.png")));
		Container can = f.getContentPane();
		Color c= new Color(255, 153, 51);
		can.setBackground(c);    // to change backgroung color of frame
		f.setBounds(270,1,600,750);


        int x=1,y=1;
		for(int i=1;i<=16;i++)
		{
			pos[i]= new JLabel("",SwingConstants.CENTER);
	
			if(x>4)
			{
			  x=1;
			  y++;
		    }

			
			pos[i].setBounds(30+x*90,90+y*90,90,90);
			pos[i].setOpaque(true);
			pos[i].setBackground(new Color(255, 203, 152));
			f.add(pos[i]);

			pos[i].setBorder(BorderFactory.createLineBorder(Color.black,3));
			pos[i].setFont(new Font("",Font.BOLD,20));


 
			x++;

			flag[i]=0;	


		}



		start = new JButton("New Game");
		start.setBounds(210,590,150,40);
		f.add(start);
		start.addKeyListener(this);
		start.addActionListener(this);

		score= new JLabel("SCORE");
		f.add(score);
		score.setBounds(200,110,200,40);
		score.setFont(new Font("",Font.BOLD,38));

		name= new JLabel("2048!");
		f.add(name);
		name.setBounds(205,30,200,50);
		name.setFont(new Font("",Font.BOLD,50));
		name.setForeground(new Color(51, 51, 255));




		f.setVisible(true);

    }



    public static void main(String[] args)
    {
		new MyFrame();
	}
    

	public static int randInt() {

    // NOTE: Usually this should be a field rather than a method
    // variable so that it is not re-seeded every call.
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
     randomNum = rand.nextInt(16)+1 ;                   // nextInt( max-min+1) + min
    if(randomNum==0)
         	randomNum=4;

     
    return randomNum;

    }
    public  void actionPerformed(ActionEvent e)
    {
    	if(e.getSource()==start)
    	{
    		for(int i=1;i<=16;i++)
    		{
    			pos[i].setText("");
    		}
    		int value= MyFrame.randInt();

    		pos[value].setText(2+"");

    	}
    }

    public void keyReleased(KeyEvent e)
    {
    	for(int i=1;i<=16;i++)
    	{
    		String value =pos[i].getText();

    		if(value=="")
    			value="0";
    		posValue[i]= Integer.parseInt(value);

    	//	System.out.println(posValue[i]);
    	}

    	if(e.getKeyCode()==KeyEvent.VK_UP)
    	{
    		for(int i=1;i<=4;i++)
    		{
    			int y1=i;
    			int y2,y3,y4;
    			y2=y1+4; y3=y1+8; y4=y1+12;

    			if(posValue[y1]==0)
    			{   int loop =1;
    				while(posValue[y1]==0&&loop<=3)
    				{
    					posValue[y1] = posValue[y2];
    					posValue[y2] = posValue[y3];
    					posValue[y3] = posValue[y4];
    					posValue[y4] =0;
    					//System.out.println(loop + " "+posValue[y1]  + posValue[y2]+ posValue[y3]+ posValue[y4]);

    					loop++;
    				}
    			}
    			else if(posValue[y2]==0&&posValue[y1]!=0)
    			{
    				int loop=1;
    				while(posValue[y2]==0&&loop<=2)
    				{
    					posValue[y2] = posValue[y3];
    					posValue[y3] = posValue[y4];
    					posValue[y4] =0;

    					loop++;
    				}
    			}
    			else if(posValue[y2]!=0&&posValue[y1]!=0&&posValue[y3]==0)
    			{
    				posValue[y3] = posValue[y4];
    				posValue[y4] =0;
    			}

    			if(posValue[y1]==posValue[y2])
    			{
    				if(posValue[y3]==posValue[y4])
    				{
    					posValue[y1] +=posValue[y2];
    					posValue[y3] +=posValue[y4];

    					scoreValue += posValue[y1] +posValue[y3];

    					posValue[y2]=posValue[y3];
    					posValue[y3]=0;
    					posValue[y4]=0;

    				}
    				else
    				{
    					posValue[y1] *= 2;

    					scoreValue += posValue[y1];
    					posValue[y2]=posValue[y3];
    					posValue[y3]=posValue[y4];
    					posValue[y4]=0;

    				}
    			}
    			else if(posValue[y1]!=posValue[y2])
    			{
    				if(posValue[y2]==posValue[y3])
    				{
    					posValue[y2] *= 2;
    					scoreValue += posValue[y2];
    					posValue[y3]=posValue[y4];
    					posValue[y4]=0;
    				}
    				else if(posValue[y3]==posValue[y4])
    				{
    					posValue[y3] *=2;
    					scoreValue += posValue[y3];
    					posValue[y4]=0;
    				}

    			}


                
    		}

   
    	}
    	if(e.getKeyCode()==KeyEvent.VK_DOWN)
    	{
           // System.out.println("hello");   // just for debug
    		for(int i=1;i<=4;i++)
    		{
    			int y1=12+i;
    			int y2,y3,y4;
    			y2=y1-4; y3=y1-8; y4=y1-12;

    			if(posValue[y1]==0)
    			{   int loop =1;
    				while(posValue[y1]==0&&loop<=3)
    				{
    					posValue[y1] = posValue[y2];
    					posValue[y2] = posValue[y3];
    					posValue[y3] = posValue[y4];
    					posValue[y4] =0;
    					//System.out.println(loop + " "+posValue[y1]  + posValue[y2]+ posValue[y3]+ posValue[y4]);

    					loop++;
    				}
    			}
    			else if(posValue[y2]==0&&posValue[y1]!=0)
    			{
    				int loop=1;
    				while(posValue[y2]==0&&loop<=2)
    				{
    					posValue[y2] = posValue[y3];
    					posValue[y3] = posValue[y4];
    					posValue[y4] =0;

    					loop++;
    				}
    			}
    			else if(posValue[y2]!=0&&posValue[y1]!=0&&posValue[y3]==0)
    			{
    				posValue[y3] = posValue[y4];
    				posValue[y4] =0;
    			}

    			if(posValue[y1]==posValue[y2])
    			{
    				if(posValue[y3]==posValue[y4])
    				{
    					posValue[y1] +=posValue[y2];
    					posValue[y3] +=posValue[y4];

    					scoreValue += posValue[y1] +posValue[y3];

    					posValue[y2]=posValue[y3];
    					posValue[y3]=0;
    					posValue[y4]=0;

    				}
    				else
    				{
    					posValue[y1] *= 2;
    					scoreValue += posValue[y1] ;
    					posValue[y2]=posValue[y3];
    					posValue[y3]=posValue[y4];
    					posValue[y4]=0;

    				}
    			}
    			else if(posValue[y1]!=posValue[y2])
    			{
    				if(posValue[y2]==posValue[y3])
    				{
    					posValue[y2] *= 2;
    					scoreValue += posValue[y2] ;
    					posValue[y3]=posValue[y4];
    					posValue[y4]=0;
    				}
    				else if(posValue[y3]==posValue[y4])
    				{
    					posValue[y3] *=2;
    					scoreValue += posValue[y3];
    					posValue[y4]=0;
    				}

    			}


                
    		}

    	}
    	if(e.getKeyCode()==KeyEvent.VK_LEFT)
    	{
    		for(int i=1;i<=4;i++)
    		{
    			int y1=1+((i-1)*4);
    			int y2,y3,y4;
    			y2=y1+1; y3=y1+2; y4=y1+3;

    			if(posValue[y1]==0)
    			{   int loop =1;
    				while(posValue[y1]==0&&loop<=3)
    				{
    					posValue[y1] = posValue[y2];
    					posValue[y2] = posValue[y3];
    					posValue[y3] = posValue[y4];
    					posValue[y4] =0;
    					//System.out.println(loop + " "+posValue[y1]  + posValue[y2]+ posValue[y3]+ posValue[y4]);

    					loop++;
    				}
    			}
    			else if(posValue[y2]==0&&posValue[y1]!=0)
    			{
    				int loop=1;
    				while(posValue[y2]==0&&loop<=2)
    				{
    					posValue[y2] = posValue[y3];
    					posValue[y3] = posValue[y4];
    					posValue[y4] =0;

    					loop++;
    				}
    			}
    			else if(posValue[y2]!=0&&posValue[y1]!=0&&posValue[y3]==0)
    			{
    				posValue[y3] = posValue[y4];
    				posValue[y4] =0;
    			}

    			if(posValue[y1]==posValue[y2])
    			{
    				if(posValue[y3]==posValue[y4])
    				{
    					posValue[y1] +=posValue[y2];
    					posValue[y3] +=posValue[y4];

    					scoreValue += posValue[y1] +posValue[y3];

    					posValue[y2]=posValue[y3];
    					posValue[y3]=0;
    					posValue[y4]=0;

    				}
    				else
    				{
    					posValue[y1] *= 2;
    					scoreValue += posValue[y1] ;
    					posValue[y2]=posValue[y3];
    					posValue[y3]=posValue[y4];
    					posValue[y4]=0;

    				}
    			}
    			else if(posValue[y1]!=posValue[y2])
    			{
    				if(posValue[y2]==posValue[y3])
    				{
    					posValue[y2] *= 2;
    					scoreValue += posValue[y2] ;
    					posValue[y3]=posValue[y4];
    					posValue[y4]=0;
    				}
    				else if(posValue[y3]==posValue[y4])
    				{
    					posValue[y3] *=2;
    					scoreValue += posValue[y3];
    					posValue[y4]=0;
    				}

    			}


                
    		}


    	}
    	if(e.getKeyCode()==KeyEvent.VK_RIGHT)
    	{
    		for(int i=1;i<=4;i++)
    		{
    			int y1=i*4;
    			int y2,y3,y4;
    			y2=y1-1; y3=y1-2; y4=y1-3;

    			if(posValue[y1]==0)
    			{   int loop =1;
    				while(posValue[y1]==0&&loop<=3)
    				{
    					posValue[y1] = posValue[y2];
    					posValue[y2] = posValue[y3];
    					posValue[y3] = posValue[y4];
    					posValue[y4] =0;
    					//System.out.println(loop + " "+posValue[y1]  + posValue[y2]+ posValue[y3]+ posValue[y4]);

    					loop++;
    				}
    			}
    			else if(posValue[y2]==0&&posValue[y1]!=0)
    			{
    				int loop=1;
    				while(posValue[y2]==0&&loop<=2)
    				{
    					posValue[y2] = posValue[y3];
    					posValue[y3] = posValue[y4];
    					posValue[y4] =0;

    					loop++;
    				}
    			}
    			else if(posValue[y2]!=0&&posValue[y1]!=0&&posValue[y3]==0)
    			{
    				posValue[y3] = posValue[y4];
    				posValue[y4] =0;
    			}

    			if(posValue[y1]==posValue[y2])
    			{
    				if(posValue[y3]==posValue[y4])
    				{
    					posValue[y1] +=posValue[y2];
    					posValue[y3] +=posValue[y4];
    					scoreValue += posValue[y1] +posValue[y3];

    					posValue[y2]=posValue[y3];
    					posValue[y3]=0;
    					posValue[y4]=0;

    				}
    				else
    				{
    					posValue[y1] *= 2;
    					scoreValue += posValue[y1];
    					posValue[y2]=posValue[y3];
    					posValue[y3]=posValue[y4];
    					posValue[y4]=0;

    				}
    			}
    			else if(posValue[y1]!=posValue[y2])
    			{
    				if(posValue[y2]==posValue[y3])
    				{
    					posValue[y2] *= 2;
    					scoreValue +=posValue[y2];
    					posValue[y3]=posValue[y4];
    					posValue[y4]=0;
    				}
    				else if(posValue[y3]==posValue[y4])
    				{
    					posValue[y3] *=2;
    					scoreValue += posValue[y3];
    					posValue[y4]=0;
    				}

    			}


                
    		}


    	}

    	for(int i=1;i<=16;i++)
    	{
    		if(posValue[i]==0)
    		{
    			pos[i].setText("");
    			flag[i]=0;
    		}
    		else
    		{
    			pos[i].setText(posValue[i]+"");
    			flag[i]=1;
    		}
    	}


    	int value1= MyFrame.randInt();
        
        if(flag[value1]==1)
        {
         while(flag[value1]==1)
         {
          value1 =	MyFrame.randInt();
         }
        }
    	pos[value1].setText(2+"");

    	score.setText("Score "+scoreValue);

    }
    public void keyTyped(KeyEvent e)
    {
        
    }
    public void keyPressed(KeyEvent e)
    {

    }

}