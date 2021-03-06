package steuerungslogik;

import datenlogik.Inventar;
import steuerungslogik.util.JsfUtil;
import steuerungslogik.util.PaginationHelper;
import geschaeftslogik.InventarFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import java.util.List;
import java.util.Iterator;

@Named("inventarController")
@SessionScoped
public class InventarController implements Serializable {

    private Inventar current;
    private DataModel items = null;
    @EJB
    private geschaeftslogik.InventarFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public InventarController() {
    }

    public Inventar getSelected() {
        if (current == null) {
            current = new Inventar();
            selectedItemIndex = -1;
        }
        return current;
    }

    private InventarFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Inventar) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Inventar();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("ressources/Bundle").getString("InventarCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("ressources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Inventar) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("ressources/Bundle").getString("InventarUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("ressources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Inventar) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("ressources/Bundle").getString("InventarDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("ressources/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Inventar getInventar(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    public String prepareInventoryByStation() {
        current = (Inventar) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Stationsbestand";
    }
    
    public List<Inventar> getInventoryByStation(java.lang.Integer standortNr){
        return ejbFacade.inventoryByStation(standortNr);
    }
    
    public List<Inventar> getInventoryByProductOrder(java.lang.Integer artikelNr){
       return ejbFacade.getItemOrderBy("artikelNr", artikelNr, "standortNr");
    }
    
    public String prepareInventoryByProduct(){
       current = (Inventar) getItems().getRowData();
       selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
       return "Produktbestand";
    }
    
    public String prepareInventoryByOneProductAmount(){
       current = (Inventar) getItems().getRowData();
       return "Gesamtlagerbestand_Produkt";
    }
    
    public Integer getSelectedProductAmount(java.lang.Integer productID){
       List<Inventar> inventoryList = ejbFacade.inventoryByProduct(productID);
       
       int amount = 0;
       Iterator<Inventar> iterator = inventoryList.iterator();
       while(iterator.hasNext())
       {
          Inventar inv = (Inventar) iterator.next();
          amount += inv.getStueckzahlIST();
       }
       return amount;
    }
    
    public String prepareInventoryByAllProductAmount(){
       return "Gesamtlagerbestand_Alle";
    }

    @FacesConverter(forClass = Inventar.class)
    public static class InventarControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InventarController controller = (InventarController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "inventarController");
            return controller.getInventar(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Inventar) {
                Inventar o = (Inventar) object;
                return getStringKey(o.getInventarNr());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Inventar.class.getName());
            }
        }
    }
}