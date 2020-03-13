package capstone.lip.landinformationportal.bean;

import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author AnhHao
 */

@Named
@ViewScoped
public class ListOwnRealEstateBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
    private IRealEstateService realEstateService;
    
    @Autowired
    private IUserService userService;
    
    private List<RealEstate> listUserRealEstate;
    private RealEstate realEstateClicked;
    
    @PostConstruct
    public void init() {
        listUserRealEstate = new ArrayList<>();
        listUserRealEstate = userService.getListRealEstate(1L);
    }
    
    public void clickOnButtonRowRealEstate(String realEstateId){
        realEstateClicked = listUserRealEstate.stream().filter(x -> x.getRealEstateId().equals(Long.parseLong(realEstateId))).collect(Collectors.toList()).get(0);
    }

    public List<RealEstate> getListUserRealEstate() {
        return listUserRealEstate;
    }

    public void setListUserRealEstate(List<RealEstate> listUserRealEstate) {
        this.listUserRealEstate = listUserRealEstate;
    }

    public ListOwnRealEstateBean(RealEstate realEstateClicked) {
        this.realEstateClicked = realEstateClicked;
    }
    
}
