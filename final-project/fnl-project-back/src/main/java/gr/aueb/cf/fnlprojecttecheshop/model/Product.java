package gr.aueb.cf.fnlprojecttecheshop.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = true)
    private String shortDesc;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
////    @Getter(AccessLevel.PROTECTED)
//    private Category category;

}
