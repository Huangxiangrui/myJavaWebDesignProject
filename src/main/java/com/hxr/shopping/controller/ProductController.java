package com.hxr.shopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxr.shopping.pojo.Businessman;
import com.hxr.shopping.pojo.ProductInfo;
import com.hxr.shopping.pojo.ProductType;
import com.hxr.shopping.service.BusinessmanService;
import com.hxr.shopping.service.ProductInfoService;
import com.hxr.shopping.service.ProductTypeService;
import com.hxr.shopping.utils.Code;
import com.hxr.shopping.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("prod")
public class ProductController {
    final private int PAGESIZE = 5;
    @Autowired
    ProductTypeService productTypeService;
    @Autowired
    ProductInfoService productInfoService;
    @Value("${file-save-path}")
    private String fileSavePath;

    //图片上传
    @PostMapping("/upload")
    public R uploadimg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        File dir = new File(fileSavePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        System.out.println(suffix);
        if(suffix.equals(".jpg")||suffix.equals(".png")) {
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
            System.out.println(newFileName);
            File newFile = new File(fileSavePath + newFileName);

            try {
                file.transferTo(newFile);//transferTo()方法将此文件复制到另一个文件newFile中。
                //String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + newFileName;
                return new R(Code.WORK_OK, "上传成功", newFileName);
            } catch (IOException e) {
                return new R(Code.WORK_ERR, "IO异常");
            }
        }else{
            return new R(Code.WORK_ERR,"文件类型不对");
        }
    }

    @PostMapping("/typelist")
    public R getAllProductType() {
        List<ProductType> productList = productTypeService.getAll();
        Map<Integer, Object> map = new HashMap<>();
        if (productList != null) {
            for (ProductType product : productList) {
                map.put(product.getTypeId(), product.getTypeName());
            }
            return new R(Code.WORK_OK, "getlist", map);
        } else {
            return new R(Code.WORK_ERR, "notype");
        }
    }

    @PostMapping("/productlist/{current}")
    public Page<ProductInfo> getProductPage(@PathVariable("current") long current, @RequestBody ProductInfo productInfo) {
        Page<ProductInfo> page = new Page<>(current, PAGESIZE);
        QueryWrapper<ProductInfo> wrapper = new QueryWrapper<>();
        if (productInfo.getBid() != null) {
            wrapper.eq("b_id", productInfo.getBid());
        }
        if (productInfo.getPname() != null && !productInfo.getPname().equals("")) {
            wrapper.like("p_name", productInfo.getPname());
        }
        if (productInfo.getTypeId() != null) {
            wrapper.eq("type_id", productInfo.getTypeId());
        }
        Page<ProductInfo> productInfoList = productInfoService.productPage(page, wrapper);
        if (productInfoList == null) {
            return null;
        } else {
            return productInfoList;
        }
    }
    @PostMapping("/getoneprod")
    public R getoneProd(@RequestBody ProductInfo productInfo){
        productInfo = productInfoService.getOneProductInfo(productInfo.getPid());
        if(productInfo!=null) {
            return new R(Code.WORK_OK, "查询成功", productInfo);
        }else{
            return new R(Code.WORK_ERR,"查询失败");
        }
    }
    @PostMapping("/updprod")
    public R updproduct(@RequestBody ProductInfo productInfo){
        int flag = -1;
        try {
            flag = productInfoService.updOneProductInfo(productInfo);
            if(flag>0){
                return new R(Code.WORK_OK,"更新成功",productInfo);
            }else{
                return new R(Code.WORK_ERR,"更新失败");
            }
        } catch (Exception e) {
                return new R(Code.WORK_ERR,"无效");
        }
    }
    @PostMapping("/addprod")
    public R addOneProduct(@RequestBody ProductInfo productInfo) {
        int flag = -1;
        try {
            flag = productInfoService.addProductInfo(productInfo);
            if(flag>0){
                return new R(Code.WORK_OK,"增加成功",productInfo);
            }else{
                return new R(Code.WORK_ERR,"增加失败");
            }
        } catch (Exception e) {
            return new R(Code.WORK_ERR,"无效");
        }
    }

    @PostMapping("/delprod")
    public R delOneProduct(@RequestBody ProductInfo productInfo) {
        ProductInfo productInfo1 = productInfoService.getOneProductInfo(productInfo.getPid());
        int flag = -1;
        try {
            flag = productInfoService.delOneProductInfo(productInfo);
            if(flag>0){
                return new R(Code.WORK_OK,"删除成功",productInfo);
            }else{
                return new R(Code.WORK_ERR,"删除失败");
            }
        } catch (Exception e) {
            return new R(Code.WORK_ERR,"无效");
        }
    }

}
