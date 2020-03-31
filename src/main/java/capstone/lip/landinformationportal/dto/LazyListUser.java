/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.dto;

import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import capstone.lip.landinformationportal.service.UserService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author AnhHao
 */
public class LazyListUser extends LazyDataModel<User> implements Serializable {

    @Autowired
    private IUserService userService;

    public LazyListUser(IUserService service) {
        this.userService = service;
        this.setRowCount((int) userService.count());
    }

    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        Page<User> list
                = userService.findAll(PageRequest.of(first / pageSize, pageSize));
        List<User> list1 = list.stream().map(x -> x).collect(Collectors.toList());
        return list1;
    }

    @Override
    public User getRowData(String rowKey) {
        return userService.findById(Long.parseLong(rowKey));
    }

    @Override
    public Object getRowKey(User user) {
        return user.getUserId();
    }
}
