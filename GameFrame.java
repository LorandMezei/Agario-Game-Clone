package game;

import javax.swing.*; import javax.swing.event.*; import java.awt.*; import java.awt.event.*; 
import java.io.*; import java.net.*; import java.applet.*; 
import javax.sound.sampled.*; import sun.audio.*; import java.io.*;

public class GameFrame extends JFrame 
{   
    private final int FRAME_X_SIZE = 1000;
    private final int FRAME_Y_SIZE = 1000;
    
    private int frame_mouse_x_coor;
    private int frame_mouse_y_coor;
    private double frame_slope;
    
    private Draw circles;
    
    private Timer dotTimer;
    private Timer moveTimer;
    
    private JLabel radiusLabel = new JLabel();
    private JPanel radiusPanel = new JPanel();
    
    public GameFrame()
    {                   
        setSize(FRAME_X_SIZE, FRAME_Y_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create blob and dots
        circles = new Draw(2500, FRAME_X_SIZE, FRAME_Y_SIZE);
        add(circles);
        
        addMouseMotionListener(new MyMouseMotionListener()); 
        
        dotTimer = new Timer(10, new DotTimerListener());
        dotTimer.start();
        
        moveTimer = new Timer(1, new MoveTimerListener());
        moveTimer.start();
        
        setVisible(true);
    } 
    
    private class MyMouseMotionListener implements MouseMotionListener
    {
        private int mouse_x_coor;
        private int mouse_y_coor;
                
        public void mouseDragged(MouseEvent e)
        {}
        
        public void mouseMoved(MouseEvent e)
        {
            mouse_x_coor = e.getX() - 10;
            mouse_y_coor = e.getY() - 40;
            
            frame_mouse_x_coor = mouse_x_coor;
            frame_mouse_y_coor = mouse_y_coor;
            
            circles.getBlobsArrayList().get(0).setDistanceFromMouse(mouse_x_coor, mouse_y_coor);
            
            moveBySlope(mouse_x_coor, mouse_y_coor);
        }        
        
        public void moveBySlope(int mousex, int mousey)
        { 
            double slope = 0;
            
            if (mousex < 500)
            {
                slope = (double)(500 - mousey) / (double)(500 - mousex); 
                frame_slope = slope;
                System.out.println("Slope = " + slope);
            }
            
            if (mousex > 500)
            {
                slope = (double)(mousey - 500) / (double)(mousex - 500);
                frame_slope = slope;
                System.out.println("Slope = " + slope);
            }
            
            if (mousex == 500){System.out.println("Slope is undefined");}         
        }   
    }      
    
    public void createAudioFile()
    {        
        try 
        {
            File pop =
            new File("C:\\Users\\loran\\Downloads\\splooge.wav");
            
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(pop));
            clip.start();
        }  
        
        catch (Exception e) 
        { 
            System.out.println("File was not found");
        }
    }
        
    private class DotTimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (circles.getNumberOfDots() < 1000)
            {
                circles.createDot();
                repaint();
            }   
        }            
    }       
    
    private class MoveTimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // move up
            if (frame_mouse_x_coor == circles.getBlobsArrayList().get(0).getCenterXCoor()  
                && frame_mouse_y_coor < circles.getBlobsArrayList().get(0).getCenterYCoor())
            {
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots 
                        circles.getDotsArrayList().get(i).increaseYCoor(1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(1);
                    }  
            }   
            
            // move down
            else if (frame_mouse_x_coor == circles.getBlobsArrayList().get(0).getCenterXCoor()  
                && frame_mouse_y_coor > circles.getBlobsArrayList().get(0).getCenterYCoor())
            {
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots 
                        circles.getDotsArrayList().get(i).increaseYCoor(-1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(-1);
                    }  
            }
            
            // move left ///////////////////////////////////////////////////////////////
            else if ((frame_mouse_x_coor < circles.getBlobsArrayList().get(0).getCenterXCoor())   
                     )
            {
                if (frame_slope == 0)
                {
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
               
                        circles.getDotsArrayList().get(i).increaseXCoor(1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(1);
                    }
                }
                
                else if (frame_slope > -1 && frame_slope < 0)
                {
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
               
                        circles.getDotsArrayList().get(i).increaseXCoor(2);
                        circles.getDotsArrayList().get(i).increaseYCoor(-1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(2);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(2);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(2);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(2);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(-1);
                    
                    } 
                }
                
                else if (frame_slope == -1)
                {
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
               
                        circles.getDotsArrayList().get(i).increaseXCoor(1);
                        circles.getDotsArrayList().get(i).increaseYCoor(-1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(-1);
                    
                    } 
                }
                
                else if (frame_slope > -1000 && frame_slope < -1)
                {
                    //x + 2; y + 1
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
                        circles.getDotsArrayList().get(i).increaseXCoor(1);
                        circles.getDotsArrayList().get(i).increaseYCoor(-2); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();   
                    }
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(-2);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(-2);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(-2);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(-2);
                    } 
                }
                
                else if (frame_slope > 0 && frame_slope < 1)
                {
                    //x + 2; y + 1
                    
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
               
                        circles.getDotsArrayList().get(i).increaseXCoor(2);
                        circles.getDotsArrayList().get(i).increaseYCoor(1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(2);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(2);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(2);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(2);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(1);
                    } 
                }
                
                else if (frame_slope == 1)
                {
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
               
                        circles.getDotsArrayList().get(i).increaseXCoor(1);
                        circles.getDotsArrayList().get(i).increaseYCoor(1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(1);
                    
                    } 
                }
                
                else if (frame_slope > 1 && frame_slope < 1000)
                {
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
                        circles.getDotsArrayList().get(i).increaseXCoor(1);
                        circles.getDotsArrayList().get(i).increaseYCoor(2); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();   
                    }
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(2);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(2);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(2);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(2);
                    } 
                } 
            }    
                
            // move right /////////////////////////////////////////////////////////////////////
            else if ((frame_mouse_x_coor > circles.getBlobsArrayList().get(0).getCenterXCoor())   
                     )
            {
                if (frame_slope == 0)
                {
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
               
                        circles.getDotsArrayList().get(i).increaseXCoor(-1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(-1);
                    }
                }
                
                else if (frame_slope > -1 && frame_slope < 0)
                {
                    //x + 2; y + 1
                    
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
               
                        circles.getDotsArrayList().get(i).increaseXCoor(-2);
                        circles.getDotsArrayList().get(i).increaseYCoor(1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(-2);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(-2);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(-2);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(-2);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(1);
                    
                    } 
                }
                
                else if (frame_slope == -1)
                {
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
               
                        circles.getDotsArrayList().get(i).increaseXCoor(-1);
                        circles.getDotsArrayList().get(i).increaseYCoor(1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(1);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(1);
                    
                    } 
                }
                
                else if (frame_slope > -1000 && frame_slope < -1)
                {
                    //x + 2; y + 1
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
                        circles.getDotsArrayList().get(i).increaseXCoor(-1);
                        circles.getDotsArrayList().get(i).increaseYCoor(2); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();   
                    }
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(2);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(2);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(2);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(2);
                    } 
                }
                
                else if (frame_slope > 0 && frame_slope < 1)
                {
                    //x + 2; y + 1
                    
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
               
                        circles.getDotsArrayList().get(i).increaseXCoor(-2);
                        circles.getDotsArrayList().get(i).increaseYCoor(-1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(-2);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(-2);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(-2);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(-2);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(-1);
                    } 
                }
                
                else if (frame_slope == 1)
                {
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
               
                        circles.getDotsArrayList().get(i).increaseXCoor(-1);
                        circles.getDotsArrayList().get(i).increaseYCoor(-1); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();
                    }    
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(1);
                    
                    } 
                }
                
                else if (frame_slope > 1 && frame_slope < 1000)
                {
                    //x + 2; y + 1
                    for (int i = 0; i < circles.getDotsArrayList().size(); i++)
                    {
                    // for dots
                        circles.getDotsArrayList().get(i).increaseXCoor(-1);
                        circles.getDotsArrayList().get(i).increaseYCoor(-2); 
                    
                        circles.getDotsArrayList().get(i).setCenterXCoor(); 
                        circles.getDotsArrayList().get(i).setCenterYCoor();   
                    }
                    
                    for (int i = 0; i < circles.getGridsArrayList().size(); i++)
                    {
                    // for grid
                        circles.getGridsArrayList().get(i).increaseStartXCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorHorizontal(-2);
                        circles.getGridsArrayList().get(i).increaseEndXCoorHorizontal(-1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorHorizontal(-2);
                        circles.getGridsArrayList().get(i).increaseStartXCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseStartYCoorVertical(-2);
                        circles.getGridsArrayList().get(i).increaseEndXCoorVertical(-1);
                        circles.getGridsArrayList().get(i).increaseEndYCoorVertical(-2);
                    } 
                } 
                
            }        
////////////////////////////////////////////////////////////////////////////////////
            for (int i = 0; i < circles.getDotsArrayList().size(); i++)
            {
                circles.getDotsArrayList().get(i).setDistanceFromBlobCenter();
            }    
            
            for (int i = 0; i < circles.getDotsArrayList().size(); i++)
            {
                if (circles.getDotsArrayList().get(i).getDistanceFromBlobCenter() <= 
                    circles.getBlobsArrayList().get(0).getRadiusOfBlob())
                {
                    // remove the blob
                    circles.removeDot(i);
                    
                    // play sound
                    createAudioFile();
                    
                    // increase size of blob
                    circles.getBlobsArrayList().get(0).setHeight(1);
                    circles.getBlobsArrayList().get(0).setWidth(1);
                    
                    // move blob to compensate the change in size so it stays centered
                    circles.getBlobsArrayList().get(0).setXCoor((int)(500 - circles.getBlobsArrayList().get(0).getRadiusOfBlob()));
                    circles.getBlobsArrayList().get(0).setYCoor((int)(500 - circles.getBlobsArrayList().get(0).getRadiusOfBlob()));  
                    
                    // update blob center 
                    circles.getBlobsArrayList().get(0).setCenterXCoor();
                    circles.getBlobsArrayList().get(0).setCenterYCoor();
                            
                    //repaint(); // dont think i need
                } 
            } 
            
            repaint();  
        }       
    }    
}
