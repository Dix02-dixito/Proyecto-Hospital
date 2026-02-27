package recursos;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.stream.Collectors;

public class ComboAutoComplete {

    public static void setup(JComboBox<String> combo, List<String> items ) {
    	combo.setEditable(true);
    	
    	JTextComponent editor = (JTextComponent) combo.getEditor().getEditorComponent();
    	final boolean[] adjusting = {false};
    	
    	//BASE DEL MODELO
    	DefaultComboBoxModel<String> base = new DefaultComboBoxModel<String>();
    	items.forEach(base::addElement);
    	combo.setModel(base);
    	
    	editor.getDocument().addDocumentListener(new DocumentListener() {
    		private void update() {
    			if (adjusting[0]) return;
    			adjusting[0] = true;
    			
    			String text = editor.getText().trim().toLowerCase();
    			
    			List<String> filtered = items.stream()
    					.filter(it -> it.toLowerCase().contains(text)).collect(Collectors.toList());
    			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    			filtered.forEach(model::addElement);
    			
    			combo.setModel(model);
    			combo.setEditable(true);
    			combo.getEditor().setItem(editor.getText());
    			
    			combo.setPopupVisible(!filtered.isEmpty() && !text.isEmpty());
    			adjusting[0] = false;
    		}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				update();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				update();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				update();
			}
		});
    	
    	
    }
}