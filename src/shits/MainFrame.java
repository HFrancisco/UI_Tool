package shits;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;
import javax.swing.UIManager;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;


public class MainFrame implements DropTargetListener {

	public int x, y;
	protected JPanel pane = new JPanel();

	protected DropTarget dropTarget;

	protected boolean acceptableType; // Indicates whether data is acceptable

	protected DataFlavor targetFlavor; // Flavor to use for transfer
	
	private  Object selected;
	private String typeSelected;
	private JLabel lblNewLabel; 
	ArrayList<Component> listComponents = new ArrayList<Component>();
	ArrayList<DnDButton> listButtons = new ArrayList<DnDButton>();
	ArrayList<DnDLabel> listLabels = new ArrayList<DnDLabel>();
	ArrayList<DnDTextField> listTxtFields = new ArrayList<DnDTextField>();
	private JTextField Edit;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		try {
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } catch (Exception evt) {}
	  
	  JPanel panel2 = new JPanel();
	  MainFrame panel = new MainFrame(panel2);
	  panel2.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MainFrame(JPanel pane) {
		
		selected = null;
		typeSelected = "";
		
		this.pane = pane;
	    pane.setLayout(null);
	    
	    // Create the DropTarget and register
	    // it with the JPanel.
	    dropTarget = new DropTarget(pane, DnDConstants.ACTION_COPY_OR_MOVE,
	        this, true, null);
		
		final JFrame source = new JFrame("Shite");
		JPanel panelComponents = new JPanel();
		
		source.getContentPane().add(panelComponents);
		source.setSize(900, 450);
		
		/*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);*/
		
		/*JPanel panelOutput = new JPanel();
		panelOutput.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelOutput.setBounds(169, 0, 1005, 662);
		contentPane.add(panelOutput);
		//PanelDropTarget panelDropTgt = new PanelDropTarget(panelOutput);
		panelOutput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));*/
		
		
		DnDLabel lblComponents = new DnDLabel("New label");
		lblComponents.setBounds(56, 60, 46, 14);
		panelComponents.add(lblComponents);
		
		DnDButton btnComponents = new DnDButton("New button");
		btnComponents.setBounds(35, 104, 89, 23);
		panelComponents.add(btnComponents);
		
		DnDTextField txtComponents = new DnDTextField();
		txtComponents.setBounds(35, 170, 89, 52);
		panelComponents.add(txtComponents);
		
		JScrollPane panelOutput = new JScrollPane(pane);
		panelOutput.setBounds(169, 0, 700, 400);
		panelComponents.add(panelOutput);
		
		JButton btnSave = new JButton("Save Layout");
		btnSave.setBounds(35, 250, 89, 23);
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				try {
					DocumentBuilderFactory doFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = doFactory.newDocumentBuilder();
					
					Document doc  = docBuilder.newDocument();
					

					Element file = doc.createElement("File");
					doc.appendChild(file);
					
					System.out.println(listButtons.size());
					
					for(int i = 0; listButtons.size() > i; i++) {
						Element rootElement = doc.createElement("Component");
						file.appendChild(rootElement);
						
						Element type = doc.createElement("Type");
						type.appendChild(doc.createTextNode("Button"));
						rootElement.appendChild(type);
						
						Element name = doc.createElement("Text");
						name.appendChild(doc.createTextNode(listButtons.get(i).getText()));
						rootElement.appendChild(name);
						
						Element xValue = doc.createElement("X");
						xValue.appendChild(doc.createTextNode(Integer.toString(listButtons.get(i).getX())));
						rootElement.appendChild(xValue);
						
						Element yValue = doc.createElement("Y");
						yValue.appendChild(doc.createTextNode(Integer.toString(listButtons.get(i).getY())));
						rootElement.appendChild(yValue);
						
						Element widthValue = doc.createElement("Width");
						widthValue.appendChild(doc.createTextNode(Integer.toString(listButtons.get(i).getWidth())));
						rootElement.appendChild(widthValue);
						
						Element heightValue = doc.createElement("Height");
						heightValue.appendChild(doc.createTextNode(Integer.toString(listButtons.get(i).getHeight())));
						rootElement.appendChild(heightValue);
						
					}
					
					for(int i = 0; listLabels.size() > i; i++) {
						Element rootElement = doc.createElement("Component");
						file.appendChild(rootElement);
						
						Element type = doc.createElement("Type");
						type.appendChild(doc.createTextNode("Label"));
						rootElement.appendChild(type);
						
						Element name = doc.createElement("Text");
						name.appendChild(doc.createTextNode(listLabels.get(i).getText()));
						rootElement.appendChild(name);
						
						Element xValue = doc.createElement("X");
						xValue.appendChild(doc.createTextNode(Integer.toString(listLabels.get(i).getX())));
						rootElement.appendChild(xValue);
						
						Element yValue = doc.createElement("Y");
						yValue.appendChild(doc.createTextNode(Integer.toString(listLabels.get(i).getY())));
						rootElement.appendChild(yValue);
						
						Element widthValue = doc.createElement("Width");
						widthValue.appendChild(doc.createTextNode(Integer.toString(listLabels.get(i).getWidth())));
						rootElement.appendChild(widthValue);
						
						Element heightValue = doc.createElement("Height");
						heightValue.appendChild(doc.createTextNode(Integer.toString(listLabels.get(i).getHeight())));
						rootElement.appendChild(heightValue);
						
					}
					
					for(int i = 0; listTxtFields.size() > i; i++) {
						Element rootElement = doc.createElement("Component");
						file.appendChild(rootElement);
						
						Element type = doc.createElement("Type");
						type.appendChild(doc.createTextNode("TextField"));
						rootElement.appendChild(type);
						
						Element name = doc.createElement("Text");
						name.appendChild(doc.createTextNode(listTxtFields.get(i).getText()));
						rootElement.appendChild(name);
						
						Element xValue = doc.createElement("X");
						xValue.appendChild(doc.createTextNode(Integer.toString(listTxtFields.get(i).getX())));
						rootElement.appendChild(xValue);
						
						Element yValue = doc.createElement("Y");
						yValue.appendChild(doc.createTextNode(Integer.toString(listTxtFields.get(i).getY())));
						rootElement.appendChild(yValue);
						
						Element widthValue = doc.createElement("Width");
						widthValue.appendChild(doc.createTextNode(Integer.toString(listTxtFields.get(i).getWidth())));
						rootElement.appendChild(widthValue);
						
						Element heightValue = doc.createElement("Height");
						heightValue.appendChild(doc.createTextNode(Integer.toString(listTxtFields.get(i).getHeight())));
						rootElement.appendChild(heightValue);
						
					}
					
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File("C:\\Users\\Harry\\Desktop\\eclipse-workspace\\CMPILER_UITool\\src\\file.xml"));
					
					
					transformer.transform(source, result);
					

					
				} catch(ParserConfigurationException pce) {
					pce.printStackTrace();
				} catch (TransformerException tfe) {
					tfe.printStackTrace();
				}
				
			}
		});
		panelComponents.add(btnSave);

		panelComponents.setLayout(null);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(35, 313, 89, 23);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(selected != null) {
					System.out.println("fasdfadfasdfa");
					if(((Component) selected).getClass().equals("DnDButton")) {
						System.out.println("puta");
  				  		listButtons.remove(((Component) selected));
  			  		}
  			  		else if(((Component) selected).getClass().equals("DnDLabel")) {
  				  		listLabels.remove(((Component) selected));
  			  		}
  			  		else if(((Component) selected).getClass().equals("DnDTextField")) {
  				  		listTxtFields.remove(((Component) selected));
  			  		}
  			  
  			  		pane.remove(((Component) selected));
  			  		pane.repaint();
  			  		pane.revalidate();
  			  		
  			  		selected = null;
  			  
				} else {
					System.out.println("Null value");
				}
				
			}
		});
		panelComponents.add(btnDelete);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(56, 300, 46, 14);
		panelComponents.add(lblNewLabel);
		
		Edit = new JTextField();
		Edit.setBounds(38, 347, 86, 20);
		panelComponents.add(Edit);
		Edit.setColumns(10);
		
		JButton setText = new JButton("Set text");
		setText.setBounds(35, 377, 89, 23);
		setText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(selected != null) {
					
					
					System.out.println(typeSelected);
					
					if(typeSelected.matches("DnDButton")) {
						((DnDButton) selected).setText(Edit.getText());
					}
					else if(typeSelected.matches("DnDLabel")) {
						((DnDLabel) selected).setText(Edit.getText());
					}
					else if(typeSelected.matches("DnDTextField")) {
						((DnDTextField) selected).setText(Edit.getText());
					}
					
					selected = null;
					
					pane.repaint();
					pane.revalidate();
				}
				
			}
		});
		panelComponents.add(setText);
		source.setVisible(true);
	}
	
	// Implementation of the DropTargetListener interface
	  public void dragEnter(DropTargetDragEvent dtde) {
	    DnDUtils.debugPrintln("dragEnter, drop action = "
	        + DnDUtils.showActions(dtde.getDropAction()));

	    // Get the type of object being transferred and determine
	    // whether it is appropriate.
	    checkTransferType(dtde);

	    // Accept or reject the drag.
	    acceptOrRejectDrag(dtde);
	  }

	  public void dragExit(DropTargetEvent dte) {
	    DnDUtils.debugPrintln("DropTarget dragExit");
	  }

	  public void dragOver(DropTargetDragEvent dtde) {
	    DnDUtils.debugPrintln("DropTarget dragOver, drop action = "
	        + DnDUtils.showActions(dtde.getDropAction()));

	    // Accept or reject the drag
	    acceptOrRejectDrag(dtde);
	  }

	  public void dropActionChanged(DropTargetDragEvent dtde) {
	    DnDUtils.debugPrintln("DropTarget dropActionChanged, drop action = "
	        + DnDUtils.showActions(dtde.getDropAction()));

	    // Accept or reject the drag
	    acceptOrRejectDrag(dtde);
	  }

	  public void drop(DropTargetDropEvent dtde) {
	    DnDUtils.debugPrintln("DropTarget drop, drop action = "
	        + DnDUtils.showActions(dtde.getDropAction()));

	    // Check the drop action
	    if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
	      // Accept the drop and get the transfer data
	      dtde.acceptDrop(dtde.getDropAction());
	      Transferable transferable = dtde.getTransferable();

	      try {
	    	  
	    	  x = dtde.getLocation().x;
	    	  y = dtde.getLocation().y;
	        boolean result = dropComponent(transferable);

	        dtde.dropComplete(result);
	        DnDUtils.debugPrintln("Drop completed, success: " + result);
	    	  
	      } catch (Exception e) {
	        DnDUtils.debugPrintln("Exception while handling drop " + e);
	        dtde.dropComplete(false);
	      }
	    } else {
	      DnDUtils.debugPrintln("Drop target rejected drop");
	      dtde.rejectDrop();
	    }
	  }

	  // Internal methods start here

	  protected boolean acceptOrRejectDrag(DropTargetDragEvent dtde) {
	    int dropAction = dtde.getDropAction();
	    int sourceActions = dtde.getSourceActions();
	    boolean acceptedDrag = false;

	    DnDUtils.debugPrintln("\tSource actions are "
	        + DnDUtils.showActions(sourceActions) + ", drop action is "
	        + DnDUtils.showActions(dropAction));

	    // Reject if the object being transferred
	    // or the operations available are not acceptable.
	    if (!acceptableType
	        || (sourceActions & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
	      DnDUtils.debugPrintln("Drop target rejecting drag");
	      dtde.rejectDrag();
	    } else if ((dropAction & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
	      // Not offering copy or move - suggest a copy
	      DnDUtils.debugPrintln("Drop target offering COPY");
	      dtde.acceptDrag(DnDConstants.ACTION_COPY);
	      acceptedDrag = true;
	    } else {
	      // Offering an acceptable operation: accept
	      DnDUtils.debugPrintln("Drop target accepting drag");
	      dtde.acceptDrag(dropAction);
	      acceptedDrag = true;
	    }

	    return acceptedDrag;
	  }

	  protected void checkTransferType(DropTargetDragEvent dtde) {
	    // Only accept a flavor that returns a Component
	    acceptableType = false;
	    DataFlavor[] fl = dtde.getCurrentDataFlavors();
	    for (int i = 0; i < fl.length; i++) {
	      Class dataClass = fl[i].getRepresentationClass();

	      if (Component.class.isAssignableFrom(dataClass)) {
	        // This flavor returns a Component - accept it.
	        targetFlavor = fl[i];
	        acceptableType = true;
	        break;
	      }
	    }

	    DnDUtils.debugPrintln("File type acceptable - " + acceptableType);
	  }

	  protected boolean dropComponent(Transferable transferable)
	      throws IOException, UnsupportedFlavorException {
	    Object o = transferable.getTransferData(targetFlavor);
	    if (o instanceof Component) {
	      DnDUtils.debugPrintln("Dragged component class is "
	          + o.getClass().getName());
	      
	      ((Component) o).setDropTarget(null);
	      ((Component) o).setBounds(x, y, 150, 40);
	      	  
	      System.out.println(o.getClass().getName());
	      
	      if(((Component) o ).getClass().getName().equals("shits.DnDButton")) {
	    	  System.out.println("Btn");
	    	  listButtons.add((DnDButton) o);
	      }
	      else if(((Component) o ).getClass().getName().equals("shits.DnDLabel")) {
	    	  System.out.println("Lbl");
	    	  listLabels.add((DnDLabel) o);
	      }
	      else if(((Component) o ).getClass().getName().equals("shits.DnDTextField")) {
	    	  System.out.println("TxtFld");
	    	  listTxtFields.add((DnDTextField) o);
	      }
	      
	      ((Component) o).addMouseListener(new MouseListener() {
	    	  
	    	  @Override
	    	  public void mousePressed(MouseEvent e) {
	    		  
	    	  }
	    	  
	    	  @Override
	    	  public void mouseReleased(MouseEvent e) {
	    		  
	    	  }
	    	  
	    	  @Override
	    	  public void mouseClicked(MouseEvent e) {
	    		  /*if(SwingUtilities.isRightMouseButton(e) == true) {
	    			  if(((Component) o).getClass().equals("DnDButton")) {
	    				  listButtons.remove(((Component) o));
	    			  }
	    			  else if(((Component) o).getClass().equals("DnDLabel")) {
	    				  listLabels.remove(((Component) o));
	    			  }
	    			  else if(((Component) o).getClass().equals("DnDTextField")) {
	    				  listTxtFields.remove(((Component) o));
	    			  }
	    			  
	    			  pane.remove(((Component) o));
	    			  pane.repaint();
	    			  pane.revalidate();
	    		  }*/
	    		  
	    		  selected = o;
	    		  
	    		  
	    		  if(((Component) o ).getClass().getName().equals("shits.DnDButton")) {
	    			  typeSelected = "DnDButton";
	    	      }
	    	      else if(((Component) o ).getClass().getName().equals("shits.DnDLabel")) {
	    	    	  typeSelected = "DnDLabel";
	    	      }
	    	      else if(((Component) o ).getClass().getName().equals("shits.DnDTextField")) {
	    	    	  typeSelected = "DnDTextField";
	    	      }
	    		  
	    		  System.out.println(selected);
	    		  
	    		  
	    	  }
	    	  
	    	  @Override
	    	  public void mouseEntered(MouseEvent e) {
	    		  
	    	  }
	    	  
	    	  @Override
	    	  public void mouseExited(MouseEvent e) {
	    		  
	    	  }
	      });
	      
	      ((Component) o).addMouseMotionListener(new MouseAdapter() {
	    	  
	    	  @Override
	    	  public void mouseDragged(MouseEvent e) {
	    		  
	    		  pane.remove((Component) o);
	    		  if(((Component) o).getClass().getName().equals("DnDButton")) {
	    			  listButtons.remove(((Component) o));
	    		  }
	    		  else if(((Component) o).getClass().getName().equals("DnDLabel")) {
	    			  listLabels.remove(((Component) o));
	    		  }
	    		  else if(((Component) o).getClass().getName().equals("DnDTextField")) {
	    			  listTxtFields.remove(((Component) o));
	    		  }
	    		  
	    		  ((Component) o).setLocation(e.getX(), e.getY());
	    	  }
	      });
 	      
	      pane.add((Component) o);
	      pane.invalidate();
	      pane.revalidate();
	      pane.repaint();
	      pane.updateUI();
	      
	      return true;
	    }
	    return false;
	  }
	}

	class DnDUtils {
	  public static String showActions(int action) {
	    String actions = "";
	    if ((action & (DnDConstants.ACTION_LINK | DnDConstants.ACTION_COPY_OR_MOVE)) == 0) {
	      return "None";
	    }

	    if ((action & DnDConstants.ACTION_COPY) != 0) {
	      actions += "Copy ";
	    }

	    if ((action & DnDConstants.ACTION_MOVE) != 0) {
	      actions += "Move ";
	    }

	    if ((action & DnDConstants.ACTION_LINK) != 0) {
	      actions += "Link";
	    }

	    return actions;
	  }

	  public static boolean isDebugEnabled() {
	    return debugEnabled;
	  }

	  public static void debugPrintln(String s) {
	    if (debugEnabled) {
	      System.out.println(s);
	    }
	  }

	  private static boolean debugEnabled = (System
	      .getProperty("DnDExamples.debug") != null);
	
}
