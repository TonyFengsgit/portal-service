package com.suitfit.portal.biz.impl;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.ListUtils;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.portal.base.service.MenuService;
import com.suitfit.portal.base.service.RoleMenuService;
import com.suitfit.portal.base.service.UserRoleService;
import com.suitfit.portal.base.service.utils.SecurityFactory;
import com.suitfit.portal.biz.MenuBiz;
import com.suitfit.portal.model.entity.Menu;
import com.suitfit.portal.model.entity.Role;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.criteria.QueryCriteria;
import com.suitfit.portal.model.pojo.dto.MenuDTO;
import com.suitfit.portal.model.pojo.vo.req.MenuReq;
import com.suitfit.portal.model.pojo.vo.resp.MenuMetaVO;
import com.suitfit.portal.model.pojo.vo.resp.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuBizImpl implements MenuBiz {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SecurityFactory securityFactory;

    @Override
    public List<MenuVO> init() {
        Long userId = securityFactory.getUserId();
        List<Role> roles = userRoleService.findByUserId(userId);
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<Menu> menuList = roleMenuService.findByRoleIds(roleIds);
        List<MenuDTO> voList = (List<MenuDTO>) BeanUtils.convert(menuList, MenuDTO.class);
        List<MenuDTO> trees = buildTree(voList);
        List<MenuVO> menus = buildMenu(trees);
        return menus;
    }

    @Override
    public List<Map<String, Object>> getMenuTree(List<Menu> menus) {
        if (ListUtils.isNullOrEmpty(menus)) {
            menus = menuService.findByPid(0L);
        }
        List<Map<String, Object>> list = new LinkedList<>();
        menus.forEach(menu -> {
                    if (menu != null) {
                        List<Menu> menuList = menuService.findByPid(menu.getId());
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", menu.getId());
                        map.put("label", menu.getName());
                        if (menuList != null && menuList.size() != 0) {
                            map.put("children", getMenuTree(menuList));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }

    @Override
    public List<MenuDTO> getMenus(MenuReq req) {
        QueryCriteria query = new QueryCriteria();
        BeanUtils.copyProperties(req, query);
        List<Menu> entityList = menuService.findByCriteria(query);
        List<MenuDTO> dtoList = (List<MenuDTO>) BeanUtils.convert(entityList, MenuDTO.class);
        return buildTree(dtoList);
    }

    @Override
    public void create(MenuReq req) {
        req.setId(null);
        if (menuService.findByName(req.getName()) != null) {
            throw new BaseException("");
        }
        if (req.getFrame() != null && req.getFrame()) {
            if (!(req.getPath().toLowerCase().startsWith("http://") || req.getPath().toLowerCase().startsWith("https://"))) {
                throw new BaseException(ResponseCode.HTTP_URL_ERROR);
            }
        }
        Menu entity = new Menu();
        BeanUtils.copyProperties(req, entity);
        menuService.save(entity);
    }

    @Override
    public void update(MenuReq req) {
        if (req.getId().equals(req.getParentId())) {
            throw new BaseException(ResponseCode.PARENT_NOT_SELF_ERROR);
        }
        if (req.getFrame() != null && req.getFrame()) {
            if (!(req.getPath().toLowerCase().startsWith("http://") || req.getPath().toLowerCase().startsWith("https://"))) {
                throw new BaseException(ResponseCode.HTTP_URL_ERROR);
            }
        }
        Menu m1 = menuService.findById(req.getId());
        Menu m2 = menuService.findByName(req.getName());
        if (m2 != null && !m2.getId().equals(m1.getId())) {
            throw new BaseException(ResponseCode.MENU_EXISTS_ERROR);
        }
        Menu entity = new Menu();
        BeanUtils.copyProperties(req, entity);
        entity.setId(m1.getId());
        menuService.updateEntity(entity);
    }

    @Override
    public void delete(Long id) {
        List<Menu> menuList = menuService.findByPid(id);
        for (Menu menu : menuList) {
            roleMenuService.removeByMenuId(menu.getId());
            menuService.deleteById(menu.getId());
        }
        roleMenuService.removeByMenuId(id);
        menuService.deleteById(id);
    }

    private List<MenuVO> buildMenu(List<MenuDTO> trees) {
        List<MenuVO> list = new LinkedList<>();
        trees.forEach(menuDTO -> {
                    if (menuDTO != null) {
                        List<MenuDTO> menuDTOList = menuDTO.getChildren();
                        MenuVO menuVo = new MenuVO();
                        menuVo.setName(menuDTO.getName());
                        menuVo.setPath(menuDTO.getPath());

                        // 如果不是外链
                        if (!menuDTO.getFrame()) {
                            if (menuDTO.getParentId().equals(0L)) {
                                //一级目录需要加斜杠，不然访问 会跳转404页面
                                menuVo.setPath("/" + menuDTO.getPath());
                                menuVo.setComponent(StringUtils.isEmpty(menuDTO.getComponent()) ? "Layout" : menuDTO.getComponent());
                            } else if (!StringUtils.isEmpty(menuDTO.getComponent())) {
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVO(menuDTO.getName(), menuDTO.getIcon()));
                        if (menuDTOList != null && menuDTOList.size() != 0) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenu(menuDTOList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menuDTO.getParentId().equals(0L)) {
                            MenuVO menuVo1 = new MenuVO();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if (!menuDTO.getFrame()) {
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVO> list1 = new ArrayList<MenuVO>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    private List<MenuDTO> buildTree(List<MenuDTO> voList) {
        List<MenuDTO> trees = new ArrayList<>();
        if (voList!=null) {
            for (MenuDTO menuDTO : voList) {
                if ("0".equals(menuDTO.getParentId().toString())) {
                    trees.add(menuDTO);
                }
                for (MenuDTO it : voList) {
                    if (it.getParentId().equals(menuDTO.getId())) {
                        if (menuDTO.getChildren() == null) {
                            menuDTO.setChildren(new ArrayList<MenuDTO>());
                        }
                        menuDTO.getChildren().add(it);
                    }
                }
            }
        }
        return trees.size() == 0 ? voList : trees;
    }
}
