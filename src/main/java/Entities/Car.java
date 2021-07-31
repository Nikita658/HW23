package Entities;

import Entities.GearTypes;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "test.car")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private String price;

    @Column(name = "manufacture_date")
    private String manufactureDate;

    @Column(name = "sell_date")
    private String sellDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gear_type")
    private GearTypes gearType;

    @Column(name = "fuel_volume")
    private int fuelVolume;

    public Car(String title, String price, String manufactureDate, String sellDate, GearTypes gearType, int fuelVolume) {
        this.title = title;
        this.price = price;
        this.manufactureDate = manufactureDate;
        this.sellDate = sellDate;
        this.gearType = gearType;
        this.fuelVolume = fuelVolume;
    }
}