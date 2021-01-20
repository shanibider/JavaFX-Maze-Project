package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    //Build the maze manually
    int [][] mazeData={
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1},
            {1,0,1,1,1,1,0,1,1,1,1,1,0,1,0,1},
            {1,0,1,1,1,1,0,0,0,0,1,0,0,1,1,1},
            {1,0,0,0,0,1,1,1,1,1,1,0,1,1,1,1},
            {1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,0,0,0,0,1,1,1,1,1,0,1},
            {1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1},
    };


    @FXML
    MazeDisplayer mazeDisplayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mazeDisplayer.setMazeData(mazeData);

        //request for the focus to be on the maze
        mazeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->mazeDisplayer.requestFocus() );

        //Typing on the keyboard and the character will move [setOnKeyPressed need to get an object that implements EventHandler (from <KeyEvent> type, therefore we need to override func handle]
        mazeDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
               int r= mazeDisplayer.getcRow();                        //dataMember that represent row
               int c=mazeDisplayer.getcCol();                         //dataMember that represent column

               if (keyEvent.getCode()== KeyCode.UP){
                   mazeDisplayer.setCharacterPosition(r-1,c);
               }
                if (keyEvent.getCode()== KeyCode.DOWN){
                    mazeDisplayer.setCharacterPosition(r+1,c);
                }
                if (keyEvent.getCode()== KeyCode.RIGHT){
                    mazeDisplayer.setCharacterPosition(r,c+1);
                }
                if (keyEvent.getCode()== KeyCode.LEFT){
                    mazeDisplayer.setCharacterPosition(r,c-1);
                }
            }
        });

    }



    public void start(){
    System.out.println ("start");
    }

    public void openFile(){
        FileChooser fc= new FileChooser();
        fc.setTitle("open maze file");
        fc.setInitialDirectory(new File("./resources"));
        File chosen= fc.showOpenDialog(null);
        if (chosen!=null) {
            System.out.println(chosen.getName());
        }
    }

}
