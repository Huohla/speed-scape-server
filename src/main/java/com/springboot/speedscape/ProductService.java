package com.springboot.speedscape;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductEntity> getProducts() {
        return repository.findAll();
    }

    public ProductEntity create(NewProduct newProduct) {
        return repository.saveAndFlush(new ProductEntity(newProduct.name(), newProduct.description()));
    }

    public List<ProductEntity> search(ProductSearch productSearch) {
        if(StringUtils.hasText(productSearch.name())
        && StringUtils.hasText(productSearch.description())) {
            return repository.findByNameContainsOrDescriptionContainsAllIgnoreCase ( //
                productSearch.name(), productSearch.description());
        }
        
        if (StringUtils.hasText(productSearch.name())) {
            return repository.findByNameContainsIgnoreCase(productSearch.name());
        }
      
        if (StringUtils.hasText(productSearch.description())) {
            return repository.findByDescriptionContainsIgnoreCase(productSearch.description());
        }
      
        return Collections.emptyList();
    }

    public List<ProductEntity> search(UniversalSearch search) {
        ProductEntity probe = new ProductEntity();
        if (StringUtils.hasText(search.value())) {
          probe.setName(search.value());
          probe.setDescription(search.value());
        }
        Example<ProductEntity> example = Example.of(probe, //
          ExampleMatcher.matchingAny() //
            .withIgnoreCase() //
            .withStringMatcher(StringMatcher.CONTAINING));
        return repository.findAll(example);
      }

    /*private List<NewProduct> products = List.of(
        new NewProduct("Vela Caldero - Buena Suerte", "Vela en forma de caldero de color verde que atrae la buena suerte."),
        new NewProduct("Porta incienso gato salem", "Portador de inciensos con diseño de un gato negro de Salem."),
        new NewProduct("Cajita Wicca Bola Mágica", "Bola mágica protegida en una cajita."),
        new NewProduct("Esfera de Obsidiana Negra", "Bola mágica de obsidiana."),
        new NewProduct("Bolsita para Hechizos", "Bolsa para guardar materiales de herbología o lo que gustes."),
        new NewProduct("Péndulo para tomar decisiones", "Si eres indeciso, usa este péndulo para que decida por ti."),
        new NewProduct("Vela de miel", "Vela hecha con miel, tiene su olor y textura de panal."),
        new NewProduct("Plato cerámico Bastet", "Plato hecho de cerámica con diseño de la diosa Bastet."),
        new NewProduct("Kit de Adivinación Bastet", "Un Kit con diferentes elementos para sesiones de adivinación.")
    );*/

     /*@PostConstruct
     void initDatabase() {
         repository.save(new ProductEntity("Vela Caldero - Buena Suerte",
         "Vela en forma de caldero de color verde que atrae la buena suerte."));
         repository.save(new ProductEntity("Porta incienso gato salem",
         "Portador de inciensos con diseño de un gato negro de Salem."));
         repository.save(new ProductEntity("Cajita Wicca Bola Mágica",
         "Bola mágica protegida en una cajita."));
         repository.save(new ProductEntity("Esfera de Obsidiana Negra",
         "Bola mágica de obsidiana."));
         repository.save(new ProductEntity("Bolsita para Hechizos",
         "Bolsa para guardar materiales de herbología o lo que gustes."));
         repository.save(new ProductEntity("Péndulo para tomar decisiones",
         "Si eres indeciso, usa este péndulo para que decida por ti."));
         repository.save(new ProductEntity("Vela de miel",
         "Vela hecha con miel, tiene su olor y textura de panal."));
         repository.save(new ProductEntity("Plato cerámico Bastet",
         "Plato hecho de cerámica con diseño de la diosa Bastet."));
         repository.save(new ProductEntity("Kit de Adivinación Bastet",
         "Un Kit con diferentes elementos para sesiones de adivinación."));
     }*/
    
    /*Sort sort = Sort.by("product_name").ascending()
    .and(Sort.by("description").descending());*/
}
