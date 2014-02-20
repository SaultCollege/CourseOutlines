package org.byfc.controllers;

import org.byfc.entities.Elementofperformance;
import org.byfc.controllers.util.JsfUtil;
import org.byfc.controllers.util.PaginationHelper;
import org.byfc.facades.ElementofperformanceFacade;

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

@Named("elementofperformanceController")
@SessionScoped
public class ElementofperformanceController implements Serializable {

    private Elementofperformance current;
    private DataModel items = null;
    @EJB
    private org.byfc.facades.ElementofperformanceFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ElementofperformanceController() {
    }

    public Elementofperformance getSelected() {
        if (current == null) {
            current = new Elementofperformance();
            current.setElementofperformancePK(new org.byfc.entities.ElementofperformancePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ElementofperformanceFacade getFacade() {
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
        current = (Elementofperformance) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Elementofperformance();
        current.setElementofperformancePK(new org.byfc.entities.ElementofperformancePK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getElementofperformancePK().setLearningoutcomeCourseoutlineId(current.getLearningoutcome().getLearningoutcomePK().getCourseoutlineId());
            current.getElementofperformancePK().setLearningoutcomeId(current.getLearningoutcome().getLearningoutcomePK().getId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ElementofperformanceCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Elementofperformance) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getElementofperformancePK().setLearningoutcomeCourseoutlineId(current.getLearningoutcome().getLearningoutcomePK().getCourseoutlineId());
            current.getElementofperformancePK().setLearningoutcomeId(current.getLearningoutcome().getLearningoutcomePK().getId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ElementofperformanceUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Elementofperformance) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ElementofperformanceDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
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

    public Elementofperformance getElementofperformance(org.byfc.entities.ElementofperformancePK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Elementofperformance.class)
    public static class ElementofperformanceControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ElementofperformanceController controller = (ElementofperformanceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "elementofperformanceController");
            return controller.getElementofperformance(getKey(value));
        }

        org.byfc.entities.ElementofperformancePK getKey(String value) {
            org.byfc.entities.ElementofperformancePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new org.byfc.entities.ElementofperformancePK();
            key.setId(Integer.parseInt(values[0]));
            key.setLearningoutcomeId(Integer.parseInt(values[1]));
            key.setLearningoutcomeCourseoutlineId(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(org.byfc.entities.ElementofperformancePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getId());
            sb.append(SEPARATOR);
            sb.append(value.getLearningoutcomeId());
            sb.append(SEPARATOR);
            sb.append(value.getLearningoutcomeCourseoutlineId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Elementofperformance) {
                Elementofperformance o = (Elementofperformance) object;
                return getStringKey(o.getElementofperformancePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Elementofperformance.class.getName());
            }
        }

    }

}
