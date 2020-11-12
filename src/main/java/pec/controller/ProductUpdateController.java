package pec.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pec.dto.CategoryDto;
import pec.dto.MedicineInfoDto;
import pec.dto.PackagingInfoDto;
import pec.dto.ProductInfoDto;
import pec.service.ProductInfoService;
import pec.datacode.AdminPageInfo;


@Controller
@RequestMapping("/admin/productUpdate")
public class ProductUpdateController {
	
	@Autowired
	ProductInfoService productInfoService;
	
	@ModelAttribute("medicinelist")
	public List<MedicineInfoDto> getMedicineInfoList() {
		List<MedicineInfoDto> res = productInfoService.getMedicineInfoList();
		return res;
	}
	
	@ModelAttribute("categorylist")
	public List<CategoryDto> getCategoryInfoList() {
		List<CategoryDto> res = productInfoService.getCategoryInfoList();
		return res;
	}
	
	@ModelAttribute("packaginglist")
	public List<PackagingInfoDto> getPackagingList() {
		List<PackagingInfoDto> res = productInfoService.getPackagingList();
		return res;
	}
	
	@RequestMapping(value="/{product_info_id}", method=RequestMethod.GET)
	public String update(Model model ,@PathVariable("product_info_id") Integer product_info_id, RedirectAttributes attr, HttpServletRequest req) throws IOException {
			/*Map<String, String[]> reqMap = req.getParameterMap();
			reqMap.forEach((key,value)-> {
				model.addAttribute(key, value[0]);});
			*/
			List<ProductInfoDto> productList= productInfoService.getProductList();
			model= productInfoService.messageProperty(model);
			model.addAttribute("productList", productList);
			model.addAttribute("productInfoDto", new ProductInfoDto());
			model.addAttribute("data_tb","save");
		
			ProductInfoDto product=new ProductInfoDto();
			product=productInfoService.getProduct(product_info_id);
			model.addAttribute("productInfoDto",product);
			return AdminPageInfo.PRODUCT_UPDATE.template;
	}
	
	@RequestMapping(params="update", method=RequestMethod.POST)
	public String update(Model model,/*@Valid*/ ProductInfoDto prdInfoDto , BindingResult res, RedirectAttributes attr) throws IOException {
		if(res.hasErrors()) {
			attr.addFlashAttribute("msg","<p style='color:red'>Input is invalid!</p>");
			return AdminPageInfo.PRODUCT_UPDATE.redirectUrl;		
		}

		if(prdInfoDto.getProductimage().getOriginalFilename().equalsIgnoreCase("") || prdInfoDto.getProductimage().getOriginalFilename() == null) {
			productInfoService.updateProduct(prdInfoDto);
		} else {
			prdInfoDto.setProduct_image(prdInfoDto.getProductimage().getBytes());
			prdInfoDto.setProduct_image_name(prdInfoDto.getProductimage().getOriginalFilename());
			productInfoService.updateProductWithImage(prdInfoDto);
		}
		
		productInfoService.updateProduct(prdInfoDto);
		attr.addFlashAttribute("msg", "Product successfully updated");
		return AdminPageInfo.PRODUCT_LIST.redirectUrl;
	}
	
}
