package com.highcom.admin.controller;
import java.io.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.github.pagehelper.*;
import com.highcom.admin.pojo.*;
import com.highcom.admin.service.*;
import com.highcom.common.UploadUtil;
import io.swagger.annotations.*;

@Api(tags = "后台管理-VIP企业管理")
@RestController
@RequestMapping("/admin")
public class AdminVipCompanyController {

	@Autowired
	private CompanyService companyService;

	@ApiOperation(value = "添加企业信息")
	@RequestMapping(value = "/vipcomsave", method = RequestMethod.POST)
	public Result<Object> vipComSave(Company company) {
		Map<Integer, String> map = UploadUtil.imgsUpload(AssignConfig.uploadUrl, company.getFiles());
		company.setMainImage(map.get(0));
		this.companyService.addcompany(company);
		return new Result<>(200, "企业信息添加成功", "");
	}

	/**
	 * layui分页查询
	 * @param comName 自定义参烽
	 * @param management  自定义参烽
	 * @param searchbegin  自定义参烽
	 * @param searchend  自定义参烽
	 * @param page 固定参数名,页码
	 * @param limit  固定参数名 页面大小
	 * @return layui固定的数据格式
	 *
	@ApiOperation(value = "查询企业信息列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "comName", value = "企业名称", paramType = "query"),
			@ApiImplicitParam(name = "management", value = "主营业务", paramType = "query"),
			@ApiImplicitParam(name = "searchbegin", value = "开始时间", paramType = "query"),
			@ApiImplicitParam(name = "searchend", value = "结束时间", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "当前页码数", paramType = "query"),
			@ApiImplicitParam(name = "limit", value = "页面大小", paramType = "query"),
	})
	
	
	@RequestMapping(value = "/vipcomlist", method = RequestMethod.GET)
	public  Map<String, Object> vipComList(String comName, String management, String searchbegin, String searchend,
			Integer page,Integer limit)
	 {
		Company company = new Company();
		company.setComName(comName);
		company.setManagement(management);
		company.setSearchbegin(searchbegin);
		company.setSearchend(searchend);
		company.setPageSize(limit);
		company.setPageNum(page);
		
		PageHelper.clearPage();
		PageHelper.startPage(page, limit);
		List<Company> list = this.companyService.findVipCompanyList(company);
		PageInfo<Company> pageInfo = new PageInfo<>(list, company.getPageSize());

		//下面是layui 分页要求返回的固定数据式
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("data", list);
		map.put("count", pageInfo.getTotal());
		map.put("pages", pageInfo.getPages());
		map.put("pageNum", pageInfo.getPageNum());
     	
		return map;
	}
	*/
	
	@RequestMapping(value = "/vipcomlist", method = RequestMethod.GET)
	public Result<PageInfo<Company>> vipComList(String comName, String management, String searchbegin, String searchend,
			Integer page,Integer limit,String sortType
	) {
		Company company = new Company();
		company.setComName(comName);
		company.setManagement(management);
		company.setSearchbegin(searchbegin);
		company.setSearchend(searchend);
		company.setPageSize(limit);
		company.setPageNum(page);
		company.setSortType(sortType);

		PageHelper.clearPage();
		PageHelper.startPage(page, limit);
		List<Company> list = this.companyService.findVipCompanyList(company);
		PageInfo<Company> pageInfo = new PageInfo<>(list, company.getPageSize());

		return new Result<PageInfo<Company>>(200, "", pageInfo);
	}
	

	@ApiOperation(value = "查询企业信息")
	@ApiImplicitParam(name = "comid", value = "企业主键值", paramType = "query")
	@RequestMapping(value = "/findVipCompanyById", method = RequestMethod.GET)
	public Result<Company> findCompanyById(String comid) {
		Company company = this.companyService.findCompanyById(comid);
		return new Result<Company>(200, "", company);
	}
	
	
	@ApiOperation(value = "根据名称查询企业信息")
	@ApiImplicitParam(name = "comName", value = "企业名称", paramType = "query")
	@RequestMapping(value = "/findVipCompanyByName", method = RequestMethod.GET)
	public Result<Company> findCompanyByName(String comName) {
		Company company = this.companyService.findCompanyByName(comName);
		if(company!=null) {
			return new Result<>(-1, "企业名称已经存在", company);
		}
		else {
			return new Result<>(200,"企业名称可用",null);
		}
	}
	
	
	@ApiOperation(value = "根据用户名称查询查询企业信息")
	@ApiImplicitParam(name = "userName", value = "用户名称", paramType = "query")
	@RequestMapping(value = "/findVipCompanyByUserName", method = RequestMethod.GET)
	public Result<Company> findVipCompanyByUserName(String userName) {
		Company company = this.companyService.findVipCompanyByUserName(userName);
		if(company!=null) {
			return new Result<>(-1, "用户名已经存在", company);
		}
		else {
			return new Result<>(200,"用户名可用",null);
		}
	}
	

	@ApiOperation(value = "修改企业信息")
	@RequestMapping(value = "/updateVipCompany", method = RequestMethod.POST)
	public Result<String> updateCompany(Company company) {
		Map<Integer, String> map = UploadUtil.imgsUpload(AssignConfig.uploadUrl, company.getFiles());
		if (map.get(0) != null)
			company.setMainImage(map.get(0));
		this.companyService.updateCompany(company);
		return new Result<>(200,"企业信息更新成功!","");
	}

	@ApiOperation(value = "删除企业信息")
	@ApiImplicitParam(name = "comid", value = "企业主键值", paramType = "query")
	@RequestMapping(value = "/deleteVipCompany", method = RequestMethod.GET)
	public Result<String> deleteCompany(String comid) {
		this.companyService.deleteCompany(comid);
		return new Result<>(200, "企业信息删除成功!", "");
	}
	
	@RequestMapping(value = "/uploadVipDoc", method = RequestMethod.POST)
	public Result<String> uploadVipDoc(@RequestParam("vipdoc") CommonsMultipartFile vipdoc) throws IllegalStateException, IOException {
		String path = AssignConfig.uploadUrl +"viptemplate.doc";    //uploadUrl=E:/upload/
		vipdoc.transferTo(new File(path));
		return new Result<>(200, "文件上传成功!", "");
	}
	
	/**
	 * index.jsp上呈现的合作伙伴列表 , 三种类型,各查询修改日期最近的前8个,这里把查询8条直接写在底层的sql中了
	 */
	@ResponseBody
	@RequestMapping(value = "/indexCompList", method = RequestMethod.GET)
	public Result<Map<Integer,List<Company>>> indexCompList() {
		// 1 中国入驻企业 2 俄罗斯入驻企业 3 事业合作企业
		List<Company> list1 = this.companyService.findVipCompanyListByCategory(1);
		List<Company> list2 = this.companyService.findVipCompanyListByCategory(2);
		List<Company> list3 = this.companyService.findVipCompanyListByCategory(3);
		
		Map<Integer,List<Company>> map=new HashMap<>();
		map.put(1, list1);
		map.put(2, list2);
		map.put(3, list3);
		
		return new Result<>(200, "", map);
	}
}
