package darrotech.project.b2fly.controllers;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dnd.DragSource;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("drag-and-drop")
public class DragAndDropView extends VerticalLayout {

    public DragAndDropView() {
        Div draggable = new Div();
        draggable.setText("Drag me!");
        draggable.getStyle().set("padding", "10px");
        draggable.getStyle().set("border", "1px solid black");

        // Make the div draggable
        DragSource<Div> dragSource = DragSource.create(draggable);

        Div dropTarget = new Div();
        dropTarget.setText("Drop here!");
        dropTarget.getStyle().set("padding", "50px");
        dropTarget.getStyle().set("border", "1px solid black");
        dropTarget.getStyle().set("margin-top", "20px");

        // Make the div a drop target
        DropTarget<Div> dropTargetComponent = DropTarget.create(dropTarget);
        dropTargetComponent.addDropListener(event -> {
            dropTarget.setText("Dropped: " + event.getDragSourceComponent().get());
        });

        add(draggable, dropTarget);
    }
}
