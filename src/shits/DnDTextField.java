package shits;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class DnDTextField extends JTextField implements Transferable, DragSourceListener, DragGestureListener{

	//marks this JTextArea as the source of the Drag
    private DragSource source;

    private TransferHandler t;

    public DnDTextField(){
        this("");
    }

    public DnDTextField(String message){
        super(message);

        //The TransferHandler returns a new DnDTextArea
        //to be transferred in the Drag
        t = new TransferHandler(){

              public Transferable createTransferable(JComponent c){
                    return new DnDTextField(getText());
              }
        };
        setTransferHandler(t);

        //The Drag will copy the DnDTextArea rather than moving it
        source = new DragSource();
        source.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this);
    }

     //The DataFlavor is a marker to let the DropTarget know how to
     //handle the Transferable
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{new DataFlavor(DnDTextField.class, "JTextArea")};
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return true;
    }

    public Object getTransferData(DataFlavor flavor) {
        return this;
    }


    public void dragEnter(DragSourceDragEvent dsde) {}
    public void dragOver(DragSourceDragEvent dsde) {}
    public void dropActionchanged(DragSourceDragEvent dsde) {}
    public void dragExit(DragSourceEvent dse) {}

    //when the drag finishes, then repaint the DnDTextArea
    //so it doesn't look like it has still been pressed down
    public void dragDropEnd(DragSourceDropEvent dsde) {
        repaint();
    }

    //when a DragGesture is recognized, initiate the Drag
    public void dragGestureRecognized(DragGestureEvent dge) {
         source.startDrag(dge, DragSource.DefaultMoveDrop, new DnDTextField("Text"), this);        
    }

	@Override
	public void dropActionChanged(DragSourceDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
