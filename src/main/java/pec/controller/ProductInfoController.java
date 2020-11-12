package pec.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.dto.CategoryDto;
import pec.dto.InventoryInfoDto;
import pec.dto.MedicineInfoDto;
import pec.dto.PackagingInfoDto;
import pec.dto.ProductInfoDto;
import pec.service.ProductInfoService;
import pec.datacode.AdminPageInfo;


@Controller
@RequestMapping("/admin/productInfo")
public class ProductInfoController {
	
	@Autowired
	ProductInfoService productInfoService;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.PRODUCT_LIST.redirectUrl;
	}
		
	/*@RequestMapping(method=RequestMethod.GET)
	public String initialize(Model model) throws IOException {
		List<ProductInfoDto> productList= productInfoService.getProductList();
		model= productInfoService.messageProperty(model);
		model.addAttribute("productList", productList);
		model.addAttribute("data_tb","save");
		return AdminPageInfo.PRODUCT_LIST.template;
	}*/
	
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
	
	@RequestMapping(method=RequestMethod.GET)
	public String productList(Model model, HttpServletRequest req) throws IOException {
		/*Map<String, String[]> reqMap = req.getParameterMap();
		reqMap.forEach((key,value)-> {
			model.addAttribute(key, value[0]);
		});*/
		
		List<ProductInfoDto> productList= productInfoService.getProductList();
		model= productInfoService.messageProperty(model);
		model.addAttribute("productList", productList);
		model.addAttribute("productInfoDto", new ProductInfoDto());
		model.addAttribute("data_tb","save");
		return AdminPageInfo.PRODUCT_LIST.template;
	}
	
	@RequestMapping(params="register", method=RequestMethod.POST)
	public String register(Model model,/* @Valid*/ ProductInfoDto prdInfoDto, BindingResult res, RedirectAttributes attr) throws IOException {
		model = productInfoService.messageProperty(model);
		int count = productInfoService.getDuplicate(prdInfoDto.getProduct_code());
		
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		
		if(count != 0) {
			model.addAttribute("errormsg",prop.getProperty("E000130"));
		}else {
			prdInfoDto.setProduct_image(prdInfoDto.getProductimage().getBytes());
			prdInfoDto.setProduct_image_name(prdInfoDto.getProductimage().getOriginalFilename());

			String filePath="src/main/resources/static/images/product_image/";
			File imageFile = new File(filePath+prdInfoDto.getProductimage().getOriginalFilename()); // we can put any name of file (just name of new file created).
			FileOutputStream outputStream;	
			outputStream = new FileOutputStream(imageFile);
			outputStream.write(prdInfoDto.getProduct_image());
			outputStream.close();
			
			productInfoService.addProduct(prdInfoDto);
			
			InventoryInfoDto inventoryDto = new InventoryInfoDto();
			inventoryDto.setProduct_info_id(prdInfoDto.getProduct_info_id());	
			productInfoService.addProductQuantity(inventoryDto);
			
			attr.addFlashAttribute("msg", "Product add Successful.");
			return AdminPageInfo.PRODUCT_INSERT.redirectUrl;
		}
		
		List<ProductInfoDto> productList= productInfoService.getProductList();
		model.addAttribute("productList", productList);
		model.addAttribute("productInfoDto", prdInfoDto);
		return AdminPageInfo.PRODUCT_LIST.template;
		
	}
}
