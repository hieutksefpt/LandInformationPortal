package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.dto.Coordinate;
import capstone.lip.landinformationportal.dto.LazyListAllRealEstate;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.service.Interface.IHouseService;
import capstone.lip.landinformationportal.service.Interface.IHousesDetailService;
import capstone.lip.landinformationportal.service.Interface.ILandService;
import capstone.lip.landinformationportal.service.Interface.ILandsDetailService;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author AnhHao
 */
@Named
@ViewScoped
public class ListAllRealEstateBean implements Serializable {

    @Autowired
    private IRealEstateService realEstateService;

    @Autowired
    private ILandService landService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private ILandsDetailService landsDetailService;

    @Autowired
    private IHousesDetailService housesDetailService;

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
        this.listAllRealEstate = realEstateService.findAll();
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
            listAllRealEstate.remove(findRealEstateIndexInList(listSelectedRealEstate.get(i).getRealEstateId()));
        }
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
