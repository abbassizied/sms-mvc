package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.entities.Image;
import io.github.abbassizied.sms.entities.Product;
import io.github.abbassizied.sms.entities.Supplier;
import io.github.abbassizied.sms.forms.ProductForm;
import io.github.abbassizied.sms.forms.OnCreate;
import io.github.abbassizied.sms.forms.OnUpdate;
import io.github.abbassizied.sms.services.ProductService;
import io.github.abbassizied.sms.services.StorageService;
import io.github.abbassizied.sms.services.SupplierService;
import io.github.abbassizied.sms.utils.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private MessageSource messageSource;

	private final ProductService productService;
	private final SupplierService supplierService;
	private final StorageService storageService;

	// Constructor injection
	public ProductController(ProductService productService, SupplierService supplierService,
			StorageService storageService) {
		this.productService = productService;
		this.supplierService = supplierService;
		this.storageService = storageService;
	}

	// Redirect root URL to the paginated list view
	@GetMapping
	public String index() {
		return "redirect:products/list";
	}

	@GetMapping("/list")
	public String listProducts(Model model) {
		model.addAttribute("products", productService.listProducts());
		return "product/listProducts";
	}

	@GetMapping("/add")
	public String addProductForm(Model model) {
		ProductForm productForm = new ProductForm();
		// Assuming you have a list of suppliers
		List<Supplier> suppliers = supplierService.listSuppliers();
		model.addAttribute("productForm", productForm);
		model.addAttribute("suppliers", suppliers);
		return "product/addProduct";
	}

	@PostMapping("/add")
	public String addProduct(@Validated(OnCreate.class) @ModelAttribute("productForm") ProductForm productForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "product/addProduct"; // Return the form if validation fails
		}

		String mainImageName = storageService.storeSingleFile(productForm.getMainImage());

		// Handle successful form submission
		Product product = new Product();
		// Mapping simple fields
		product.setName(productForm.getName());
		product.setMainImage(mainImageName); // be carefull
		product.setDescription(productForm.getDescription());
		product.setPrice(productForm.getPrice());
		product.setQuantity(productForm.getQuantity());
		product.setReorderLevel(productForm.getReorderLevel());
		product.setSupplier(productForm.getSupplier());

		// Use a list to keep track of the stored image names for potential cleanup
		List<String> storedImageNames = new ArrayList<>();

		// Process uploaded files and convert them directly to Image entities in one
		// stream operation
		List<Image> images = productForm.getImages().stream().filter(file -> !file.isEmpty()) // Skip empty files
				.map(imageFile -> {
					// Store the uploaded file and retrieve its stored name
					String imageName = storageService.storeSingleFile(imageFile);

					// Track the stored file name for potential rollback
					storedImageNames.add(imageName);

					// Create and configure the Image entity
					Image image = new Image();
					image.setUrl(imageName); // Set the stored file name as the URL
					image.setProduct(product); // Establish the relationship with the current Product
					return image; // Return the created Image entity
				}).collect(Collectors.toList()); // Collect all Image entities into a list

		// Set the images on the product
		product.setImages(images);

		try {
			// Save the product
			productService.saveProduct(product);
			// Add success alert
			redirectAttributes.addFlashAttribute("alert", new Alert("success",
					messageSource.getMessage("success.create", null, LocaleContextHolder.getLocale())));
		} catch (Exception e) {
			// Delete any stored images in case of an error
			storageService.deleteAll(storedImageNames);
			// Add an error alert
			redirectAttributes.addFlashAttribute("alert", new Alert("danger",
					messageSource.getMessage("error.server", null, LocaleContextHolder.getLocale())));
		}
		return "redirect:/products/list";
	}

	@GetMapping("/show/{id}")
	public String showProduct(@PathVariable("id") long id, Model model) {

		model.addAttribute("product", productService.getProductById(id));
		return "product/showProduct";
	}

	@GetMapping("/update/{id}")
	public String updateProductForm(@PathVariable("id") long id, Model model) {

		Product product = productService.getProductById(id);

		ProductForm productForm = new ProductForm();
		productForm.setId(product.getId());
		productForm.setName(product.getName());
		// You don't need to set the mainImage as it's a file input in the form
		productForm.setDescription(product.getDescription());
		productForm.setPrice(product.getPrice());
		productForm.setQuantity(product.getQuantity());
		productForm.setReorderLevel(product.getReorderLevel());
		productForm.setSupplier(product.getSupplier());

		// Associate the list of suppliers to view
		List<Supplier> suppliers = supplierService.listSuppliers();
		model.addAttribute("suppliers", suppliers);

		model.addAttribute("productForm", productForm);
		return "product/updateProduct";
	}

	@PostMapping("/update/{id}")
	public String updateProduct(@PathVariable("id") long id,
			@Validated(OnUpdate.class) @ModelAttribute("ProductForm") ProductForm productForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "product/updateProduct";
		}

		// Fetch the existing product entity
		Product product = productService.getProductById(id);

		// Initialize a list to keep track of newly uploaded images for cleanup
		List<String> newlyUploadedImages = new ArrayList<>();

		// Handle the mainImage file (only update if a new file is uploaded)
		if (productForm.getMainImage() != null && !productForm.getMainImage().isEmpty()) {
			// Delete old existing main image from storage
		    // Check if the product has an existing main image
		    if (product.getMainImage() != null && !product.getMainImage().isEmpty()) {
		        // Delete the old existing main image from storage
		        storageService.delete(product.getMainImage());
		    }

			// Upload the new main image
			String mainImageFileName = storageService.storeSingleFile(productForm.getMainImage());
			product.setMainImage(mainImageFileName); // Update mainImage
		}

		// Handle additional Images (only update if new files are uploaded)
		if (productForm.getImages() != null && productForm.getImages().stream().anyMatch(file -> !file.isEmpty())) {
			// Delete all existing images in storage in one operation
			product.getImages().stream().map(Image::getUrl) // Extract the URL of each image
					.forEach(storageService::delete); // Delete each file using the storage service

			// Process and upload new images, creating Image entities in one stream
			// operation
			List<Image> newImages = productForm.getImages().stream().filter(file -> !file.isEmpty()) // Process only
																										// non-empty
																										// files
					.map(imageFile -> {
						// Store the uploaded file and retrieve its stored name
						String imageFileName = storageService.storeSingleFile(imageFile);

						newlyUploadedImages.add(imageFileName); // Track the new file name

						// Create a new Image entity and set its properties
						Image image = new Image();
						image.setUrl(imageFileName); // Set the file name as the URL
						image.setProduct(product); // Associate the image with the current product
						return image; // Return the created Image entity
					}).collect(Collectors.toList()); // Collect all Image entities into a list

			// Update the product's list of images with the new list
			product.setImages(newImages);
		}

		// Update other fields
		product.setName(productForm.getName());
		product.setDescription(productForm.getDescription());
		product.setPrice(productForm.getPrice());
		product.setQuantity(productForm.getQuantity());
		product.setReorderLevel(productForm.getReorderLevel());

		try { 
			// Save updated product
			productService.saveProduct(product); 
			redirectAttributes.addFlashAttribute("alert", new Alert("success",
					messageSource.getMessage("success.update", null, LocaleContextHolder.getLocale())));
		} catch (Exception e) {
			// Delete the newly uploaded images in case of failure
			storageService.deleteAll(newlyUploadedImages);

			// Add an error alert
			redirectAttributes.addFlashAttribute("alert", new Alert("danger",
					messageSource.getMessage("error.server", null, LocaleContextHolder.getLocale())));
		}

		 return "redirect:/products/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {

		Product product = productService.getProductById(id);
		// Delete old existing image from storage
		storageService.delete(product.getMainImage());
		// Extract the URLs from the images and pass them to the deleteAll method
		List<String> imageUrls = product.getImages().stream().map(Image::getUrl) // Extract the 'url' field from each
																					// Image object
				.collect(Collectors.toList());

		storageService.deleteAll(imageUrls);

		// Then, delete product
		try {
			productService.deleteProductById(id);
			redirectAttributes.addFlashAttribute("alert", new Alert("success",
					messageSource.getMessage("success.delete", null, LocaleContextHolder.getLocale())));
		} catch (Exception e) {
			// Add an error alert
			redirectAttributes.addFlashAttribute("alert", new Alert("danger",
					messageSource.getMessage("error.server", null, LocaleContextHolder.getLocale())));
		}

		return "redirect:/products/list";
	}

}
