package com.tobias.saul.catalogservice.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.tobias.saul.catalogservice.controller.ProductController;
import com.tobias.saul.catalogservice.pojos.Product;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>>{

	@Override
	public EntityModel<Product> toModel(Product product) {
		return new EntityModel<Product>(product,
				linkTo(methodOn(ProductController.class).productByCode(product.getCode())).withSelfRel(),
				linkTo(methodOn(ProductController.class).allProducts()).withRel("products"));
	}

}
