package org.byfc.controllers;

import org.byfc.entities.Learningoutcome;
import org.byfc.controllers.util.JsfUtil;
import org.byfc.controllers.util.PaginationHelper;
import org.byfc.facades.LearningoutcomeFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.inject.Inject;
import org.byfc.entities.Courseoutline;

@Named("learningoutcomeController")
@SessionScoped
public class LearningoutcomeController implements Serializable {

    private Learningoutcome current;
    private DataModel items = null;
    @EJB
    private org.byfc.facades.LearningoutcomeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    /**
     * *******************************
     */
    private DataModel list;
    @Inject CourseoutlineController courseoutlineController;
    @EJB
    private org.byfc.facades.CourseoutlineFacade courseoutlineFacade;
    public void myUpdate() {
        current = (Learningoutcome) getItems().getRowData();
        items=null;
        update();
    }
    public void myCreate() {
        current = new Learningoutcome();
        current.setLearningoutcomePK(new org.byfc.entities.LearningoutcomePK());
        Courseoutline co = courseoutlineController.getSelected();
        Courseoutline courseoutline = courseoutlineController.getCourseoutline(co.getId());
        current.setCourseoutline(courseoutline);
        selectedItemIndex = -1;
        try {
            current.getLearningoutcomePK().setCourseoutlineId(current.getCourseoutline().getId());
            getFacade().create(current);
            List<Learningoutcome> lol = courseoutline.getLearningoutcomeList();
            if(lol==null)
                lol=new ArrayList<>();
            lol.add(current);
            courseoutlineFacade.edit(courseoutline);
            items=null;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LearningoutcomeCreated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    public DataModel getItemsByCourseoutline() {
        Courseoutline courseoutline = courseoutlineController.getSelected();
        list = new ListDataModel(getFacade().getItemsByCourseoutline(courseoutline));
        return list;
    }

    /**
     * *******************************
     */

    public LearningoutcomeController() {
    }

    public Learningoutcome getSelected() {
        if (current == null) {
            current = new Learningoutcome();
            current.setLearningoutcomePK(new org.byfc.entities.LearningoutcomePK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private LearningoutcomeFacade getFacade() {
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
//                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    return new ListDataModel(getFacade().getItemsByCourseoutline(courseoutlineController.getSelected()));
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
        current = (Learningoutcome) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Learningoutcome();
        current.setLearningoutcomePK(new org.byfc.entities.LearningoutcomePK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getLearningoutcomePK().setCourseoutlineId(current.getCourseoutline().getId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LearningoutcomeCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Learningoutcome) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getLearningoutcomePK().setCourseoutlineId(current.getCourseoutline().getId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LearningoutcomeUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Learningoutcome) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LearningoutcomeDeleted"));
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

    public Learningoutcome getLearningoutcome(org.byfc.entities.LearningoutcomePK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Learningoutcome.class)
    public static class LearningoutcomeControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LearningoutcomeController controller = (LearningoutcomeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "learningoutcomeController");
            return controller.getLearningoutcome(getKey(value));
        }

        org.byfc.entities.LearningoutcomePK getKey(String value) {
            org.byfc.entities.LearningoutcomePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new org.byfc.entities.LearningoutcomePK();
            key.setId(Integer.parseInt(values[0]));
            key.setCourseoutlineId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(org.byfc.entities.LearningoutcomePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getId());
            sb.append(SEPARATOR);
            sb.append(value.getCourseoutlineId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Learningoutcome) {
                Learningoutcome o = (Learningoutcome) object;
                return getStringKey(o.getLearningoutcomePK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Learningoutcome.class.getName());
            }
        }

    }

}
