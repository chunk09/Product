package kr.lamgda.product.controller;

import kr.lamgda.product.entity.Product;
import kr.lamgda.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/write")
    public String WriteForm() {
        return "ProductWrite";
    }

    @PostMapping("/write/pro")
    public String WritePro(Product product, Model model) {
        service.write(product);

        model.addAttribute("message", "상품 등록이 완료되었습니다.");
        model.addAttribute("searchUrl", "/list");

        return "message";
    }

    @GetMapping("/list")
    public String ProductList(Model model) {
        model.addAttribute("list", service.getList());

        return "/ProductList";
    }

    @GetMapping("/view")
    public String ProductView(Model model, int id) {
        model.addAttribute("product", service.getData(id));

        return "ProductView";
    }

    @GetMapping("/modify/{id}")
    public String ProductModify(@PathVariable("id") int id, Product product, Model model) {
        model.addAttribute("product", service.getData(id));

        return "ProductModify";
    }

    @PostMapping("/update/{id}")
    public String ProductUpdate(@PathVariable("id") int id, Product product, Model model) {
        Product productTemp = service.getData(id);

        productTemp.setName(product.getName());
        productTemp.setPrice(product.getPrice());
        productTemp.setMount(product.getMount());

        service.write(productTemp);

        model.addAttribute("message", "수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/list");

        return "message";
    }

}
