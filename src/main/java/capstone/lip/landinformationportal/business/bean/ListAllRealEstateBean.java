package capstone.lip.landinformationportal.business.bean;

import capstone.lip.landinformationportal.business.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.common.dto.LazyListAllRealEstate;
import capstone.lip.landinformationportal.common.entity.RealEstate;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author AnhHao
 */
@Named
@ViewScoped
public class ListAllRealEstateBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    private IRealEstateService realEstateService;

    private LazyDataModel<RealEstate> lazyReo;
    private List<RealEstate> listAllRealEstate;
    private List<String> listRealEstateSource;
    private List<RealEstate> listSelectedRealEstate;

    public List<RealEstate> getListSelectedRealEstate() {
        return listSelectedRealEstate;
    }

    public void setListSelectedRealEstate(List<RealEstate> listSelectedRealEstate) {
        this.listSelectedRealEstate = listSelectedRealEstate;
    }

    @PostConstruct
    public void init() {
        this.listRealEstateSource = realEstateService.listRealEstateSource();
        this.lazyReo = new LazyListAllRealEstate(realEstateService);
    }

    public void changeRealEstateStatusToConfused() {
        for (int i = 0; i < listSelectedRealEstate.size(); i++) {
            listSelectedRealEstate.get(i).setRealEstateStatus("-1");
            realEstateService.save(listSelectedRealEstate.get(i));
        }
        this.lazyReo = new LazyListAllRealEstate(realEstateService);
    }

    public void changeRealEstateStatusToNotVerified() {
        for (int i = 0; i < listSelectedRealEstate.size(); i++) {
            listSelectedRealEstate.get(i).setRealEstateStatus("0");
            realEstateService.save(listSelectedRealEstate.get(i));
        }
        this.lazyReo = new LazyListAllRealEstate(realEstateService);
    }

    public void changeRealEstateStatusToVerified() {
        for (int i = 0; i < listSelectedRealEstate.size(); i++) {
            listSelectedRealEstate.get(i).setRealEstateStatus("1");
            realEstateService.save(listSelectedRealEstate.get(i));
        }
        this.lazyReo = new LazyListAllRealEstate(realEstateService);
    }

    public void deleteSelectedRealEstate() {
        for (int i = 0; i < listSelectedRealEstate.size(); i++) {
            realEstateService.delete(listSelectedRealEstate.get(i).getRealEstateId());
        }
        this.lazyReo = new LazyListAllRealEstate(realEstateService);
    }

    public int findRealEstateIndexInList(long realEstateId) {
        for (int i = 0; i < listAllRealEstate.size(); i++) {
            if (listAllRealEstate.get(i).getRealEstateId() == realEstateId) {
                return i;
            }
        }
        return -1;
    }

    public void goToDetails(long realEstateId) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/viewrealestatedetail.xhtml?realEstateId=" + realEstateId);
    }

    public List<RealEstate> getListAllRealEstate() {
        return listAllRealEstate;
    }

    public void setListAllRealEstate(List<RealEstate> listAllRealEstate) {
        this.listAllRealEstate = listAllRealEstate;
    }

    public List<String> getListRealEstateSource() {
        return listRealEstateSource;
    }

    public void setListRealEstateSource(List<String> listRealEstateSource) {
        this.listRealEstateSource = listRealEstateSource;
    }

    public LazyDataModel<RealEstate> getLazyReo() {
        return lazyReo;
    }

    public void setLazyReo(LazyDataModel<RealEstate> lazyReo) {
        this.lazyReo = lazyReo;
    }

}
