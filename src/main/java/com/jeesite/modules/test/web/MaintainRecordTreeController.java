/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import java.util.List;
import java.util.Map;

import com.jeesite.modules.test.entity.ComponentInfo;
import com.jeesite.modules.test.entity.EquipInfo;
import com.jeesite.modules.test.entity.TroubleNotice;
import com.jeesite.modules.test.service.ComponentInfoService;
import com.jeesite.modules.test.service.EquipInfoService;
import com.jeesite.modules.test.service.TroubleNoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.test.entity.MaintainRecordTree;
import com.jeesite.modules.test.service.MaintainRecordTreeService;

/**
 * 维修工单库Controller
 * @author dyl
 * @version 2018-08-24
 */
@Controller
@RequestMapping(value = "${adminPath}/test/maintainRecordTree")
public class MaintainRecordTreeController extends BaseController {

	@Autowired
	private MaintainRecordTreeService maintainRecordTreeService;

	@Autowired
	private EquipInfoService equipInfoService;

	@Autowired
	private ComponentInfoService componentInfoService;

	@Autowired
	private TroubleNoticeService troubleNoticeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public MaintainRecordTree get(String id, boolean isNewRecord) {
		return maintainRecordTreeService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:maintainRecordTree:view")
	@RequestMapping(value = {"list", ""})
	public String list(MaintainRecordTree maintainRecordTree, Model model) {
		model.addAttribute("maintainRecordTree", maintainRecordTree);
		return "modules/test/maintainRecordTreeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:maintainRecordTree:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<MaintainRecordTree> listData(MaintainRecordTree maintainRecordTree) {
		if (StringUtils.isBlank(maintainRecordTree.getParentCode())) {
			maintainRecordTree.setParentCode(MaintainRecordTree.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getRecordCode())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getRecordTreeName())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getNoticeCode())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getMaintainerCode())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getEquipCode())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getMaintainPart())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getComponentCode())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getComponentName())){
			maintainRecordTree.setParentCode(null);
		}
//		if (StringUtils.isNotBlank(maintainRecordTree.getComponentNumber())){
//			maintainRecordTree.setParentCode(null);
//		}
		//手动修改，将Date类型转化为CharSequence
		if (StringUtils.isEmpty((CharSequence)maintainRecordTree.getMaintainStartTime())){
			maintainRecordTree.setParentCode(null);
		}
//		if (StringUtils.isNotBlank(maintainRecordTree.getMaintainStartTime())){
//			maintainRecordTree.setParentCode(null);
//		}
//		手动修改，将Date类型转化为CharSequence
		if (StringUtils.isEmpty((CharSequence)maintainRecordTree.getMaintainEndTime())){
			maintainRecordTree.setParentCode(null);
		}
//		if (StringUtils.isNotBlank(maintainRecordTree.getMaintainEndTime())){
//			maintainRecordTree.setParentCode(null);
//		}
//		if (StringUtils.isNotBlank(maintainRecordTree.getMaintainHours())){
//			maintainRecordTree.setParentCode(null);
//		}
		if (StringUtils.isNotBlank(maintainRecordTree.getMaintainContent())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getMaintainMethod())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getMaintainRunStatus())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getMaintainResultDepict())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getMaintainResultType())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getTroubleType())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getStatus())){
			maintainRecordTree.setParentCode(null);
		}
		if (StringUtils.isNotBlank(maintainRecordTree.getRemarks())){
			maintainRecordTree.setParentCode(null);
		}
		List<MaintainRecordTree> list = maintainRecordTreeService.findList(maintainRecordTree);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:maintainRecordTree:view")
	@RequestMapping(value = "form")
	public String form(MaintainRecordTree maintainRecordTree, Model model) {
		//从数据库获取所有设备信息传到前端显示
		EquipInfo equipInfo = new EquipInfo();
		List<EquipInfo> equipInfoList = equipInfoService.findList(equipInfo);
		model.addAttribute("equipInfoList", equipInfoList);
		//从数据库获取所有备品备件信息传到前端显示
		ComponentInfo componentInfo = new ComponentInfo();
		List<ComponentInfo> componentInfoList = componentInfoService.findList(componentInfo);
		model.addAttribute("componentInfoList", componentInfoList);
		//从数据库获取通知单信息传到前端显示
		TroubleNotice troubleNotice = new TroubleNotice();
		List<TroubleNotice> troubleNoticeList = troubleNoticeService.findList(troubleNotice);
		model.addAttribute("troubleNoticeList", troubleNoticeList);
		// 创建并初始化下一个节点信息
		maintainRecordTree = createNextNode(maintainRecordTree);
		model.addAttribute("maintainRecordTree", maintainRecordTree);
		return "modules/test/maintainRecordTreeForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("test:maintainRecordTree:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public MaintainRecordTree createNextNode(MaintainRecordTree maintainRecordTree) {
		if (StringUtils.isNotBlank(maintainRecordTree.getParentCode())){
			maintainRecordTree.setParent(maintainRecordTreeService.get(maintainRecordTree.getParentCode()));
		}
		if (maintainRecordTree.getIsNewRecord()) {
			MaintainRecordTree where = new MaintainRecordTree();
			where.setParentCode(maintainRecordTree.getParentCode());
			MaintainRecordTree last = maintainRecordTreeService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				maintainRecordTree.setTreeSort(last.getTreeSort() + 30);
				maintainRecordTree.setRecordCode(IdGen.nextCode(last.getRecordCode()));
			}else if (maintainRecordTree.getParent() != null){
				maintainRecordTree.setRecordCode(maintainRecordTree.getParent().getRecordCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (maintainRecordTree.getTreeSort() == null){
			maintainRecordTree.setTreeSort(MaintainRecordTree.DEFAULT_TREE_SORT);
		}
		return maintainRecordTree;
	}

	/**
	 * 保存维修工单库
	 */
	@RequiresPermissions("test:maintainRecordTree:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated MaintainRecordTree maintainRecordTree) {
		maintainRecordTreeService.save(maintainRecordTree);
		return renderResult(Global.TRUE, text("保存维修工单库成功！"));
	}
	
	/**
	 * 停用维修工单库
	 */
	@RequiresPermissions("test:maintainRecordTree:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(MaintainRecordTree maintainRecordTree) {
		MaintainRecordTree where = new MaintainRecordTree();
		where.setStatus(MaintainRecordTree.STATUS_NORMAL);
		where.setParentCodes("," + maintainRecordTree.getId() + ",");
		long count = maintainRecordTreeService.findCount(where);
		if (count > 0) {
			return renderResult(Global.FALSE, text("该维修工单库包含未停用的子维修工单库！"));
		}
		maintainRecordTree.setStatus(MaintainRecordTree.STATUS_DISABLE);
		maintainRecordTreeService.updateStatus(maintainRecordTree);
		return renderResult(Global.TRUE, text("停用维修工单库成功"));
	}
	
	/**
	 * 启用维修工单库
	 */
	@RequiresPermissions("test:maintainRecordTree:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(MaintainRecordTree maintainRecordTree) {
		maintainRecordTree.setStatus(MaintainRecordTree.STATUS_NORMAL);
		maintainRecordTreeService.updateStatus(maintainRecordTree);
		return renderResult(Global.TRUE, text("启用维修工单库成功"));
	}
	
	/**
	 * 删除维修工单库
	 */
	@RequiresPermissions("test:maintainRecordTree:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(MaintainRecordTree maintainRecordTree) {
		maintainRecordTreeService.delete(maintainRecordTree);
		return renderResult(Global.TRUE, text("删除维修工单库成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("test:maintainRecordTree:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<MaintainRecordTree> list = maintainRecordTreeService.findList(new MaintainRecordTree());
		for (int i=0; i<list.size(); i++){
			MaintainRecordTree e = list.get(i);
			// 过滤非正常的数据
			if (!MaintainRecordTree.STATUS_NORMAL.equals(e.getStatus())){
				continue;
			}
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (e.getId().equals(excludeCode)){
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")){
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getRecordCode(), e.getRecordTreeName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("test:maintainRecordTree:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(MaintainRecordTree maintainRecordTree){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		maintainRecordTreeService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}