package game;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Draw extends JPanel
{       
    private int numberOfDots = 0;
    
    private ArrayList<Blob> blobs = new ArrayList<Blob>();
    private ArrayList<Dot> dots = new ArrayList<Dot>();
    private ArrayList<Grid> grids = new ArrayList<Grid>();
    
    private Random blobColorRng = new Random();
    private Random dotColorRng = new Random();
    
    private int mouse_x_coor;
    private int mouse_y_coor;
    
    private int frame_x_size;
    private int frame_y_size;
  
    public Draw(int n, int x, int y)
    {
        frame_x_size = x;
        frame_y_size = y;
        
        // create blob
        blobs.add(new Blob(x, y));
        
        // create dots
        numberOfDots = n;
        for (int i = 0; i < numberOfDots; i++)
        {
            dots.add(new Dot());
        }    
        
        // create grid
        int firstValue = 25;
        int increaseBy = 0;
        for (int i = 0; i < 40; i++)
        {
            grids.add(new Grid(firstValue, increaseBy));
            increaseBy += 25;
        }    
    }        
     
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
         
        // makes a grid
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 40; i++)
        {
            // horizontal lines
            g.drawLine(grids.get(i).start_x_coor_horizontal,
                       grids.get(i).start_y_coor_horizontal,
                       grids.get(i).end_x_coor_horizontal,
                       grids.get(i).end_y_coor_horizontal
                      );
            // vertical lines
            g.drawLine(grids.get(i).start_x_coor_vertical,
                       grids.get(i).start_y_coor_vertical,
                       grids.get(i).end_x_coor_vertical,
                       grids.get(i).end_y_coor_vertical
                      );
        }
        
        // center of frame
        g.setColor(Color.BLACK);
        g.drawLine(0, 500, 1000, 500);
        g.drawLine(500, 0, 500, 1000);
        
        
        // draws each dot in arraylist of dots   
        for (int i = 0; i < dots.size(); i++)
        {
            switch(dots.get(i).currentColor)
            {
                case 0: g.setColor(Color.RED); break;
                case 1: g.setColor(Color.GREEN); break;
                case 2: g.setColor(Color.BLUE); break;
                case 3: g.setColor(Color.CYAN); break;
                case 4: g.setColor(Color.MAGENTA); break;
                case 5: g.setColor(Color.ORANGE); break;
                case 6: g.setColor(Color.PINK); break;
                case 7: g.setColor(Color.YELLOW); break;
                default:
            }   
            
            // controls relative size of blob to dots
            if (blobs.get(0).radius <= 50)
            {
                g.fillOval(dots.get(i).getXCoor(), dots.get(i).getYCoor(), 
                           dots.get(i).width, dots.get(i).height);
            }    
            else if (blobs.get(0).radius > 50 && blobs.get(0).radius < 100)
            {
                g.fillOval(dots.get(i).getXCoor(), dots.get(i).getYCoor(), 
                           dots.get(i).width - 1, dots.get(i).height - 1);  
            }    
            else if (blobs.get(0).radius >= 100 && blobs.get(0).radius < 200)
            {
                g.fillOval(dots.get(i).getXCoor(), dots.get(i).getYCoor(), 
                           dots.get(i).width - 2, dots.get(i).height - 2);  
            }
            
            else if (blobs.get(0).radius >= 200)
            {
                g.fillOval(dots.get(i).getXCoor(), dots.get(i).getYCoor(), 
                           dots.get(i).width - 4, dots.get(i).height - 4);  
            }
        }
        // move origin
        //g.translate(500, 500);
        
        //blobs.get(0).setXCoor(-(int)(blobs.get(0).radius));
        //blobs.get(0).setYCoor(-(int)(blobs.get(0).radius));
        //blobs.get(0).setCenterXCoor();
        //blobs.get(0).setCenterYCoor();
        
        // show coordinates of blob
        //System.out.println("Coordinates of blob are: " + blobs.get(0).x_coor 
        //                    + ", " + blobs.get(0).y_coor);
       
        // one blob in arraylist of blobs
        switch(blobs.get(0).currentColor)
        {
            case 0: 
                g.setColor(Color.RED);
                break;
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.RED);
                break;
                default:
        }
        g.fillOval(blobs.get(0).getXCoor(), blobs.get(0).getYCoor(),
                   blobs.get(0).width, blobs.get(0).width);
       
        g.setColor(Color.BLACK);
        
        // visual for moving blob
        g.drawLine((blobs.get(0).x_coor),
                   (blobs.get(0).y_coor),
                   (blobs.get(0).x_coor), 
                   (blobs.get(0).y_coor + (int)(blobs.get(0).radius) * 2)
                  );
        g.drawLine((blobs.get(0).x_coor + (int)(blobs.get(0).radius) * 2),
                   (blobs.get(0).y_coor),
                   (blobs.get(0).x_coor + (int)(blobs.get(0).radius) * 2), 
                   (blobs.get(0).y_coor + (int)(blobs.get(0).radius) * 2)
                  );
        g.drawLine((blobs.get(0).x_coor),
                   (blobs.get(0).y_coor + (int)(blobs.get(0).radius) * 2),
                   (blobs.get(0).x_coor + (int)(blobs.get(0).radius) * 2), 
                   (blobs.get(0).y_coor + (int)(blobs.get(0).radius) * 2)
                  );
        g.drawLine((blobs.get(0).x_coor),
                   (blobs.get(0).y_coor),
                   (blobs.get(0).x_coor + (int)(blobs.get(0).radius) * 2), 
                   (blobs.get(0).y_coor)
                  );
        g.drawLine((blobs.get(0).x_coor),
                   (blobs.get(0).y_coor + (int)(blobs.get(0).radius) * 2),
                   (blobs.get(0).x_coor + (int)(blobs.get(0).radius) * 2), 
                   (blobs.get(0).y_coor + (int)(blobs.get(0).radius) * 2)
                  );
        
        // diagonal visual for moving blob
        g.drawLine((blobs.get(0).center_x_coor),
                   (blobs.get(0).center_y_coor),
                   (blobs.get(0).center_x_coor + (int)(blobs.get(0).radius)), 
                   (blobs.get(0).center_y_coor - (int)(blobs.get(0).radius))
                  );
        g.drawLine((blobs.get(0).center_x_coor),
                   (blobs.get(0).center_y_coor),
                   (blobs.get(0).center_x_coor - (int)(blobs.get(0).radius)), 
                   (blobs.get(0).center_y_coor + (int)(blobs.get(0).radius))
                  );
        g.drawLine((blobs.get(0).center_x_coor),
                   (blobs.get(0).center_y_coor),
                   (blobs.get(0).center_x_coor - (int)(blobs.get(0).radius)), 
                   (blobs.get(0).center_y_coor - (int)(blobs.get(0).radius))
                  );
        g.drawLine((blobs.get(0).center_x_coor),
                   (blobs.get(0).center_y_coor),
                   (blobs.get(0).center_x_coor + (int)(blobs.get(0).radius)), 
                   (blobs.get(0).center_y_coor + (int)(blobs.get(0).radius))
                  );
      
        // line from center of blob to mouse
        g.drawLine(blobs.get(0).center_x_coor, blobs.get(0).center_y_coor,
                   mouse_x_coor, mouse_y_coor);
        
        // center of blob
        g.fillOval(blobs.get(0).center_x_coor - 3, blobs.get(0).center_y_coor - 3, 6, 6);
        
        // display size on blob
        String sizeOfBlob = "Radius of blob = " + String.valueOf(blobs.get(0).radius);
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 35)); 
        g.drawString(sizeOfBlob, 25, 50);
        
        // display mouse coordinates
        String mouseCoordinates = "Mouse Coordinates = " 
                                  + String.valueOf("(" + mouse_x_coor + ", " + mouse_y_coor + " )");
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 15)); 
        g.drawString(mouseCoordinates, 25, 100);
        
        // (x,y) coor of blob
        g.fillOval(blobs.get(0).x_coor - 3, blobs.get(0).y_coor - 3, 6, 6);
    }  
    
//#############################################################################    
    protected class Blob
    {
        private int x_coor;
        private int y_coor;
        
        private int center_x_coor;
        private int center_y_coor;
        
        private int width;
        private int height;
        private double radius;
        
        private double distanceFromMouse;
        
        private int currentColor;
    
        public Blob(int framex, int framey)
        {
            setWidth(150);
            setHeight(150);
            radius = width/2;
            setXCoor((int)((framex/2) - radius));
            setYCoor((int)((framey/2) - radius));
            setCenterXCoor();
            setCenterYCoor();
            
            currentColor = blobColorRng.nextInt(3);
        }        
        
        public void setRadius()
        {
            radius = width/2;
        }        
        
        public void setHeight(int heightIncrease)
        {
            if (height <= 500)
                height += heightIncrease;
            
            radius = width/2;
        }

        public void setWidth(int widthIncrease)
        {
            if (width <= 500)
                width += widthIncrease;
        }        
        
        public int getXCoor()
        {
            return x_coor;
        }        
        
        public int getYCoor()
        {
            return y_coor;
        }   
        
        public void setXCoor(int x)
        {
            x_coor = x;
        }        
        
        public void setYCoor(int y)
        {
            y_coor = y;
        }
        
        public void increaseXCoor(int x)
        {
            x_coor += x;
        }
        
        public void increaseYCoor(int y)
        {
            y_coor += y;
        }
        
        public int getCenterXCoor()
        {
            return center_x_coor;
        }
        
        public int getCenterYCoor()
        {
            return center_y_coor;
        }
        
        public void setCenterXCoor()
        {
            center_x_coor = (int)(x_coor + radius);
        }

        public void setCenterYCoor()
        {
            center_y_coor = (int)(y_coor + radius);
        }        
        
        public double getRadiusOfBlob()
        {
            return radius;
        }        
        
        public double getDistanceFromMouse()
        {
            return distanceFromMouse;
        }    
        
        public void setDistanceFromMouse(int x_mouse, int y_mouse)
        {
            distanceFromMouse =
            Math.sqrt(Math.abs(Math.abs(Math.pow(((x_mouse) - (getBlobsArrayList().get(0).center_x_coor)), 2)) + 
                       Math.abs(Math.pow(((y_mouse) - (getBlobsArrayList().get(0).center_y_coor)), 2))) 
                     );
            
            mouse_x_coor = x_mouse;
            mouse_y_coor = y_mouse;
        }
    }        
    
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    
    protected class Dot 
    {       
        private Random xrng = new Random();
        private Random yrng = new Random();
    
        private int x_coor;
        private int y_coor;
        
        private int center_x_coor;
        private int center_y_coor;
        
        private int width;
        private int height;
        private double radius;
        
        private int currentColor;
        
        private double distanceFromBlobCenter;
    
        public Dot()
        {
            x_coor = xrng.nextInt(2500);
            y_coor = yrng.nextInt(2500);    
            
            width = 12;
            height = 12;
            center_x_coor = (int)(x_coor + radius);
            center_y_coor = (int)(y_coor + radius);
            radius = width/2;
            setDistanceFromBlobCenter();
            
            currentColor = dotColorRng.nextInt(8);
        }        
        
        public int getXCoor()
        {
            return x_coor;
        }        
        
        public int getYCoor()
        {
            return y_coor;
        }        
        
        public void setXCoor(int x)
        {
            x_coor = x;
        }        
        
        public void setYCoor(int y)
        {
            y_coor = y;
        }
        
        public void increaseXCoor(int x)
        {
            x_coor += x;
        }
        
        public void increaseYCoor(int y)
        {
            y_coor += y;
        }
        
        public int getCenterXCoor()
        {
            return center_x_coor;
        }
        
        public int getCenterYCoor()
        {
            return center_y_coor;
        }
        
        public void setCenterXCoor()
        {
            center_x_coor = (int)(x_coor + radius);
        }

        public void setCenterYCoor()
        {
            center_y_coor = (int)(y_coor + radius);
        }        
        
        public void setDistanceFromBlobCenter()
        {
            distanceFromBlobCenter =
            Math.sqrt(Math.abs(Math.abs(Math.pow(((this.center_x_coor) - (getBlobsArrayList().get(0).center_x_coor)), 2)) + 
                       Math.abs(Math.pow(((this.center_y_coor) - (getBlobsArrayList().get(0).center_y_coor)), 2))) 
                     );    
        }   
        
        public double getDistanceFromBlobCenter()
        {
            return distanceFromBlobCenter;
        }                
    }        
    
    protected class Grid 
    {
        // horizontal line
        private int start_x_coor_horizontal = 0;
        private int start_y_coor_horizontal;        
        private int end_x_coor_horizontal = 1000;        
        private int end_y_coor_horizontal;        
        
        // vertical line
        private int start_x_coor_vertical;
        private int start_y_coor_vertical = 0;        
        private int end_x_coor_vertical;        
        private int end_y_coor_vertical = 1000;
        
        public Grid(int firstValue, int increase)
        {
            // horizontal line variables
            start_y_coor_horizontal = (firstValue + increase);
            end_y_coor_horizontal = (firstValue + increase);
            
            // vertical line variables
            start_x_coor_vertical = (firstValue + increase);
            end_x_coor_vertical = (firstValue + increase);
        }    
        
        public void increaseStartXCoorHorizontal(int x)
        {
            start_x_coor_horizontal += x;
        } 
        public void increaseStartYCoorHorizontal(int y)
        {
            start_y_coor_horizontal += y;
        }
        public void increaseEndXCoorHorizontal(int x)
        {
            end_x_coor_horizontal += x;
        }public void increaseEndYCoorHorizontal(int y)
        {
            end_y_coor_horizontal += y;
        }
        public void increaseStartXCoorVertical(int x)
        {
            start_x_coor_vertical += x;
        } 
        public void increaseStartYCoorVertical(int y)
        {
            start_y_coor_vertical += y;
        }
        public void increaseEndXCoorVertical(int x)
        {
            end_x_coor_vertical += x;
        }public void increaseEndYCoorVertical(int y)
        {
            end_y_coor_vertical += y;
        }
    }        
//#############################################################################    
    
    public ArrayList<Blob> getBlobsArrayList()
    {
        return blobs;
    }  
    
    public ArrayList<Dot> getDotsArrayList()
    {
        return dots;
    }
    
    public ArrayList<Grid> getGridsArrayList()
    {
        return grids;
    }        
    
    public void createDot()
    {
        dots.add(new Dot());
        numberOfDots++;
    }        
    
    public void removeDot(int index)
    {
        dots.remove(index);
        numberOfDots--;
    }
    
    public int getNumberOfDots()
    {
        return numberOfDots;
    }      
    
    public void increaseFrameXSize(int x)
    {
        frame_x_size += x;
    }
      
    public void increaseFrameYSize(int y)
    {
        frame_y_size += y;
    }
}