package com.sojson.permission.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.core.mybatis.page.Pagination;
import com.sojson.permission.bo.URoleBo;
import com.sojson.permission.bo.UserRoleAllocationBo;
import com.sojson.permission.service.PermissionService;
import com.sojson.user.service.UUserService;

/**
 * <p>
 * <p>
 * 用户属性分配
 * <p>
 * <p>
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("role")
public class UserRoleAllocationController extends BaseController {
    @Autowired
    UUserService userService;
    @Autowired
    PermissionService permissionService;

    /**
     * 用户属性权限分配
     *
     * @param modelMap
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value = "allocation")
    public ModelAndView allocation(ModelMap modelMap, Integer pageNo, String findContent) {
        modelMap.put("findContent", findContent);
        Pagination<UserRoleAllocationBo> boPage = userService.findUserAndRole(modelMap, pageNo, pageSize);
        modelMap.put("page", boPage);
        return new ModelAndView("role/allocation");
    }

    /**
     * 根据用户ID查询权限
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectRoleByUserId")
    @ResponseBody
    public List<URoleBo> selectRoleByUserId(Long id) {
        List<URoleBo> bos = userService.selectRoleByUserId(id);
        return bos;
    }

    /**
     * 操作用户的属性
     *
     * @param userId 用户ID
     * @param ids    属性ID，以‘,’间隔
     * @return
     */
    @RequestMapping(value = "addRole2User")
    @ResponseBody
    public Map<String, Object> addRole2User(Long userId, String ids) {
        return userService.addRole2User(userId, ids);
    }

    /**
     * 根据用户id清空属性。
     *
     * @param userIds 用户ID ，以‘,’间隔
     * @return
     */
    @RequestMapping(value = "clearRoleByUserIds")
    @ResponseBody
    public Map<String, Object> clearRoleByUserIds(String userIds) {
        return userService.deleteRoleByUserIds(userIds);
    }
}
