/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.bean;

import capstone.rep.realestateportal.entity.Route;
import capstone.rep.realestateportal.service.CommonService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author tuans
 */
@ManagedBean
@RequestScoped
public class DrawLandBean {

    private String routeName;
    private String areaNearName;

    @PostConstruct
    public void init() {

    }

    private String routeId;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public List<Route> listRouteByHint() {
        String hintLowerCase = (routeId + "").toLowerCase();
        CommonService commonService = new CommonService();
        List<Route> listRouteByHint = commonService.getRouteByHint(hintLowerCase);
        return listRouteByHint.stream().collect(Collectors.toList());
//        return listRouteByHint.stream().filter(t -> t.getName().toLowerCase().startsWith(routeHint)).collect(Collectors.toList());
    }

    private String json="worksdjkl";

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void changeRouteViewById() {
        int i = 1;
        i++;
        json = "{\n"
                + "  \"type\": \"FeatureCollection\",\n"
                + "  \"features\": [\n"
                + "    {\n"
                + "      \"type\": \"Feature\",\n"
                + "      \"properties\": {},\n"
                + "      \"geometry\": {\n"
                + "        \"type\": \"Polygon\",\n"
                + "        \"coordinates\": [\n"
                + "          [\n"
                + "            [\n"
                + "              105.5241537094116,\n"
                + "              21.011269908487428\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52434146404266,\n"
                + "              21.010869288954442\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52473843097685,\n"
                + "              21.0106389322358\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52521049976349,\n"
                + "              21.011004498167104\n"
                + "            ],\n"
                + "            [\n"
                + "              105.5249959230423,\n"
                + "              21.011385086404026\n"
                + "            ],\n"
                + "            [\n"
                + "              105.5244916677475,\n"
                + "              21.011535318335525\n"
                + "            ],\n"
                + "            [\n"
                + "              105.5241537094116,\n"
                + "              21.011269908487428\n"
                + "            ]\n"
                + "          ]\n"
                + "        ]\n"
                + "      }\n"
                + "    },\n"
                + "    {\n"
                + "      \"type\": \"Feature\",\n"
                + "      \"properties\": {},\n"
                + "      \"geometry\": {\n"
                + "        \"type\": \"Polygon\",\n"
                + "        \"coordinates\": [\n"
                + "          [\n"
                + "            [\n"
                + "              105.52500128746031,\n"
                + "              21.011745642785428\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52550554275513,\n"
                + "              21.011224838843674\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52606344223022,\n"
                + "              21.011655503771777\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52561819553375,\n"
                + "              21.012066136615044\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52500128746031,\n"
                + "              21.011745642785428\n"
                + "            ]\n"
                + "          ]\n"
                + "        ]\n"
                + "      }\n"
                + "    },\n"
                + "    {\n"
                + "      \"type\": \"Feature\",\n"
                + "      \"properties\": {},\n"
                + "      \"geometry\": {\n"
                + "        \"type\": \"Polygon\",\n"
                + "        \"coordinates\": [\n"
                + "          [\n"
                + "            [\n"
                + "              105.52478671073914,\n"
                + "              21.010563815837596\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52543044090271,\n"
                + "              21.010032992212228\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52532851696014,\n"
                + "              21.01095942844316\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52478671073914,\n"
                + "              21.010563815837596\n"
                + "            ]\n"
                + "          ]\n"
                + "        ]\n"
                + "      }\n"
                + "    },\n"
                + "    {\n"
                + "      \"type\": \"Feature\",\n"
                + "      \"properties\": {},\n"
                + "      \"geometry\": {\n"
                + "        \"type\": \"Polygon\",\n"
                + "        \"coordinates\": [\n"
                + "          [\n"
                + "            [\n"
                + "              105.52552163600922,\n"
                + "              21.01049871492854\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52670180797577,\n"
                + "              21.01049871492854\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52670180797577,\n"
                + "              21.011069598855414\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52552163600922,\n"
                + "              21.011069598855414\n"
                + "            ],\n"
                + "            [\n"
                + "              105.52552163600922,\n"
                + "              21.01049871492854\n"
                + "            ]\n"
                + "          ]\n"
                + "        ]\n"
                + "      }\n"
                + "    }\n"
                + "  ]\n"
                + "}";
        
    }

}
