package com.highcom.admin.controller;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.highcom.admin.pojo.Company;
import com.highcom.admin.pojo.Result;
import com.highcom.admin.service.AssignConfig;
import com.highcom.admin.service.CompanyService;
import com.highcom.common.UploadUtil;
import io.swagger.annotations.*;
@Api(tags = "后台管理-普通企业管理")
@RestController
@RequestMapping("/admin")
public class AdminNormalCompanyController {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
    private AssignConfig assignConfig;
	
	/**
	 * layui分页查询 普通企业列表
	 * @param comName 自定义参烽
	 * @param management  自定义参烽
	 * @param searchbegin  自定义参烽
	 * @param searchend  自定义参烽
	 * @param page 固定参数名,页码
	 * @param limit  固定参数名 页面大小
	 * @return layui固定的数据格式
	
	@ApiOperation(value = "查询企业信息列表")
	@ApiImplicitParams({ 
		    @ApiImplicitParam(name = "comName", value = "企业名称", paramType = "query"),
			@ApiImplicitParam(name = "management", value = "主营业务", paramType = "query"),
			@ApiImplicitParam(name = "searchbegin", value = "开始时间", paramType = "query"),
			@ApiImplicitParam(name = "searchend", value = "结束时间", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "当前页码数", paramType = "query"),
			@ApiImplicitParam(name = "limit", value = "页面大小", paramType = "query") 
		    })
	@RequestMapping(value="/normalCompanyList", method = RequestMethod.GET)
	public  Map<String, Object> normalCompanyList(
									String comName, 
									String management, 
									String searchbegin,
									String searchend,
									Integer page,
						            Integer limit
						            ) {
		
		Company company = new Company();
		company.setComName(comName);
		company.setManagement(management);
		company.setSearchbegin(searchbegin);
		company.setSearchend(searchend);
		company.setPageSize(limit);
		company.setPageNum(page);

		PageHelper.clearPage();
		PageHelper.startPage(company.getPageNum(), company.getPageSize());
		
		List<Company> list = this.companyService.findNormalCompanyList( company);
		PageInfo<Company> pageInfo = new PageInfo<>(list,company.getPageSize());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("data", list);
		map.put("count", pageInfo.getTotal());
		return map;
	}
	 */
	
	@ApiOperation(value = "查询企业信息列表")
	@ApiImplicitParams({ 
		    @ApiImplicitParam(name = "comName", value = "企业名称", paramType = "query"),
			@ApiImplicitParam(name = "management", value = "主营业务", paramType = "query"),
			@ApiImplicitParam(name = "searchbegin", value = "开始时间", paramType = "query"),
			@ApiImplicitParam(name = "searchend", value = "结束时间", paramType = "query"),
			@ApiImplicitParam(name = "page", value = "当前页码数", paramType = "page"),
			@ApiImplicitParam(name = "limit", value = "每页显示记录数量", paramType = "query") 
		    })
	@RequestMapping(value="/normalCompanyList", method = RequestMethod.GET)
	public Result<PageInfo<Company>> normalCompanyList(
									String comName, 
									String management, 
									String searchbegin,
									String searchend,
						            String page,
						            int limit
						            ) {
		Company company = new Company();
		company.setComName(comName);
		company.setManagement(management);
		company.setSearchbegin(searchbegin);
		company.setSearchend(searchend);
		company.setPageNum(Integer.parseInt(page));
		company.setPageSize(limit);
		PageHelper.clearPage();
		PageHelper.startPage(company.getPageNum(), company.getPageSize());
		List<Company> list = this.companyService.findNormalCompanyList( company);
		PageInfo<Company> pageInfo = new PageInfo<>(list,company.getPageSize());
		return new Result<PageInfo<Company>>(200,"success",pageInfo);
	}
	
	
	
	
	@ApiOperation(value = "添加企业信息")
	@RequestMapping(value = "/normalcomsave" ,method = RequestMethod.POST)
	public Result<Company> comSave( Company company) {
		Map<Integer, String> map = UploadUtil.imgsUpload(assignConfig.uploadUrl, company.getFiles());
		company.setMainImage(map.get(0));
		this.companyService.addcompany(company);

		return new Result<Company>(200, "success", company);

	}
	
	@ApiOperation(value = "按主键值查询企业信息")
	@ApiImplicitParam(name = "comid", value = "企业主键值", paramType = "query")
	@RequestMapping(value="/findNormaoCompanyById", method = RequestMethod.GET)
	public Result<Company> findNormaoCompanyById(String comid){
		Company company = this.companyService.findCompanyById(comid);
		return new Result<Company>(200,"",company);
	}
	@ApiOperation(value = "升级为VIP用户")
	@RequestMapping(value="/upgradetovip", method = RequestMethod.POST)
	public Result upgradevip(
								String comid,
								String vipbegin,
								String vipend){
						
		Company company = new Company();
		company.setComid(comid);
		company.setVipbegin(vipbegin);
		company.setVipend(vipend);
		int flag = this.companyService.updateCompany(company);
		
		return new Result(200, "操作成功!");
		
	}
	@ApiOperation(value = "修改企业信息")
	@RequestMapping(value="/updateNormalcompany", method = RequestMethod.POST)
	public Result updateNormalCompany(Company company){
		Map<Integer,String> map = UploadUtil.imgsUpload(assignConfig.uploadUrl, company.getFiles());
		if(map.get(0) != null)
		company.setMainImage(map.get(0));

		this.companyService.updateCompany(company);
		
		return new Result(200, "企业信息修改成功");
		
	}
	@ApiOperation(value = "删除企业信息")
	@ApiImplicitParam(name = "comid", value = "企业主键值", paramType = "query")
	@RequestMapping(value="/deleteNormalCompany",method = RequestMethod.GET)
	public Result<String> deleteNormalCompany(String comid){
		this.companyService.deleteCompany(comid);
		
		return new Result<String>(200, "企业信息删除成功!","");
		
	}
	
}
