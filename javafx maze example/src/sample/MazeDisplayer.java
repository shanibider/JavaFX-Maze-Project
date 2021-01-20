package sample;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MazeDisplayer extends Canvas {

    int[][] mazeData;       //matrix of the maze
    private StringProperty wallFileName;
    int cCol, cRow;


    //CTOR TO wallFileName,cCol, cRow
    public MazeDisplayer(){
        wallFileName= new SimpleStringProperty();
        cCol=0;
        cRow=1;
    }

    public void setMazeData(int[][] mazeData) {
        this.mazeData = mazeData;
        redraw();
    }
    public int [][] getMazeData (){
        return mazeData;
    }

    public String getWallFileName() {
        return wallFileName.get();
    }
    public void setWallFileName(String wallFileName) {
        this.wallFileName.set(wallFileName);
    }
    
    public int getcCol() {
        return cCol;
    }
    public int getcRow() {
        return cRow;
    }


    public void setCharacterPosition(int row, int col){
        cRow=row;
        cCol=col;
        redraw();
    }




    //draw the maze
    public void redraw(){
      if (mazeData!=null){
          //W=width of mazeDate, w=width of mazeDate[i][j]
          double W= getWidth();
          double H= getHeight();
          double w=W/mazeData[0].length;
          double h=H/mazeData.length;

          //create character (GraphicsContext)
         GraphicsContext gc= getGraphicsContext2D();

         //create picture that fill the maze walls
          Image wall=null;
          try {
              wall = new Image(new FileInputStream(wallFileName.get()));
          } catch (FileNotFoundException e) { e.printStackTrace(); }

          //clear the character
          gc.clearRect(0,0,W,H);

          //create the maze walls
          for (int i=0; i<mazeData.length; i++)
              for (int j = 0; j < mazeData[i].length; j++) {
                  if (mazeData[i][j] != 0) {
                      if (wall == null)
                          gc.fillRect(j * w, i * h, w, h);
                      else
                          gc.drawImage(wall, j * w, i * h, w, h);
                  }
              }
            //create the character
          gc.setFill(Color.RED);
              gc.fillOval(cCol*w,cRow*h,w,h);
      }
    }

}
